package goods;

import enums.Enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Clothing extends Item{
    HashMap<String, ArrayList<String>> brandModel = new HashMap<>();
    protected Enums.Gender gender;
    String[] clothingBrands={"Chubbies","FootJoy","Adidas","Nike","Puma", "Travis Mathews","Barstool Golf","Jamie Sadock","Lilly Pulitzer","Tail"};
    protected String size;
    String[] colors =  {"Red","Navy","Beige","White", "Black","Green","Yellow","Orange","Yellow"};
    ArrayList<String>[] clothingModels = new ArrayList[clothingBrands.length];
    String color;
    String[] sizes = {"S","M","L","XL","XXL"};
    public Clothing()
    {
        super();
        generateModels();
        Random rand = new Random();
        color = colors[rand.nextInt(colors.length)];// pick a random color for the shirt

        int choiceBrand = rand.nextInt(clothingBrands.length);
        brand = clothingBrands[choiceBrand];

        int choiceModels = rand.nextInt(brandModel.get(brand).size());
        model = brandModel.get(brand).get(choiceModels);

//        int choiceBrand = rand.nextInt(clothingBrands.length);
//        int choiceModels = rand.nextInt(clothingModels[choiceBrand].size());
//        brand = clothingBrands[choiceBrand];
//        model = clothingModels[choiceBrand].get(choiceModels);
        type = Enums.Goods.Clothing;
        size = sizes[rand.nextInt(sizes.length)];
        generatePrice();
        price = initialPrice;
    }
    public Clothing(String _size)
    {
        super();
        generateModels();
        Random rand = new Random();
        color = colors[rand.nextInt(colors.length)];// pick a random color for the shirt

        int choiceBrand = rand.nextInt(clothingBrands.length);
        brand = clothingBrands[choiceBrand];

        int choiceModels = rand.nextInt(brandModel.get(brand).size());
        model = brandModel.get(brand).get(choiceModels);

//        int choiceBrand = rand.nextInt(clothingBrands.length);
//        int choiceModels = rand.nextInt(clothingModels[choiceBrand].size());
//        brand = clothingBrands[choiceBrand];
//        model = clothingModels[choiceBrand].get(choiceModels);
        type = Enums.Goods.Clothing;
        size = _size;
        generatePrice();
        price = initialPrice;
    }
    public String getSize()
    {
        return size;
    }

    @Override
    public void generatePrice() {
        initialPrice = ((Math.random() * (100.0 - 20.0)) + 20.0);
    }

    @Override
    public void generateModels() {
//        clothingModels[0].add("Polo");
//
//        //Nike
//        clothingModels[1].add("Polo");
//        clothingModels[2].add("Polo");{"Chubbies","FootJoy","Adidas","Nike","Puma", "Travis Mathews","Barstool Golf","Jamie Sadock","Lilly Pulitzer","Tail"}

        brandModel.put(clothingBrands[0], new ArrayList<>(Arrays.asList("Performance Polo", "Quarter-Zip Pull over","Core 8-Inch Everywear Short","Compression Lined Short","Stretch 6 Short","Everywear Pant","Schwoodie Hoodie")));
        brandModel.put(clothingBrands[1], new ArrayList<>(Arrays.asList("Self Collar Polo","HydroLite Rain Jacket","Performance 9.5 Knit Short","Athletic Fit Pant","Drop Needle Half-Zip Pullover")));
        brandModel.put(clothingBrands[2], new ArrayList<>(Arrays.asList("DWR 1/4-Zip Sweatshirt","Ultimate365 10in Pants","Core Crew Sweatshirt","Jacquard Polo Shirt","Go-To Hybrid 9in Shorts","Adicross Hooded","Quarter Zip Pull Over")));
        brandModel.put(clothingBrands[3], new ArrayList<>(Arrays.asList("Dri-FIT Victory Pants","Dri-FIT Shorts","Dri-FIT Victory Polo","Therma-FIT Half-Zip Vest","Dri-FIT Hoodie","Dri-FIT Vapor Printed Polo","Storm-FIT ADV Full-Zip Jacket","Dri-FIT UV Ace 17in Skirt","Victory 12in Skirt"," Dri-FIT Tennis Tank")));
        brandModel.put(clothingBrands[4], new ArrayList<>(Arrays.asList("Dealer Short 10in","Caddie Stripe Polo","Jackpot Shorts 2.0","PWRSHAPE Love 16in Skirt","Mattr Track Polo","Jackpot Pants")));
        brandModel.put(clothingBrands[5], new ArrayList<>(Arrays.asList("Skyview Hoodie","Topflight Hoodie","Heater Vest","Rockies Road T-Shirt","Starnes Short","Rattler Polo","Woodland Polo")));
        brandModel.put(clothingBrands[6], new ArrayList<>(Arrays.asList("Packable Windbreaker","Camo Jacquard Polo","Crossed Tees Elite Quarter Zip","Floral Polo","Apex Pants","Camo Qtr Zip","Tiger Tee")));
        brandModel.put(clothingBrands[7], new ArrayList<>(Arrays.asList("Short Sleeve Top","Sleeveless Top","Quarter Zip Pull Over","Jungle Sunrise Sleeveless Leopard Dress","Savannah Tropics 16in Skort","Cooltrex Grommet Pleated 16in Skort","Airwear Hybrid 17.5in Skort","Airwear 13.5in Knee Capri","Skinnylicious 28.5in Ankle Pant"," Long Sleeve V Neck")));
        brandModel.put(clothingBrands[8], new ArrayList<>(Arrays.asList("Frida Puff Sleeve Polo Shirt","Imara Pique Sleeveless Polo Shirt","Skipper Floral Quarter Zip Popover","Pique Sleeveless Dress","Pique 17in Skort")));
        brandModel.put(clothingBrands[9], new ArrayList<>(Arrays.asList("Jossie Colorblock Quarter Zip Pull Over","Zee Fun in the Sun Sleeveless Top","Larimar Daffodil Wave 35in Dress","Arabella Pleated Hem 17in Skort","Tokyo Solid Bi-Stretch 6in Short","Isma Oasis Palms 18in Pull-On Skort")));


    }
}
