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
//    enum clubHead;
    ArrayList<String>[] shaftModels = new ArrayList[shaftBrands.length];
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
        length =  new Double(((Math.random() * (39 - 32)) + 32)).intValue();
        generatePrice();
        price = initialPrice;
    }
//    public void generateShaft()
//    {
//        if(clubHead)
//        {
//
//        }
//    }
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
