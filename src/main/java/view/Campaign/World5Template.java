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

public class World5Template extends WorldTemplate {

    public World5Template(){
        super();
    }
    public World5Template(int[][] levelArray, CampaignController campaignController) throws FileNotFoundException {
        super(levelArray, campaignController, 90);
        initialize();
    }



    public void initialize() {
        setEnemy(new Enemy("file:files/mob_egypt.png", getSquareSize(), this, 6));
        ArrayList<ImageView> ghosts = enemy.getGhosts();
        switch (campaignController.getLevel()){
            case 1:

                add(ghosts.get(0), 9, 10);
                add(ghosts.get(1), 20, 4);

                enemy.setRectangle(300,150,65,-150,0);
                enemy.setRectangle(97,280,65,-150,1);

                enemy.setAnimation(0, 4, false);
                enemy.setAnimation(1, 4, false);
                break;

            case 2:
                add(ghosts.get(0), 6,3);
                add(ghosts.get(1), 20,5);

                enemy.setRectangle(270,100,50,-150,0);
                enemy.setRectangle(100,100,80,-150,1);

                enemy.setAnimation(0, 2, false);
                enemy.setAnimation(1, 2, false);
                break;

            case 3:
                add(ghosts.get(0), 13,3);
                add(ghosts.get(1), 12,10);

                enemy.setRectangle(132,100,50,-150,0);
                enemy.setRectangle(100,100,80,-150,1);

                enemy.setAnimation(0,1.5,false);
                enemy.setAnimation(1,1.5,false);
                break;
            case 4:

                add(ghosts.get(0), 12, 8);
                add(ghosts.get(1),14,0);

                enemy.setRectangle(132,200,80,-150,0);
                enemy.setRectangle(0, 135,80,-150,1);

                enemy.setAnimation(0, 2,false);
                enemy.setAnimation(1, 3,true);
                break;
            case 5:
                add(ghosts.get(0),7,6);
                add(ghosts.get(1),6,3);
                add(ghosts.get(2),16,9);
                add(ghosts.get(3),17,10);

                enemy.setRectangle(100,100,80,-150,0);
                enemy.setRectangle(100,0,80,-150,1);
                enemy.setRectangle(0,450,80,17,2);
                enemy.setRectangle(70,130,80,-150,3);

                enemy.setAnimation(0,2,false);
                enemy.setAnimation(1,2.5,true);
                enemy.setAnimation(2,2,true);
                enemy.setAnimation(3,3,false);

                break;


            default:
                break;
        }
    }
}
