package uvg.edu.gt;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FloydWarshallTest {
    private Graph graph;
    private FloydWarshall floydWarshall;

    @Before
    public void setUp() {
        graph = new Graph(10);
        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 3);
        graph.addEdge("A", "C", 10);
        floydWarshall = new FloydWarshall(graph);
        floydWarshall.runFloydWarshall();
    }

    @Test
    public void testFloydWarshall() {
        int[][] distances = floydWarshall.getDistances();
        int fromIndex = graph.getNodes().get("A");
        int toIndex = graph.getNodes().get("C");
        assertEquals(8, distances[fromIndex][toIndex]);
    }

    @Test
    public void testGetPath() {
        int fromIndex = graph.getNodes().get("A");
        int toIndex = graph.getNodes().get("C");
        String path = floydWarshall.getPath(fromIndex, toIndex);
        assertEquals("0 -> 1 -> 2", path);
    }

    @Test
    public void testGetGraphCenter() {
        int center = floydWarshall.getGraphCenter();
        assertEquals(2, center);
    }

}
