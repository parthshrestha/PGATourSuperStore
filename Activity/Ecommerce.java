package Activity;

import enums.Enums;
import goods.Item;
import map.Map;
import searchAlgorithm.SearchAlgorithm;

import java.lang.reflect.Array;
import java.util.*;
public class Ecommerce {
    // Fields
    private ArrayList<int []> goals; // Set an array of goals
    private int[] coordGoal;
    private int[] coordStart;
    private map.Map map;
    private SearchAlgorithm algorithm;
    private HashMap<int[], List<Item>> goalCoordMap;
    private final int xMin = 0;
    private final int xMax = 20;
    private final int yMin = 0;
    private final int yMax = 10;
    private final Random rand = new Random();
    private ArrayList<int[]> availCoordinates;
    private ArrayList<List<int[]>> queueSolutions;
    // Section for availCoordinates by x axis
    private HashMap<Integer, ArrayList<int[]>> sectionCoord;

    // Constructor
    public Ecommerce(){
        // So the idea here is that the ecommerce activity is going to setup the search algorithm it's going to use while also giving information where each location is at
        // In order to make sure that walls are not overriden, we're going to set an arraylist of avail coordinates
        // Each item is going to be assigned to one of the coordinates (coordinates, item) map
        // If coordinate is visited and set as a solution, you would print out the item name being potential taken

        // Common setup
        coordStart = new int[] {0,0}; // Always make start at 0,0
        coordGoal = new int[] {0,1}; // Make sure goal is something other than 0,0 (this can change while the goal is being updated)
        goals = new ArrayList<int[]>();
        algorithm = null;
        goalCoordMap = new HashMap<>(); // This is the hashmap between int and Item
        availCoordinates = new ArrayList<>();
        queueSolutions = new ArrayList<>(); // Create this queue to pop solution paths left and right
        sectionCoord = new HashMap<>();

        // We'll make all walls to be in an odd number (this can change once we setup various maps)
        map = new map.Map(xMin,xMax,yMin,yMax);
        for(int i = 0; i < xMax; i++){
            if(i%2 !=0){
                map.setWallColumn(i, true);
            }
        }

        // Find what are the available Coordinates for items
        setAvailCoordinates();

        // Set initial to always at 0,0
        map.setObject(0,0, Enums.MapItems.INITIAL);

        // We're going to set all items to a random location since we assume that each time this is ran, it's a new place for the items

    }

    public Ecommerce(ArrayList<Item> input){
        // So the idea here is that the ecommerce activity is going to setup the search algorithm it's going to use while also giving information where each location is at
        // In order to make sure that walls are not overriden, we're going to set an arraylist of avail coordinates
        // Each item is going to be assigned to one of the coordinates (coordinates, item) map
        // If coordinate is visited and set as a solution, you would print out the item name being potential taken

        // Common setup
        coordStart = new int[] {0,0}; // Always make start at 0,0
        coordGoal = new int[] {0,1}; // Make sure goal is something other than 0,0 (this can change while the goal is being updated)
        goals = new ArrayList<int[]>();
        algorithm = null;
        goalCoordMap = new HashMap<>(); // This is the hashmap between int and Item
        availCoordinates = new ArrayList<>();
        queueSolutions = new ArrayList<>(); // Create this queue to pop solution paths left and right
        sectionCoord = new HashMap<>();

        // We'll make all walls to be in an odd number (this can change once we setup various maps)
        map = new map.Map(xMin,xMax,yMin,yMax);
        for(int i = 0; i < xMax; i++){
            if(i%2 !=0){
                map.setWallColumn(i, true);
            }
        }

        // Find what are the available Coordinates for items
        setAvailCoordinates();

        // Set initial to always at 0,0
//        map.setObject(0,0, Enums.MapItems.INITIAL);

        // We're going to set all items to a random location since we assume that each time this is ran, it's a new place for the items
        // Since this one has a parameter, make sure to add goals with all the items in parameter
        for(Item x : input){
            addGoals(x);
        }

    }

    public void printMap(){
        this.map.visualize();
    }


    // Function

    public void setMap(Map input){
        this.map = input;
    } // Set a map if there's a different map of walls being setup

    public void setAvailCoordinates(){ // This is just strictly for this map (can change according to the map, we can implement this to map class)
        if(availCoordinates.size() > 0){
            availCoordinates.clear();
            sectionCoord.clear();
        }

        for(int x = 0; x < xMax; x++){
            sectionCoord.put(x, new ArrayList<int[]>());
            for(int y = 1; y < yMax-1; y++){
                if(map.getValueCoordinate(x,y) != Enums.MapItems.WALL && map.getValueCoordinate(x,y) != Enums.MapItems.INITIAL){
                    int[] temp = new int[]{x,y};
                    sectionCoord.get(x).add(temp);
                    availCoordinates.add(temp);
                }
            }
        }
//        sectionCoord.get(0).remove(0); // Removes (0,0)
//        availCoordinates.remove(0); // This removes (0,0) out of the equation


//        for(int[] x : availCoordinates){
//            System.out.println("(" + x[0] + ", " + x[1] + ")");
//        }

//        System.out.println("Reseted Avail Coordinates");

    }

