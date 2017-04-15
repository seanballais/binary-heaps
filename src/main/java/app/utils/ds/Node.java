package app.utils.ds;

public class Node
{
    private Node parent;
    private Node leftChild;
    private Node rightChild;
    private int value;

    public Node(Node parent, int value)
    {
        this.parent = parent;
        this.value = value;
    }

    public void setLeftChild(Node child)
    {
        this.leftChild = child;
        this.leftChild.setParent(this);
    }

    public void setRightChild(Node child)
    {
        this.rightChild = child;
        this.rightChild.setParent(this);
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public Node getParent()
    {
        return this.parent;
    }

    public Node getLeftChild()
    {
        return this.leftChild;
    }

    public Node getRightChild()
    {
        return this.rightChild;
    }

    public int getValue()
    {
        return this.value;
    }

    public static void swapValues(Node first, Node second)
    {
        int tmpVal = first.getValue();
        first.setValue(second.getValue());
        second.setValue(tmpVal);
    }

    private void setParent(Node parent)
    {
        this.parent = parent;
    }
}
