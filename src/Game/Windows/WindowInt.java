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

/**
 * Interfejs implementujacy okna aplikacji
 */
public interface WindowInt{
    /**
     * metoda domyślna tworzy przycisk z standardowymi parametrami
     * @param button
     * @param x
     * @param y
     * @param filepath
     * @param list
     */
    default void createButton(JButton button, int x, int y, String filepath, ActionListener list) {
        ImageIcon icon = new ImageIcon(filepath);
        button.setIcon(icon);
        button.setBounds(x,y,icon.getIconWidth(),icon.getIconHeight());
        button.addActionListener(list);
        button.setFont(new Font("Hobo Std", Font.PLAIN, 32));
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setForeground(Color.decode("#2A2C2E"));
        button.setFocusable(false);
    }

    /**
     * pobieranie obrazka tla gry
     * @param nameBackground
     * @return
     * @throws IOException
     */
    default Image getBackgroundImage(String nameBackground) throws IOException{
            return ImageIO.read(new File("textures/" + nameBackground + ".png"));
    }

    /**
     * rysowanie obramowan na ekranie
     * @param graphics
     * @param width
     * @param heigth
     */
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
