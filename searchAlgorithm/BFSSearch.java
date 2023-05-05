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
    public List<int[]> findPath(Map map, int[] start, int[] goal) {
        Queue<int[]> queue = new LinkedList<>(); // This is to check the next possible path is where
        HashMap<String, int[]> cameFrom = new HashMap<>(); // This is the map to remember where it starts from
        queue.add(start); // This adds the start into frontier
        cameFrom.put(getKey(start[0], start[1]), null); // Use string as key here cuz comparing between

        while (!queue.isEmpty()) {
            int[] n = queue.poll();

            // If goal is found, return path
            if( (n[0]==goal[0]) && (n[1]==goal[1]) ){
                return reconstructPath(cameFrom, n);
            }
            // If not go here
            for (int[] neighbor :  getNeighbors(map, n)) {
                String key = getKey(neighbor[0], neighbor[1]);
                if (!cameFrom.containsKey(key)) {
                    queue.add(neighbor);
                    cameFrom.put(key, n);
                }
            }
        }

        return null; // If not found, just null anyways
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
