package Models;

import java.util.Objects;

public class TreeNode {
    private String word;
    private TreeNode left;
    private TreeNode right;
    private float prob;

    public TreeNode() {
    }

    public TreeNode(String word, float prob) {
        this.word = word;
        this.prob = prob;
    }

    public TreeNode(String word, TreeNode left, TreeNode right, float prob) {
        this.word = word;
        this.left = left;
        this.right = right;
        this.prob = prob;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public float getProb() {
        return prob;
    }

    public void setProb(float prob) {
        this.prob = prob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return Float.compare(treeNode.prob, prob) == 0 &&
                Objects.equals(word, treeNode.word) &&
                Objects.equals(left, treeNode.left) &&
                Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, left, right, prob);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "word='" + word + '\'' +
                ", left=" + left +
                ", right=" + right +
                ", prob=" + prob +
                '}';
    }
}
