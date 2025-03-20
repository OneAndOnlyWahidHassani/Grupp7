package LevelEditor.model;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
/**
 * Manages themes by loading and retrieving theme images.
 *
 * This class initializes a set of predefined themes and provides methods to fetch theme images.
 *
 * @author Alanah Öster berg Coleman
 */
public class ThemeManager {

    private static final String BASE_PATH = "file:files/themes/";
    private static final String IMAGE_EXTENSION = ".png";

    private final Map<String, Image> themeImages;
    /**
     * Constructs a ThemeManager and loads all available themes.
     */
    public ThemeManager() {
        themeImages = new HashMap<>();
        loadThemes();
    }
    /**
     * Loads all themes into a HashMap for quick access.
     */
    private void loadThemes() {
        String[] themes = {"Cloud", "Desert", "Forest", "Lava", "Space", "Underground"};
        for (String theme : themes) {
            themeImages.put(theme, new Image(BASE_PATH + theme.toLowerCase() + IMAGE_EXTENSION, 200, 150, false, false));
        }
    }

    /**
     * Retrieves an image based on the given theme.
     *
     * @param theme The name of the theme.
     * @return An Image representing the theme, or a default image if the theme is missing.
     */
    public Image getThemeImage(String theme) {
        return themeImages.getOrDefault(theme, new Image(BASE_PATH + "default.png", 200, 150, false, false));
    }
}
