package dijkstraAlgorithm;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class DijkstraShortestPaths {
    public LinkedHashSet<Integer> getVisitOrder() {
        return visitOrder;
    }

    public Double[] getShortestPaths() {
        return shortestPaths;
    }

    public int getFromValue() {
        return fromValue;
    }

    private final Double[] shortestPaths;
    private final int fromValue;
    private final LinkedHashSet<Integer> visitOrder = new LinkedHashSet<>();

    public DijkstraShortestPaths(Double[][] paths, int from) {
        validateInput(paths, from);
        this.fromValue = from;
        shortestPaths = findShortestPaths(paths, from);
    }

    private Double[] findShortestPaths(Double[][] paths, int from) {
        Double[] result = new Double[paths.length];
        Arrays.fill(result, Double.POSITIVE_INFINITY);
        result[from] = 0d;

        visitOrder.add(from);
        result = recount(paths, result.clone(), from);
        int minValueIndex = findMinValueIndex(result, visitOrder, from);

        while (visitOrder.size() < paths.length) {
            visitOrder.add(minValueIndex);
            result = recount(paths, result.clone(), minValueIndex);
            minValueIndex = findMinValueIndex(result, visitOrder, minValueIndex);
        }
        return result;
    }

    private static Double[] recount(Double[][] paths, Double[] result, int from){
        for (int j = 0; j < paths.length; j++) {
            if (paths[from][j] < Double.POSITIVE_INFINITY) {
                double countedPath = result[from] + paths[from][j];
                result[j] = Math.min(countedPath, result[j]);
            }
        }
        return result;
    }

    private int findMinValueIndex(Double[] arr, Set<Integer> visited, int exceptOf) {
        Double value = Double.POSITIVE_INFINITY;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != exceptOf && arr[i] < value && !visited.contains(i)) {
                value = arr[i];
                index = i;
            }
        }
        return index;
    }

    private void validateInput(Double[][] paths, int from) {
        if (from < 0 || from > paths.length) {
            throw new IllegalArgumentException("From param must be greater or equal to 0 and less or equal to size of input array");
        }
        if (paths.length == 0) {
            throw new IllegalArgumentException("Paths size must be greater or equal to 0");
        }
        for (Double[] path : paths) {
            if (path.length != paths.length) {
                throw new IllegalArgumentException("Inner arrays of paths should have the same length as the outer one");
            }
        }
        for (int i = 0; i < paths.length; i++) {
            for (int j = 0; j < paths[i].length; j++) {
                if (paths[i][j] < 0) {
                    throw new IllegalArgumentException("Paths must not be negative");
                }
                if (!paths[i][j].equals(paths[j][i])){
                    throw new IllegalArgumentException("Graph must not be oriented");
                }
            }
        }
    }
}
