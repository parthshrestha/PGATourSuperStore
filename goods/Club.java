package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Club extends Item{
    String[] clubBrands ={"Callaway","Titelist","TaylorMade","Cobra","Ping"};
    HashMap<String, ArrayList<String>> clubModels = new HashMap<>();
    String[] clubHeads = {"Driver", "3-Wood", "5-Wood", "3-Iron", "4-Iron", "5-Iron", "6-Iron", "7-Iron", "8-Iron", "9-Iron", "Pitching Wedge", "Sand Wedge", "Lob Wedge", "Putter"};
    protected String size;

    protected String clubHead;
    protected Enums.ClubHeads clubHeadEnum;
    protected Grip clubGrip;
    protected Shaft clubShaft;
    protected Enums.Condition condition;
    public Club()
    {
        super();
        clubGrip = new Grip();
        clubShaft = new Shaft();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(clubBrands.length);
        brand = clubBrands[choiceBrand];
        int choiceModels = rand.nextInt(clubModels.get(brand).size());
        model = clubModels.get(brand).get(choiceModels);
        type = Enums.Goods.Club;
        clubHead = clubHeads[rand.nextInt(clubHeads.length)];
        clubHeadEnum = typeClub();
        condition = generateCondition();
        generatePrice();
        price = initialPrice;
    }
    public Club(Enums.ClubHeads clubHeadName)
    {
        //parameterized club head
        super();
        clubGrip = new Grip();
        clubShaft = new Shaft();
        generateModels();
        Random rand = new Random();
        int choiceBrand = rand.nextInt(clubBrands.length);
        brand = clubBrands[choiceBrand];
        int choiceModels = rand.nextInt(clubModels.get(brand).size());
        model = clubModels.get(brand).get(choiceModels);
        type = Enums.Goods.Club;
//        clubHead = clubHeads[rand.nextInt(clubHeads.length)];
//        clubHeadEnum = typeClub();

        generatePrice();
        price = initialPrice;
        if(clubHeadName ==Enums.ClubHeads.DRIVER )
        {
            clubHeadEnum = Enums.ClubHeads.DRIVER;
            clubHead = "Driver";
        }
        else if(clubHeadName ==Enums.ClubHeads.THREEWOOD)
        {
            clubHeadEnum = Enums.ClubHeads.THREEWOOD;
            clubHead = "3-Wood";
        }
        else if(clubHeadName ==Enums.ClubHeads.FIVEWOOD)
        {
            clubHeadEnum = Enums.ClubHeads.FIVEWOOD;
            clubHead = "5-Wood";
        }
        else if(clubHeadName ==Enums.ClubHeads.THREEIRON)
        {
            clubHeadEnum = Enums.ClubHeads.THREEIRON;
            clubHead = "3-Iron";
        }
        else if(clubHeadName ==Enums.ClubHeads.FOURIRON)
        {
            clubHeadEnum = Enums.ClubHeads.FOURIRON;
            clubHead = "4-Iron";
        }
        else if(clubHeadName ==Enums.ClubHeads.FIVEIRON)
        {
            clubHeadEnum = Enums.ClubHeads.FIVEIRON;
            clubHead = "5-Iron";
        }
        else if(clubHeadName ==Enums.ClubHeads.SIXIRON)
        {
            clubHeadEnum = Enums.ClubHeads.SIXIRON;
            clubHead = "6-Iron";
        }
        else if(clubHeadName ==Enums.ClubHeads.SEVENIRON)
        {
            clubHeadEnum = Enums.ClubHeads.SEVENIRON;
            clubHead = "7-Iron";
        }
        else if(clubHeadName ==Enums.ClubHeads.EIGHTIRON)
        {
            clubHeadEnum = Enums.ClubHeads.EIGHTIRON;
            clubHead = "8-Iron";
        }
        else if(clubHeadName ==Enums.ClubHeads.NINEIRON)
        {
            clubHeadEnum = Enums.ClubHeads.NINEIRON;
            clubHead = "9-Iron";
        }
        else if(clubHeadName ==Enums.ClubHeads.PITCHINGWEDGE)
        {
            clubHeadEnum = Enums.ClubHeads.PITCHINGWEDGE;
            clubHead = "Pitching Wedge";
        }
        else if(clubHeadName ==Enums.ClubHeads.SANDWEDGE)
        {
            clubHeadEnum = Enums.ClubHeads.SANDWEDGE;
            clubHead = "Sand Wedge";
        }
        else if(clubHeadName ==Enums.ClubHeads.LOBWEDGE)
        {
            clubHeadEnum = Enums.ClubHeads.LOBWEDGE;
            clubHead = "Lob Wedge";
        }
        else//putter
        {
            clubHeadEnum = Enums.ClubHeads.PUTTER;
            clubHead = "Putter";
        }
    }
    public Enums.ClubHeads typeClub()
    {
       // {"Driver", "3-Wood", "5-Wood", "3-Iron", "4-Iron", "5-Iron", "6-Iron", "7-Iron", "8-Iron", "9-Iron", "Pitching Wedge", "Sand Wedge", "Lob Wedge", "Putter"}
        if(clubHead =="Driver")
        {
            return  Enums.ClubHeads.DRIVER;
        }
        else if(clubHead =="3-Wood" )
        {
            return Enums.ClubHeads.THREEWOOD;
        }
        else if(clubHead =="5-Wood" )
        {
            return Enums.ClubHeads.FIVEWOOD;
        }
        else if(clubHead == "3-Iron" )
        {
            return Enums.ClubHeads.THREEIRON;
        }
        else if(clubHead == "4-Iron" )
        {
            return Enums.ClubHeads.FOURIRON;
        }
        else if(clubHead == "5-Iron" )
        {
            return Enums.ClubHeads.FIVEIRON;
        }
        else if(clubHead == "6-Iron" )
        {
            return Enums.ClubHeads.SIXIRON;
        }
        else if(clubHead == "7-Iron" )
        {
            return Enums.ClubHeads.SEVENIRON;
        }
        else if(clubHead == "8-Iron" )
        {
            return Enums.ClubHeads.EIGHTIRON;
        }
        else if(clubHead == "9-Iron" )
        {
            return Enums.ClubHeads.NINEIRON;
        }
        else if(clubHead == "Pitching Wedge" )
        {
            return Enums.ClubHeads.PITCHINGWEDGE;
        }
        else if(clubHead == "Sand Wedge" )
        {
            return Enums.ClubHeads.SANDWEDGE;
        }
        else if(clubHead == "Lob Wedge" )
        {
            return Enums.ClubHeads.PITCHINGWEDGE;
        }
        else //putter
        {
            return Enums.ClubHeads.PUTTER;
        }
    }
    public Enums.Condition generateCondition()
    {
        Random rand = new Random();
        double chance = rand.nextDouble(); //random number 0-1
        if (chance <= 0.05) // 5% chance
        {
            return Enums.Condition.BROKEN;
        }
        else if ( chance > 0.5 && chance <= 0.4) { // 35% chance
            return Enums.Condition.PREOWNED;
        }
        else // 60% chance
        {
            return Enums.Condition.PERFECT;
        }
    }

    @Override
    public void generatePrice() {
        initialPrice = ((Math.random() * (50 - 20)) + 20);
    }

    @Override
    public void generateModels() {

        clubModels.put(clubBrands[0], new ArrayList<>(Arrays.asList("Paradym","Jaws MD5", "Rogue ST Max", "Mavrik")));
        clubModels.put(clubBrands[1], new ArrayList<>(Arrays.asList("Vokey SM8","TSR3","TSi3","TSR2","TSi2")));
        clubModels.put(clubBrands[2], new ArrayList<>(Arrays.asList("Stealth","Stealth2","Sim2 Max","M4")));
        clubModels.put(clubBrands[3], new ArrayList<>(Arrays.asList("AeroJet", "LTDx","King Tec", "Air-X","Radspeed" )));
        clubModels.put(clubBrands[4], new ArrayList<>(Arrays.asList("G425", "G430" )));


    }

    public Enums.Condition getCondition()
    {
        return condition;
    }
    public String getClubHead()
    {
        return clubHead;
    }
    public void setNewGrip(Grip newGrip)
    {
        clubGrip.brand = newGrip.brand;
        clubGrip.model = newGrip.model;
        clubGrip.size = newGrip.size;
        clubGrip.price = newGrip.price;
        clubGrip.initialPrice = newGrip.initialPrice;
    }
    public void setCondition(Enums.Condition _condition)
    {
        condition = _condition;
    }
    public String getClubGripSize()
    {
        return clubGrip.size;

    }
    public void setClubLength(int len)
    {
        clubShaft.length = len;
    }
    public String getClubShaftFlex()
    {
        return clubShaft.flex;
    }
    public int getClubShaftLength()
    {
        return clubShaft.length;
    }
    public Enums.ClubHeads getClubHeadEnum()
    {
        return clubHeadEnum;
    }
    public Grip getClubGrip()
    {
        return clubGrip;
    }
    public Shaft getClubShaft()
    {
        return clubShaft;
    }
}

