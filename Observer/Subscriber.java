package Observer;

public interface Subscriber {
    //part of the observer pattern
    void update(String fncd,String type,String msg);
}
