package PGA;

import Activity.Ecommerce;
import Activity.Fitting;
import Activity.Selling;
import Activity.Service;
import Observer.Publisher;
import buyer.Customer;
import enums.Enums;
import factory.GoodsFactory;
import factory.StaffFactory;
import goods.*;
import searchAlgorithm.BFSSearch;
import searchAlgorithm.DijkstraSearch;
import staff.*;
import buyer.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PGATourSuperstore implements Publisher {
    ArrayList<Staff>[] employees;//[fritter,logistics, management, Service person, Soft goods]
    ArrayList<Staff> departedStaff = new ArrayList<Staff>();
    ArrayList<Item> inventory;
    ArrayList<Item> soldInventory;
    int numCustomersTotal = 0;
    ArrayList<Customer> soldCustomers;
    protected String storeNum;
    //ArrayList<Customer> serviceOrders;
    private GoodsFactory goodCreate;
    private StaffFactory staffCreate;
    private double budget;
    private double netSales;
    double staffEarnings = 0.0;
    ArrayList<Customer> pastCustomers = new ArrayList<Customer>();
    // Counter for items

    private int[] itemCounters;

    private int bagCounter = 0;
    private int ballCounter = 0;
    private int clothingCounter = 0;
    private int clubCounter = 0;
    private int gloveCounter = 0;
    private int shoeCounter = 0;
    private int accessoryCounter= 0;
    private int gripCounter = 0;
    private int racketCounter = 0;
    private int shaftCounter = 0;

    private boolean initialized;
    private int totalEmployees = 0;
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";//learned on geeks for geeks
    Service s;
    Ecommerce ecom;
    Fitting fit;
    Selling sell;
    Ecommerce ecommerce;


    public PGATourSuperstore(String num){
        storeNum = num;
        budget = 500000;
        netSales = 0;
        employees = new ArrayList[5];
        for(int i = 0; i < employees.length; i++)
        {
            employees[i] = new ArrayList<Staff>();
        }
        inventory = new ArrayList<>();
        soldInventory = new ArrayList<>();
        goodCreate = new GoodsFactory();
        staffCreate = new StaffFactory();
        hireEmployees();
        s = new Service(storeNum);
//      ecom = new Ecommerce(storeNum);
        fit = new Fitting(storeNum);
        sell = new Selling(storeNum);
        ecommerce = new Ecommerce();
        initialized = false;


        itemCounters = new int[Enums.Goods.values().length];
        Arrays.fill(itemCounters, 0);

        fillInventory();
        initialized = true;
    }

    public void income(double money)
    {
        budget += money;
        notifySubscriber("budgetAdd", String.valueOf(money));
    }
    public void expense(double money){
        budget -= money;
        notifySubscriber("budgetSub", String.valueOf(money));
        if (budget < 0)
        {
            income(250000.0);

            System.out.println(ANSI_RED +"!!!!!!!PGA Borrowed $250000 from the bank!!!!!!!"+ANSI_RESET);
            notifySubscriber("log","!!!!!!!PGA Borrowed $250000 from the bank!!!!!!!");
        }
        //notifySubscriber("budgetSub",);
    }

    public void reCount()
    {
        bagCounter = 0;
        ballCounter = 0;
        clothingCounter = 0;
        clubCounter = 0;
        gloveCounter = 0;
        shoeCounter = 0;
        accessoryCounter= 0;
        gripCounter = 0;
        racketCounter = 0;
        shaftCounter = 0;
        for(int i =0; i< inventory.size(); i++)
        {

            Item currItem = inventory.get(i);
            if(currItem.getType() == Enums.Goods.Clothing)
            {
                clothingCounter++;
            } else if (currItem.getType() == Enums.Goods.Club) {
                clubCounter++;
            }
            else if (currItem.getType() == Enums.Goods.Glove) {
                gloveCounter++;
            }
            else if (currItem.getType() == Enums.Goods.Grip) {
                gripCounter++;
            }
            else if (currItem.getType() == Enums.Goods.Shaft) {
                shaftCounter++;
            }else if (currItem.getType() == Enums.Goods.Accessory) {
                accessoryCounter++;
            }
            else if (currItem.getType() == Enums.Goods.Bag) {
                bagCounter++;
            }
            else if (currItem.getType() == Enums.Goods.Balls) {
                ballCounter++;
            }
            else if (currItem.getType() == Enums.Goods.Racket) {
                racketCounter++;
            }
            else if (currItem.getType() == Enums.Goods.Shoes) {
                shoeCounter++;
            }
        }
    }



    public void fillInventory(){//fix initialized and add inventory
        while(bagCounter < 10 ||
                clothingCounter < 10 ||
                ballCounter < 10 ||
                clubCounter < 10 ||
                gloveCounter < 10 ||
                shoeCounter < 10||
                accessoryCounter < 10||
                gripCounter < 10||
                racketCounter < 10||
                shaftCounter < 10)//while there is less than 100 of any of these items fill the inventory
        {
            if(bagCounter < 10){
                Bag bag = (Bag) goodCreate.getInstanceItem(Enums.Goods.Bag);
                if(initialized)
                {
                    expense(bag.getCost());
                }

                inventory.add(bag);
                bagCounter++;
                System.out.println("Store purchased " + bag.getBrand() + " " + bag.getModel() + " for a price of " + bag.getPrice());
            }
            if(clothingCounter < 10){
                Clothing clothing = (Clothing) goodCreate.getInstanceItem(Enums.Goods.Clothing);
                if(initialized)
                {
                    expense(clothing.getCost());
                }
                inventory.add(clothing);
                clothingCounter++;
                System.out.println("Store purchased " + clothing.getBrand() + " " + clothing.getModel() + " for a price of " + clothing.getPrice());
            }
            if(ballCounter < 10){
                Balls balls = (Balls) goodCreate.getInstanceItem(Enums.Goods.Balls);
                if(initialized)
                {
                    expense(balls.getCost());
                }
                inventory.add(balls);
                ballCounter++;
                System.out.println("Store purchased " + balls.getBrand() + " " + balls.getModel() + " for a price of " + balls.getPrice());
            }
            if(clubCounter < 10){
                Club club = (Club) goodCreate.getInstanceItem(Enums.Goods.Club);
                if(initialized)
                {
                    expense(club.getCost());
                }
                inventory.add(club);
                clubCounter++;
                System.out.println("Store purchased " + club.getBrand() + " " + club.getModel() + " for a price of " + club.getPrice());
            }
            if(gloveCounter < 10){
                Glove gloves = (Glove) goodCreate.getInstanceItem(Enums.Goods.Glove);
                if(initialized)
                {
                    expense(gloves.getCost());
                }
                inventory.add(gloves);
                gloveCounter++;
                System.out.println("Store purchased " + gloves.getBrand() + " " + gloves.getModel() + " for a price of " + gloves.getPrice());
            }
            if(shoeCounter < 10){
                Shoes shoes = (Shoes) goodCreate.getInstanceItem(Enums.Goods.Shoes);
                if(initialized)
                {
                    expense(shoes.getCost());
                }
                inventory.add(shoes);
                shoeCounter++;
                System.out.println("Store purchased " + shoes.getBrand() + " " + shoes.getModel() + " for a price of " + shoes.getPrice());
            }
            if(accessoryCounter < 10){
                Accessory acc = (Accessory) goodCreate.getInstanceItem(Enums.Goods.Accessory);
                if(initialized)
                {
                    expense(acc.getCost());
                }
                inventory.add(acc);
                accessoryCounter++;
                System.out.println("Store purchased " + acc.getBrand() + " " + acc.getModel() + " for a price of " + acc.getPrice());
            }
            if(gripCounter < 10){
                Grip grip = (Grip) goodCreate.getInstanceItem(Enums.Goods.Grip);
                if(initialized)
                {
                    expense(grip.getCost());
                }
                inventory.add(grip);
                gripCounter++;
                System.out.println("Store purchased " + grip.getBrand() + " " + grip.getModel() + " for a price of " + grip.getPrice());
            }
            if(racketCounter < 10){
                Racket tennisRacket = (Racket) goodCreate.getInstanceItem(Enums.Goods.Racket);
                if(initialized)
                {
                    expense(tennisRacket.getCost());
                }
                inventory.add(tennisRacket);
                racketCounter++;
                System.out.println("Store purchased " + tennisRacket.getBrand() + " " + tennisRacket.getModel() + " for a price of " + tennisRacket.getPrice());
            }
            if(shaftCounter < 10){
                Shaft golfShaft = (Shaft) goodCreate.getInstanceItem(Enums.Goods.Shaft);
                if(initialized)
                {
                    expense(golfShaft.getCost());
                }
                inventory.add(golfShaft);
                shaftCounter++;
                System.out.println("Store purchased " + golfShaft.getBrand() + " " + golfShaft.getModel() + " for a price of " + golfShaft.getPrice());
            }
        }
    }
    public void hireEmployees()
    {
        for(int i = 0; i < employees.length;i++)
        {
            //[fritter,logistics, management, Service person, Soft goods]
            while(i == 0 && (employees[i].size()<5))//fitters
            {
                Fitter temp = (Fitter) staffCreate.getInstanceStaff(Enums.StaffType.Fitter);
                employees[0].add(temp);
                System.out.println("Hired new fitter named " + temp.getName());
                notifySubscriber("log","Hired new fitter Named "+ temp.getName());
                totalEmployees++;
            }
            while(i == 1 && (employees[i].size()<5))//logistics
            {
                Logistics temp = (Logistics) staffCreate.getInstanceStaff(Enums.StaffType.Logistic);
                employees[i].add(temp);
                System.out.println("Hired new logisitic named " + temp.getName());
                notifySubscriber("log","Hired new logistic Named "+ temp.getName());
                totalEmployees++;
            }
            while(i == 2 && (employees[i].size()<5))//management
            {
                Management temp = (Management) staffCreate.getInstanceStaff(Enums.StaffType.Management);
                employees[i].add(temp);
                System.out.println("Hired new management named " + temp.getName());
                notifySubscriber("log","Hired new logistic Named "+ temp.getName());
                totalEmployees++;
            }
            while(i == 3 && (employees[i].size()<5))//service person
            {
                ServicePerson temp = (ServicePerson) staffCreate.getInstanceStaff(Enums.StaffType.ServicePerson);
                employees[i].add(temp);
                System.out.println("Hired new Service Person named " + temp.getName());
                notifySubscriber("log","Hired new logistic Named "+ temp.getName());
                totalEmployees++;
            }
            while(i == 4 && (employees[i].size()<5))//softgoods
            {
                SoftGood temp = (SoftGood) staffCreate.getInstanceStaff(Enums.StaffType.SoftGood);
                employees[i].add(temp);
                System.out.println("Hired new Soft Good named " + temp.getName());
                notifySubscriber("log","Hired new logistic Named "+ temp.getName());
                totalEmployees++;
            }
        }
    }
    public void opening()
    {
        // Using system.out.println for making printed out logs, then we can use observers to create logs that can be saved (if it's a feature)
        System.out.println("Opening Store");
        notifySubscriber("log","Now Opening.....");
        fillInventory();
        // Make sure that there at least 10 goods of each type
        // 4 for every staff
        // Hire people in opening
        if(totalEmployees < 20) // 4 per type in 5 types = 20 total employees in 1 store
        {
            hireEmployees();
        }

        System.out.println("Opening Sucessful");

    }

    public void Service(ArrayList<Customer> serviceOrders)
    {
        // Parth
        // Fixing
        double beforeService = budget;
        double afterService= s.service(serviceOrders, employees[3], beforeService);
        double expended = afterService- beforeService;
        income(expended);
        reCount();
    }
    public void fitting(ArrayList<Customer> serviceOrders)
    {
        // Later
        // use fitters
        double beforeService = budget;
        double afterService = fit.clubFitting(serviceOrders,employees[0],beforeService);
        double expended = afterService-beforeService;
        income(expended);
        reCount();
    }
    public void pickupEcom(ArrayList<Item> goals)
    {
        // Make this as a to-do (future work)
        Ecommerce ecommerce = new Ecommerce();
        for(Item x : goals){
            ecommerce.addGoals(x);
        }
        ecommerce.setAlgorithm(new DijkstraSearch());
        ecommerce.findPaths();
    }

    public void printRecipt()
    {
        for(int i = numCustomersTotal; i < pastCustomers.size(); i++)
        {
            Customer currCustomer = pastCustomers.get(i);
            System.out.println("Customer: " + currCustomer.name);
            notifySubscriber("log","Customer: " + currCustomer.name);
            for(int j = 0; j < currCustomer.getCartSize(); j++)
            {
                Item currItem = currCustomer.getCartAt(j);
                System.out.println(currItem.getBrand() +" "+ currItem.getModel()+ "........" + currItem.getPrice());
                notifySubscriber("log", currItem.getBrand() +" "+ currItem.getModel()+ "........" + currItem.getPrice());
            }
        }
    }

    public void selling(Customer joe)
    {
        double beforeSelling = netSales;

        double netsaleAfter =sell.selling(joe, employees, inventory, soldInventory, netSales, staffEarnings, pastCustomers);
        netSales += (netsaleAfter-beforeSelling);
        income (netsaleAfter-beforeSelling);//to account for only the most recent sale
        reCount();
    }

    public double ending()
    {
        // Later
        // make it similar to PGA
        // fire people
        //
        for(int i = 0; i < employees.length; i++ ) //pay all employees
        {
            for(int j = 0; j < employees[i].size(); j++)
            {
                Staff currEmployee = employees[i].get(j);
//                System.out.println("Days worked before pay"+currEmployee.daysWorked);
                double pay = currEmployee.pay();
                expense(pay);
//                System.out.println("Days worked after pay"+currEmployee.daysWorked);
                notifySubscriber("budget", String.valueOf(pay));
                notifySubscriber("staff", String.valueOf(pay));

            }

        }
        quitting();//chance staff type quitting
        report();// generates report

        System.out.println("____Totals____"+storeNum);

//        System.out.println("Operating Budget: " + budget);
        System.out.println("Net Sales Today: " + netSales);
        double temp = netSales;
        netSales = 0;//reset everyday
        return temp;
    }

    public void quitting()
    {
        for(int i = employees.length-2; i > 0; i--)//for staff other than driver
        {
            Random rand = new Random();
            double chance = rand.nextDouble();//0-1
            if(chance <=0.1)
            {
                int quit = rand.nextInt(employees[i].size());
                Staff quitter = employees[i].get(quit);
                totalEmployees--;
                if(i == 2)//manager then replace right away
                {
                    int pull = rand.nextInt(employees[1].size());
                    System.out.println(": There are this many logistics associates that could get promoted:" + employees[0].size());
                    notifySubscriber("log","There are this many logistics associates that could get promoted:" + employees[0].size());
                    Staff logistic = employees[1].get(pull);//pulls random intern

                    Management manager = new Management();
                    manager.setName(logistic.getName());
                    manager.setBalance(logistic.getBalance());
                    manager.setDaysWorked(logistic.getDaysWorked());
                    manager.setBonus(logistic.getBonusEarned());
                    manager.setType(logistic.getType());
                    manager.setId(logistic.getId());


                }
                else{

                    departedStaff.add(quitter);//graveyard
                    System.out.println(quitter.getName()+" Quit the job");
                    employees[i].remove(quit);//remove employee
                }

            }
        }
    }

    public void report()
    {
        System.out.println(" ====End of day report====");
        notifySubscriber("log","====End of day report====");
        staffReport();
        showInventory();
    }

    public void showInventory()
    {
        String data[] = new String[7];
        System.out.println(" ====Current PGA Inventory====");
        notifySubscriber("log","====Current PGA Inventory====");
        data[0] = "Make&Model";
        data[1] ="Year";
        data[2] = "MSRP";
        data[3] = "Vin";
        data[4] = "Condition";
        data[5] = "Cleanliness";
        data[6] = "Inventory Status";
        notifySubscriber("logInventory",data[0]);
        notifySubscriber("logInventory",data[1]);
        notifySubscriber("logInventory",data[2]);
        notifySubscriber("logInventory",data[3]);
        notifySubscriber("logInventory",data[4]);
        notifySubscriber("logInventory",data[5]);
        notifySubscriber("logInventoryln",data[6]);
        System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n", data); //print headers
        for(int i = 0; i < inventory.size(); i++)
        {
            Item item = inventory.get(i);

            data[0] =item.getBrand()+" "+item.getModel();
            data[1] = String.valueOf(item.getUpc());
            data[2] = String.valueOf(item.getPrice());
            data[3] = String.valueOf(item.getType());
            data[4] = "In Stock";
            notifySubscriber("logInventory",data[0]);
            notifySubscriber("logInventory",data[1]);
            notifySubscriber("logInventory",data[2]);
            notifySubscriber("logInventory",data[3]);
            notifySubscriber("logInventory",data[4]);
            System.out.printf("%-25s%-25s%-25s%-25s%-25s\n", data);
        }
        System.out.println("  ");
        System.out.println(" ====PGA Sold List====");
        notifySubscriber("log","====PGA Sold List====");
        if (soldInventory.size() == 0)
        {
            System.out.println(" No cars sold yet");
        }
        for(int i = 0; i < soldInventory.size(); i++)
        {


            Item item = soldInventory.get(i);

            data[0] =item.getBrand()+" "+item.getModel();
            data[1] = String.valueOf(item.getUpc());
            data[2] = String.valueOf(item.getPrice());
            data[3] = String.valueOf(item.getType());
            data[4] = "In Stock";
            notifySubscriber("logInventory",data[0]);
            notifySubscriber("logInventory",data[1]);
            notifySubscriber("logInventory",data[2]);
            notifySubscriber("logInventory",data[3]);
            notifySubscriber("logInventory",data[4]);
            System.out.printf("%-25s%-25s%-25s%-25s%-25s\n", data);
        }

    }

    public void staffReport()
    {
        //active staff
        System.out.println("======Here is the reports on all active staff members======");
        String data[] = new String[5];
        for(int i = 0; i < employees.length;i++)
        {
            System.out.println(": -----"+employees[i].get(0).getType()+ "s List-----");
            notifySubscriber("log","-----"+employees[i].get(0).getType() + "s List-----");
            data[0] = "Name";
            data[1] = "Total days worked";
            data[2] = "Total Earnings";
            data[3] = "Total Bonus Earned";
            data[4] = "Work Status";
            notifySubscriber("logstaff",data[0]);
            notifySubscriber("logstaff",data[1]);
            notifySubscriber("logstaff",data[2]);
            notifySubscriber("logstaff",data[3]);
            notifySubscriber("logstaffln",data[4]);
            System.out.printf(": "+"%-25s%-25s%-25s%-25s%-25s\n", data); //print headers
            for(int j = 0; j < employees[i].size();j++)
            {
//                System.out.println("=================================");
                Staff currEmp = employees[i].get(j);

                data[0] = currEmp.getName();
                data[1] = String.valueOf(currEmp.getDaysWorked());
                data[2] = String.valueOf(currEmp.getBalance());
                data[3] = String.valueOf(currEmp.getBonus());
                data[4] = "Active";
                System.out.printf("%-25s%-25s%-25s%-25s%-25s\n", data); //print data
                notifySubscriber("logstaff",data[0]);
                notifySubscriber("logstaff",data[1]);
                notifySubscriber("logstaff",data[2]);
                notifySubscriber("logstaff",data[3]);
                notifySubscriber("logstaffln",data[4]);//newline
            }
        }
        //inactive staff
        System.out.println(" ");//white space
        System.out.println(" =====Here is the reports on all Terminated staff members======");
        notifySubscriber("log","======Here is the reports on all Terminated staff members======");
        if(departedStaff.size() == 0)
        {
            System.out.println(" No Staff Members have quit so far");
        }
        else {
            data[0] = "Name";
            data[1] = "Total days worked";
            data[2] = "Total Earnings";
            data[3] = "Total Bonus Earned";
            data[4] = "Work Status";
            notifySubscriber("logstaff",data[0]);
            notifySubscriber("logstaff",data[1]);
            notifySubscriber("logstaff",data[2]);
            notifySubscriber("logstaff",data[3]);
            notifySubscriber("logstaffln",data[4]);
            System.out.printf("%-25s%-25s%-25s%-25s%-25s\n", data); //print headers
            for (int i = 0; i < departedStaff.size(); i++) {
//                System.out.println("=================================");
                Staff currEmp1 = departedStaff.get(i);

                data[0] = currEmp1.getName();
                data[1] = String.valueOf(currEmp1.getDaysWorked());
                data[2] = String.valueOf(currEmp1.getBalance());
                data[3] = String.valueOf(currEmp1.getBonus());
                data[4] = "Inactive";
                System.out.printf("%-25s%-25s%-25s%-25s%-25s\n", data); //print data
                notifySubscriber("logstaff",data[0]);
                notifySubscriber("logstaff",data[1]);
                notifySubscriber("logstaff",data[2]);
                notifySubscriber("logstaff",data[3]);
                notifySubscriber("logstaffln",data[4]);//newline

            }
        }
    }

    public ArrayList<Staff>[] getStaff() {return employees;}
    public ArrayList<Staff> getStaffArrayList() {
        ArrayList<Staff> temp = new ArrayList<>();
        for(int i =0; i < employees.length; i++){
            temp.addAll(employees[i]);
        }
        return temp;
    }
    public ArrayList<Item> getInventory() {return inventory;}
    public ArrayList<Item> getSoldInventory() {return soldInventory;}
    public double getBudget(){return budget;}
    public void removeEmployee(Staff employee, int type)
    {
        // Notify Subscriber
        System.out.println("Fired " + employee.getName());
        employees[type].remove(employee);
        totalEmployees--;
    }
    public void removeItem(Item item)
    {
        // Notify Subscriber
        System.out.println("Removed " + item.getType() + ", " + item.getModel() +  " from the inventory" );
        inventory.remove(item);
        itemCounters[item.getType().ordinal()]--;
    }

    public void addItem(String brand, String model, Enums.Goods type, double price)
    {
        // Notify Subsrciber required here
        Item item = goodCreate.getInstanceItem(type);
        item.setBrand(brand);
        item.setModel(model);
        item.setPrice(price);

        inventory.add(item);

        System.out.println("Added " + item.getType() + ", " + item.getModel() +  " to the inventory" );
    }

    public void addEmployee(String name, Enums.StaffType type, double pay)
    {
        Staff staff = staffCreate.getInstanceStaff(type);
        staff.setName(name);
        staff.setPayRate(pay);

        employees[type.ordinal()].add(staff);
        System.out.println("Hired new Service Person named " + name);
        totalEmployees++;
    }
}
