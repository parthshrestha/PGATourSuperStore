package goods;

import enums.Enums;

import java.util.Random;

public abstract class Item {
    protected int upc;//uni
    protected String brand;
    protected String model;
    protected Enums.Goods type;
    protected Double initialPrice;
    protected Double price;//final price
    public Item()//create a database for all items correlating to the upc
    {
        Random rand = new Random();
        //attempt to create a unique upc number
        upc = rand.nextInt(100000000) + rand.nextInt(1000000)+ rand.nextInt(10000)+ rand.nextInt(100)+ rand.nextInt(10);
    }
    //getters
    public int getUpc()
    {
        return upc;
    }
    public String getBrand()
    {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Enums.Goods getType() {
        return type;
    }

    public Double getInitialPrice() {
        return initialPrice;
    }

    public Double getPrice() {
        return price;
    }
    //setters

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(Enums.Goods type) {
        this.type = type;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    //METHODS
    public abstract void generatePrice();
    public abstract void generateModels();

}
