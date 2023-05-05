package searchAlgorithm;
import map.Map;

import java.util.*;

// DFS Search is inspired from these links
// https://www.baeldung.com/java-depth-first-search#:~:text=Depth%2Dfirst%20search%20(DFS),moving%20to%20explore%20another%20branch.
// With changes to fit with the Map class
public class DFSSearch implements SearchAlgorithm {
    public List<int[]> findPath(Map map, int[] start, int[] goal) {
        Stack<int[]> stack = new Stack<>();
        HashMap<String, int[]> cameFrom = new HashMap<>(); // Same as BFS to know where you're coming from since we're using 2d arrays
        stack.push(start);
        cameFrom.put(getKey(start[0], start[1]), null);

        while (!stack.isEmpty()) {
            int[] current = stack.pop();

            // Check if it's goal, if it is reconstruct
            if ((current[0]==goal[0]) && (current[1] == goal[1])) {
                return reconstructPath(cameFrom, current);
            }

            for (int[] neighbor : getNeighbors(map, current)) {
                String key = getKey(neighbor[0], neighbor[1]);
                if (!cameFrom.containsKey(key)) { // Rather than having a visited node, the cameFrom node tells if you have visited a node or not fro before
                    stack.push(neighbor);
                    cameFrom.put(key, current);
                }
            }
        }

        return null;
    }

    public List<int[]> getNeighbors(Map map, int[] current) {
        int tempX = current[0];
        int tempY = current[1];
        List<int[]> neighbors = new ArrayList<>();

        // Check for all x+1, x-1, y+1 and y-1 coordinates if they are viable neighbors or not

        if ((map.isValidCoordinate(tempX - 1, tempY)) && (map.isPath(tempX - 1, tempY))) {
            neighbors.add(new int[]{tempX - 1, tempY});
        }

        if ((map.isValidCoordinate(tempX + 1, tempY)) && (map.isPath(tempX + 1, tempY))) {
            neighbors.add(new int[]{tempX + 1, tempY});
        }

        if ((map.isValidCoordinate(tempX, tempY - 1)) && (map.isPath(tempX, tempY - 1))) {
            neighbors.add(new int[]{tempX, tempY - 1});
        }

        if ((map.isValidCoordinate(tempX, tempY + 1)) && (map.isPath(tempX, tempY + 1))) {
            neighbors.add(new int[]{tempX, tempY + 1});
        }

        return neighbors;
    }

    // Reconstruct path from the Map that tells where the node cameFrom and what the current was (most likely goal)
    private List<int[]> reconstructPath(HashMap<String, int[]> cameFrom, int[] current) {
        List<int[]> path = new ArrayList<>();
        path.add(current);
        while (cameFrom.get(getKey(current[0], current[1])) != null) {
            current = cameFrom.get(getKey(current[0], current[1]));
            path.add(0, current);
        }
        return path;
    }

    // Use key here for the Map between String and pairing of int coordinates
    private String getKey(int x, int y) {
        return x+","+y;
    }
}