package Game;

import Game.Entities.Player;
import Game.Windows.GameWindow;
import Game.Windows.MainWindow;
import Game.Windows.WindowInt;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Daniel on 24.04.2017.
 */
public class Input extends KeyAdapter implements KeyListener,MouseListener {
    JFrame window;
    Player p1, p2;



    public Input(JFrame window, Player p1, Player p2){
        this.window = window;
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private final Set<Integer> pressed = new HashSet<Integer>();

    @Override
    public synchronized void keyPressed(KeyEvent e) {
       pressed.add(e.getKeyCode());
      /*  if (pressed.size() > 1) {
            if(pressed.contains(KeyEvent.VK_LEFT) && pressed.contains(KeyEvent.VK_UP)){
                window.getPlayer(1).setMovingLeft(true);
                p1.setJumping(true);
            }

            else if(pressed.contains(KeyEvent.VK_RIGHT) && pressed.contains(KeyEvent.VK_UP)){
                p1.setMovingRight(true);
                p1.setJumping(true);
            }
        }
        else{
            if (pressed.contains(KeyEvent.VK_RIGHT)) {
                window.getPlayer(1).setMovingLeft(true);
            }
            if (pressed.contains(KeyEvent.VK_LEFT)) {
                window.getPlayer(1).setMovingLeft(true);
            }
            if (pressed.contains(KeyEvent.VK_UP)) {
                window.getPlayer(1).setMovingLeft(true);
            }

        }*/
        System.out.println(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) p1.setMovingRight(false);
        if(e.getKeyCode() == KeyEvent.VK_LEFT) p1.setMovingLeft(false);
        if(e.getKeyCode() == KeyEvent.VK_UP) p1.setJumping(false);
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