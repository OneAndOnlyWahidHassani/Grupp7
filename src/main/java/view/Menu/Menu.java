package view.Menu;

import control.MainProgram;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import view.AudioPlayer;

import java.io.FileNotFoundException;

/**
 * @author Viktor Näslund
 */

public class Menu extends Pane {
    private MainProgram mainProgram;
    private Image campaign;
    private Image campaignResize;
    private Image randomize;
    private Image randomizeResize;
    private Image help;
    private Image helpResize;
    private Image select;
    private Image selectResize;

    private Image highscore;
    private Image highscoreResize;
    private Image mazegen;
    private AudioPlayer audioPlayer;
    private RightPanel panel;

    /**
     * Konstruktor som tar emot mainProgram, audioPlayer och panel
     * Kör sedan metoder för att länka Image-objekten med png-filer
     * @param mainProgram tas emot och sätts
     * @param audioPlayer tas emot och sätts
     * @param panel tas emot och sätts
     */
    public Menu(MainProgram mainProgram, AudioPlayer audioPlayer, RightPanel panel){
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        this.panel = panel;
        setBackground();
        setupImages();
        addButtons();
    }

    /**
     * Metod som länkar Image-objekten till png-filer
     */
    public void setupImages(){
        mazegen = new Image("file:files/texts/MazegenTitel.png", 800, 600, false,false);
        campaign = new Image("file:files/texts/Campaign.png", 250, 30, false, false);
        campaignResize = new Image("file:files/texts/Campaign.png", 255, 33, false, false);
        randomize = new Image("file:files/texts/Randomize.png", 250, 30, false, false);
        randomizeResize = new Image("file:files/texts/Randomize.png", 255, 33, false, false);
        help = new Image("file:files/texts/Help.png", 250, 30, false, false);
        helpResize = new Image("file:files/texts/Help.png", 255, 33, false, false);
        select = new Image("file:files/texts/Select.png", 250, 30, false, false);
        selectResize = new Image("file:files/texts/Select.png", 255, 33, false, false);
        highscore = new Image("file:files/texts/Highscore.png", 250, 30, false, false);
        highscoreResize = new Image("file:files/texts/Highscore.png", 255, 33, false, false);
    }

    /**
     * Metod som sätter bakgrundsbilden
     */
    public void setBackground(){
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }

    /**
     * Metod som lägger till klickbara ImageViews i scenen
     * Imageviews förstoras när man hovrar och byter scen när man klickar på dem
     */
    public void addButtons(){
        ImageView mazegenView = new ImageView(mazegen);
        mazegenView.setStyle("fx-background-color: transparent;");


        ImageView campaignView = new ImageView(campaign);
        campaignView.setStyle("fx-background-color: transparent;");
        campaignView.setTranslateX(275);
        campaignView.setTranslateY(200);
        campaignView.toFront();
        campaignView.setPickOnBounds(true);
        campaignView.setOnMouseEntered(e -> {
            campaignView.setImage(campaignResize);
            campaignView.setTranslateX(273);
            campaignView.setTranslateY(197);
        });
        campaignView.setOnMouseExited(e -> {
            campaignView.setImage(campaign);
            campaignView.setTranslateX(275);
            campaignView.setTranslateY(200);
        });
        campaignView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToCampaign();
                audioPlayer.playLevelMusic("forest");
                panel.setTheTime(25);
                panel.resetTimerLabel();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView selectView = new ImageView(select);
        selectView.setStyle("fx-background-color: transparent;");
        selectView.setTranslateX(275);
        selectView.setTranslateY(250);
        selectView.toFront();
        selectView.setPickOnBounds(true);
        selectView.setOnMouseEntered(e -> {
            selectView.setImage(selectResize);
            selectView.setTranslateX(273);
            selectView.setTranslateY(247);
        });
        selectView.setOnMouseExited(e -> {
            selectView.setImage(select);
            selectView.setTranslateX(275);
            selectView.setTranslateY(250);
        });
        selectView.setOnMouseClicked(e -> {
            mainProgram.selectWorldMap();
            audioPlayer.playButtonSound();
        });

        ImageView randomizeView = new ImageView(randomize);
        randomizeView.setStyle("fx-background-color: transparent;");
        randomizeView.setTranslateX(275);
        randomizeView.setTranslateY(300);
        randomizeView.toFront();
        randomizeView.setPickOnBounds(true);
        randomizeView.setOnMouseEntered(e -> {
            randomizeView.setImage(randomizeResize);
            randomizeView.setTranslateX(273);
            randomizeView.setTranslateY(297);
        });
        randomizeView.setOnMouseExited(e -> {
            randomizeView.setImage(randomize);
            randomizeView.setTranslateX(275);
            randomizeView.setTranslateY(300);
        });
        randomizeView.setOnMouseClicked(e -> {
            mainProgram.chooseDimension();
            audioPlayer.playButtonSound();
        });

        ImageView helpView = new ImageView(help);
        helpView.setStyle("fx-background-color: transparent;");
        helpView.setTranslateX(275);
        helpView.setTranslateY(350);
        helpView.toFront();
        helpView.setPickOnBounds(true);
        helpView.setOnMouseEntered(e -> {
            helpView.setImage(helpResize);
            helpView.setTranslateX(273);
            helpView.setTranslateY(347);
        });
        helpView.setOnMouseExited(e -> {
            helpView.setImage(help);
            helpView.setTranslateX(275);
            helpView.setTranslateY(350);
        });
        helpView.setOnMouseClicked(e -> {
            mainProgram.changeToHelp();
            audioPlayer.playButtonSound();
        });


        ImageView highscoreView = new ImageView(highscore);
        highscoreView.setStyle("fx-background-color: transparent;");
        highscoreView.setTranslateX(275);
        highscoreView.setTranslateY(400);
        highscoreView.toFront();
        highscoreView.setPickOnBounds(true);
        highscoreView.setOnMouseEntered(e -> {
            highscoreView.setImage(highscoreResize);
            highscoreView.setTranslateX(273);
            highscoreView.setTranslateY(397);
        });
        highscoreView.setOnMouseExited(e -> {
            highscoreView.setImage(highscore);
            highscoreView.setTranslateX(275);
            highscoreView.setTranslateY(400);
        });
        highscoreView.setOnMouseClicked(e -> {
            mainProgram.showHighscore();
            audioPlayer.playButtonSound();
        });

        this.getChildren().addAll(campaignView,randomizeView,selectView, helpView,highscoreView, mazegenView);
    }

}
