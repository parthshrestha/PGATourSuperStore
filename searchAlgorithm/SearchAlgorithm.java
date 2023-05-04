package searchAlgorithm;

import map.Map;

import java.util.List;

public interface SearchAlgorithm {
    public List<int[]> getNeighbors(Map map, int[] current);
    public List<int[]> findPath(Map map, int[] start, int[] goal);

}
