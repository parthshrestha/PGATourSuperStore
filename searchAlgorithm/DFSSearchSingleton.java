package searchAlgorithm;

public class DFSSearchSingleton {
    private static DFSSearchSingleton instance;
    private DFSSearch dfsSearch;
    private DFSSearchSingleton(){
        dfsSearch = new DFSSearch();
    };

    public static DFSSearchSingleton getInstance(){
        if(instance == null){
            instance = new DFSSearchSingleton();
        }
        return instance;
    }

    public DFSSearch getDfsSearch(){
        return dfsSearch;
    }
}
