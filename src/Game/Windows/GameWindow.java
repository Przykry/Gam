package Game.Windows;

import Game.ButtonListeners.BackButtonListener;
import Game.Entities.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Daniel on 29.04.2017.
 */
public class GameWindow extends JPanel implements WindowInt{
    private int width, heigth;
    private Image backgroundImage;
    private Goal leftGoal, rigthGoal;
    private Ball ball;
    private JButton backButton;
    private Player player1, player2;

    public GameWindow(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
        try {
            backgroundImage = getBackgroundImage("mainBackground");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        backButton = new JButton();
        createButton(backButton,50,25,"textures\\backButton.png",new BackButtonListener());
        this.setLayout(null);
        this.add(backButton);
        leftGoal = new Goal(1);
        rigthGoal = new Goal(2);
        ball = new Ball((width-30)/2,50,0,0,this);
    }

    public void paintComponent(Graphics graphics){
        drawBackground(graphics,backgroundImage,this);
        drawBorders(graphics,860,640);
        graphics.drawImage(ball.getBallImage(),ball.getX(),ball.getY(),this);
        graphics.drawImage(leftGoal.getGoalImage(),16,heigth-15-leftGoal.getHeigth(),this);
        graphics.drawImage(rigthGoal.getGoalImage(),width-15-rigthGoal.getWidth(),heigth-15-rigthGoal.getHeigth(),this);
    }
}
