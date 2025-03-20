package view;

import control.MainProgram;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Maps.World1Maps;
import model.Maps.World2Maps;
import org.junit.jupiter.api.*;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import view.Campaign.WorldTemplate;
import view.Menu.Menu;
import view.Menu.RightPanel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CampaignTests extends ApplicationTest {
    private MainProgram mainProgram;
    private Random random;
    private RightPanel rightPanel;

    @Override
    public void start(Stage stage) {
        random = new Random();
        mainProgram = new MainProgram();
        try {
            mainProgram.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp() {
    }

    /**
     * Testar om det går att stänga av musiken
     * Testar dock inte om musiken faktiskt stängs av, utan testar om
     * musiknappens ikon ändras
     * @author Elvira Grubb
     */
    @Test
    public void testTurnoffSound(){
        WaitForAsyncUtils.waitForFxEvents();
        moveToAndClickOn("introButton");
        moveToAndClickOn("campaignButton");
        moveToAndClickOn("musicLabel");

        Label musicLabel = lookup("#musicLabel").query();
        ImageView currentImage = (ImageView) musicLabel.getGraphic();
        String result = currentImage.getImage().getUrl();
        String expectedResult = ("file:files/soundbuttons/musicoff.png");
        assertEquals(expectedResult, result, "Expected result is " + expectedResult + " and the actual result is " + result);

        /*Label label = lookup("#soundLabel").query();
        assertNotNull(label, "Label är null :(");*/
    }

    /**
     * Testar om man förlorar ett liv när man kolliderar med en vägg genom att
     * checka att bilden till liven ändras
     * @author Elvira Grubb
     */
    @Test
    public void testLoseOneLife(){
        WaitForAsyncUtils.waitForFxEvents();
        moveToAndClickOn("introButton");
        moveToAndClickOn("campaignButton");
        sleep(200);
        moveToAndClickOn("startLabel");
        moveTo("#goalLabel");

        Label heartLabel = lookup("#heartLabel").query();
        ImageView currentImage = (ImageView) heartLabel.getGraphic();
        String result = currentImage.getImage().getUrl();
        String expectedResult = ("file:files/hearts/2heart.png");
        assertEquals(expectedResult, result, "Expected result is " + expectedResult + " and the actual result is " + result);

        sleep(500);
    }


    /**
     * Testar att spelet avslutas när användaren tappar tre liv
     * genom att cheka att GameOver animationen existerar
     * Checkar också att användaren kan komma tillbaka till menyn efter
     * game over
     * @author Elvira Grubb
     */
    @Test
    public void testGameOverWhenLosingThreeLives(){
        WaitForAsyncUtils.waitForFxEvents();
        moveToAndClickOn("introButton");
        moveToAndClickOn("campaignButton");
        sleep(200);
        moveToAndClickOn("startLabel");
        moveTo("#goalLabel");
        moveToAndClickOn("startLabel");
        moveTo("#goalLabel");
        moveToAndClickOn("startLabel");
        moveTo("#goalLabel");

        sleep(2000);

        ImageView gameOver = lookup("#gameOverAnimation").query();
        assertNotNull(gameOver, "Game Over animationen should be played");
        clickOn("#gameOverAnimation");
        Menu menu = lookup("#menu").query();
        assertNotNull(menu, "Menu should show");

        sleep(2000);
    }

    private void moveToAndClickOn(String buttonId){
        moveTo("#" + buttonId);
        clickOn();
    }
}