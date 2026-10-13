package com.analyzer;

import com.analyzer.array.CustomArray;
import com.analyzer.benchmark.PerformanceTracker;
import com.analyzer.linkedlist.CustomLinkedList;
import com.analyzer.search.SearchAlgorithms;
import com.analyzer.util.InputHelper;
import java.util.Random;

/**
 * Main application console menu and controller for the Data Structure and Graph Analyzer.
 * Initialized and developed by Member 1 (C.K.L. Karunarathna - 23DA2-0096).
 */
public class Main {
    private static final CustomArray sharedArray = new CustomArray();
    private static final CustomLinkedList sharedLinkedList = new CustomLinkedList();

    public static void main(String[] args) {
        // Pre-populate initial sample data for demonstration ease
        sharedArray.insert(25);
        sharedArray.insert(10);
        sharedArray.insert(45);
        sharedArray.insert(70);
        sharedArray.insert(15);

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = InputHelper.readInt("Enter your choice: ");
            System.out.println();

            switch (choice) {
                case 1:
                    handleArrayMenu();
                    break;
                case 2:
                    handleStackMenuPlaceholder();
                    break;
                case 3:
                    handleQueueMenuPlaceholder();
                    break;
                case 4:
                    handleLinkedListMenu();
                    break;
                case 5:
                    handleSearchMenu();
                    break;
                case 6:
                    handleGraphMenuPlaceholder();
                    break;
                case 7:
                    PerformanceTracker.displayComparisonTable();
                    InputHelper.pause();
                    break;
                case 8:
                    displayAllResults();
                    InputHelper.pause();
                    break;
                case 9:
                    System.out.println("Exiting Data Structure and Graph Performance Analyzer. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please choose an option between 1 and 9.");
                    InputHelper.pause();
                    break;
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=============================================");
        System.out.println("       DATA STRUCTURE & GRAPH ANALYZER       ");
        System.out.println("=============================================");
        System.out.println("1. Array Operations");
        System.out.println("2. Stack Operations (Member 2)");
        System.out.println("3. Queue Operations (Member 2)");
        System.out.println("4. Linked List Operations (Member 3)");
        System.out.println("5. Searching Operations");
        System.out.println("6. Graph Operations (Member 4)");
        System.out.println("7. Performance Comparison");
        System.out.println("8. Display All Results");
        System.out.println("9. Exit");
        System.out.println("=============================================");
    }

    // -------------------------------------------------------------
    // 1. ARRAY SUBMENU (MEMBER 1)
    // -------------------------------------------------------------
    private static void handleArrayMenu() {
        boolean inSubMenu = true;
        while (inSubMenu) {
            System.out.println("\n--------------- ARRAY OPERATIONS ------------");
            System.out.println("1. Insert Element (Append)");
            System.out.println("2. Insert Element at Specific Index");
            System.out.println("3. Delete Element by Index");
            System.out.println("4. Delete Element by Value");
            System.out.println("5. Search Element");
            System.out.println("6. Display Array");
            System.out.println("7. Load Sample Random Data");
            System.out.println("8. Clear Array");
            System.out.println("9. Return to Main Menu");
            System.out.println("---------------------------------------------");

            int choice = InputHelper.readInt("Enter your choice: ");
            System.out.println();

            switch (choice) {
                case 1:
                    int valToInsert = InputHelper.readInt("Enter integer value to insert: ");
                    sharedArray.insert(valToInsert);
                    System.out.println("Inserted " + valToInsert + " successfully.");
                    sharedArray.display();
                    break;
                case 2:
                    int index = InputHelper.readInt("Enter target index: ");
                    int val = InputHelper.readInt("Enter integer value to insert: ");
                    if (sharedArray.insertAt(index, val)) {
                        System.out.println("Inserted successfully at index " + index + ".");
                    }
                    sharedArray.display();
                    break;
                case 3:
                    if (sharedArray.isEmpty()) {
                        System.out.println("Array is empty!");
                    } else {
                        int delIndex = InputHelper.readInt("Enter index to delete (0 to " + (sharedArray.size() - 1) + "): ");
                        if (sharedArray.deleteAt(delIndex)) {
                            System.out.println("Element deleted successfully.");
                        }
                        sharedArray.display();
                    }
                    break;
                case 4:
                    if (sharedArray.isEmpty()) {
                        System.out.println("Array is empty!");
                    } else {
                        int delValue = InputHelper.readInt("Enter value to delete: ");
                        if (sharedArray.deleteValue(delValue)) {
                            System.out.println("Value deleted successfully.");
                        }
                        sharedArray.display();
                    }
                    break;
                case 5:
                    if (sharedArray.isEmpty()) {
                        System.out.println("Array is empty!");
                    } else {
                        int target = InputHelper.readInt("Enter value to search: ");
                        int foundIdx = sharedArray.search(target);
                        if (foundIdx != -1) {
                            System.out.println("Found " + target + " at index: " + foundIdx);
                        } else {
                            System.out.println("Value " + target + " not found in array.");
                        }
                    }
                    break;
                case 6:
                    sharedArray.display();
                    break;
                case 7:
                    int count = InputHelper.readIntInRange("How many random items to append (1 - 50)? ", 1, 50);
                    Random rand = new Random();
                    for (int i = 0; i < count; i++) {
                        sharedArray.insert(rand.nextInt(100));
                    }
                    System.out.println("Added " + count + " random numbers.");
                    sharedArray.display();
                    break;
                case 8:
                    sharedArray.clear();
                    System.out.println("Array cleared.");
                    break;
                case 9:
                    inSubMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-9.");
                    break;
            }
            if (inSubMenu && choice != 6) {
                InputHelper.pause();
            }
        }
    }

    // -------------------------------------------------------------
    // 5. SEARCHING SUBMENU (MEMBER 1)
    // -------------------------------------------------------------
    private static void handleSearchMenu() {
        boolean inSubMenu = true;
        while (inSubMenu) {
            System.out.println("\n------------- SEARCHING OPERATIONS -----------");
            System.out.println("Current Array Status:");
            sharedArray.display();
            System.out.println("---------------------------------------------");
            System.out.println("1. Linear Search on Current Array");
            System.out.println("2. Binary Search on Current Array (Sorted)");
            System.out.println("3. Compare Linear vs Binary Search on Current Array");
            System.out.println("4. Benchmark Large Dataset (10,000 Elements)");
            System.out.println("5. Return to Main Menu");
            System.out.println("---------------------------------------------");

            int choice = InputHelper.readInt("Enter your choice: ");
            System.out.println();

            switch (choice) {
                case 1:
                    if (sharedArray.isEmpty()) {
                        System.out.println("Array is empty! Add elements first.");
                    } else {
                        int target = InputHelper.readInt("Enter target value for Linear Search: ");
                        SearchAlgorithms.SearchResult res = SearchAlgorithms.linearSearch(sharedArray.toArray(), target);
                        System.out.println("Result: " + (res.found ? "Found at index " + res.index : "Not Found"));
                        System.out.println("Steps Taken: " + res.steps);
                        System.out.printf("Execution Time: %.4f ms%n", res.executionTimeNs / 1_000_000.0);
                    }
                    break;
                case 2:
                    if (sharedArray.isEmpty()) {
                        System.out.println("Array is empty! Add elements first.");
                    } else {
                        int target = InputHelper.readInt("Enter target value for Binary Search: ");
                        int[] sortedArr = sharedArray.toArray();
                        java.util.Arrays.sort(sortedArr);
                        System.out.println("Sorted Array Copy: " + java.util.Arrays.toString(sortedArr));
                        SearchAlgorithms.SearchResult res = SearchAlgorithms.binarySearch(sortedArr, target);
                        System.out.println("Result: " + (res.found ? "Found at sorted index " + res.index : "Not Found"));
                        System.out.println("Steps Taken: " + res.steps);
                        System.out.printf("Execution Time: %.4f ms%n", res.executionTimeNs / 1_000_000.0);
                    }
                    break;
                case 3:
                    if (sharedArray.isEmpty()) {
                        System.out.println("Array is empty! Add elements first.");
                    } else {
                        int target = InputHelper.readInt("Enter target value to compare: ");
                        SearchAlgorithms.compareSearches(sharedArray.toArray(), target);
                    }
                    break;
                case 4:
                    System.out.println("Generating 10,000 random sorted integers for benchmark...");
                    int size = 10000;
                    int[] largeDataset = new int[size];
                    Random r = new Random(42);
                    for (int i = 0; i < size; i++) {
                        largeDataset[i] = r.nextInt(50000);
                    }
                    java.util.Arrays.sort(largeDataset);
                    int testTarget = largeDataset[size - 1]; // Worst-case scenario for linear search
                    System.out.println("Testing worst-case search for target element: " + testTarget);
                    SearchAlgorithms.compareSearches(largeDataset, testTarget);
                    break;
                case 5:
                    inSubMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-5.");
                    break;
            }
            if (inSubMenu) {
                InputHelper.pause();
            }
        }
    }

    // -------------------------------------------------------------
    // PLACEHOLDERS FOR TEAM MEMBERS
    // -------------------------------------------------------------
    private static void handleStackMenuPlaceholder() {
        System.out.println("---------------------------------------------");
        System.out.println("      STACK OPERATIONS (MODULE ASSIGNED)     ");
        System.out.println("Assigned Member: K.D.P. Jayathilaka (23DA2-0105)");
        System.out.println("Status: Pending implementation on branch feature/stack-and-queue");
        System.out.println("---------------------------------------------");
        InputHelper.pause();
    }

    private static void handleQueueMenuPlaceholder() {
        System.out.println("---------------------------------------------");
        System.out.println("      QUEUE OPERATIONS (MODULE ASSIGNED)     ");
        System.out.println("Assigned Member: K.D.P. Jayathilaka (23DA2-0105)");
        System.out.println("Status: Pending implementation on branch feature/stack-and-queue");
        System.out.println("---------------------------------------------");
        InputHelper.pause();
    }

    private static void handleGraphMenuPlaceholder() {
        System.out.println("---------------------------------------------");
        System.out.println("      GRAPH OPERATIONS (MODULE ASSIGNED)     ");
        System.out.println("Assigned Member: H.G.R. Shehan (23DA2-0108)");
        System.out.println("Status: Pending implementation on branch feature/graph-component");
        System.out.println("---------------------------------------------");
        InputHelper.pause();
    }

    // -------------------------------------------------------------
    // 8. DISPLAY ALL RESULTS
    // -------------------------------------------------------------
    private static void displayAllResults() {
        System.out.println("==========================================================================");
        System.out.println("                          ALL SYSTEM RESULTS                              ");
        System.out.println("==========================================================================");
        System.out.println("Current Array Snapshot:");
        sharedArray.display();
        System.out.println("Current Linked List Snapshot:");
        sharedLinkedList.display();
        System.out.println();
        PerformanceTracker.displayComparisonTable();
    }

    private static void handleLinkedListMenu() {
        boolean inSubMenu = true;
        while (inSubMenu) {
            System.out.println("\n---------- LINKED LIST OPERATIONS -----------");
            System.out.println("1. Insert at Tail (Append)");
            System.out.println("2. Insert at Head");
            System.out.println("3. Insert at Specific Position");
            System.out.println("4. Delete by Value");
            System.out.println("5. Delete by Position");
            System.out.println("6. Search Element");
            System.out.println("7. Display Linked List");
            System.out.println("8. Clear List");
            System.out.println("9. Return to Main Menu");
            System.out.println("---------------------------------------------");

            int choice = InputHelper.readInt("Enter your choice: ");
            System.out.println();

            switch (choice) {
                case 1: {
                    int value = InputHelper.readInt("Enter integer value to append: ");
                    sharedLinkedList.insertAtTail(value);
                    System.out.println("Inserted " + value + " successfully at the tail.");
                    sharedLinkedList.display();
                    break;
                }
                case 2: {
                    int value = InputHelper.readInt("Enter integer value to insert: ");
                    sharedLinkedList.insertAtHead(value);
                    System.out.println("Inserted " + value + " successfully at the head.");
                    sharedLinkedList.display();
                    break;
                }
                case 3: {
                    int index = InputHelper.readInt("Enter insertion index (0 to " + sharedLinkedList.size() + "): ");
                    int value = InputHelper.readInt("Enter integer value to insert: ");
                    if (sharedLinkedList.insertAtPosition(index, value)) {
                        System.out.println("Inserted " + value + " successfully at index " + index + ".");
                    }
                    sharedLinkedList.display();
                    break;
                }
                case 4: {
                    int value = InputHelper.readInt("Enter value to delete: ");
                    if (sharedLinkedList.deleteByValue(value)) {
                        System.out.println("Value " + value + " deleted successfully.");
                    }
                    sharedLinkedList.display();
                    break;
                }
                case 5: {
                    if (sharedLinkedList.isEmpty()) {
                        sharedLinkedList.deleteAtPosition(0);
                    } else {
                        int index = InputHelper.readInt("Enter deletion index (0 to " + (sharedLinkedList.size() - 1) + "): ");
                        if (sharedLinkedList.deleteAtPosition(index)) {
                            System.out.println("Element at index " + index + " deleted successfully.");
                        }
                    }
                    sharedLinkedList.display();
                    break;
                }
                case 6: {
                    int target = InputHelper.readInt("Enter value to search: ");
                    int index = sharedLinkedList.search(target);
                    if (index >= 0) {
                        System.out.println("Found " + target + " at index " + index + ".");
                    } else {
                        System.out.println("Value " + target + " not found in linked list.");
                    }
                    break;
                }
                case 7:
                    sharedLinkedList.display();
                    break;
                case 8:
                    sharedLinkedList.clear();
                    System.out.println("Linked list cleared.");
                    break;
                case 9:
                    inSubMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-9.");
                    break;
            }
            if (inSubMenu) {
                InputHelper.pause();
            }
        }
    }
}