    public void addGoals(Item input){
        // Add goals into coordinate pair which will be used to solve and most likely be in which part of the map
        int[] randomCoordinate = availCoordinates.get(rand.nextInt(availCoordinates.size()));


//        System.out.println(goalCoordMap.get(randomCoordinate).size());

        // This is for putting goals according to their types
        Enums.Goods itemType = input.getType();
        List<int[]> tempArrayList = new ArrayList<>();
        switch(itemType){
            case Accessory:
                tempArrayList = sectionCoord.get(0);
                break;
            case Bag:
                tempArrayList = sectionCoord.get(2);
                break;
            case Balls:
                tempArrayList = sectionCoord.get(4);
                break;
            case Clothing:
                tempArrayList = sectionCoord.get(6);
                break;
            case Club:
                tempArrayList = sectionCoord.get(8);
                break;
            case Grip:
                tempArrayList = sectionCoord.get(10);
                break;
            case Racket:
                tempArrayList = sectionCoord.get(12);
                break;
            case Shaft:
                tempArrayList = sectionCoord.get(14);
                break;
            case Shoes:
                tempArrayList = sectionCoord.get(16);
                break;
            case Glove:
                tempArrayList = sectionCoord.get(18);
                break;
        }
        randomCoordinate = tempArrayList.get(rand.nextInt(tempArrayList.size()));

        if(goalCoordMap.containsKey(randomCoordinate)){
            goalCoordMap.get(randomCoordinate).add(input);
        } else {
            goalCoordMap.put(randomCoordinate, new ArrayList<>());
            goalCoordMap.get(randomCoordinate).add(input);
        }

    }
    public void resetGoals(){
        goalCoordMap.clear();
        map.resetMapSolution();
    }

    public void clearMap(){
        map.resetMapSolution();
    }

    public void setAlgorithm(SearchAlgorithm input){ // Strategy Pattern
        algorithm = input;
    }

    public void switchGoals(int[] input){
        coordStart[0] = coordGoal[0];
        coordStart[1] = coordGoal[1];

        coordGoal[0] = input[0];
        coordGoal[1] = input[1];
    }

    // Random Number Generator for Even number only (to make sure it doesn't stick to a wall)
    public int[] randomCoordinateGeneratorItem(){
        int startRange = xMin;
        int endRange = xMax - 1;
        int randomX = xMin + rand.nextInt((endRange - startRange)/2) * 2;
        int randomY = rand.nextInt(yMax);
        return new int[] {randomX, randomY};
    }

    // add a coordinate - item HashMap pair
    public void addGoalCordPair(){
        int[] tempPair = randomCoordinateGeneratorItem();

    }

    // return all goals
    public ArrayList<int[]> getGoals(){
        return this.goals;
    }

    public List<int[]> runSearch(int[] currGoal){
        return this.algorithm.findPath(this.map, coordStart, currGoal);
    }

    public void findPaths(){
        // First get a list of possible coordinates to check as a solution
        coordStart = new int[] {0,0};
        map.setObject(coordStart[0], coordStart[1], Enums.MapItems.INITIAL);
        queueSolutions.clear(); // Clear all queue for new solutions
        int[] tempGoal;


        for(int i = 0; i < availCoordinates.size(); i++){
            List<Item> currentItems = goalCoordMap.get(availCoordinates.get(i));
            if(currentItems != null && currentItems.size() > 0){
                goals.add(availCoordinates.get(i));
            }
        }

        // While the list of goals are not finished, keep running this algorithm
        while(goals.size() > 0){
            int bestIndex = 0;
            int pathCost = 999999;
            List<int[]> tempSolutionPath = new ArrayList<>();
            List<int[]> bestSolutionPath = new ArrayList<>();

            for(int i = 0; i < goals.size(); i++){
                tempGoal = goals.get(i);

                // Find the temporary solution
                tempSolutionPath = runSearch(tempGoal);

                // Now check if solution is optimal or not through this
                if(tempSolutionPath.size() < pathCost){
                    bestIndex = i;
                    bestSolutionPath = tempSolutionPath;
                    pathCost = tempSolutionPath.size();
                }

//                if(tempSolutionPath.size() == pathCost){ // In the case they are equal, check for the amount of items they can pick up
//                    int[] bestGoalKey = goals.get(bestIndex);
//                    if(goalCoordMap.get(bestGoalKey).size() <= goalCoordMap.get(tempGoal).size()){
//                        bestIndex = i;
//                        bestSolutionPath = tempSolutionPath;
//                        pathCost = tempSolutionPath.size();
//                    }
//                }
            }

//            map.setObject(coordStart[0], coordStart[1], Enums.MapItems.INITIAL);

            queueSolutions.add(bestSolutionPath);

            for(int i = 1; i < bestSolutionPath.size(); i++){
                int[] temp = bestSolutionPath.get(i);
                map.setSolution(temp[0], temp[1]);
            }

//            map.visualize(); // Visualize map after solution
//            // Print items that were picked up
//            System.out.println("Got these items: ");
//            int counter = 1;
//            // Now print what items are picked up
//            for(Item x : goalCoordMap.get(goals.get(bestIndex))){
//                System.out.println(counter + ". " + x.getBrand() + " " + x.getModel() + " " +  x.getType());
//                counter++;
//            }
//            System.out.println();
//            System.out.println();
//            map.resetMapSolution();
            coordStart = goals.get(bestIndex);
            goals.remove(bestIndex);
        }

        // If everything is done, return to the start

        tempGoal = new int[] {0,0};
//        map.setObject(coordStart[0], coordStart[1], Enums.MapItems.INITIAL);
        List<int[]> finalSolution = new ArrayList<>();
        finalSolution = runSearch(tempGoal);
//
        for(int i = 0; i < finalSolution.size()-1; i++){
            int[] temp = finalSolution.get(i);
            map.setSolution(temp[0], temp[1]);
        }

        map.visualize();
//        map.resetMapSolution();


        //
    }

    public void resetMap(){
        this.map.resetMapSolution();
    }

    public Map getMap(){
        return this.map;
    }
}
