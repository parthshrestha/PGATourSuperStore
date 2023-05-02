package buyer;

import enums.Enums;
import goods.Club;
import goods.Grip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Customer {
    public String name;
    int age;
    double wristToFloorDistance;
    Club[] golfBag = new Club[14];
    ArrayList<goods.Item> cart;
    Enums.ServiceType service;
    protected Enums.CustomerIntent intent;
    Enums.CustomerIntent[] intents = {Enums.CustomerIntent.SERVICE,Enums.CustomerIntent.FITTING,Enums.CustomerIntent.SHOPPING};
    Enums.ServiceType[] services= {Enums.ServiceType.REGRIP,Enums.ServiceType.RESHAFT,Enums.ServiceType.LENGTHEN,Enums.ServiceType.SHORTEN,Enums.ServiceType.NONE};
    HashMap<String,ArrayList<Enums.ServiceType>> jobToEachClub;//<club head,jobs to be done>
    String MemberID;
    public Customer()
    {
        name = naming();
        //salary, staff itself does not have a salary set yet
        age =  (int)((Math.random() * (65 - 18)) + 18);
        wristToFloorDistance = ((Math.random() * (41 - 25)) + 25);
        MemberID = UUID.randomUUID().toString().replace("-",""); // this gives vin a 32 letter unique code that only it has
        generateSet();
        // initialize hash map
        for(int i= 0; i < golfBag.length; i++)
        {
            Club currClub = golfBag[i];
            jobToEachClub.put(currClub.getClubHead() ,new ArrayList<Enums.ServiceType>());
        }

    }
    public void generateSet()
    {
        Enums.ClubHeads[] set = {Enums.ClubHeads.DRIVER,
                Enums.ClubHeads.THREEWOOD,
                Enums.ClubHeads.FIVEWOOD,
                Enums.ClubHeads.THREEIRON,
                Enums.ClubHeads.FOURIRON,
                Enums.ClubHeads.FIVEIRON,
                Enums.ClubHeads.SIXIRON,
                Enums.ClubHeads.SEVENIRON,
                Enums.ClubHeads.EIGHTIRON,
                Enums.ClubHeads.NINEIRON,
                Enums.ClubHeads.PITCHINGWEDGE,
                Enums.ClubHeads.SANDWEDGE,
                Enums.ClubHeads.LOBWEDGE,
                Enums.ClubHeads.PUTTER};
        for(int i = 0; i < set.length; i++)
        {
            Club currClub = new Club(set[i]);
            golfBag[i] = currClub;
        }
    }
    public void generateService()
    {
        Random rand = new Random();
        for(int i = 0; i < golfBag.length; i++)
        {
            Club currClub = golfBag[i];
            if(currClub.getCondition() == Enums.Condition.BROKEN)// shaft needs change
            {
                jobToEachClub.get(currClub.getClubHead()).add(Enums.ServiceType.RESHAFT);
                double chance = rand.nextDouble(); //random number 0-1
                if (chance > 0.01)
                {
                    jobToEachClub.get(currClub.getClubHead()).add(Enums.ServiceType.REGRIP);
                }
                else//keep only a slight chance they don't get a re grip
                {
                    jobToEachClub.get(currClub.getClubHead()).add(Enums.ServiceType.NONE);
                }
            }
            else if(currClub.getCondition() == Enums.Condition.PREOWNED)
            {
                double chance = rand.nextDouble(); //random number 0-1
                if(chance >= 0.1 && chance < 0.65)//55%
                {

                    jobToEachClub.get(currClub.getClubHead()).add(Enums.ServiceType.REGRIP);
                }
                else if (chance < 0.1)//10%
                {
                    jobToEachClub.get(currClub.getClubHead()).add(Enums.ServiceType.NONE);
                }
                else //35%
                {
                    jobToEachClub.get(currClub.getClubHead()).add(Enums.ServiceType.RESHAFT);
                    jobToEachClub.get(currClub.getClubHead()).add(Enums.ServiceType.REGRIP);
                }
            }
            else//perfect
            {
                double chance = rand.nextDouble(); //random number 0-1
                if(chance >= 0.7 && chance < 0.95)//25%
                {

                    jobToEachClub.get(currClub.getClubHead()).add(Enums.ServiceType.REGRIP);
                }
                else if (chance < 0.7)////new should not have to do service
                {
                    jobToEachClub.get(currClub.getClubHead()).add(Enums.ServiceType.NONE);
                }
                else //5%
                {
                    jobToEachClub.get(currClub.getClubHead()).add(Enums.ServiceType.RESHAFT);
                    jobToEachClub.get(currClub.getClubHead()).add(Enums.ServiceType.REGRIP);
                }
            }
        }
    }
    public String naming()
    {

        String[] first = {"Alice","Alyssa","Alex","Andrew",
                "Bob","Blake","Bobby","Batrice",
                "Charlie","Charlotte", "Cameron","Camden",
                "David","Denice","Danny","Dakota",
                "Emily","Elliot","Emmet","Esmeralda",
                "Frank","Fred","Frannie","Franchesca",
                "Grace", "Grant", "Gerald", "Garret",
                "Henry", "Hunter","Hema", "Hope","Hermionie",
                "Isabella", "Issac", "Isa","Ingrid",
                "Jack", "Jasper", "James", "Jose",
                "Kate", "Kristine", "Kathrine","Kendrick",
                "Liam", "Leo","Lilly","Lila",
                "Mia", "Mandira", "Mandy", "Maddy",
                "Noah", "Nova", "Nate", "Neo",
                "Olivia", "Oliver","Oly",
                "Parker", "Parth", "Pat","Patricia",
                "Quin",
                "Riley", "Randalph", "Ronald","Roshan",
                "Samantha", "Sushma", "Sami", "Sam",
                "Thomas", "Tom", "Tallon","Taylor",
                "Uma", "Umrita",
                "Victoria", "Victor", "Veronica",
                "William", "Will","Wade",
                "Xavier", "Xander",
                "Yara", "Yushma","Yoru", "Yash",
                "Zachary", "Zach","Zoreh"};
        String[] last = {"Anderson","Ames", "Brown","Baylor", "Carter",  "Davis","Dong", "Evans", "Ford", "Garcia", "Harris", "Jackson", "Khan", "Lee","Lamar", "Miller", "Nelson", "O'Connor", "Patel", "Rivera", "Smith","Shrestha", "Thompson", "Upton", "Vargas", "Young","Yun", "Zhang"};
        boolean flip = false; // switches to true once string is confirmed to be unique
        String generateName = "";
        Random rand = new Random();
        int f = rand.nextInt(first.length);
        int l = rand.nextInt(last.length);
        generateName = first[f] + " "+ last[l];

        return generateName;
    }
    public Club getClubAt(int i)
    {
        return golfBag[i];
    }
    public ArrayList<Enums.ServiceType> getServices(String _name)
    {
        return jobToEachClub.get(_name);
    }
    public Enums.CustomerIntent getIntent()
    {
        return intent;
    }
    public Double getWristToFloor()
    {
        return wristToFloorDistance;
    }





}
