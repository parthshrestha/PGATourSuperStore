package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Grip extends Item{
    String[] gripBrands ={"Lamkin","Nike","Callaway","Titelist"};
    String[] sizes = {"Standard","Mid-Size","Putter"};
    protected String size;
    ArrayList<String>[] gripModels = new ArrayList[gripBrands.length];
    public Grip()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(gripBrands.length);
        int choiceModels = rand.nextInt(gripModels[choiceBrand].size());
        brand = gripBrands[choiceBrand];
        model = gripModels[choiceBrand].get(choiceModels);
        type = Enums.Goods.Glove;
        size = sizes[rand.nextInt(sizes.length)];
        generatePrice();
        price = initialPrice;
    }
    public Grip(String _size)
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(gripBrands.length);
        int choiceModels = rand.nextInt(gripModels[choiceBrand].size());
        brand = gripBrands[choiceBrand];
        model = gripModels[choiceBrand].get(choiceModels);
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
        gripModels[0].add("grip1");
    }
}
