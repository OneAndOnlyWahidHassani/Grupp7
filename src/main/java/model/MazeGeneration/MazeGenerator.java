package model.MazeGeneration;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * The MazeGenerator class is responsible for generating a maze.
 * It provides methods to generate a maze, set start and goal points, and retrieve the maze in various formats.
 * The class can generate a new maze, use an existing maze to create new paths, and support maze editing
 * @author Wahid Hassani
 * @author Alanah Öster berg Coleman
 * @author Linus Sigurd
 */

public class MazeGenerator {

    private Stack<Node> stack = new Stack<>();
    private Random rand = new Random();
    private int[][] maze;
    private int dimension;
    private boolean generateGoalAndStart;


    /**
     * Constructor to generate a maze of specified dimension and optionally set the start and goal positions.
     *
     * @param dim The dimension of the maze (width and height).
     * @param setGoalAndStart Flag to indicate whether to generate start and goal in the maze.
     */
    public MazeGenerator(int dim, boolean setGoalAndStart) {
        this.generateGoalAndStart = setGoalAndStart;
        maze = new int[dim][dim];
        dimension = dim;
        generateMaze();
        createStartAndGoal();
        System.out.println("Raw maze usual: " + getRawMaze());
    }
    /**
     * Constructor for editor mode with specified dimension, start/goal settings, and editor flag.
     *
     * @param dim The dimension of the maze (width and height).
     * @param SetGoalAndStart Flag to indicate whether to generate start and goal in the maze.
     * @param editor Flag indicating if the maze is for an editor mode.
     * @author Linus Sigurd
     */
    public MazeGenerator(int dim, boolean SetGoalAndStart, boolean editor) {
        this.generateGoalAndStart = SetGoalAndStart;
        maze = new int[dim][dim];
        dimension = dim;
        generateEmptyMaze();
        createSetStartAndGoal();
        System.out.println("Raw maze editor: " + getRawMaze());
    }
    /**
     * Constructor that initializes a maze from an existing maze array and optionally sets start and goal positions.
     *
     * @param existingMaze The existing maze array to be used.
     * @param setGoalAndStart Flag to indicate whether to generate start and goal in the maze.
     * @param editor Flag indicating if the maze is for an editor mode.
     * @author Wahid Hassani
     */
    public MazeGenerator(int [][] existingMaze , boolean setGoalAndStart, boolean editor) {
        this.maze = existingMaze;
        dimension = existingMaze.length;
        this.generateGoalAndStart = setGoalAndStart;
        if(setGoalAndStart) {
            createSetStartAndGoal();
        }
        generateMazeFromExisting();
        System.out.println("Raw maze from existing: " + getRawMaze());
    }

    /**
     * Generates a maze using depth-first search algorithm.
     */
    public void generateMaze() {
        stack.push(new Node(0, 0));
        while (!stack.empty()) {
            Node next = stack.pop();
            if (validNextNode(next)) {
                maze[next.y][next.x] = 1;
                ArrayList<Node> neighbors = findNeighbors(next);
                randomlyAddNodesToStack(neighbors);
            }
        }
    }

