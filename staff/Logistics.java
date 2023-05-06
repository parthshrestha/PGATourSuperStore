package staff;

import enums.Enums;

public class Logistics extends Staff{
    public Logistics()
    {
        super();
        payRate = ((Math.random() * (22.5 - 18.3)) + 18.3);
        type = Enums.StaffType.Logistic;
    }
    @Override
    public double pay() {
        balance += payRate;
        daysWorked++;
        return payRate;
    }
}
