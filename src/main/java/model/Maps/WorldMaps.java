package model.Maps;
/**
 * The WorldMaps class is responsible for storing and providing access to different levels in the game.
 * Each level is represented as a 2D array of integers, and the class offers methods to retrieve these levels.
 * @author Elvira Grubb
 */
public class WorldMaps {
    /**
     * Constructor that initializes the world map for a given world.
     * This constructor is meant to load the necessary files based on the world parameter.
     *
     * @param world The world identifier (an integer that determines which world map to load).
     */
    public WorldMaps(int world){
        //file to load
    }

    /**
     * Gets the 2D integer array representing levels.
     *
     * @return A 2D integer array representing the map for levels.
     */
    private int[][] level1 = {{}};

    private int[][] level2 = {{}};

    private int[][] level3 = {{}};

    private int[][] level4 = {{}};

    private int[][] level5 = {{}};

    public int[][] getLevel1(){
        return level1;
    }
    public int[][] getLevel2(){
        return level2;
    }
    public int[][] getLevel3(){
        return level3;
    }
    public int[][] getLevel4(){
        return level4;
    }
    public int[][] getLevel5(){
        return level5;
    }

}
