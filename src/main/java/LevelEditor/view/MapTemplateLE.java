package LevelEditor.view;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.MazeGeneration.GenerateNextLevel;
import control.MainProgram;
import javafx.animation.FadeTransition;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.scene.media.Media;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
    private String baseTheme;


    /**
     * Konstruktorn ska kunna ta emot int-arrayer och representera dem i GUIt
     */
    public MapTemplateLE(int[][] level, MainProgram mainProgram, GenerateNextLevel generateNextLevel, int themeInt) throws FileNotFoundException {
        System.out.println("Theme in maptemplkate " + themeInt);
        this.mainProgram = mainProgram;
        this.level = level;
        this.generateNextLevel = generateNextLevel;
        this.themeInt = themeInt;
        this.baseTheme = getThemeString(themeInt);

        squareSize = 600/(level.length+2);
        setBackground();
        setupImages(baseTheme);
        setupBorders();
        setupLevel();
    }

    private String getThemeString(int themeInt) {
        switch (themeInt) {
            case 0: return "forest";
            case 1: return "lava";
            case 2: return "underground";
            case 3: return "cloud";
            case 4: return "desert";
            case 5: return "space";
            default: return "forest";
        }
    }


    public MapTemplateLE(int[][] level, MainProgram mainProgram, int themeInt) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.level = level;
        this.themeInt = themeInt;


        squareSize = 600/(level.length+2);
        setBackground();
        setupImages(baseTheme);
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
            add(getBorders(baseTheme), i, 0);
        }
        for (int i = 0; i < level.length + 1; i++) {
            add(getBorders(baseTheme), 0, i);
        }
        for (int i = 0; i < level.length + 2; i++) {
            add(getBorders(baseTheme), i, level.length + 1);
        }
        for (int i = 0; i < level.length + 2; i++) {
            add(getBorders(baseTheme), level.length + 1, i);
        }
    }

    public void updateMapTemplate(int[][] level) {
        this.level = level;
        setupLevel();
    }
    /**
     * Omvandlar värdena i arrayen av siffror till olika grafiska komponenter baserat på vilken siffra en position har.
     * Exempelvis så representerar 1:or väg, 0:or väggar, och 7:or hjärtan osv.
     */
    public void setupLevel() {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level.length; j++) {
                switch (level[i][j]) {
                    case 0:
                        add(getWall(baseTheme), j + 1, i + 1);
                        break;
                    case 1:
                        add(getPath(baseTheme), j + 1, i + 1);
                        break;
                    case 2:
                        add(getStart(baseTheme), j + 1, i + 1);
                        break;
                    case 3:
                        add(getGoal(baseTheme), j + 1, i + 1);
                        break;
                    case 4:
                        add(getPath(baseTheme), j + 1, i + 1);
                        add(addCollectible(baseTheme), j + 1, i + 1);
                        break;
                    case 5:
                        add(getPath(baseTheme), j + 1, i + 1);
                        add(addHeart(baseTheme), j + 1, i + 1);
                        break;
                    case 6:
                        add(getPath(baseTheme), j + 1, i + 1);
                        add(addPickaxe(baseTheme), j + 1, i + 1);
                        break;
                    case 7:
                        add(getBreakableWall(baseTheme), j + 1, i + 1);
                        break;
                    case 8:
                        add(getBorders(baseTheme), j + 1, i + 1);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Instansierar de olika bilderna som används som grafik inuti spelet.
     * Baserad på value så sätts bilderna till en specifik folder per värld.
     */
    private void setupImages(String theme) {
        path = new Image("file:files/" + theme + "/path.png", squareSize, squareSize, false, false);
        goal = new Image("file:files/" + theme + "/goal.png", squareSize, squareSize, false, false);
        diamond = new Image("file:files/" + theme + "/collectible.png", squareSize, squareSize, false, false);
        start = new Image("file:files/" + theme + "/start.png", squareSize, squareSize, false, false);

        if (!theme.equals("space")) {
            border = new Image("file:files/" + theme + "/border.png", squareSize, squareSize, false, false);
            wall = new Image("file:files/" + theme + "/wall.png", squareSize, squareSize, false, false);
        }

        wall = new Image("file:files/" + theme + "/breakableWall.png", squareSize, squareSize, false, false);
    }
    /**
     * En metod som skapar ett objekt av label som representerar en vägg.
     * @return Returnerar en label.
     */
    public Label getWall(String theme) {
        Label label = new Label();
        Image wallImage = new Image("file:files/" + theme + "/wall.png", squareSize, squareSize, false, false);
        ImageView wallView = new ImageView(wallImage);
        wallView.setFitHeight(squareSize);
        wallView.setFitWidth(squareSize);
        label.setGraphic(wallView);
        label.setOnMouseEntered(e -> enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        label.setOnDragOver(e -> holdOnLabel(e));
        label.setOnDragDropped(e -> releaseOnLabel(e));
        return label;
    }

    private Label getBreakableWall(String theme) {
        Label breakableWallLabel = new Label();
        Image breakableWallImage = new Image("file:files/" + theme + "/breakableWall.png", squareSize, squareSize, false, false);
        ImageView breakableWallView = new ImageView(breakableWallImage);
        breakableWallView.setFitHeight(squareSize);
        breakableWallView.setFitWidth(squareSize);
        breakableWallLabel.setGraphic(breakableWallView);
        breakableWallLabel.setOnMouseEntered(e -> enteredWall(e));
        breakableWallLabel.setOnMouseExited(e -> exitedLabel(e));
        breakableWallLabel.setOnDragOver(e -> holdOnLabel(e));
        breakableWallLabel.setOnDragDropped(e -> releaseOnLabel(e));
        return breakableWallLabel;
    }

    /**
     * En metod som skapar ett objekt av label som representerar en väg.
     * @return Returnerar en label.
     */
    public Label getPath(String theme) {
        Label label = new Label();
        Image pathImage = new Image("file:files/" + theme + "/path.png", squareSize, squareSize, false, false);
        ImageView pathView = new ImageView(pathImage);
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
    private Label getBorders(String theme) {
        Label label = new Label();
        Image borderImage = new Image("file:files/" + theme + "/border.png", squareSize, squareSize, false, false);
        ImageView borderView = new ImageView(borderImage);
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
     * En metod som skapar ett objekt av label som representerar en förstörbar vägg.
     * @return Returnerar en label.
     */
    private Label getGoal(String theme) {
        Label label = new Label();
        Image goalImage = new Image("file:files/" + theme + "/goal.png", squareSize, squareSize, false, false);
        ImageView borderView = new ImageView(goalImage);
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
    private Label getStart(String theme) {
        Label label = new Label();
        Image startImage = new Image("file:files/" + theme + "/start.png", squareSize, squareSize, false, false);
        ImageView borderView = new ImageView(startImage);
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
    public Label addCollectible(String theme) {
        Label collectible = new Label();
        Image diamond = new Image("file:files/" + theme + "/collectible.png", squareSize, squareSize, false, false);
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
    private Label addHeart(String theme) {
        Label heartLabel = new Label();
        Image heartImage = new Image("file:files/items/heart.png", squareSize, squareSize, false, false);
        ImageView heartView = new ImageView(heartImage);
        heartView.setFitHeight(squareSize);
        heartView.setFitWidth(squareSize);
        heartLabel.setGraphic(heartView);
        heartLabel.setOnMouseEntered(e -> enteredWall(e));
        heartLabel.setOnMouseExited(e -> exitedLabel(e));
        heartLabel.setOnDragOver(e -> holdOnLabel(e));
        heartLabel.setOnDragDropped(e -> releaseOnLabel(e));
        return heartLabel;
    }

    private Label addPickaxe(String theme) {
        Label pickaxeLabel = new Label();
        Image pickaxeImage = new Image("file:files/items/pickaxe.png", squareSize, squareSize, false, false);
        ImageView pickaxeView = new ImageView(pickaxeImage);
        pickaxeView.setFitHeight(squareSize);
        pickaxeView.setFitWidth(squareSize);
        pickaxeLabel.setGraphic(pickaxeView);
        pickaxeLabel.setOnMouseEntered(e -> enteredWall(e));
        pickaxeLabel.setOnMouseExited(e -> exitedLabel(e));
        pickaxeLabel.setOnDragOver(e -> holdOnLabel(e));
        pickaxeLabel.setOnDragDropped(e -> releaseOnLabel(e));
        return pickaxeLabel;
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

        e.acceptTransferModes(TransferMode.COPY_OR_MOVE);

        e.consume();
    }


    private void releaseOnLabel(DragEvent e) {
        Dragboard db = e.getDragboard();
        boolean success = false;
        Label dropTargetLabel = (Label) e.getGestureTarget();

        String[] data = db.getString().split(",");
        int type = Integer.parseInt(data[0]);
        String objectTheme = data[1];

        int row = GridPane.getRowIndex(dropTargetLabel) - 1;
        int col = GridPane.getColumnIndex(dropTargetLabel) - 1;
        level[row][col] = type;

        updateLevelWithObjectTheme(row, col, objectTheme);

        success = true;
        e.setDropCompleted(success);
        e.consume();
    }

    /**
     * En metod som uppdaterar level med en ny label.
     * @param row Raden som ska uppdateras.
     * @param col Kolumnen som ska uppdateras.
     * @param objectTheme Temat som ska användas.
     */
    private void updateLevelWithObjectTheme(int row, int col, String objectTheme) {
        this.getChildren().removeIf(node -> {
            Integer nodeRow = GridPane.getRowIndex(node);
            Integer nodeCol = GridPane.getColumnIndex(node);
            return nodeRow != null && nodeCol != null && nodeRow == row + 1 && nodeCol == col + 1;
        });

        switch (level[row][col]) {
            case 0:
                add(getWall(objectTheme), col + 1, row + 1);
                break;
            case 1:
                add(getPath(objectTheme), col + 1, row + 1);
                break;
            case 2:
                add(getStart(objectTheme), col + 1, row + 1);
                break;
            case 3:
                add(getGoal(objectTheme), col + 1, row + 1);
                break;
            case 4:
                add(getPath(baseTheme), col + 1, row + 1);
                add(addCollectible(objectTheme), col + 1, row + 1);
                break;
            case 5:
                add(getPath(baseTheme), col + 1, row + 1);
                add(addHeart(objectTheme), col + 1, row + 1);
                break;
            case 6:
                add(getPath(baseTheme), col + 1, row + 1);
                add(addPickaxe(objectTheme), col + 1, row + 1);
                break;
            case 7:
                add(getBreakableWall(objectTheme), col + 1, row + 1);
            case 8:
                add(getBorders(objectTheme), col + 1, row + 1);
                break;
            default:
                break;
        }
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
