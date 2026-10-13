package com.analyzer.linkedlist;

import com.analyzer.benchmark.BenchmarkResult;
import com.analyzer.benchmark.PerformanceTracker;

/**
 * Singly linked list supporting insertion, deletion, search, and display operations.
 */
public class CustomLinkedList {
    private static class Node {
        private int data;
        private Node next;

        private Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public boolean insertAtHead(int value) {
        long startTime = System.nanoTime();
        long steps = 0;

        Node newNode = new Node(value);
        steps++;
        newNode.next = head;
        head = newNode;
        size++;
        steps++;

        record("Linked List Insert", "Insert At Head", steps, startTime,
                "Value " + value + " inserted at index 0");
        return true;
    }

    public boolean insertAtTail(int value) {
        long startTime = System.nanoTime();
        long steps = 0;
        Node newNode = new Node(value);
        steps++;

        if (head == null) {
            head = newNode;
            steps++;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
                steps++;
            }
            current.next = newNode;
            steps++;
        }

        size++;
        record("Linked List Insert", "Insert At Tail", steps, startTime,
                "Value " + value + " appended");
        return true;
    }

    public boolean insertAtPosition(int index, int value) {
        long startTime = System.nanoTime();
        long steps = 0;

        if (index < 0 || index > size) {
            record("Linked List Insert", "Insert At Position", steps, startTime,
                    "Insertion failed: index " + index + " is out of bounds");
            System.out.println("Error: Index " + index + " out of bounds! Valid range: 0 to " + size);
            return false;
        }

        Node newNode = new Node(value);
        steps++;
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            steps++;
        } else {
            Node previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
                steps++;
            }
            newNode.next = previous.next;
            previous.next = newNode;
            steps += 2;
        }
        size++;

        record("Linked List Insert", "Insert At Position", steps, startTime,
                "Value " + value + " inserted at index " + index);
        return true;
    }

    public boolean deleteByValue(int value) {
        long startTime = System.nanoTime();
        long steps = 0;

        if (head == null) {
            record("Linked List Delete", "Delete By Value", steps, startTime,
                    "Deletion failed: list is empty");
            System.out.println("Error: Cannot delete from an empty list!");
            return false;
        }

        if (head.data == value) {
            head = head.next;
            size--;
            steps++;
            record("Linked List Delete", "Delete By Value", steps, startTime,
                    "Removed " + value + " from index 0");
            return true;
        }

        Node previous = head;
        Node current = head.next;
        steps++;
        while (current != null) {
            steps++;
            if (current.data == value) {
                previous.next = current.next;
                size--;
                steps++;
                record("Linked List Delete", "Delete By Value", steps, startTime,
                        "Removed " + value);
                return true;
            }
            previous = current;
            current = current.next;
        }

        record("Linked List Delete", "Delete By Value", steps, startTime,
                "Value " + value + " not found");
        System.out.println("Value " + value + " not found in linked list.");
        return false;
    }

    public boolean deleteAtPosition(int index) {
        long startTime = System.nanoTime();
        long steps = 0;

        if (head == null) {
            record("Linked List Delete", "Delete At Position", steps, startTime,
                    "Deletion failed: list is empty");
            System.out.println("Error: Cannot delete from an empty list!");
            return false;
        }
        if (index < 0 || index >= size) {
            record("Linked List Delete", "Delete At Position", steps, startTime,
                    "Deletion failed: index " + index + " is out of bounds");
            System.out.println("Error: Index " + index + " out of bounds! Valid range: 0 to " + (size - 1));
            return false;
        }

        int removedValue;
        if (index == 0) {
            removedValue = head.data;
            head = head.next;
            steps++;
        } else {
            Node previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
                steps++;
            }
            removedValue = previous.next.data;
            previous.next = previous.next.next;
            steps += 2;
        }
        size--;

        record("Linked List Delete", "Delete At Position", steps, startTime,
                "Removed " + removedValue + " from index " + index);
        return true;
    }

    public int search(int target) {
        long startTime = System.nanoTime();
        long steps = 0;
        Node current = head;
        int index = 0;

        while (current != null) {
            steps++;
            if (current.data == target) {
                record("Linked List Search", "Linear Search", steps, startTime,
                        "Found " + target + " at index " + index);
                return index;
            }
            current = current.next;
            index++;
        }

        record("Linked List Search", "Linear Search", steps, startTime,
                "Target " + target + " not found");
        return -1;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("NULL");
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void record(String operation, String algorithm, long steps, long startTime, String notes) {
        long elapsed = System.nanoTime() - startTime;
        PerformanceTracker.record(new BenchmarkResult(operation, algorithm, steps, elapsed, notes));
    }
}
