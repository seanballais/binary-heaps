package app;

import app.utils.ds.BinaryHeap;
import app.utils.ds.Node;

import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        BinaryHeap binaryHeap;
        byte heapType;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Start with [0 | n]: ");
        char numStartNodes = scanner.nextLine().charAt(0);

        String[] startingNodes = null;
        if (numStartNodes != '0') {
            System.out.print("Input array of size n (comma separated): ");
            startingNodes = scanner.nextLine().split(",");
        }

        System.out.print("Choose heap order [ma(x) / mi(n)]: ");
        char optOrder = scanner.nextLine().charAt(0);

        if (optOrder == 'x') {
            // Max-heap
            heapType = 1;
            System.out.println("Binary heap will be a max-heap.");
        } else {
            // Min-heap
            heapType = 0;
            System.out.println("Binary heap will be a min-heap.");
        }

        System.out.println("Generating binary heap...");

        binaryHeap = new BinaryHeap(heapType);

        if (startingNodes != null) {
            for (String node : startingNodes) {
                binaryHeap.insert(new Node(null, Integer.parseInt(node)));
            }
        }

        System.out.println("Resulting Heap");
        System.out.println(binaryHeap.getContents());

        // Let the user play with the heap.
        char optChar;
        while (true) {
            System.out.print("Choose operation [(D)elete | (I)nsert | (E)xit]: ");
            optChar = Character.toLowerCase(scanner.nextLine().charAt(0));

            if (optChar == 'd') {
                System.out.println("Deleting root node.");
                binaryHeap.delete();
            } else if (optChar == 'i') {
                System.out.print("Value to be inserted: ");
                binaryHeap.insert(new Node(null, scanner.nextInt()));
                scanner.nextLine(); // Discard remaining characters (if any).
            } else if (optChar == 'e') {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Unknown command. Only enter 'd', 'i', or 'e'.");
                continue;
            }

            System.out.println("Resulting Heap");
            System.out.println(binaryHeap.getContents());
        }
    }
}
