package Activity;

import Observer.Publisher;
import buyer.Customer;
import decorator.ClubCover;
import decorator.Warranty;
import enums.Enums;
import goods.Item;
import staff.SoftGood;
import staff.Staff;

import java.util.ArrayList;
import java.util.Random;

public class Selling implements Publisher {
    protected String pgaStorenumber;

    public Selling(String store)
    {
        pgaStorenumber = store;
    }
    public double sellAddons(Item i)//helper function to instantiate decorator pattern
    {
        Item temp = i;
        Random random = new Random();
        double soldAddon = 0;
        double chanceWarranty = random.nextDouble();
        double chanceHeadcover = random.nextDouble();

        if(chanceWarranty <= 0.3)// 25% chance  buyer buys it
        {
            temp = new Warranty(i);
            i.setPrice(temp.getPrice());
            soldAddon += i.getInitialPrice() *0.3;//price of add-on
            i.setModel(temp.getModel());
            System.out.print(i.getBrand()+" "+i.getModel()+" "+i.getType());

            System.out.println( "CLub after Extended warranty costs:" + i.getPrice());
            notifySubscriber("log",i.getBrand()+" "+i.getModel()+" "+i.getType());
            notifySubscriber("log","CLub after Extended warranty costs:" + i.getPrice());

        }
        if(chanceHeadcover<=0.4)//40% chance
        {
            temp = new ClubCover(i);
            i.setPrice(temp.getPrice());
            soldAddon += i.getInitialPrice() *0.3;//price of add-on
            i.setModel(temp.getModel());
            System.out.print(i.getBrand()+" "+i.getModel()+" "+i.getType());

            System.out.println( "CLub after Extra Head Cover costs:" + i.getPrice());
            notifySubscriber("log",i.getBrand()+" "+i.getModel()+" "+i.getType());
            notifySubscriber("log","CLub after Extra Head Cover costs:" + i.getPrice());

        }


        return soldAddon;
    }
    public double selling(Customer customer, ArrayList<Staff>[] employees, ArrayList<Item> inventory, ArrayList<Item> soldInventory, double netSales, double staffEarnigns, ArrayList pastCustomers)
    {
        System.out.println("Starting selling...");
        Random rand = new Random();
        int numItemsOffered= rand.nextInt(20);
        int randEmployee = rand.nextInt(employees[4].size());
        SoftGood currEmployee= (SoftGood) employees[4].get(randEmployee);
        for(int i = 0; i < numItemsOffered; i++)
        {
            Item currItem = inventory.get(rand.nextInt(inventory.size()));//gets random item from bound
            double chance = rand.nextDouble(); //number 0-1
            if(chance <=0.45)//buy product
            {
                customer.addToCart(currItem);
                System.out.println("Customer: "+ customer.name +" choose to buy "+ currItem.getBrand() + " "+currItem.getModel()+" :" + currItem.getPrice());
                notifySubscriber("log","Customer: "+ customer.name +" choose to buy "+ currItem.getBrand() + " "+currItem.getModel()+" :" + currItem.getPrice());
                netSales += currItem.getPrice();
//                System.out.println(currItem.getType());
//                System.out.println(currItem.getBrand());
                if(currItem.getType() == Enums.Goods.Club){
                    netSales -= sellAddons(currItem);
                }
                double bonus = currItem.getPrice() * 0.1;
                System.out.println("Associate: "+ currEmployee.getName()+ "recived bonus- "+ bonus);
                notifySubscriber("log","Associate: "+ currEmployee.getName()+ "recived bonus- "+ bonus);
                netSales-= bonus;
                currEmployee.setBonus(bonus);
                notifySubscriber("staff",String.valueOf(bonus));
                soldInventory.add(currItem);//add to sold inventory
                inventory.remove(currItem);//remove from inventory
                //recountinventory


            }
            else// not buy
            {
                System.out.println("Customer: "+ customer.name +" choose not to buy "+ currItem.getBrand() + " "+currItem.getModel()+" :" + currItem.getPrice());
                notifySubscriber("log","Customer: "+ customer.name +" choose not to buy "+ currItem.getBrand() + " "+currItem.getModel()+" :" + currItem.getPrice());
            }
            //show receipt
        }
        //notifySubscriber("budgetAdd", String.valueOf(netSales));
        System.out.println("done selling");
        pastCustomers.add(customer);
        return netSales;
    }
}
