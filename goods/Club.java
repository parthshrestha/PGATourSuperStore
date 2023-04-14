package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Random;

public class Club extends Item{
    String[] clubBrands ={"FootJoy","Nike","Callaway","Titelist","Under Armour","Bionic","TaylorMade","PGA Tour","Zero Friction"};
    String[] clubHeads = {"Driver", "3-Wood", "5-Wood", "3-Iron", "4-Iron", "5-Iron", "6-Iron", "7-Iron", "8-Iron", "9-Iron", "Pitching Wedge", "Sand Wedge", "Lob Wedge", "Putter"};
    protected String size;
    ArrayList<String>[] clubModels = new ArrayList[clubBrands.length];
    String clubHead;
    Grip clubGrip;
    Shaft clubShaft;
    public Club()
    {
        super();
        clubGrip = new Grip();
        clubShaft = new Shaft();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(clubBrands.length);
        int choiceModels = rand.nextInt(clubModels[choiceBrand].size());
        brand = clubBrands[choiceBrand];
        model = clubModels[choiceBrand].get(choiceModels);
        type = Enums.Goods.Glove;
        clubHead = clubHeads[rand.nextInt(clubHeads.length)];
        generatePrice();
        price = initialPrice;
    }
    public Club(Enums.ClubHeads clubHead)
    {
        //parameterized club head
    }

    @Override
    public void generatePrice() {
        initialPrice = ((Math.random() * (50 - 20)) + 20);
    }

    @Override
    public void generateModels() {

        clubModels[0].add("ComfortSof");
        clubModels[0].add("StaSof");
    }
}
