package Game.ButtonListeners;

import Game.Entities.Player;
import Game.Windows.GameWindow;

import javax.swing.*;
import java.awt.event.*;

import static Game.Windows.GameWindow.getGround;

/**
 * Created by Daniel on 02.05.2017.
 */

/**
 *
 * Klasa implementuje ActionListenera i KeyListenera, które powodują ruch piłkarza
 */
public class PlayerMoveListener implements KeyListener,ActionListener{
    GameWindow gameWindow;
    private Player player1, player2;
    private boolean check = true;
    private static Timer moveTimer;



    public PlayerMoveListener(Player player1, Player player2, GameWindow gameWindow){
        this.gameWindow = gameWindow;
        this.player1 = player1;
        this.player2 = player2;
        moveTimer = new Timer(130,this);
        moveTimer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metoda powoduje ruch piłkarza w odpowiednią stronę (zależną od wciśniętego klawisza)
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(gameWindow.isGameEnd()){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                gameWindow.getBackButton().doClick();
            }
        }
        playerMove(e,player2);
        playerMove(e,player1);
        setPlayerImage(e,player1);
        setPlayerImage(e,player2);
    }

    /**
     * Metoda stopuje ruch piłkarza po puszczeniu przycisku.
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        playerStop(e,player1);
        playerStop(e,player2);
    }

    /**
     * Metoda ustawia jeden z dwóch obrazów piłkarza biegnącego w daną stronę (ładowanie tych obrazów na przemian symuluje animację biegu)
     * @param e
     * @param player
     */
    private void setPlayerImage(KeyEvent e, Player player){
        if(e.getKeyCode() == player.getLeftKey()){
            player.setPlayerHeadImage(0);
        }
        else if(e.getKeyCode() == player.getRightKey()){
            player.setPlayerHeadImage(1);
        }
    }

    /**
     * Metoda ustawia flagi odpowiedzialne za poruszanie się piłkarza i kopanie piłki na true.
     * @param e
     * @param player
     */
    private void playerMove(KeyEvent e,Player player){
        if(e.getKeyCode() == player.getLeftKey()){
            player.setMovingLeft(true);
        }
        else if(e.getKeyCode() == player.getRightKey()){
            player.setMovingRight(true);
        }
        else if(e.getKeyCode() == player.getJumpKey()){
            if(player.getY()+player.getHeightTorso()+2*player.getRadiusHead() == getGround()) player.setJumping(true);
        }
        else if(e.getKeyCode() == player.getShotKey()){
            player.setShooting(true);
        }
    }

    /**
     * Metoda ustawia flagi odpowiedzialne za poruszanie się piłkarza i kopanie piłki na false
     * @param e
     * @param player
     */
    private void playerStop(KeyEvent e,Player player){
        if(e.getKeyCode() == player.getLeftKey()){
            player.setMovingLeft(false);
        }
        else if(e.getKeyCode() == player.getRightKey()){
            player.setMovingRight(false);
        }
        else if(e.getKeyCode() == player.getShotKey()){
            player.setShooting(false);
        }
    }

    /**
     * Jeśli piłkarz porusza się w którąś stronę to ustawia naprzemiennie odpowiedni obraz tułowia.
     * @param player
     */
    private void torsoMove(Player player) {
        if(player.getMovingLeft()){
            if (check) {
                player.setPlayerTorsoImage(0);
                check = !check;
            } else {
                player.setPlayerTorsoImage(1);
                check = !check;
            }
        }
        else if(player.getMovingRight()) {
            if (check) {
                player.setPlayerTorsoImage(2);
                check = !check;
            } else {
                player.setPlayerTorsoImage(3);
                check = !check;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            torsoMove(player1);
            torsoMove(player2);
    }
}
