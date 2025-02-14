package view.Campaign;

import control.GameController;
import control.EnemyController;
import model.Enemy;

import java.io.FileNotFoundException;

/**
 * @author Filip Örnling
 */

public class World5Template extends WorldTemplate {

    public World5Template(){
        super();
    }
    public World5Template(int[][] levelArray, GameController gameController) throws FileNotFoundException {
        super(levelArray, gameController);
        initialize();
    }

    public void initialize() {
        EnemyController enemyController = getGameController().getEnemyController();
        Enemy enemy;
        switch (getGameController().getLevel()){
            case 1:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Egypt"),getSquareSize(),300,150,65,-150);
                enemyController.setAnimation(enemy, 4, false);
                add(enemy.getIcon(),9,10);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Egypt"),getSquareSize(),97,280,65,-150);
                enemyController.setAnimation(enemy, 4, false);
                add(enemy.getIcon(),20,4);
                break;

            case 2:

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Egypt"),getSquareSize(),270,100,50,-150);
                enemyController.setAnimation(enemy, 2, false);
                add(enemy.getIcon(),6,3);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Egypt"),getSquareSize(),100,100,80,-150);
                enemyController.setAnimation(enemy, 2, false);
                add(enemy.getIcon(),20,5);
                break;

            case 3:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Egypt"),getSquareSize(),132,100,50,-150);
                enemyController.setAnimation(enemy, 1.5, false);
                add(enemy.getIcon(),13,3);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Egypt"),getSquareSize(),100,100,80,-150);
                enemyController.setAnimation(enemy, 1.5, false);
                add(enemy.getIcon(),12,10);
                break;

            case 4:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Egypt"),getSquareSize(),132,200,80,-150);
                enemyController.setAnimation(enemy, 2, false);
                add(enemy.getIcon(),12,8);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Egypt"),getSquareSize(),0,135,80,-150);
                enemyController.setAnimation(enemy, 3, true);
                add(enemy.getIcon(),14,0);
                break;

            case 5:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Egypt"),getSquareSize(),100,100,80,-150);
                enemyController.setAnimation(enemy, 2, false);
                add(enemy.getIcon(),7,6);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Egypt"),getSquareSize(),100,0,80,-150);
                enemyController.setAnimation(enemy, 2.5, true);
                add(enemy.getIcon(),6,3);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Egypt"),getSquareSize(),0,450,80,17);
                enemyController.setAnimation(enemy, 2, true);
                add(enemy.getIcon(),16,9);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Egypt"),getSquareSize(),70,130,80,-150);
                enemyController.setAnimation(enemy, 3, false);
                add(enemy.getIcon(),17,10);

                break;

            default:
                break;
        }
    }
}
