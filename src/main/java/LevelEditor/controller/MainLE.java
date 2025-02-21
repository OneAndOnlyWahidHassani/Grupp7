package LevelEditor.controller;

public class MainLE {

    private String currentLevelName;
    private String currentTheme;
    private String dimension;

    /**
     * Sparar nivån med ett namn och tema.
     * @param levelName Namnet på banan
     * @param selectedTheme Det valda temat
     */
    public void saveLevel(String levelName, String selectedTheme, String dimension) {
        this.currentLevelName = levelName;
        this.currentTheme = selectedTheme;
        this.dimension = dimension;
        System.out.println("Nivå sparad: " + levelName + " med tema: " + selectedTheme + "Dimension: " + dimension);

        // Här kan du lägga till kod för att spara nivån i en fil/databas om det behövs
    }

}
