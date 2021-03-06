package Game.ButtonListeners.ButtonListeners.Menu;

import Game.Windows.MenuWindow;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Przykry on 09.05.2017.
 */

/**
 * klasa sluzy do zczytywania wartosci klawiatury
 */

public class MenuKeyListener implements KeyListener {
    private static int keys[] = MenuWindow.getKeys();
    private MenuWindow window;
    private static boolean canChange[] = new boolean[10];

    static void setCanChange(int i) {
        MenuKeyListener.canChange[i] = true;
    }


    public MenuKeyListener(JPanel window){
        this.window = (MenuWindow)window;
    }


    /**
     * pobiera wcisniety klawisz i ustawia jego wartosc oraz odswieza ekran
     * @param e
     */
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
