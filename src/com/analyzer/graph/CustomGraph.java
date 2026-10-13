package com.analyzer.graph;

import com.analyzer.benchmark.BenchmarkResult;
import com.analyzer.benchmark.PerformanceTracker;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Integer graph represented by adjacency lists in insertion order.
 * Developed by Member 4: H.G.R. Shehan (23DA2-0108).
 *
 * <p>Missing edge endpoints are created automatically. Duplicate adjacency
 * entries are ignored; self-loops are stored once. Direction is chosen per
 * edge, so directed and undirected edges can coexist. Adding a directed edge
 * never removes an existing reverse edge.
 *
 * <p>Traversals visit only vertices reachable from the starting vertex.
 * Each visited vertex and each inspected adjacency entry counts as one step.
 * An undirected edge normally contributes two inspected entries. Traversals
 * take O(V + E) time and O(V) auxiliary space for the reachable subgraph.
 * This class is intended for single-threaded console use.
 */
public class CustomGraph {
    private final Map<Integer, List<Integer>> adjacencyList = new LinkedHashMap<>();

    /** Adds a vertex without overwriting existing neighbors. */
    public boolean addVertex(int vertex) {
        if (adjacencyList.containsKey(vertex)) {
            return false;
        }
        adjacencyList.put(vertex, new ArrayList<Integer>());
        return true;
    }

    /**
     * Adds missing endpoints and the requested connection.
     * Returns true if at least one adjacency entry was added.
     */
    public boolean addEdge(int source, int destination, boolean isDirected) {
        addVertex(source);
        addVertex(destination);
        boolean changed = addNeighbor(source, destination);
        if (!isDirected) {
            boolean reverseAdded = addNeighbor(destination, source);
            changed = changed || reverseAdded;
        }
        return changed;
    }

    private boolean addNeighbor(int source, int destination) {
        List<Integer> neighbors = adjacencyList.get(source);
        if (neighbors.contains(destination)) {
            return false;
        }
        neighbors.add(destination);
        return true;
    }

    /** Prints each vertex and its outgoing neighbors. */
    public void displayGraph() {
        if (adjacencyList.isEmpty()) {
            System.out.println("Graph is empty. Add vertices or load the sample graph.");
            return;
        }
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.println("Vertex " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    /**
     * Prints, records, and returns the BFS visit order using a FIFO queue.
     * Invalid starting vertices return an empty list without recording a run.
     * The returned list is independent of the graph.
     */
    public List<Integer> bfs(int startVertex) {
        if (!validateStart(startVertex)) {
            return new ArrayList<Integer>();
        }

        long startTime = System.nanoTime();
        long steps = 0;
        List<Integer> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            path.add(vertex);
            steps++; // Visit one vertex.
            for (int neighbor : adjacencyList.get(vertex)) {
                steps++; // Inspect one outgoing adjacency entry.
                if (visited.add(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }

        long elapsed = System.nanoTime() - startTime;
        reportTraversal("BFS", startVertex, path, steps, elapsed);
        return path;
    }

    /**
     * Prints, records, and returns the DFS visit order using an explicit stack.
     * Iterator frames preserve recursive DFS order without using the call stack.
     * Invalid starting vertices return an empty list without recording a run.
     * The returned list is independent of the graph.
     */
    public List<Integer> dfs(int startVertex) {
        if (!validateStart(startVertex)) {
            return new ArrayList<Integer>();
        }

        long startTime = System.nanoTime();
        long steps = 0;
        List<Integer> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Iterator<Integer>> stack = new ArrayDeque<>();
        visited.add(startVertex);
        path.add(startVertex);
        steps++; // Visit the starting vertex.
        stack.push(adjacencyList.get(startVertex).iterator());

        while (!stack.isEmpty()) {
            Iterator<Integer> neighbors = stack.peek();
            if (!neighbors.hasNext()) {
                stack.pop();
                continue;
            }
            int neighbor = neighbors.next();
            steps++; // Inspect one outgoing adjacency entry.
            if (visited.add(neighbor)) {
                path.add(neighbor);
                steps++; // Visit a newly discovered vertex.
                stack.push(adjacencyList.get(neighbor).iterator());
            }
        }

        long elapsed = System.nanoTime() - startTime;
        reportTraversal("DFS", startVertex, path, steps, elapsed);
        return path;
    }

    private boolean validateStart(int startVertex) {
        if (adjacencyList.isEmpty()) {
            System.out.println("Graph is empty. Add vertices or load the sample graph first.");
            return false;
        }
        if (!adjacencyList.containsKey(startVertex)) {
            System.out.println("Vertex " + startVertex + " does not exist in the graph.");
            return false;
        }
        return true;
    }

    // Formatting, console output, and benchmark recording are outside the timer.
    private void reportTraversal(String algorithm, int startVertex,
            List<Integer> path, long steps, long elapsed) {
        PerformanceTracker.record(new BenchmarkResult("Graph Traversal", algorithm,
                steps, elapsed, "Start: " + startVertex + "; Visited: " + path));
        System.out.println(algorithm + " traversal from " + startVertex + ": " + path);
        System.out.println("Steps Taken: " + steps);
        System.out.println("Execution Time: " + elapsed + " ns");
    }

    /** Replaces the current graph with six connected vertices and seven undirected edges. */
    public void loadSampleGraph() {
        clear();
        for (int vertex = 1; vertex <= 6; vertex++) {
            addVertex(vertex);
        }
        addEdge(1, 2, false);
        addEdge(1, 3, false);
        addEdge(2, 4, false);
        addEdge(2, 5, false);
        addEdge(3, 5, false);
        addEdge(4, 6, false);
        addEdge(5, 6, false);
    }

    /** Removes all vertices and edges; existing benchmark history is retained. */
    public void clear() {
        adjacencyList.clear();
    }
}
