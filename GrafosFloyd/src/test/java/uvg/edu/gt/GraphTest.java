package uvg.edu.gt;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {
    private Graph graph;

    @Before
    public void setUp() {
        graph = new Graph(10);
    }

    @Test
    public void testAddNode() {
        graph.addNode("A");
        assertTrue(graph.getNodes().containsKey("A"));
    }

    @Test
    public void testRemoveEdge() {
        graph.addEdge("A", "B", 5);
        graph.removeEdge("A", "B");
        int fromIndex = graph.getNodes().get("A");
        int toIndex = graph.getNodes().get("B");
        assertEquals(Integer.MAX_VALUE, graph.getAdjMatrix()[fromIndex][toIndex]);
    }

    @Test
    public void testGetSize() {
        graph.addNode("A");
        graph.addNode("B");
        assertEquals(2, graph.getSize());
    }

    @Test
    public void testPrintAdjMatrix() {
        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 10);
        graph.printAdjMatrix();

    }

}
