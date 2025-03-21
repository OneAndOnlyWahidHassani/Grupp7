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
import model.TimeThread;
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
 * @author Wahid Hassani, Linus Sigurd, Elvira Grubb, Alanah Coleman, Kasper Svenlin , Viktor Näslund, André Eklund
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


    /**
     * Main method that starts the application.
     * @param primaryStage
     * @throws Exception
     * @author Linus Sigurd, Alanah Coleman, Wahid Hassani, Elvira Grubb,  Kasper Svenlin, Viktor Näslund, André Eklund
     */

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
    /**
     * @author Linus Sigurd
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
     * Switches to the level editor scene.
     * @author Linus Sigurd
     * @edit Wahid Hassani
     */
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


        // 5. Pick a custom resolution for the scene (slightly wider to accommodate the bigger right panel).
        double editorScaleFactor = scaleFactor;
        double editorSceneWidth = designWidth * editorScaleFactor + 275;
        double editorSceneHeight = designHeight * editorScaleFactor;
        levelEditorScene = new Scene(levelEditorPane, editorSceneWidth, editorSceneHeight, Color.BLACK);

        // 6. Set a custom cursor if needed.
        levelEditorScene.setCursor(new ImageCursor(cursorImage));

        // 7. Generate your maze, create `mapTemplateLE`, and the code to manage the level logic.
        mazeGenerator = new MazeGenerator(dimension, true, true);
        generateNextLevel = new GenerateNextLevel(this, levelEditorPane, mazeGenerator, dimension, themeInt);
        mapTemplateLE = new MapTemplateLE(mazeGenerator.getMaze(), this, generateNextLevel, themeInt);

        setUp.getMainLE().setMazeGenerator(mazeGenerator);

        RightPanel levelEditorRightPanel = new RightPanel(this, audioPlayer, themeInt, setUp.getMainLE(), mapTemplateLE);
        levelEditorRightPanel.setPrefWidth(515);
        levelEditorRightPanel.setBackground(
                new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))
        );
        levelEditorPane.setRight(levelEditorRightPanel);

        // 8. Add mapTemplateLE to the same group so it scales together with the editorContent.
        editorGroup.getChildren().add(mapTemplateLE);

        // 9. Set the center just once, with the group containing both editor content and the map template.
        BorderPane.setAlignment(editorGroup, Pos.TOP_LEFT);
        levelEditorPane.setCenter(editorGroup);

        // 10. Finally, show this scene in your primary stage/window.
        mainWindow.setScene(levelEditorScene);
    }

    /**
     * Switches to the level editor scene with a specified maze.
     * @param maze the maze to edit
     * @param dimension the size of the maze
     * @param themeInt the theme of the maze
     * @throws FileNotFoundException if there is an issue loading the maze files
     * @author Wahid Hassani
     */

    public void enterLevelEditorFromEdit(int[][] maze, int dimension, int themeInt) throws FileNotFoundException {
        System.out.println("Maze vid enterLevelEditorFromEdit:" + Arrays.deepToString(maze));
        BorderPane levelEditorPane = new BorderPane();
        Pane editorContent = new Pane();
        Group editorGroup = new Group(editorContent);

        Scale scale = new Scale(scaleFactor, scaleFactor);
        editorGroup.getTransforms().add(scale);



        double editorScaleFactor = scaleFactor;
        double editorSceneWidth = designWidth * editorScaleFactor + 275;
        double editorSceneHeight = designHeight * editorScaleFactor;
        levelEditorScene = new Scene(levelEditorPane, editorSceneWidth, editorSceneHeight, Color.BLACK);

        levelEditorScene.setCursor(new ImageCursor(cursorImage));

        mazeGenerator = new MazeGenerator(maze, false, true);
        System.out.println("Maze vid enterLevelEditorFromEdit 1:");
        for (int i = 0; i < maze.length; i++) {
            System.out.println(java.util.Arrays.toString(maze[i]));
        }


        generateNextLevel = new GenerateNextLevel(this, levelEditorPane, mazeGenerator, dimension, themeInt);
        System.out.println("Maze vid enterLevelEditorFromEdit:");
        for (int i = 0; i < maze.length; i++) {
            System.out.println(java.util.Arrays.toString(maze[i]));
        }

        mapTemplateLE = new MapTemplateLE(maze, this, generateNextLevel, themeInt);
        System.out.println("Maze vid enterLevelEditorFromEdit:");
        for (int i = 0; i < maze.length; i++) {
            System.out.println(java.util.Arrays.toString(maze[i]));
        }

        RightPanel levelEditorRightPanel = new RightPanel(this, audioPlayer, themeInt, setUp.getMainLE(), mapTemplateLE);
        levelEditorRightPanel.setPrefWidth(515);
        levelEditorRightPanel.setBackground(
                new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))
        );
        levelEditorPane.setRight(levelEditorRightPanel);


        editorGroup.getChildren().add(mapTemplateLE);

        BorderPane.setAlignment(editorGroup, Pos.TOP_LEFT);
        levelEditorPane.setCenter(editorGroup);

        mainWindow.setScene(levelEditorScene);
    }

    /**
     * Starts a test level with the specified parameters.
     * @param themeInt
     * @param dimension
     * @param maze
     * @param seconds
     * @param lives
     * @return true if the test level was started successfully, false otherwise
     * @author Wahid Hassani
     */

    public boolean startTestLevel(int themeInt, int dimension, int[][] maze, int seconds, int lives) {
        BorderPane testLevelPane = new BorderPane();

        Pane testLevelContent = new Pane();
        Group testLevelGroup = new Group(testLevelContent);

        Scale scale = new Scale(scaleFactor, scaleFactor);
        testLevelGroup.getTransforms().add(scale);
        try {


            double testLevelSceneWidth = designWidth * scaleFactor + 275;
            double testLevelSceneHeight = designHeight * scaleFactor;
            Scene testLevelScene = new Scene(testLevelPane, testLevelSceneWidth, testLevelSceneHeight, Color.BLACK);

            testLevelScene.setCursor(new ImageCursor(cursorImage));

            MazeGenerator mazeGenerator = new MazeGenerator(maze, false, true);
            GenerateNextLevel generateNextLevel = new GenerateNextLevel(this, testLevelPane, mazeGenerator, dimension, themeInt);

            //todo lägg in level alltså maze direkt i din kontruktor för gamecontroller. I konrktorn skapa worldtemplate med vår maze och tema
            //todo skapa även en rightpanel som ser ut som i campaign som har modifierats med indata från testrightpanel
            RightPanel rightPanelforTestingLvl = new RightPanel(this, "LevelCustom", audioPlayer, null, null, seconds);
            rightPanelforTestingLvl.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            rightPanelforTestingLvl.setPrefWidth(515);


            gameController = new GameController(this, rightPanelforTestingLvl, audioPlayer, gameOverScreen, testLevelPane, 0 , lives, seconds, themeInt);

            MapTemplate mapTemplate = new MapTemplate(maze, this, gameController, generateNextLevel, themeInt);

            testLevelGroup.getChildren().add(mapTemplate);
            testLevelPane.setRight(rightPanelforTestingLvl);

            //todo: fixa med tiden och liv. Den behöver indatan från levelEditorn.
            BorderPane.setAlignment(testLevelGroup, Pos.TOP_LEFT);
            testLevelPane.setCenter(testLevelGroup);

            mainWindow.setScene(testLevelScene);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    /**
     * Switches to the menu scene.
     * @author Linus Sigurd
     */
    public void changeToMenu() {
        mainWindow.setScene(menuScene);
    }

    /**
     * Switches to the LevelEditor scene.
     * @author Alanah Coleman
     */

    public void changeToLevelEditor() {
        setDimensionLevelEditor();
    }

    /**
     * Sets dimension for the LevelEditor scene.
     * @author Alanah Coleman
     */

    public void setDimensionLevelEditor() {
        mainWindow.setScene(LEScene);
    }

    /**
     * Sets scene to the LevelEditor menu.
     * @author Alanah Coleman
     */

    public void setDimensionLevelEditormenu() {
        mainWindow.setScene(menuLEScene);
    }

    /**
     * Sets dimension to LevelEditor menu .
     * @author Alanah Coleman
     */

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
     * @Kasper Svenlin, Elvira Grubb
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

    /**
     * Returns the GameController.
     * @return gameController
     */

    public GameController getCampaignController() {
        return gameController;
    }

    /**
     * Switches to a specified campaign level.
     * @author Elvira Grubb
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
     * @author Elvira Grubb
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

    /**
     * Returns the right panel.
     * @return rightPanel
     * @author Elvira Grubb
     */

    public RightPanel getRightPanel()
    {
        return rightPanel;
    }

    /**
     * Returns the SetUp class
     * @return SetUp
     * @author Linus Sigurd
     */

    public SetUp getSetUp() {
        return setUp;
    }

    /**
     * Returns the MazeGenerator.
     * @return mazeGenerator
     * @author Linus Sigurd
     */

    public MazeGenerator getMazeGenerator() {
        return this.mazeGenerator;
    }

    /**
     * returns the MapTemplateLE.
     * @return mapTemplateLE
     * @author Linus Sigurd
     */

    public MapTemplateLE getMapTemplateLE() {
        return this.mapTemplateLE;
    }

    /**
     * Starts the application.
     * @param args
     */

    public static void main(String[] args) {
        launch(args);
    }
}
