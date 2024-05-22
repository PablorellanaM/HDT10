package uvg.edu.gt;

public class FloydWarshall {
    private final int INF = Integer.MAX_VALUE;
    private int[][] dist;
    private int[][] next;
    private Graph graph;

    public FloydWarshall(Graph graph) {
        this.graph = graph;
        int size = graph.getSize();
        dist = new int[size][size];
        next = new int[size][size];
        initialize();
    }

    private void initialize() {
        int size = graph.getSize();
        int[][] adjMatrix = graph.getAdjMatrix();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dist[i][j] = adjMatrix[i][j];
                if (adjMatrix[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }
    }

    public void runFloydWarshall() {
        int size = graph.getSize();

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    public int[][] getDistances() {
        return dist;
    }

    public String getPath(int u, int v) {
        if (next[u][v] == -1) {
            return "No path";
        }
        StringBuilder path = new StringBuilder();
        while (u != v) {
            path.append(u + " -> ");
            u = next[u][v];
        }
        path.append(v);
        return path.toString();
    }

    public int getGraphCenter() {
        int size = graph.getSize();
        int[] eccentricity = new int[size];

        for (int i = 0; i < size; i++) {
            int maxDist = 0;
            for (int j = 0; j < size; j++) {
                if (dist[i][j] != INF) {
                    maxDist = Math.max(maxDist, dist[i][j]);
                }
            }
            eccentricity[i] = maxDist;
        }

        int center = 0;
        int minEccentricity = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            if (eccentricity[i] < minEccentricity) {
                minEccentricity = eccentricity[i];
                center = i;
            }
        }

        return center;
    }
}
