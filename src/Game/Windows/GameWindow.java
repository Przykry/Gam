package Game.Windows;

import Game.ButtonListeners.BackButtonListener;
import Game.Entities.Ball;
import Game.Entities.Goal;
import Game.Entities.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Daniel on 29.04.2017.
 */
public class GameWindow extends JPanel implements WindowInt{
    private int width, heigth;
    private Image backgroundImage;
    private Goal leftGoal, rightGoal;
    private Ball ball;
    private JButton backButton;
    private Player player1, player2;

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
}

    public GameWindow(int width, int heigth, ChoosePlayerWindow window){
        this.width = width;
        this.heigth = heigth;
        this.player1 = window.getPlayer1();
        this.player2 = window.getPlayer2();
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
        rightGoal = new Goal(2);
        ball = new Ball((width-30)/2,50,0,0,this);
        player1.setX(leftGoal.getWidth()+20+15);
        player1.setY(heigth-15-player1.getHeigthTorso()-player1.getRadiusHead());
        player2.setX(width - rightGoal.getWidth()-player2.getRadiusHead()-20);
        player2.setY(heigth-15-player2.getHeigthTorso()-player2.getRadiusHead());
        player1.setKeys(1);
        player2.setKeys(2);
    }

    private void drawGoalsAndBall(Graphics graphics){
        graphics.drawImage(ball.getBallImage(),ball.getX(),ball.getY(),this);
        graphics.drawImage(leftGoal.getGoalImage(),16,heigth-15-leftGoal.getHeigth(),this);
        graphics.drawImage(rightGoal.getGoalImage(),width-15- rightGoal.getWidth(),heigth-15- rightGoal.getHeigth(),this);
    }

    private void drawPlayers(Graphics graphics){
        graphics.drawImage(player1.getHeadImage(2),player1.getX(),player1.getY(),this);
        graphics.drawImage(player1.getTorsoImage(3),player1.getX()+3,player1.getY()+player1.getRadiusHead(),this);

        graphics.drawImage(player2.getHeadImage(0),player2.getX(),player2.getY(),this);
        graphics.drawImage(player2.getTorsoImage(0),player2.getX()+3,player2.getY()+player2.getRadiusHead(),this);
    }

    public void paintComponent(Graphics graphics){
        drawBackground(graphics,backgroundImage,this);
        drawBorders(graphics,860,640);
        drawGoalsAndBall(graphics);
        drawPlayers(graphics);
    }
}
