package view.Menu;


import control.MainProgram;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.TimeThread;
import model.TotalTime;
import view.AudioPlayer;

import java.io.FileNotFoundException;

/**
 * @author Filip Örnling
 * @edit Viktor Näslund, Sebastian Helin
 */

public class RightPanel extends Pane {

    private MainProgram mainProgram;
    private String gameMode;
    private int seconds;

    private Image imageMenu;
    private ImageView menuView;

    private Image levelNumber;
    private ImageView currentLevelView;
    private Label levelLabel;
    private Thread timer;

    private Image heart;
    private ImageView currentHeartView;
    private Label heartLabel;

    private Image pickaxe;
    private ImageView pickaxeView;
    private Label pickaxeLabel;

    private Image soundImage;
    private ImageView soundView;
    private Label soundLabel;
    private boolean soundOn;

    private Image musicImage;
    private ImageView musicView;
    private Label musicLabel;
    private boolean musicOn;
    private boolean timerIsStartedOnce = false;

    private Image emptySprite;
    private ImageView emptyView;

    private static Integer STARTTIME = 15;
    private Timeline timeline = new Timeline();
    private Label timerLabel = new Label();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    private IntegerProperty stackedSeconds = new SimpleIntegerProperty();
    private Font font = Font.loadFont("file:files/fonts/PressStart2P.ttf", 35);

    private Image pathImage;
    private ImageView pathView;
    private Label pathLabel;

    // ====== Wall variables ======
    private Image wallImage;
    private ImageView wallView;
    private Label wallLabel;

    // ====== Collectible variables ======
    private Image collectibleImage;
    private ImageView collectibleView;
    private Label collectibleLabel;

    // ====== Start variables ======
    private Image startImage;
    private ImageView startView;
    private Label startLabel;

    // ====== Goal variables ======
    private Image goalImage;
    private ImageView goalView;
    private Label goalLabel;

    private int themeInt;
    private AudioPlayer audioPlayer;
    private TimeThread time;
    private TotalTime totTime;


    /**
     * Instansierar objekten och lägger till bilder och labels på scenen
     * @param mainProgram Huvudprogrammet
     * @param gameMode Kollar ifall användaren valt randomized eller kampanjläge
     * @param audioPlayer Klass som spelar upp ljud & musik
     * @param time En tråd som räknar hur många sekunder spelaren har på sig
     * @throws FileNotFoundException
     */
    public RightPanel(MainProgram mainProgram, String gameMode, AudioPlayer audioPlayer, TimeThread time) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.gameMode = gameMode;
        this.audioPlayer = audioPlayer;
        this.time = time;
        this.setId("campaignScene");

        soundOn = true;
        musicOn = true;

        imageMenu = new Image("file:files/texts/Menu.png", 90, 30, false, false);
        menuView = new ImageView(imageMenu);

        emptySprite = new Image("file:files/emptySprite.png", 30, 30, false, false);
        emptyView = new ImageView(emptySprite);

        pickaxe = new Image("file:files/items/pickaxe.png", 30, 30, false, false);
        pickaxeView = new ImageView(pickaxe);
        pickaxeLabel = new Label();
        pickaxeLabel.setGraphic(emptyView);

        levelNumber = new Image("file:files/levelcounter/"+ gameMode +".png", 90, 30, false, false);
        currentLevelView = new ImageView(levelNumber);
        levelLabel = new Label();
        levelLabel.setGraphic(currentLevelView);

        soundImage = new Image("file:files/soundbuttons/soundon.png", 30,30,false,false);
        soundView = new ImageView(soundImage);
        soundLabel = new Label();
        soundLabel.setGraphic(soundView);

        musicImage = new Image("file:files/soundbuttons/musicon.png", 30,30,false,false);
        musicView = new ImageView(musicImage);
        musicLabel = new Label();
        musicLabel.setGraphic(musicView);

        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(font);

        // Add everything to the Pane (absolute positioning)
        getChildren().addAll(menuView, levelLabel, pickaxeLabel,
                soundLabel, musicLabel);

        // Position them with setLayoutX/Y:
        menuView.setLayoutX(100);
        menuView.setLayoutY(5);

