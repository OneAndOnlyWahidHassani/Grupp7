package LevelEditor.model;

import LevelEditor.controller.MainLE;

public class Level {

    private MainLE mainLE;
    private String levelName;
    private String theme;
    private String dimension;


    public Level(MainLE mainLE) {
        this.mainLE = mainLE;
    }

    /**
     * Sätter namnet på banan.
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Hämtar namnet på banan.
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Sätter temat för banan.
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * Hämtar temat för banan.
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Sätter storleken för banan.
     */
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    /**
     * Hämtar storleken för banan.
     */
    public String getDimension() {
        return dimension;
    }


}

