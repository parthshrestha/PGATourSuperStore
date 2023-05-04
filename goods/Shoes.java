package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Shoes extends Item{

    String[] shoeBrands ={"Nike", "Adidas", "FootJoy", "G/Fore","ECCO"};
    double[] sizes = {4,4.5,5,5.5,6,6.5,7,7.5,8,8.5,9,9.5,10,10.5,11,11.5,12,13};
    protected Double size;
    ArrayList<String>[] shoeModels = new ArrayList[shoeBrands.length];
    public Shoes ()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(shoeBrands.length);
        brand = shoeBrands[choiceBrand];
        int choiceModels = rand.nextInt(shoeModels[choiceBrand].size());
        model = shoeModels[choiceBrand].get(choiceModels);
        type = Enums.Goods.Shoes;
        size = sizes[rand.nextInt(sizes.length)];
        initialPrice = generatePrice(90, 150);
        price = initialPrice;
    }
    public double getSize()
    {
        return size;
    }

    @Override
    public void generateModels() {
        for(int i = 0; i < shoeBrands.length; i++)
        {
            shoeModels[i] = new ArrayList<String>();
        }
        shoeModels[0].add("Retro 1");
        shoeModels[0].add("Air Max 90");

        shoeModels[1].add("Ultraboost");
        shoeModels[1].add("Tour Response");
        shoeModels[1].add("Code Chaoss");


        shoeModels[2].add("Hyerflex");
        shoeModels[2].add("Traditions");
        shoeModels[2].add("Pro|SL");
        shoeModels[2].add("Tour Alpha");

        shoeModels[3].add("MG4X2");
        shoeModels[3].add("MG4+");
        shoeModels[3].add("Cap Toe Gallivanter");

        shoeModels[4].add("Biom C4");
        shoeModels[4].add("S-Three");
        shoeModels[4].add("Golf Core");

    }
}