        // Example layout positions (adjust to taste)
        levelLabel.setLayoutX(100);
        levelLabel.setLayoutY(60);

        pickaxeLabel.setLayoutX(100);
        pickaxeLabel.setLayoutY(80);

        soundLabel.setLayoutX(140);
        soundLabel.setLayoutY(570);

        musicLabel.setLayoutX(170);
        musicLabel.setLayoutY(570);

        timerLabel.setLayoutX(120);
        timerLabel.setLayoutY(250);
        getChildren().add(timerLabel);


        //Hearts only in Campaign
        if(gameMode!="Random"){
            heart = new Image("file:files/hearts/3heart.png", 90, 30, false, false);
            currentHeartView = new ImageView(heart);
            heartLabel = new Label();
            heartLabel.setGraphic(currentHeartView);
            heartLabel.setId("heartLabel");
            getChildren().add(heartLabel);
            heartLabel.setLayoutX(100);
            heartLabel.setLayoutY(140);
        }



        soundLabel.setOnMouseClicked(e -> soundLabelClicked());
        musicLabel.setOnMouseClicked(e -> musicLabelClicked());



        menuView.setOnMouseClicked(e -> MainMenuClicked(e));

        totTime = new TotalTime(false);
    }

    //rightPanel for leveleditor
    public RightPanel(MainProgram mainProgram, AudioPlayer audioPlayer, int themeInt) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        this.themeInt = themeInt;


        soundOn = true;
        musicOn = true;

        imageMenu = new Image("file:files/texts/Menu.png", 90, 30, false, false);
        menuView = new ImageView(imageMenu);

        emptySprite = new Image("file:files/emptySprite.png", 30, 30, false, false);
        emptyView = new ImageView(emptySprite);


        levelNumber = new Image("file:files/levelcounter/"+ gameMode +".png", 90, 30, false, false);
        currentLevelView = new ImageView(levelNumber);
        levelLabel = new Label();
        levelLabel.setGraphic(currentLevelView);

        soundImage = new Image("file:files/soundbuttons/soundon.png", 30,30,false,false);
        soundView = new ImageView(soundImage);
        soundLabel = new Label();
        soundLabel.setLayoutX(340);
        soundLabel.setLayoutY(660);
        soundLabel.setGraphic(soundView);
        soundLabel.setId("soundLabel");

        musicImage = new Image("file:files/soundbuttons/musicon.png", 30,30,false,false);
        musicView = new ImageView(musicImage);
        musicLabel = new Label();
        musicLabel.setLayoutX(370);
        musicLabel.setLayoutY(660);
        musicLabel.setGraphic(musicView);
        musicLabel.setId("musicLabel");


        menuView.setLayoutX(310);


        soundLabel.setOnMouseClicked(e -> soundLabelClicked());
        musicLabel.setOnMouseClicked(e -> musicLabelClicked());

        getChildren().addAll(menuView, soundLabel, musicLabel);


        menuView.setOnMouseClicked(e -> MainMenuClicked(e));

        setupObjectButtons();


    }
    public String getThemeString() {
        switch (themeInt) {
            case 0:
                return "forest";
            case 1:
                return "lava";
            case 2:
                return "underground";
            case 3:
                return "cloud";
            case 4:
                return "desert";
            case 5:
                return "space";
            default:
                return "forest";
        }
    }

    public void setupObjectButtons() {
        String theme = getThemeString();
        int x = 80;
        int v = 45;
        createPathButton(theme, x, v);
        x += 50;
        createWallButton(theme, x, v);
        x += 50;
        createStartButton(theme, x, v);
        x += 50;
        createGoalButton(theme, x, v);
        x += 50;
        createCollectibleButton(theme, x, v);

    }
    public void createPathButton(String theme, int x, int v) {
        pathImage = new Image ("file:files/" + theme + "/path.png", v, v, false, false);
        pathView = new ImageView(pathImage);
        pathLabel = new Label();
        pathLabel.setGraphic(pathView);
        pathLabel.setLayoutX(x);
        pathLabel.setLayoutY(150);
        pathLabel.setOnMouseEntered(e -> {
            pathLabel.setTooltip(new Tooltip("Path"));
        });
        makeDraggable(pathLabel, pathImage, 1);

        getChildren().add(pathLabel);
    }

    /**
     * Creates the wall button.
     */
    public void createWallButton(String theme, int x, int v) {
        wallImage = new Image("file:files/" + theme + "/wall.png", v, v, false, false);
        wallView = new ImageView(wallImage);
        wallLabel = new Label();
        wallLabel.setGraphic(wallView);
        wallLabel.setLayoutX(x);   // Slightly to the right of path
        wallLabel.setLayoutY(150);
        wallLabel.setOnMouseEntered(e -> {
            wallLabel.setTooltip(new Tooltip("Wall"));
        });
        makeDraggable(wallLabel, wallImage, 0);
        getChildren().add(wallLabel);
    }

    /**
     * Creates the collectible button.
     */
    public void createCollectibleButton(String theme, int x, int v) {
        collectibleImage = new Image("file:files/" + theme + "/collectible.png", v, v, false, false);
        collectibleView = new ImageView(collectibleImage);
        collectibleLabel = new Label();
        collectibleLabel.setGraphic(collectibleView);
        collectibleLabel.setLayoutX(x);  // Next to wall
        collectibleLabel.setLayoutY(150);
        collectibleLabel.setOnMouseEntered(e -> {
            collectibleLabel.setTooltip(new Tooltip("Collectible"));
        });
            makeDraggable(collectibleLabel, collectibleImage, 4);
        getChildren().add(collectibleLabel);
    }

    /**
     * Creates the start button.
     */
    public void createStartButton(String theme, int x, int v) {
        startImage = new Image("file:files/" + theme + "/start.png", v, v, false, false);
        startView = new ImageView(startImage);
        startLabel = new Label();
        startLabel.setGraphic(startView);
        startLabel.setLayoutX(x);  // Next to collectible
        startLabel.setLayoutY(150);
        startLabel.setOnMouseEntered(e -> {
            startLabel.setTooltip(new Tooltip("Start"));
        });
        makeDraggable(startLabel, startImage, 2);
        getChildren().add(startLabel);
    }

    /**
     * Creates the goal button.
     */
    public void createGoalButton(String theme, int x, int v) {
        goalImage = new Image("file:files/" + theme + "/goal.png", v, v, false, false);
        goalView = new ImageView(goalImage);
        goalLabel = new Label();
        goalLabel.setGraphic(goalView);
        goalLabel.setLayoutX(x);  // Next to start
        goalLabel.setLayoutY(150);
        goalLabel.setOnMouseEntered(e -> {
            goalLabel.setTooltip(new Tooltip("Goal"));
        });
        makeDraggable(goalLabel, goalImage, 3);
        getChildren().add(goalLabel);
    }

    //för att kunna dra objekten
    public void makeDraggable (Label label, Image image, int type) {

        // 1) OnDragDetected: initiate the drag
        label.setOnDragDetected(event -> {
            Dragboard db = label.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            ClipboardContent content = new ClipboardContent();
            // Put the image on the dragboard
            content.putImage(image);
            content.putString(String.valueOf(type));
            db.setContent(content);

            // Optionally: do some visual feedback or change cursor
            event.consume();
        });
    }
    /**
     * Slår på/av spelljud
     */
    public void soundLabelClicked(){
        if(soundOn){
            soundImage = new Image("file:files/soundbuttons/soundoff.png", 30,30,false,false);
            audioPlayer.muteSound(true);
            soundOn = false;
        } else{
            soundImage = new Image("file:files/soundbuttons/soundon.png", 30,30,false,false);
            audioPlayer.muteSound(false);
            soundOn = true;
        }
        soundView.setImage(soundImage);
        soundLabel.setGraphic(soundView);
    }
    /**
     * Slår på/av musik
     */
    public void musicLabelClicked(){
        if(musicOn){
            musicImage = new Image("file:files/soundbuttons/musicoff.png",30,30,false,false);
            audioPlayer.muteMusic(true);
            musicOn = false;
        } else{
            musicImage = new Image("file:files/soundbuttons/musicon.png",30,30,false,false);
            audioPlayer.muteMusic(false);
            musicOn = true;
        }
        musicView.setImage(musicImage);
        musicLabel.setGraphic(musicView);
    }
    /**
     * Sätter en ny bild beroende på om man plockar upp/tappar ett liv
     * @param number
     */
    public void changeHeartCounter(String number){
        heart = new Image("file:files/hearts/" + number + "heart.png", 90, 30, false, false);
        currentHeartView.setImage(heart);
        heartLabel.setGraphic(currentHeartView);
    }
    /**
     * Sätter bilden på yxan i en label
     */
    public void addPickaxe(){
        pickaxeLabel.setGraphic(pickaxeView);
    }
    /**
     * Tar bort yxan när man plcokat upp den
     */
    public void removePickaxe(){
        pickaxeLabel.setGraphic(emptyView);
    }
    /**
     * Byter bild beroende vilken nivå man befinner sig på
     * @param number
     */
    public void changeLevelCounter(String number){
        levelNumber = new Image("file:files/levelcounter/" + number + ".png", 90, 30, false, false);
        currentLevelView.setImage(levelNumber);
        levelLabel.setGraphic(currentLevelView);
        levelLabel.setId("levelLabel");
    }
    /**
     * Byter scen till menyn
     * @param e
     */
    private void MainMenuClicked(MouseEvent e){
        mainProgram.changeToMenu();
        audioPlayer.playButtonSound();
        audioPlayer.stopMusic();
    }
    /**
     * Startar den visuella klockan i GUIt
     */
    public void runClock() {
        timeSeconds.set(STARTTIME);

        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
    }
    /**
     * Pausar klockan vid avancemang till ny nivå
     */
    public void pauseClock(){
        seconds = timeSeconds.getValue().intValue();
        timeline.stop();

        timeSeconds.set(seconds);
        timeline = null;
    }
    /**
     * Sätter tiden som visuella klockan ska visa
     * @param timesec
     */
    public void setTheTime(int timesec){
        timeSeconds.set(timesec);
    }
    /**
     * Kör igång klockan när spelaren trycker på startknappen
     */
    public void resumeClock(){
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();

    }
    /**
     * En setter som finns i varje maptemplate för att bestämma antal sekunder spelaren
     * har på sig
     * @param STARTTIME
     */
    public void setSTARTTIME(Integer STARTTIME) {
        RightPanel.STARTTIME = STARTTIME;
    }
    /**
     * Startar en tråd som räknar den totala tiden
     */
    public void startTotalTimer(){
        if (!timerIsStartedOnce)
        totTime.start();
    }
    /**
     * Startar en task för Game Over
     */
    public void startTask(){
        timer = null;

        timer = new Thread(startNewTask());
        timer.start();
    }
    /**
     * Tasks run-metod som sätter den totala tiden det tog att spela
     * Pausar musik & visar Game Over texten
     */
    public void gameIsOver(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mainProgram.getCampaignController().gameOver();
                audioPlayer.playGameOverSound();
                audioPlayer.stopMusic();
                totTime.setGameOver(true);
                removePickaxe();
            }
        });
    }
    /**
     * Skapar en task vid gameOver
     * @return
     */
    public Task startNewTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                gameIsOver();
                return null;
            }
        };
        return task;
    }
    /**
     * Setter för Tråden att veta när det blir GameOver
     * @param b
     */
    public void setGameOver(boolean b) {
        totTime.setGameOver(b);
    }
    /**
     * Setter för att kontrollera om tiden har startat
     * @param timerIsStartedOnce
     */
    public void setTimerIsStarted(boolean timerIsStartedOnce) {
        this.timerIsStartedOnce = timerIsStartedOnce;
    }
    /**
     * Om spelaren har 5 sekunder kvar kallas denna metod från tråden
     */
    public void fiveSecLeft() {
        audioPlayer.playTimeLeftSound();
        timerLabel.setStyle("-fx-text-fill: red;");
    }
    /**
     * Nollställer textens färg till vit och stoppar klockljudet
     */
    public void resetTimerLabel(){
        timerLabel.setStyle("-fx-text-fill: white;");
        audioPlayer.stopClockSound();
    }
}