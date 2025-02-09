package view.Campaign;

import control.CampaignController;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.AudioPlayer;
import view.Menu.RightPanel;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author Filip Örnling
 */

public class World6Template extends WorldTemplate {
    public World6Template(){
        super();
    }
    public World6Template(int[][] levelArray, CampaignController campaignController) throws FileNotFoundException {
        super(levelArray, campaignController, 99);
        initialize();

    }

    public void initialize(){
        setEnemy(new Enemy("file:files/space_mob3.png", getSquareSize(), this, 10));
        ArrayList<ImageView> ghosts = enemy.getGhosts();
        switch (campaignController.getLevel()){
            case 1:
                add(ghosts.get(0),5,10);
                add(ghosts.get(1),4,5);
                add(ghosts.get(2),9,4);
                add(ghosts.get(3),10,13);
                add(ghosts.get(4),16,4);

                enemy.setRectangle(120,93,72,-45,0);
                enemy.setRectangle(120,93,72,-45,1);
                enemy.setRectangle(120,93,72,-45,2);
                enemy.setRectangle(120,93,72,-45,3);
                enemy.setRectangle(120,93,72,-45,4);

                enemy.setAnimation(0,4,false);
                enemy.setAnimation(1,4,false);
                enemy.setAnimation(2,4,false);
                enemy.setAnimation(3,4,false);
                enemy.setAnimation(4,4,false);
                break;

            case 2:
                add(ghosts.get(0),5,10);
                add(ghosts.get(1),4,5);
                add(ghosts.get(2),9,4);
                add(ghosts.get(3),10,13);
                add(ghosts.get(4),16,4);

                enemy.setRectangle(120,93,72,-45,0);
                enemy.setRectangle(120,93,72,-45,1);
                enemy.setRectangle(120,93,72,-45,2);
                enemy.setRectangle(120,93,72,-45,3);
                enemy.setRectangle(120,93,72,-45,4);

                enemy.setAnimation(0,5, true,2);
                enemy.setAnimation(1,5, true,2);
                enemy.setAnimation(2,5, true,2);
                enemy.setAnimation(3,5, true,2);
                enemy.setAnimation(4,5, true,2);
                break;

            case 3:
                add(ghosts.get(0),3,0);
                add(ghosts.get(1),3,2);
                add(ghosts.get(2),3,4);
                add(ghosts.get(3),3,6);
                add(ghosts.get(4),3,8);
                add(ghosts.get(5),3,10);
                add(ghosts.get(6),3,12);
                add(ghosts.get(7),3,14);
                add(ghosts.get(8),3,16);

                enemy.setRectangle(525,0,72,-45, 0);
                enemy.setRectangle(525,0,105,-45,1);

                enemy.setAnimation(0,5,true,3,2);
                enemy.setAnimation(1,5,true,3,2);
                enemy.setAnimation(2,5,true,3,2);
                enemy.setAnimation(3,5,true,3,2);
                enemy.setAnimation(4,5,true,3,2);
                enemy.setAnimation(5,5,true,3,2);
                enemy.setAnimation(6,5,true,3,2);
                enemy.setAnimation(7,5,true,3,2);
                enemy.setAnimation(8,5,true,3,2);
                break;

            case 4:
                add(ghosts.get(0),12, 8);
                add(ghosts.get(1),14, 0);
                add(ghosts.get(2),10,18);


                enemy.setRectangle(132,200,80,-150,0);
                enemy.setRectangle(0,135,80,-150, 1);
                enemy.setRectangle(400,0,20,-150, 2);

                enemy.setAnimation(0, 2, false);
                enemy.setAnimation(1, 3, false);
                enemy.setAnimation(2, 2.5, false);
                break;

            case 5:
                add(ghosts.get(0), 7, 6);
                add(ghosts.get(1), 6, 3);
                add(ghosts.get(2), 16, 0);
                add(ghosts.get(3), 17, 10);

                enemy.setRectangle(100,100,80,-150,0);
                enemy.setRectangle(100,0,80,-150,1);
                enemy.setRectangle(0,450,80,17,2);
                enemy.setRectangle(70,130,80,-150,3);

                enemy.setAnimation(0,2,false);
                enemy.setAnimation(1,2.5,true);
                enemy.setAnimation(2,2,true);
                enemy.setAnimation(3,3,false);
        }
    }
}
