package LevelEditor.model;

import LevelEditor.controller.MainLE;

public class Level {

    private MainLE mainLE;
    private String levelName;
    private String theme;
    private int size;

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
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Hämtar storleken för banan.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sparar nivåns information (namn, tema, storlek).
     */
    public void saveLevel() {
        mainLE.saveLevel(levelName, theme);
    }

    /**
     * Byter till editor-scenen.
     */
    public void changeToEditor() {
        mainLE.changeToEditor();
    }
}

