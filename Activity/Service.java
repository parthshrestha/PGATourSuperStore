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
    protected double shaftRepairCost = 17.99;
    protected double reGripCost = 2.99;
    protected String pgaStorenumber;
    public Service(String name)
    {
        pgaStorenumber = name;
    }
    public double service(ArrayList<Customer> serviceOrders, ArrayList<Staff> employees, double budget) {
        System.out.println("Now Servicing all orders");
        for (int i = 0; i < serviceOrders.size(); i++)
        {
            Random rand = new Random();
            Customer currentOrder = serviceOrders.get(i);// fifo
            System.out.println("Customer: "+ currentOrder.name+" is in for "+ currentOrder.getIntent());
            if(currentOrder.getIntent() == Enums.CustomerIntent.SERVICE) {
                int employeeSelection = rand.nextInt(employees.size());
                ServicePerson employee = (ServicePerson) employees.get(employeeSelection);
                if (employee.getNumServiced() <= 5)//max per day limit
                {
                    for (int j = 0; j < 14; j++)//all clubs
                    {
                        Club currClub = currentOrder.getClubAt(j);
                        System.out.println(currClub.getClubHead() + "is in: "+ currClub.getCondition()+ " Condition");
                        ArrayList<Enums.ServiceType> jobList = currentOrder.getServices(currClub.getClubHead());
                        for (int k = 0; k < jobList.size(); k++)//do services
                        {   double serviceCost = 0.0;
                            if (jobList.get(k) == Enums.ServiceType.REGRIP) {
                                serviceCost = reGripCost;
                                budget += reGripCost ;
                                //set new grip
                                Grip newGrip = new Grip(currClub.getClubGripSize());
                                budget += newGrip.getPrice();
                                currClub.setNewGrip(newGrip);
                                System.out.println("New grip is: "+ (currClub.getClubGrip()).getBrand()+" "+(currClub.getClubGrip()).getModel()+ "Size: "+(currClub.getClubGrip()).getSize() );
                                notifySubscriber("log","New grip is: "+ (currClub.getClubGrip()).getBrand()+" "+(currClub.getClubGrip()).getModel()+ "Size: "+(currClub.getClubGrip()).getSize());
                            } else if (jobList.get(k) == Enums.ServiceType.RESHAFT) {
                                serviceCost = shaftRepairCost;
                                budget += shaftRepairCost;
                                //set new shaft old grip
                                Shaft newShaft = new Shaft(currClub.getClubShaftFlex(), currClub.getClubShaftLength());//same specs
                                budget += newShaft.getPrice();
                                currClub.setNewShaft(newShaft);
                                System.out.println("New Shaft is: "+ (currClub.getClubShaft()).getBrand()+" "+(currClub.getClubShaft()).getModel()+ "Flex: "+(currClub.getClubShaft()).getFlex() );
                                notifySubscriber("log",(currClub.getClubShaft()).getBrand()+" "+(currClub.getClubShaft()).getModel()+ "Flex: "+(currClub.getClubShaft()).getFlex());
                            } else//none
                            {
                                System.out.println(currClub.getClubHead() + " Does not need any additional repair");
                                notifySubscriber("log",currClub.getClubHead() + " Does not need any additional repair");
                            }
                            employee.serviceType.serviceNow(currClub);
                            if(currClub.getCondition() == Enums.Condition.PERFECT)
                            {

                                double bonus = 0.15 * serviceCost;
                                employee.setBonus(bonus);
                                budget-= bonus;
                                System.out.println(employee.getName() +" Earned bonus of: " + bonus);
                                notifySubscriber("log",employee.getName() +" Earned bonus of: " + bonus);
                                notifySubscriber("staff",String.valueOf(bonus));
                            }
                        }
                        //after doing all jobs change the condition to better or worse or the same
                        //call strategy method


                    }
                    employee.incrementNumServiced();
                } else {
                    System.out.println("Service member has already serviced 5 orders");
                    notifySubscriber("log","Service member has already serviced 5 orders");
                }


            }
        }
        System.out.println("finished servicing");
        notifySubscriber("log","finished servicing");
        return budget;
    }

}
