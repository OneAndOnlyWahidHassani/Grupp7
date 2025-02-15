package view.Menu;

import control.MainProgram;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import view.AudioPlayer;

import java.io.IOException;

public class SelectWorldMap extends Pane {
    private MainProgram mainProgram;
    private Image selectWorldMap;
    private Image map1;
    private Image map1Resize;
    private Image map2;
    private Image map2Resize;
    private Image map3;
    private Image map3Resize;
    private Image map4;
    private Image map4Resize;
    private Image map5;
    private Image map5Resize;
    private Image map6;
    private Image map6Resize;
    private Image returnImage;
    private Image returnResize;
    private AudioPlayer audioPlayer;
    SelectLevel selectLevel;

    /**
     * Konstruktor som tar emot mainProgram och audioPlayer och kör några metoder för att
     * sätta bilder och knappar
     *
     * @param mainProgram tas emot och instansvariabeln sätts
     * @param audioPlayer tas emot och instansvariabeln sätts
     */
    public SelectWorldMap(MainProgram mainProgram, AudioPlayer audioPlayer) {
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        setBackground();
        setupImages();
        addButtons();
    }

    /**
     * Metod som länkar Image-objekten till png-filer
     */
    public void setupImages() {
        selectWorldMap = new Image("file:files/texts/SelectWorldMap.png", 800, 600, false, false);
        map1 = new Image("file:files/texts/Map1.png", 250, 30, false, false);
        map1Resize = new Image("file:files/texts/Map1.png", 255, 33, false, false);
        map2 = new Image("file:files/texts/Map2.png", 250, 30, false, false);
        map2Resize = new Image("file:files/texts/Map2.png", 255, 33, false, false);
        map3 = new Image("file:files/texts/Map3.png", 250, 30, false, false);
        map3Resize = new Image("file:files/texts/Map3.png", 255, 33, false, false);
        map4 = new Image("file:files/texts/map4.png", 250, 30, false, false);
        map4Resize = new Image("file:files/texts/map4.png", 255, 33, false, false);
        map5 = new Image("file:files/texts/map5.png", 250, 30, false, false);
        map5Resize = new Image("file:files/texts/map5.png", 255, 33, false, false);
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
        ImageView dimensionView = new ImageView(selectWorldMap);
        dimensionView.setStyle("fx-background-color: transparent;");


        ImageView map1View = new ImageView(map1);
        map1View.setStyle("fx-background-color: transparent;");
        map1View.setTranslateX(275);
        map1View.setTranslateY(150);
        map1View.toFront();
        map1View.setPickOnBounds(true);
        map1View.setOnMouseEntered(e -> {
            map1View.setImage(map1Resize);
            map1View.setTranslateX(273);
            map1View.setTranslateY(147);
        });
        map1View.setOnMouseExited(e -> {
            map1View.setImage(map1);
            map1View.setTranslateX(275);
            map1View.setTranslateY(150);
        });
        map1View.setOnMouseClicked(e -> {
            mainProgram.selectLevelMap(1);
            audioPlayer.playButtonSound();
        });

        ImageView map2View = new ImageView(map2);
        map2View.setStyle("fx-background-color: transparent;");
        map2View.setTranslateX(275);
        map2View.setTranslateY(200);
        map2View.toFront();
        map2View.setPickOnBounds(true);
        map2View.setOnMouseEntered(e -> {
            map2View.setImage(map2Resize);
            map2View.setTranslateX(273);
            map2View.setTranslateY(197);
        });
        map2View.setOnMouseExited(e -> {
            map2View.setImage(map2);
            map2View.setTranslateX(275);
            map2View.setTranslateY(200);
        });
        map2View.setOnMouseClicked(e -> {
            mainProgram.selectLevelMap(2);
            audioPlayer.playButtonSound();
        });

        ImageView map3View = new ImageView(map3);
        map3View.setStyle("fx-background-color: transparent;");
        map3View.setTranslateX(275);
        map3View.setTranslateY(250);
        map3View.toFront();
        map3View.setPickOnBounds(true);
        map3View.setOnMouseEntered(e -> {
            map3View.setImage(map3Resize);
            map3View.setTranslateX(273);
            map3View.setTranslateY(247);
        });
        map3View.setOnMouseExited(e -> {
            map3View.setImage(map3);
            map3View.setTranslateX(275);
            map3View.setTranslateY(250);
        });
        map3View.setOnMouseClicked(e -> {
            mainProgram.selectLevelMap(3);
            audioPlayer.playButtonSound();
        });

        ImageView map4View = new ImageView(map4);
        map4View.setStyle("fx-background-color: transparent;");
        map4View.setTranslateX(275);
        map4View.setTranslateY(300);
        map4View.toFront();
        map4View.setPickOnBounds(true);
        map4View.setOnMouseEntered(e -> {
            map4View.setImage(map4Resize);
            map4View.setTranslateX(273);
            map4View.setTranslateY(297);
        });
        map4View.setOnMouseExited(e -> {
            map4View.setImage(map4);
            map4View.setTranslateX(275);
            map4View.setTranslateY(300);
        });
        map4View.setOnMouseClicked(e -> {
            mainProgram.selectLevelMap(4);
            audioPlayer.playButtonSound();
        });

        ImageView map5View = new ImageView(map5);
        map5View.setStyle("fx-background-color: transparent;");
        map5View.setTranslateX(275);
        map5View.setTranslateY(350);
        map5View.toFront();
        map5View.setPickOnBounds(true);
        map5View.setOnMouseEntered(e -> {
            map5View.setImage(map5Resize);
            map5View.setTranslateX(273);
            map5View.setTranslateY(347);
        });
        map5View.setOnMouseExited(e -> {
            map5View.setImage(map5);
            map5View.setTranslateX(275);
            map5View.setTranslateY(350);
        });
        map5View.setOnMouseClicked(e -> {
            mainProgram.selectLevelMap(5);
            audioPlayer.playButtonSound();
        });

        ImageView map6View = new ImageView(map6);
        map6View.setStyle("fx-background-color: transparent;");
        map6View.setTranslateX(275);
        map6View.setTranslateY(400);
        map6View.toFront();
        map6View.setPickOnBounds(true);
        map6View.setOnMouseEntered(e -> {
            map6View.setImage(map6Resize);
            map6View.setTranslateX(273);
            map6View.setTranslateY(397);
        });
        map6View.setOnMouseExited(e -> {
            map6View.setImage(map6);
            map6View.setTranslateX(275);
            map6View.setTranslateY(400);
        });
        map6View.setOnMouseClicked(e -> {
            mainProgram.selectLevelMap(6);
            audioPlayer.playButtonSound();
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
            mainProgram.changeToMenu();
            audioPlayer.playButtonSound();
        });

        getChildren().addAll(dimensionView, map1View, map2View, map3View, map4View, map5View, map6View, returnView);
    }
}