    /**
     * Generates a maze using the depth-first search algorithm based on an existing maze structure.
     * @author Wahid Hassani
     */
    public void generateMazeFromExisting() {
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (maze[y][x] == 1) {
                    stack.push(new Node(x, y));
                }
            }
        }
        while (!stack.empty()) {
            Node next = stack.pop();
            if (validNextNode(next)) {
                maze[next.y][next.x] = 1;
                ArrayList<Node> neighbors = findNeighbors(next);
                randomlyAddNodesToStack(neighbors);
            }
        }
        System.out.println("Generated maze from existing" + Arrays.deepToString(maze));
    }
    /**
     * Returns the raw maze in string format with 1s and 0s representing walls and paths.
     *
     * @return A string representing the maze layout.
     */
    public String getRawMaze() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : maze) {
            sb.append(Arrays.toString(row) + "\n");
        }
        return sb.toString();
    }
    /**
     * Returns a symbolic version of the maze using '*' for paths and spaces for walls.
     *
     * @return A string representing the maze with symbols.
     */
    public String getSymbolicMaze() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                sb.append(maze[i][j] == 1 ? "*" : " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    /**
     * Checks if a given node is valid to visit based on neighboring nodes.
     *
     * @param node The node to check for validity.
     * @return True if the node is valid to visit, false otherwise.
     */
    private boolean validNextNode(Node node) {
        int numNeighboringOnes = 0;
        for (int y = node.y - 1; y < node.y + 2; y++) {
            for (int x = node.x - 1; x < node.x + 2; x++) {
                if (pointOnGrid(x, y) && pointNotNode(node, x, y) && maze[y][x] == 1) {
                    numNeighboringOnes++;
                }
            }
        }
        return (numNeighboringOnes < 3) && maze[node.y][node.x] != 1;
    }
    /**
     * Randomly adds nodes to the stack for further maze generation.
     *
     * @param nodes The list of nodes to be added.
     */
    private void randomlyAddNodesToStack(ArrayList<Node> nodes) {
        int targetIndex;
        while (!nodes.isEmpty()) {
            targetIndex = rand.nextInt(nodes.size());
            stack.push(nodes.remove(targetIndex));
        }
    }
    /**
     * Finds the valid neighboring nodes of a given node for maze generation.
     *
     * @param node The node whose neighbors are to be found.
     * @return A list of neighboring nodes.
     */
    private ArrayList<Node> findNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int y = node.y - 1; y < node.y + 2; y++) {
            for (int x = node.x - 1; x < node.x + 2; x++) {
                if (pointOnGrid(x, y) && pointNotCorner(node, x, y)
                        && pointNotNode(node, x, y)) {
                    neighbors.add(new Node(x, y));
                }
            }
        }
        return neighbors;
    }
    /**
     * Creates a start and goal point in the maze if enabled by the flag.
     */
    public void createStartAndGoal() {
        if (generateGoalAndStart) {
            maze[randomIndex()][0] = 2;
            maze[randomIndex()][maze.length - 1] = 3;
        }
    }
    /**
     * Creates a fixed start and goal point in the maze for editor mode.
     * @author Linus Sigurd
     */
    public void createSetStartAndGoal() {
        if (generateGoalAndStart) {
            maze[0][0] = 2;
            maze[maze.length - 1][maze.length - 1] = 3;
        }
    }
    /**
     * Generates an empty maze (all walls) by setting all cells to 1.
     * @author Linus Sigurd
     */
    public void generateEmptyMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = 1;
            }
        }
    }
    /**
     * Returns a random index value (either 0 or the size of the maze - 1).
     *
     * @return A random index (either 0 or size - 1).
     */
    public int randomIndex() {
        return new Random().nextBoolean() ? 0 : maze.length - 1;
    }
    /**
     * Returns the current maze as a 2D array.
     *
     * @return The 2D array representing the maze.
     */
    public int[][] getMaze() {
        return maze;
    }
    /**
     * Checks if a point (x, y) is within the grid boundaries.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return True if the point is within the grid boundaries, false otherwise.
     */
    private Boolean pointOnGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < dimension && y < dimension;
    }
    /**
     * Checks if a point (x, y) is not a corner relative to the given node.
     *
     * @param node The reference node.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return True if the point is not a corner relative to the node, false otherwise.
     */
    private Boolean pointNotCorner(Node node, int x, int y) {
        return (x == node.x || y == node.y);
    }
    /**
     * Checks if a point (x, y) is not the same as the given node.
     *
     * @param node The reference node.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return True if the point is not the same as the node, false otherwise.
     */
    private Boolean pointNotNode(Node node, int x, int y) {
        return !(x == node.x && y == node.y);
    }


    /**
     * Returns the current maze as a raw 2D array.
     *
     * @return The 2D array representing the maze.
     * @author Alanah Öster berg Coleman
     */
    public int[][] getRawMazeArray() {
        return maze;
    }

    /**
     * Sets the current maze to the provided 2D array.
     *
     * @param maze The 2D array representing the new maze.
     * @author Wahid Hassani
     */
    public void setRawMazeArray(int[][] maze) {
        this.maze = maze;
    }

}
