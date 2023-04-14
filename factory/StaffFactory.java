package factory;

import enums.Enums;
import staff.*;

import java.util.ArrayList;

public class StaffFactory {
    public Staff getInstanceStaff(Enums.StaffType type)
    {
        if (type == Enums.StaffType.Fitter)
        {
            return new Fitter();
        }
        else if(type == Enums.StaffType.Logistic)
        {
            return new Logistics();
        }
        else if(type == Enums.StaffType.Management)
        {
            return new Management();
        }
        else if(type == Enums.StaffType.ServicePerson)
        {
            return new ServicePerson();
        }else //softgood
        {
            return new SoftGood();
        }

    }

}
