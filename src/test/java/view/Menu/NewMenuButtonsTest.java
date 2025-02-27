package view.Menu;

import control.MainProgram;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.service.finder.WindowFinder;
import org.testfx.util.WaitForAsyncUtils;
import view.AudioPlayer;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.testfx.api.FxAssert.verifyThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NewMenuButtonsTest extends ApplicationTest {
    private MainProgram mainProgram;
    private Random random;
    private Menu menu;
    private AudioPlayer audioPlayer;
    private RightPanel panel;

    @Override
    public void start(Stage stage) {
        MockitoAnnotations.openMocks(this);
        random = new Random();
        mainProgram = new MainProgram();
        mainProgram = mock(MainProgram.class);
        try {
            mainProgram.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp()
    {
        mainProgram = mock(MainProgram.class);
        audioPlayer = mock(AudioPlayer.class);
        panel = mock(RightPanel.class);
        menu = new Menu(mainProgram, audioPlayer, panel);
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
        verifyThat(window("campaignScene"), WindowMatchers.isShowing());
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