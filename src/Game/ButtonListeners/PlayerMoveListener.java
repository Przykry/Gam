package Game.ButtonListeners;

import Game.Entities.Player;

import java.awt.event.*;
import java.security.Key;

import static Game.Windows.GameWindow.getGround;

/**
 * Created by Daniel on 02.05.2017.
 */
public class PlayerMoveListener implements KeyListener{
    Player player1, player2;

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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playerStop(e,player1);
        playerStop(e,player2);
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
