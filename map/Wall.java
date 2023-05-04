package map;

import enums.Enums;

public class Wall implements MapItem {
    private Enums.MapItems type;

    public Wall(){
        this.type = Enums.MapItems.WALL;
    }

    @Override
    public Enums.MapItems getMapItemType() {
        return type;
    }
}
