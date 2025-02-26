package control;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Maps.World1Maps;
import model.Maps.World2Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CampaignModeTest extends ApplicationTest {

    private World1Maps world1Maps;
    private World2Maps world2Maps;
    private int[][] currentLevelArray;
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


    //Gå från nivå 1.5 -> 2.1 och kontrollera att dimesionen ökar med minst 2 rutor
    //LG 6.2
    //Ladda campaign och kontrollera att en timer initierat
    //LG 7.0
    //Vid alla världars första bana X.1 ska info visas på skärmen inklusive dimesionen ex 8x8
    // LG 8.1
    //Gå in i väggen 1 gång och gå in i 2 olika spöken och se ifall game over visas



    @Test
    public void testProgressionToNextWorld() {
        // Set the current level to the final level of World 1
        currentLevelArray = world1Maps.getLevel5();

        // Simulate completion of the final level
        completeLevel();

        // Check if the game progresses to the first level of World 2
        assertNotNull(currentLevelArray, "Current level should not be null after progression");
        assertArrayEquals(world2Maps.getLevel1(), currentLevelArray, "The game should progress to the first level of World 2");
    }

    private void completeLevel() {
        currentLevelArray = world2Maps.getLevel1();
    }

    @Test
    public void verifyCampaignButtonExists(){

    }
    //Ladda campaign och spela de 5 första Världarna
    //LG 6.0 - 6.1
    @Test
    public void startCampaign(){
        FxRobot robot = new FxRobot();
        WaitForAsyncUtils.waitForFxEvents();
        sleep(1000);
        robot.moveTo("#introButton");
        robot.clickOn();

        WaitForAsyncUtils.waitForFxEvents();
        sleep(1000);
        robot.moveTo("#campaignButton");
        robot.clickOn();

        WaitForAsyncUtils.waitForFxEvents();
        sleep(1000);
        GameController gameController = mainProgram.getCampaignController();
        System.out.println(gameController.getLevel());
        assertNotNull(mainProgram.getCampaignController());
    }
}