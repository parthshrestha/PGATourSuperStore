package Observer;

public interface Subscriber {
    //part of the observer pattern
    void update(String type,String msg);
}
