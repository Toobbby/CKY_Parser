import Models.CKY;
import Models.TreeNode;
import Utils.GrammarReader;
import Utils.Metrics;

import java.io.IOException;
import java.util.List;

public class ProbCKY {

    public static void main(String[] args) throws IOException {
        if(args.length != 3) {
            System.out.println("Usage: \"grammar file path\"  \"A test sentence .\" \"the gold standard s-expression\"");
            return;
        }

        try {
            GrammarReader grammarReader = new GrammarReader(args[0]);
            CKY cky = new CKY(grammarReader);
            List<TreeNode> nodes = cky.parseSentence(args[1]);

            if(nodes.size() == 0) {
                System.out.println("Sentence rejected: There is no possible parse.");
                return;
            }
            else {
                System.out.println("Sentence accepted: There are " + nodes.size() + " possible parse(s).");
                float totalProb = 0f;
                for(TreeNode node : nodes) {
                    String myGrammar = cky.constructedRes(node);
                    Metrics metrics = new Metrics(args[1], myGrammar, args[2]);
                    float[] pr = metrics.getPrecisionAndRecall();
                    totalProb += node.getProb();
                    System.out.println("parse: " + myGrammar);
                    System.out.println("precision: " + pr[0]);
                    System.out.println("recall: " + pr[1]);
                    System.out.println("prob: " + String.format("%.9f", node.getProb()) + " (" + node.getProb() + ") ");
                    System.out.println("-------------------------------");
                }

                System.out.println();
                System.out.println("Total prob of the sentence: " + String.format("%.9f", totalProb) + " (" + totalProb + ") ");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return;
    }
}
