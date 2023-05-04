package map;
// To make it possible for A*Searh, each coordinates need to be able to have a value inside of them
// We'll say that
// 0 = possible place to pass
// 1 = objectives
// -100 = not possible

import enums.Enums;
import javafx.scene.paint.Color;

public class Map {
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    private MapItem[][] coordinates;

    public Map(int xMin, int xMax, int yMin, int yMax){
        this.xMin = xMin;
        this.xMax= xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.coordinates = new MapItem[xMax][yMax]; // We'll assume all paths that are available are false while walls are set to true
        fillPath();
    }

    public Color getRightColor(Enums.MapItems tempType){
        if(tempType == Enums.MapItems.WALL){
            return Color.RED;
        } else if(tempType == Enums.MapItems.PATH){
            return Color.BLUE;
        } else if(tempType == Enums.MapItems.ITEMS){
            return Color.PURPLE;
        } else if (tempType == Enums.MapItems.SOLUTION){
            return Color.YELLOW;
        } else if(tempType == Enums.MapItems.INITIAL){
            return Color.GREEN;
        } else {
            return null;
        }

    }

    public void fillPath(){
        for (int y = 0; y < coordinates.length; y++) {
            for (int x = 0; x < coordinates[0].length; x++) {
                coordinates[y][x] = new Path();
            }
        }
    }

    public boolean isValidCoordinate(int x, int y) { // Checks if the coordinate is in scope
        int adjustedX = x - xMin + 1;
        int adjustedY = y - yMin + 1;
        if (x < 0 || y < 0 || y >= coordinates[0].length || x >= coordinates.length) {
            return false;
        }
        return true; // remember paths that are available are false therefore to check if it's valid, return !false or true
    }

    public boolean isPath(int x, int y){
        int adjustedX = x - xMin + 1;
        int adjustedY = y - yMin + 1;

        if(coordinates[x][y].getMapItemType() == Enums.MapItems.WALL){
            return false;
        } else {
            return true;
        }

    }

    public Enums.MapItems getValueCoordinate(int x, int y){
        int adjustedX = x - xMin + 1;
        int adjustedY = y - yMin + 1;
        return coordinates[x][y].getMapItemType();
    }

    public void visualize() {
        String ANSI_GREEN = "\033[0;32m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_YELLOW = "\033[0;33m";
        for (int y = 0; y < coordinates[0].length; y++) {
            for (int x = 0; x < coordinates.length; x++) {
                Enums.MapItems tempType = coordinates[x][y].getMapItemType();
                if(tempType == Enums.MapItems.WALL){
                    System.out.print(ANSI_RED + "[W]" + ANSI_RESET);
                } else if(tempType == Enums.MapItems.PATH){
                    System.out.print("[O]");
                } else if(tempType == Enums.MapItems.ITEMS){
                    System.out.print("[I]");
                } else if (tempType == Enums.MapItems.SOLUTION){
                    System.out.print(ANSI_YELLOW + "[X]" + ANSI_RESET);
                } else if(tempType == Enums.MapItems.INITIAL){
                    System.out.print(ANSI_GREEN + "[S]" + ANSI_RESET);
                }
            }
            System.out.println();
        }

        System.out.println();
    }

    public void setWall(int x, int y){
        coordinates[x][y] = new Wall();
    }

    public void setWallColumn(int x, boolean reverse){
        if(!reverse) {
            for (int y = 1; y < coordinates[0].length - 1; y++) {
                setObject(x, y, Enums.MapItems.WALL);
            }
        } else {
            for (int y = coordinates[0].length-2; y > 0; y--) {
                setObject(x, y, Enums.MapItems.WALL);
            }
        }

    }

    public void setSolution(int x, int y){
        coordinates[x][y] = new SolutionPath();
    }

    public void setObject(int x, int y, Enums.MapItems input){
        switch(input){
            case WALL:
                coordinates[x][y] = new Wall();
//                System.out.println("WALL");
                break;
            case PATH:
                coordinates[x][y] = new Path();
//                System.out.println("PATH");
                break;
            case ITEMS:
//                System.out.println("ITEM");
                break;
            case INITIAL:
                coordinates[x][y] = new Initial();
//                System.out.println("INITIAL");
                break;
            case SOLUTION:
                coordinates[x][y] = new SolutionPath();
//                System.out.println("SOLUTION");
                break;
        }

    }

    public void resetMapSolution(){
        for (int y = 0; y < coordinates[0].length; y++) {
            for (int x = 0; x < coordinates.length; x++) {
                if(coordinates[x][y].getMapItemType() == Enums.MapItems.SOLUTION || coordinates[x][y].getMapItemType() == Enums.MapItems.INITIAL){
                    coordinates[x][y] = new Path();
                }
            }
        }
    }


}
