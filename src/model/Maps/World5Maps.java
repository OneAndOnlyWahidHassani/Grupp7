package model.Maps;

import model.ReaderWriter.FileManager;

import java.io.IOException;
import java.util.List;

/**
 * @author André Eklund, Sebastian Helin, Viktor Näslund, Filip Örnling
 */

public class World5Maps {
    FileManager fileManager = new FileManager();
    private int[][] level51;
    private int[][] level52;
    private int[][] level53;
    private int[][] level54;
    private int[][] level55;

    public World5Maps() throws IOException, ClassNotFoundException {
        List<int[][]> world5Map = fileManager.read("worldMap5");
        level51 = world5Map.get(0);
        level52 = world5Map.get(1);
        level53 = world5Map.get(2);
        level54 = world5Map.get(3);
        level55 = world5Map.get(4);
    }
    /**
     * Returnerar en array som representerar en nivå i spelet.
     * @return returnerar en array av siffror.
     */
    public int[][] getLevel51(){
        return level51;
    }

    public int[][] getLevel52(){
        return level52;
    }

    public int[][] getLevel53(){
        return level53;
    }

    public int[][] getLevel54(){
        return level54;
    }

    public int[][] getLevel55() {
        return level55;
    }
}
