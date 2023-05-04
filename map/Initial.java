package map;

import enums.Enums;

public class Initial implements MapItem{
    private Enums.MapItems type;

    public Initial(){
        this.type = Enums.MapItems.INITIAL;
    }

    @Override
    public Enums.MapItems getMapItemType() {
        return type;
    }
}
