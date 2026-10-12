package com.analyzer.search;

import com.analyzer.benchmark.BenchmarkResult;
import com.analyzer.benchmark.PerformanceTracker;
import java.util.Arrays;

/**
 * Searching algorithms implementation demonstrating Linear Search and Binary Search
 * with step-counting, runtime benchmarking, and complexity analysis.
 * Developed by Member 1 (C.K.L. Karunarathna - 23DA2-0096)
 */
public class SearchAlgorithms {

    public static class SearchResult {
        public final int index;
        public final long steps;
        public final long executionTimeNs;
        public final boolean found;

        public SearchResult(int index, long steps, long executionTimeNs, boolean found) {
            this.index = index;
            this.steps = steps;
            this.executionTimeNs = executionTimeNs;
            this.found = found;
        }
    }

    /**
     * Executes Linear Search.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static SearchResult linearSearch(int[] array, int target) {
        long startTime = System.nanoTime();
        long steps = 0;
        int foundIndex = -1;

        for (int i = 0; i < array.length; i++) {
            steps++; // Element comparison
            if (array[i] == target) {
                foundIndex = i;
                break;
            }
        }

        long elapsed = System.nanoTime() - startTime;
        boolean found = (foundIndex != -1);
        String note = found ? "Target found at index " + foundIndex : "Target not found";

        PerformanceTracker.record(new BenchmarkResult("Search", "Linear Search", steps, elapsed, note));
        return new SearchResult(foundIndex, steps, elapsed, found);
    }

    /**
     * Executes Binary Search on a sorted array.
     * Precondition: Array must be sorted in ascending order.
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static SearchResult binarySearch(int[] sortedArray, int target) {
        long startTime = System.nanoTime();
        long steps = 0;
        int low = 0;
        int high = sortedArray.length - 1;
        int foundIndex = -1;

        while (low <= high) {
            steps++; // Loop boundary check (low <= high)
            int mid = low + (high - low) / 2;

            steps++; // Equality comparison (sortedArray[mid] == target)
            if (sortedArray[mid] == target) {
                foundIndex = mid;
                break;
            } else {
                steps++; // Ordering comparison (sortedArray[mid] < target)
                if (sortedArray[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        long elapsed = System.nanoTime() - startTime;
        boolean found = (foundIndex != -1);
        String note = found ? "Target found at sorted index " + foundIndex : "Target not found";

        PerformanceTracker.record(new BenchmarkResult("Search", "Binary Search", steps, elapsed, note));
        return new SearchResult(foundIndex, steps, elapsed, found);
    }

    /**
     * Executes both Linear and Binary search on the dataset and prints a comparative breakdown.
     */
    public static void compareSearches(int[] originalArray, int target) {
        if (originalArray == null || originalArray.length == 0) {
            System.out.println("Cannot run search comparison on an empty array!");
            return;
        }

        System.out.println("\n--------------------------------------------------------------");
        System.out.println("             SEARCH ALGORITHMS COMPARISON TEST                ");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Array Size : " + originalArray.length);
        System.out.println("Target Item: " + target);
        System.out.println("--------------------------------------------------------------");

        // 1. Run Linear Search on original unsorted/sorted data
        SearchResult linearResult = linearSearch(originalArray, target);

        // 2. Prepare sorted dataset for Binary Search
        int[] sortedCopy = Arrays.copyOf(originalArray, originalArray.length);
        Arrays.sort(sortedCopy);
        SearchResult binaryResult = binarySearch(sortedCopy, target);

        // Display results
        System.out.printf("%-18s %-10s %-12s %-16s %-14s%n",
                "Algorithm", "Found?", "Result Index", "Steps (Operations)", "Time (ms)");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-18s %-10s %-12s %-16d %-14.4f%n",
                "Linear Search",
                (linearResult.found ? "Yes" : "No"),
                (linearResult.found ? String.valueOf(linearResult.index) : "N/A"),
                linearResult.steps,
                linearResult.executionTimeNs / 1_000_000.0);

        System.out.printf("%-18s %-10s %-12s %-16d %-14.4f%n",
                "Binary Search",
                (binaryResult.found ? "Yes" : "No"),
                (binaryResult.found ? String.valueOf(binaryResult.index) : "N/A"),
                binaryResult.steps,
                binaryResult.executionTimeNs / 1_000_000.0);

        System.out.println("--------------------------------------------------------------");
        System.out.println("Algorithmic Complexity Summary:");
        System.out.println("• Linear Search: Time Complexity = O(n), Space Complexity = O(1)");
        System.out.println("  Requires sequentially checking elements from left to right.");
        System.out.println("• Binary Search: Time Complexity = O(log n), Space Complexity = O(1)");
        System.out.println("  Repeatedly divides the search interval in half on sorted data.");
        if (linearResult.steps > binaryResult.steps) {
            long diff = linearResult.steps - binaryResult.steps;
            System.out.println("=> Binary Search saved " + diff + " comparison steps compared to Linear Search!");
        }
        System.out.println("--------------------------------------------------------------\n");
    }
}
