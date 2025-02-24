package control;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.HighscoreList;
import model.MazeGeneration.GenerateNextLevel;
import model.MazeGeneration.MazeGenerator;
import view.AudioPlayer;
import view.GameOverScreen;
import view.Menu.*;
import view.Randomize.MapTemplate;
import view.WorldIntroAnimation;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Updated MainProgram implementing a scalable UI with a special layout for the Level Editor.
 */
public class MainProgram extends Application {

    private Stage mainWindow;
    private BorderPane mainPaneRandomMaze;
    private BorderPane mainPaneCampaign;
    private MapTemplate mapTemplate;
    private Scene menuScene;
    private Scene introScene;
    private Scene helpScene;
    private Scene highscoreScene;
    private Scene chooseDimensionScene;
    private Scene selectMapScene;
    private Scene selectLevelScene;
    private Scene levelEditorScene; // new scene for level editor
    private Intro intro;
    private Menu menu;
    private Help help;
    private ChooseDimension chooseDimension;
    private SelectWorldMap selectWorldMap;
    private SelectLevel selectLevel;
    private HighscoreView highscoreView;
    private Scene randomScene;
    private Scene campaignScene;
    private RightPanel rightPanel;
    private RightPanel rightPnlRndm;
    private MazeGenerator mazeGenerator;
    private GenerateNextLevel generateNextLevel;
    private WorldIntroAnimation introAnimation;
    private AudioPlayer audioPlayer;
    private GameOverScreen gameOverScreen;
    private Image cursorImage;
    private GameController gameController;
    private HighscoreList highscoreList;

    // Base dimensions your UI was designed for.
    private final double designWidth = 800;
    private final double designHeight = 600;

    // Scale factor for the main UI scenes.
    private double scaleFactor = 1.2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        audioPlayer = new AudioPlayer();
        audioPlayer.playIntroMusic();

        rightPanel = new RightPanel(this, "11", audioPlayer, null);
        rightPanel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        menu = new Menu(this, audioPlayer, rightPanel);
        intro = new Intro(this, audioPlayer);
        help = new Help(this, audioPlayer);
        highscoreList = new HighscoreList();
        highscoreView = new HighscoreView(this, audioPlayer, 1, highscoreList);
        chooseDimension = new ChooseDimension(this, audioPlayer);
        selectWorldMap = new SelectWorldMap(this, audioPlayer);

        // Create scaled scenes using our helper method.
        introScene = createScaledScene(intro);
        menuScene = createScaledScene(menu);
        helpScene = createScaledScene(help);
        selectMapScene = createScaledScene(selectWorldMap);
        chooseDimensionScene = createScaledScene(chooseDimension);
        highscoreScene = createScaledScene(highscoreView);

        mainPaneRandomMaze = new BorderPane();
        mainPaneCampaign = new BorderPane();
        introAnimation = new WorldIntroAnimation();

        mainWindow = primaryStage;
        cursorImage = new Image("file:files/imagecursor.png");

        mainWindow.setTitle("Mazegen");
        // Prevent user from resizing the window manually.
        mainWindow.setResizable(false);

        mainPaneCampaign.setRight(rightPanel);

        rightPnlRndm = new RightPanel(this, "Random", audioPlayer, null);
        rightPnlRndm.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        mainPaneRandomMaze.setRight(rightPnlRndm);

        campaignScene = createScaledScene(mainPaneCampaign);
        randomScene = createScaledScene(mainPaneRandomMaze);

        mainWindow.setScene(introScene);
        mainWindow.show();

