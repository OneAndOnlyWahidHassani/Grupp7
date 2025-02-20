package control;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import model.Maps.World1Maps;
import model.Maps.World2Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampaignModeTest {

    private World1Maps world1Maps;
    private World2Maps world2Maps;
    private int[][] currentLevel;

    /*@BeforeEach
    public void setUp() {
        world1Maps = new World1Maps();
        world2Maps = new World2Maps();
    }

    @Test
    public void testProgressionToNextWorld() {
        // Set the current level to the final level of World 1
        currentLevel = world1Maps.getLevel15();

        // Simulate completion of the final level
        completeLevel();

        // Check if the game progresses to the first level of World 2
        assertNotNull(currentLevel, "Current level should not be null after progression");
        assertArrayEquals(world2Maps.getLevel21(), currentLevel, "The game should progress to the first level of World 2");
    }

    private void completeLevel() {
        currentLevel = world2Maps.getLevel21();
    }*/
}