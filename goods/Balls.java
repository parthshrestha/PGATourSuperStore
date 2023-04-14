package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Balls extends Item{
    protected boolean yellow;
    String[] golfBallBrands ={"Callaway,Titelist,Srixon,TaylorMade"};
    ArrayList<String>[] golfBallModels = new ArrayList[golfBallBrands.length];
    public Balls()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(golfBallBrands.length);
        int choiceModels = rand.nextInt(golfBallModels[choiceBrand].size());
        brand = golfBallBrands[choiceBrand];
        model = golfBallModels[choiceBrand].get(choiceModels);
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
        for(int i = 0; i < golfBallBrands.length; i++) { // initializes the array lists for each car make
            golfBallModels[i] = new ArrayList<String>();
        }

        //callaway
        golfBallModels[0].add("SuperSoft");
        golfBallModels[0].add("ChromeSoft");

        //Titelist
        golfBallModels[1].add("V");
        golfBallModels[1].add("V1");
        golfBallModels[1].add("V1x");

        //Srixon
        golfBallModels[2].add("NeonRed");
        golfBallModels[2].add("NeonOrange");
        golfBallModels[2].add("NeonGreen");

        //taylor made
        golfBallModels[3].add("tpx5");

    }
}
