package Game.ButtonListeners;

import Game.Windows.ChoosePlayerWindow;

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
            } else {
                panel.setFirstPlayerChoose(panel.getFirstPlayerChoose() - 1);
            }
            panel.setPlayer1(panel.playerModels[panel.getFirstPlayerChoose()]);
            panel.setResizedHead(panel.getPlayer1(),player-1,2);
            panel.setResizedTorso(panel.getPlayer1(),player-1,3);
        }else{
            int n = panel.getSecondPlayerChoose();
            if (n == 0) {
                panel.setSecondPlayerChoose(panel.playerModels.length - 1);
            } else {
                panel.setSecondPlayerChoose(panel.getSecondPlayerChoose() - 1);
            }
            panel.setPlayer2(panel.playerModels[panel.getSecondPlayerChoose()]);
            panel.setResizedHead(panel.getPlayer2(),player-1,0);
            panel.setResizedTorso(panel.getPlayer2(),player-1,0);
        }
        panel.repaint();
    }
}