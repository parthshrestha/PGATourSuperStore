package searchAlgorithm;
import map.Map;

import java.util.*;
public class DFSSearch implements SearchAlgorithm {
    public List<int[]> findPath(map.Map map, int[] start, int[] goal) {
        Stack<int[]> frontier = new Stack<>();
        HashMap<String, int[]> cameFrom = new HashMap<>();
        frontier.push(start);
        cameFrom.put(getKey(start[0], start[1]), null);

        while (!frontier.isEmpty()) {
            int[] current = frontier.pop();

            if (current[0] == goal[0] && current[1] == goal[1]) {
                return reconstructPath(cameFrom, current);
            }

            for (int[] neighbor : getNeighbors(map, current)) {
                String key = getKey(neighbor[0], neighbor[1]);
                if (!cameFrom.containsKey(key)) {
                    frontier.push(neighbor);
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