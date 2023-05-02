package Activity;

import buyer.Customer;
import enums.Enums;
import goods.Club;
import staff.ServicePerson;
import staff.Staff;

import java.util.ArrayList;
import java.util.Random;

public class Fitting {
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
            else{return true;}
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
        else
        {
            //do someting acording to chart
            len = 0;
        }
        return len;
    }
    public double clubFitting(ArrayList<Customer> serviceOrders, ArrayList<Staff> employees, double budget)
    {
        for (int i = 0; i < serviceOrders.size(); i++)
        {
            Random rand = new Random();
            Customer currentOrder = serviceOrders.get(i);// fifo
            int employeeSelection = rand.nextInt(employees.size());
            ServicePerson employee = (ServicePerson) employees.get(employeeSelection);
            if(currentOrder.getIntent() == Enums.CustomerIntent.FITTING)
            {
                for (int j = 0; j < 14; j++)//all clubs
                {
                    Club currClub = currentOrder.getClubAt(j);
                    //check fitting
                    //check grip size

                    //check club length
                   currClub.setClubLength(checkLength(currClub.getClubShaftLength(),currentOrder.getWristToFloor(),currClub.getClubHeadEnum()));
                    //check flext
                }
            }
        }
        return budget;
    }


}
