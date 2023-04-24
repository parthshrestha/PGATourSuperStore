package goods;

import enums.Enums;

import java.lang.reflect.Array;
import java.util.*;

public class Balls extends Item{
    protected boolean yellow;
    HashMap<String, ArrayList<String>> brandModel = new HashMap<>();
    String[] golfBallBrands ={"Callaway,Titelist,Srixon,TaylorMade"};
   // ArrayList<String>[] golfBallModels = new ArrayList[golfBallBrands.length];
    public Balls()
    {
        super();
        generateModels();
        Random rand = new Random();
        ArrayList<String> keysAsArray = new ArrayList<>(brandModel.keySet());
        int choiceBrand = rand.nextInt(brandModel.get(brand).size());
        brand = keysAsArray.get(choiceBrand);

        int choiceModels = rand.nextInt(brandModel.get(brand).size());
        model = brandModel.get(brand).get(choiceModels);

//        int choiceBrand = rand.nextInt(brandModel.keySet().size());
//        int choiceModels = rand.nextInt(golfBallModels[choiceBrand].size());
//        brand = golfBallBrands[choiceBrand-1];
//        model = golfBallModels[choiceBrand-1].get(choiceModels-1);
        type = Enums.Goods.Balls;
        generatePrice();
        price = initialPrice;
        if(choiceBrand != 2)
        {
            int chance = rand.nextInt(100);
            if(chance <= 50){yellow = true;}
            else{yellow=false;}
        }
    }

    @Override
    public void generatePrice() {
        initialPrice = ((Math.random() * (50 - 20)) + 20);
    }

    public void generateModels()
    {
//

        // Create Brand Names
        brandModel.put( golfBallBrands[0], new ArrayList<>(Arrays.asList("Chrome Soft","SuperSoft", "Waybird","CXR","ERC","Reva","Superfast")));
        brandModel.put(golfBallBrands[1], new ArrayList<>(Arrays.asList("Pro V1", "Pro V1x", "AVX", "TrueFeel","Velocity","Tour Soft","Tour Speed")));
        brandModel.put(golfBallBrands[2], new ArrayList<>(Arrays.asList("Z-Star", "Z-Star XV", "Q-Star", "Soft Feel")));
        brandModel.put(golfBallBrands[3], new ArrayList<>(Arrays.asList("Distance+","TP5", "Tour Response", "Soft Response","TP5x")));
    }
}
