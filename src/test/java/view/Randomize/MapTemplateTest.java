package view.Randomize;

import control.MainProgram;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.MazeGeneration.GenerateNextLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.FileNotFoundException;
import java.util.List;
import view.Randomize.MapTemplate.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//testar LOG3.3
class MapTemplateTest extends ApplicationTest {

    private MainProgram mainProgram;
    private MapTemplate mapTemplate;
    private GenerateNextLevel generateNextLevelMock;
    private Label testCollectible;

    /**
     * Tester från sprint 1. Inte funktionella längre?
     */

    @BeforeEach
    void setUp() throws FileNotFoundException {
        mainProgram = Mockito.mock(MainProgram.class);
        // Mocka GenerateNextLevel eftersom vi inte testar nivågenerering här
        generateNextLevelMock = mock(GenerateNextLevel.class);

        // Skapa en testlabyrint
        int[][] testLevel = {
                {0, 1, 1, 0},
                {1, 4, 1, 1},  // 4 = collectible på väg
                {1, 1, 4, 1},
                {0, 1, 1, 0}
        };

        // Skapa en instans av MapTemplate
        mapTemplate = new MapTemplate(testLevel, mainProgram, generateNextLevelMock);
        testCollectible = mapTemplate.addCollectible();
    }

    /*@Test
    void testCollectiblesGeneratedOnPath() {
        // Hämta alla collectibles som placerades i världen
        List<Label> collectibles = mapTemplate.getCollectibles();

        // Kontrollera att varje collectible har en bild och är tillagd korrekt
        for (Label collectible : collectibles) {
            assertNotNull(collectible.getGraphic(), "Collectible saknar bild");
        }

        // Kontrollera att antalet collectibles inte är noll
        assertFalse(collectibles.isEmpty(), "Inga collectibles genererades");


    }

    //Är inte klar, skulle testa LOG8.5
    @Test
    void collectibleShouldDisappearWhenTouchedDuringGameplay() {
        // Simulate game start
        mapTemplate.startLevel();

        // Create mock event targeting our test collectible
        MouseEvent event = mock(MouseEvent.class);
        when(event.getSource()).thenReturn(testCollectible);



        // Trigger the handler
        //mapTemplate.getMouseListener().handle(event);

        // Verify through public getters
        assertFalse(testCollectible.isVisible(),
            "Collectible should become invisible");

        assertEquals(1, mapTemplate.getCollectiblesObtained(),
            "Collected counter should increment");

        assertTrue(mapTemplate.isAllCollectiblesObtained(),
            "Should flag all collected when last one obtained");
    }

    //Är inte klar, skulle testa LOG8.5
    @Test
    void collectibleShouldNotBeCollectedWhenGameNotActive() {
        MouseEvent event = mock(MouseEvent.class);
        when(event.getSource()).thenReturn(testCollectible);

        //mapTemplate.getMouseListener().handle(event);

        assertTrue(testCollectible.isVisible(),
            "Collectible should remain visible");

        assertEquals(0, mapTemplate.getCollectiblesObtained(),
            "Counter should not increment");

        assertFalse(mapTemplate.isAllCollectiblesObtained(),
            "All collected flag should remain false");
    }*/

}
