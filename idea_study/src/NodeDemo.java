class Node {
    private Node next;
    private Integer value;

    public Node(int value) {
        this.value = value;
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public Node getNext() {
        return this.next;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

public class NodeDemo {
    private Node node;

    public void add(Integer value) {
        Node newNode = new Node(value);
        if (node == null) {
            node = newNode;
            return;
        }
        Node temp = node;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(newNode);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        while (node.getNext() != null) {
            sb.append(node.getValue() + "、");
            node = node.getNext();
        }
        sb.append(node.getValue());
        return sb.toString();
    }

    public static void main(String[] args) {
        NodeDemo a = new NodeDemo();
        a.add(2);
        a.add(4);
        a.add(6);
        a.add(8);
        a.add(10);
        System.out.println(a);
    }

}