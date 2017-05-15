package Game.ButtonListeners.ButtonListeners.Menu;

import Game.Windows.GameWindow;
import Game.Windows.MenuWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Przykry on 29.04.2017.
 */
public class AcceptMenuButtonListener implements ActionListener {
    private int keys[] = MenuWindow.getKeys();
    private String savedKeys = "";


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int k : keys) {
            savedKeys += (k + "\n");
        }
        System.out.println(savedKeys);
        try {
            PrintWriter writer = new PrintWriter("textures\\KeyBindings.txt", "UTF-8");
            writer.println(savedKeys);
            writer.close();
            GameWindow.setPlayerKeys();
        } catch (IOException exp) {
            exp.printStackTrace();
        }
        savedKeys = "";
    }
}
