import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = scnr.nextLine().trim();

        List<Integer> original = readArrayFromFile(filename);

        if (original == null || original.isEmpty()) {
            System.out.println("Error: Could not read valid input array from file.");
            return;
        }

        List<Integer> bubbleList = new ArrayList<>(original);
        List<Integer> mergeList = new ArrayList<>(original);

        System.out.println("\nOriginal List: " + original);

        long startBubble = System.nanoTime();
        Sorter.bubbleSort(bubbleList);
        long endBubble = System.nanoTime();

        long startMerge = System.nanoTime();
        Sorter.mergeSort(mergeList);
        long endMerge = System.nanoTime();

        System.out.println("Bubble Sorted: " + bubbleList);
        System.out.println("Merge Sorted:  " + mergeList);

        System.out.printf("\nBubble Sort Time: %.3f ms\n", (endBubble - startBubble) / 1_000_000.0);
        System.out.printf("Merge Sort Time:  %.3f ms\n", (endMerge - startMerge) / 1_000_000.0);
    }

    // Reads integers from file (one per line)
    private static List<Integer> readArrayFromFile(String filename) {
        List<Integer> result = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    result.add(Integer.parseInt(line));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
        return result;
    }
}
