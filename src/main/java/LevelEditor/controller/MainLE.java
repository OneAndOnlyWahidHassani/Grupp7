package LevelEditor.controller;

import LevelEditor.model.Level;
import control.MainProgram;
import model.MazeGeneration.MazeGenerator;
import model.ReaderWriter.FileManager;

import java.util.Arrays;
import java.util.Map;

public class MainLE {
    private String currentLevelName;
    private String currentTheme;
    private String dimension;
    private Level level;
    private MazeGenerator mazeGenerator; // Lägg till en referens till MazeGenerator
    private FileManager fileManager;
    private MainProgram mainProgram;

    public MainLE(MainProgram mainProgram) {
        this.mainProgram = mainProgram;
    }

//todo fixa så att den sparar labyrinten
    public void saveLevel(String levelName, String selectedTheme, String dimension, int[][] maze) {
        if (maze != null) {
            System.out.println("Maze when saving level from editor: " + Arrays.deepToString(maze));
        }

        this.level = new Level(this);
        this.currentLevelName = levelName;
        this.currentTheme = selectedTheme;
        this.dimension = dimension;
        level.setLevelName(levelName);
        level.setTheme(selectedTheme);
        level.setDimension(dimension);

        String[] dimensionParts = dimension.split("x");
        int mazeSize = Integer.parseInt(dimensionParts[0]);

        if (maze != null) {
            mazeGenerator = new MazeGenerator(maze, false, true);
            mazeGenerator.setRawMazeArray(maze);
        } else {
            mazeGenerator = new MazeGenerator(mazeSize, true);
        }

        saveLevelData();
    }


    public int getThemeInt() {
        return level.getThemeInt();
    }


    public void saveLevelData() {
        System.out.println("Maze som matas in i writeLevelData:");
        for (int i = 0; i < mazeGenerator.getRawMazeArray().length; i++) {
            System.out.println(java.util.Arrays.toString(mazeGenerator.getRawMazeArray()[i]));
        }
        int[][] rawMaze = mazeGenerator.getRawMazeArray(); // Hämta labyrinten
        FileManager.writeLevelData(currentLevelName, currentTheme, dimension, rawMaze);

    }

    public void loadLevel(String levelName) {
        Map<String, Object> levelData = FileManager.readLevelData(levelName);
        this.level = new Level(this);
        this.currentLevelName = (String) levelData.get("levelName");
        this.currentTheme = (String) levelData.get("theme");
        System.out.println(currentTheme);
        this.dimension = (String) levelData.get("dimension");
        level.setLevelName(currentLevelName);
        int[][] maze = (int[][]) levelData.get("maze");

        level.setLevelName(currentLevelName);
        level.setTheme(currentTheme);
        level.setDimension(dimension);

        mazeGenerator = new MazeGenerator(maze.length, true);
        mazeGenerator.setRawMazeArray(maze);
    }

    public boolean testLevel(String currentLevelName, String currentTheme, String dimension, int[][] maze) {
        if (maze != null) {
            System.out.println("Maze when testing level from editor: " + Arrays.deepToString(maze));
        }

        this.level = new Level(this);
        this.currentLevelName = currentLevelName;
        this.currentTheme = currentTheme;
        this.dimension = dimension;
        level.setLevelName(currentLevelName);
        level.setTheme(currentTheme);
        level.setDimension(dimension);

        String[] dimensionParts = dimension.split("x");
        int mazeSize = Integer.parseInt(dimensionParts[0]);

        if (maze != null) {
            mazeGenerator = new MazeGenerator(maze, false, true);
            mazeGenerator.setRawMazeArray(maze);

        } else {
            mazeGenerator = new MazeGenerator(mazeSize, true);
        }

        return mainProgram.startTestLevel( getThemeInt(), mazeSize, mazeGenerator.getRawMazeArray(), 50, 3); //todo input seconds and heart from user
    }



    public String getDimension() {
        return dimension;
    }

    public MazeGenerator getMazeGenerator() {
        return mazeGenerator;
    }

    public Level getLevel() {
        return level;
    }

    public String getCurrentLevelName() {
        return currentLevelName;
    }

    public String getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentLevelName(String currentLevelName) {
        this.currentLevelName = currentLevelName;
    }

    public void setCurrentTheme(String currentTheme) {
        this.currentTheme = currentTheme;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setMazeGenerator(MazeGenerator mazeGenerator) {
        this.mazeGenerator = mazeGenerator;
    }



}
