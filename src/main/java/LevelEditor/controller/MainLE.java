package LevelEditor.controller;

import LevelEditor.model.Level;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainLE {

    private String currentLevelName;
    private String currentTheme;
    private String dimension;
    private Level level;

    /**
     * Sparar nivån med ett namn och tema.
     *
     * @param levelName     Namnet på banan
     * @param selectedTheme Det valda temat
     * @param dimension     Dimensionen på labyrinten
     */
    public void saveLevel(String levelName, String selectedTheme, String dimension) {
        this.level = new Level(this);
        this.currentLevelName = levelName;
        this.currentTheme = selectedTheme;
        this.dimension = dimension;
        level.setLevelName(levelName);
        level.setTheme(selectedTheme);
        level.setDimension(dimension);
        System.out.println("Nivå sparad: " + levelName + " med tema: " + selectedTheme + " Dimension: " + dimension);

        // Ändra maze.dat
        updateMazeFile();
    }

    public int getThemeInt() {
        return level.getThemeInt();
    }


    public void updateMazeFile() {
        Path mazeFilePath = Paths.get("createdLevels", "maze.dat");

        if (Files.exists(mazeFilePath)) {
            System.out.println("Maze file exists.");

            try {
                String content = new String(Files.readAllBytes(mazeFilePath));


                File newMazeFile = new File("createdLevels", currentLevelName + ".dat");


                try (BufferedWriter writer = new BufferedWriter(new FileWriter(newMazeFile))) {

                    writer.write(level.getLevelName() + "\n");
                    writer.write(level.getTheme() + "\n");
                    writer.write(level.getDimension() + "\n");

                    writer.write(content);
                    writer.write("\n");


                    System.out.println("Maze file updated and saved as: " + newMazeFile.getAbsolutePath());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No maze.dat file found to update.");
        }
    }


}
