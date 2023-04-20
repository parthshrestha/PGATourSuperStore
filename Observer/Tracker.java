package Observer;

public class Tracker implements Subscriber{
    protected double budget =500000;
    protected double staffExpense = 0;
    protected double budget1 =500000;
    protected double staffExpense1 = 0;

    @Override
    public void update(String fncd,String type,String msg) {//part of the observer pattern


        if(fncd.equals("FNCD0"))
        {
            if(type == "staff")
            {
                staffExpense += Double.parseDouble(msg);
            }
            else if(type == "budgetAdd")
            {
                budget += Double.parseDouble(msg);
            }
            else if(type == "budgetSub")
            {
                budget -= Double.parseDouble(msg);
            }
        }
        else
        {
            if(type == "staff")
            {
                staffExpense1 += Double.parseDouble(msg);
            }
            else if(type == "budgetAdd")
            {
                budget1 += Double.parseDouble(msg);
            }
            else if(type == "budgetSub")
            {
                budget1 -= Double.parseDouble(msg);
            }
        }



    }
    public double getstaffEarnigs(String fncd)
    {
        if(fncd.equals("FNCD0"))
        {
            return staffExpense;
        }
        else
        {
            return staffExpense1;
        }
    }

    public void showReport()
    {
        System.out.println("This is the report0++++++++");
        System.out.println("So far Staff expense is: "+ staffExpense);
        System.out.println("Budget: "+ budget);
        System.out.println("This is the report1++++++++");
        System.out.println("So far Staff expense is: "+ staffExpense1);
        System.out.println("Budget: "+ budget1);


    }

}
