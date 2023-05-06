package decorator;

import goods.Item;

public class Warranty extends Addons{
    protected Item copyClub;
    public Warranty(Item club)
    {
        copyClub = club;
    }

    @Override
    public Double getPrice() {
        return copyClub.getInitialPrice() + (copyClub.getInitialPrice()* 0.3);
    }

    public void generatePrice() {
        System.out.println("This should not happen here");
    }

    @Override
    public void generateModels() {
        System.out.println("This should not happen here");
    }

    @Override
    public String getModel() {
        return copyClub.getModel() + "+ [2-year warranty]";
    }
}
