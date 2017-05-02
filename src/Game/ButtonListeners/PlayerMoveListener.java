package Game.ButtonListeners;

import Game.Entities.Player;

import java.awt.event.*;

/**
 * Created by Daniel on 02.05.2017.
 */
public class PlayerMoveListener implements KeyListener, MouseListener{
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
            player2.setMovingLeft(true);
            System.out.println();
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
        System.out.println(e.getKeyCode());
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

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
