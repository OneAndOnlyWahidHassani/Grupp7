package model.MazeGeneration;

import LevelEditor.view.MapTemplateLE;
import control.MainProgram;
import javafx.scene.layout.BorderPane;
import view.Randomize.MapTemplate;

import view.Menu.RightPanel;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 * The GenerateNextLevel class handles the generation of the next level of the maze.
 * It includes functionality to generate a new maze, ensure the start and goal positions
 * are accessible, and update the game UI with the newly generated maze.
 *
 * @author André Eklund
 * @edit Viktor Näslund
 * @edit Linus Sigurd
 */

public class GenerateNextLevel {

    private MazeGenerator mazeGenerator;
    private BorderPane mainPane;
    private MainProgram mainProgram;
    private RightPanel rightPanel;
    private int dimension;
    private int themeInt;


    /**
     * Constructor that initializes the necessary components to generate a new maze.
     *
     * @param mainProgram  The main program object responsible for handling game logic.
     * @param mainPane     The main BorderPane layout of the game.
     * @param mazeGenerator The maze generator object that generates new mazes.
     * @param rightPanel   The right panel showing information such as lives, time, level, etc.
     * @param dimension    The size of the maze to be generated.
     */

    public GenerateNextLevel(MainProgram mainProgram, BorderPane mainPane, MazeGenerator mazeGenerator, RightPanel rightPanel, int dimension){
        this.dimension = dimension;
        this.mazeGenerator = mazeGenerator;
        this.mainProgram = mainProgram;
        this.mainPane = mainPane;
        this.rightPanel = rightPanel;
    }
    /**
     * Constructor that initializes the necessary components to generate a new maze, with an additional theme parameter.
     *
     * @param mainProgram  The main program object responsible for handling game logic.
     * @param mainPane     The main BorderPane layout of the game.
     * @param mazeGenerator The maze generator object that generates new mazes.
     * @param dimension    The size of the maze to be generated.
     * @param themeInt     The integer representing the current theme for the level.
     * @author Linus Sigurd
     */
    public GenerateNextLevel(MainProgram mainProgram, BorderPane mainPane, MazeGenerator mazeGenerator, int dimension, int themeInt){
        this.dimension = dimension;
        this.mazeGenerator = mazeGenerator;
        this.mainProgram = mainProgram;
        this.mainPane = mainPane;
        this.themeInt = themeInt;
    }

    /**
     * Generates a new maze, ensuring that the start and goal positions are accessible and updates the UI to display the new maze.
     *
     * @throws FileNotFoundException if there is an issue loading the maze files.
     */
    public void generateNewMaze() throws FileNotFoundException {
        int currentMaze[][] = mazeGenerator.getMaze();
        MazeGenerator newMazegenerator = new MazeGenerator(dimension, false);
        int nextMaze[][] = newMazegenerator.getMaze();
        int col = 0;

        for (int i = 0; i < currentMaze.length; i++) {
            for (int j = 0; j < currentMaze[i].length; j++) {
                if (currentMaze[i][j] == 3) {
                    nextMaze[i][j] = 2;
                }
                else if (currentMaze[i][j] == 2) {
                    col = j;
                }
            }
        }
        nextMaze[new Random().nextBoolean() ? 0 : nextMaze.length - 1][col] = 3;
        mainPane.setCenter(new MapTemplate(checkStartAndGoalNeighbors(nextMaze), mainProgram, this));
        mainPane.setCenter(new MapTemplateLE(checkStartAndGoalNeighbors(nextMaze), mainProgram, this, themeInt));
        this.mazeGenerator = newMazegenerator;
    }


    /**
     * Ensures that the start and goal positions in the maze are not enclosed by walls.
     * This method checks the neighbors of the start and goal positions and adjusts the maze to ensure accessibility.
     *
     * @param maze The 2D array representing the maze to be checked.
     * @return The modified maze with accessible start and goal positions.
     */
    public int[][] checkStartAndGoalNeighbors(int[][] maze) {

        int wallCounterStart = 0;
        int wallCounterGoal = 0;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {

                if (maze[i][j] == 2) {
                    for (int offsetRow = i - 1; offsetRow <= i + 1; offsetRow++){
                        for (int offsetCol = j - 1; offsetCol <= j + 1; offsetCol++){

                            if ((offsetRow >= 0) && (offsetRow < maze.length)) {

                                if ((offsetCol >= 0) && (offsetCol < maze[0].length)) {
                                    if (maze[offsetRow][offsetCol] == 0) {
                                        if((i == 0) && (j == 0)){
                                            if((offsetRow!=1 && offsetCol!=1) || (offsetRow!=1 && offsetCol != maze.length-1) ||(offsetRow!=maze.length-1 && offsetCol!=1) || (offsetRow!=maze.length-1 && offsetCol!=maze.length-1)){
                                                wallCounterStart++;
                                            }
                                        }
                                    }
                                    else if (wallCounterStart >= 2) {
                                        for (int offsetRow2 = offsetRow - 1; offsetRow2 <= offsetRow + 1; offsetRow2++){
                                            for (int offsetCol2 = offsetCol - 1; offsetCol2 <= offsetCol + 1; offsetCol2++){
                                                if ((offsetRow2 >= 0) && (offsetRow2 < maze.length)) {
                                                    if ((offsetCol2 >= 0) && (offsetCol2 < maze[0].length)) {
                                                        if (maze[offsetRow2][offsetCol2] == 0) {
                                                            maze[offsetRow2][offsetCol2] = 1;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else if (maze[i][j] == 3) {
                    for (int offsetRow = i - 1; offsetRow <= i + 1; offsetRow++){
                        for (int offsetCol = j - 1; offsetCol <= j + 1; offsetCol++){

                            if ((offsetRow >= 0) && (offsetRow < maze.length)) {
                                if ((offsetCol >= 0) && (offsetCol < maze[0].length)) {
                                    if (maze[offsetRow][offsetCol] == 0) {
                                        if((offsetRow!=1 && offsetCol!=1) || (offsetRow!=1 && offsetCol != maze.length-1) ||(offsetRow!=maze.length-1 && offsetCol!=1) || (offsetRow!=maze.length-1 && offsetCol!=maze.length-1)){
                                            wallCounterGoal++;
                                        }
                                    }
                                    else if (wallCounterGoal >= 2) {
                                        for (int offsetRow2 = offsetRow - 1; offsetRow2 <= offsetRow + 1; offsetRow2++){
                                            for (int offsetCol2 = offsetCol - 1; offsetCol2 <= offsetCol + 1; offsetCol2++){
                                                if ((offsetRow2 >= 0) && (offsetRow2 < maze.length)) {
                                                    if ((offsetCol2 >= 0) && (offsetCol2 < maze[0].length)) {
                                                        if (maze[offsetRow2][offsetCol2] == 0) {
                                                            maze[offsetRow2][offsetCol2] = 1;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return maze;
    }
}
