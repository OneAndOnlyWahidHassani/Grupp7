package control;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.util.Duration;
import model.Enemy;

/**
 * Controller class for managing enemy animations and creation.
 * Handles setting animations for enemies and creating new enemy instances.
 *
 * @author Elvira Grubb
 */
public class EnemyController {
    private GameController gameController;
    private PathTransition animation;
    /**
     * Default constructor.
     */
    public EnemyController(){}
    /**
     * Constructs an EnemyController with a reference to the GameController.
     *
     * @param gameController the game controller managing the game state
     */
    public EnemyController(GameController gameController){
        this.gameController = gameController;
    }
    /**
     * Sets and starts an animation for an enemy using a specified duration and reversal option.
     *
     * @param enemy    the enemy to animate
     * @param duration the duration of the animation in seconds
     * @param reverse  whether the animation should auto-reverse
     */
    public void setAnimation(Enemy enemy, double duration, boolean reverse) {
        animation = new PathTransition();
        animation.setNode(enemy.getIcon());
        animation.setDuration(Duration.seconds(duration));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setPath(enemy.getMovementPath1());
        animation.setAutoReverse(reverse);
        animation.play();
    }
    /**
     * Sets an animation for an enemy with an initial duration and a second duration upon completion.
     *
     * @param enemy         the enemy to animate
     * @param firstDuration the duration of the first animation cycle in seconds
     * @param secondDuration the duration of the second animation cycle in seconds
     * @param repetition    the number of times the first animation should repeat
     * @param reverse       whether the animation should auto-reverse
     */
    public void setAnimation(Enemy enemy, double firstDuration, double secondDuration, int repetition, boolean reverse){
        animation = new PathTransition();
        animation.setNode(enemy.getIcon());
        animation.setDuration(Duration.seconds(firstDuration));
        animation.setCycleCount(repetition);
        animation.setPath(enemy.getMovementPath1());
        animation.setAutoReverse(reverse);
        enemy.setAnimation(animation);
        enemy.getAnimation().play();
        enemy.getAnimation().setOnFinished(actionEvent -> {
            enemy.getAnimation().setDuration(Duration.seconds(secondDuration));
            enemy.getAnimation().setCycleCount(Animation.INDEFINITE);
            enemy.getAnimation().play();
        });
    }
    /**
     * Sets an animation for an enemy with two movement phases, each having a different duration and repetition count.
     *
     * @param enemy          the enemy to animate
     * @param firstDuration  the duration of the first animation phase in seconds
     * @param secondDuration the duration of the second animation phase in seconds
     * @param firstRepetition the number of times the first animation should repeat
     * @param secondRepetition the number of times the second animation should repeat
     * @param reverse        whether the animation should auto-reverse
     */
    public void setAnimation(Enemy enemy, double firstDuration, double secondDuration, int firstRepetition, int secondRepetition, boolean reverse){
        animation = new PathTransition();
        animation.setNode(enemy.getIcon());
        animation.setDuration(Duration.seconds(firstDuration));
        animation.setCycleCount(firstRepetition);
        animation.setPath(enemy.getMovementPath1());
        animation.setAutoReverse(reverse);
        enemy.setAnimation(animation);
        enemy.getAnimation().play();
        enemy.getAnimation().setOnFinished(actionEvent -> {
            enemy.getAnimation().setDuration(Duration.seconds(secondDuration));
            enemy.getAnimation().setCycleCount(secondRepetition);
            enemy.getAnimation().setPath(enemy.getMovementPath2());
            enemy.getAnimation().play();
            enemy.getAnimation().setOnFinished(actionEvent1 -> {
                enemy.getAnimation().setCycleCount(Animation.INDEFINITE);
                enemy.getAnimation().setPath(enemy.getMovementPath1());
                enemy.getAnimation().play();
            });
        });
    }
    /**
     * Creates an enemy instance with specified attributes.
     *
     * @param iconFilePath the file path of the enemy's icon
     * @param seize        the size of the enemy
     * @param var1         movement variable 1
     * @param var2         movement variable 2
     * @param y            initial y-coordinate
     * @param x            initial x-coordinate
     * @return a new Enemy instance
     */
    public Enemy createEnemy(String iconFilePath, int seize, double var1, double var2, int y, int x){
        Enemy enemy = new Enemy(this, iconFilePath, seize, var1, var2, y, x);
        return enemy;
    }
    /**
     * Creates an enemy instance with additional movement attributes.
     *
     * @param iconFilePath the file path of the enemy's icon
     * @param seize        the size of the enemy
     * @param var1         movement variable 1
     * @param var2         movement variable 2
     * @param y1           first y-coordinate
     * @param x1           first x-coordinate
     * @param var3         second movement variable
     * @param var4         fourth movement variable
     * @param y2           second y-coordinate
     * @param x2           second x-coordinate
     * @return a new Enemy instance
     */
    public Enemy createEnemy(String iconFilePath, int seize, double var1, double var2, int y1, int x1,double var3, double var4, int y2, int x2){
        Enemy enemy = new Enemy(this, iconFilePath, seize, var1, var2, y1, x1, var3, var4, y2, x2);
        return enemy;
    }

    /**
     * Retrieves the file path of an enemy's icon based on its type.
     *
     * @param icon the type of enemy
     * @return the corresponding file path of the enemy's icon
     */
    public String getIconFilePath(String icon){
        switch (icon){
            case "Ghost":
                return "file:files/ghost.png";
            case "God":
                return "file:files/god_mob2.png";
            case "Egypt":
                return "file:files/mob_egypt.png";
            case "Space":
                return "file:files/space_mob3.png";
            default:
                return "";
        }
    }
    /**
     * Gets the GameController instance associated with this controller.
     *
     * @return the GameController instance
     */
    public GameController getGameController() {
        return gameController;
    }
}
