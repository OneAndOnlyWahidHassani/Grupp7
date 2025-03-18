package view.Menu;


import LevelEditor.controller.MainLE;
import LevelEditor.view.MapTemplateLE;
import control.MainProgram;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.TimeThread;
import model.TotalTime;
import view.AudioPlayer;
import view.Randomize.MapTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

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
    private int currentXPositionWall = 100;

    // ====== Collectible variables ======
    private Image collectibleImage;
    private ImageView collectibleView;
    private Label collectibleLabel;
    private int currentXPosition = 100;

    // ====== Start variables ======
    private Image startImage;
    private ImageView startView;
    private Label startLabel;

    // ====== Goal variables ======
    private Image goalImage;
    private ImageView goalView;
    private Label goalLabel;

    private Image itemsImage;
    private ImageView itemsView;
    private Label itemsLabel;

    private int themeInt;
    private AudioPlayer audioPlayer;
    private TimeThread time;
    private TotalTime totTime;
    private Set<String> createdCollectibles = new HashSet<>();

    //rightPanel for leveleditor
    private MainLE mainLE;
    private MapTemplateLE mapTemplateLE;
    private boolean userInitiatedDrag = false;



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
        musicLabel.setId("musicLabel");

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
    public RightPanel(MainProgram mainProgram, AudioPlayer audioPlayer, int themeInt, MainLE mainLE, MapTemplateLE mapTemplateLE) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        this.themeInt = themeInt;
        this.mainLE = mainLE;
        this.mapTemplateLE = mapTemplateLE;
        Font customFont = loadFont();


        soundOn = true;
        musicOn = true;

        imageMenu = new Image("file:files/texts/Menu.png", 110, 30, false, false);
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
        soundLabel.setLayoutX(460);
        soundLabel.setLayoutY(690);
        soundLabel.setGraphic(soundView);
        soundLabel.setId("soundLabel");

        musicImage = new Image("file:files/soundbuttons/musicon.png", 30,30,false,false);
        musicView = new ImageView(musicImage);
        musicLabel = new Label();
        musicLabel.setLayoutX(490);
        musicLabel.setLayoutY(690);
        musicLabel.setGraphic(musicView);
        musicLabel.setId("musicLabel");


        menuView.setLayoutX(400);


        soundLabel.setOnMouseClicked(e -> soundLabelClicked());
        musicLabel.setOnMouseClicked(e -> musicLabelClicked());

        //save level button
        ImageView saveLevelView = new ImageView(new Image("file:files/texts/pngSaveImage.png", 30, 30, false, false));
        Button saveLevel = new Button();
        saveLevel.setGraphic(saveLevelView);
        saveLevel.setTranslateX(60);
        saveLevel.setTranslateY(650);
        saveLevel.setId("saveLevelButton");
        saveLevel.setStyle("-fx-border-color: transparent; -fx-border-width: 2px;");
        saveLevel.setOnMouseEntered(e -> saveLevel.setStyle("-fx-border-color: red; -fx-border-width: 2px;"));
        saveLevel.setOnMouseExited(e -> saveLevel.setStyle("-fx-border-color: transparent; -fx-border-width: 2px;"));
        saveLevel.setOnAction(e -> {
            mainLE.saveLevel(mainLE.getCurrentLevelName(), mainLE.getCurrentTheme(), mainLE.getDimension(), mainLE.getMazeGenerator().getRawMazeArray());
            audioPlayer.playButtonSound();
        });

        Tooltip saveTooltip = new Tooltip("Save Level?");
        saveTooltip.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 14px;");
        saveLevel.setTooltip(saveTooltip);

        //Test level button
        Button testLevel = new Button("Test Level");
        testLevel.setFont(customFont);
        testLevel.setTranslateX(150);
        testLevel.setTranslateY(652.5);
        testLevel.setId("testLevelButton");
        testLevel.setOnMouseEntered(e -> testLevel.setTextFill(Color.RED));
        testLevel.setOnMouseExited(e -> testLevel.setTextFill(Color.BLACK));
        testLevel.setOnAction(e -> {
            mainLE.saveLevel(mainLE.getCurrentLevelName(), mainLE.getCurrentTheme(), mainLE.getDimension(), mainLE.getMazeGenerator().getRawMazeArray());
            mainLE.testLevel(mainLE.getCurrentLevelName(), mainLE.getCurrentTheme(), mainLE.getDimension(), mainLE.getMazeGenerator().getRawMazeArray());
            audioPlayer.playButtonSound();
        });

        getChildren().addAll(menuView, soundLabel, musicLabel, saveLevel, testLevel);
        menuView.setOnMouseClicked(e -> MainMenuClicked(e));



        setupObjectButtons(getThemeString(themeInt));



    }

    private Font loadFont() {
        try {
            return Font.loadFont(new FileInputStream("files/fonts/PressStart2P.ttf"), 20);
        } catch (FileNotFoundException e) {
            return Font.font("Verdana", 20);
        }
    }
    public String getThemeString(int themeInt) {
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

    public void setupObjectButtons(String theme) {
        int xStart = 50;
        int yStart = 60;
        int buttonWidth = 50;
        int buttonHeight = 50;
        int rowHeight = 80;
        int columnSpacing = 75;

        int x = xStart;
        int y = yStart;

        createPathButton(theme, x, y, buttonWidth, buttonHeight);
        x += columnSpacing;

        createWallButton(theme, x, y, buttonWidth, buttonHeight);
        x += columnSpacing;

        createBorderButton(theme, x, y, buttonWidth, buttonHeight);
        x += columnSpacing;

        createStartButton(theme, x, y, buttonWidth, buttonHeight);
        x += columnSpacing;

        createGoalButton(theme, x, y, buttonWidth, buttonHeight);

        createBreakableWallButton(theme, x, y, buttonWidth, buttonHeight);
        y += rowHeight;
        x -= columnSpacing;
        x -= columnSpacing;
        x -= columnSpacing;

        createItemsButton(x, y, buttonWidth, buttonHeight);
        x -= columnSpacing;

        createCollectibleButton(theme, x, y, buttonWidth, buttonHeight);

    }

    public void createPathButton(String theme, int x, int y, int v, int h) {
        pathImage = new Image("file:files/" + theme + "/path.png", v, h, false, false);
        pathView = new ImageView(pathImage);
        pathLabel = new Label();
        pathLabel.setGraphic(pathView);
        pathLabel.setLayoutX(x);
        pathLabel.setLayoutY(y);
        pathLabel.setOnMouseEntered(e -> {
            pathLabel.setTooltip(new Tooltip("Path"));
        });
        makeDraggable(pathLabel, pathImage, 1, theme);
        getChildren().add(pathLabel);
    }

    public void createWallButton(String theme, int x, int y, int v, int h) {
        wallImage = new Image("file:files/" + theme + "/wall.png", v, h, false, false);
        wallView = new ImageView(wallImage);
        wallLabel = new Label();
        wallLabel.setGraphic(wallView);
        wallLabel.setLayoutX(x);
        wallLabel.setLayoutY(y);
        wallLabel.setOnMouseEntered(e -> {
            wallLabel.setTooltip(new Tooltip("Wall"));
        });
        makeDraggable(wallLabel, wallImage, 0, theme);
        getChildren().add(wallLabel);
    }

    public void createBreakableWallButton(String theme, int x, int y, int v, int h) {
        wallImage = new Image("file:files/" + theme + "/breakableWall.png", v, h, false, false);

        wallView = new ImageView(wallImage);
        wallLabel = new Label();
        wallLabel.setGraphic(wallView);
        wallLabel.setOnMouseEntered(e -> {
            wallLabel.setTooltip(new Tooltip("Breakable Wall"));
        });

        makeDraggable(wallLabel, wallImage, 7, theme);

        wallLabel.setLayoutX(currentXPositionWall - 160);
        wallLabel.setLayoutY(y);
        currentXPositionWall += 37;

        getChildren().add(wallLabel);
    }

    public void createBorderButton(String theme, int x, int y, int v, int h) {
        wallImage = new Image("file:files/" + theme + "/border.png", v, h, false, false);
        wallView = new ImageView(wallImage);
        wallLabel = new Label();
        wallLabel.setGraphic(wallView);
        wallLabel.setLayoutX(x);
        wallLabel.setLayoutY(y);
        wallLabel.setOnMouseEntered(e -> {
            wallLabel.setTooltip(new Tooltip("Border"));
        });
        makeDraggable(wallLabel, wallImage, 8, theme);
        getChildren().add(wallLabel);
    }

    public void createCollectibleButton(String theme, int x, int y, int v, int h) {
        String collectibleType = getCollectibleType(theme);

        if (!createdCollectibles.contains(collectibleType)) {
            collectibleImage = new Image("file:files/" + theme + "/collectible.png", v, h, false, false);

            collectibleView = new ImageView(collectibleImage);
            collectibleLabel = new Label();
            collectibleLabel.setGraphic(collectibleView);
            collectibleLabel.setOnMouseEntered(e -> {
                collectibleLabel.setTooltip(new Tooltip("Collectible"));
            });

            makeDraggable(collectibleLabel, collectibleImage, 4, theme);

            collectibleLabel.setLayoutX(currentXPosition - 50);
            collectibleLabel.setLayoutY(y);
            currentXPosition += 90;

            getChildren().add(collectibleLabel);
            createdCollectibles.add(collectibleType);
        }
    }

    private String getCollectibleType(String theme) {
        switch (theme) {
            case "desert":
            case "forest":
            case "lava":
            case "underground":
                return "commonColor";
            default:
                return theme;
        }
    }

    public void createStartButton(String theme, int x, int y, int v, int h) {
        startImage = new Image("file:files/" + theme + "/start.png", v, h, false, false);
        startView = new ImageView(startImage);
        startLabel = new Label();
        startLabel.setGraphic(startView);
        startLabel.setLayoutX(x);
        startLabel.setLayoutY(y);
        startLabel.setOnMouseEntered(e -> {
            startLabel.setTooltip(new Tooltip("Start"));
        });
        makeDraggable(startLabel, startImage, 2, theme);
        getChildren().add(startLabel);
    }

    public void createGoalButton(String theme, int x, int y, int v, int h) {
        goalImage = new Image("file:files/" + theme + "/goal.png", v, h, false, false);
        goalView = new ImageView(goalImage);
        goalLabel = new Label();
        goalLabel.setGraphic(goalView);
        goalLabel.setLayoutX(x);
        goalLabel.setLayoutY(y);
        goalLabel.setOnMouseEntered(e -> {
            goalLabel.setTooltip(new Tooltip("Goal"));
        });
        makeDraggable(goalLabel, goalImage, 3, theme);
        getChildren().add(goalLabel);
    }

    private boolean heartCreated = false;
    private boolean pickaxeCreated = false;

    public void createItemsButton(int x, int y, int v, int h) {
        if (!heartCreated) {
            Image heartImage = new Image("file:files/items/heart.png", v, h, false, false);
            ImageView heartView = new ImageView(heartImage);
            Label heartLabel = new Label();
            heartLabel.setGraphic(heartView);
            heartLabel.setLayoutX(x);
            heartLabel.setLayoutY(y);
            heartLabel.setOnMouseEntered(e -> {
                heartLabel.setTooltip(new Tooltip("Heart"));
            });
            makeDraggable(heartLabel, heartImage, 5, getThemeString(themeInt));
            getChildren().add(heartLabel);

            heartCreated = true;
        }

        if (!pickaxeCreated) {
            Image pickaxeImage = new Image("file:files/items/pickaxe.png", v, h, false, false);
            ImageView pickaxeView = new ImageView(pickaxeImage);
            Label pickaxeLabel = new Label();
            pickaxeLabel.setGraphic(pickaxeView);
            pickaxeLabel.setLayoutX(x + 90);
            pickaxeLabel.setLayoutY(y);
            pickaxeLabel.setOnMouseEntered(e -> {
                pickaxeLabel.setTooltip(new Tooltip("Pickaxe"));
            });
            makeDraggable(pickaxeLabel, pickaxeImage, 6, getThemeString(themeInt));
            getChildren().add(pickaxeLabel);

            pickaxeCreated = true;
        }
    }

    private void removePreviousStartOrGoal(int type) {
        int[][] maze = mainLE.getMazeGenerator().getRawMazeArray();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (type == 2 && maze[i][j] == 2) {
                    maze[i][j] = 1;


                } else if (type == 3 && maze[i][j] == 3) {
                    maze[i][j] = 1;
                }
            }
        }
        mainLE.getMazeGenerator().setRawMazeArray(maze);
    }




    //för att kunna dra objekten
    public void makeDraggable(Label label, Image image, int type, String theme) {
        label.setOnDragDetected(event -> {
            userInitiatedDrag = true;
            Dragboard db = label.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(image);
            content.putString(type + "," + theme);
            db.setContent(content);
            if (type == 2 || type == 3 && userInitiatedDrag) {
                removePreviousStartOrGoal(type);
                mapTemplateLE.updateMapTemplate(mainLE.getMazeGenerator().getRawMazeArray());
            }
            event.consume();
        });
        label.setOnDragDone(event -> {
            userInitiatedDrag = false;
            mainLE.saveLevel(mainLE.getCurrentLevelName(), mainLE.getCurrentTheme(), mainLE.getDimension(), mainLE.getMazeGenerator().getRawMazeArray());
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
        musicLabel.setId("musicLabel");
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
