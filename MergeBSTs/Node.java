
public class Node {

    private Object value;   // pagal nutylejima priskiriama reiksme null
    private Node parent;    // pagal nutylejima priskiriama reiksme null
    private Node smaller;   // pagal nutylejima priskiriama reiksme null
    private Node larger;    // pagal nutylejima priskiriama reiksme null

    public Node() {
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getSmaller() {
        return smaller;
    }

    public void setSmaller(Node smaller) {
        this.smaller = smaller;
    }

    public Node getLarger() {
        return larger;
    }


    public void setLarger(Node larger) {
        this.larger = larger;
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    // returns node with mim value starting from this node
    public Node minimum() {
        Node node = this;

        while (node.getSmaller() != null) {
            node = node.getSmaller();
        }
        return node;
    }

    // returns node with max value starting from this node
    public Node maximum() {
        Node node = this;

        while (node.getLarger() != null) {
            node = node.getLarger();
        }
        return node;
    }

    // is it a smaller child of its parent
    public boolean isSmaller() {
        return getParent() != null && this == getParent().getSmaller();
    }

    // is it a larger child of its parent
    public boolean isLarger() {
        return getParent() != null && this == getParent().getLarger();
    }
}
