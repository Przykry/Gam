package Game.ButtonListeners;

import Game.Entities.Player;

import java.awt.event.*;

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

    private void playerMove(KeyEvent e,Player player){
        if(e.getKeyCode() == player.getLeftKey()){
            player.setMovingLeft(true);
        }
        else if(e.getKeyCode() == player.getRightKey()){
            player.setMovingRight(true);
        }
        else if(e.getKeyCode() == player.getJumpKey()){
            if(player.getY() == player.getGround()) player.setJumping(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player2.setMovingLeft(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player2.setMovingRight(false);
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            player1.setMovingLeft(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            player1.setMovingRight(false);
        }
    }
}
