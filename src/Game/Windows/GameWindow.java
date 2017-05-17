package Game.Windows;

import Game.ButtonListeners.BackButtonListener;
import Game.ButtonListeners.PlayerMoveListener;
import Game.Entities.Ball;
import Game.Entities.Goal;
import Game.Entities.Player;
import Game.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 29.04.2017.
 */
public class GameWindow extends JPanel implements WindowInt, ActionListener{
    private int width, height;
    private Image backgroundImage;
    private Goal leftGoal, rightGoal;
    private static Ball ball;
    private JButton backButton;
    private static Player player1, player2;
    private static Timer timer;
    static Thread[] entities;
    public static Timer getTimer() {
        return timer;
    }

    public static Player getPlayer(int player) {
       if(player == 1) return player1;
       else return player2;
    }

    public GameWindow(int width, int height, ChoosePlayerWindow window){
        this.width = width;
        this.height = height;
        this.player1 = window.getPlayer1();
        player1.setPlayerHeadImage(1);
        player1.setPlayerTorsoImage(2);
        this.player2 = window.getPlayer2();
        player2.setPlayerTorsoImage(0);
        player2.setPlayerHeadImage(0);
        setPlayerKeys();
        //player1.setTurnedLeft(false);
        //player2.setTurnedLeft(true);
        try {
            backgroundImage = getBackgroundImage("mainBackground");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        backButton = new JButton();
        createButton(backButton,50,25,"textures\\backButton.png",new BackButtonListener());
        this.add(backButton);
        leftGoal = new Goal(1);
        rightGoal = new Goal(2);
        ball = new Ball(width/2, getGround()-600,0,0,this);
        setPlayerStanding();

        this.addKeyListener(new PlayerMoveListener(player1,player2));
        this.setFocusable(true);
        this.setLayout(null);
        Main.setGameWindow(this);
        timer = new Timer(5,this);
    }

    public static void setPlayerKeys(){
        player1.setKeys(1);
        player2.setKeys(2);
    }

    private void setPlayerStanding(){
        player1.setX(leftGoal.getWidth()+20+15);
        player1.setY(height -15-player1.getHeightTorso()-2*player1.getRadiusHead());
        player2.setX(width - rightGoal.getWidth()-2*player2.getRadiusHead()-20);
        player2.setY(height -15-player2.getHeightTorso()-2*player2.getRadiusHead());
        player1.setCenterHeadX(player1.getX() + player1.getRadiusHead());
        player1.setCenterHeadY(player1.getY() + player1.getRadiusHead());
        player2.setCenterHeadX(player2.getX() + player2.getRadiusHead());
        player2.setCenterHeadY(player2.getY() + player2.getRadiusHead());
    }

    public static void runEntities(){
        List<Runnable> entity = new ArrayList<>();
        entity.add(player1);
        entity.add(player2);
        entity.add(ball);
        entities = new Thread[3];
        for(int i=0;i<3;i++){
            entities[i] = new Thread(entity.get(i));
            entities[i].start();
        }
    }

    public static void stopEntities(){
        for(Thread t : entities){
            t.suspend();
        }
    }


    private void drawGoalsAndBall(Graphics graphics){
        ball.drawBall(graphics);
        graphics.drawImage(leftGoal.getGoalImage(),16, height -15-leftGoal.getHeigth(),this);
        graphics.drawImage(rightGoal.getGoalImage(),width-15- rightGoal.getWidth(), height -15- rightGoal.getHeigth(),this);
    }





    private void drawPlayers(Graphics graphics){
        graphics.drawImage(player1.getHeadImage(player1.getPlayerHeadImage()),player1.getX(),player1.getY(),this);
        graphics.drawImage(player1.getTorsoImage(player1.getPlayerTorsoImage()),player1.getX()+3,player1.getY()+2*player1.getRadiusHead(),this);

        graphics.drawImage(player2.getHeadImage(player2.getPlayerHeadImage()),player2.getX(),player2.getY(),this);
        graphics.drawImage(player2.getTorsoImage(player2.getPlayerTorsoImage()),player2.getX()+3,player2.getY()+2*player2.getRadiusHead(),this);
    }

    public void paintComponent(Graphics graphics){
        drawBackground(graphics,backgroundImage,this);
        drawBorders(graphics,860,640);
        drawGoalsAndBall(graphics);
        drawPlayers(graphics);
    }


    private void checkIfBodyIntersects(Ball ball, Player player2, Player player1){
        if(ball.getCenterX() + ball.getRadius() >= player2.getX()+28 && ball.getY() <= player2.getY() + player2.getRadiusHead()+player2.getHeightTorso() && ball.getY() > player2.getY() + player2.getRadiusHead()){
            ball.reverseSpeedX();
            ball.setSpeedX((int)(ball.getSpeedX()*0.8 + player2.getSpeed()*0.8));
        }
        if(ball.getCenterX() - ball.getRadius() <= player1.getX()-10 +player1.getWidthTorso() && ball.getY() <= player1.getY() + player1.getRadiusHead()+player1.getHeightTorso() && ball.getY() > player1.getY() + player1.getRadiusHead()){
            ball.reverseSpeedX();
            ball.setSpeedX((int)(ball.getCenterX()*0.8 + player1.getSpeed()*0.8));
        }
    }

    private void blockPlayers(){
        if(ball.checkIfIntersectsBoth(player1,player2)){
            if(player1.getCenterHeadX() < player2.getCenterHeadX() ){
                player1.setBlockedRight(true);
                player2.setBlockedLeft(true);
                ball.setBlocked(true);
            }else{
                player1.setBlockedLeft(true);
                player2.setBlockedRight(true);
                ball.setBlocked(true);
            }
        }else{
            player1.setBlockedLeft(false);
            player1.setBlockedRight(false);
            player2.setBlockedLeft(false);
            player2.setBlockedRight(false);
            ball.setBlocked(false);
        }
    }

    public static int getGround(){
        return 625;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        blockPlayers();
        this.repaint();
    }
}
