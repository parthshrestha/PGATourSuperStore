package factory;

import enums.Enums;
import goods.*;

import java.util.ArrayList;

public class GoodsFactory {
    public Item getInstanceItem(ArrayList<Item> itemInventory, ArrayList<Item> soldItemInventory, Enums.Goods type)
    {
        if (type == Enums.Goods.Glove)
        {return new Glove();}
        else if (type == Enums.Goods.Clothing)
        {return new Clothing();}
        else if (type == Enums.Goods.Shaft)
        {return new Shaft();}
        else if (type == Enums.Goods.Racket)
        {return new Racket();}
        else if (type == Enums.Goods.Accessory)
        {return new Accessory();}
        else if (type == Enums.Goods.Bag)
        {return new Bag();}
        else if (type == Enums.Goods.Balls)
        {return new Balls();}
        else if (type == Enums.Goods.Club)
        {return new Club();}
        else if (type == Enums.Goods.Grip)
        {return new Grip();}
        else //shoes
        {return new Shoes();}


    }
}
