package model.Maps;

import model.ReaderWriter.FileManager;

import java.io.IOException;
import java.util.List;

/**
 * @author André Eklund, Sebastian Helin, Viktor Näslund, Filip Örnling
 */

public class World2Maps {
    FileManager fileManager = new FileManager();
    private int[][] level21;
    private int[][] level22;
    private int[][] level23;
    private int[][] level24;
    private int[][] level25;

    public World2Maps() throws IOException, ClassNotFoundException
    {
        List<int[][]> world2Map = fileManager.read("worldMap2");
        level21 = world2Map.get(0);
        level22 = world2Map.get(1);
        level23 = world2Map.get(2);
        level24 = world2Map.get(3);
        level25 = world2Map.get(4);
    }

    /**
     * Returnerar en array som representerar en nivå i spelet.
     * @return returnerar en array av siffror.
     */
    public int[][] getLevel21(){
        return level21;
    }

    public int[][] getLevel22(){
        return level22;
    }

    public int[][] getLevel23(){
        return level23;
    }

    public int[][] getLevel24(){
        return level24;
    }

    public int[][] getLevel25() {
        return level25;
    }
}
