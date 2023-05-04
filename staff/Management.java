package staff;

import enums.Enums;

public class Management extends Staff{

    public Management()
    {
        super();
        payRate = randPayRate(35.5, 26.3);
        type = Enums.StaffType.Management;
    }
    @Override
    public void pay() {
        balance += payRate;
    }
}
