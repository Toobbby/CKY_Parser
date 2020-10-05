package Utils;

import Models.Rule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GrammarReader {
    List<Rule> rules;
    SymbolGenerator symbolGenerator;

    public GrammarReader(String path) throws IOException {
        symbolGenerator = new SymbolGenerator();
        getGrammar(path);
    }

    public List<Rule> getRules() {
        return rules;
    }

    private void getGrammar(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();
        while(line != null) {
            line = line.toLowerCase();
            String[] components = line.split("->");
            if(components.length >= 2) {
                String[] probAndFrom = components[0].split(" ");
                String[] tos = components[1].split(" ");
                if(tos.length > 2) {
                    binarization(probAndFrom, tos);
                }
                else {
                    rules.add(new Rule(probAndFrom[1], components[1], Float.parseFloat(probAndFrom[0])));
                }

            }
            line = br.readLine();
        }

    }

    private void binarization(String[] probAndFrom, String[] tos) {
        while(tos.length > 2) {
            String newRuleTo = tos[tos.length - 2] + " " + tos[tos.length - 1];
            String newRuleFrom = getFrom(newRuleTo);
            if(newRuleFrom == null) {
                String newSymbol = symbolGenerator.getNewSymbol();
                rules.add(new Rule(newSymbol, tos[tos.length - 2] + " " + tos[tos.length - 1], 1));
                tos = updateTos(newSymbol, tos);
            }
            else {
                tos = updateTos(newRuleFrom, tos);
            }
        }
        rules.add(new Rule(probAndFrom[1], tos[0] + " " + tos[1], Float.parseFloat(probAndFrom[0])));

    }

    private String getFrom(String newRuleTo) {
        List<Rule> allRulesWithThisTo = findAllByTo(newRuleTo);
        for(Rule rule : allRulesWithThisTo) {
            if(symbolGenerator.hasSymbol(rule.getFrom())) {
                return rule.getFrom();
            }
        }
        return null;
    }

    private String[] updateTos(String newSymbol, String[] tos) {
        String[] updatedTos = new String[tos.length - 1];
        for(int i = 0; i < updatedTos.length - 1; i++) {
            updatedTos[i] = tos[i];
        }
        updatedTos[updatedTos.length - 1] = newSymbol;
        return updatedTos;
    }

    private List<Rule> findAllByTo(String to) {
        List<Rule> res = new ArrayList<>();
        for(Rule rule : rules) {
            if(rule.getTo().equals(to)) {
                res.add(rule);
            }
        }
        return res;
    }
}
