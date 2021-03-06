package Game.ButtonListeners;

import Game.Windows.ChoosePlayerWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel on 26.04.2017.
 *
 */

/**
 *
 * Klasa implementuje Action Listenera który jest przypisany do przycisku przesunięcia w prawo podczas wyboru zawodnika.
 */
public class RightSwipeButtonListener implements ActionListener{
    private ChoosePlayerWindow panel;
    private int player;
    public RightSwipeButtonListener(ChoosePlayerWindow panel, int player){
        this.panel = panel;
        this.player = player;
    }

    /**
     * Metoda działa w zależności od parametru player - przesuwa w prawo wybór gracza nr 1 bądź nr 2
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(player == 1) {
            int n = panel.getFirstPlayerChoose();
            if (n + 1 == panel.player1Models.length) {
                panel.setFirstPlayerChoose(0);
            } else {
                panel.setFirstPlayerChoose(panel.getFirstPlayerChoose() + 1);
            }
            panel.setPlayer1(panel.player1Models[panel.getFirstPlayerChoose()]);
            panel.setResizedHead(panel.getPlayer1(),player-1,1);
            panel.setResizedTorso(panel.getPlayer1(),player-1,3);
        }else{
            int n = panel.getSecondPlayerChoose();
            if (n + 1 == panel.player2Models.length) {
                panel.setSecondPlayerChoose(0);
            } else {
                panel.setSecondPlayerChoose(panel.getSecondPlayerChoose() + 1);
            }
            panel.setPlayer2(panel.player2Models[panel.getSecondPlayerChoose()]);
            panel.setResizedHead(panel.getPlayer2(),player-1,0);
            panel.setResizedTorso(panel.getPlayer2(),player-1,0);
        }
        panel.repaint();
    }
}
