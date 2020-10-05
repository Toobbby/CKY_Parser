package Utils;

import java.util.HashSet;
import java.util.Set;

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

    }

    private Set<String> getWordsInSentence(String sentence) {
        Set<String> res = new HashSet<>();
        String[] words = sentence.split(" ");
        for(String word : words) {
            res.add(word);
        }

        return res;
    }
}
