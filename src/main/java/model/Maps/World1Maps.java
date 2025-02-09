package model.Maps;

/**
 * @author André Eklund, Sebastian Helin, Viktor Näslund, Filip Örnling
 */

public class World1Maps extends WorldMaps {

    private int[][] level1 = {{0, 0, 0, 0, 0, 0, 1, 3},
            {0, 0, 1, 1, 4, 1, 1, 1},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 4, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 4, 0},
            {1, 4, 1, 1, 0, 0, 0, 0},
            {2, 1, 1, 0, 0, 0, 0, 0}};

    private int[][] level2 = {{0, 0, 0, 0, 0, 0, 1, 2},
            {0, 4, 1, 0, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 4, 0},
            {0, 0, 1, 1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 4, 1, 0, 1, 1, 4, 1},
            {0, 0, 1, 1, 4, 0, 0, 3}};

    private int[][] level3 = {{4, 0, 0, 0, 0, 0, 0, 4},
            {1, 0, 1, 1, 4, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 0, 0},
            {0, 1, 0, 4, 0, 1, 1, 0},
            {0, 1, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 1, 4, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 4, 1},
            {0, 3, 1, 4, 1, 0, 0, 2}};

    private int[][] level4 = {{0, 0, 0, 0, 0, 0, 0, 0},
            {4, 1, 0, 1, 4, 1, 0, 4},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 0, 4, 1, 1},
            {4, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 4, 1, 0, 4},
            {0, 1, 0, 1, 0, 1, 4, 0},
            {0, 2, 1, 1, 0, 0, 1, 3}};

    private int[][] level5 = {{0, 0, 0, 0, 0, 4, 0, 0},
            {0, 4, 1, 5, 0, 1, 1, 1},
            {0, 1, 0, 0, 0, 4, 0, 1},
            {0, 1, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 0},
            {0, 6, 0, 1, 1, 0, 0, 0},
            {4, 7, 4, 0, 1, 1, 1, 0},
            {4, 3, 4, 0, 0, 0, 1, 2}};

    public World1Maps(int world) {
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
