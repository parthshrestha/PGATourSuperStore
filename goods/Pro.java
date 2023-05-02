package goods;

import enums.Enums;
import goods.Club;
import goods.ExperienceLevelService;

import java.util.Random;

public class Pro implements ExperienceLevelService {
    Random rand = new Random();
    @Override
    public void serviceNow(Club club) {
        System.out.println("Pro service techniques in use");
        if (club.getCondition() == Enums.Condition.BROKEN) {
            double chance = rand.nextDouble(); //random number 0-1
            if (chance < 0.4) // 40% chance
            {
                club.setCondition(Enums.Condition.PREOWNED);
                System.out.println(club.getClubHead() +" Club is now in Pre Owned condition");
            } else if (chance >= 0.4 && chance < 0.5) { // 40% chance
                club.setCondition(Enums.Condition.BROKEN);
                System.out.println(club.getClubHead() +" Club Stayed in Broken Condition");
            } else // 50% chance
            {
                club.setCondition(Enums.Condition.PERFECT);
                System.out.println(club.getClubHead() +" Club is now in Prefect condition");
            }
        }
        else if (club.getCondition() == Enums.Condition.PREOWNED) {
            double chance = rand.nextDouble(); //random number 0-1
            if (chance < 0.35) // 35% chance
            {
                club.setCondition(Enums.Condition.PREOWNED);
                System.out.println(club.getClubHead() +" Club stayed in Pre Owned condition");
            } else if (chance >= 0.35 && chance < 0.40) { // 50% chance
                club.setCondition(Enums.Condition.BROKEN);
                System.out.println(club.getClubHead() +" Club is now in Broken condition");
            } else // 60% chance
            {
                club.setCondition(Enums.Condition.PERFECT);
                System.out.println(club.getClubHead() +" Club is now in Perfect condition");
            }
        }
        else if (club.getCondition() == Enums.Condition.PERFECT) {
            double chance = rand.nextDouble(); //random number 0-1
            if (chance < 0.3) // 30% chance
            {
                club.setCondition(Enums.Condition.PREOWNED);
                System.out.println(club.getClubHead() +" Club is now in Pre Owned condition");
            } else if (chance >= 0.3 && chance < 0.31) { // 10% chance
                club.setCondition(Enums.Condition.BROKEN);
                System.out.println(club.getClubHead() +" Club is now in Broken condition");
            } else // 79% chance
            {
                club.setCondition(Enums.Condition.PERFECT);
                System.out.println(club.getClubHead() +" Club Stayed in Perfect condition");

            }
        }
    }
}
