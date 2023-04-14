package staff;

import enums.Enums;

public class Fitter extends Staff{
    public Fitter()
    {
        super();
        payRate = ((Math.random() * (21.5 - 18.3)) + 18.3);
        type = Enums.StaffType.Fitter;
    }
    @Override
    public void pay() {
        balance += payRate;
    }
}
