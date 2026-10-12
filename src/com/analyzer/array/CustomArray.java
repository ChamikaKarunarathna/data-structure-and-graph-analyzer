package com.analyzer.array;

import com.analyzer.benchmark.BenchmarkResult;
import com.analyzer.benchmark.PerformanceTracker;

/**
 * Array data structure implementation supporting Insert, Delete, Search, and Display operations
 * with step-counting and time complexity benchmarking.
 * Developed by Member 1 (C.K.L. Karunarathna - 23DA2-0096)
 */
public class CustomArray {
    private int[] data;
    private int size;
    private int capacity;

    public CustomArray(int initialCapacity) {
        if (initialCapacity <= 0) {
            initialCapacity = 10;
        }
        this.capacity = initialCapacity;
        this.data = new int[capacity];
        this.size = 0;
    }

    public CustomArray() {
        this(10);
    }

    /**
     * Inserts an element at the end of the array.
     */
    public boolean insert(int value) {
        long startTime = System.nanoTime();
        long steps = 0;

        ensureCapacity();
        steps++; // Array resizing check step

        data[size] = value;
        steps++; // Assignment step
        size++;

        long elapsed = System.nanoTime() - startTime;
        PerformanceTracker.record(new BenchmarkResult("Array Insert", "Append", steps, elapsed, "Value " + value + " appended"));
        return true;
    }

    /**
     * Inserts an element at a specific index, shifting subsequent elements.
     */
    public boolean insertAt(int index, int value) {
        long startTime = System.nanoTime();
        long steps = 0;

        if (index < 0 || index > size) {
            System.out.println("Error: Index " + index + " out of bounds! Valid range: 0 to " + size);
            return false;
        }

        ensureCapacity();
        steps++;

        // Shift elements right
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
            steps++;
        }

        data[index] = value;
        steps++;
        size++;

        long elapsed = System.nanoTime() - startTime;
        PerformanceTracker.record(new BenchmarkResult("Array Insert", "Insert At Index", steps, elapsed, "Value " + value + " at index " + index));
        return true;
    }

    /**
     * Deletes an element at a specific index, shifting subsequent elements.
     */
    public boolean deleteAt(int index) {
        long startTime = System.nanoTime();
        long steps = 0;

        if (isEmpty()) {
            System.out.println("Error: Cannot delete from an empty array!");
            return false;
        }

        if (index < 0 || index >= size) {
            System.out.println("Error: Index " + index + " out of bounds! Valid range: 0 to " + (size - 1));
            return false;
        }

        int removedValue = data[index];
        steps++;

        // Shift elements left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            steps++;
        }

        size--;
        long elapsed = System.nanoTime() - startTime;
        PerformanceTracker.record(new BenchmarkResult("Array Delete", "Delete At Index", steps, elapsed, "Removed " + removedValue + " from index " + index));
        return true;
    }

    /**
     * Deletes the first occurrence of a specified value.
     */
    public boolean deleteValue(int value) {
        int index = search(value);
        if (index != -1) {
            return deleteAt(index);
        } else {
            System.out.println("Value " + value + " not found in array.");
            return false;
        }
    }

    /**
     * Linear search for a value in the array.
     * Returns index if found, -1 otherwise.
     */
    public int search(int target) {
        long startTime = System.nanoTime();
        long steps = 0;

        for (int i = 0; i < size; i++) {
            steps++; // Comparison step
            if (data[i] == target) {
                long elapsed = System.nanoTime() - startTime;
                PerformanceTracker.record(new BenchmarkResult("Array Search", "Linear Search", steps, elapsed, "Found " + target + " at index " + i));
                return i;
            }
        }

        long elapsed = System.nanoTime() - startTime;
        PerformanceTracker.record(new BenchmarkResult("Array Search", "Linear Search", steps, elapsed, "Target " + target + " not found"));
        return -1;
    }

    /**
     * Displays all elements in the array.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Array is empty. [Size: 0, Capacity: " + capacity + "]");
            return;
        }

        System.out.print("Array Elements [Size: " + size + ", Capacity: " + capacity + "]: [ ");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        return data[index];
    }

    public int[] toArray() {
        int[] copy = new int[size];
        System.arraycopy(data, 0, copy, 0, size);
        return copy;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        data = new int[capacity];
    }

    private void ensureCapacity() {
        if (size >= capacity) {
            int newCapacity = capacity * 2;
            int[] newData = new int[newCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
            capacity = newCapacity;
        }
    }
}
