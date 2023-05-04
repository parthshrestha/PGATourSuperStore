package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Racket extends Item{
    String[] racketBrands ={"Wilson","Babolat", "Yonex", "Head"};
    String[] sizes = {"19","21","23","25"};
    protected String size;
    //ArrayList<String>[] racketModels = new ArrayList[racketBrands.length];
    HashMap<String, ArrayList<String>> racketModels = new HashMap<>();
    public Racket()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(racketBrands.length);
        brand = racketBrands[choiceBrand];

        int choiceModels = rand.nextInt(racketModels.get(brand).size());

        model = racketModels.get(brand).get(choiceModels);
        type = Enums.Goods.Racket;
        size = sizes[rand.nextInt(sizes.length)];
        initialPrice = generatePrice(30, 260);
        price = initialPrice;
    }

    @Override
    public void generateModels() {
        racketModels.put(racketBrands[0],new ArrayList<>(Arrays.asList("Ultra", "Pro Staff","Blade","Clash","Triad Five","Burn Pink","Hyper Hammer")));
        racketModels.put(racketBrands[1],new ArrayList<>(Arrays.asList("Pure Drive","Nadal","Pure Aero Lite","BOOST A","Pure Strike")));
        racketModels.put(racketBrands[2],new ArrayList<>(Arrays.asList("EZONE","VCore Pro")));
        racketModels.put(racketBrands[3],new ArrayList<>(Arrays.asList("G360+ Instinct MP","Graphene XT Instinct","BOOM Team","Instinct PWR Radical","Speed MP","Instinct Team Lite")));

    }
}
