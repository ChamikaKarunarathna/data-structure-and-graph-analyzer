package com.analyzer.graph;

import com.analyzer.benchmark.BenchmarkResult;
import com.analyzer.benchmark.PerformanceTracker;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/** Standalone regression tests; no external test framework is required. */
public final class CustomGraphTest {
    public static void main(String[] args) {
        PrintStream console = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (PrintStream captured = new PrintStream(output)) {
            System.setOut(captured);
            testSampleAndReset(output);
            testDirectedEdgesAndCycles();
            testInvalidStartsAndSelfLoops();
            testDeepGraph();
        } finally {
            System.setOut(console);
            PerformanceTracker.clearResults();
        }
        System.out.println("All CustomGraph tests passed.");
    }

    private static void testSampleAndReset(ByteArrayOutputStream output) {
        CustomGraph graph = new CustomGraph();
        graph.loadSampleGraph();
        check(!graph.addVertex(1), "Existing vertices must retain their edges");
        check(!graph.addEdge(2, 1, false), "Reversed undirected duplicate");
        checkTraversal(graph, true, 1, 20, 1, 2, 3, 4, 5, 6);
        checkTraversal(graph, false, 1, 20, 1, 2, 4, 6, 5, 3);
        output.reset();
        graph.displayGraph();
        String expected = "Vertex 1 -> [2, 3]\nVertex 2 -> [1, 4, 5]\n"
                + "Vertex 3 -> [1, 5]\nVertex 4 -> [2, 6]\n"
                + "Vertex 5 -> [2, 3, 6]\nVertex 6 -> [4, 5]\n";
        check(expected.equals(output.toString().replace("\r\n", "\n")), "Adjacency display");
        graph.addVertex(99);
        checkTraversal(graph, true, 99, 1, 99);
        checkTraversal(graph, false, 99, 1, 99);
        graph.loadSampleGraph();
        check(graph.bfs(99).isEmpty(), "Loading sample must replace previous data");
        checkTraversal(graph, true, 1, 20, 1, 2, 3, 4, 5, 6);
        int previousRecords = PerformanceTracker.getAllResults().size();
        graph.clear();
        graph.clear();
        check(graph.bfs(1).isEmpty(), "Clear removes vertices");
        check(PerformanceTracker.getAllResults().size() == previousRecords,
                "Clear and invalid traversals must preserve benchmark history");
        output.reset();
        graph.displayGraph();
        check(output.toString().contains("Graph is empty"), "Empty graph display");
    }

    private static void testDirectedEdgesAndCycles() {
        CustomGraph graph = new CustomGraph();
        check(graph.addEdge(1, 2, true), "Create missing endpoints");
        check(!graph.addEdge(1, 2, true), "Ignore duplicate directed edge");
        checkTraversal(graph, true, 2, 1, 2);
        checkTraversal(graph, false, 2, 1, 2);
        graph.addEdge(1, 3, true);
        graph.addEdge(2, 3, true);
        graph.addEdge(3, 1, true);
        graph.addEdge(2, 4, true);
        checkTraversal(graph, true, 1, 9, 1, 2, 3, 4);
        checkTraversal(graph, false, 1, 9, 1, 2, 3, 4);
        check(graph.addEdge(1, 2, false), "Undirected insertion adds missing reverse edge");
        check(!graph.addEdge(2, 1, false), "Mixed duplicate is ignored");
        checkTraversal(graph, true, 1, 10, 1, 2, 3, 4);
        checkTraversal(graph, false, 1, 10, 1, 2, 3, 4);
    }

    private static void testInvalidStartsAndSelfLoops() {
        PerformanceTracker.clearResults();
        CustomGraph graph = new CustomGraph();
        check(graph.bfs(0).isEmpty(), "BFS on empty graph");
        check(graph.dfs(0).isEmpty(), "DFS on empty graph");
        graph.addVertex(-7);
        check(graph.bfs(123).isEmpty(), "Missing BFS start");
        check(graph.dfs(123).isEmpty(), "Missing DFS start");
        check(PerformanceTracker.getAllResults().isEmpty(), "Invalid runs are not recorded");
        check(graph.addEdge(-7, -7, false), "Self-loop added once");
        check(!graph.addEdge(-7, -7, true), "Duplicate self-loop ignored");
        checkTraversal(graph, true, -7, 2, -7);
        checkTraversal(graph, false, -7, 2, -7);
        graph.bfs(-7).clear();
        checkTraversal(graph, true, -7, 2, -7);
    }

    private static void testDeepGraph() {
        CustomGraph graph = new CustomGraph();
        int size = 20000;
        for (int i = 0; i < size - 1; i++) {
            graph.addEdge(i, i + 1, true);
        }
        List<Integer> path = graph.dfs(0);
        check(path.size() == size, "DFS handles deep paths without call stack overflow");
        for (int i = 0; i < size; i++) {
            check(path.get(i) == i, "Deep DFS order at " + i);
        }
        List<BenchmarkResult> results = PerformanceTracker.getAllResults();
        check(results.get(results.size() - 1).getSteps() == 2L * size - 1,
                "Deep graph step count");
    }

    private static void checkTraversal(CustomGraph graph, boolean bfs, int start,
            long steps, Integer... expected) {
        int before = PerformanceTracker.getAllResults().size();
        List<Integer> actual = bfs ? graph.bfs(start) : graph.dfs(start);
        check(actual.equals(Arrays.asList(expected)), "Traversal order: " + actual);
        List<BenchmarkResult> results = PerformanceTracker.getAllResults();
        check(results.size() == before + 1, "Exactly one benchmark per traversal");
        BenchmarkResult result = results.get(before);
        check("Graph Traversal".equals(result.getOperation()), "Benchmark operation");
        check((bfs ? "BFS" : "DFS").equals(result.getAlgorithm()), "Benchmark algorithm");
        check(result.getSteps() == steps, "Expected " + steps + " steps, got " + result.getSteps());
        check(result.getExecutionTimeNs() >= 0, "Nonnegative elapsed time");
        check(result.getNotes().equals("Start: " + start + "; Visited: " + actual),
                "Benchmark notes include start and visited path");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
