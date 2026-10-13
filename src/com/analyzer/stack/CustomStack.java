package com.analyzer.stack;

import com.analyzer.benchmark.BenchmarkResult;
import com.analyzer.benchmark.PerformanceTracker;

/**
 * Array-backed integer stack with operation and execution-time tracking.
 */
public class CustomStack {
    private int[] data;
    private int size;

    public CustomStack() {
        data = new int[10];
    }

    public void push(int value) {
        long startTime = System.nanoTime();
        long steps = 0;

        if (size == data.length) {
            int[] expandedData = new int[data.length * 2];
            System.arraycopy(data, 0, expandedData, 0, size);
            data = expandedData;
            steps += size;
        }
        data[size] = value;
        size++;
        steps++;

        PerformanceTracker.record(new BenchmarkResult(
                "Stack Push", "Array-backed Stack", steps, System.nanoTime() - startTime,
                "Pushed " + value));
    }

    public Integer pop() {
        long startTime = System.nanoTime();
        long steps = 1;
        if (isEmpty()) {
            record("Stack Pop", steps, startTime, "Underflow: stack is empty");
            return null;
        }

        int value = data[--size];
        steps++;
        record("Stack Pop", steps, startTime, "Popped " + value);
        return value;
    }

    public Integer peek() {
        long startTime = System.nanoTime();
        long steps = 1;
        if (isEmpty()) {
            record("Stack Peek", steps, startTime, "Stack is empty");
            return null;
        }

        steps++;
        int value = data[size - 1];
        record("Stack Peek", steps, startTime, "Top value is " + value);
        return value;
    }

    public void display() {
        long startTime = System.nanoTime();
        long steps = 0;
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            System.out.print("Stack (top to bottom): [ ");
            for (int index = size - 1; index >= 0; index--) {
                System.out.print(data[index]);
                if (index > 0) {
                    System.out.print(", ");
                }
                steps++;
            }
            System.out.println(" ]");
        }
        PerformanceTracker.record(new BenchmarkResult(
                "Stack Display", "Array-backed Stack", steps, System.nanoTime() - startTime,
                "Displayed " + size + " item(s)"));
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void record(String operation, long steps, long startTime, String notes) {
        PerformanceTracker.record(new BenchmarkResult(
                operation, "Array-backed Stack", steps, System.nanoTime() - startTime, notes));
    }
}
