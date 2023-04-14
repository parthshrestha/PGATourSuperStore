package staff;



import enums.Enums;

public class SoftGood extends Staff{

    public SoftGood()
    {
        super();
        payRate = ((Math.random() * (18.5 - 15.3)) + 15.3);
        type = Enums.StaffType.SoftGood;
    }
    @Override
    public void pay() {
        balance += payRate;
    }
}
