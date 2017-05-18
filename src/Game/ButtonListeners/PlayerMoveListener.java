package Game.ButtonListeners;

import Game.Entities.Player;

import javax.swing.*;
import java.awt.event.*;

import static Game.Windows.GameWindow.getGround;

/**
 * Created by Daniel on 02.05.2017.
 */
public class PlayerMoveListener implements KeyListener,ActionListener{
    private Player player1, player2;
    private boolean check = true;
    private static Timer moveTimer;



    public PlayerMoveListener(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        moveTimer = new Timer(130,this);
        moveTimer.start();
    }

    static void stopTimer(){
        moveTimer.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        playerMove(e,player2, player1);
        playerMove(e,player1, player2);
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
        }
        else if(e.getKeyCode() == player.getRightKey()){
            player.setPlayerHeadImage(1);
        }
    }

    private void playerMove(KeyEvent e,Player player1, Player player2){
        if(e.getKeyCode() == player1.getLeftKey()){
            player1.setMovingLeft(true);
        }
        else if(e.getKeyCode() == player1.getRightKey()){
            player1.setMovingRight(true);
        }
        else if(e.getKeyCode() == player1.getJumpKey()){
            if(player1.getY()+player1.getHeightTorso()+2*player1.getRadiusHead() == getGround()) player1.setJumping(true);
        }
        else if(e.getKeyCode() == player1.getShotKey()){
            //System.out.println("shooting");
            //player.setShooting(true);
        }
    }

    private void playerStop(KeyEvent e,Player player){
        if(e.getKeyCode() == player.getLeftKey()){
            player.setMovingLeft(false);
        }
        else if(e.getKeyCode() == player.getRightKey()){
            player.setMovingRight(false);
        }
        else if(e.getKeyCode() == player.getShotKey()){
            //player.setShooting(false);
        }
    }

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
