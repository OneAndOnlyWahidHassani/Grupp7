package LevelEditor.view;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.MazeGeneration.GenerateNextLevel;
import control.MainProgram;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import javafx.scene.media.Media;
import view.Menu.RightPanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author André Eklund
 * edit Viktor Näslund
 */

public class MapTemplateLE extends GridPane {

    private MainProgram mainProgram;
    private GenerateNextLevel generateNextLevel;
    private int[][] level;
    private ArrayList<Label> collectibles = new ArrayList<>();
    private MouseListener mouseListener = new MouseListener();

    private boolean startButtonPressed;
    private boolean allCollectiblesObtained;
    private int collectiblesObtained = 0;
    private int squareSize;;

    private Image wall;
    private Image path;
    private Image border;
    private Image goal;
    private Image diamond;
    private Image start;

    private File diamondSound = new File("files/sounds/Diamond1.mp3");
    private Media diamondMedia = new Media(diamondSound.toURI().toString());
    private MediaPlayer diamondPlayer = new MediaPlayer(diamondMedia);

    private File deathSound = new File("files/sounds/MazegenDeath.mp3");
    private Media deathMedia = new Media(deathSound.toURI().toString());
    private MediaPlayer deathPlayer = new MediaPlayer(deathMedia);


    private File startSound = new File("files/sounds/MazegenStart.mp3");
    private Media startMedia = new Media(startSound.toURI().toString());
    private MediaPlayer startPlayer = new MediaPlayer(startMedia);

    private File goalSound = new File("files/sounds/MazegenGoal.mp3");
    private Media goalMedia = new Media(goalSound.toURI().toString());
    private MediaPlayer goalPlayer = new MediaPlayer(goalMedia);

    private int themeInt;


    /**
     * Konstruktorn ska kunna ta emot int-arrayer och representera dem i GUIt
     */
    public MapTemplateLE(int[][] level, MainProgram mainProgram, GenerateNextLevel generateNextLevel, int themeInt) throws FileNotFoundException {
        System.out.println("Theme in maptemplkate " + themeInt);
        this.mainProgram = mainProgram;
        this.level = level;
        this.generateNextLevel = generateNextLevel;
        this.themeInt = themeInt;


        squareSize = 600/(level.length+2);
        setBackground();
        setupImages(themeInt);
        setupBorders();
        setupLevel();
    }
    public MapTemplateLE(int[][] level, MainProgram mainProgram, int themeInt) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.level = level;
        this.themeInt = themeInt;


        squareSize = 600/(level.length+2);
        setBackground();
        setupImages(themeInt);
        setupBorders();
        setupLevel();
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
               else if (level[i][j]== 4) {
                    add(getPath(),j + 1,i + 1);
                    add(addCollectible(),j + 1,i + 1);
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
            folder = "lava";
        }
        else if (value == 2) {
            folder = "underground";
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
        label.setOnDragOver(e -> holdOnLabel(e));
        label.setOnDragDropped(e -> releaseOnLabel(e));
        return label;
    }
    /**
     * En metod som skapar ett objekt av label som representerar en väg.
     * @return Returnerar en label.
     */
    public Label getPath() {
        Label label = new Label();
        ImageView pathView = new ImageView(path);
        pathView.setFitHeight(squareSize);
        pathView.setFitWidth(squareSize);
        label.setGraphic(pathView);
        label.setOnMouseEntered(e -> enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        label.setOnDragOver(e -> holdOnLabel(e));
        label.setOnDragDropped(e -> releaseOnLabel(e));
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
    private Label getGoal() {
        Label label = new Label();
        ImageView borderView = new ImageView(goal);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseEntered(e -> enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        label.setOnDragOver(e -> holdOnLabel(e));
        label.setOnDragDropped(e -> releaseOnLabel(e));
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
        label.setOnMouseEntered(e -> enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        label.setOnDragOver(e -> holdOnLabel(e));
        label.setOnDragDropped(e -> releaseOnLabel(e));
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
        collectible.setOnMouseEntered(e -> enteredWall(e));
        collectible.setOnMouseExited(e -> exitedLabel(e));
        collectible.setOnDragOver(e -> holdOnLabel(e));
        collectible.setOnDragDropped(e -> releaseOnLabel(e));
        return collectible;
    }

    /**
     * Om spelaren vidrör muspekaren vid en vägg avslutas spelrundan.
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
            deathPlayer.play();
            deathPlayer.seek(Duration.ZERO);
            startButtonPressed = false;
        }
    }

    /**
     * När muspekaren lämnar en label slutar den att highlightas.
     * @param e Används för att hitta rätt label.
     */
    private void exitedLabel(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(0.6);
        fade.setToValue(10);
        fade.play();
    }
    private void holdOnLabel(DragEvent e) {
        // Only allow drop if it has a string (for example) and if the source allows copy or move

            // Indicate that we can accept this transfer mode
        e.acceptTransferModes(TransferMode.COPY_OR_MOVE);

        e.consume();
    }
    private void releaseOnLabel(DragEvent e) {
        Dragboard db = e.getDragboard();
        boolean success = false;
        Label dropTargetLabel = (Label) e.getGestureTarget();
        System.out.println(dropTargetLabel.getLayoutX() + " " + dropTargetLabel.getLayoutY() );

        int type = Integer.parseInt(db.getString());
        int row = GridPane.getRowIndex(dropTargetLabel) -1;
        int col = GridPane.getColumnIndex(dropTargetLabel) -1;
        System.out.println("Row: " + row + " Col: " + col);

        level[row][col] = type;
        System.out.println(level[row][col]);
        updateLevel();


        System.out.println("Dropped on label");
        success = true;

        e.setDropCompleted(success);
        e.consume();


    }

    private void updateLevel() {
        this.getChildren().clear();
        setupBorders();
        setupLevel();
    }



    /**
     * En listener som körs när spelaren plockar upp en collectible.
     */
    private class MouseListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            if (startButtonPressed) {
                diamondPlayer.play();
                diamondPlayer.seek(Duration.ZERO);
                for (Label label: collectibles) {
                    if (e.getSource() == label) {
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
}
