import enums.Enums;
import goods.Club;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Customer {
    public String name;
    int age;
    double wristToFloorDistance;
    Club[] golfBag = new Club[14];
    ArrayList<goods.Item> cart;
    Enums.ServiceType service;
    Enums.CustomerIntent intent;
    Enums.ServiceType[] conditions= {Enums.ServiceType.LENGTHEN,Sho}
    String MemberID;
    public Customer()
    {
        name = naming();
        //salary, staff itself does not have a salary set yet
        age = new Double(((Math.random() * (65 - 18)) + 18)).intValue();
        wristToFloorDistance = ((Math.random() * (41 - 25)) + 25);
        MemberID = UUID.randomUUID().toString().replace("-",""); // this gives vin a 32 letter unique code that only it has

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




}
