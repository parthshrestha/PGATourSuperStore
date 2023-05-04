package staff;



import enums.Enums;

public class SoftGood extends Staff{

    public SoftGood()
    {
        super();
        payRate = randPayRate(18.5, 15.3);
        type = Enums.StaffType.SoftGood;
    }
    @Override
    public void pay() {
        balance += payRate;
    }
}
