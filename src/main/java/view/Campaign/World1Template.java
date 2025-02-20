package view.Campaign;


import control.MainProgram;
import javafx.animation.FadeTransition;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.TimeThread;
import model.TotalTime;
import view.AudioPlayer;
import view.Menu.RightPanel;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author André Eklund
 * @edit Filip Örnling, Sebastian Helin, Viktor Näslund
 */

public class World1Template extends GridPane {



    private MainProgram mainProgram;
    private int[][] level;
    private ArrayList<Label> collectibles = new ArrayList<>();
    private ArrayList<Label> pickaxes = new ArrayList<>();
    private MouseListener mouseListener = new MouseListener();
    private Image wall;
    private Image path;
    private Image border;
    private Image goal;
    private Image diamond;
    private Image start;
    private Image heart;
    private Image breakableWall;
    private boolean startButtonPressed;
    private boolean allCollectiblesObtained;
    private boolean wallDestroyed;
    private int collectiblesObtained = 0;
    private int squareSize;
    private int currentLevel;
    private int heartCrystals;
    private Image pickAxeImage;
    private boolean pickaxeObtained;
    private boolean gameStarted;
    private boolean startNotClickedOnce = true;
    private boolean totalTimeStarted = false;
    private int world;
    private int seconds = 25;

    private RightPanel rightPanel;
    private AudioPlayer audioPlayer;
    private TimeThread time;
    private TotalTime totTime;

    /**
     * Instansierar objekten.
     * @param level Den array som sedan omvandlas till en nivå inuti spelet.
     * @param currentLevel Den aktuella nivån
     * @param heartCrystals Spelarens liv.
     * @param mainProgram Huvudprogrammet.
     * @param rightPanel Panelen som visar information så som liv, tid, nivå osv.
     * @param world Används för att sätta rätt grafik på världen.
     * @param audioPlayer Används för att spela upp ljud inne i spelet.
     * @param seconds Tidsbegränsningen för varje bana.
     * @throws FileNotFoundException
     */

    //Konstruktorn ska kunna ta emot int-arrayer och representera dem i GUIt
    public World1Template(int[][] level, int currentLevel, int heartCrystals, MainProgram mainProgram, RightPanel rightPanel, int world, AudioPlayer audioPlayer, int seconds) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.currentLevel = currentLevel;
        this.level = level;
        this.heartCrystals = heartCrystals;
        this.seconds = seconds;

        rightPanel.changeHeartCounter(String.valueOf(heartCrystals));
        this.rightPanel = rightPanel;
        this.audioPlayer = audioPlayer;
        this.world = world;
        squareSize = 600/(level.length+2);
        setBackground();
        setupImages(world);
        setupBorders();
        setupLevel();
        rightPanel.setSTARTTIME(seconds);
        rightPanel.resetTimerLabel();


