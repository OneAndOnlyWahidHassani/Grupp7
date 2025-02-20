package view.Campaign;

import control.GameController;
import control.EnemyController;
import model.Enemy;

import java.io.FileNotFoundException;

/**
 * @author Filip Örnling
 */

public class World6Template extends WorldTemplate {
    public World6Template(){
        super();
    }
    public World6Template(int[][] levelArray, GameController gameController) throws FileNotFoundException {
        super(levelArray, gameController);
    }

    public void initialize(){
        EnemyController enemyController = getGameController().getEnemyController();
        Enemy enemy;
        switch (getGameController().getLevel()){
            case 1:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),120,93,72,-45);
                enemyController.setAnimation(enemy, 4, false);
                add(enemy.getIcon(),5,10);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),120,93,72,-45);
                enemyController.setAnimation(enemy, 4, false);
                add(enemy.getIcon(),4,5);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),120,93,72,-45);
                enemyController.setAnimation(enemy, 4, false);
                add(enemy.getIcon(),9,4);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),120,93,72,-45);
                enemyController.setAnimation(enemy, 4, false);
                add(enemy.getIcon(),10,13);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),120,93,72,-45);
                enemyController.setAnimation(enemy, 4, false);
                add(enemy.getIcon(),16,4);

                break;

            case 2:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),120,93,72,-45);
                enemyController.setAnimation(enemy, 5,2,2, true);
                add(enemy.getIcon(),5,10);


                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),120,93,72,-45);
                enemyController.setAnimation(enemy, 5,2,2, true);
                add(enemy.getIcon(),4,5);


                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),120,93,72,-45);
                enemyController.setAnimation(enemy, 5, 2, 2,true);
                add(enemy.getIcon(),9,4);


                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),120,93,72,-45);
                enemyController.setAnimation(enemy, 5,2,2, true);
                add(enemy.getIcon(),10,13);


                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),120,93,72,-45);
                enemyController.setAnimation(enemy, 5,2,2, true);
                add(enemy.getIcon(),16,4);
                break;

            case 3:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),525,0,72,-45, 525,0,105,-45 );
                enemyController.setAnimation(enemy, 5,2.5,4,6, true);
                add(enemy.getIcon(),3,0);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),525,0,72,-45, 525,0,105,-45 );
                enemyController.setAnimation(enemy, 5,2.5,4,6, true);
                add(enemy.getIcon(),3,2);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),525,0,72,-45, 525,0,105,-45 );
                enemyController.setAnimation(enemy, 5,2.5,4,6, true);
                add(enemy.getIcon(),3,4);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),525,0,72,-45, 525,0,105,-45 );
                enemyController.setAnimation(enemy, 5,2.5,4,6, true);
                add(enemy.getIcon(),3,6);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),525,0,72,-45, 525,0,105,-45 );
                enemyController.setAnimation(enemy, 5,2.5,4,6, true);
                add(enemy.getIcon(),3,8);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),525,0,72,-45, 525,0,105,-45 );
                enemyController.setAnimation(enemy, 5,2.5,4,6, true);
                add(enemy.getIcon(),3,10);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),525,0,72,-45, 525,0,105,-45 );
                enemyController.setAnimation(enemy, 5,2.5,4,6, true);
                add(enemy.getIcon(),3,12);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),525,0,72,-45, 525,0,105,-45 );
                enemyController.setAnimation(enemy, 5,2.5,4,6, true);
                add(enemy.getIcon(),3,14);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),525,0,72,-45, 525,0,105,-45 );
                enemyController.setAnimation(enemy, 5,2.5,4,6, true);
                add(enemy.getIcon(),3,16);
                break;

            case 4:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),132,200,80,-150);
                enemyController.setAnimation(enemy, 2, false);
                add(enemy.getIcon(),12,8);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),0,135,80,-150);
                enemyController.setAnimation(enemy, 3, false);
                add(enemy.getIcon(),14,0);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),400,0,20,-150);
                enemyController.setAnimation(enemy, 2.5, false);
                add(enemy.getIcon(),10,18);

                break;

            case 5:

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),100,100,80,-150);
                enemyController.setAnimation(enemy, 2, false);
                add(enemy.getIcon(),7,6);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),100,0,80,-150);
                enemyController.setAnimation(enemy, 2.5, true);
                add(enemy.getIcon(),6,3);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),0,450,80,17);
                enemyController.setAnimation(enemy, 2, true);
                add(enemy.getIcon(),16,0);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Space"),getSquareSize(),70,130,80,-150);
                enemyController.setAnimation(enemy, 3, false);
                add(enemy.getIcon(),17,10);
                break;
            default:
                break;
        }
    }
}
