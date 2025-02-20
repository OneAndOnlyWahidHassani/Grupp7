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
        chooseDimension = new Image("file:files/texts/ChooseDimension.png", 400, 300, false, false);
        tenByTen = new Image("file:files/texts/10x10.png", 250, 30, false, false);
        tenByTenResize = new Image("file:files/texts/10x10.png", 255, 33, false, false);
        fourteen = new Image("file:files/texts/14x14.png", 250, 30, false, false);
        fourteenResize = new Image("file:files/texts/14x14.png", 255, 33, false, false);
        eighteen = new Image("file:files/texts/18x18.png", 250, 30, false, false);
        eighteenResize = new Image("file:files/texts/18x18.png", 255, 33, false, false);

        Theme = new Image("file:files/texts/Level1.png", 250, 30, false, false);
        ThemeResize = new Image("file:files/texts/Level1.png", 255, 33, false, false);

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

        ImageView dimensionView = new ImageView(chooseDimension);
        dimensionView.setStyle("fx-background-color: transparent;");
        dimensionView.setTranslateX(350);
        dimensionView.setTranslateY(150);


        ImageView tenByTenView = new ImageView(tenByTen);
        tenByTenView.setStyle("fx-background-color: transparent;");
        tenByTenView.setTranslateX(400);
        tenByTenView.setTranslateY(200);
        tenByTenView.toFront();
        tenByTenView.setPickOnBounds(true);
        tenByTenView.setOnMouseEntered(e -> {
            tenByTenView.setImage(tenByTenResize);
            tenByTenView.setTranslateX(400);
            tenByTenView.setTranslateY(198);
        });
        tenByTenView.setOnMouseExited(e -> {
            tenByTenView.setImage(tenByTen);
            tenByTenView.setTranslateX(400);
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
        fourteenView.setTranslateX(400);
        fourteenView.setTranslateY(250);
        fourteenView.toFront();
        fourteenView.setPickOnBounds(true);
        fourteenView.setOnMouseEntered(e -> {
            fourteenView.setImage(fourteenResize);
            fourteenView.setTranslateX(400);
            fourteenView.setTranslateY(248);
        });
        fourteenView.setOnMouseExited(e -> {
            fourteenView.setImage(fourteen);
            fourteenView.setTranslateX(400);
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
        eighteenView.setTranslateX(400);
        eighteenView.setTranslateY(300);
        eighteenView.toFront();
        eighteenView.setPickOnBounds(true);
        eighteenView.setOnMouseEntered(e -> {
            eighteenView.setImage(eighteenResize);
            eighteenView.setTranslateX(400);
            eighteenView.setTranslateY(298);
        });
        eighteenView.setOnMouseExited(e -> {
            eighteenView.setImage(eighteen);
            eighteenView.setTranslateX(400);
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

        //CLOUD
        ImageView cloudView = new ImageView(cloud);
        cloudView.setStyle("fx-background-color: transparent;");
        cloudView.setTranslateX(100);
        cloudView.setTranslateY(200);
        cloudView.toFront();
        cloudView.setPickOnBounds(true);
        cloudView.setOnMouseEntered(e -> {
            cloudView.setImage(cloudResize);
            cloudView.setTranslateX(100);
            cloudView.setTranslateY(200);
        });
        cloudView.setOnMouseExited(e -> {
            cloudView.setImage(cloud);
            cloudView.setTranslateX(100);
            cloudView.setTranslateY(200);
        });
        cloudView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(28);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        //Dessert
        ImageView dessertView = new ImageView(desert);
        dessertView.setStyle("fx-background-color: transparent;");
        dessertView.setTranslateX(100);
        dessertView.setTranslateY(250);
        dessertView.toFront();
        dessertView.setPickOnBounds(true);
        dessertView.setOnMouseEntered(e -> {
            dessertView.setImage(desertResize);
            dessertView.setTranslateX(100);
            dessertView.setTranslateY(250);
        });
        dessertView.setOnMouseExited(e -> {
            dessertView.setImage(desert);
            dessertView.setTranslateX(100);
            dessertView.setTranslateY(250);
        });
        dessertView.setOnMouseClicked(e -> {
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
        });


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





        getChildren().addAll(dimensionView, tenByTenView, fourteenView, eighteenView, cloudView, dessertView, forestView, lavaView, spaceView, undergroundView,  returnView);
    }
}
