package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Shaft extends Item{
    String[] shaftBrands ={"Mitsubishi","Fujikara"};
    int[] sizes = {33,34,35};
    String[] shaftFlex = {"Regular","Stiff","Extra Stiff"};
    String flex;
    protected int length;

    public Shaft()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(shaftBrands.length);
        int choiceModels = rand.nextInt(shaftModels[choiceBrand].size());
        int choiceFlex = rand.nextInt(shaftFlex.length);
        brand = shaftBrands[choiceBrand];
        model = shaftModels[choiceBrand].get(choiceModels);
        flex = shaftFlex[choiceFlex];
        type = Enums.Goods.Shaft;
        length =  new Double(((Math.random() * (46 - 36)) + 36)).intValue();
        generatePrice();
        price = initialPrice;
    }
    public Shaft(String _flex, int lengthInInches)//specific length
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(shaftBrands.length);
        brand = shaftBrands[choiceBrand];
        int choiceModels = rand.nextInt(shaftModels.get(brand).size());
        int choiceFlex = rand.nextInt(shaftFlex.length);

        model = shaftModels.get(brand).get(choiceModels);
        flex = _flex;
        type = Enums.Goods.Shaft;
        length =  lengthInInches;
        generatePrice();
        price = initialPrice;
    }

    @Override
    public void generatePrice() {
        initialPrice = ((Math.random() * (50 - 20)) + 20);

    }

    @Override
    public void generateModels() {
        shaftModels[0].add("ventus");
        shaftModels[1].add("blue");
        shaftModels[1].add("hazurdus");
    }
}
