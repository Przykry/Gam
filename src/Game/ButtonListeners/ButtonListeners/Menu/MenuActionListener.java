package Game.ButtonListeners.ButtonListeners.Menu;

import Game.Windows.MenuWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Przykry on 09.05.2017.
 */


public class MenuActionListener implements ActionListener {

    private MenuWindow window;
    private int i;


    /**
     * konstruktor menuActionListenera
     * @param window parametr okna
     * @param i parametr przycisku ktory zostal wcisniety
     */
    public MenuActionListener(MenuWindow window, int i) {
        this.window = window;
        this.i = i;
    }


    /**
     * sprawdza czy zostal kliknięty przycisk od zmiany klawiszy sterowania
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(window.switcherClicked(i)) {
            MenuKeyListener.setCanChange(i);
        }
    }
}
