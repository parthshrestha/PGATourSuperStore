package searchAlgorithm;

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import map.*;

public class DijkstraSearch implements SearchAlgorithm {
    public List<int[]> findPath(Map map, int[] start, int[] goal) {
        PriorityQueue<int[]> frontier = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        HashMap<String, int[]> cameFrom = new HashMap<>();
        HashMap<String, Integer> costSoFar = new HashMap<>();
        frontier.offer(new int[]{start[0], start[1], 0});
        cameFrom.put(getKey(start[0], start[1]), null);
        costSoFar.put(getKey(start[0], start[1]), 0);

        while (!frontier.isEmpty()) {
            int[] current = frontier.poll();

            if (current[0] == goal[0] && current[1] == goal[1]) {
                return reconstructPath(cameFrom, current);
            }

            for (int[] neighbor : getNeighbors(map, current)) {
                String key = getKey(neighbor[0], neighbor[1]);
                int newCost = costSoFar.get(getKey(current[0], current[1])) + getCost(current, neighbor);
                if (!costSoFar.containsKey(key) || newCost < costSoFar.get(key)) {
                    costSoFar.put(key, newCost);
                    int priority = newCost;
                    frontier.offer(new int[]{neighbor[0], neighbor[1], priority});
                    cameFrom.put(key, current);
                }
            }
        }

        return null;
    }

    public List<int[]> getNeighbors(Map map, int[] current) {
        int x = current[0];
        int y = current[1];
        List<int[]> neighbors = new ArrayList<>();

        if ((map.isValidCoordinate(x - 1, y)) && (map.isPath(x - 1, y))) {
            neighbors.add(new int[]{x - 1, y});
        }

        if ((map.isValidCoordinate(x + 1, y)) && (map.isPath(x + 1, y))) {
            neighbors.add(new int[]{x + 1, y});
        }

        if ((map.isValidCoordinate(x, y - 1)) && (map.isPath(x, y - 1))) {
            neighbors.add(new int[]{x, y - 1});
        }

        if ((map.isValidCoordinate(x, y + 1)) && (map.isPath(x, y + 1))) {
            neighbors.add(new int[]{x, y + 1});
        }

        return neighbors;
    }

    public List<int[]> reconstructPath(HashMap<String, int[]> cameFrom, int[] current) {
        List<int[]> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.get(getKey(current[0], current[1])) != null) {
            current = cameFrom.get(getKey(current[0], current[1]));
            path.add(0, current);
        }

        return path;
    }

    public String getKey(int x, int y) {
        return x + "," + y;
    }

    private int getCost(int[] current, int[] neighbor) {
        int x = Math.abs(current[0] - neighbor[0]);
        int y = Math.abs(current[1] - neighbor[1]);
        return x+y;
    }
}

