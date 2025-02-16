package view;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

public class WorldIntroAnimationTest extends ApplicationTest {

    private WorldIntroAnimation worldIntroAnimation;

    @Override
    public void start(Stage stage) {
        worldIntroAnimation = new WorldIntroAnimation("1");
        Scene scene = new Scene(worldIntroAnimation);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testWorldIntroAnimation() {
        ImageView imageView = (ImageView) worldIntroAnimation.getChildren().get(0);
        assertNotNull(imageView);
        assertEquals("file:files/worlds/World1.png", imageView.getImage().getUrl());
    }  }