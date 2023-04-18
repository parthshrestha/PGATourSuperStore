package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Bag extends Item{
    String[] bagBrands ={"FootJoy","Nike","Callaway","Titelist","Under Armour","Bionic","TaylorMade","PGA Tour","Zero Friction"};
    ArrayList<String> bagModels = new ArrayList<>();
    public Bag()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(bagBrands.length);
        int choiceModels = rand.nextInt(bagModels.size());
        brand = bagBrands[choiceBrand];
        model = bagModels.get(choiceModels);
        type = Enums.Goods.Bag;
        generatePrice();
        price = initialPrice;
    }
    @Override
    public void generatePrice() {
        initialPrice = ((Math.random() * (50 - 20)) + 20);
    }

    @Override
    public void generateModels() {
        bagModels.add("ComfortSof");
        bagModels.add("StaSof");
        bagModels.add("PureTouch");
        bagModels.add("RainGrip");
    }
}
