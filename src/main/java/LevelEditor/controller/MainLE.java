package LevelEditor.controller;

import LevelEditor.model.Level;

public class MainLE {

    private String currentLevelName;
    private String currentTheme;
    private String dimension;
    private Level level;


    /**
     * Sparar nivån med ett namn och tema.
     * @param levelName Namnet på banan
     * @param selectedTheme Det valda temat
     */
    public void saveLevel(String levelName, String selectedTheme, String dimension) {
        this.level = new Level(this);
        this.currentLevelName = levelName;
        this.currentTheme = selectedTheme;
        this.dimension = dimension;
        level.setLevelName(levelName);
        level.setTheme(selectedTheme);
        level.setDimension(dimension);
        System.out.println("Nivå sparad: " + levelName + " med tema: " + selectedTheme + "Dimension: " + dimension);

    }

}
