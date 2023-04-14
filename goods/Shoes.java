package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Shoes extends Item{

    String[] shoeBrands ={"Bionic","TaylorMade","PGA Tour","Zero Friction"};
    double[] sizes = {4,4.5,5,5.5,6,6.5,7,7.5,8,8.5,9,9.5,10,10.5,11,11.5,12,13};
    protected Double size;
    ArrayList<String>[] shoeModels = new ArrayList[shoeBrands.length];
    public Shoes ()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(shoeBrands.length);
        int choiceModels = rand.nextInt(shoeModels[choiceBrand].size());
        brand = shoeBrands[choiceBrand];
        model = shoeModels[choiceBrand].get(choiceModels);
        type = Enums.Goods.Glove;
        size = sizes[rand.nextInt(sizes.length)];
        generatePrice();
        price = initialPrice;
    }
    @Override
    public void generatePrice() {
        initialPrice = ((Math.random() * (50 - 20)) + 20);

}

    @Override
    public void generateModels() {
        shoeModels[0].add("ComfortSof");
        shoeModels[1].add("StaSof");
        shoeModels[2].add("StaSof");

    }
}
