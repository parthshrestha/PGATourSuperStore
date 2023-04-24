package Activity;

import Observer.Publisher;
import buyer.Customer;
import enums.Enums;
import goods.Club;
import goods.Grip;
import goods.Item;
import goods.Shaft;
import staff.ServicePerson;
import staff.Staff;


import java.util.ArrayList;
import java.util.Random;


public class Service implements Publisher {
    protected double shaftRepair = 17.99;
    protected double reGrip = 2.99;
    protected String pgaStorenumber;
    public Service(String name)
    {
        pgaStorenumber = name;
    }
    public double service(ArrayList<Customer> serviceOrders, ArrayList<Staff> employees, double budget) {

        for (int i = 0; i < serviceOrders.size(); i++)
        {
            Random rand = new Random();
            Customer currentOrder = serviceOrders.get(i);// fifo
            int employeeSelection = rand.nextInt(employees.size());
            ServicePerson employee = (ServicePerson)employees.get(employeeSelection);
            if(employee.getNumServiced() <=5)//max per day limit
            {
                for(int j = 0; j < 14;j++)//all clubs
                {
                    Club currClub = currentOrder.getClubAt(j);
                    ArrayList<Enums.ServiceType> jobList = currentOrder.getServices(currClub.getClubHead());
                    for(int k =0; k < jobList.size(); k++)//do services
                    {
                        if(jobList.get(k) == Enums.ServiceType.REGRIP)
                        {
                            budget -= reGripCost;
                            //set new grip
                            Grip newGrip = new Grip(currClub.getClubGripSize());
                        }
                        else if(jobList.get(k) == Enums.ServiceType.RESHAFT)
                        {
                            budget -= shaftRepairCost;
                            //set new shaft old grip
                            Shaft newShaft = new Shaft(currClub.getClubShaftFlex(), currClub.getClubShaftLength());//same specs
                        }
                        else//none
                        {
                            System.out.println(currClub.getClubHead() +" Does not need any additional repair");
                        }
                    }
                    //after doing all jobs change the condition to better or worse or the same
                    if(currClub.getCondition() == Enums.Condition.BROKEN)
                    {
                        double chance = rand.nextDouble(); //random number 0-1
                        if (chance < 0.6) // 60% chance
                        {
                            currClub.setCondition(Enums.Condition.PREOWNED);
                        }
                        else if ( chance >=0.6 &&  chance < 0.7 ) { // 10% chance
                            currClub.setCondition(Enums.Condition.BROKEN);
                        }
                        else // 30% chance
                        {
                            currClub.setCondition(Enums.Condition.PERFECT);
                        }
                    }

                }
                employee.incrementNumServiced();
            }
            else{
                System.out.println("Service member has already serviced 5 orders");
            }


        }
        System.out.println("finished servicing");
      return budget;
    }

}
