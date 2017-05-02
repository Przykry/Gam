package Game.Windows;

import Game.ButtonListeners.AcceptMenuButtonListener;
import Game.ButtonListeners.BackButtonListener;
import Game.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Przykry on 29.04.2017.
 */
public class MenuWindow extends JPanel implements WindowInt {
    int width,heigth;
    Image backgroundImage;

    private JButton [] buttons = new JButton[]{
            new JButton(),
            new JButton()
    };

    private String [] filepaths = new String[]{
            "textures\\backButton.png",
            "textures\\acceptMenuButton.png"
    };

    private ActionListener[] listeners = new ActionListener[]{
            new BackButtonListener(),
            new AcceptMenuButtonListener(),
    };

    public MenuWindow(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
        try {
            this.backgroundImage = getBackgroundImage("mainBackground");
        }
        catch (IOException e){
           this.backgroundImage = Toolkit.getDefaultToolkit().createImage("background.jpg");
        }
        for(int i = 0; i < buttons.length; i++){
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
