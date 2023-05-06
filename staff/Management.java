package staff;

import enums.Enums;

public class Management extends Staff{

    public Management()
    {
        super();
        payRate = ((Math.random() * (35.5 - 26.3)) + 26.3);
        type = Enums.StaffType.Management;
    }
    @Override
    public double pay() {
        balance += payRate;
        daysWorked++;
        return payRate;
    }
}
