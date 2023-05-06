package Activity;

import Observer.Publisher;
import buyer.Customer;
import enums.Enums;
import goods.Club;
import goods.Grip;
import staff.Fitter;
import staff.ServicePerson;
import staff.Staff;

import java.util.ArrayList;
import java.util.Random;

public class Fitting implements Publisher {
    protected String pgaStorenumber;
    private Object employee;

    public Fitting(String name)
    {
        pgaStorenumber = name;
    }
    public boolean correalateLengthDriver(int len, double wtf)
    {
        if(wtf <= 33.9)
        {
            if(len> 43)
            {
                return false;
            }
            else
            {return true;}
        }
        else if (wtf >= 34 && wtf<= 35.9)
        {
            if (len == 44)
            {
                return true;
            }
            else {
                return false;
            }
        }
        else if (wtf >= 36 && wtf <= 37.9)
        {
            if (len == 45)
            {
                return true;
            }
            else {
                return false;
            }
        }
        else //38+
        {
            if (len == 46)
            {
                return true;
            }
            else {
                return false;
            }
        }

    }
    public boolean correalateLengthWoods(int len, double wtf)
    {
        if(wtf <= 33.9)
        {
            if(len> 40)
            {
                return false;
            }
            else
            {return true;}
        }
        else if (wtf >= 34 && wtf<= 35.9)
        {
            if (len == 41)
            {
                return true;
            }
            else {
                return false;
            }
        }
        else if (wtf >= 36 && wtf <= 37.9)
        {
            if (len == 42)
            {
                return true;
            }
            else {
                return false;
            }
        }
        else //38+
        {
            if (len == 43)
            {
                return true;
            }
            else {
                return false;
            }
        }
    }
    public boolean correalateLengthIrons(int len, double wtf)
    {
        if(wtf <=33.9)
        {
            if(len == 36.0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(wtf >= 34 && wtf<=34.9)
        {
            if(len == 36.5)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(wtf >= 35 && wtf<=35.9)
        {
            if(len == 37.0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(wtf >= 36 && wtf<=36.9)
        {
            if(len == 37.5)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(wtf >= 37 && wtf<=37.9)
        {
            if(len == 38.0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(wtf >= 38 && wtf<=38.9)
        {
            if(len == 38.5)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else //39+
        {
            if (len == 39)
            {
                return true;
            }
            else {
                return false;
            }
        }

    }

    public int checkLength(int length, double wristToFloor, Enums.ClubHeads headName)
    {
        int len = 0;
        boolean trueLength = false;
        if(headName == Enums.ClubHeads.DRIVER)
        {
            trueLength = correalateLengthDriver( length, wristToFloor);
            if(trueLength)
            {
                return length;
            }
            else
            {
                if(wristToFloor <= 33.9)
                {
                    len = 43;
                }
                else if (wristToFloor >= 34 && wristToFloor <= 35.9)
                {
                    len= 44;
                }
                else if (wristToFloor >= 36 && wristToFloor <= 37.9)
                {
                    len = 45;
                }
                else //38+
                {
                    len = 46;
                }
            }
        }
        else if(headName == Enums.ClubHeads.FIVEWOOD ||headName == Enums.ClubHeads.THREEWOOD )
        {
            trueLength = correalateLengthWoods(length, wristToFloor);
            if(trueLength)
            {
                return length;
            }
            else
            {
                if(wristToFloor <= 33.9)
                {
                    len = 40;
                }
                else if (wristToFloor >= 34 && wristToFloor <= 35.9)
                {
                    len= 41;
                }
                else if (wristToFloor >= 36 && wristToFloor <= 37.9)
                {
                    len = 42;
                }
                else //38+
                {
                    len = 43;
                }
            }
        }
        else //other irons
        {
            //do someting acording to chart
            len = 0;
        }
        return len;
    }
    public Grip checkGrip(String grip, double handSize)//https://www.golfpride.com/blog/what-size-golf-grip-do-i-need/
    {
        Grip nGrip;
        if(handSize<6.0 || handSize <= 7.5)
        {//standard
            nGrip = new Grip("Standard");
        }
        else if (handSize >= 7.6 && handSize <=9.0)
        {//midsize
            nGrip = new Grip("Mid-Size");
        }
        else //9.1+
        {
            //jumbo
            nGrip = new Grip("Jumbo");
        }
        return nGrip;
    }
    public double clubFitting(ArrayList<Customer> serviceOrders, ArrayList<Staff> employees, double budget)
    {
        for (int i = 0; i < serviceOrders.size(); i++)
        {
            Random rand = new Random();
            Customer currentOrder = serviceOrders.get(i);// fifo
            int employeeSelection = rand.nextInt(employees.size());
            Fitter employee = (Fitter) employees.get(employeeSelection);
            if(currentOrder.getIntent() == Enums.CustomerIntent.FITTING)
            {
                for (int j = 0; j < 14; j++)//all clubs
                {
                    Club currClub = currentOrder.getClubAt(j);
                    //check fitting
                    //check grip size
                    if(currClub.getClubHead() != "Putter")//everything but putter grip needs change
                    {
                        Grip g = checkGrip(currClub.getClubGripSize(),currentOrder.getHandSize());
                        if(currClub.getClubGrip().getSize() != g.getSize())
                        {
                            System.out.println("Club has incorrect Size Grip");
                            notifySubscriber("log","Club has incorrect Size Grip");
                            System.out.print("current grip size is: "+ currClub.getClubGrip().getSize());
                            notifySubscriber("log","current grip size is: "+ currClub.getClubGrip().getSize());
                            currClub.setNewGrip(g);
                            System.out.println(" new grip size is: "+ currClub.getClubGrip().getSize());
                            notifySubscriber("log"," new grip size is: "+ currClub.getClubGrip().getSize());
                            budget += currClub.getClubGrip().getPrice();

                        }
                        else
                        {
                            System.out.println("Club has Correct Size Grip");
                        }

                    }
                    //check club length
                    int len = checkLength(currClub.getClubShaftLength(),currentOrder.getWristToFloor(),currClub.getClubHeadEnum());
                    if(currClub.getClubShaftLength() != len)
                    {
                        currClub.setClubLength(len);
                        budget+= currClub.getClubShaft().getPrice();
                    }

                    //check flext

                }
            }
        }
        return budget;
    }


}