        totTime = new TotalTime(false);
        time = null;

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
        for (int i = 0; i < level.length + 1; i++) {
            add(getBorders(), i, 0);
        }
        for (int i = 0; i < level.length + 1; i++) {
            add(getBorders(), 0, i);
        }
        for (int i = 0; i < level.length + 2; i++) {
            add(getBorders(), i, level.length + 1);
        }
        for (int i = 0; i < level.length + 2; i++) {
            add(getBorders(), level.length + 1, i);
        }
    }

    /**
     * Omvandlar värdena i arrayen av siffror till olika grafiska komponenter baserat på vilken siffra en position har.
     * Exempelvis så representerar 1:or väg, 0:or väggar, och 7:or hjärtan osv.
     */
    public void setupLevel() {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level.length; j++) {

                if (level[i][j] == 1) {
                    add(getPath(),j + 1,i + 1);
                }
                else if (level[i][j] == 0){
                    add(getWall(),j + 1,i + 1);
                }
                else if (level[i][j] == 2){
                    add(getStart(),j + 1,i + 1);
                }
                else if (level[i][j] == 3){
                    add(getGoal(),j + 1,i + 1);
                }
                else if (level[i][j] == 4){
                    add(getPath(),j + 1,i + 1);
                    add(addCollectible(),j + 1,i + 1);
                }
                else if (level[i][j] == 5){
                    add(getPath(),j + 1,i + 1);
                    add(addPickAxe(),j + 1,i + 1);
                }
                else if (level[i][j] == 6){
                    add(getBreakableWall(),j + 1,i + 1);
                }
                else if (level[i][j] == 7){
                    add(getPath(),j + 1,i + 1);
                    add(addHeartCrystal(),j + 1,i + 1);
                }
            }
        }
    }

    /**
     * Instansierar de olika bilderna som används som grafik inuti spelet.
     * Baserad på value så sätts bilderna till en specifik folder per värld.
     * @param value Den aktuella världen.
     */
    public void setupImages(int value){

        String folder = "";

        if (value == 0) {
            folder = "forest";
        }
        else if (value == 1) {
            folder = "underground";
        }
        else if (value == 2) {
            folder = "lava";
        }
        else if(value == 3) {
            folder = "cloud";
        }
        else if(value == 4) {
            folder = "desert";
        }
        else if(value == 5) {
            folder = "space";
        }

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
        label.setOnMouseEntered(e -> enteredWall(e));
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
        label.setOnMouseEntered(e -> enteredWall(e));
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
        label.setOnMouseEntered(e -> enteredBreakableWall(e));
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
        label.setOnMouseEntered(e -> {
            try {
                enteredGoal();
            } catch (FileNotFoundException | InterruptedException fileNotFoundException) {
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
        label.setOnMouseClicked(e -> startLevel());
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
        collectible.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseListener);
        collectibles.add(collectible);
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
        heartCrystal.setOnMouseEntered(e -> heartCrystalObtained(e));
        return heartCrystal;
    }

    /**
     * När en användare vidrör en label av typen heartCrystal körs denna metod.
     * Om spelaren har mindre än tre återstående liv inkrementeras variabeln heartCrystals.
     * @param e Används för att hitta rätt label.
     */

    private void heartCrystalObtained(MouseEvent e) {

        Label label = (Label)e.getSource();

        if (startButtonPressed) {
            audioPlayer.playHeartSound();
            label.setVisible(false);
            if(heartCrystals<3){
                heartCrystals++;
                rightPanel.changeHeartCounter(String.valueOf(heartCrystals));
            }
        }
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
        pickAxe.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseListener);
        pickaxes.add(pickAxe);
        return pickAxe;
    }

    /**
     * Om en spelare vidrör en vägg med muspekaren körs denna metod.
     * Om spelrundan är aktiverad förlorar spelaren ett liv.
     * Om spelaren endast har ett återstående liv kvar vid kollisionen körs metoden gameOver.
     * @param e Används för att hitta rätt label.
     */
    public void enteredWall(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(10);
        fade.setToValue(0.6);
        fade.play();

        if (startButtonPressed) {

            heartCrystals--;
            rightPanel.changeHeartCounter(String.valueOf(heartCrystals));

            if (heartCrystals == 0) {
                gameOver();
            }
            audioPlayer.playDeathSound();
            startButtonPressed = false;
        }
    }

    /**
     * Om en spelare vidrör ett spöke med muspekaren körs denna metod.
     * Om spelrundan är aktiverad förlorar spelaren ett liv.
     * Om spelaren endast har ett återstående liv kvar vid kollisionen körs metoden gameOver.
     * @param e
     */
    public void enteredGhost(MouseEvent e){
        ImageView view = (ImageView) e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(view);
        fade.setDuration(Duration.seconds(1));
        fade.setFromValue(10);
        fade.setToValue(0.6);
        fade.setToValue(10);
        fade.play();


        if (startButtonPressed) {
            audioPlayer.playMobSound();
            audioPlayer.playDeathSound();
            heartCrystals--;
            rightPanel.changeHeartCounter(String.valueOf(heartCrystals));

            if (heartCrystals == 0) {
                gameOver();
            }
            startButtonPressed = false;
        }
    }

    /**
     * Avslutar spelrundan och kör metoden gameOver i mainProgram.
     */
    private void gameOver() {
        audioPlayer.playGameOverSound();
        audioPlayer.stopMusic();
        mainProgram.gameOver();
        rightPanel.pauseClock();
        gameStarted = true;
        time.setGameOver(true);
        rightPanel.setGameOver(true);
        time = null;
        rightPanel.removePickaxe();

    }

    /**
     * Om spelrundan är aktiverad och spelaren har plockat upp alla collectibles startas nästa nivå.
     * @throws FileNotFoundException
     * @throws InterruptedException
     */
    public void enteredGoal() throws FileNotFoundException, InterruptedException {
        if (startButtonPressed && allCollectiblesObtained) {
            audioPlayer.stopClockSound();
            audioPlayer.playGoalSound();
            nextLevel();
            rightPanel.pauseClock();
            rightPanel.setTheTime(seconds);
            gameStarted = true;
            time.setGameOver(true);
            time = null;
        }
    }

    /**
     * Baserad på den aktuella världen väljer programmmet vilken nivå som ska spelas.
     * @throws FileNotFoundException
     * @throws InterruptedException
     */
    public void nextLevel() throws FileNotFoundException, InterruptedException {

        if (world == 0) {
            mainProgram.nextWorld1Level(currentLevel, heartCrystals);
        }
        else if (world == 1) {
            mainProgram.nextWorld2Level(currentLevel, heartCrystals);
        }
        else if (world == 2) {
            mainProgram.nextWorld3Level(currentLevel, heartCrystals);
        }
        else if (world == 3) {
            mainProgram.nextWorld4Level(currentLevel, heartCrystals);
        }
        else if (world == 4) {
            mainProgram.nextWorld5Level(currentLevel, heartCrystals);
        }
        else if (world == 5) {
            mainProgram.nextWorld6Level(currentLevel, heartCrystals);
        }
    }

    /**
     * Startar spelrundan och timern.
     */
    public void startLevel() {

        if (!totalTimeStarted){
            rightPanel.startTotalTimer();
            rightPanel.setTimerIsStarted(true);
        }

        if (!gameStarted){
            rightPanel.resumeClock();
            gameStarted = true;
            time = new TimeThread(seconds, rightPanel);
            time.setGameOver(false);
            time.start();

        }else if (startNotClickedOnce){
            rightPanel.runClock();
            time = new TimeThread(seconds, rightPanel);
            time.setGameOver(false);
            time.start();

        }
        totalTimeStarted = true;
        startNotClickedOnce = false;
        audioPlayer.playStartSound();
        startButtonPressed = true;
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

    /**
     * Om spelrundan är startad och spelaren har plockat upp en yxa går det att förstöra väggen.
     * Om spelrundan är startad och spelaren inte plockat upp en yxa förlorar hen ett liv vid kollision med väggen.
     * @param e Används för att hitta rätt label.
     */
    public void enteredBreakableWall(MouseEvent e) {

        Label label = (Label)e.getSource();
        ImageView pathView = new ImageView(path);

        if (startButtonPressed) {

            if (pickaxeObtained) {
                label.setGraphic(pathView);
                pickaxeObtained = false;
                rightPanel.removePickaxe();
                wallDestroyed = true;
                audioPlayer.playBreakableWallSound();
            }
            else if (!wallDestroyed) {
                enteredWall(e);
            }
        }
    }

    /**
     * En listener som körs när spelaren plockar upp en collectible eller en yxa.
     */
    private class MouseListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            if (startButtonPressed) {

                for (Label label : pickaxes){
                    if (e.getSource()== label){
                        audioPlayer.playPickAxeSound();
                        label.setVisible(false);
                        pickaxeObtained = true;
                        rightPanel.addPickaxe();
                    }
                }

                for (Label label: collectibles) {
                    if (e.getSource() == label) {
                        audioPlayer.playCollectibleSound();
                        label.setVisible(false);
                        collectiblesObtained++;
                        if (collectiblesObtained == collectibles.size()) {
                            allCollectiblesObtained = true;
                        }
                    }
                }

            }
        }
    }
    // In World1Template class
    public int getHeartCrystals() {
        return heartCrystals;
    }

    public boolean isPickaxeObtained() {
        return pickaxeObtained;
    }

    public boolean isStartButtonPressed() {
        return startButtonPressed;
    }
    public MouseListener getMouseListener() {
        return mouseListener;
    }
}
