package Activity;

import buyer.Customer;
import goods.Item;
import staff.Staff;

import javax.sound.midi.SysexMessage;
import java.util.Random;

import java.util.ArrayList;

public class Selling {

    protected String pgaStorenumber;

    public Selling(String pgaStorenumber) { this.pgaStorenumber = pgaStorenumber; }

    //Returns the total of the sale
    public double selling(Customer customer, ArrayList<Staff>[] employees, ArrayList<Item> inventory, ArrayList<Item> soldInventory, double staffEarnigns) {

        //Select random employee to help customer
        Random rand = new Random();
        int staffIndex = rand.nextInt(employees[3].size());
        Staff salesman = employees[3].get(staffIndex);

        //Fill customers cart
        customer.fillCartRandom(inventory);

        //Get the customers cart
        ArrayList<goods.Item> cart = customer.getCart();

        //If there are no items in the cart return -1
        if(cart == null){ return -1; }

        int cartSize = cart.size();
        double total = 0;

        //Get each item and add its price to total
        //Remove it from inventory and add to sold inventory
        //Log it
        for(int i = 0; i < cartSize; i++)
        {
            Item item = cart.get(i);
            total += item.getPrice();

            soldInventory.add(item);
            inventory.remove(item);

            System.out.println("Customer  " + customer.name + " purchased " + item.getModel() + " for " + item.getPrice());
        }

        //Give staff bonus and log it
        double staffBonus = total / 10;
        System.out.println(salesman.getName() + "received a bonus of: " + staffBonus);

        return total;
    }
}
