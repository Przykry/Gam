package Game.ButtonListeners;

import Game.ChoosePlayerWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel on 26.04.2017.
 */
public class LeftSwipeButtonListener implements ActionListener{
    ChoosePlayerWindow panel;
    int player;
    public LeftSwipeButtonListener(ChoosePlayerWindow panel, int player){
        this.panel = panel;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(player == 1) {
            int n = panel.getFirstPlayerChoose();
            if (n == 0) {
                panel.setFirstPlayerChoose(panel.playerModels.length - 1);
                panel.setResizedHead(panel.resizePlayerHead(panel.getPlayer1(),2),0);
                panel.setResizedTorso(panel.resizePlayerTorso(panel.getPlayer1(),2),0);
            }
            else {
                panel.setFirstPlayerChoose(panel.getFirstPlayerChoose() - 1);
                panel.setResizedHead(panel.resizePlayerHead(panel.getPlayer1(),2),0);
                panel.setResizedTorso(panel.resizePlayerTorso(panel.getPlayer1(),2),0);
            }
            panel.setPlayer1(panel.playerModels[panel.getFirstPlayerChoose()]);
        }
        else{
            int n = panel.getSecondPlayerChoose();
            if (n == 0) {
                panel.setSecondPlayerChoose(panel.playerModels.length - 1);
                panel.setResizedHead(panel.resizePlayerHead(panel.getPlayer2(),2),1);
                panel.setResizedTorso(panel.resizePlayerTorso(panel.getPlayer2(),2),1);
            }
            else {
                panel.setSecondPlayerChoose(panel.getSecondPlayerChoose() - 1);
                panel.setResizedHead(panel.resizePlayerHead(panel.getPlayer2(),2),1);
                panel.setResizedTorso(panel.resizePlayerTorso(panel.getPlayer2(),2),1);
            }
            panel.setPlayer2(panel.playerModels[panel.getSecondPlayerChoose()]);
        }
        panel.repaint();
    }
}
