package Game.Windows;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 29.04.2017.
 */
public class GameWindow extends JPanel implements WindowConstants{
    private int width, heigth;
    private Image backgroundImage;

    public GameWindow(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
    }
}
