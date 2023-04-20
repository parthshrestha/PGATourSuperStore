package Observer;

import java.util.ArrayList;

public interface Publisher {
    //part of the observer pattern
    ArrayList<Subscriber> subscribersList = new ArrayList<Subscriber>();
    default void registerSubscriber(Subscriber follower)
    {
        subscribersList.add(follower);
    }
    default void unregisterSubscriber(Subscriber follower)
    {subscribersList.remove(follower);}
    default void notifySubscriber(String fncd,String type,String msg)//types //log,budgetTrack, staffTrack
    {
        for(int i = 0;i<subscribersList.size(); i++)
        {
//            System.out.println(subscribersList.get(i));
            subscribersList.get(i).update(fncd,type,msg);
        }
    }
}
