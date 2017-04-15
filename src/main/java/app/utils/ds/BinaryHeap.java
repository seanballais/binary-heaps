package app.utils.ds;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryHeap
{
    private Node root;
    private byte heapType;

    public BinaryHeap(byte heapType)
    {
        // BinaryHeap types
        //  0 - Min-heaps
        //  1 - Max-heaps

        this.heapType = heapType;
    }

    private void insert(Node newNode)
    {
        this.addNode(newNode);
        this.fixHeapOrder(newNode);
    }

    public void delete();

    public String getContents();

    private void addNode(Node newNode)
    {
        if (this.root == null) {
            this.root = newNode;
        }

        // Modified BFS to insert new nodes in the heap.
        Queue<Node> q = new LinkedList<>();
        q.add(this.root);

        Node current;
        while (!q.isEmpty()) {
            current = q.remove();

            if (current.getLeftChild() == null) {
                current.setLeftChild(newNode);
                break;
            } else if (current.getRightChild() == null) {
                current.setRightChild(newNode);
                break;
            }

            q.add(current.getLeftChild());
            q.add(current.getRightChild());
        }
    }

    private void fixHeapOrder(Node anomalousNode)
    {
        if (this.heapType == 0) {
            // Min-heaps
            while (anomalousNode.getParent().getValue() > anomalousNode.getValue()) {
                // In min-heaps, the parent's value must be less than or equal to the
                // value of its children.

                Node.swapValues(anomalousNode.getParent(), anomalousNode);
                anomalousNode = anomalousNode.getParent();
            }
        } else if (this.heapType == 1) {
            // Max-heaps
            while (anomalousNode.getParent().getValue() < anomalousNode.getValue()) {
                // In max-heaps, the parent's value must be greater than or equal to the
                // value of its children.

                Node.swapValues(anomalousNode.getParent(), anomalousNode);
                anomalousNode = anomalousNode.getParent();
            }
        }
    }
}
