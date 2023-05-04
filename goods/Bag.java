package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Bag extends Item{
    String[] bagBrands ={"Titlist","TaylorMade","Sun Mountain","Ping", "Callaway" };
    HashMap<String, ArrayList<String>> bagModels = new HashMap<>();
    public Bag()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(bagBrands.length);
        brand = bagBrands[choiceBrand];
        int choiceModels = rand.nextInt(bagModels.get(brand).size());

        model = bagModels.get(brand).get(choiceModels);
        type = Enums.Goods.Bag;
        initialPrice = generatePrice(20, 50);
        price = initialPrice;
    }

    @Override
    public void generateModels() {
        bagModels.put(bagBrands[0], new ArrayList<>(Arrays.asList("Hybrid 14","Players 4", "Cart 14","Players 5","StaDry ","Carbon S" )));
        bagModels.put(bagBrands[1], new ArrayList<>(Arrays.asList("FlexTech", "Supreme", "Cart Lite", "Tour Staff", "Vessel Lite")));
        bagModels.put(bagBrands[2], new ArrayList<>(Arrays.asList("C-130", "Stellar","4.5LS", "Diva", "Mavrick", "WeatherMax", "Eco-Lite", "H2NO")));
        bagModels.put(bagBrands[3], new ArrayList<>(Arrays.asList("Hoofer Lite", "DLX", "Pioneer", "Traverse","L8","Moonlite")));
        bagModels.put(bagBrands[4], new ArrayList<>(Arrays.asList("Fairway C", "ORG", "Chev", "HL Zero", "PAR3","Fairway 14")));
    }
}
