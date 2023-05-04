package map;

import enums.Enums;

public class SolutionPath implements MapItem {
    private Enums.MapItems mapItemType;

    public SolutionPath(){
        mapItemType = Enums.MapItems.SOLUTION;
    }

    @Override
    public Enums.MapItems getMapItemType() {
        return mapItemType;
    }
}
