package searchAlgorithm;

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import map.*;
public class BFSSearch implements SearchAlgorithm {
    public List<int[]> findPath(map.Map map, int[] start, int[] goal) {
        Queue<int[]> frontier = new LinkedList<>();
        HashMap<String, int[]> cameFrom = new HashMap<>();
        frontier.offer(start);
        cameFrom.put(getKey(start[0], start[1]), null);

        while (!frontier.isEmpty()) {
            int[] current = frontier.poll();

            if (current[0] == goal[0] && current[1] == goal[1]) {
                return reconstructPath(cameFrom, current);
            }

            for (int[] neighbor :  getNeighbors(map, current)) {
                String key = getKey(neighbor[0], neighbor[1]);
                if (!cameFrom.containsKey(key)) {
                    frontier.offer(neighbor);
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

    private List<int[]> reconstructPath(HashMap<String, int[]> cameFrom, int[] current) {
        List<int[]> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.get(getKey(current[0], current[1])) != null) {
            current = cameFrom.get(getKey(current[0], current[1]));
            path.add(0, current);
        }

        return path;
    }

    private String getKey(int x, int y) {
        return x + "," + y;
    }
}
