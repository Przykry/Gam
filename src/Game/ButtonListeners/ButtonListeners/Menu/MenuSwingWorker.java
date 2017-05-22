package Game.ButtonListeners.ButtonListeners.Menu;

import Game.Windows.MenuWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Przykry on 09.05.2017.
 */
public class MenuSwingWorker extends SwingWorker<Void,Void> {
    private MenuWindow window;
    private int i;
    private int keys[] = MenuWindow.getKeys();

    MenuSwingWorker(MenuWindow window, int i){
        this.window = window;
        this.i = i;
    }

    @Override
    protected Void doInBackground() throws Exception {
        return null;
    }

    @Override
    protected void done() {
        window.getKeySwitcher(i).setText(KeyEvent.getKeyText(keys[i]));
    }

}
