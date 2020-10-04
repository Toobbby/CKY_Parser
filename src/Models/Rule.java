package Models;

import java.util.Objects;

public class Rule {
    private String from;
    private String to;
    private float prob;

    public Rule(String from, String to, float prob) {
        this.from = from;
        this.to = to;
        this.prob = prob;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
        Rule rule = (Rule) o;
        return Float.compare(rule.prob, prob) == 0 &&
                Objects.equals(from, rule.from) &&
                Objects.equals(to, rule.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, prob);
    }

    @Override
    public String toString() {
        return "Rule{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", prob=" + prob +
                '}';
    }
}
