package Game.ButtonListeners;

import Game.Entities.Player;
import Game.Windows.GameWindow;

import java.awt.event.*;
import java.security.Key;

import static Game.Windows.GameWindow.getGround;

/**
 * Created by Daniel on 02.05.2017.
 */
public class PlayerMoveListener implements KeyListener{
    private Player player1, player2;
    private boolean cheack = true;

    public PlayerMoveListener(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        playerMove(e,player2);
        playerMove(e,player1);
        setPlayerImage(e,player1);
        setPlayerImage(e,player2);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playerStop(e,player1);
        playerStop(e,player2);
    }


    private void setPlayerImage(KeyEvent e, Player player){
        if(e.getKeyCode() == player.getLeftKey()){
            player.setPlayerHeadImage(0);
            if(cheack) {
                player.setPlayerTorsoImage(0);
                cheack = !cheack;
            }
            else{
                player.setPlayerTorsoImage(1);
                cheack = !cheack;
            }
        }
        else if(e.getKeyCode() == player.getRightKey()){
            player.setPlayerHeadImage(1);
            if(cheack) {
                player.setPlayerTorsoImage(2);
                cheack = !cheack;
            }
            else{
                player.setPlayerTorsoImage(3);
                cheack = !cheack;
            }
        }
    }

    private void playerMove(KeyEvent e,Player player){
        if(e.getKeyCode() == player.getLeftKey()){
            player.setMovingLeft(true);
        }
        else if(e.getKeyCode() == player.getRightKey()){
            player.setMovingRight(true);
        }
        else if(e.getKeyCode() == player.getJumpKey()){
            if(player.getY() == getGround()) player.setJumping(true);
        }
    }

    private void playerStop(KeyEvent e,Player player){
        System.out.println(player.getLeftKey());
        if(e.getKeyCode() == player.getLeftKey()){
            player.setMovingLeft(false);
        }
        else if(e.getKeyCode() == player.getRightKey()){
            player.setMovingRight(false);
        }
    }
}
