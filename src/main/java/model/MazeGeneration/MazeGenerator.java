package model.MazeGeneration;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator {

    private Stack<Node> stack = new Stack<>();
    private Random rand = new Random();
    private int[][] maze;
    private int dimension;
    private boolean generateGoalAndStart;


    /**
     * @param dim Tar in en dimension för hur stor labyrinten ska vara
     * @param setGoalAndStart Sätter start & mål
     */
    public MazeGenerator(int dim, boolean setGoalAndStart) {
        this.generateGoalAndStart = setGoalAndStart;
        maze = new int[dim][dim];
        dimension = dim;
        generateMaze();
        createStartAndGoal();
        System.out.println("Raw maze usual: " + getRawMaze());
    }

    public MazeGenerator(int dim, boolean SetGoalAndStart, boolean editor) {
        this.generateGoalAndStart = SetGoalAndStart;
        maze = new int[dim][dim];
        dimension = dim;
        generateEmptyMaze();
        createSetStartAndGoal();
        System.out.println("Raw maze editor: " + getRawMaze());
    }

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

    public String getRawMaze() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : maze) {
            sb.append(Arrays.toString(row) + "\n");
        }
        return sb.toString();
    }

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

    private void randomlyAddNodesToStack(ArrayList<Node> nodes) {
        int targetIndex;
        while (!nodes.isEmpty()) {
            targetIndex = rand.nextInt(nodes.size());
            stack.push(nodes.remove(targetIndex));
        }
    }

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

    public void createStartAndGoal() {
        if (generateGoalAndStart) {
            maze[randomIndex()][0] = 2;
            maze[randomIndex()][maze.length - 1] = 3;
        }
    }

    public void createSetStartAndGoal() {
        if (generateGoalAndStart) {
            maze[0][0] = 2;
            maze[maze.length - 1][maze.length - 1] = 3;
        }
    }

    public void generateEmptyMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = 1;
            }
        }
    }

    public int randomIndex() {
        return new Random().nextBoolean() ? 0 : maze.length - 1;
    }

    public int[][] getMaze() {
        return maze;
    }

    private Boolean pointOnGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < dimension && y < dimension;
    }

    private Boolean pointNotCorner(Node node, int x, int y) {
        return (x == node.x || y == node.y);
    }

    private Boolean pointNotNode(Node node, int x, int y) {
        return !(x == node.x && y == node.y);
    }

  /*  public void saveMazeToFile(String fileName) {

        File dir = new File("createdLevels");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(getRawMaze());
            System.out.println("Maze saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public int[][] getRawMazeArray() {
        return maze;
    }

    public void setRawMazeArray(int[][] maze) {
        this.maze = maze;
    }

}
