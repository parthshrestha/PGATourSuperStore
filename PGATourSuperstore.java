import factory.GoodsFactory;
import factory.StaffFactory;
import goods.Club;
import goods.Item;
import staff.SoftGood;
import staff.Staff;
import strat.ReGripStrat;

import java.util.ArrayList;

public class PGATourSuperstore {
ArrayList<Staff> staff;
ArrayList<Item> goods;
GoodsFactory good;
StaffFactory staffCreate;

void regrip()
{
    ReGripStrat strategy = new ReGripStrat() {
        @Override
        public void ReGrip(Club club) {

        }
    };
}
    void opening()
    {}
    void Service()
    {

    }
    void pickupEcom()
    {}
    void Selling()
    {

    }
    void fitting()
    {}
    void ending()
    {}


}
