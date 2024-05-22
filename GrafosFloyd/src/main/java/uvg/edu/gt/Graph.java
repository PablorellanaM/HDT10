package uvg.edu.gt;

import java.util.*;

public class Graph {
    private final int INF = Integer.MAX_VALUE;
    private Map<String, Integer> nodes;
    private int[][] adjMatrix;
    private int size;

    public Graph(int maxNodes) {
        nodes = new HashMap<>();
        adjMatrix = new int[maxNodes][maxNodes];
        size = 0;

        for (int i = 0; i < maxNodes; i++) {
            Arrays.fill(adjMatrix[i], INF);
            adjMatrix[i][i] = 0;
        }
    }

    public void addNode(String node) {
        if (!nodes.containsKey(node)) {
            nodes.put(node, size++);
        }
    }

    public void addEdge(String from, String to, int weight) {
        addNode(from);
        addNode(to);
        int fromIndex = nodes.get(from);
        int toIndex = nodes.get(to);
        adjMatrix[fromIndex][toIndex] = weight;
    }

    public void removeEdge(String from, String to) {
        int fromIndex = nodes.get(from);
        int toIndex = nodes.get(to);
        adjMatrix[fromIndex][toIndex] = INF;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public Map<String, Integer> getNodes() {
        return nodes;
    }

    public int getSize() {
        return size;
    }

    public void printAdjMatrix() {
        System.out.println("Matriz de Adyacencia:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (adjMatrix[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(adjMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
