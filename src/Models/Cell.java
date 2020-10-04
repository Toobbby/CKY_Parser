package Models;

import java.util.List;
import java.util.Objects;

public class Cell {
    private List<TreeNode> nodes;

    public Cell() {
    }

    public Cell(List<TreeNode> nodes) {
        this.nodes = nodes;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return Objects.equals(nodes, cell.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "nodes=" + nodes +
                '}';
    }
}
