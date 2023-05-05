package searchAlgorithm;

public class DijkstraSearchSingleton {
    private static DijkstraSearchSingleton instance;
    private DijkstraSearch dijkstraSearch;
    private DijkstraSearchSingleton(){
        dijkstraSearch = new DijkstraSearch();
    };

    public static DijkstraSearchSingleton getInstance(){
        if(instance == null){
            instance = new DijkstraSearchSingleton();
        }
        return instance;
    }

    public DijkstraSearch getDijkstraSearch(){
        return dijkstraSearch;
    }
}
