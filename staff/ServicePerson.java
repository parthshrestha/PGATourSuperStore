package staff;

import enums.Enums;
import goods.ExperienceLevelService;
import goods.Experienced;
import goods.Newbie;
import goods.Pro;

public class ServicePerson extends Staff{
    protected int numServiced;

    public ExperienceLevelService serviceType;
    public ServicePerson()
    {
        super();
        payRate = randPayRate(19.5, 17.3);
        numServiced = 0;
        type = Enums.StaffType.ServicePerson;
        serviceType = generateServiceType();
    }
    @Override
    public void pay() {
        balance += payRate;
    }
    public int getNumServiced()
    {return numServiced;}
    @Override
    public void incrementDaysWorked()
    {
        daysWorked++;
        serviceType = generateServiceType();
    }
    public void incrementNumServiced()
    {
        numServiced++;
    }
    public ExperienceLevelService generateServiceType()
    {
        if(this.daysWorked < 7) //1 week
        {
            return new Newbie();
        }
        else if(this.daysWorked < 14)//2 weeks
        {
            return new Newbie();
        }
        else //2weeks+
        {
            return new Pro();
        }
    }
    public void useStrategy()
    {}
}
