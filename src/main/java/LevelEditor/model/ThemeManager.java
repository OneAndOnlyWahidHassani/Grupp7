package LevelEditor.model;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ThemeManager {

    private static final String BASE_PATH = "file:files/themes/";
    private static final String IMAGE_EXTENSION = ".png";

    private final Map<String, Image> themeImages;

    public ThemeManager() {
        themeImages = new HashMap<>();
        loadThemes();
    }

    /**
     * Laddar in alla teman i en hashmap
     */
    private void loadThemes() {
        String[] themes = {"Cloud", "Desert", "Forest", "Lava", "Space", "Underground"};
        for (String theme : themes) {
            themeImages.put(theme, new Image(BASE_PATH + theme.toLowerCase() + IMAGE_EXTENSION, 200, 150, false, false));
        }
    }

    /**
     * Hämtar en bild baserat på temat.
     * @param theme Namnet på temat
     * @return En Image av temat, eller en default-bild om temat saknas
     */
    public Image getThemeImage(String theme) {
        return themeImages.getOrDefault(theme, new Image(BASE_PATH + "default.png", 200, 150, false, false));
    }
}
