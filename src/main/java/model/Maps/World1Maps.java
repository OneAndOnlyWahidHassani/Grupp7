package model.Maps;

import model.ReaderWriter.FileManager;

import java.io.IOException;
import java.util.List;

/**
 * @author André Eklund, Sebastian Helin, Viktor Näslund, Filip Örnling
 */

public class World1Maps {
    FileManager fileManager = new FileManager();
    private int[][] level11;
    private int[][] level12;
    private int[][] level13;
    private int[][] level14;
    private int[][] level15;

    public World1Maps() throws IOException, ClassNotFoundException
    {
        List<int[][]> world1Map = fileManager.read("worldMap1");
        level11 = world1Map.get(0);
        level12 = world1Map.get(1);
        level13 = world1Map.get(2);
        level14 = world1Map.get(3);
        level15 = world1Map.get(4);
    }

    /**
     * Returnerar en array som representerar en nivå i spelet.
     * @return returnerar en array av siffror.
     */
    public int[][] getLevel11()
    {
        return level11;
    }

    public int[][] getLevel12(){
        return level12;
    }

    public int[][] getLevel13(){
        return level13;
    }

    public int[][] getLevel14(){
        return level14;
    }

    public int[][] getLevel15() {
        return level15;
    }
}
