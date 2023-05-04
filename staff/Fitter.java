package staff;

import enums.Enums;

public class Fitter extends Staff{
    // Can only help 5 people per day
    public Fitter()
    {
        super();
        payRate = randPayRate(21.5, 18.3);
        type = Enums.StaffType.Fitter;
    }
    @Override
    public void pay() {
        balance += payRate;
    }
}
