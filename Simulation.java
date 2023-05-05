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
            //ArrayList<Buyer> buyers1 = new ArrayList<Buyer>();
            System.out.println("===== Day: " + (day + 1) + "," + getDayOfTheWeek() + "=====");
//                myDealership.opening();
//                myDealership1.opening();
            pga.opening();

            Random rand = new Random();
            int numService = rand.nextInt(30);
            ArrayList<Customer> customers = new ArrayList<Customer>();

            for(int i = 0; i< numService;i++)
            {
                customers.add(new Customer());
            }

            pga.Service(customers);


            System.out.println("\n-------------------------------------");
            System.out.println("Selling \n");

            for(int j = 0; j < customers.size(); j++)
            {
                Customer customer = customers.get(j);
                if(customer.getIntent() == Enums.CustomerIntent.SHOPPING)
                {
                    pga.selling(customer);
                }
            }

            System.out.println();

//            for(int i = 0; i < pga.size(); i++)
//            {
//                pga.get(i).fitting(customers);
//            }

//

            track.showReport();//should match and part of observer pattern tracking side
//                myDealership.unregisterSubscriber(log);//logger in observer pattern changes every day
//                myDealership1.unregisterSubscriber(log);//logger in observer pattern changes every day

            pga.unregisterSubscriber(log);
            day++;
        }
//        String fncdGraph1 = dealerships.get(0).name + "Graph.jpg";
//        FncdGraph graph1 = new FncdGraph(vehiclesSoldPerDay[0],staffEarningsPerDay[0],fncdEarningsPerDay[0],fncdGraph1);
//        String fncdGraph2 = dealerships.get(1).name + "Graph.jpg";
//        FncdGraph graph2 = new FncdGraph(vehiclesSoldPerDay[1],staffEarningsPerDay[1],fncdEarningsPerDay[1],fncdGraph2);
//        sellingInteractive();

//        myDealership.unregisterSubscriber(track);//tracking from observer pattern ends when simulation does
//        myDealership1.unregisterSubscriber(track);
        pga.unregisterSubscriber(track);
    }

    public PGATourSuperstore getPga(){
        return this.pga;
    }

}
