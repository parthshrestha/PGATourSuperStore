package staff;

import enums.Enums;

public class ServicePerson extends Staff{

    public ServicePerson()
    {
        super();
        payRate = ((Math.random() * (19.5 - 17.3)) + 17.3);
        type = Enums.StaffType.ServicePerson;
    }
    @Override
    public void pay() {
        balance += payRate;
    }
}
