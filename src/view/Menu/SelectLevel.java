package view.Menu;

import control.MainProgram;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import view.AudioPlayer;

import java.io.IOException;

public class SelectLevel extends Pane{
    private MainProgram mainProgram;
    private Image selectLevel;
    private Image level1;
    private Image level1Resize;
    private Image level2;
    private Image level2Resize;
    private Image level3;
    private Image level3Resize;
    private Image level4;
    private Image level4Resize;
    private Image level5;
    private Image level5Resize;
    private Image map6;
    private Image map6Resize;
    private Image returnImage;
    private Image returnResize;
    private AudioPlayer audioPlayer;
    private int selectedMap;


    /**
     * Konstruktor som tar emot mainProgram och audioPlayer och kör några metoder för att
     * sätta bilder och knappar
     *
     * @param mainProgram tas emot och instansvariabeln sätts
     * @param audioPlayer tas emot och instansvariabeln sätts
     */
    public SelectLevel(MainProgram mainProgram, AudioPlayer audioPlayer, int map) {
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        this.selectedMap = map;
        setBackground();
        setupImages();
        addButtons();
    }

    /**
     * Metod som länkar Image-objekten till png-filer
     */
    public void setupImages() {
        selectLevel = new Image("file:files/texts/SelectLevel.png", 800, 600, false, false);
        level1 = new Image("file:files/texts/level1.png", 250, 30, false, false);
        level1Resize = new Image("file:files/texts/level1.png", 255, 33, false, false);
        level2 = new Image("file:files/texts/level2.png", 250, 30, false, false);
        level2Resize = new Image("file:files/texts/level2.png", 255, 33, false, false);
        level3 = new Image("file:files/texts/level3.png", 250, 30, false, false);
        level3Resize = new Image("file:files/texts/level3.png", 255, 33, false, false);
        level4 = new Image("file:files/texts/level4.png", 250, 30, false, false);
        level4Resize = new Image("file:files/texts/level4.png", 255, 33, false, false);
        level5 = new Image("file:files/texts/level5.png", 250, 30, false, false);
        level5Resize = new Image("file:files/texts/level5.png", 255, 33, false, false);
        map6 = new Image("file:files/texts/map6.png", 250, 30, false, false);
        map6Resize = new Image("file:files/texts/map6.png", 255, 33, false, false);
        returnImage = new Image("file:files/texts/return.png", 250, 30, false, false);
        returnResize = new Image("file:files/texts/return.png", 255, 33, false, false);
    }

    /**
     * Metod som sätter bakgrundsbilden
     */
    public void setBackground() {
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg", 800, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }

    /**
     * Metod som placerar bilderna som klickbara ImageViews i scenen med events för knapptryck och hovering.
     * Bilderna förstoras när man hovrar över dem och scenen byts när man trycker på dem.
     */
    public void addButtons() {
        ImageView dimensionView = new ImageView(selectLevel);
        dimensionView.setStyle("fx-background-color: transparent;");


        ImageView level1View = new ImageView(level1);
        level1View.setStyle("fx-background-color: transparent;");
        level1View.setTranslateX(275);
        level1View.setTranslateY(150);
        level1View.toFront();
        level1View.setPickOnBounds(true);
        level1View.setOnMouseEntered(e -> {
            level1View.setImage(level1Resize);
            level1View.setTranslateX(273);
            level1View.setTranslateY(147);
        });
        level1View.setOnMouseExited(e -> {
            level1View.setImage(level1);
            level1View.setTranslateX(275);
            level1View.setTranslateY(150);
        });
        level1View.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToCampaign();
                audioPlayer.playButtonSound();
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView level2View = new ImageView(level2);
        level2View.setStyle("fx-background-color: transparent;");
        level2View.setTranslateX(275);
        level2View.setTranslateY(200);
        level2View.toFront();
        level2View.setPickOnBounds(true);
        level2View.setOnMouseEntered(e -> {
            level2View.setImage(level2Resize);
            level2View.setTranslateX(273);
            level2View.setTranslateY(197);
        });
        level2View.setOnMouseExited(e -> {
            level2View.setImage(level2);
            level2View.setTranslateX(275);
            level2View.setTranslateY(200);
        });
        level2View.setOnMouseClicked(e -> {
            try {
                mainProgram.nextWorld2Level(2, 3);
                audioPlayer.playButtonSound();
            } catch (IOException | InterruptedException | ClassNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView level3View = new ImageView(level3);
        level3View.setStyle("fx-background-color: transparent;");
        level3View.setTranslateX(275);
        level3View.setTranslateY(250);
        level3View.toFront();
        level3View.setPickOnBounds(true);
        level3View.setOnMouseEntered(e -> {
            level3View.setImage(level3Resize);
            level3View.setTranslateX(273);
            level3View.setTranslateY(247);
        });
        level3View.setOnMouseExited(e -> {
            level3View.setImage(level3);
            level3View.setTranslateX(275);
            level3View.setTranslateY(250);
        });
        level3View.setOnMouseClicked(e -> {
            try {
                mainProgram.nextWorld3Level(3, 3);
                audioPlayer.playButtonSound();
            } catch (IOException | InterruptedException | ClassNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView level4View = new ImageView(level4);
        level4View.setStyle("fx-background-color: transparent;");
        level4View.setTranslateX(275);
        level4View.setTranslateY(300);
        level4View.toFront();
        level4View.setPickOnBounds(true);
        level4View.setOnMouseEntered(e -> {
            level4View.setImage(level4Resize);
            level4View.setTranslateX(273);
            level4View.setTranslateY(297);
        });
        level4View.setOnMouseExited(e -> {
            level4View.setImage(level4);
            level4View.setTranslateX(275);
            level4View.setTranslateY(300);
        });
        level4View.setOnMouseClicked(e -> {
            try {
                mainProgram.nextWorld4Level(4, 3);
                audioPlayer.playButtonSound();
            } catch (IOException | InterruptedException | ClassNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView level5View = new ImageView(level5);
        level5View.setStyle("fx-background-color: transparent;");
        level5View.setTranslateX(275);
        level5View.setTranslateY(350);
        level5View.toFront();
        level5View.setPickOnBounds(true);
        level5View.setOnMouseEntered(e -> {
            level5View.setImage(level5Resize);
            level5View.setTranslateX(273);
            level5View.setTranslateY(347);
        });
        level5View.setOnMouseExited(e -> {
            level5View.setImage(level5);
            level5View.setTranslateX(275);
            level5View.setTranslateY(350);
        });
        level5View.setOnMouseClicked(e -> {
            try {
                mainProgram.nextWorld5Level(5, 3);
                audioPlayer.playButtonSound();
            } catch (IOException | InterruptedException | ClassNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView returnView = new ImageView(returnImage);
        returnView.setStyle("fx-background-color: transparent;");
        returnView.setTranslateX(300);
        returnView.setTranslateY(450);
        returnView.toFront();
        returnView.setPickOnBounds(true);
        returnView.setOnMouseEntered(e -> {
            returnView.setImage(returnResize);
            returnView.setTranslateX(298);
            returnView.setTranslateY(448);
        });
        returnView.setOnMouseExited(e -> {
            returnView.setImage(returnImage);
            returnView.setTranslateX(300);
            returnView.setTranslateY(450);
        });
        returnView.setOnMouseClicked(e -> {
            mainProgram.selectWorldMap();
            audioPlayer.playButtonSound();
        });

        getChildren().addAll(dimensionView, level1View, level2View, level3View, level4View, level5View, returnView);
    }
}
