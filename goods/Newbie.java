package goods;

import enums.Enums;
import goods.Club;


import java.util.Random;

public class Newbie implements ExperienceLevelService {
    Random rand = new Random();
    @Override
    public void serviceNow(Club club) {
        System.out.println("Newbie service techniques in use");
        if (club.getCondition() == Enums.Condition.BROKEN) {
            double chance = rand.nextDouble(); //random number 0-1
            if (chance < 0.5) // 50% chance
            {
                club.setCondition(Enums.Condition.PREOWNED);
                System.out.println(club.getClubHead() +" Club is now in Pre Owned condition");
            } else if (chance >= 0.5 && chance < 0.80) { // 30% chance
                club.setCondition(Enums.Condition.BROKEN);
                System.out.println(club.getClubHead() +" Club Stayed in Broken Condition");
            } else // 20% chance
            {
                club.setCondition(Enums.Condition.PERFECT);
                System.out.println(club.getClubHead() +" Club is now in Prefect condition");
            }
        }
        else if (club.getCondition() == Enums.Condition.PREOWNED) {
            double chance = rand.nextDouble(); //random number 0-1
            if (chance < 0.35) // 60% chance
            {
                club.setCondition(Enums.Condition.PREOWNED);
                System.out.println(club.getClubHead() +" Club stayed in Pre Owned condition");
            } else if (chance >= 0.35 && chance < 0.85) { // 50% chance
                club.setCondition(Enums.Condition.BROKEN);
                System.out.println(club.getClubHead() +" Club is now in Broken condition");
            } else // 15% chance
            {
                club.setCondition(Enums.Condition.PERFECT);
                System.out.println(club.getClubHead() +" Club is now in Perfect condition");
            }
        }
        else if (club.getCondition() == Enums.Condition.PERFECT) {
            double chance = rand.nextDouble(); //random number 0-1
            if (chance < 0.45) // 60% chance
            {
                club.setCondition(Enums.Condition.PREOWNED);
                System.out.println(club.getClubHead() +" Club is now in Pre Owned condition");
            } else if (chance >= 0.45 && chance < 0.55) { // 10% chance
                club.setCondition(Enums.Condition.BROKEN);
                System.out.println(club.getClubHead() +" Club is now in Broken condition");
            } else // 45% chance
            {
                club.setCondition(Enums.Condition.PERFECT);
                System.out.println(club.getClubHead() +" Club Stayed in Perfect condition");

            }
        }
    }
}
