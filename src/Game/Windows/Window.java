package Game.Windows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Created by Przykry on 29.04.2017.
 */
public interface Window {

    default void createButton(JButton button, int x, int y, String filepath, ActionListener list) {
        ImageIcon icon = new ImageIcon(filepath);
        //Image scaledImg = icon.getImage().getScaledInstance(246, 60,  Image.SCALE_SMOOTH);
        //icon = new ImageIcon(scaledImg);
        button.setIcon(icon);
        button.setBounds(x,y,icon.getIconWidth(),icon.getIconHeight());
        button.addActionListener(list);
        button.setFocusable(false);
    }

    default Image getBackgroundImage(String nameBackground) throws IOException{
            return ImageIO.read(new File("textures\\" + nameBackground + ".png"));
    }

    default void drawBorders(Graphics graphics, int width, int heigth){
        graphics.setColor(Color.black);
        graphics.fillRect(0,0,15,heigth);
        graphics.fillRect(0,0,width,15);
        graphics.fillRect(0,heigth-15,width,15);
        graphics.fillRect(width-15,0,15,heigth);
    }

    default void drawBackground(Graphics g, Image backgroundImage, ImageObserver window){
        g.drawImage(backgroundImage,0,0, window);
    }
}
