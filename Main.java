import enums.Enums;
import factory.GoodsFactory;
import goods.*;
import javafx.stage.Stage;
import staff.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int numPga = 0;
        ArrayList<PGATourSuperstore> pga = new ArrayList<PGATourSuperstore>();
        pga.add(new PGATourSuperstore("FNCD"+String.valueOf(numPga)));

        Simulation sim = new Simulation(pga.get(0));
        sim.run();

        ManagerMode manager = new ManagerMode();
        manager.openWindow(pga.get(0));
    }
}