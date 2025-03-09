package control;


import LevelEditor.controller.MainLE;
import LevelEditor.view.MapTemplateLE;
import LevelEditor.view.MenuLE;
import LevelEditor.view.SetUp;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import java.util.Arrays;

/**
 * Updated MainProgram implementing a scalable UI with a special layout for the Level Editor.
 */
public class MainProgram extends Application {

    private Stage mainWindow;
    private BorderPane mainPaneRandomMaze;
    private BorderPane mainPaneCampaign;

    private MapTemplate mapTemplate;
    private MapTemplateLE mapTemplateLE;
    private Scene menuScene;
    private Scene introScene;
    private Scene helpScene;
    private Scene highscoreScene;
    private Scene chooseDimensionScene;
    private Scene selectMapScene;
    private Scene selectLevelScene;
    private Scene levelEditorScene; // new scene for level editor
    private Scene LEScene;
    private Scene menuLEScene;
    private Intro intro;
    private Menu menu;
    private Help help;
    private SetUp setUp;
    private MenuLE menuLE;
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
        setUp = new SetUp(this, audioPlayer);
        menuLE = new MenuLE(this, audioPlayer, setUp.getMainLE());



        // Create scaled scenes using our helper method.
        introScene = createScaledScene(intro);
        menuScene = createScaledScene(menu);
        helpScene = createScaledScene(help);
        selectMapScene = createScaledScene(selectWorldMap);
        chooseDimensionScene = createScaledScene(chooseDimension);
        highscoreScene = createScaledScene(highscoreView);
        LEScene = createScaledScene(setUp);
        menuLEScene = createScaledScene(menuLE);



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

    public void enterLevelEditor(int dimension, int themeInt) throws FileNotFoundException {
        // 1. Create a new BorderPane for the level editor.
        BorderPane levelEditorPane = new BorderPane();

        // 2. Create your editor pane/content (initially empty or customized as you wish).
        Pane editorContent = new Pane();
        // ...Add any setup for editorContent here...

        // 3. Optionally apply scaling to everything in the center.
        //    Wrap your editor content in a Group so the scale transform is applied to both
        //    the editor content AND the map template.
        Group editorGroup = new Group(editorContent);

        Scale scale = new Scale(scaleFactor, scaleFactor);
        editorGroup.getTransforms().add(scale);

        // 4. Create the larger right panel for the level editor.
        RightPanel levelEditorRightPanel = new RightPanel(this, audioPlayer, themeInt, setUp.getMainLE());
        levelEditorRightPanel.setPrefWidth(515);
        levelEditorRightPanel.setBackground(
                new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))
        );
        levelEditorPane.setRight(levelEditorRightPanel);

        // 5. Pick a custom resolution for the scene (slightly wider to accommodate the bigger right panel).
        double editorScaleFactor = scaleFactor;
        double editorSceneWidth = designWidth * editorScaleFactor + 275;
        double editorSceneHeight = designHeight * editorScaleFactor;
        levelEditorScene = new Scene(levelEditorPane, editorSceneWidth, editorSceneHeight, Color.BLACK);

        // 6. Set a custom cursor if needed.
        levelEditorScene.setCursor(new ImageCursor(cursorImage));

        // 7. Generate your maze, create `mapTemplateLE`, and the code to manage the level logic.
        mazeGenerator = new MazeGenerator(dimension, true, true);
        generateNextLevel = new GenerateNextLevel(this, levelEditorPane, mazeGenerator, levelEditorRightPanel, dimension, themeInt);
        mapTemplateLE = new MapTemplateLE(mazeGenerator.getMaze(), this, generateNextLevel, themeInt);

        setUp.getMainLE().setMazeGenerator(mazeGenerator);

        // 8. Add mapTemplateLE to the same group so it scales together with the editorContent.
        editorGroup.getChildren().add(mapTemplateLE);

        // 9. Set the center just once, with the group containing both editor content and the map template.
        BorderPane.setAlignment(editorGroup, Pos.TOP_LEFT);
        levelEditorPane.setCenter(editorGroup);

        // 10. Finally, show this scene in your primary stage/window.
        mainWindow.setScene(levelEditorScene);
    }
//TODO fix this
    public void enterLevelEditorFromEdit(int[][] maze, int dimension, int themeInt) throws FileNotFoundException {
        System.out.println("Maze vid enterLevelEditorFromEdit:" + Arrays.deepToString(maze));
        BorderPane levelEditorPane = new BorderPane();
        Pane editorContent = new Pane();
        Group editorGroup = new Group(editorContent);

        Scale scale = new Scale(scaleFactor, scaleFactor);
        editorGroup.getTransforms().add(scale);

        RightPanel levelEditorRightPanel = new RightPanel(this, audioPlayer, themeInt, setUp.getMainLE());
        levelEditorRightPanel.setPrefWidth(500);
        levelEditorRightPanel.setBackground(
                new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))
        );
        levelEditorPane.setRight(levelEditorRightPanel);

        double editorScaleFactor = scaleFactor;
        double editorSceneWidth = designWidth * editorScaleFactor + 150;
        double editorSceneHeight = designHeight * editorScaleFactor;
        levelEditorScene = new Scene(levelEditorPane, editorSceneWidth, editorSceneHeight, Color.BLACK);

        levelEditorScene.setCursor(new ImageCursor(cursorImage));

        mazeGenerator = new MazeGenerator(maze, false, true);
        System.out.println("Maze vid enterLevelEditorFromEdit 1:");
        for (int i = 0; i < maze.length; i++) {
            System.out.println(java.util.Arrays.toString(maze[i]));
        }


        generateNextLevel = new GenerateNextLevel(this, levelEditorPane, mazeGenerator, levelEditorRightPanel, dimension, themeInt);
        System.out.println("Maze vid enterLevelEditorFromEdit:");
        for (int i = 0; i < maze.length; i++) {
            System.out.println(java.util.Arrays.toString(maze[i]));
        }

        mapTemplateLE = new MapTemplateLE(maze, this, generateNextLevel, themeInt);
        System.out.println("Maze vid enterLevelEditorFromEdit:");
        for (int i = 0; i < maze.length; i++) {
            System.out.println(java.util.Arrays.toString(maze[i]));
        }


        editorGroup.getChildren().add(mapTemplateLE);

        BorderPane.setAlignment(editorGroup, Pos.TOP_LEFT);
        levelEditorPane.setCenter(editorGroup);

        mainWindow.setScene(levelEditorScene);
    }

    /**
     * Switches to the menu scene.
     */
    public void changeToMenu() {
        mainWindow.setScene(menuScene);
    }

    public void changeToLevelEditor() {
        setDimensionLevelEditor();
    }

    public void setDimensionLevelEditor() {
        mainWindow.setScene(LEScene);
    }

    public void setDimensionLevelEditormenu() {
        mainWindow.setScene(menuLEScene);
    }

    public void changeToLevelEditorMenu() {
        setDimensionLevelEditormenu();
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

    public RightPanel getRightPanel()
    {
        return rightPanel;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
