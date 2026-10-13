package com.analyzer.queue;

import com.analyzer.benchmark.BenchmarkResult;
import com.analyzer.benchmark.PerformanceTracker;

/**
 * Circular-array integer queue with operation and execution-time tracking.
 */
public class CustomQueue {
    private int[] data;
    private int front;
    private int size;

    public CustomQueue() {
        data = new int[10];
    }

    public void enqueue(int value) {
        long startTime = System.nanoTime();
        long steps = 0;
        if (size == data.length) {
            resize();
            steps += size;
        }

        int insertionIndex = (front + size) % data.length;
        data[insertionIndex] = value;
        size++;
        steps++;
        PerformanceTracker.record(new BenchmarkResult(
                "Queue Enqueue", "Circular Array Queue", steps, System.nanoTime() - startTime,
                "Enqueued " + value));
    }

    public Integer dequeue() {
        long startTime = System.nanoTime();
        long steps = 1;
        if (isEmpty()) {
            record("Queue Dequeue", steps, startTime, "Queue is empty");
            return null;
        }

        int value = data[front];
        front = (front + 1) % data.length;
        size--;
        steps++;
        if (size == 0) {
            front = 0;
        }
        record("Queue Dequeue", steps, startTime, "Dequeued " + value);
        return value;
    }

    public Integer peek() {
        long startTime = System.nanoTime();
        long steps = 1;
        if (isEmpty()) {
            record("Queue Peek", steps, startTime, "Queue is empty");
            return null;
        }

        steps++;
        int value = data[front];
        record("Queue Peek", steps, startTime, "Front value is " + value);
        return value;
    }

    public void display() {
        long startTime = System.nanoTime();
        long steps = 0;
        if (isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.print("Queue (front to rear): [ ");
            for (int offset = 0; offset < size; offset++) {
                System.out.print(data[(front + offset) % data.length]);
                if (offset < size - 1) {
                    System.out.print(", ");
                }
                steps++;
            }
            System.out.println(" ]");
        }
        PerformanceTracker.record(new BenchmarkResult(
                "Queue Display", "Circular Array Queue", steps, System.nanoTime() - startTime,
                "Displayed " + size + " item(s)"));
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        int[] expandedData = new int[data.length * 2];
        for (int offset = 0; offset < size; offset++) {
            expandedData[offset] = data[(front + offset) % data.length];
        }
        data = expandedData;
        front = 0;
    }

    private void record(String operation, long steps, long startTime, String notes) {
        PerformanceTracker.record(new BenchmarkResult(
                operation, "Circular Array Queue", steps, System.nanoTime() - startTime, notes));
    }
}
