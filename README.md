# Data Structure and Graph Performance Analyzer

**Course:** CIT300 - Data Structures and Algorithms  
**Assessment:** Graded Practical Assignment 2 (Week 12)  
**Type:** Console-Based System (Java)  
**Institution:** Sri Lanka Technological Campus (SLTC)  

---

## 1. Project Overview
The **Data Structure and Graph Performance Analyzer** is an integrated console-based Java application designed to demonstrate the implementation, traversal, algorithmic behavior, and comparative performance of core data structures.

The system allows users to interactively test and benchmark operations on:
- Arrays
- Stacks
- Queues
- Linked Lists
- Searching Algorithms (Linear Search vs. Binary Search)
- Graphs (Adjacency representations, Breadth-First Search, Depth-First Search)
- Comparative Performance & Complexity Analysis (step count & execution time)

---

## 2. Team Members & Responsibilities

| Student Name | Student ID | Assigned Responsibility |
| :--- | :--- | :--- |
| **C.K.L. Karunarathna** | 23DA2-0096 | Project Initialization, Array Operations, Searching Implementation, Main Integration |
| **K.D.P. Jayathilaka** | 23DA2-0105 | Stack Operations, Queue Operations, Performance Benchmarking Collaboration |
| **A.P. Poorna Chathuranga** | 23DA2-0072 | Linked List Implementation, Edge Case Handling, System Integration Collaboration |
| **H.G.R. Shehan** | 23DA2-0108 | Graph Implementation, Graph Traversals (BFS & DFS), Testing Collaboration |

---

## 3. Individual Contributions

### Member 1: C.K.L. Karunarathna (23DA2-0096)
- **Role:** Project Lead & Module Developer
- **Assigned Modules:** Project Structure, Array Operations, Searching Algorithms
- **Individual Contribution:**
  - Initialized project architecture and repository workflow.
  - Implemented Array data structure (Insert, Delete, Search, Display).
  - Implemented Linear Search and Binary Search algorithms with step-counter tracking.
  - Assisted in main menu integration and system testing.

### Member 2: K.D.P. Jayathilaka (23DA2-0105)
- **Role:** Module Developer
- **Assigned Modules:** Stack and Queue Operations
- **Individual Contribution:**
  - Implemented Stack data structure with Push, Pop, Peek, Display, and empty/overflow checks.
  - Implemented Queue data structure with Enqueue, Dequeue, Peek/Front, and Display.
  - Handled error states and boundary conditions for linear data structures.

### Member 3: A.P. Poorna Chathuranga (23DA2-0072)
- **Role:** Module Developer
- **Assigned Modules:** Linked List Operations
- **Individual Contribution:**
  - Implemented Linked List class and dynamic node architecture.
  - Implemented Insert, Delete, Search, and Display operations.
  - Handled null reference checks and traversal step tracking.

### Member 4: H.G.R. Shehan (23DA2-0108)
- **Role:** Module Developer
- **Assigned Modules:** Graph Operations and Traversals
- **Individual Contribution:**
  - Implemented Graph representation (Adjacency List / Matrix).
  - Implemented Add Vertex, Add Edge, and Graph Display operations.
  - Implemented Breadth-First Search (BFS) and Depth-First Search (DFS) traversals.
  - Documented graph traversal complexity metrics.

---

## 4. Key Features & Menu Structure
- **Main Menu Options:**
  1. Array Operations
  2. Stack Operations
  3. Queue Operations
  4. Linked List Operations
  5. Searching Operations
  6. Graph Operations
  7. Performance Comparison
  8. Display All Results
  9. Exit
- **Interactive Submenus:** Each component features dedicated submenus with input validation.
- **Performance Benchmarking:** Records execution time (nanoseconds/milliseconds) and operational step counts for algorithmic comparison.

---

## 5. Technologies Used
- **Language:** Java (JDK 8 or higher)
- **Paradigm:** Object-Oriented Programming (OOP)
- **Version Control:** Git & GitHub (Branching, PRs, Commits)
- **Interface:** Interactive Command-Line Interface (CLI)

---

## 6. How to Build and Run

### Prerequisites
- Java Development Kit (JDK 8 or higher installed)
- Git

### Compilation & Execution
```powershell
# Clone the repository
git clone https://github.com/<your-username>/data-structure-and-graph-analyzer.git
cd data-structure-and-graph-analyzer

# Compile all source files
javac -d bin -sourcepath src src/com/analyzer/Main.java

# Run the console application
java -cp bin com.analyzer.Main
```

### Graph Component (Member 4)

Implemented by H.G.R. Shehan (23DA2-0108) in `src/com/analyzer/graph/CustomGraph.java`.
Main menu option 6 opens the graph submenu. Edges can be directed or undirected;
missing endpoints are created automatically, duplicate edges are ignored, and
negative integer vertex labels and self-loops are supported. Vertices and
neighbors are displayed and traversed in insertion order.

Choose graph option 6 to replace the current graph with the six-vertex sample.
From vertex 1, BFS visits `[1, 2, 3, 4, 5, 6]` and DFS visits
`[1, 2, 4, 6, 5, 3]`. Both take 20 counted steps: six visited vertices plus
14 adjacency entries for seven undirected edges. Press Enter at each pause.
Graph option 8 returns to the main menu; main option 7 displays benchmark history.

Traversals only visit the reachable subgraph and run in O(V + E) time with O(V)
auxiliary space. DFS uses an explicit stack to support deep graphs. Each valid
traversal records its algorithm, step count, nanosecond duration, and visit order.
Timing excludes console output and benchmark recording. Invalid starting vertices
print a message without adding a benchmark. Clearing the graph retains history.

Compile and run the standalone regression tests (no additional dependencies):

```powershell
javac -d bin -sourcepath src src/com/analyzer/Main.java
javac -cp bin -d bin tests/com/analyzer/graph/CustomGraphTest.java
java -cp bin com.analyzer.graph.CustomGraphTest
```
