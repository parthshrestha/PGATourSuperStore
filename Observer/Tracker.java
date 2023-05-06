package Observer;

public class Tracker implements Subscriber{
    protected double budget =500000;
    protected double staffExpense = 0;
    protected double budget1 =500000;
    protected double staffExpense1 = 0;

    @Override
    public void update(String type,String msg) {//part of the observer pattern



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
    public double getstaffEarnigs()
    {
        return staffExpense;

    }

    public void showReport()
    {
        System.out.println("This is the report0++++++++");
        System.out.println("So far Staff expense is: "+ staffExpense);
        System.out.println("Budget: "+ budget);



    }

}
