package Activity;

import buyer.Customer;
import goods.Item;
import staff.Staff;
import java.util.Random;

import java.util.ArrayList;

public class Selling {

    //Returns the total of the sale
    public double selling(Customer customer, ArrayList<Staff>[] employees, ArrayList<Item> inventory, ArrayList<Item> soldInventory, double netSales, double staffEarnigns) {

        Random rand = new Random();
        int staffIndex = rand.nextInt(employees[3].size());
        Staff salesman = employees[3].get(staffIndex);

        ArrayList<goods.Item> cart = customer.getCart();
        int cartSize = cart.size();

        double total = 0;

        for(int i = 0; i < cartSize; i++)
        {
            Item item = cart.get(i);
            total += item.getPrice();

            soldInventory.add(item);
            inventory.remove(item);

            System.out.println("Sold" + item.getModel() + " for " + item.getPrice());
        }

        double staffBonus = total / 10;

        System.out.println(salesman.getName() + "received a bonus of: " + staffBonus);

        return total;
    }
}
