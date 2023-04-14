package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Glove extends Item{
    String[] gloveBrands ={"FootJoy","Nike","Callaway","Titelist","Under Armour","Bionic","TaylorMade","PGA Tour","Zero Friction"};
    String[] sizes = {"S","M","L","XL","Cadet S","Cadet M","Cadet L","Cadet XL"};
    protected String size;
    ArrayList<String>[] gloveModels = new ArrayList[gloveBrands.length];
    public Glove()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(gloveBrands.length);
        int choiceModels = rand.nextInt(gloveModels[choiceBrand].size());
        brand = gloveBrands[choiceBrand];
        model = gloveModels[choiceBrand].get(choiceModels);
        type = Enums.Goods.Glove;
        size = sizes[rand.nextInt(sizes.length)];
        generatePrice();
        price = initialPrice;
    }
    public Glove(String _size)
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(gloveBrands.length);
        int choiceModels = rand.nextInt(gloveModels[choiceBrand].size());
        brand = gloveBrands[choiceBrand];
        model = gloveModels[choiceBrand].get(choiceModels);
        type = Enums.Goods.Glove;
        size = _size;
        generatePrice();
        price = initialPrice;
    }

    @Override
    public void generatePrice() {
        initialPrice = ((Math.random() * (50 - 20)) + 20);
    }

    @Override
    public void generateModels() {
        //footjoy
        gloveModels[0].add("ComfortSof");
        gloveModels[0].add("StaSof");
        gloveModels[0].add("PureTouch");
        gloveModels[0].add("RainGrip");
        gloveModels[0].add("WeatherSof");
        gloveModels[0].add("ProDry");
        //Nike
        gloveModels[1].add("Tour Flex");
        gloveModels[1].add("Tour Classic");
        gloveModels[1].add("Sport");
        gloveModels[1].add("Dura Feel");
        //Callaway
        gloveModels[2].add("Dawn Patrol");
        gloveModels[2].add("Weather Spann");
        gloveModels[2].add("X-Tech");
        gloveModels[2].add("Fusion Pro");
        //Titelist
        gloveModels[3].add("Players");
        gloveModels[3].add("Perma Soft");
        gloveModels[3].add("Players Flex");
        gloveModels[3].add("Velocity");
        //Under Armour
        gloveModels[3].add("UA Playoff");
        gloveModels[3].add("Strikeskin Tour");
        gloveModels[3].add("UA Radar");
        gloveModels[3].add("UA Iso-Chill");
        //Bionic
        gloveModels[4].add("StableGrip");
        gloveModels[4].add("RelaxGrip");
        gloveModels[4].add("PerformanceGrip");
        gloveModels[4].add("AquaGrip");
        //TaylorMade
        gloveModels[4].add("Stratus Tech");
        gloveModels[4].add("Tour Preferred");
        gloveModels[4].add("TP Flex");
        gloveModels[4].add("Targa");
        //PGA tour
        gloveModels[5].add("Pro Series");
        //Zero Friction
        gloveModels[6].add("Motion Fit");
        gloveModels[6].add("Compression Fit");
        gloveModels[6].add("Cabretta Elite");
    }


}
