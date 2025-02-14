package model.Maps;

import model.ReaderWriter.FileManager;

import java.io.IOException;
import java.util.List;

public class World3Maps {

    /**
     * @author André Eklund, Sebastian Helin, Viktor Näslund, Filip Örnling
     */
    FileManager fileManager = new FileManager();
    private int[][] level31;
    private int[][] level32;
    private int[][] level33;
    private int[][] level34;
    private int[][] level35;

    public World3Maps() throws IOException, ClassNotFoundException
    {
        List<int[][]> world3Map = fileManager.read("worldMap3");
        level31 = world3Map.get(0);
        level32 = world3Map.get(1);
        level33 = world3Map.get(2);
        level34 = world3Map.get(3);
        level35 = world3Map.get(4);
    }


    /**
     * Returnerar en array som representerar en nivå i spelet.
     * @return returnerar en array av siffror.
     */
    public int[][] getLevel31(){
        return level31;
    }

    public int[][] getLevel32(){
        return level32;
    }

    public int[][] getLevel33(){
        return level33;
    }

    public int[][] getLevel34(){
        return level34;
    }

    public int[][] getLevel35() {
        return level35;
    }
}
