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
        for(int i = 0; i < gloveBrands.length; i++)
        {
            gloveModels[i] = new ArrayList<String>();
        }
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
        gloveModels[4].add("UA Playoff");
        gloveModels[4].add("Strikeskin Tour");
        gloveModels[4].add("UA Radar");
        gloveModels[4].add("UA Iso-Chill");
        //Bionic
        gloveModels[5].add("StableGrip");
        gloveModels[5].add("RelaxGrip");
        gloveModels[5].add("PerformanceGrip");
        gloveModels[5].add("AquaGrip");
        //TaylorMade
        gloveModels[6].add("Stratus Tech");
        gloveModels[6].add("Tour Preferred");
        gloveModels[6].add("TP Flex");
        gloveModels[6].add("Targa");
        //PGA tour
        gloveModels[7].add("Pro Series");
        gloveModels[7].add("Pro Series");
        //Zero Friction
        gloveModels[8].add("Motion Fit");
        gloveModels[8].add("Compression Fit");
        gloveModels[8].add("Cabretta Elite");
    }


}
