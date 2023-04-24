//import Observer.Logger;
//import Observer.Tracker;
//
//public class Simulation {
//
////    ArrayList<FNCD> dealerships;
//
//    static int day = 0;
//    static String days[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
//    int numFNCD;
////    ArrayList <Integer>[] vehiclesSoldPerDay;// to account for multiple fncd
////    ArrayList <Double>[] staffEarningsPerDay;
////    ArrayList<Double>[] fncdEarningsPerDay;
//    public Simulation()
//    {
//        numFNCD = 0;
////        dealerships = new ArrayList<FNCD>();
//
//        //this.dealerships.add(new FNCD("FNCD"+String.valueOf(numFNCD)));
//        numFNCD++;
//        //this.dealerships.add(new FNCD("FNCD"+String.valueOf(numFNCD)));
//        numFNCD++;
////        vehiclesSoldPerDay = new ArrayList[2];
////        staffEarningsPerDay = new ArrayList[2];
////        fncdEarningsPerDay = new ArrayList[2];
//        for(int i = 0; i < 2; i++)
////        {
////            vehiclesSoldPerDay[i] = new ArrayList<Integer>();
////            staffEarningsPerDay[i] = new ArrayList<Double>();
////            fncdEarningsPerDay[i] = new ArrayList<Double>();
//        }
//
//        run();//start simulation
//    }
//    public static String getDayOfTheWeek()
//    {
//        return days[day %7];
//    }
//    public void run() {
//
//
//        Tracker track = Singleton.getInstanceTracker();
//        for(int i = 0; i < 1; i++) {
//            //dealerships.get(i).registerSubscriber(track);
//        }
//        while (day < 30) {
//            Logger log = Singleton.getInstanceLogger(day);
//            for(int i = 0; i < 1; i++)
//            {
//                dealerships.get(i).registerSubscriber(log);//adding the subcriber into the list
//            }
//
//            ArrayList<Buyer> buyers = new ArrayList<Buyer>();
//            ArrayList<Buyer> buyers1 = new ArrayList<Buyer>();
//            System.out.println("===== Day: " + (day + 1) + "," + getDayOfTheWeek() + "=====");
////                myDealership.opening();
////                myDealership1.opening();
//            for(int i = 0; i < 2; i++)//opening for all fncd
//            {
//                dealerships.get(i).opening();
//            }
//
////                myDealership.washing();
////                myDealership1.washing();
//            for(int i = 0; i < 2; i++)//washing for all fncd
//            {
//                dealerships.get(i).washing();
//            }
//
//
////                myDealership.repairing();
////                myDealership1.repairing();
//            for(int i = 0; i < 2; i++)//repairing for all fncd
//            {
//                dealerships.get(i).repairing();
//            }
//
//            if (getDayOfTheWeek() == "Wednesday" || getDayOfTheWeek() == "Sunday") {
//
////                    myDealership.race();
////                    myDealership1.race();
//                for(int i = 0; i < 2; i++)//opening for all fncd
//                {
//                    dealerships.get(i).race();
//                }
//            }
//            Random rand = new Random();
//            int numBuyers;
//            int numBuyers1;
//            if (getDayOfTheWeek() == "Friday" || getDayOfTheWeek() == "Saturday") {
//                numBuyers = rand.nextInt(6) + 2; //min 2 max 8 from a generation of 0-6
//                numBuyers1 = rand.nextInt(6) + 2; //min 2 max 8 from a generation of 0-6
//            } else//working days
//            {
//                numBuyers = rand.nextInt(5); // generation of 0-5
//                numBuyers1 = rand.nextInt(5); // generation of 0-5
//            }
//            System.out.println("There are " + numBuyers + " buyers at" +dealerships.get(0).name + "today");
//            System.out.println("There are " + numBuyers1 + " buyers at" + dealerships.get(1).name + "today");
//            for (int i = 0; i < numBuyers; i++)//initialize number of buyers based on day
//            {
//                buyers.add(new Buyer());
//            }
//            for (int i = 0; i < numBuyers1; i++)//initialize number of buyers based on day
//            {
//                buyers1.add(new Buyer());
//            }
//            for (int i = 0; i < buyers.size(); i++)//takes every buyer to selling faze
//            {
//
//                System.out.println("===========" +dealerships.get(0).name + "===========");
//                System.out.println("Buyer #" + (i + 1) + " wants to buy a vehicle");
//
//                Buyer currBuyer = buyers.get(i);
//                System.out.println("Mood:" + currBuyer.getIntention());
//                System.out.println("Prefered type " + currBuyer.getTypeOfVehicle());
//                dealerships.get(0).selling(currBuyer);
//                System.out.println("===================== Sale Ended =====================");
//            }
//            for (int i = 0; i < buyers1.size(); i++)//takes every buyer to selling faze
//            {
//
//                System.out.println("===========" + dealerships.get(1).name + "===========");
//                System.out.println(dealerships.get(1).name + " Buyer #" + (i + 1) + " wants to buy a vehicle");
//
//                Buyer currBuyer = buyers1.get(i);
//                System.out.println("Mood:" + currBuyer.getIntention());
//                System.out.println("Prefered type " + currBuyer.getTypeOfVehicle());
//                dealerships.get(1).selling(currBuyer);
//                System.out.println("===================== Sale Ended =====================");
//            }
//
////                myDealership.ending();
////                myDealership1.ending();
//            for(int i = 0; i < 2; i++)//ending for all fncd
//            {
//
//                fncdEarningsPerDay[i].add(dealerships.get(i).ending());
//            }
//            for(int i = 0; i < 2; i++)//add data for graph
//            {
//                vehiclesSoldPerDay[i].add(dealerships.get(i).numVehiclesSold);
//                staffEarningsPerDay[i].add(track.getstaffEarnigs(dealerships.get(i).name));
//                System.out.println("net sales is: "+ dealerships.get(i).getNetSales());
//                //fncdEarningsPerDay[i].add(dealerships.get(i).getNetSales());
//            }
//
//            track.showReport();//should match and part of observer pattern tracking side
////                myDealership.unregisterSubscriber(log);//logger in observer pattern changes every day
////                myDealership1.unregisterSubscriber(log);//logger in observer pattern changes every day
//            for(int i = 0; i < 2; i++)//opening for all fncd
//            {
//                dealerships.get(i).unregisterSubscriber(log);
//            }
//            day++;
//        }
//        String fncdGraph1 = dealerships.get(0).name + "Graph.jpg";
//        FncdGraph graph1 = new FncdGraph(vehiclesSoldPerDay[0],staffEarningsPerDay[0],fncdEarningsPerDay[0],fncdGraph1);
//        String fncdGraph2 = dealerships.get(1).name + "Graph.jpg";
//        FncdGraph graph2 = new FncdGraph(vehiclesSoldPerDay[1],staffEarningsPerDay[1],fncdEarningsPerDay[1],fncdGraph2);
//        sellingInteractive();
//
////        myDealership.unregisterSubscriber(track);//tracking from observer pattern ends when simulation does
////        myDealership1.unregisterSubscriber(track);
//        for(int i = 0; i < 1; i++)//opening for all fncd
//        {
//            dealerships.get(i).unregisterSubscriber(track);
//        }
//    }
//
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
//}
