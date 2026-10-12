package com.analyzer.benchmark;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages benchmark and operation history across all data structure operations.
 */
public class PerformanceTracker {
    private static final List<BenchmarkResult> results = new ArrayList<>();

    public static void record(BenchmarkResult result) {
        results.add(result);
    }

    public static List<BenchmarkResult> getAllResults() {
        return new ArrayList<>(results);
    }

    public static void clearResults() {
        results.clear();
    }

    public static void displayComparisonTable() {
        System.out.println("==========================================================================");
        System.out.println("                         PERFORMANCE COMPARISON                           ");
        System.out.println("==========================================================================");
        if (results.isEmpty()) {
            System.out.println("No performance records found yet. Run searching or traversal tests first.");
            System.out.println("==========================================================================");
            return;
        }

        System.out.printf("%-16s %-16s %-10s %-15s %s%n",
                "Operation", "Algorithm", "Steps", "Time (ms)", "Notes/Result");
        System.out.println("--------------------------------------------------------------------------");
        for (BenchmarkResult res : results) {
            System.out.println(res);
        }
        System.out.println("==========================================================================");
    }
}
