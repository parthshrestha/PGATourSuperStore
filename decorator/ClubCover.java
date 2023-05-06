package decorator;

import goods.Item;

public class ClubCover extends Addons{
    protected Item copyClub;
    public ClubCover(Item club)
    {
        copyClub = club;
    }

    @Override
    public Double getPrice() {
        return copyClub.getInitialPrice() + (copyClub.getInitialPrice()* 0.1);
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
        return copyClub.getModel() + "+ [Custom Head Cover]";
    }
}
