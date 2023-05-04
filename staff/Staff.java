package staff;
import enums.Enums;

import java.text.DecimalFormat;
import java.util.*;
import java.util.UUID;

public abstract class Staff {
    protected String name;
    protected Double balance;
    protected int daysWorked;
    protected double bonusEarned;
    protected Enums.StaffType type;
    protected String staffId;
    protected Double payRate;

    private Vector<String> names = new Vector<>();
    public Staff()
    {
        name = naming();
        //salary, staff itself does not have a salary set yet
        balance = 0.0; // not paid yet
        daysWorked = 0;
        bonusEarned = 0.0;// startign bonus would be 0
        staffId = UUID.randomUUID().toString().replace("-",""); // this gives vin a 32 letter unique code that only it has
    }

    protected double randPayRate(double min, double max)
    {
        DecimalFormat df = new DecimalFormat("#.##");
        double pay = ((Math.random() * (max - min)) + min);
        return Double.parseDouble(df.format(pay));
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
        while (!flip)
        {
            Random rand = new Random();
            int f = rand.nextInt(first.length);
            int l = rand.nextInt(last.length);
            generateName = first[f] + " "+ last[l];

            if(!names.contains(generateName))// check is still uniqu
            {
                names.add(generateName);
                flip = true;// end loop
            }
        }
        return generateName;
    }
    public abstract void pay();// different  per class depending on fixed rates
    // getters
    public String getName()
    {
        return name;
    }
    public String getStaffId()
    {
        return staffId;
    }
    public double getBalance()
    {
        return balance;
    }
    public int getDaysWorked()
    {
        return daysWorked;
    }
    public double getBonusEarned() {return bonusEarned; }
    public double getPayRate(){return payRate;}
    public Enums.StaffType getType() {return type;}

    //setters
    public  void setName(String name) { this.name = name; }
    public void setPayRate( double pay) {this.payRate = pay; }
    public void setBonus(double tip)
    {
        bonusEarned += tip;
    }
    public void setBalance(double money)
    {
        balance = money;
    }

    public void incrementDaysWorked()
    {
        daysWorked++;
    }

    public void setType(Enums.StaffType type) {
        this.type = type;
    }

    public String toString() {
        return String.format("%-20s  $%-10.2f  %-10d  $%-10.2f  %-15s  %-10.2f  $%-10s",
                name, balance, daysWorked, bonusEarned, type.toString(), payRate, staffId);
    }
}

