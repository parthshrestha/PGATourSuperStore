package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Accessory extends Item{
    String[] accBrands ={"FootJoy","Nike","Callaway","Titelist","Under Armour","Bionic","TaylorMade","PGA Tour","Zero Friction"};

    ArrayList<String>[] accModels = new ArrayList[accBrands.length];
    public Accessory()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(accBrands.length);
        int choiceModels = rand.nextInt(accModels[choiceBrand].size());
        brand = accBrands[choiceBrand];
        model = accModels[choiceBrand].get(choiceModels);
        type = Enums.Goods.Accessory;
        generatePrice();
        price = initialPrice;
    }
    @Override
    public void generatePrice() {
        initialPrice = ((Math.random() * (50 - 20)) + 20);
    }

    @Override
    public void generateModels() {
        accModels[0].add("ComfortSof");
        accModels[1].add("StaSof");
        accModels[2].add("PureTouch");
    }
}
