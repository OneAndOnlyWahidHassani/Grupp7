package model.Maps;

import model.ReaderWriter.FileManager;

import java.io.IOException;

/**
 * @author André Eklund, Sebastian Helin, Viktor Näslund, Filip Örnling
 */

public class World1Maps {
    FileManager fileManager = new FileManager();
    private int[][] level11 = fileManager.read("level11");;
    private int[][] level12 = fileManager.read("level12");;
    private int[][] level13 = fileManager.read("level13");;
    private int[][] level14 = fileManager.read("level14");;
    private int[][] level15 = fileManager.read("level15");;

    public World1Maps() throws IOException, ClassNotFoundException {
    }

    /**
     * Returnerar en array som representerar en nivå i spelet.
     * @return returnerar en array av siffror.
     */
    public int[][] getLevel11(){
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
