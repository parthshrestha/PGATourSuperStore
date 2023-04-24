package staff;

import enums.Enums;

public class ServicePerson extends Staff{
    protected int numServiced;

    public ServicePerson()
    {
        super();
        payRate = ((Math.random() * (19.5 - 17.3)) + 17.3);
        numServiced = 0;
        type = Enums.StaffType.ServicePerson;
    }
    @Override
    public void pay() {
        balance += payRate;
    }
}
