package LevelEditor.view;

import control.MainProgram;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import view.AudioPlayer;

import java.io.FileNotFoundException;

public class SetUp extends Pane {

    private MainProgram mainProgram;
    private Image titel;
    private Image chooseDimension;
    private Image tenByTen;
    private Image tenByTenResize;
    private Image fourteen;
    private Image fourteenResize;
    private Image eighteen;
    private Image eighteenResize;
    private Image returnImage;
    private Image returnResize;
    private AudioPlayer audioPlayer;

    //Button
    private Image RightButton;
    private Image LeftButton;

    //Themes
    private Image[] themes;
    private int currentThemeIndex = 0;
    private ImageView themeView;


    /**
     * Konstruktor som tar emot mainProgram och audioPlayer och kör några metoder för att
     * sätta bilder och knappar
     *
     * @param mainProgram tas emot och instansvariabeln sätts
     * @param audioPlayer tas emot och instansvariabeln sätts
     */
    public SetUp(MainProgram mainProgram, AudioPlayer audioPlayer) {
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
        titel = new Image("file:files/texts/lvl.png", 300, 80, false, true);
        chooseDimension = new Image("file:files/texts/ChooseDimension.png", 400, 300, false, false);
        tenByTen = new Image("file:files/texts/10x10.png", 200, 30, false, false);
        tenByTenResize = new Image("file:files/texts/10x10.png", 205, 33, false, false);
        fourteen = new Image("file:files/texts/14x14.png", 200, 30, false, false);
        fourteenResize = new Image("file:files/texts/14x14.png", 205, 33, false, false);
        eighteen = new Image("file:files/texts/18x18.png", 200, 30, false, false);
        eighteenResize = new Image("file:files/texts/18x18.png", 205, 33, false, false);

        //Button Choode
        RightButton = new Image("file:files/texts/RB.png", 430, 300, false, false);
        LeftButton = new Image("file:files/texts/LB.png", 430, 300, false, false);

        themes = new Image[]{
                new Image("file:files/theme/cloud.png", 125, 82, false, false),
                new Image("file:files/theme/desert.png", 125, 82, false, false),
                new Image("file:files/theme/forest.png", 115, 110, false, false),
                new Image("file:files/theme/lava.png", 115, 100, false, false),
                new Image("file:files/theme/space.png", 140, 90, false, false),
                new Image("file:files/theme/underground.png", 125, 100, false, false)
        };



        returnImage = new Image("file:files/texts/return.png", 250, 30, false, false);


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

        ImageView titelImageView = new ImageView(titel);
        titelImageView.setStyle("fx-background-color: transparent;");
        titelImageView.setTranslateX(250);
        titelImageView.setTranslateY(50);

        ImageView dimensionView = new ImageView(chooseDimension);
        dimensionView.setStyle("fx-background-color: transparent;");
        dimensionView.setTranslateX(200);
        dimensionView.setTranslateY(150);


        ImageView tenByTenView = new ImageView(tenByTen);
        tenByTenView.setStyle("fx-background-color: transparent;");
        tenByTenView.setTranslateX(275);
        tenByTenView.setTranslateY(200);
        tenByTenView.toFront();
        tenByTenView.setPickOnBounds(true);
        tenByTenView.setOnMouseEntered(e -> {
            tenByTenView.setImage(tenByTenResize);
            tenByTenView.setTranslateX(273);
            tenByTenView.setTranslateY(198);
        });
        tenByTenView.setOnMouseExited(e -> {
            tenByTenView.setImage(tenByTen);
            tenByTenView.setTranslateX(275);
            tenByTenView.setTranslateY(200);
        });
        tenByTenView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(10);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView fourteenView = new ImageView(fourteen);
        fourteenView.setStyle("fx-background-color: transparent;");
        fourteenView.setTranslateX(275);
        fourteenView.setTranslateY(250);
        fourteenView.toFront();
        fourteenView.setPickOnBounds(true);
        fourteenView.setOnMouseEntered(e -> {
            fourteenView.setImage(fourteenResize);
            fourteenView.setTranslateX(273);
            fourteenView.setTranslateY(248);
        });
        fourteenView.setOnMouseExited(e -> {
            fourteenView.setImage(fourteen);
            fourteenView.setTranslateX(275);
            fourteenView.setTranslateY(250);
        });
        fourteenView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(14);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView eighteenView = new ImageView(eighteen);
        eighteenView.setStyle("fx-background-color: transparent;");
        eighteenView.setTranslateX(275);
        eighteenView.setTranslateY(300);
        eighteenView.toFront();
        eighteenView.setPickOnBounds(true);
        eighteenView.setOnMouseEntered(e -> {
            eighteenView.setImage(eighteenResize);
            eighteenView.setTranslateX(273);
            eighteenView.setTranslateY(298);
        });
        eighteenView.setOnMouseExited(e -> {
            eighteenView.setImage(eighteen);
            eighteenView.setTranslateX(275);
            eighteenView.setTranslateY(300);
        });
        eighteenView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(18);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });


        ImageView RButtonView = new ImageView(RightButton);
        RButtonView.setStyle("fx-background-color: transparent;");
        RButtonView.setTranslateX(350);
        RButtonView.setTranslateY(350);
        RButtonView.toFront();
        RButtonView.setPickOnBounds(true);
        RButtonView.setOnMouseEntered(e -> {
            RButtonView.setImage(RightButton);
            RButtonView.setTranslateX(350);
            RButtonView.setTranslateY(350);
        });
        RButtonView.setOnMouseExited(e -> {
            RButtonView.setImage(RightButton);
            RButtonView.setTranslateX(350);
            RButtonView.setTranslateY(350);
        });
        RButtonView.setOnMouseClicked(e -> {
                nextTheme();
                audioPlayer.playButtonSound();

        });


        ImageView LButtonView = new ImageView(LeftButton);
        LButtonView.setStyle("fx-background-color: transparent;");
        LButtonView.setTranslateX(50);
        LButtonView.setTranslateY(350);
        LButtonView.toFront();
        LButtonView.setPickOnBounds(true);
        LButtonView.setOnMouseEntered(e -> {
            LButtonView.setImage(LeftButton);
            LButtonView.setTranslateX(50);
            LButtonView.setTranslateY(350);
        });
        LButtonView.setOnMouseExited(e -> {
            LButtonView.setImage(LeftButton);
            LButtonView.setTranslateX(50);
            LButtonView.setTranslateY(350);
        });
        LButtonView.setOnMouseClicked(e -> {
                previousTheme();
                audioPlayer.playButtonSound();

        });


        themeView = new ImageView(themes[currentThemeIndex]);
        themeView.setStyle("fx-background-color: transparent;");
        themeView.setTranslateX(350);
        themeView.setTranslateY(400);
        themeView.toFront();
        themeView.setPickOnBounds(true);


        ImageView returnView = new ImageView(returnImage);
        returnView.setStyle("fx-background-color: transparent;");
        returnView.setTranslateX(500);
        returnView.setTranslateY(550);
        returnView.toFront();
        returnView.setPickOnBounds(true);
        returnView.setOnMouseEntered(e -> {
            returnView.setImage(returnResize);
            returnView.setTranslateX(498);
            returnView.setTranslateY(548);
        });
        returnView.setOnMouseExited(e -> {
            returnView.setImage(returnImage);
            returnView.setTranslateX(500);
            returnView.setTranslateY(550);
        });
        returnView.setOnMouseClicked(e -> {
            mainProgram.changeToMenu();
            audioPlayer.playButtonSound();
        });


        getChildren().addAll(titelImageView, dimensionView, tenByTenView, fourteenView, eighteenView, RButtonView, themeView, LButtonView, returnView);
    }

    private void nextTheme() {
        currentThemeIndex = (currentThemeIndex + 1) % themes.length;
        themeView.setImage(themes[currentThemeIndex]);
    }

    private void previousTheme() {
        currentThemeIndex = (currentThemeIndex - 1 + themes.length) % themes.length;
        themeView.setImage(themes[currentThemeIndex]);
    }
}
