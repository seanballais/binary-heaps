package app.utils.ds;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryHeap
{
    private Node root;
    private Node lastNode; // This will always point to the last node.
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
        this.fixHeapOrderAfterInsert(newNode);
    }

    public void delete()
    {
        Node.swapValues(this.root, this.lastNode);

        Node lastNodeParent = this.lastNode.getParent();
        if (lastNodeParent.getLeftChild() == lastNode) {
            lastNodeParent.setLeftChild(null);
        } else if (lastNodeParent.getRightChild() == lastNode) {
            lastNodeParent.setRightChild(null);
        }

        this.fixHeapOrderAfterDeletion(this.root);
    }

    public String getContents();

    private Node getMax(Node first, Node second)
    {
        if (first == null) {
            return second;
        } else if (second == null) {
            return first;
        }

        if (Math.max(first.getValue(), second.getValue()) == first.getValue()) {
            return first;
        } else {
            return second;
        }
    }

    private Node getMin(Node first, Node second)
    {
        if (first == null) {
            return second;
        } else if (second == null) {
            return first;
        }

        if (Math.min(first.getValue(), second.getValue()) == first.getValue()) {
            return first;
        } else {
            return second;
        }
    }

    private void addNode(Node newNode)
    {
        this.lastNode = newNode;

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

    private void fixHeapOrderAfterDeletion(Node anomalousNode)
    {
        if (this.heapType == 0 && anomalousNode.getLeftChild() != null && anomalousNode.getRightChild() != null) {
            // Min-heaps

            Node minNode;

            while (anomalousNode.getLeftChild().getValue() < anomalousNode.getValue() ||
                   anomalousNode.getRightChild().getValue() < anomalousNode.getValue()) {

                minNode = this.getMin(anomalousNode.getLeftChild(), anomalousNode.getRightChild();
                Node.swapValues(anomalousNode, minNode);

                anomalousNode = minNode;

                if (anomalousNode.getLeftChild() == null && anomalousNode.getRightChild() == null) {
                    break;
                }
            }
        } else if (this.heapType == 1 anomalousNode.getLeftChild() != null && anomalousNode.getRightChild() != null) {
            // Max-heaps

            Node maxNode;

            while (anomalousNode.getLeftChild().getValue() > anomalousNode.getValue() ||
                   anomalousNode.getRightChild().getValue() > anomalousNode.getValue()) {

                maxNode = this.getMax(anomalousNode.getLeftChild(), anomalousNode.getRightChild();
                Node.swapValues(anomalousNode, maxNode);

                anomalousNode = maxNode;

                if (anomalousNode.getLeftChild() == null && anomalousNode.getRightChild() == null) {
                    break;
                }
            }
        }
    }

    private void fixHeapOrderAfterInsert(Node anomalousNode)
    {
        if (this.heapType == 0 && anomalousNode.getParent() != null) {
            // Min-heaps

            while (anomalousNode.getParent().getValue() > anomalousNode.getValue()) {
                // In min-heaps, the parent's value must be less than or equal to the
                // value of its children.

                Node.swapValues(anomalousNode.getParent(), anomalousNode);
                anomalousNode = anomalousNode.getParent();

                if (anomalousNode == null) {
                    break;
                }
            }
        } else if (this.heapType == 1 && anomalousNode.getParent() != null) {
            // Max-heaps

            while (anomalousNode.getParent().getValue() < anomalousNode.getValue()) {
                // In max-heaps, the parent's value must be greater than or equal to the
                // value of its children.

                Node.swapValues(anomalousNode.getParent(), anomalousNode);
                anomalousNode = anomalousNode.getParent();

                if (anomalousNode == null) {
                    break;
                }
            }
        }
    }
}
