import enums.Enums;
import factory.GoodsFactory;
import factory.StaffFactory;
import goods.*;
import staff.*;
import strat.ReGripStrat;

import java.util.ArrayList;

public class PGATourSuperstore {
    ArrayList<Staff>[] staff;
    ArrayList<Item> goods;
    private GoodsFactory goodCreate;
    private StaffFactory staffCreate;
    private double budget;

    // Counter for items
    private int bagCounter = 0;
    private int ballCounter = 0;
    private int clothingCounter = 0;
    private int clubCounter = 0;
    private int gloveCounter = 0;
    private int shoeCounter = 0;
    private boolean initialized = false;

    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";//learned on geeks for geeks


    public PGATourSuperstore(){
        staff = new ArrayList[5];
        goods = new ArrayList<>();
        goodCreate = new GoodsFactory();
        staffCreate = new StaffFactory();
    }

    public void income(double money)
    {
        budget += money;
//        notifySubscriber(name,"budgetAdd", String.valueOf(money));
    }

    public void regrip()
    {
        ReGripStrat strategy = new ReGripStrat() {
            @Override
            public void ReGrip(Club club) {

            }
        };
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

    public void fillInventory(){
        while(bagCounter < 100 || clothingCounter < 100 || ballCounter < 100 || clubCounter < 100 || gloveCounter < 100 || shoeCounter < 100){
            if(bagCounter < 100){
                Bag bag = (Bag) goodCreate.getInstanceItem(Enums.Goods.Bag);
                expense(bag.getPrice());
                System.out.println("Store purchased " + bag.getBrand() + " " + bag.getModel() + " for a price of " + bag.getPrice());
            }
            if(clothingCounter < 100){
                Clothing clothing = (Clothing) goodCreate.getInstanceItem(Enums.Goods.Clothing);
                System.out.println("Store purchased " + clothing.getBrand() + " " + clothing.getModel() + " for a price of " + clothing.getPrice());
            }
            if(ballCounter < 100){
                Balls balls = (Balls) goodCreate.getInstanceItem(Enums.Goods.Balls);
                System.out.println("Store purchased " + balls.getBrand() + " " + balls.getModel() + " for a price of " + balls.getPrice());
            }
            if(clubCounter < 100){
                Club club = (Club) goodCreate.getInstanceItem(Enums.Goods.Club);
                System.out.println("Store purchased " + club.getBrand() + " " + club.getModel() + " for a price of " + club.getPrice());
            }
            if(gloveCounter < 100){
                Glove gloves = (Glove) goodCreate.getInstanceItem(Enums.Goods.Glove);
                System.out.println("Store purchased " + gloves.getBrand() + " " + gloves.getModel() + " for a price of " + gloves.getPrice());
            }
            if(shoeCounter < 100){
                Shoes shoes = (Shoes) goodCreate.getInstanceItem(Enums.Goods.Shoes);
                System.out.println("Store purchased " + shoes.getBrand() + " " + shoes.getModel() + " for a price of " + shoes.getPrice());
            }
        }
    }

    public void fillStaff(int amount){
        while(staff[0].size() < amount){ // Hire Fitters
            Fitter temp = (Fitter) staffCreate.getInstanceStaff(Enums.StaffType.Fitter);
            staff[0].add(temp);
            System.out.println("Hired new fitter named " + temp.getName());
//            notifySubscriber(name,"log","Hired new fitter Named "+ temp.getName());
        }

        while(staff[1].size() < amount){ // Hire Logistics
            Logistics temp = (Logistics) staffCreate.getInstanceStaff(Enums.StaffType.Logistic);
            staff[1].add(temp);
            System.out.println("Hired new logisitic named " + temp.getName());
//            notifySubscriber(name,"log","Hired new logistic Named "+ temp.getName());
        }

        while(staff[2].size() < amount){ // Hire Management
            Management temp = (Management) staffCreate.getInstanceStaff(Enums.StaffType.Management);
            staff[2].add(temp);
            System.out.println("Hired new management named " + temp.getName());
//            notifySubscriber(name,"log","Hired new logistic Named "+ temp.getName());
        }

        while(staff[3].size() < amount){ // Hire Service Person
            ServicePerson temp = (ServicePerson) staffCreate.getInstanceStaff(Enums.StaffType.ServicePerson);
            staff[3].add(temp);
            System.out.println("Hired new Service Person named " + temp.getName());
//            notifySubscriber(name,"log","Hired new logistic Named "+ temp.getName());
        }

        while(staff[4].size() < amount){ // Hire Soft Good
            SoftGood temp = (SoftGood) staffCreate.getInstanceStaff(Enums.StaffType.SoftGood);
            staff[4].add(temp);
            System.out.println("Hired new Soft Good named " + temp.getName());
//            notifySubscriber(name,"log","Hired new logistic Named "+ temp.getName());
        }
    }
    public void opening()
    {
        // Using system.out.println for making printed out logs, then we can use observers to create logs that can be saved (if it's a feature)
        System.out.println("Opening Store");
//        notifySubscriber(name,"log","Now Opening.....");
        fillInventory();
        fillStaff(5);
        // Make sure that there at least 10 goods of each type
        // 4 for every staff
        // Hire people in opening
    }

    public void Service()
    {
        // Parth
        // Fixing
    }
    public void pickupEcom()
    {
        // Make this as a to-do (future work)
    }
    public void Selling()
    {
        // Aiden
        // Soft goods people
    }
    public void fitting()
    {
        // Later
        // use fitters
    }
    public void ending()
    {
        // Later
        // make it similar to FNCD
        // fire people
        //
    }


}
