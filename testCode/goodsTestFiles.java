package testCode;

import goods.*;

public class goodsTestFiles {
    public goodsTestFiles()
    {
        runTest();
    }
    public void runTest()
    {
        System.out.println("Balls");
        for(int i = 0; i< 10; i++) {
            Balls ball = new Balls();
            System.out.println(ball.getBrand() + " " + ball.getModel() + ": " + ball.getPrice());
        }
        System.out.println("Bag");
        for(int i = 0; i< 10; i++) {
            Bag bag = new Bag();
            System.out.println(bag.getBrand() + " " + bag.getModel() + ": " + bag.getPrice());
        }
        System.out.println("Clothing");
        for(int i = 0; i< 10; i++) {
            Clothing cloth = new Clothing();
            System.out.println(cloth.getBrand() + " " + cloth.getModel() +", "+ cloth.getSize()+ ": " + cloth.getPrice());
        }
        System.out.println("club");
        for(int i = 0; i< 10; i++) {
            Club club = new Club();
            System.out.println(club.getBrand() + " " + club.getModel() +", "+ club.getClubHead()+ ": " + club.getPrice());
        }
        System.out.println("Grip");
        for(int i = 0; i< 10; i++) {
            Grip grip = new Grip();
            System.out.println(grip.getBrand() + " " + grip.getModel() +", "+ grip.getSize()+ ": " + grip.getPrice());
        }
        System.out.println("Racket");
        for(int i = 0; i< 10; i++) {
            Racket racket = new Racket();
            System.out.println(racket.getBrand() + " " + racket.getModel() +", "+ ": " + racket.getPrice());
        }
        System.out.println("Shaft");
        for(int i = 0; i< 10; i++) {
            Shaft shaft = new Shaft();
            System.out.println(shaft.getBrand() + " " + shaft.getModel() +", "+ shaft.getFlex()+ ": " + shaft.getPrice());
        }
        System.out.println("Shaft");
        for(int i = 0; i< 10; i++) {
            Shaft shaft = new Shaft();
            System.out.println(shaft.getBrand() + " " + shaft.getModel() +", "+ shaft.getFlex()+ ": " + shaft.getPrice());
        }
        System.out.println("Accesories");
        for(int i = 0; i< 10; i++) {
            Accessory acc = new Accessory();
            System.out.println(acc.getBrand() + " " +acc.getModel() +", "+ ": " + acc.getPrice());
        }
        System.out.println("Shoes");
        for(int i = 0; i< 10; i++) {
            Shoes shoe = new Shoes();
            System.out.println(shoe.getBrand() + " " + shoe.getModel() +", "+ shoe.getSize()+ ": " + shoe.getPrice());
        }
    }
}