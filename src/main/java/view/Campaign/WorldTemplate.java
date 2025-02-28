package view.Campaign;


import control.GameController;
import control.EnemyController;
import javafx.animation.FadeTransition;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.util.Duration;
import view.AudioPlayer;
import view.Menu.RightPanel;
import view.WorldIntroAnimation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class WorldTemplate extends GridPane {
    //private MainProgram mainProgram;
    private GameController gameController;
    private int[][] levelArray;
    private Image wall;
    private Image path;
    private Image border;
    private Image goal;
    private Image diamond;
    private Image start;
    private Image heart;
    private Image breakableWall;
    private int squareSize;
    private int heartCrystals;
    private Image pickAxeImage;
    private int seconds;

    private RightPanel rightPanel;
    private AudioPlayer audioPlayer;

    public WorldTemplate(){

    }
    public WorldTemplate(int[][] levelArray, GameController gameController) throws FileNotFoundException {
        this.gameController = gameController;
        this.levelArray = levelArray;
        this.seconds = gameController.getSeconds();
        this.heartCrystals = gameController.getHeartCrystals();
        this.rightPanel = gameController.getRightPanel();
        this.audioPlayer = gameController.getAudioPlayer();
        this.setId("worldTemplate");
        rightPanel.changeHeartCounter(String.valueOf(heartCrystals));
        squareSize = 600/(levelArray.length+2);
        setBackground();
        setupImages(gameController.getWorld());
        setupBorders();
        setupLevel();
        rightPanel.setSTARTTIME(seconds);
        rightPanel.resetTimerLabel();
    }

    /**
     * Sätter bakgrunden i fönstret.
     */
    public void setBackground(){
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }

    /**
     * Skapar en ram runt spelplanen.
     */
    public void setupBorders() {
        for (int i = 0; i < levelArray.length + 1; i++) {
            add(getBorders(), i, 0);
        }
        for (int i = 0; i < levelArray.length + 1; i++) {
            add(getBorders(), 0, i);
        }
        for (int i = 0; i < levelArray.length + 2; i++) {
            add(getBorders(), i, levelArray.length + 1);
        }
        for (int i = 0; i < levelArray.length + 2; i++) {
            add(getBorders(), levelArray.length + 1, i);
        }
    }

    /**
     * Omvandlar värdena i arrayen av siffror till olika grafiska komponenter baserat på vilken siffra en position har.
     * Exempelvis så representerar 1:or väg, 0:or väggar, och 7:or hjärtan osv.
     */
    public void setupLevel() {
        for (int i = 0; i < levelArray.length; i++) {
            for (int j = 0; j < levelArray.length; j++) {

                if (levelArray[i][j] == 1) {
                    add(getPath(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 0){
                    add(getWall(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 2){
                    add(getStart(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 3){
                    add(getGoal(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 4){
                    add(getPath(),j + 1,i + 1);
                    add(addCollectible(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 5){
                    add(getPath(),j + 1,i + 1);
                    add(addPickAxe(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 6){
                    add(getBreakableWall(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 7){
                    add(getPath(),j + 1,i + 1);
                    add(addHeartCrystal(),j + 1,i + 1);
                }
            }
        }
    }

    public String getWorldName(int value){
        switch (value){
            case 1:
                return "forest";
            case 2:
                return  "underground";
            case 3:
                return "lava";
            case 4:
                return "cloud";
            case 5:
                return "desert";
            case 6:
                return "space";
            default:
                return "";
        }
    }

    /**
     * Instansierar de olika bilderna som används som grafik inuti spelet.
     * Baserad på value så sätts bilderna till en specifik folder per värld.
     * @param value Den aktuella världen.
     */
    public void setupImages(int value){
        String folder = getWorldName(value);
        path = new Image("file:files/" + folder + "/path.png", squareSize, squareSize, false, false);
        goal = new Image("file:files/" + folder + "/goal.png", squareSize, squareSize, false, false);
        diamond = new Image("file:files/" + folder + "/collectible.png", squareSize, squareSize, false, false);
        start = new Image("file:files/" + folder + "/start.png", squareSize, squareSize, false, false);
        pickAxeImage = new Image("file:files/items/pickaxe.png", squareSize, squareSize, false, false);
        heart = new Image("file:files/items/heart.png", squareSize, squareSize, false, false);
        if (value == 3) {
            breakableWall = new Image("file:files/cloud/breakablewall.png", squareSize, squareSize, false, false);
        }
        else {
            breakableWall = new Image("file:files/breakablewall.png", squareSize, squareSize, false, false);
        }
        if(value!=5){
            border = new Image("file:files/" + folder + "/border.png", squareSize, squareSize, false, false);
            wall = new Image("file:files/" + folder + "/wall.png", squareSize, squareSize, false, false);
        }
    }

    /**
     * En metod som skapar ett objekt av label som representerar en vägg.
     * @return Returnerar en label.
     */
    public Label getWall() {
        Label label = new Label();
        ImageView wallView = new ImageView(wall);
        wallView.setFitHeight(squareSize);
        wallView.setFitWidth(squareSize);
        label.setGraphic(wallView);
        label.setOnMouseEntered(e -> gameController.enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }

    /**
     * En metod som skapar ett objekt av label som representerar en väg.
     * @return Returnerar en label.
     */
    private Label getPath() {
        Label label = new Label();
        ImageView pathView = new ImageView(path);
        pathView.setFitHeight(squareSize);
        pathView.setFitWidth(squareSize);
        label.setGraphic(pathView);
        return label;
    }

    /**
     * En metod som skapar ett objekt av label som representerar en border.
     * @return Returnerar en label.
     */
    private Label getBorders() {
        Label label = new Label();
        ImageView borderView = new ImageView(border);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseEntered(e -> gameController.enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }

    /**
     * En metod som skapar ett objekt av label som representerar en förstörbar vägg.
     * @return Returnerar en label.
     */
    private Label getBreakableWall() {
        Label label = new Label();
        ImageView borderView = new ImageView(breakableWall);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseEntered(e -> gameController.enteredBreakableWall(e));
        return label;
    }

    /**
     * En metod som skapar ett objekt av label som representerar ett mål.
     * @return Returnerar en label.
     */
    private Label getGoal() {
        Label label = new Label();
        ImageView borderView = new ImageView(goal);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setId("goalLabel");
        label.setOnMouseEntered(e -> {
            try {
                gameController.enteredGoal();
            } catch ( IOException | ClassNotFoundException | InterruptedException fileNotFoundException  ) {
                fileNotFoundException.printStackTrace();
            }
        });
        return label;
    }

    /**
     * En metod som skapar ett objekt av label som representerar start.
     * @return Returnerar en label.
     */
    private Label getStart() {
        Label label = new Label();
        ImageView borderView = new ImageView(start);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setId("startLabel");
        label.setOnMouseClicked(e -> gameController.startLevel());
        return label;
    }

    /**
     * En metod som skapar ett objekt av label som representerar en collectible.
     * @return Returnerar en label.
     */
    public Label addCollectible() {
        Label collectible = new Label();
        ImageView borderView = new ImageView(diamond);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.7);
        borderView.setEffect(glow);
        collectible.setStyle("fx-background-color: transparent;");
        collectible.setGraphic(borderView);
        collectible.addEventHandler(MouseEvent.MOUSE_ENTERED, gameController.getMouseListener());
        gameController.getCollectibles().add(collectible);
        return collectible;
    }

    /**
     * En metod som skapar ett objekt av label som representerar ett spelarliv.
     * @return Returnerar en label.
     */
    public Label addHeartCrystal() {
        Label heartCrystal = new Label();
        ImageView borderView = new ImageView(heart);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.8);
        borderView.setEffect(glow);
        heartCrystal.setStyle("fx-background-color: transparent;");
        heartCrystal.setGraphic(borderView);
        heartCrystal.setOpacity(0.8);
        heartCrystal.setOnMouseEntered(e -> gameController.heartCrystalObtained(e));
        return heartCrystal;
    }

    /**
     * En metod som skapar ett objekt av label som representerar en yxa.
     * @return Returnerar en label.
     */
    public Label addPickAxe() {
        Label pickAxe = new Label();
        ImageView borderView = new ImageView(pickAxeImage);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.7);
        borderView.setEffect(glow);
        pickAxe.setStyle("fx-background-color: transparent;");
        pickAxe.setGraphic(borderView);
        pickAxe.addEventHandler(MouseEvent.MOUSE_ENTERED, gameController.getMouseListener());
        gameController.getPickaxes().add(pickAxe);
        return pickAxe;
    }


    /**
     * När muspekaren lämnar en label slutar den att highlightas.
     * @param e Används för att hitta rätt label.
     */
    public void exitedLabel(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(0.6);
        fade.setToValue(10);
        fade.play();
    }

    public void setNewWorldAnimation(BorderPane mainPaneCampaign, int world){
        WorldIntroAnimation introAnimation = new WorldIntroAnimation(String.valueOf(world));
        mainPaneCampaign.getChildren().add(introAnimation);
        introAnimation.setDisable(true);
        audioPlayer.playWorldIntroSound();
        audioPlayer.playLevelMusic(getWorldName(world));
        rightPanel.setTheTime(seconds);
        rightPanel.resetTimerLabel();
    }


    public int getSquareSize() {
        return squareSize;
    }

    public Image getImagePath(){
        return path;
    }

    public GameController getGameController() {
        return gameController;
    }
}
