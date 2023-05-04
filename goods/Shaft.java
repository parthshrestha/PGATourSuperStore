package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Shaft extends Item{
    String[] shaftBrands ={"Mitsubishi","Fujikura","Project x", "KBS","True Temper"};
    HashMap<String, ArrayList<String>> shaftModels = new HashMap<>();
    String[] shaftFlex = {"Regular","Stiff","Extra Stiff"};
    String flex;
    protected int length;

    public Shaft()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(shaftBrands.length);
        brand = shaftBrands[choiceBrand];
        int choiceModels = rand.nextInt(shaftModels.get(brand).size());
        int choiceFlex = rand.nextInt(shaftFlex.length);

        model = shaftModels.get(brand).get(choiceModels);
        flex = shaftFlex[choiceFlex];
        type = Enums.Goods.Shaft;
        length =  (int)((Math.random() * (46 - 36)) + 36);
        initialPrice = generatePrice(25, 150);
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

        model = shaftModels.get(brand).get(choiceModels);
        flex = _flex;
        type = Enums.Goods.Shaft;
        length =  lengthInInches;
        initialPrice = generatePrice(25, 150);
        price = initialPrice;
    }
    public String getFlex()
    {
        return flex;
    }

    @Override
    public void generateModels() {
        shaftModels.put(shaftBrands[0], new ArrayList<>(Arrays.asList("Diamana GT", "Tensei","C6", "DF-Series","Kuro Kage","Vanquish")));
        shaftModels.put(shaftBrands[1], new ArrayList<>(Arrays.asList("Axiom", "Ventus TR", "Ventus","Vista Pro", "Air Speeder", "Sakura", "Motore X")));
        shaftModels.put(shaftBrands[2], new ArrayList<>(Arrays.asList("HZRDUS", "Riptide CB","EvenFlow", "Cypher", "Blackout")));
        shaftModels.put(shaftBrands[3], new ArrayList<>(Arrays.asList("C-TAPER","$-TAPER","TGI Tour", "Tour V", "Tour LITE","PGI","CARLSBAD")));
        shaftModels.put(shaftBrands[4], new ArrayList<>(Arrays.asList("Dynamic Gold", "Steel Fiber","VRTX")));


    }
}
