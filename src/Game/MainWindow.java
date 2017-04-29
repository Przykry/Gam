package Game;

import Game.ButtonListeners.ExitGameButtonListener;
import Game.ButtonListeners.HallOfFameButtonListener;
import Game.ButtonListeners.MenuButtonListener;
import Game.ButtonListeners.StartGameButtonListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Daniel on 24.04.2017.
 */
public class MainWindow extends JPanel implements ActionListener,Window{
    int width;
    int heigth;


    int xO, yO;
    int ballSpeedX;
    int ballSpeedY;
    JButton [] buttons = new JButton[]{
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton()
    };
    String [] filePaths = new String[]{
            "textures\\startGameButton.png",
            "textures\\hallOfFameButton.png",
            "textures\\menuButton.png",
            "textures\\exitGameButton.png"
    };
    ActionListener [] listeners = new ActionListener[]{
            new StartGameButtonListener(),
            new HallOfFameButtonListener(),
            new MenuButtonListener(),
            new ExitGameButtonListener()
    };
    Image backgroundImage = null, ball = null;

    private void setBall(){
        try {
            ball = ImageIO.read(new File("textures\\pilka.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public MainWindow(int width, int heigth) throws IOException {
        this.width = width;
        this.heigth = heigth;
        this.backgroundImage = getBackgroundImage("mainBackground");
        setBall();
        ballSpeedX = 3;
        ballSpeedY = 3;
        for(int i = 0; i<4; i++){
            createButton(buttons[i],width-300, i*60+i*40+60, filePaths[i],listeners[i]);
            this.add(buttons[i]);
        }
        this.xO = this.yO = 70;
        this.setFocusable(true);
        this.setLayout(null);
    }

    public void paintComponent(Graphics graphics){
        //background
        timer.start();
        drawBackground(graphics, backgroundImage,this);
        drawBorders(graphics,width,heigth);
        graphics.drawImage(ball,xO,yO,this);
    }

    Timer timer = new Timer(10,this);

    public void directoryOfBall(){
        xO += ballSpeedX;
        yO += ballSpeedY;
        if (new Rectangle(0,0,5,heigth).intersects(new Rectangle(xO,yO,60,60))){
            ballSpeedX = -ballSpeedX; // Reflect along normal
        } else if (new Rectangle(0,0,width,10).intersects(new Rectangle(xO,yO,60,60))) {
            ballSpeedY = -ballSpeedY;
        }
        else if (new Rectangle(0,heigth-5,width,5).intersects(new Rectangle(xO,yO,60,60))){
            ballSpeedY = -ballSpeedY;
        } else if (new Rectangle(width-5,0,5,heigth).intersects(new Rectangle(xO,yO,60,60))) {
            ballSpeedX = -ballSpeedX;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        directoryOfBall();
        this.repaint();
    }
}
