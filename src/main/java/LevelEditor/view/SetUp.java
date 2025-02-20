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
    private Image Theme;
    private Image ThemeResize;
    private Image returnImage;
    private Image returnResize;
    private AudioPlayer audioPlayer;

    //Button
    private Image RightButton;
    private Image LeftButton;

    //Themes
    private Image cloud;
    private Image cloudResize;
    private Image desert;
    private Image desertResize;
    private Image forest;
    private Image forestResize;
    private Image lava;
    private Image lavaResize;
    private Image space;
    private Image spaceResize;
    private Image underground;
    private Image undergroundResize;


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

        Theme = new Image("file:files/texts/theme/cloud.png", 250, 30, false, false);
        ThemeResize = new Image("file:files/texts/Level1.png", 255, 33, false, false);

        //Button Choode
        RightButton = new Image("file:files/texts/RB.png", 430, 300, false, false);
        LeftButton = new Image("file:files/texts/LB.png", 430, 300, false, false);


        //Themes
        cloud = new Image("file:files/texts/Level1.png", 250, 30, false, false);
        cloudResize = new Image("file:files/texts/Level1.png", 255, 33, false, false);

        desert = new Image("file:files/texts/Level2.png", 250, 30, false, false);
        desertResize = new Image("file:files/texts/Level2.png", 255, 33, false, false);

        forest = new Image("file:files/texts/Level3.png", 250, 30, false, false);
        forestResize = new Image("file:files/texts/Level3.png", 255, 33, false, false);

        lava = new Image("file:files/texts/Level4.png", 250, 30, false, false);
        lavaResize = new Image("file:files/texts/Level4.png", 255, 33, false, false);

        space = new Image("file:files/texts/Level5.png", 250, 30, false, false);
        spaceResize = new Image("file:files/texts/Level5.png", 255, 33, false, false);

        underground = new Image("file:files/texts/Level1.png", 250, 30, false, false);
        undergroundResize = new Image("file:files/texts/Level1.png", 255, 33, false, false);

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
        RButtonView.setTranslateX(300);
        RButtonView.setTranslateY(300);
        RButtonView.toFront();
        RButtonView.setPickOnBounds(true);
        RButtonView.setOnMouseEntered(e -> {
            RButtonView.setImage(RightButton);
            RButtonView.setTranslateX(300);
            RButtonView.setTranslateY(300);
        });
        RButtonView.setOnMouseExited(e -> {
            RButtonView.setImage(RightButton);
            RButtonView.setTranslateX(300);
            RButtonView.setTranslateY(300);
        });
        RButtonView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(28);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        //Dessert
        ImageView LButtonView = new ImageView(LeftButton);
        LButtonView.setStyle("fx-background-color: transparent;");
        LButtonView.setTranslateX(10);
        LButtonView.setTranslateY(300);
        LButtonView.toFront();
        LButtonView.setPickOnBounds(true);
        LButtonView.setOnMouseEntered(e -> {
            LButtonView.setImage(LeftButton);
            LButtonView.setTranslateX(10);
            LButtonView.setTranslateY(300);
        });
        LButtonView.setOnMouseExited(e -> {
            LButtonView.setImage(LeftButton);
            LButtonView.setTranslateX(10);
            LButtonView.setTranslateY(300);
        });
        LButtonView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(28);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });



        //CLOUD
      /*  ImageView RButtonView = new ImageView(cloud);
        RButtonView.setStyle("fx-background-color: transparent;");
        RButtonView.setTranslateX(100);
        RButtonView.setTranslateY(200);
        RButtonView.toFront();
        RButtonView.setPickOnBounds(true);
        RButtonView.setOnMouseEntered(e -> {
            RButtonView.setImage(cloudResize);
            RButtonView.setTranslateX(100);
            RButtonView.setTranslateY(200);
        });
        RButtonView.setOnMouseExited(e -> {
            RButtonView.setImage(cloud);
            RButtonView.setTranslateX(100);
            RButtonView.setTranslateY(200);
        });
        RButtonView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(28);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        //Dessert
        ImageView LButtonView = new ImageView(desert);
        LButtonView.setStyle("fx-background-color: transparent;");
        LButtonView.setTranslateX(100);
        LButtonView.setTranslateY(250);
        LButtonView.toFront();
        LButtonView.setPickOnBounds(true);
        LButtonView.setOnMouseEntered(e -> {
            LButtonView.setImage(desertResize);
            LButtonView.setTranslateX(100);
            LButtonView.setTranslateY(250);
        });
        LButtonView.setOnMouseExited(e -> {
            LButtonView.setImage(desert);
            LButtonView.setTranslateX(100);
            LButtonView.setTranslateY(250);
        });
        LButtonView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(28);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });


        //Forest
        ImageView forestView = new ImageView(forest);
        forestView.setStyle("fx-background-color: transparent;");
        forestView.setTranslateX(100);
        forestView.setTranslateY(300);
        forestView.toFront();
        forestView.setPickOnBounds(true);
        forestView.setOnMouseEntered(e -> {
            forestView.setImage(forestResize);
            forestView.setTranslateX(100);
            forestView.setTranslateY(300);
        });
        forestView.setOnMouseExited(e -> {
            forestView.setImage(forest);
            forestView.setTranslateX(100);
            forestView.setTranslateY(300);
        });
        forestView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(28);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });


        //Lava
        ImageView lavaView = new ImageView(lava);
        lavaView.setStyle("fx-background-color: transparent;");
        lavaView.setTranslateX(100);
        lavaView.setTranslateY(350);
        lavaView.toFront();
        lavaView.setPickOnBounds(true);
        lavaView.setOnMouseEntered(e -> {
            lavaView.setImage(lavaResize);
            lavaView.setTranslateX(100);
            lavaView.setTranslateY(350);
        });
        lavaView.setOnMouseExited(e -> {
            lavaView.setImage(lava);
            lavaView.setTranslateX(100);
            lavaView.setTranslateY(350);
        });
        lavaView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(28);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });


        //Space

        ImageView spaceView = new ImageView(space);
        spaceView.setStyle("fx-background-color: transparent;");
        spaceView.setTranslateX(100);
        spaceView.setTranslateY(400);
        spaceView.toFront();
        spaceView.setPickOnBounds(true);
        spaceView.setOnMouseEntered(e -> {
            spaceView.setImage(spaceResize);
            spaceView.setTranslateX(100);
            spaceView.setTranslateY(400);
        });
        spaceView.setOnMouseExited(e -> {
            spaceView.setImage(space);
            spaceView.setTranslateX(100);
            spaceView.setTranslateY(400);
        });
        spaceView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(28);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        //Underground
        ImageView undergroundView = new ImageView(underground);
        undergroundView.setStyle("fx-background-color: transparent;");
        undergroundView.setTranslateX(100);
        undergroundView.setTranslateY(450);
        undergroundView.toFront();
        undergroundView.setPickOnBounds(true);
        undergroundView.setOnMouseEntered(e -> {
            undergroundView.setImage(undergroundResize);
            undergroundView.setTranslateX(100);
            undergroundView.setTranslateY(450);
        });
        undergroundView.setOnMouseExited(e -> {
            undergroundView.setImage(underground);
            undergroundView.setTranslateX(100);
            undergroundView.setTranslateY(450);
        });
        undergroundView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(28);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }); */


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





        getChildren().addAll(titelImageView, dimensionView, tenByTenView, fourteenView, eighteenView,RButtonView, LButtonView, returnView);
        //RButtonView, LButtonView, forestView, lavaView, spaceView, undergroundView,
    }
}
