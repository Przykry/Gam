package Game.ButtonListeners;

import Game.Entities.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Daniel on 02.05.2017.
 */
public class PlayerMoveListener extends KeyAdapter implements KeyListener{
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
        if(e.getKeyCode() == player2.getLeftKey()){
            System.out.println("pressed");
            player2.setMovingLeft(true);
        }
        else if(e.getKeyCode() == player2.getRightKey()){
            player2.setMovingRight(true);
        }
        else if(e.getKeyCode() == player2.getJumpKey()){
            player2.setJumping(true);
        }
        if(e.getKeyCode() == player1.getLeftKey()){
            player1.setMovingLeft(true);
        }
        else if(e.getKeyCode() == player1.getRightKey()){
            player1.setMovingRight(true);
        }
        else if(e.getKeyCode() == player1.getJumpKey()){
            player1.setJumping(true);
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
        else if(e.getKeyCode() == KeyEvent.VK_UP){
            player2.setJumping(false);
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            player1.setMovingLeft(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            player1.setMovingRight(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_W){
            player1.setJumping(false);
        }
    }
}
