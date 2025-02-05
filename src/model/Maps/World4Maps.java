package model.Maps;

import model.ReaderWriter.FileManager;

import java.io.IOException;
import java.util.List;

/**
 * @author André Eklund, Sebastian Helin, Viktor Näslund, Filip Örnling
 */
public class World4Maps {
    FileManager fileManager = new FileManager();
    private int[][] level41;
    private int[][] level42;
    private int[][] level43;
    private int[][] level44;
    private int[][] level45;

    public World4Maps() throws IOException, ClassNotFoundException {
        List<int[][]> world4Map = fileManager.read("worldMap4");
        level41 = world4Map.get(0);
        level42 = world4Map.get(1);
        level43 = world4Map.get(2);
        level44 = world4Map.get(3);
        level45 = world4Map.get(4);
    }

    /**
     * Returnerar en array som representerar en nivå i spelet.
     * @return returnerar en array av siffror.
     */
    public int[][] getLevel41(){
        return level41;
    }

    public int[][] getLevel42(){
        return level42;
    }

    public int[][] getLevel43(){
        return level43;
    }

    public int[][] getLevel44(){
        return level44;
    }

    public int[][] getLevel45() {
        return level45;
    }
}
