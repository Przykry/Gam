package Game.ButtonListeners;

import Game.ChoosePlayerWindow;
import Game.Main;
import Game.MenuWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Daniel on 25.04.2017.
 */
public class MenuButtonListener implements ActionListener {

    public MenuButtonListener(){

    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("Start game button pressed");
        Main.contentPane = Main.frame.getContentPane();
        Main.contentPane.removeAll();
        Main.contentPane.add(new MenuWindow(860, 640));
        Main.frame.validate();
        Main.frame.repaint();
        Main.frame.setVisible(true);
        System.out.println("Menu button pressed");
    }
}
