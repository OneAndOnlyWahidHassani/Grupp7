package LevelEditor.controller;

import LevelEditor.model.Level;
import model.MazeGeneration.MazeGenerator;
import model.ReaderWriter.FileManager;

public class MainLE {
    private String currentLevelName;
    private String currentTheme;
    private String dimension;
    private Level level;
    private MazeGenerator mazeGenerator; // Lägg till en referens till MazeGenerator
    private FileManager fileManager;

    public void saveLevel(String levelName, String selectedTheme, String dimension) {
        this.level = new Level(this);
        this.currentLevelName = levelName;
        this.currentTheme = selectedTheme;
        this.dimension = dimension;
        level.setLevelName(levelName);
        level.setTheme(selectedTheme);
        level.setDimension(dimension);


        String[] dimensionParts = dimension.split("x");
        int mazeSize = Integer.parseInt(dimensionParts[0]);


        mazeGenerator = new MazeGenerator(mazeSize, true);


        saveLevelData();
    }


    public int getThemeInt() {
        return level.getThemeInt();
    }


    public void saveLevelData() {
        int[][] rawMaze = mazeGenerator.getRawMazeArray(); // Hämta labyrinten
        FileManager.writeLevelData(currentLevelName, currentTheme, dimension, rawMaze);
    }



}
