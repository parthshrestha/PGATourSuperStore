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
    //    ArrayList <Integer>[] vehiclesSoldPerDay;// to account for multiple fncd
//    ArrayList <Double>[] staffEarningsPerDay;
//    ArrayList<Double>[] fncdEarningsPerDay;
    public Simulation(PGATourSuperstore pga)
    {
        this.pga = pga;

        //this.dealerships.add(new FNCD("FNCD"+String.valueOf(numFNCD)));

//        vehiclesSoldPerDay = new ArrayList[2];
//        staffEarningsPerDay = new ArrayList[2];
//        fncdEarningsPerDay = new ArrayList[2];
//        for(int i = 0; i < 2; i++)
////        {
////            vehiclesSoldPerDay[i] = new ArrayList<Integer>();
////            staffEarningsPerDay[i] = new ArrayList<Double>();
////            fncdEarningsPerDay[i] = new ArrayList<Double>();
//        }

        //run();//start simulation
    }
    public static String getDayOfTheWeek()
    {
        return days[day %7];
    }

    //Runs the simulation for the passed number of days
    public void run(int runDays) {

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

//    private void sellingInteractive(){
//        //Location of Commands and Invoker
//
//        System.out.println("Welcome to the FNCD!");
//        System.out.println("What can I help you with?");
//        System.out.println("(Enter the number of the command)");
//        System.out.println();
//        printInteractiveMenu();
//
//        CustomerInvoker invoker = new CustomerInvoker();
//
//        Scanner scanner = new Scanner(System.in);
//        String userInput = scanner.nextLine();
//
//        FNCD currentFNCD = this.dealerships.get(0);
//        Staff currentSalesperson = currentFNCD.getSalespeople().get(0);
//
//        while(true){
//
//            if(userInput.equals("1")){
//                //Switch FND
//
//                System.out.println("Which FNCD would you like to switch too?");
//                for(int i = 0; i < this.numFNCD; i++){
//                    System.out.println((i+1) + ". " + this.dealerships.get(i).getName());
//                }
//
//                userInput = scanner.nextLine();
//                currentFNCD = this.dealerships.get(Integer.parseInt(userInput) - 1);
//                currentSalesperson = currentFNCD.getSalespeople().get(0);
//
//                System.out.println("Switched to " + currentFNCD.getName());
//
//            }else if(userInput.equals("2")){
//                //Get salesperson name
//
//                invoker.setCommand(new AskSalespersonName(currentSalesperson));
//                invoker.execute();
//            }else if(userInput.equals("3")){
//                //Get time
//
//                invoker.setCommand(new AskTime());
//                invoker.execute();
//            }else if(userInput.equals("4")){
//                //Switch Salesperson
//
//                System.out.println("Which Salesperson would you like to switch too?");
//
//                ArrayList<Staff> salespeople = currentFNCD.getSalespeople();
//                int numSalespeople = salespeople.size();
//
//                for(int i = 0; i < numSalespeople; i++){
//                    System.out.println((i+1)+ ". " + salespeople.get(i).getName());
//                }
//
//                userInput = scanner.nextLine();
//                currentSalesperson = currentFNCD.getSalespeople().get(Integer.parseInt(userInput) -1);
//
//                System.out.println("Switched to " + currentSalesperson.getName());
//
//            }else if(userInput.equals("5")){
//                //Get Inventory
//
//                invoker.setCommand(new AskForInventory(currentFNCD));
//                invoker.execute();
//            }else if(userInput.equals("6")){
//                //Get car details
//
//                System.out.println("Which Car would you like details about?");
//                System.out.println();
//
//                invoker.setCommand(new AskForInventory(currentFNCD));
//                invoker.execute();
//
//                userInput = scanner.nextLine();
//                invoker.setCommand(new AskVehicleDetails(currentFNCD.getInventory().get(Integer.parseInt(userInput) - 1)));
//                invoker.execute();
//
//            }else if(userInput.equals("7")){
//                //Buy car
//                // If a vehicle is chosen, give an option to buy or not buy
//
//                System.out.println("What Car would you like to buy?");
//                System.out.println();
//
//                invoker.setCommand(new AskForInventory(currentFNCD));
//                invoker.execute();
//
//                userInput = scanner.nextLine();
//                int carChoice = Integer.parseInt(userInput) - 1;
//
//                System.out.println("Are you sure you want to buy this car?");
//                System.out.println("(y/n)");
//                System.out.println();
//
//                userInput = scanner.nextLine();
//
//                if(userInput.equals("y")){
//                    //Which addons would the user purchase
//
//                    System.out.println("Which Add-Ons would you like?");
//                    int[] addons = {0,0,0,0};
//
//                    while(true) {
//
//                        System.out.println("1. Extended Warranty " + addons[0]);
//                        System.out.println("2. Satellite Radio " + addons[1]);
//                        System.out.println("3. Undercoating " + addons[2]);
//                        System.out.println("4. Road Rescue Coverage " + addons[3]);
//                        System.out.println("5. Purchase");
//
//                        userInput = scanner.nextLine();
//
//                        if (userInput.equals("1")) {
//                            addons[0] = 1 - addons[0];
//                        } else if (userInput.equals("2")) {
//                            addons[1] = 1 - addons[1];
//                        } else if (userInput.equals("3")) {
//                            addons[2] = 1 - addons[2];
//                        } else if (userInput.equals("4")) {
//                            addons[3] = 1 - addons[3];
//                        } else if (userInput.equals("5")) {
//                            break;
//                        }
//                    }
//
//                    invoker.setCommand(new SellVehicleCommand(currentFNCD, currentSalesperson, currentFNCD.getInventory().get(carChoice), addons));
//                    invoker.execute();
//                }
//
//            }else if(userInput.equals("8")){
//                break;
//            }else{
//                System.out.println("Invalid Input");
//            }
//
//            printInteractiveMenu();
//            userInput = scanner.nextLine();
//        }
//
//    }
//
//    private void printInteractiveMenu(){
//        System.out.println();
//        System.out.println("1. Switch FNCD");
//        System.out.println("2. Ask Salesperson's name");
//        System.out.println("3. Ask Time");
//        System.out.println("4. Switch Salesperson");
//        System.out.println("5. Ask For Inventory");
//        System.out.println("6. Ask For Vehicle Details");
//        System.out.println("7. Buy Vehicle");
//        System.out.println("8. Quit");
//        System.out.println();
//    }
}
