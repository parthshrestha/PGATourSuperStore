package goods;

import enums.Enums;

import java.lang.reflect.Array;
import java.util.*;

public class Balls extends Item{
    protected boolean yellow;
    HashMap<String, ArrayList<String>> brandModel = new HashMap<>();
    String[] golfBallBrands ={"Callaway,Titelist,Srixon,TaylorMade"};
    ArrayList<String>[] golfBallModels = new ArrayList[golfBallBrands.length];
    public Balls()
    {
        super();
        generateModels();
        Random rand = new Random();
        ArrayList<String> keysAsArray = new ArrayList<>(brandModel.keySet());
        int choiceBrand = rand.nextInt(keysAsArray.size());
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
//        for(int i = 0; i < golfBallBrands.length; i++) { // initializes the array lists for each car make
//            golfBallModels[i] = new ArrayList<String>();
//        }
//
//        //callaway
//        golfBallModels[0].add("SuperSoft");
//        golfBallModels[0].add("ChromeSoft");
//
//        //Titelist
//        golfBallModels[1].add("V");
//        golfBallModels[1].add("V1");
//        golfBallModels[1].add("V1x");
//
//        //Srixon
//        golfBallModels[2].add("NeonRed");
//        golfBallModels[2].add("NeonOrange");
//        golfBallModels[2].add("NeonGreen");
//
//        //taylor made
//        golfBallModels[3].add("tpx5");

        // Create Brand Names
        brandModel.put("Calloway", new ArrayList<>(Arrays.asList("SuperSoft", "ChromeSoft")));
        brandModel.put("Titelist", new ArrayList<>(Arrays.asList("V", "V1", "V1x")));
        brandModel.put("Srixon", new ArrayList<>(Arrays.asList("NeonRed", "NeonGreen", "NeonOrange")));
        brandModel.put("TaylorMade", new ArrayList<>(Arrays.asList("tpx5")));
    }
}
