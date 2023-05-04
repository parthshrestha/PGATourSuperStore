package staff;

import enums.Enums;

public class Logistics extends Staff{
    public Logistics()
    {
        super();
        payRate = randPayRate(22.5, 18.3);
        type = Enums.StaffType.Logistic;
    }
    @Override
    public void pay() {
        balance += payRate;
    }
}
