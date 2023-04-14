package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Racket extends Item{
    String[] racketBrands ={"FootJoy","Nike","Callaway","Titelist","Under Armour","Bionic","TaylorMade","PGA Tour","Zero Friction"};
    String[] sizes = {"S","M","L","XL","Cadet S","Cadet M","Cadet L","Cadet XL"};
    protected String size;
    ArrayList<String>[] racketModels = new ArrayList[racketBrands.length];
    public Racket()
    {
        super();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(racketBrands.length);
        int choiceModels = rand.nextInt(racketModels[choiceBrand].size());
        brand = racketBrands[choiceBrand];
        model = racketModels[choiceBrand].get(choiceModels);
        type = Enums.Goods.Racket;
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
        racketModels[0].add("ComfortSof");

        racketModels[1].add("Dura Feel");
        //Callaway
        racketModels[2].add("Dawn Patrol");
        racketModels[2].add("Weather Spann");

    }
}
