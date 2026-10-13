# Data Structure and Graph Performance Analyzer

**Course:** CIT300 - Data Structures and Algorithms  
**Assessment:** Graded Practical Assignment 2 (Week 12)  
**Type:** Console-Based System (Java)  
**Institution:** Sri Lanka Technological Campus (SLTC)  
**Repository:** [github.com/ChamikaKarunarathna/data-structure-and-graph-analyzer](https://github.com/ChamikaKarunarathna/data-structure-and-graph-analyzer)  
**Version:** `v1.0.0`

---

## 1. Project Overview
The **Data Structure and Graph Performance Analyzer** is a fully integrated, modular, console-based Java application developed to demonstrate the practical application, traversal, algorithmic complexity, and comparative performance of core linear and non-linear data structures.

Developed in strict accordance with the **CIT300 Practical Assignment 2** requirements, the system allows users to interactively manipulate data structures, execute search and graph traversal algorithms, observe step counts and execution timings, and evaluate real-time performance metrics in a centralized benchmark table.

---

## 2. Team Members & Responsibilities

| Student Name | Student ID | Assigned Responsibility |
| :--- | :--- | :--- |
| **C.K.L. Karunarathna (Lead)** | **23DA2-0096** | Project Initiation, Architecture, Array Operations, Searching Algorithms, System Integration |
| **K.D.P. Jayathilaka** | **23DA2-0105** | Stack Operations, Queue Operations (Circular Array), Linear Data Structure Underflow/Overflow Handling |
| **A.P. Poorna Chathuranga** | **23DA2-0072** | Linked List Implementation (Dynamic Nodes), Boundary Validation, Traversal Step Tracking |
| **H.G.R. Shehan** | **23DA2-0108** | Graph Representation (Adjacency List), Graph Traversals (BFS & DFS), Standalone Regression Tests |

---

## 3. Individual Contributions (As per Guidelines Section 7)

### Member 1: C.K.L. Karunarathna
- **Student ID:** 23DA2-0096
- **Assigned Responsibility:** Project Architecture, Array Operations, Searching Component, Main Integration
- **Individual Contribution:**
  - Initialized repository architecture, package hierarchy (`com.analyzer.*`), and `.gitignore`.
  - Implemented `CustomArray.java` supporting Insert (Append), Insert at Index, Delete by Index, Delete by Value, Linear Search, and dynamic array resizing.
  - Implemented `SearchAlgorithms.java` featuring Linear Search ($O(n)$) and Binary Search ($O(\log n)$) with separate step-counting for equality and ordering comparisons.
  - Built comparative search benchmarking utility, including large dataset stress testing (10,000 items).
  - Developed `InputHelper.java` and `PerformanceTracker.java` benchmark logging system.
  - Coordinated team git merges and release tagging (`v0.1.0`, `v0.1.1`, `v1.0.0`).

### Member 2: K.D.P. Jayathilaka
- **Student ID:** 23DA2-0105
- **Assigned Responsibility:** Stack & Queue Components
- **Individual Contribution:**
  - Implemented `CustomStack.java` adhering to LIFO (Last-In, First-Out) with Push, Pop, Peek, and Display operations.
  - Implemented underflow protection preventing program crashes when popping/peeking an empty stack.
  - Implemented `CustomQueue.java` adhering to FIFO (First-In, First-Out) using a space-efficient circular array with dynamic expansion.
  - Added empty queue validation, peek/front inspection, and step/nanosecond logging for all queue operations.
  - Integrated Stack and Queue interactive submenus into `Main.java`.

### Member 3: A.P. Poorna Chathuranga
- **Student ID:** 23DA2-0072
- **Assigned Responsibility:** Linked List Component
- **Individual Contribution:**
  - Implemented `CustomLinkedList.java` using a dynamic pointer-based singly linked list (`Node` architecture).
  - Implemented Insert at Head ($O(1)$), Insert at Tail (Append), Insert at Specific Position, Delete by Value, Delete at Position, Search, and Display.
  - Engineered null pointer safety checks for empty list operations, single-node deletions, and boundary invalidation.
  - Connected step-counter tracking to the central benchmark logger and created the interactive Linked List submenu.

### Member 4: H.G.R. Shehan
- **Student ID:** 23DA2-0108
- **Assigned Responsibility:** Graph Component & Traversals, Regression Testing
- **Individual Contribution:**
  - Implemented `CustomGraph.java` using an Adjacency List (`Map<Integer, List<Integer>>` preserving insertion order).
  - Developed Add Vertex, Add Edge (supporting directed and undirected edges, self-loops, and automatic vertex creation), and Graph Display.
  - Implemented Breadth-First Search (BFS) using a FIFO queue and Depth-First Search (DFS) using an explicit stack avoiding call stack overflows on deep graphs ($O(V + E)$ time, $O(V)$ auxiliary space).
  - Implemented pre-loaded six-vertex sample graph utility for demonstration ease.
  - Created standalone regression test suite `CustomGraphTest.java` covering edge cases, duplicate rejection, and deep graph traversals.

---

## 4. System Architecture & Components

```
data-structure-and-graph-analyzer/
├── src/
│   └── com/analyzer/
│       ├── Main.java                        # Interactive console UI & menu routing
│       ├── array/
│       │   └── CustomArray.java             # Dynamic array implementation
│       ├── stack/
│       │   └── CustomStack.java             # LIFO stack with underflow checks
│       ├── queue/
│       │   └── CustomQueue.java             # FIFO circular array queue
│       ├── linkedlist/
│       │   └── CustomLinkedList.java        # Singly linked list with node pointers
│       ├── search/
│       │   └── SearchAlgorithms.java        # Linear Search vs Binary Search
│       ├── graph/
│       │   └── CustomGraph.java             # Adjacency list graph with BFS & DFS
│       ├── benchmark/
│       │   ├── BenchmarkResult.java         # Metric model (steps, time, notes)
│       │   └── PerformanceTracker.java      # Central benchmark registry & table display
│       └── util/
│           └── InputHelper.java             # Safe console input validation helper
└── tests/
    └── com/analyzer/graph/
        └── CustomGraphTest.java             # Standalone regression tests for Graph module
```

---

## 5. Main Menu & Navigation Structure

```
=============================================
       DATA STRUCTURE & GRAPH ANALYZER       
=============================================
1. Array Operations
2. Stack Operations
3. Queue Operations
4. Linked List Operations
5. Searching Operations
6. Graph Operations
7. Performance Comparison
8. Display All Results
9. Exit
=============================================
```

### Submenu Functionalities:
- **1. Array Operations:** Append element, insert at index, delete by index, delete by value, search element, display array with size and capacity, populate random numbers, clear array.
- **2. Stack Operations:** Push value, pop value (with underflow handling), peek top value, display stack top-to-bottom.
- **3. Queue Operations:** Enqueue value, dequeue value (with empty check), peek front value, display queue front-to-rear.
- **4. Linked List Operations:** Insert at tail, insert at head, insert at position, delete by value, delete by position, search element, display list (`10 -> 20 -> NULL`), clear list.
- **5. Searching Operations:** Linear search on current array, binary search on sorted copy, side-by-side comparative benchmark, large dataset benchmark (10,000 items).
- **6. Graph Operations:** Add vertex, add directed/undirected edge, display adjacency structure, BFS traversal, DFS traversal, load 6-vertex sample graph, clear graph.
- **7. Performance Comparison:** Formatted table showing all logged operations, algorithms used, total operational steps, and elapsed time in milliseconds.
- **8. Display All Results:** Instant visual snapshot of all currently active data structures (Array, Stack, Queue, Linked List, Graph) followed by the benchmark comparison table.

---

## 6. Algorithmic Complexity Summary

| Data Structure / Operation | Algorithm / Approach | Time Complexity (Best / Avg / Worst) | Space Complexity |
| :--- | :--- | :--- | :--- |
| **Array Access / Peek** | Direct Indexing | $O(1)$ | $O(1)$ |
| **Array Insert (End)** | Dynamic Array Append | $O(1)$ amortized / $O(n)$ resize | $O(1)$ |
| **Array Insert (Index)** | Shift elements right | $O(1)$ / $O(n)$ / $O(n)$ | $O(1)$ |
| **Array Delete (Index)** | Shift elements left | $O(1)$ / $O(n)$ / $O(n)$ | $O(1)$ |
| **Stack Push / Pop / Peek** | LIFO Array Stack | $O(1)$ | $O(1)$ |
| **Queue Enqueue / Dequeue** | Circular Array FIFO | $O(1)$ | $O(1)$ |
| **Linked List Insert (Head)**| Pointer update | $O(1)$ | $O(1)$ |
| **Linked List Insert (Tail)**| Traversal + pointer | $O(n)$ | $O(1)$ |
| **Linked List Search/Delete**| Sequential pointer walk | $O(1)$ / $O(n)$ / $O(n)$ | $O(1)$ |
| **Linear Search** | Sequential comparison | $O(1)$ / $O(n)$ / $O(n)$ | $O(1)$ |
| **Binary Search** | Divide-and-conquer (Sorted)| $O(1)$ / $O(\log n)$ / $O(\log n)$ | $O(1)$ |
| **Graph BFS Traversal** | Queue-based exploration | $O(V + E)$ | $O(V)$ auxiliary |
| **Graph DFS Traversal** | Stack-based exploration | $O(V + E)$ | $O(V)$ auxiliary |

---

## 7. How to Build, Run, and Test

### Prerequisites
- Java Development Kit (JDK 8 or higher)
- Git

### Build & Run the Main Application
```powershell
# Clone the repository
git clone https://github.com/ChamikaKarunarathna/data-structure-and-graph-analyzer.git
cd data-structure-and-graph-analyzer

# Compile all source files into bin directory
javac -d bin -sourcepath src src/com/analyzer/Main.java

# Run the console application
java -cp bin com.analyzer.Main
```

### Run Automated Regression Tests
A standalone test suite validating graph behavior, directed/undirected edges, cycle handling, deep graph scalability (20,000 nodes), and benchmark retention can be run without external libraries:
```powershell
# Compile test suite
javac -cp bin -d bin tests/com/analyzer/graph/CustomGraphTest.java

# Execute regression tests
java -cp bin com.analyzer.graph.CustomGraphTest
```

---

## 8. GitHub Collaboration & Version Control Evidence

In compliance with **Section 9 (GitHub Collaboration Requirement)** of the assignment:
- Individual feature branches were created and maintained (`do1`, `do2`, `do3`, `do4`, `do5`).
- Collaboration tracked through Pull Requests, code reviews, and explicit non-fast-forward merge commits.
- Meaningful commit messages and version release tags:
  - `v0.1.0`: Project architecture, CustomArray, and SearchAlgorithms baseline.
  - `v0.1.1`: Precision fix for binary search comparison step counting.
  - `v1.0.0`: Full integration of Stack, Queue, Linked List, Graph, and centralized benchmark suite.
