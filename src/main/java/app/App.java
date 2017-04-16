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
        String input;

        char numStartNodes = '\0';
        while (numStartNodes != '0' && numStartNodes != 'n') {
            System.out.print("Start with [0 | n]: ");
            input = scanner.nextLine();

            if (input.length() != 0) {
                numStartNodes = Character.toLowerCase(input.charAt(0));
            }

            if (numStartNodes != '0' && numStartNodes != 'n') {
                System.out.println("Error: Enter only '0' or 'n'.\n");
            }
        }

        String[] startingNodes = null;
        if (numStartNodes != '0') {
            boolean isNodesValid = false;
            while (!isNodesValid) {
                System.out.print("Input array of size n (comma separated): ");
                startingNodes = scanner.nextLine().split(",");

                if (!isAllInt(startingNodes) || startingNodes.length == 0) {
                    System.out.println("Error: All nodes must only be integers separated by commas with no other symbols in between.\n");
                } else {
                    isNodesValid = true;
                }
            }
        }

        char optOrder = '\0';
        while (optOrder != 'x' && optOrder != 'n') {
            System.out.print("Choose heap order [ma(x) / mi(n)]: ");
            input = scanner.nextLine();

            if (input.length() != 0) {
                optOrder = Character.toLowerCase(input.charAt(0));
            }

            if (optOrder != '0' && optOrder != 'n') {
                System.out.println("Enter only 'x' or 'n'.\n");
            }
        }

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
            input = scanner.nextLine();

            if (input.length() != 0) {
                optChar = Character.toLowerCase(input.charAt(0));
            } else {
                System.out.println("Unknown command. Only enter 'd', 'i', or 'e'.\n");
                continue;
            }

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
                System.out.println("Unknown command. Only enter 'd', 'i', or 'e'.\n");
                continue;
            }

            System.out.println("Resulting Heap");
            System.out.println(binaryHeap.getContents());
        }
    }

    private static boolean isAllInt(String[] integers)
    {
        for (String integer : integers) {
            if (!integer.matches("[0-9]+")) {
                return false;
            }
        }

        return true;
    }
}
