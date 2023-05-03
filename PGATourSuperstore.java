import Activity.Ecommerce;
import Activity.Fitting;
import Activity.Selling;
import Activity.Service;
import Observer.Publisher;
import buyer.Customer;
import enums.Enums;
import factory.GoodsFactory;
import factory.StaffFactory;
import goods.*;
import staff.*;
import buyer.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PGATourSuperstore implements Publisher {
    ArrayList<Staff>[] employees;//[fritter,logistics, management, Service person, Soft goods]
    ArrayList<Item> inventory;
    ArrayList<Item> soldInventory;
    protected String storeNum;
    //ArrayList<Customer> serviceOrders;
    private GoodsFactory goodCreate;
    private StaffFactory staffCreate;
    private double budget;
    private double netSales;
    double staffEarnings = 0.0;
    // Counter for items

    private int[] itemCounters;

    /**
    private int bagCounter = 0;
    private int ballCounter = 0;
    private int clothingCounter = 0;
    private int clubCounter = 0;
    private int gloveCounter = 0;
    private int shoeCounter = 0;
    private int accessoryCounter= 0;
    private int gripCounter = 0;
    private int racketCounter = 0;
    private int shaftCounter = 0;
     **/

    private boolean initialized;
    private int totalEmployees = 0;
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";//learned on geeks for geeks
    Service s;
    Ecommerce ecom;
    Fitting fit;
    Selling sell;


    public PGATourSuperstore(String num){
        storeNum = num;
        budget = 250000;
        netSales = 0;
        employees = new ArrayList[5];
        for(int i = 0; i < employees.length; i++)
        {
            employees[i] = new ArrayList<Staff>();
        }
        inventory = new ArrayList<>();
        soldInventory = new ArrayList<>();
        goodCreate = new GoodsFactory();
        staffCreate = new StaffFactory();
        hireEmployees();
        s = new Service(storeNum);
//      ecom = new Ecommerce(storeNum);
        fit = new Fitting(storeNum);
        sell = new Selling(storeNum);
        initialized = false;

        itemCounters = new int[Enums.Goods.values().length];
        Arrays.fill(itemCounters, 0);

        fillInventory();
        initialized = true;
    }

    public void income(double money)
    {
        budget += money;
//        notifySubscriber(name,"budgetAdd", String.valueOf(money));
    }
    public void expense(double money){
        budget -= money;
//        notifySubscriber(name,"budgetSub", String.valueOf(money));
        if (budget < 0)
        {
            income(250000.0);

            System.out.println(ANSI_RED +"!!!!!!!Store Borrowed $250000 from the bank!!!!!!!"+ANSI_RESET);
//            notifySubscriber(name,"log","!!!!!!!FNCD Borrowed $250000 from the bank!!!!!!!");
        }
        //notifySubscriber("budgetSub",);
    }

//    public void regrip()
//    {
//        ReGripStrat strategy = new ReGripStrat() {
//            @Override
//            public void ReGrip(Club club) {
//
//            }
//        };
//    }



    public void fillInventory(){//fix initialized and add inventory

        int difTypes = itemCounters.length;
        for(int i = 0; i < difTypes; i++)
        {
            while(itemCounters[i] < 10)
            {
                Item item = goodCreate.getInstanceItem(Enums.Goods.values()[i]);
                if(initialized)
                {
                    expense(item.getPrice());
                }

                inventory.add(item);
                itemCounters[i]++;
                System.out.println("Store purchased " + item.getBrand() + " " + item.getModel() + " for a price of " + item.getPrice());
            }
        }

        /**
        while(bagCounter < 10 ||
                clothingCounter < 10 ||
                ballCounter < 10 ||
                clubCounter < 10 ||
                gloveCounter < 10 ||
                shoeCounter < 10||
                accessoryCounter < 10||
                gripCounter < 10||
                racketCounter < 10||
                shaftCounter < 10)//while there is less than 100 of any of these items fill the inventory
        {
            if(bagCounter < 10){
                Bag bag = (Bag) goodCreate.getInstanceItem(Enums.Goods.Bag);
                if(initialized)
                {
                    expense(bag.getPrice());
                }

                inventory.add(bag);
                bagCounter++;
                System.out.println("Store purchased " + bag.getBrand() + " " + bag.getModel() + " for a price of " + bag.getPrice());
            }
            if(clothingCounter < 10){
                Clothing clothing = (Clothing) goodCreate.getInstanceItem(Enums.Goods.Clothing);
                if(initialized)
                {
                    expense(clothing.getPrice());
                }
                inventory.add(clothing);
                clothingCounter++;
                System.out.println("Store purchased " + clothing.getBrand() + " " + clothing.getModel() + " for a price of " + clothing.getPrice());
            }
            if(ballCounter < 10){
                Balls balls = (Balls) goodCreate.getInstanceItem(Enums.Goods.Balls);
                if(initialized)
                {
                    expense(balls.getPrice());
                }
                inventory.add(balls);
                ballCounter++;
                System.out.println("Store purchased " + balls.getBrand() + " " + balls.getModel() + " for a price of " + balls.getPrice());
            }
            if(clubCounter < 10){
                Club club = (Club) goodCreate.getInstanceItem(Enums.Goods.Club);
                if(initialized)
                {
                    expense(club.getPrice());
                }
                inventory.add(club);
                clubCounter++;
                System.out.println("Store purchased " + club.getBrand() + " " + club.getModel() + " for a price of " + club.getPrice());
            }
            if(gloveCounter < 10){
                Glove gloves = (Glove) goodCreate.getInstanceItem(Enums.Goods.Glove);
                if(initialized)
                {
                    expense(gloves.getPrice());
                }
                inventory.add(gloves);
                gloveCounter++;
                System.out.println("Store purchased " + gloves.getBrand() + " " + gloves.getModel() + " for a price of " + gloves.getPrice());
            }
            if(shoeCounter < 10){
                Shoes shoes = (Shoes) goodCreate.getInstanceItem(Enums.Goods.Shoes);
                if(initialized)
                {
                    expense(shoes.getPrice());
                }
                inventory.add(shoes);
                shoeCounter++;
                System.out.println("Store purchased " + shoes.getBrand() + " " + shoes.getModel() + " for a price of " + shoes.getPrice());
            }
            if(accessoryCounter < 10){
                Accessory acc = (Accessory) goodCreate.getInstanceItem(Enums.Goods.Accessory);
                if(initialized)
                {
                    expense(acc.getPrice());
                }
                inventory.add(acc);
                accessoryCounter++;
                System.out.println("Store purchased " + acc.getBrand() + " " + acc.getModel() + " for a price of " + acc.getPrice());
            }
            if(gripCounter < 10){
                Grip grip = (Grip) goodCreate.getInstanceItem(Enums.Goods.Grip);
                if(initialized)
                {
                    expense(grip.getPrice());
                }
                inventory.add(grip);
                gripCounter++;
                System.out.println("Store purchased " + grip.getBrand() + " " + grip.getModel() + " for a price of " + grip.getPrice());
            }
            if(racketCounter < 10){
                Racket tennisRacket = (Racket) goodCreate.getInstanceItem(Enums.Goods.Racket);
                if(initialized)
                {
                    expense(tennisRacket.getPrice());
                }
                inventory.add(tennisRacket);
                racketCounter++;
                System.out.println("Store purchased " + tennisRacket.getBrand() + " " + tennisRacket.getModel() + " for a price of " + tennisRacket.getPrice());
            }
            if(shaftCounter < 10){
                Shaft golfShaft = (Shaft) goodCreate.getInstanceItem(Enums.Goods.Shaft);
                if(initialized)
                {
                    expense(golfShaft.getPrice());
                }
                inventory.add(golfShaft);
                shaftCounter++;
                System.out.println("Store purchased " + golfShaft.getBrand() + " " + golfShaft.getModel() + " for a price of " + golfShaft.getPrice());
            }
        }
         **/
    }
    public void hireEmployees()
    {
        for(int i = 0; i < employees.length;i++)
        {
            //[fritter,logistics, management, Service person, Soft goods]
            while(i == 0 && (employees[i].size()<5))//fitters
            {
                Fitter temp = (Fitter) staffCreate.getInstanceStaff(Enums.StaffType.Fitter);
                employees[0].add(temp);
                System.out.println("Hired new fitter named " + temp.getName());
//            notifySubscriber(name,"log","Hired new fitter Named "+ temp.getName());
                totalEmployees++;
            }
            while(i == 1 && (employees[i].size()<5))//logistics
            {
                Logistics temp = (Logistics) staffCreate.getInstanceStaff(Enums.StaffType.Logistic);
                employees[i].add(temp);
                System.out.println("Hired new logisitic named " + temp.getName());
//            notifySubscriber(name,"log","Hired new logistic Named "+ temp.getName());
                totalEmployees++;
            }
            while(i == 2 && (employees[i].size()<5))//management
            {
                Management temp = (Management) staffCreate.getInstanceStaff(Enums.StaffType.Management);
                employees[i].add(temp);
                System.out.println("Hired new management named " + temp.getName());
//            notifySubscriber(name,"log","Hired new logistic Named "+ temp.getName());
                totalEmployees++;
            }
            while(i == 3 && (employees[i].size()<5))//service person
            {
                ServicePerson temp = (ServicePerson) staffCreate.getInstanceStaff(Enums.StaffType.ServicePerson);
                employees[i].add(temp);
                System.out.println("Hired new Service Person named " + temp.getName());
//            notifySubscriber(name,"log","Hired new logistic Named "+ temp.getName());
                totalEmployees++;
            }
            while(i == 4 && (employees[i].size()<5))//softgoods
            {
                SoftGood temp = (SoftGood) staffCreate.getInstanceStaff(Enums.StaffType.SoftGood);
                employees[i].add(temp);
                System.out.println("Hired new Soft Good named " + temp.getName());
//            notifySubscriber(name,"log","Hired new logistic Named "+ temp.getName());
                totalEmployees++;
            }

        }
    }
    public void opening()
    {
        // Using system.out.println for making printed out logs, then we can use observers to create logs that can be saved (if it's a feature)
        System.out.println("Opening Store");
//        notifySubscriber(name,"log","Now Opening.....");
        fillInventory();
        // Make sure that there at least 10 goods of each type
        // 4 for every staff
        // Hire people in opening
        if(totalEmployees < 20) // 4 per type in 5 types = 20 total employees in 1 store
        {
            hireEmployees();
        }

        System.out.println("Opening Sucessful");

    }

    public void Service(ArrayList<Customer> serviceOrders)
    {
        // Parth
        // Fixing
        double beforeService = budget;
        double afterService= s.service(serviceOrders, employees[3], beforeService);
        double expended = beforeService-afterService;
        expense(expended);
    }
    public void fitting(ArrayList<Customer> serviceOrders)
    {
        // Later
        // use fitters
        double beforeService = budget;
        double afterService = fit.clubFitting(serviceOrders,employees[0],beforeService);
        double expended = beforeService-afterService;
        expense(expended);
    }
    public void pickupEcom()
    {
        // Make this as a to-do (future work)
    }
    public void selling(Customer customer)
    {
        double total = sell.selling(customer, employees, inventory, itemCounters, soldInventory, staffEarnings);

        if(total == -1)
        {
            System.out.println("Customer " + customer.name + " has decided not to purchase anything.");
            return;
        }

        netSales += (total);
        income (total);//to account for only the most recent sale
    }

    public void ending()
    {
        // Later
        // make it similar to FNCD
        // fire people
        //
    }

    public ArrayList<Staff>[] getStaff() {return employees;}
    public ArrayList<Item> getInventory() {return inventory;}
    public ArrayList<Item> getSoldInventory() {return soldInventory;}

    public double getBudget(){return budget;}
}
