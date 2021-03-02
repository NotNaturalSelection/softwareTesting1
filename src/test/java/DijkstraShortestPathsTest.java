import java.util.LinkedHashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dijkstraAlgorithm.DijkstraShortestPaths;

public class DijkstraShortestPathsTest {
    private static final Double inf = Double.POSITIVE_INFINITY;

    @Test
    public void testCommonCase() {
        LinkedHashSet<Integer> expectedOrder = new LinkedHashSet<>();
        int[] intOrder = new int[]{0, 1, 4, 2, 3};
        for (int i : intOrder) {
            expectedOrder.add(i);
        }

        Double[][] paths = new Double[][]{{0d, 10d, 30d, 50d, 10d},
                                          {10d, 0d, inf, 40d, inf},
                                          {30d, inf, 0d, 20d, 10d},
                                          {50d, 40d, 20d, 0d, 30d},
                                          {10d, inf, 10d, 30d, 0d}};
        Double[] expected = new Double[]{0d, 10d, 20d, 40d, 10d};
        DijkstraShortestPaths sp = new DijkstraShortestPaths(paths, 0);
        Assertions.assertArrayEquals(expected, sp.getShortestPaths());
        Assertions.assertArrayEquals(expectedOrder.toArray(), sp.getVisitOrder().toArray());
    }

    @Test
    public void test2Nodes() {
        Double[][] paths = new Double[][]{{0d, 10d}, {10d, 0d}};
        Double[] expected = new Double[]{0d, 10d};
        DijkstraShortestPaths sp = new DijkstraShortestPaths(paths, 0);
        Assertions.assertArrayEquals(expected, sp.getShortestPaths());
        DijkstraShortestPaths sp1 = new DijkstraShortestPaths(paths, 1);
        Double[] expected1 = new Double[]{10d, 0d};
        Assertions.assertArrayEquals(expected1, sp1.getShortestPaths());
    }

    @Test
    public void test2NodesAreEqual() {
        Double[][] paths = new Double[][]{{0d, 10d}, {10d, 0d}};

        Double[] expected = new Double[]{0d, 10d};
        DijkstraShortestPaths sp = new DijkstraShortestPaths(paths, 0);
        Assertions.assertArrayEquals(expected, sp.getShortestPaths());

        Double[] expected1 = new Double[]{10d, 0d};
        DijkstraShortestPaths sp1 = new DijkstraShortestPaths(paths, 1);
        Assertions.assertArrayEquals(expected1, sp1.getShortestPaths());
    }

    @Test
    public void testZeroLength() {
        Double[][] paths = new Double[0][0];
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DijkstraShortestPaths(paths, 0));
    }

    @Test
    public void testNegativePaths() {
        Double[][] paths = new Double[][]{{0d, 10d, 30d, -50d, -10d},
                                          {inf, 0d, inf, inf, inf},
                                          {inf, inf, 0d, inf, 10d},
                                          {inf, -40d, -20d, 0d, inf},
                                          {10d, inf, 10d, 30d, 0d}};
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DijkstraShortestPaths(paths, 0));
    }

    @Test
    public void testDifferentArraysLength() {
        Double[][] paths = new Double[][]{{0d, 10d, 30d, 50d, 10d}, {inf, 0d, inf}, {inf}, {}, {10d, inf, 10d, 30d, 0d, 0d, 182d, 51d}};
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DijkstraShortestPaths(paths, 0));
    }

    @Test
    public void testNegativeFromValue() {
        Double[][] paths = new Double[][]{};
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DijkstraShortestPaths(paths, -3));
    }
}
