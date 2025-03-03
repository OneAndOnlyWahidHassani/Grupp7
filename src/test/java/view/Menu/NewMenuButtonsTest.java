package view.Menu;

import control.MainProgram;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.service.finder.WindowFinder;
import org.testfx.util.WaitForAsyncUtils;
import view.AudioPlayer;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NewMenuButtonsTest extends ApplicationTest {
    private MainProgram mainProgram;
    private Random random;
    private Menu menu;
    private AudioPlayer audioPlayer;
    private RightPanel panel;

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
    void setUp()
    {
        mainProgram = new MainProgram();
        audioPlayer = mock(AudioPlayer.class);
        menu = new Menu(mainProgram, audioPlayer, panel);
        try {
            panel = new RightPanel(mainProgram, "11", audioPlayer, null);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inte funktionellt
     */
    @Test
    void testCampaignButton()
    {
        WaitForAsyncUtils.waitForFxEvents();
        moveToAndClickOn("introButton");
        moveToAndClickOn("campaignButton");
        //verify(mainProgram).changeToCampaign();
    }

    /*@Test
    void testCampaignButton() throws FileNotFoundException
    {
        ImageView campaignButton = findButton(275, 200);
        assert campaignButton != null;

        fireClickEvent(campaignButton);

        verify(mainProgram).changeToCampaign();
        verify(audioPlayer).playLevelMusic("forest");
        verify(panel).setTheTime(25);
        verify(panel).resetTimerLabel();
    }

    @Test
    void testRandomizeButton() {
        ImageView randomizeButton = findButton(275, 250);
        assert randomizeButton != null;

        fireClickEvent(randomizeButton);

        verify(mainProgram).chooseDimension();
        verify(audioPlayer).playButtonSound();
    }

    @Test
    void testHelpButton() {
        ImageView helpButton = findButton(275, 300);
        assert helpButton != null;

        fireClickEvent(helpButton);

        verify(mainProgram).changeToHelp();
        verify(audioPlayer).playButtonSound();
    }*/



    private void moveToAndClickOn(String buttonId){
        moveTo("#" + buttonId);
        clickOn();
    }
}