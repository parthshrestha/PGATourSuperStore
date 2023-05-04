package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Accessory extends Item{
    String[] accBrands ={"GOLF GIFTS & GALLERY","PINS&ACES","Zero Friction"};

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
        initialPrice = generatePrice(20, 50);
        price = initialPrice;
    }

    @Override
    public void generateModels() {
        for(int i = 0; i < accBrands.length; i++)
        {
            accModels[i] = new ArrayList<String>();
        }
        accModels[0].add("Pride Golf ProLength 3-1/4 Tees 135-Pack");
        accModels[0].add("Professional Tee System 3-1/4 Golf Tees 50-Pack");
        accModels[0].add("Pro-Stix Alignment System");
        accModels[0].add("Premium Dual Brush w/ Cord");

        accModels[1].add("Putter Cover");
        accModels[1].add("Driver Headcover");
        accModels[1].add("Ball Marker");

        accModels[2].add("Tour Variety Tees 50-Pack");
        accModels[2].add("Golf Glove Wallet");
    }
}
