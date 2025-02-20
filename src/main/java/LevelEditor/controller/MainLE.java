package LevelEditor.controller;

public class MainLE {

    private String currentLevelName;
    private String currentTheme;

    /**
     * Sparar nivån med ett namn och tema.
     * @param levelName Namnet på banan
     * @param selectedTheme Det valda temat
     */
    public void saveLevel(String levelName, String selectedTheme) {
        this.currentLevelName = levelName;
        this.currentTheme = selectedTheme;
        System.out.println("Nivå sparad: " + levelName + " med tema: " + selectedTheme);

        // Här kan du lägga till kod för att spara nivån i en fil/databas om det behövs
    }

    /**
     * Byter till ett nytt tema.
     * @param selectedTheme Det valda temat (t.ex. cloud, desert, etc.)
     */
    public void changeToTheme(String selectedTheme) {
        // Uppdatera det aktuella temat
        this.currentTheme = selectedTheme;
        System.out.println("Bytte tema till: " + selectedTheme);

        // Här kan du lägga till logik för att faktiskt ändra temat i applikationen,
        // till exempel genom att uppdatera bakgrundsbilder, knappar eller andra visuella element
    }

    /**
     * Byter till editor-scenen (simulerad här med en utskrift)
     */
    public void changeToEditor() {
        System.out.println("Byter till editor med nivå: " + currentLevelName + " och tema: " + currentTheme);

        // Här borde koden finnas för att faktiskt byta scen i JavaFX
        // T.ex. mainProgram.changeScene(editorScene);
    }
}
