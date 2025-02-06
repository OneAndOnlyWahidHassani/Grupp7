package view.Campaign;


import control.CampaignController;
import view.AudioPlayer;
import view.Menu.RightPanel;

import java.io.FileNotFoundException;


public class World1Template extends WorldTemplate {

    public World1Template(int[][] levelArray, CampaignController campaignController) throws FileNotFoundException {
        super(levelArray, campaignController, campaignController.getRightPanel(), campaignController.getAudioPlayer(), 25);
    }

    public World1Template() {
        super();
    }
}


