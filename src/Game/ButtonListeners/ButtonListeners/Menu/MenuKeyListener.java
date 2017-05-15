package Game.ButtonListeners.ButtonListeners.Menu;

import Game.Windows.MenuWindow;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Przykry on 09.05.2017.
 */
public class MenuKeyListener implements KeyListener {
    private static int keys[] = MenuWindow.getKeys();
    MenuWindow window;
    static boolean canChange[] = new boolean[10];

    public static void setCanChange(int i) {
        MenuKeyListener.canChange[i] = true;
    }


    public MenuKeyListener(JPanel window){
        this.window = (MenuWindow)window;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        for(int i = 0; i<8 ;i++) {
            if (canChange[i]) {
                keys[i] = e.getKeyCode();
                //GameWindow.setPlayerKeys();
                canChange[i] = false;
                SwingWorker<Void, Void> worker = new MenuSwingWorker(window,i);
                worker.execute();
                window.repaint();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
