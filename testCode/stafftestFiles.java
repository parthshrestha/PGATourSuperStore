package testCode;

import staff.*;

public class stafftestFiles {
    public stafftestFiles()
    {
        runTest();
    }
    public void runTest()
    {
        System.out.println("Fitter");
        for(int i = 0; i < 5; i++)
        {
            Fitter fit = new Fitter();
            System.out.println("Name "+ fit.getName()+ " Balance: " + fit.getBalance()+ " PayRate:"+ fit.getPayRate());
        }
        System.out.println("Logistic");
        for(int i = 0; i < 5; i++)
        {
            Logistics logic = new Logistics();
            System.out.println("Name "+ logic.getName()+ " Balance: " + logic.getBalance()+ " PayRate:"+ logic.getPayRate());
        }
        System.out.println("Management");
        for(int i = 0; i < 5; i++)
        {
            Management manager = new Management();
            System.out.println("Name "+ manager.getName()+ " Balance: " + manager.getBalance()+ " PayRate:"+ manager.getPayRate());
        }
        System.out.println("Service Person");
        for(int i = 0; i < 5; i++)
        {
            ServicePerson servicePerson = new ServicePerson();
            System.out.println("Name "+ servicePerson.getName()+ " Balance: " + servicePerson.getBalance()+ " PayRate:"+ servicePerson.getPayRate());
        }
        System.out.println("Soft goods");
        for(int i = 0; i < 5; i++)
        {
            SoftGood softGood =  new SoftGood();
            System.out.println("Name "+ softGood.getName()+ " Balance: " + softGood.getBalance()+ " PayRate:"+ softGood.getPayRate());
        }
    }
}