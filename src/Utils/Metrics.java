package Utils;

import sun.text.normalizer.UCharacterProperty;

import java.util.*;

public class Metrics {
    private String sentence;
    private String myGrammar;
    private String goldStandardGrammar;

    public Metrics(String sentence, String myGrammar, String goldStandardGrammar) {
        this.sentence = sentence;
        this.myGrammar = myGrammar;
        this.goldStandardGrammar = goldStandardGrammar;
    }

    public float[] getPrecisionAndRecall() {
        Set<String> words = getWordsInSentence(sentence);
        List<String> mine = getPartsInGrammar(myGrammar, words);
        List<String> gold = getPartsInGrammar(goldStandardGrammar, words);

        int matchCount = 0;
        for(String myPart : mine) {
            for(String goldPart : gold) {
                if(myPart.equals(goldPart)) {
                    matchCount++;
                }
            }
        }

        float precision = (float)matchCount / mine.size();
        float recall = (float)matchCount / gold.size();
        return new float[]{precision, recall};

    }

    private Set<String> getWordsInSentence(String sentence) {
        Set<String> res = new HashSet<>();
        String[] words = sentence.split(" ");
        for(String word : words) {
            res.add(word);
        }

        return res;
    }

    private List<String> getPartsInGrammar(String grammar, Set<String> words) {
        grammar = grammar.toLowerCase();
        char[] charArray = grammar.toCharArray();

        List<String> parts = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int index = 0;
        for(char c : charArray) {
            if(c == '[' || c == ' ') {
                if(sb.length() > 0) {
                    sb.append("(" + index);
                    stack.add(sb.toString());
                }
                sb = new StringBuilder();
            }
            else if(Character.isAlphabetic(c)) {
                sb.append(c);
            }
            else if(c == ']') {
                if(words.contains(sb.toString())) {
                    index++;
                }
                parts.add(stack.pop() + ", " + index + ")");
                sb = new StringBuilder();
            }
        }
        return parts;

    }
}