        // Set custom cursors
        introScene.setCursor(new ImageCursor(cursorImage));
        menuScene.setCursor(new ImageCursor(cursorImage));
        campaignScene.setCursor(new ImageCursor(cursorImage));
        chooseDimensionScene.setCursor(new ImageCursor(cursorImage));
        helpScene.setCursor(new ImageCursor(cursorImage));
        randomScene.setCursor(new ImageCursor(cursorImage));

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                try {
                    highscoreList.write();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);
            }
        });
    }

    /**
     * Helper method that wraps a given Pane in a Group,
     * applies a fixed Scale transform, and returns a new Scene with a black background.
     */
    private Scene createScaledScene(Pane rootPane) {
        Group group = new Group(rootPane);
        double sceneWidth = designWidth * scaleFactor;
        double sceneHeight = designHeight * scaleFactor;
        Scene scene = new Scene(group, sceneWidth, sceneHeight, Color.BLACK);
        Scale scale = new Scale(scaleFactor, scaleFactor);
        group.getTransforms().add(scale);
        return scene;
    }

    /**
     * Enters the special Level Editor mode with a custom layout and larger right panel.
     */
    public void enterLevelEditor() throws FileNotFoundException {
        // Create a new BorderPane for the level editor.
        BorderPane levelEditorPane = new BorderPane();

        // Create the center area for level editing (e.g., a canvas or editor pane)
        Pane editorContent = new Pane();
        // (Add your level editor content initialization here)

        // Optionally apply scaling to the editor content if desired.
        Group editorGroup = new Group(editorContent);
        Scale scale = new Scale(scaleFactor, scaleFactor);
        editorGroup.getTransforms().add(scale);
        levelEditorPane.setCenter(editorGroup);

        // Create a special right panel for the level editor, with a larger preferred width.
        RightPanel levelEditorRightPanel = new RightPanel(this, "Editor", audioPlayer, null);
        levelEditorRightPanel.setPrefWidth(300); // Make this area proportionally bigger.
        levelEditorRightPanel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        levelEditorPane.setRight(levelEditorRightPanel);

        // Choose a special resolution for the level editor.
        // For example, add extra width to accommodate the larger right panel.
        double editorScaleFactor = scaleFactor; // You can modify this if needed.
        double editorSceneWidth = designWidth * editorScaleFactor + 100; // Extra width for the right panel.
        double editorSceneHeight = designHeight * editorScaleFactor;
        levelEditorScene = new Scene(levelEditorPane, editorSceneWidth, editorSceneHeight, Color.BLACK);
        levelEditorScene.setCursor(new ImageCursor(cursorImage));

        // Switch to the level editor scene.
        mainWindow.setScene(levelEditorScene);
    }

    /**
     * Switches to the menu scene.
     */
    public void changeToMenu() {
        mainWindow.setScene(menuScene);
    }

    /**
     * Switches to the Randomize scene (random maze).
     */
    public void changeToRandomize(int dimension) throws FileNotFoundException {
        mazeGenerator = new MazeGenerator(dimension, true);
        generateNextLevel = new GenerateNextLevel(this, mainPaneRandomMaze, mazeGenerator, rightPanel, dimension);
        mapTemplate = new MapTemplate(mazeGenerator.getMaze(), this, generateNextLevel);
        mainPaneRandomMaze.setCenter(mapTemplate);
        mainWindow.setScene(randomScene);
    }

    /**
     * Switches to the Campaign scene.
     */
    public void changeToCampaign() {
        gameController = new GameController(this, rightPanel, audioPlayer, gameOverScreen, mainPaneCampaign, 1, 1);
        try {
            gameController.campaignWorldManager();
        } catch (InterruptedException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        mainWindow.setScene(campaignScene);
        gameController.setUpNewWorldAnimation();
    }

    public GameController getCampaignController() {
        return gameController;
    }

    /**
     * Switches to a specified campaign level.
     */
    public void changeToSpecifiedCampaign(int world, int level) {
        gameController = new GameController(this, rightPanel, audioPlayer, gameOverScreen, mainPaneCampaign, world, level);
        try {
            gameController.campaignWorldManager();
        } catch (InterruptedException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        mainWindow.setScene(campaignScene);
        gameController.setUpNewWorldAnimation();
    }

    /**
     * Switches to the dimension selection scene.
     */
    public void chooseDimension() {
        mainWindow.setScene(chooseDimensionScene);
    }

    /**
     * Switches to the world map selection scene.
     */
    public void selectWorldMap() {
        mainWindow.setScene(selectMapScene);
    }

    /**
     * Switches to the level selection scene.
     */
    public void selectLevelMap(int map) {
        selectLevel = new SelectLevel(this, audioPlayer, map);
        selectLevelScene = createScaledScene(selectLevel);
        mainWindow.setScene(selectLevelScene);
    }

    /**
     * Switches to the help scene.
     */
    public void changeToHelp() {
        mainWindow.setScene(helpScene);
    }

    /**
     * Switches to the highscore scene.
     */
    public void showHighscore() {
        mainWindow.setScene(highscoreScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
