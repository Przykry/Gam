package Game.Windows;

import Game.ButtonListeners.AcceptMenuButtonListener;
import Game.ButtonListeners.BackButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Przykry on 29.04.2017.
 */
public class HallOfFameWindow extends JPanel implements Window {
    int width,heigth;
    Image backgroundImage;

    JButton [] buttons = new JButton[]{
            new JButton(),
    };

    String [] filepaths = new String[]{
            "textures\\backButton.png",
            "textures\\acceptMenuButton.png"
    };

    ActionListener[] listeners = new ActionListener[]{
            new BackButtonListener(),
            new AcceptMenuButtonListener(),
    };

    public HallOfFameWindow(int width, int heigth) throws IOException{
        this.width = width;
        this.heigth = heigth;
        this.backgroundImage = getBackgroundImage("mainBackground");

        for(int i = 0; i < 1; i++){
            createButton(buttons[i],80+520*i,500,filepaths[i],listeners[i]);
            this.add(buttons[i]);
        }

        this.setFocusable(true);
        this.setLayout(null);
    }


    public void paintComponent(Graphics g){
        drawBackground(g,backgroundImage,this);
        drawBorders(g,width,heigth);
    }
}
