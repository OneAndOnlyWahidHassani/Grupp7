package LevelEditor.model;

import LevelEditor.controller.MainLE;
/**
 * Represents a level in the level editor.
 * Stores information such as the level's name, theme, and dimensions.
 *
 * @author Alanah Öster berg Coleman
 */
public class Level {

    private MainLE mainLE;
    private String levelName;
    private String theme;
    private String dimension;

    /**
     * Constructs a Level instance with a reference to the main level editor controller.
     *
     * @param mainLE the main level editor controller
     * @author Alanah Öster berg Coleman
     */
    public Level(MainLE mainLE) {
        this.mainLE = mainLE;
    }

    /**
     * Sets the name of the level.
     *
     * @param levelName the name of the level
     * @author Alanah Öster berg Coleman
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Retrieves the name of the level.
     *
     * @return the level name
     * @author Alanah Öster berg Coleman
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Sets the theme for the level.
     *
     * @param theme the theme of the level
     * @author Alanah Öster berg Coleman
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * Retrieves the theme of the level.
     *
     * @return the level theme
     * @author Alanah Öster berg Coleman
     */
    public String getTheme() {
        return theme;
    }

    /**
     *  Retrieves an integer representation of the theme.
     *  Each theme is mapped to a unique integer.
     *  @return an integer representing the theme
     * @author Linus Sigurd
     */
    public int getThemeInt() {
        if (theme.equals("forest")) {
            return 0;
        } else if (theme.equals("lava")) {
            return 1;
        } else if (theme.equals("underground")) {
            return 2;
        } else if (theme.equals("cloud")) {
            return 3;
        } else if (theme.equals("desert")) {
            return 4;
        } else if (theme.equals("space")) {
            return 5;
        }
        return 0;
    }

    /**
     * Sets the dimensions of the level.
     * @author Alanah Öster berg Coleman
     */
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    /**
     * Retrieves the dimensions of the level.
     * @author Alanah Öster berg Coleman
     */
    public String getDimension() {
        return dimension;
    }


}

