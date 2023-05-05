package searchAlgorithm;

public class BFSSearchSingleton {
    private static BFSSearchSingleton instance;
    private BFSSearch bfsSearch;
    private BFSSearchSingleton(){
        bfsSearch = new BFSSearch();
    };

    public static BFSSearchSingleton getInstance(){
        if(instance == null){
            instance = new BFSSearchSingleton();
        }
        return instance;
    }

    public BFSSearch getBfsSearch(){
        return bfsSearch;
    }

}
