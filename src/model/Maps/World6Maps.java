package model.Maps;

import model.ReaderWriter.FileManager;

import java.io.IOException;
import java.util.List;

/**
 * @author André Eklund, Sebastian Helin, Viktor Näslund, Filip Örnling
 */
public class World6Maps {
    FileManager fileManager = new FileManager();
    private int[][] level61;
    private int[][] level62;
    private int[][] level63;
    private int[][] level64;
    private int[][] level65;

    public World6Maps() throws IOException, ClassNotFoundException {
        List<int[][]> world6Map = fileManager.read("worldMap6");
        level61 = world6Map.get(0);
        level62 = world6Map.get(1);
        level63 = world6Map.get(2);
        level64 = world6Map.get(3);
        level65 = world6Map.get(4);
    }

    /**
     * Returnerar en array som representerar en nivå i spelet.
     * @return returnerar en array av siffror.
     */
    public int[][] getLevel61(){
        return level61;
    }

    public int[][] getLevel62(){
        return level62;
    }

    public int[][] getLevel63(){
        return level63;
    }

    public int[][] getLevel64(){
        return level64;
    }

    public int[][] getLevel65() {
        return level65;
    }
}
