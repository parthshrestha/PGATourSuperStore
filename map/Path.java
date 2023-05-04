package map;

import enums.Enums;

public class Path implements MapItem{
    private Enums.MapItems mapItemType;

    public Path(){
        mapItemType = Enums.MapItems.PATH;
    }

    @Override
    public Enums.MapItems getMapItemType() {
        return mapItemType;
    }
}
