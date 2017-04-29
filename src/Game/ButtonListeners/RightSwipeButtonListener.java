package Game.ButtonListeners;

import Game.ChoosePlayerWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel on 26.04.2017.
 */
public class RightSwipeButtonListener implements ActionListener{
    ChoosePlayerWindow panel;
    int player;
    public RightSwipeButtonListener(ChoosePlayerWindow panel, int player){
        this.panel = panel;
        this.player = player;
    }

    private void setPlayerBody(int player, int imageNumber){
        if(player == 1){
            panel.setResizedHead(panel.resizePlayerHead(panel.getPlayer1(),imageNumber),player-1);
            panel.setResizedTorso(panel.resizePlayerTorso(panel.getPlayer1(),imageNumber),player-1);
        }
        else{
            panel.setResizedHead(panel.resizePlayerHead(panel.getPlayer2(),imageNumber),player-1);
            panel.setResizedTorso(panel.resizePlayerTorso(panel.getPlayer2(),imageNumber),player-1);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(player == 1) {
            int n = panel.getFirstPlayerChoose();
            if (n + 1 == panel.playerModels.length) {
                panel.setFirstPlayerChoose(0);
                setPlayerBody(player,2);
            } else {
                panel.setFirstPlayerChoose(panel.getFirstPlayerChoose() + 1);
                setPlayerBody(player,0);
            }
            panel.setPlayer1(panel.playerModels[panel.getFirstPlayerChoose()]);
        }else{
            int n = panel.getSecondPlayerChoose();
            if (n + 1 == panel.playerModels.length) {
                panel.setSecondPlayerChoose(0);
                setPlayerBody(player,0);
            } else {
                panel.setSecondPlayerChoose(panel.getSecondPlayerChoose() + 1);
                setPlayerBody(player,0);
            }
            panel.setPlayer2(panel.playerModels[panel.getSecondPlayerChoose()]);
        }
        panel.repaint();
    }
}
