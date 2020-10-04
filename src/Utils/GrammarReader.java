package Utils;

import Models.Rule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Set;

public class GrammarReader {
    List<Rule> rules;
    SymbolGenerator symbolGenerator;

    public GrammarReader(String path) throws FileNotFoundException {
        symbolGenerator = new SymbolGenerator();
        getGrammar(path);
    }

    public List<Rule> getRules() {
        return rules;
    }

    private void getGrammar(String path) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(path));
    }
}
