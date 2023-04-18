import enums.Enums;
import factory.GoodsFactory;
import goods.Bag;
import goods.Balls;
import goods.Clothing;
import staff.ServicePerson;
import staff.Staff;

public class Main {
    public static void main(String[] args) {
//        Simulation run = new Simulation();
        GoodsFactory goodCreate = new GoodsFactory();
        Clothing balls = (Clothing) goodCreate.getInstanceItem(Enums.Goods.Clothing);
        System.out.println("Store purchased " + balls.getBrand() + " " + balls.getModel() + " for a price of " + balls.getPrice());
    }
}