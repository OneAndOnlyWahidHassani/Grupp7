package control;

import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import view.GameOverScreen;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LivesTest extends ApplicationTest {

    private MainProgram mainProgram;

    @Override
    public void start(Stage stage) {
        mainProgram = new MainProgram();
        try {
            mainProgram.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp() throws Exception {
        ApplicationTest.launch(MainProgram.class);
    }

    @Test
    public void testGameOverScreenDisplayed() throws NoSuchFieldException, IllegalAccessException {
        // Simulate the condition where the player's lives reach zero
        mainProgram.gameOver();

        Field field = MainProgram.class.getDeclaredField("mainPaneCampaign");
        field.setAccessible(true);
        Pane mainPaneCampaign = (Pane) field.get(mainProgram);

        assertTrue(mainPaneCampaign.getChildren().stream()
                .anyMatch(node -> node instanceof GameOverScreen));
    }
}