package Models;


import Utils.GrammarReader;

import java.util.ArrayList;
import java.util.List;

public class CKY {
    private GrammarReader grammarReader;

    public CKY(GrammarReader grammarReader) {
        this.grammarReader = grammarReader;
    }

    public List<TreeNode> parseSentence(String sentence) {
        String[] words = sentence.split(" ");

        int n = words.length;
        Cell[][] dp = new Cell[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = i; j >= 0; j--) {
                List<TreeNode> nodes = new ArrayList<>();
                if(j == i) {
                    TreeNode node = new TreeNode(words[i], 1);
                    buildTree(nodes, node, words[i]);
                    if(!nodes.isEmpty()) {
                        Cell cell = new Cell(nodes);
                        dp[j][i] = cell;
                    }
                }

                else {
                    for(int k = i - 1; k >= 0; k--) {
                        if(dp[k + 1][i] == null || dp[j][k] == null) {
                            continue;
                        }
                        List<TreeNode> cell0 = dp[j][k].getNodes();
                        List<TreeNode> cell1 = dp[k + 1][i].getNodes();

                        for(TreeNode node0 : cell0) {
                            for(TreeNode node1 : cell1) {
                                List<Rule> rules = grammarReader.findAllByTo(node0.getWord() + " " + node1.getWord());
                                if(rules.isEmpty()) continue;

                                for(Rule r : rules) {
                                    buildTreeWithLeftRight(nodes, node0, node1, r.getFrom(), node0.getProb() * node1.getProb() * r.getProb());
                                }
                            }
                        }

                        if(!nodes.isEmpty()) {
                            Cell cell = new Cell(nodes);
                            dp[j][i] = cell;
                        }
                    }
                }
            }
        }
        return collectNodes(dp);
    }

    private List<TreeNode> collectNodes(Cell[][] dp) {
        int n = dp.length;
        List<TreeNode> res = new ArrayList<>();

        if(dp[0][n - 1] == null) {
            return res;
        }

        for(TreeNode node : dp[0][n - 1].getNodes()) {
            if(node.getWord().equals("s")) {
                res.add(node);
            }
        }
        return res;

    }

    private void buildTree(List<TreeNode> nodes, TreeNode node, String to) {
        List<Rule> rules = grammarReader.findAllByTo(to);
        if (rules.size() == 0) return;

        for(Rule r : rules) {
            TreeNode parent = new TreeNode(r.getFrom(), node, null, node.getProb() * r.getProb());
            nodes.add(parent);
            buildTree(nodes, parent, r.getFrom());
        }
    }

    private void buildTreeWithLeftRight(List<TreeNode> nodes, TreeNode left, TreeNode right, String to, float prob) {
        TreeNode parent = new TreeNode(to, left, right, prob);
        nodes.add(parent);
        buildTree(nodes, parent, to);
    }

    public String constructedRes(TreeNode node) {
        if(node.getLeft() == null || node.getRight() == null) {
            return " " + node.getWord();
        }

        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        StringBuilder res = new StringBuilder();

        if(node.getLeft() != null) {
            left.append(constructedRes(node.getLeft()));
        }
        if(node.getRight() != null) {
            right.append(constructedRes(node.getRight()));
        }

        if(grammarReader.getSymbolGenerator().hasSymbol(node.getWord())) {
            res.append(left);
            res.append(right);
        }
        else {
            res.append("[").append(node.getWord());
            res.append(left);
            res.append(right);
            res.append("]");
        }

        return res.toString();
    }

}
