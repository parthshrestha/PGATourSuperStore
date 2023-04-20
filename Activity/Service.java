package Activity;

import Observer.Publisher;
import goods.Item;
import staff.Staff;

import java.util.ArrayList;

public class Service implements Publisher {
    protected double shaftRepair = 17.99;
    protected double reGrip = 2.99;
    protected String pgaStorenumber;
    public Service(String name)
    {
        pgaStorenumber = name;
    }
    public double service(ArrayList<Item> inventory, ArrayList<Staff>[] employees, double budget)
    {

      return budget;
    }

}
