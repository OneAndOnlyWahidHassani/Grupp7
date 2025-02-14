package model.Maps;

import model.ReaderWriter.FileManager;

import java.io.IOException;
import java.util.List;


public class World3Maps extends WorldMaps{


    /**
     * @author André Eklund, Sebastian Helin, Viktor Näslund, Filip Örnling
     */
    FileManager fileManager = new FileManager();
    private int[][] level1;
    private int[][] level2;
    private int[][] level3;
    private int[][] level4;
    private int[][] level5;

    public World3Maps() throws IOException, ClassNotFoundException
    {
        super(3);
        List<int[][]> world3Map = fileManager.read("worldMap3");
        level1 = world3Map.get(0);
        level2 = world3Map.get(1);
        level3 = world3Map.get(2);
        level4 = world3Map.get(3);
        level5 = world3Map.get(4);
    }

    public World3Maps(int world) {
        super(world);
    }


    /**
     * Returnerar en array som representerar en nivå i spelet.
     * @return returnerar en array av siffror.
     */
    @Override
    public int[][] getLevel1(){
        return level1;
    }
    @Override
    public int[][] getLevel2(){
        return level2;
    }
    @Override
    public int[][] getLevel3(){
        return level3;
    }
    @Override
    public int[][] getLevel4(){
        return level4;
    }
    @Override
    public int[][] getLevel5() {
        return level5;
    }
}
