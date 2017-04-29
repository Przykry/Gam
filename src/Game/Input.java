package Game;

import Game.Windows.MainWindow;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Daniel on 24.04.2017.
 */
public class Input extends KeyAdapter implements KeyListener,MouseListener {
    MainWindow window;

    public Input(MainWindow window){
        this.window = window;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private final Set<Integer> pressed = new HashSet<Integer>();

    @Override
    public synchronized void keyPressed(KeyEvent e) {
       /* pressed.add(e.getKeyCode());
        if (pressed.size() > 1) {
            if(pressed.contains(KeyEvent.VK_LEFT) && pressed.contains(KeyEvent.VK_UP)){
                window.getP1().setPlayerPositionX(0);
                window.getP1().setPlayerPositionY(1);
            }
            else if(pressed.contains(KeyEvent.VK_LEFT) && pressed.contains(KeyEvent.VK_DOWN)){
                window.getP1().setPlayerPositionX(0);
                window.getP1().setPlayerPositionY(0);
            }
            else if(pressed.contains(KeyEvent.VK_RIGHT) && pressed.contains(KeyEvent.VK_UP)){
                window.getP1().setPlayerPositionX(1);
                window.getP1().setPlayerPositionY(1);
            }
            else if(pressed.contains(KeyEvent.VK_RIGHT) && pressed.contains(KeyEvent.VK_DOWN)){
                window.getP1().setPlayerPositionX(1);
                window.getP1().setPlayerPositionY(0);
            }
        }
        else{
            if (pressed.contains(KeyEvent.VK_RIGHT)) {
                window.getP1().setPlayerPositionX(1);
            }
            if (pressed.contains(KeyEvent.VK_LEFT)) {
                window.getP1().setPlayerPositionX(0);
            }
            if (pressed.contains(KeyEvent.VK_UP)) {
                window.getP1().setPlayerPositionY(1);
            }

            if (pressed.contains(KeyEvent.VK_DOWN)){
                window.getP1().setPlayerPositionY(0);
            }
        }
        window.repaint();*/
    }

    @Override
    public void keyReleased(KeyEvent e) {
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