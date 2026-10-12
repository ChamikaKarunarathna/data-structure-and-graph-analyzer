package com.analyzer.benchmark;

/**
 * Encapsulates the benchmark results for an algorithm or data structure operation.
 */
public class BenchmarkResult {
    private final String operation;
    private final String algorithm;
    private final long steps;
    private final long executionTimeNs;
    private final String notes;

    public BenchmarkResult(String operation, String algorithm, long steps, long executionTimeNs, String notes) {
        this.operation = operation;
        this.algorithm = algorithm;
        this.steps = steps;
        this.executionTimeNs = executionTimeNs;
        this.notes = notes;
    }

    public String getOperation() {
        return operation;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public long getSteps() {
        return steps;
    }

    public long getExecutionTimeNs() {
        return executionTimeNs;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return String.format("%-16s %-16s %-10d %-15.3f %s",
                operation, algorithm, steps, executionTimeNs / 1_000_000.0, notes);
    }
}
