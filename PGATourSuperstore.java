import enums.Enums;
import factory.GoodsFactory;
import factory.StaffFactory;
import goods.*;
import staff.SoftGood;
import staff.Staff;
import strat.ReGripStrat;

import java.util.ArrayList;

public class PGATourSuperstore {
    ArrayList<Staff> staff;
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
        staff = new ArrayList<>();
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
        while(bagCounter < 10 || clothingCounter < 10 || ballCounter < 10 || clubCounter < 10 || gloveCounter < 10 || shoeCounter < 10){
            if(bagCounter < 10){
                Bag bag = (Bag) goodCreate.getInstanceItem(Enums.Goods.Bag);
                expense(bag.getPrice());
                System.out.println("Store purchased " + bag.getBrand() + " " + bag.getModel() + " for a price of " + bag.getPrice());
            }
            if(clothingCounter < 10){
                Clothing clothing = (Clothing) goodCreate.getInstanceItem(Enums.Goods.Clothing);
                System.out.println("Store purchased " + clothing.getBrand() + " " + clothing.getModel() + " for a price of " + clothing.getPrice());
            }
            if(ballCounter < 10){
                Balls balls = (Balls) goodCreate.getInstanceItem(Enums.Goods.Balls);
                System.out.println("Store purchased " + balls.getBrand() + " " + balls.getModel() + " for a price of " + balls.getPrice());
            }
            if(clubCounter < 10){
                Club club = (Club) goodCreate.getInstanceItem(Enums.Goods.Club);
                System.out.println("Store purchased " + club.getBrand() + " " + club.getModel() + " for a price of " + club.getPrice());
            }
            if(gloveCounter < 10){
                Glove gloves = (Glove) goodCreate.getInstanceItem(Enums.Goods.Glove);
                System.out.println("Store purchased " + gloves.getBrand() + " " + gloves.getModel() + " for a price of " + gloves.getPrice());
            }
            if(shoeCounter < 10){
                Shoes shoes = (Shoes) goodCreate.getInstanceItem(Enums.Goods.Shoes);
                System.out.println("Store purchased " + shoes.getBrand() + " " + shoes.getModel() + " for a price of " + shoes.getPrice());
            }
        }
    }
    public void opening()
    {
        // Using system.out.println for making printed out logs, then we can use observers to create logs that can be saved (if it's a feature)
        System.out.println("Opening Store");
        fillInventory();
        // Make sure that there at least 10 goods of each type
    }

    public void Service()
    {

    }
    public void pickupEcom()
    {}
    public void Selling()
    {

    }
    public void fitting()
    {}
    public void ending()
    {}


}
