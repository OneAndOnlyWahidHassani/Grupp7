package model.Maps;

import model.ReaderWriter.FileManager;

import java.io.IOException;
import java.util.List;

/**
 * @author André Eklund, Sebastian Helin, Viktor Näslund, Filip Örnling, Elvira Grubb
 */
public class World4Maps extends WorldMaps {

    FileManager fileManager = new FileManager();
    private int[][] level1;
    private int[][] level2;
    private int[][] level3;
    private int[][] level4;
    private int[][] level5;

    public World4Maps(int world) throws IOException, ClassNotFoundException {
        super(world);
        List<int[][]> world4Map = fileManager.read("worldMap4");
        level1 = world4Map.get(0);
        level2 = world4Map.get(1);
        level3 = world4Map.get(2);
        level4 = world4Map.get(3);
        level5 = world4Map.get(4);
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
