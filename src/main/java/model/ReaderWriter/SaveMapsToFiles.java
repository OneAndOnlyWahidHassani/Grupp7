package model.ReaderWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Klassen används enbart för att spara alla maps som dat. filer på nytt
 * Kan kallas på för att skriva om alla dat filer
 * @author Elvira Grubb
 */
public class SaveMapsToFiles {
    public SaveMapsToFiles() throws IOException {
        FileManager fileManager = new FileManager();
        List<int[][]> worldMap1 = Arrays.asList(level11, level12, level13, level14, level15);
        fileManager.write(worldMap1, "worldMap1");

        List<int[][]> worldMap2 = Arrays.asList(level21, level22, level23, level24, level25);
        fileManager.write(worldMap2, "worldMap2");

        List<int[][]> worldMap3 = Arrays.asList(level31, level32, level33, level34, level35);
        fileManager.write(worldMap3, "worldMap3");

        List<int[][]> worldMap4 = Arrays.asList(level41, level42, level43, level44, level45);
        fileManager.write(worldMap4, "worldMap4");

        List<int[][]> worldMap5 = Arrays.asList(level51, level52, level53, level54, level55);
        fileManager.write(worldMap5, "worldMap5");

        List<int[][]> worldMap6 = Arrays.asList(level61, level62, level63, level64, level65);
        fileManager.write(worldMap6, "worldMap6");
    }
    private int[][] level11 = {{0, 0, 0, 0, 0, 0, 1, 3},
            {0, 0, 1, 1, 4, 1, 1, 1},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 4, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 4, 0},
            {1, 4, 1, 1, 0, 0, 0, 0},
            {2, 1, 1, 0, 0, 0, 0, 0}};

    private int[][] level12 = {{0, 0, 0, 0, 0, 0, 1, 2},
            {0, 4, 1, 0, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 4, 0},
            {0, 0, 1, 1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 4, 1, 0, 1, 1, 4, 1},
            {0, 0, 1, 1, 4, 0, 0, 3}};

    private int[][] level13 = {{4, 0, 0, 0, 0, 0, 0, 4},
            {1, 0, 1, 1, 4, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 0, 0},
            {0, 1, 0, 4, 0, 1, 1, 0},
            {0, 1, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 1, 4, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 4, 1},
            {0, 3, 1, 4, 1, 0, 0, 2}};

    private int[][] level14 = {{0, 0, 0, 0, 0, 0, 0, 0},
            {4, 1, 0, 1, 4, 1, 0, 4},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 0, 4, 1, 1},
            {4, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 4, 1, 0, 4},
            {0, 1, 0, 1, 0, 1, 4, 0},
            {0, 2, 1, 1, 0, 0, 1, 3}};

    private int[][] level15 = {{0, 0, 0, 0, 0, 4, 0, 0},
            {0, 4, 1, 5, 0, 1, 1, 1},
            {0, 1, 0, 0, 0, 4, 0, 1},
            {0, 1, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 0},
            {0, 6, 0, 1, 1, 0, 0, 0},
            {4, 7, 4, 0, 1, 1, 1, 0},
            {4, 3, 4, 0, 0, 0, 1, 2}};

    private int[][] level21 = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 0, 4, 1, 1, 0, 0, 0, 3, 0},
            {1, 1, 1, 0, 1, 0, 0, 0, 4, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 4, 1, 1, 0, 0},
            {1, 0, 4, 1, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 1, 1, 4, 0, 1, 1, 4},
            {1, 0, 1, 1, 0, 1, 0, 0, 0, 1},
            {7, 0, 2, 0, 0, 1, 1, 1, 1, 4}};

    private int[][] level22 = new int[][]{{0, 5, 1, 4, 0, 0, 4, 1, 1, 0},
            {1, 0, 0, 1, 1, 0, 1, 0, 2, 0},
            {1, 4, 1, 0, 1, 1, 1, 0, 0, 0},
            {1, 0, 1, 1, 1, 0, 1, 1, 0, 0},
            {1, 0, 0, 1, 0, 0, 0, 1, 0, 0},
            {4, 1, 0, 1, 1, 4, 0, 1, 4, 0},
            {0, 1, 0, 6, 0, 1, 1, 0, 1, 0},
            {0, 1, 1, 4, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 4, 0, 1, 0},
            {1, 4, 1, 1, 0, 3, 0, 0, 1, 4}};


    private int[][] level23 = new int[][]{{0, 1, 1, 4, 0, 0, 1, 0, 0, 0},
            {1, 4, 0, 1, 0, 0, 1, 4, 6, 4},
            {4, 0, 1, 1, 0, 1, 1, 0, 0, 1},
            {1, 0, 4, 0, 0, 1, 0, 0, 0, 4},
            {4, 0, 1, 0, 0, 4, 1, 1, 0, 1},
            {1, 0, 4, 1, 0, 1, 0, 0, 0, 4},
            {4, 0, 0, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 0, 0, 0, 4, 1, 4, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 1, 1, 1},
            {5, 4, 1, 1, 1, 2, 0, 0, 0, 3}};

    private int[][] level24 = new int[][] {{0, 1, 4, 6, 1, 0, 0, 0, 1, 1},
            {0, 1, 0, 0, 1, 0, 1, 1, 1, 3},
            {0, 1, 1, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 1, 4, 1, 1, 1, 4},
            {5, 1, 1, 1, 1, 0, 0, 0, 0, 1},
            {0, 0, 1, 0, 0, 0, 0, 0, 0 ,4},
            {0, 1, 4, 0, 0, 4, 1, 4, 0 ,1},
            {0, 4, 0, 0, 0, 1, 0, 1, 0 ,4},
            {0, 1, 1, 4, 0, 1, 0, 1, 0 ,1},
            {0, 0, 0, 1, 1, 1, 0, 1, 1 ,2}};

    private int[][] level25 = new int[][] {{0, 0, 0, 0, 0, 3, 0, 0, 5, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 1, 0, 0},
            {4, 1, 1, 0, 0, 1, 0, 0, 4, 1, 2},
            {1, 0, 1, 4, 0, 6, 0, 0, 1, 0, 0},
            {4, 1, 0, 1, 0, 1, 0, 0, 1, 4, 0},
            {0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0},
            {4, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0},
            {1, 0, 0, 1, 1, 4, 0, 1, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0},
            {1, 0, 4, 1, 1, 0, 1, 0, 0, 1, 4},
            {4, 1, 1 ,0 ,1 ,6 ,1 ,1 ,4 ,1 ,0}};

    private int[][] level31 = {{0, 4, 1, 1, 1, 1, 1, 1,1,1,4,3},
            {0, 1, 0, 0, 0, 0, 0, 0,0,0,0,0},
            {0, 1, 0, 1, 1, 1, 4, 0,0,0,0,0},
            {4, 1, 1, 1, 0, 0, 1, 1,0,0,0,0},
            {0, 0, 0, 0, 0, 7, 0, 1,0,0,0,0},
            {1, 1, 1, 4, 0, 1, 0, 1,0,1,1,1},
            {4, 0, 0, 1, 0, 1, 0, 1,1,1,0,1},
            {1, 0, 0, 1, 1, 4, 1, 0,0,1,0,1},
            {1, 0, 0, 1, 0, 0, 1, 0,1,1,1,1},
            {1, 1, 0, 1, 0, 0, 1, 0,1,0,1,0},
            {0, 1, 0, 4, 0, 0, 1, 0,1,0,4,0},
            {2, 1, 4, 1, 1, 1, 1, 1,1,0,1,0},};

    private int[][] level32 = {{1, 1, 1, 1, 1, 1, 1, 4,1,1,1,2},
            {1, 0, 0, 0, 0, 4, 0, 0,0,0,0,0},
            {1, 1, 1, 1, 1, 1, 1, 1,1,0,0,0},
            {1, 0, 0, 0, 0, 1, 0, 0,1,0,0,0},
            {1, 0, 0, 0, 1, 1, 0, 0,6,0,0,0},
            {1, 0, 0, 0, 1, 0, 0, 0,1,1,1,0},
            {1, 0, 1, 1, 1, 1, 1, 1,0,0,1,0},
            {1, 0, 1, 0, 0, 0, 0, 1,0,1,1,0},
            {1, 0, 1, 0, 0, 0, 0, 1,0,1,0,0},
            {1, 0, 1, 1, 1, 1, 1, 1,0,1,1,1},
            {1, 0, 0, 0, 0, 0, 1, 0,0,0,0,1},
            {5, 0, 0, 0, 0, 0, 1, 1,1,1,4,3},};

    private int[][] level33 = {{0, 0, 0, 0, 0, 0, 0, 0,0,0,1,3},
            {0, 0, 0, 0, 0, 0, 0, 0,0,0,1,0},
            {0, 0, 0, 4, 0, 1, 0, 4,0,0,1,0},
            {1, 1, 1, 1, 1, 1, 1, 1,1,1,1,0},
            {1, 0, 0, 1, 0, 4, 0, 1,0,0,0,0},
            {1, 0, 0, 0, 0, 0, 0, 0,0,0,0,0},
            {4, 1, 1, 1, 0, 0, 0, 0,0,0,0,0},
            {1, 0, 0, 1, 1, 1, 4, 1,1,1,1,0},
            {1, 1, 1, 1, 0, 0, 1, 1,0,0,1,0},
            {0, 0, 0, 4, 1, 1, 1, 1,1,1,1,1},
            {0, 0, 0, 0, 0, 0, 0, 0,0,0,0,1},
            {0, 0, 0, 0, 0, 0, 0, 0,0,0,0,2},};

    private int[][] level34=   {{0, 0, 0, 0, 0, 1, 1, 1,1,1,1,2},
            {0, 1, 1, 1, 0, 4, 0, 0,0,0,0,0},
            {1, 7, 0, 1, 0, 1, 0, 0,0,0,0,0},
            {1, 0, 0, 1, 0, 1, 0, 0,0,0,0,0},
            {1, 1, 0, 1, 0, 1, 0, 0,0,0,0,0},
            {0, 1, 0, 1, 0, 1, 0, 0,0,0,0,0},
            {1, 1, 0, 1, 1, 4, 0, 0,0,0,0,0},
            {1, 0, 0, 0, 0, 0, 0, 0,0,0,0,0},
            {1, 1, 1, 1, 1, 0, 0, 0,0,0,0,0},
            {0, 1, 1, 1, 1, 1, 4, 1,1,1,0,0},
            {0, 0, 1, 1, 1, 1, 1, 1,1,1,1,1},
            {0, 0, 1, 1, 1, 1, 1, 4,1,1,7,3},};

    private int[][] level35 =   {{1, 1, 1, 4, 1, 1, 1, 4,1,1,1,3},
            {6, 0, 0, 0, 0, 0, 0, 0,1,0,0,0},
            {1, 1, 1, 1, 1, 1, 1, 1,1,0,0,0},
            {1, 0, 1, 1, 1, 1, 1, 1,1,0,0,0},
            {1, 0, 0, 0, 0, 0, 0, 0,0,0,0,0},
            {4, 0, 0, 5, 0, 0, 0, 1,4,1,1,4},
            {1, 0, 1, 1, 1, 1, 1, 1,0,0,0,1},
            {1, 0, 1, 0, 1, 0, 0, 1,1,1,1,1},
            {4, 0, 1, 0, 1, 0, 0, 0,0,4,0,0},
            {1, 0, 1, 1, 1, 0, 0, 1,1,1,1,1},
            {1, 0, 0, 1, 0, 0, 0, 1,0,0,0,1},
            {1, 1, 1, 1, 0, 0, 0, 1,1,1,1,2},};

    private int[][] level41 = new int[][]{{7, 0, 0, 1, 1, 1, 1, 4, 1, 1, 1, 0, 0, 2},
            {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
            {4, 0, 1, 0, 0, 4, 0, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 4, 0, 4, 0, 1, 1, 1},
            {4, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 1, 1, 1, 4, 0, 1, 1, 1, 1, 1},
            {0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 4, 0},
            {1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {4, 0, 0, 0, 4, 1, 1, 1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 1, 0, 1, 1, 1, 4, 1, 1, 1, 4, 0, 1},
            {0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {4, 1, 0, 0, 0, 0, 0, 1, 1, 4, 1, 0, 0, 1},
            {0, 0, 4, 1, 4, 1, 4, 1, 0, 0, 1, 1, 1, 3}};

    private int[][] level42 = new int[][]{{4, 1, 0, 0, 7, 0, 4, 0, 0, 0, 1, 1, 1, 1},
            {0, 4, 1, 1, 4, 0, 1, 4, 1, 4, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 4},
            {1, 1, 1, 1, 1, 1, 0, 4, 0, 4, 1, 1, 1, 0},
            {4, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 4},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
            {4, 1, 1, 1, 1, 4, 0, 4, 1, 1, 0, 4, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {1, 1, 4, 0, 4, 0, 1, 1, 4, 1, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 4},
            {4, 1, 1, 0, 0, 1, 0, 4, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 4, 0, 1},
            {1, 1, 4, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 4},
            {3, 0, 1, 1, 4, 1, 1, 4, 0, 1, 1, 1, 4, 2}};


    private int[][] level43 = new int[][]   {{3, 1, 1, 4, 0, 6, 4, 1, 1, 0, 1, 0, 0, 4},
            {0, 0, 0, 1, 1, 4, 0, 0, 1, 4, 1, 0, 4, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 1, 1, 5, 0, 4, 0, 1, 0, 0, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 4, 1},
            {7, 1, 0, 0, 1, 4, 0, 4, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 4},
            {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 4},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 4, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 4, 0, 1, 0, 0, 1, 4},
            {1, 0, 0, 0, 4, 1, 1, 1, 0, 1, 0, 4, 1, 0},
            {2, 1, 4, 1, 1, 0, 0, 1, 1, 4, 1, 1, 0, 0}};

    private int[][] level44 = new int[][]{{2, 1, 1, 4, 1, 1, 0, 1, 4, 1, 0, 4, 1, 1},
            {0, 0, 0, 0, 0, 1, 4, 1, 0, 1, 0, 1, 0, 1},
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 4},
            {1, 1, 1, 1, 4, 1, 1, 7, 0, 0, 6, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1},
            {5, 0, 4, 1, 1, 1, 4, 6, 4, 1, 0, 1, 1, 4},
            {0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 4, 1, 4, 0, 1, 1, 0, 4, 1, 1},
            {1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1},
            {4, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 4},
            {0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 4, 0, 0},
            {4, 1, 0, 4, 0, 1, 1, 4, 0, 1, 4, 1, 1, 0},
            {1, 0, 0, 1, 0, 1, 0, 0, 4, 1, 0, 0, 4, 1},
            {1, 1, 1, 4, 0, 1, 4, 1, 1, 0, 0, 0, 0, 3}};

    private int[][] level45 = new int[][] {{3, 1, 0, 4, 1, 1, 4, 1, 1, 4, 0, 4, 1, 4},
            {0, 4, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1},
            {0, 0, 4, 1, 0, 0, 1, 0, 0, 1, 4, 0, 0, 1},
            {4, 1, 0, 4, 1, 0, 1, 0, 1, 4, 0, 1, 1, 4},
            {0, 1, 0, 0, 4, 0, 4, 1, 4, 0, 0, 1, 0, 0},
            {0, 1, 4, 6, 1, 4, 1, 0, 0, 0, 0, 1, 0, 4},
            {1, 1, 0, 0, 0, 0, 0, 0, 4, 4, 0, 1, 4, 1},
            {1, 0, 0, 7, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0},
            {1, 4, 0, 0, 0, 0, 1, 0, 1, 1, 4, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 4, 1, 1, 0, 1, 1, 0, 4},
            {4, 1, 1, 1, 4, 0, 1, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 4, 0, 1, 1, 4, 1, 1},
            {5, 1, 1, 1, 4, 0, 0, 1, 1, 4, 0, 0, 0, 2}};

    private int[][] level51 = new int[][] {{2, 0, 4, 1, 1, 0, 0, 0, 1, 4, 0, 1, 1, 1, 1, 3},
            {1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 4, 0, 0, 1, 1},
            {0, 4, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 6},
            {0, 0, 0, 1, 1, 0, 4, 1, 1, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 7, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 4, 1, 1, 4, 0, 4},
            {1, 1, 1, 1, 0, 1, 1, 4, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 5, 0, 0, 1, 0, 4},
            {1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 4, 0, 1, 1},
            {4, 1, 1, 4, 0, 4, 1, 0, 4, 0, 1, 0, 1, 0, 4, 0},
            {1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1},
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 4, 0, 0, 1},
            {1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {4, 0, 1, 1, 0, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 4, 1, 0, 0, 0, 4, 1, 1, 4, 1, 1, 4, 1, 1, 4},};

    private int[][] level52 = new int[][]  {{4, 0, 0, 4, 1, 1, 4, 0, 0, 0, 1, 1, 1, 1, 0, 2},
            {1, 5, 1, 1, 0, 0, 1, 1, 4, 1, 1, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 0, 1},
            {4, 0, 1, 5, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1},
            {1, 1, 4, 0, 0, 0, 0, 1, 4, 0, 1, 0, 0, 0, 7, 4},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 4, 1, 0, 0, 1},
            {0, 1, 1, 4, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1},
            {1, 1, 0, 0, 0, 0, 1, 4, 1, 0, 0, 0, 0, 1, 1, 4},
            {4, 0, 0, 1, 0, 0, 0, 0, 1, 1, 4, 1, 4, 0, 6, 0},
            {1, 0, 0, 1, 4, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 4, 1, 0, 0, 0, 1},
            {4, 1, 4, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
            {6, 0, 0, 0, 4, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
            {1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 4},
            {1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
            {3, 0, 0, 4, 1, 1, 1, 1, 4, 0, 1, 1, 1, 4, 1, 1}};


    private int[][] level53 = new int[][] {{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 4, 1, 1, 1, 4, 1},
            {4, 1, 0, 0, 0, 1, 1, 0, 1, 4, 1, 0, 0, 0, 1, 0},
            {0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 4, 0, 0, 0, 1, 0},
            {4, 0, 1, 0, 4, 0, 0, 1, 1, 1, 1, 1, 0, 0, 4, 0},
            {1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},
            {1, 0, 4, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
            {1, 1, 1, 0, 1, 4, 0, 1, 1, 1, 1, 1, 1, 4, 0, 1},
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 6, 0, 4},
            {1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0},
            {0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 4},
            {0, 0, 1, 0, 4, 0, 0, 0, 1, 0, 1, 4, 1, 0, 0, 1},
            {1, 4, 1, 1, 1, 0, 1, 1, 1, 4, 1, 0, 0, 1, 1, 4},
            {1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1},
            {1, 0, 0, 1, 0, 0, 1, 1, 5, 0, 1, 0, 1, 0, 0, 4},
            {1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 4, 4},
            {2, 0, 0, 4, 6, 7, 0, 0, 1, 1, 4, 1, 0, 0, 4, 3}};

    private int[][] level54 = new int[][] {{1, 0, 0, 1, 1, 4, 1, 1, 0, 1, 1, 0, 1, 4, 1, 0},
            {1, 4, 1, 0, 0, 0, 0, 4, 1, 4, 1, 1, 1, 0, 1, 1},
            {0, 0, 1, 1, 4, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 1, 0, 4, 0, 0, 1, 1, 4, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 4, 1, 0, 1, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 0, 0, 5, 0, 1, 0, 0, 1, 1, 1},
            {4, 1, 0, 0, 4, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1},
            {0, 0, 1, 1, 1, 0, 0, 1, 7, 1, 0, 0, 1, 1, 1, 4},
            {0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
            {4, 1, 1, 1, 1, 0, 1, 1, 1, 1, 4, 1, 0, 4, 1, 0},
            {1, 0, 1, 4, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1},
            {4, 0, 0, 0, 0, 0, 1, 1, 4, 0, 1, 0, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 4, 1, 0, 1, 0, 1, 1, 4, 1, 0, 4},
            {0, 1, 1, 6, 4, 1, 1, 0, 4, 0, 1, 0, 0, 1, 0, 1},
            {1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 4, 0, 1, 1, 1},
            {3, 0, 0, 0, 0, 0, 1, 1, 4, 1, 1, 0, 0, 1, 0, 2}};

    private int[][] level55 = new int[][]
            {{0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                    {4, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 7},
                    {1, 7, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1},
                    {0, 0, 0, 0, 4, 1, 1, 4, 1, 1, 0, 0, 0, 1, 1, 1},
                    {1, 1, 5, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 1, 1, 4, 0, 1, 1, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 0, 0, 4, 0, 0, 1, 0, 1, 1, 6, 1},
                    {0, 1, 0, 0, 4, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 4},
                    {0, 1, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 1, 1, 1, 1, 1, 1, 4, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1},
                    {0, 0, 0, 1, 1, 4, 0, 4, 1, 1, 1, 1, 0, 1, 0, 4},
                    {0, 0, 4, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 7, 0, 1},
                    {1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 4},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 3}};

    private int[][] level61 = new int[][]{{3, 7, 1, 1, 1, 0, 1, 1, 0, 1, 1, 4, 1, 1, 1, 4, 1, 1},
            {0, 0, 0, 0, 1, 0, 0, 1, 4, 1, 0, 0, 0, 0, 0, 1, 0, 1},
            {4, 0, 4, 0, 4, 1, 0, 0, 1, 0, 0, 1, 4, 1, 0, 1, 0, 4},
            {1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 1, 0, 4, 0, 1, 0, 0, 1, 4, 0, 0, 0, 1, 1, 0},
            {1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 4, 1},
            {0, 0, 0, 1, 0, 1, 0, 4, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 4, 1, 0, 1, 1, 0, 0, 1, 0, 1, 4, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 4},
            {1, 0, 7, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 4, 0, 0, 0, 0, 0, 4, 1, 1, 0, 0, 0, 4, 1, 0, 1},
            {4, 1, 1, 0, 1, 0, 4, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 4, 1, 0, 1, 4, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 4, 1, 0, 0, 1, 0, 0, 1, 0},
            {4, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 4},
            {0, 0, 0, 1, 1, 4, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 1, 1, 0, 0, 1, 0, 2}};

    private int[][] level62 = new int[][]{{2, 1, 1, 1, 0, 0, 0, 1, 0, 1, 4, 1, 1, 4, 1, 1, 1, 7},
            {1, 1, 1, 1, 4, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 4, 1},
            {1, 4, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 4},
            {1, 7, 1, 4, 1, 1, 1, 4, 0, 1, 1, 1, 0, 4, 1, 1, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 4, 1, 0, 1, 1, 4, 1, 1, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 1, 4, 0, 0, 0, 1, 0, 1, 1, 1, 1},
            {4, 0, 1, 0, 4, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0},
            {1, 0, 1, 4, 1, 0, 0, 4, 1, 1, 4, 0, 1, 0, 1, 1, 4, 1},
            {1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0},
            {1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 4},
            {0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 4, 1, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0},
            {0, 1, 1, 1, 4, 1, 0, 1, 4, 0, 0, 0, 1, 0, 1, 4, 1, 1},
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 4, 1, 0, 1, 1, 1, 1},
            {0, 1, 1, 1, 4, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 4, 1, 1, 1, 1, 4, 4, 3}};


    private int[][] level63 = new int[][]{{3, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 4},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 0, 1, 0, 1, 0, 4, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 4, 0, 1, 0, 1, 0, 4},
            {1, 1, 4, 1, 1, 1, 1, 1, 1, 7, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 1, 1, 1},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 4},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 4, 0, 4, 0, 1, 0, 4, 0, 1},
            {1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 7},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 2}};


    private int[][] level64 = new int[][] {{2, 0, 1, 0, 0, 0, 0, 1, 1, 4, 1, 0, 4, 1, 4, 0, 1, 3},
            {1, 0, 4, 1, 1, 4, 1, 1, 0, 0, 0, 1, 4, 0, 1, 1, 1, 0},
            {1, 0, 0, 0, 0, 1, 0, 1, 4, 1, 1, 4, 0, 0, 0, 0, 1, 0},
            {1, 1, 1, 0, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 4},
            {0, 0, 1, 0, 1, 0, 0, 1, 1, 4, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 4, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 4, 1, 0, 4, 0},
            {4, 0, 1, 4, 1, 1, 0, 1, 0, 4, 1, 0, 1, 1, 0, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1},
            {4, 0, 1, 4, 1, 1, 0, 0, 1, 1, 4, 0, 1, 1, 1, 0, 0, 4},
            {1, 0, 1, 0, 0, 0, 1, 4, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1},
            {4, 0, 1, 4, 0, 1, 1, 0, 0, 0, 0, 1, 4, 1, 0, 4, 1, 0},
            {1, 0, 0, 1, 1, 4, 0, 4, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0},
            {4, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 4, 0, 0},
            {1, 0, 1, 0, 0, 0, 4, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0},
            {4, 0, 4, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 4, 0, 0, 4, 1},
            {1, 0, 1, 0, 1, 0, 4, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 4},
            {1, 1, 1, 0, 4, 1, 1, 4, 1, 4, 0, 0, 1, 4, 1, 1, 1, 1}};

    private int[][] level65 = new int[][] {{1, 1, 1, 0, 1, 1, 1, 0, 4, 1, 1, 0, 1, 1, 1, 0, 1, 2},
            {4, 0, 1, 1, 4, 0, 1, 1, 1, 0, 1, 4, 1, 0, 4, 1, 1, 0},
            {1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1, 4, 1, 0, 1, 1, 4, 0, 1, 4, 1, 0, 0},
            {1, 1, 0, 1, 4, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 1, 0},
            {1, 1, 0, 1, 1, 0, 1, 1, 1, 4, 1, 1, 1, 0, 0, 0, 1, 0},
            {0, 4, 0, 0, 4, 0, 1, 0, 0, 0, 0, 0, 4, 1, 0, 4, 1, 0},
            {1, 1, 0, 1, 1, 0, 1, 1, 4, 3, 0, 0, 1, 0, 0, 1, 0, 0},
            {1, 0, 0, 1, 0, 0, 1, 1, 4, 4, 0, 0, 1, 1, 0, 1, 4, 0},
            {4, 1, 0, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 1, 0},
            {0, 1, 0, 0, 1, 0, 1, 4, 1, 0, 4, 1, 1, 1, 0, 4, 1, 0},
            {1, 4, 0, 1, 4, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 1, 0},
            {4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 4, 0, 1, 4, 1, 0, 1, 1, 4, 0, 1, 4, 1, 0, 1, 1, 0},
            {0, 1, 4, 1, 0, 1, 1, 4, 0, 1, 1, 1, 0, 1, 4, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
}
