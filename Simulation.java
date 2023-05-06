import Observer.Logger;
import Observer.Publisher;
import Observer.Tracker;
import PGA.PGATourSuperstore;
import buyer.Customer;
import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Simulation implements Publisher {

    PGATourSuperstore pga;

    static int day = 0;
    static String days[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    int numPga;

    public Simulation(PGATourSuperstore pga)
    {
        this.pga = pga;
    }
    public static String getDayOfTheWeek()
    {
        return days[day %7];
    }

    //Runs the simulation for the passed number of days
    public void run(int runDays) {
        day = 0;



        Tracker track = Singleton.getInstanceTracker();
        for(int i = 0; i < 1; i++) {
            pga.registerSubscriber(track);
        }
        while (day < runDays) {
            Logger log = Singleton.getInstanceLogger(day);
            pga.registerSubscriber(log);//adding the subcriber into the list

            ArrayList<Customer> buyers = new ArrayList<Customer>();

            System.out.println("===== Day: " + (day + 1) + "," + getDayOfTheWeek() + "=====");
//
            pga.opening();

            Random rand = new Random();
            int numService = rand.nextInt(20);
            ArrayList<Customer> customers = new ArrayList<Customer>();
            for(int i = 0; i< numService;i++)
            {
                customers.add(new Customer());
            }
            pga.Service(customers);
            pga.fitting(customers);
            for(int i = 0; i <  customers.size(); i++)
            {
                pga.selling(customers.get(i));
            }
            pga.ending();
//

            track.showReport();//should match and part of observer pattern tracking side
//
            pga.unregisterSubscriber(log);
            day++;
        }
//
            pga.unregisterSubscriber(track);
    }

    public PGATourSuperstore getPga(){
        return this.pga;
    }

}
