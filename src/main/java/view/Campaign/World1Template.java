package view.Campaign;


import control.GameController;

import java.io.FileNotFoundException;


public class World1Template extends WorldTemplate {

    public World1Template(int[][] levelArray, GameController gameController) throws FileNotFoundException {
        super(levelArray, gameController);
    }

    public World1Template() {
        super();
    }
}


