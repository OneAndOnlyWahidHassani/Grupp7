package view.Campaign;

import javafx.application.Platform;
import org.junit.jupiter.api.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import control.MainProgram;
import view.AudioPlayer;
import view.Menu.RightPanel;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class World1TemplateCollisionTest {

    private World1Template world1Template;
    private MainProgram mainProgram;
    private RightPanel rightPanel;
    private AudioPlayer audioPlayer;

    /*@BeforeAll
    public static void setupJavaFX() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    public void setup() throws Exception {
        mainProgram = mock(MainProgram.class);
        rightPanel = mock(RightPanel.class);
        audioPlayer = mock(AudioPlayer.class);

        doNothing().when(rightPanel).changeHeartCounter(anyString());


        int[][] testLevel = {
                {2, 0, 3}  // 2 = start, 0 = wall, 3 = goal
        };

        world1Template = new TestWorld1Template(
                testLevel,
                1,              // currentLevel
                3,              // initial heartCrystals
                mainProgram,
                rightPanel,
                0,              // world
                audioPlayer,
                25              // seconds
        );
    }

    private static class TestWorld1Template extends World1Template {
        public TestWorld1Template(int[][] level, int currentLevel, int heartCrystals,
                                  MainProgram mainProgram, RightPanel rightPanel,
                                  int world, AudioPlayer audioPlayer, int seconds)
                throws FileNotFoundException {
            super(level, currentLevel, heartCrystals, mainProgram, rightPanel, world, audioPlayer, seconds);
        }

        @Override
        public void setBackground() {
        }

        @Override
        public void setupImages(int value) {
        }
    }

    @Test
    @DisplayName("Test Wall Collision Reduces Hearts")
    public void testWallCollisionReducesHearts() {
        // Start the level
        world1Template.startLevel();
        assertTrue(world1Template.isStartButtonPressed(), "Game should be started");
        assertEquals(3, world1Template.getHeartCrystals(), "Should start with 3 hearts");

        // Simulate wall collision
        Label mockWall = mock(Label.class);
        MouseEvent mockEvent = mock(MouseEvent.class);
        when(mockEvent.getSource()).thenReturn(mockWall);

        world1Template.enteredWall(mockEvent);

        // Verify heart reduction
        assertEquals(2, world1Template.getHeartCrystals(), "Should lose 1 heart after collision");
        verify(audioPlayer).playDeathSound();
        verify(rightPanel).changeHeartCounter("2");
    }*/
}