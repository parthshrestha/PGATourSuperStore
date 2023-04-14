package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Bag extends Item{
    String[] bagBrands ={"FootJoy","Nike","Callaway","Titelist","Under Armour","Bionic","TaylorMade","PGA Tour","Zero Friction"};
    ArrayList<String>[] bagModels = new ArrayList[bagBrands.length];
    public Bag()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(bagBrands.length);
        int choiceModels = rand.nextInt(bagModels[choiceBrand].size());
        brand = bagBrands[choiceBrand];
        model = bagModels[choiceBrand].get(choiceModels);
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
        bagModels[0].add("ComfortSof");
        bagModels[1].add("StaSof");
        bagModels[2].add("PureTouch");
        bagModels[3].add("RainGrip");
    }
}
