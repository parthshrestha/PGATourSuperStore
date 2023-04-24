package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Grip extends Item{
    String[] gripBrands ={"Lamkin","SuperStroke","GolfPride","Sweet Rollz","Winn"};
    String[] sizes = {"Standard","Mid-Size","Jumbo","Putter"};
    protected String size;
    //ArrayList<String>[] gripModels = new ArrayList[gripBrands.length];
    HashMap<String, ArrayList<String>> gripModels = new HashMap<>();
    public Grip()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(gripBrands.length);
        brand = gripBrands[choiceBrand];

        int choiceModels = rand.nextInt(gripModels.get(brand).size());

        model = gripModels.get(brand).get(choiceModels);
        type = Enums.Goods.Grip;
        size = sizes[rand.nextInt(sizes.length)];
        generatePrice();
        price = initialPrice;
    }
    public Grip(String _size)//parameterized for a specific size
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(gripBrands.length);
        brand = gripBrands[choiceBrand];

        int choiceModels = rand.nextInt(gripModels.get(brand).size());

        model = gripModels.get(brand).get(choiceModels);
        type = Enums.Goods.Grip;
        size = _size;
        generatePrice();
        price = initialPrice;
    }
    @Override
    public void generatePrice() {
        initialPrice = ((Math.random() * (30 - 11)) + 11);
    }

    @Override
    public void generateModels() {
        //{"Lamkin","SuperStroke","GolfPride","Sweet Rollz","Winn"}
        gripModels.put(gripBrands[0], new ArrayList<>(Arrays.asList("Crossline Full-Cord Grip","Crossline Swing Grip","Crossline 360 Standard Swing Grip","Sonar+ Swing Grip","SINKFit Pistol Rubber")));
        gripModels.put(gripBrands[1], new ArrayList<>(Arrays.asList("Traxion Tour","Zenergy Tour","Cross Comfort Traxion","S-Tech Cord")));
        gripModels.put(gripBrands[2], new ArrayList<>(Arrays.asList("MCC Plus 4","Z-Grip Cord","CP2 Wrap","MCC Align","Tour Velvet")));
        gripModels.put(gripBrands[3], new ArrayList<>(Arrays.asList("C-Note","South Beach","Scottie","Meurtos","La Piña","O'Malley")));
        gripModels.put(gripBrands[4], new ArrayList<>(Arrays.asList("Dri-Tac","Excel WinnProX","Dri-Tac 2.0","Dri-Tac LT","NTP","Dri-Tac Lite")));


    }
}
