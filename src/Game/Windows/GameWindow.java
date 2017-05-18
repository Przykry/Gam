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
import java.awt.image.ImageObserver;
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
    private Image gameBar;
    private JLabel points[] = {
            new JLabel(),
            new JLabel()
    };
    private JLabel barText[] = {
            new JLabel(),
            new JLabel()
    };

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
            gameBar = getBackgroundImage("gameBar");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        createBackButton();
        leftGoal = new Goal(1);
        rightGoal = new Goal(2);
        ball = new Ball(width/2, getGround()-600,0,0,this);
        setPlayerStanding();
        createPointsLabel();
        createColonLabel();
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

    private void createBackButton(){
        backButton = new JButton();
        createButton(backButton,15,15,"textures\\backGameButton.png",new BackButtonListener());
        backButton.setFont(new Font("Comic Sans", Font.BOLD, 32));
        backButton.setVerticalTextPosition(SwingConstants.CENTER);
        backButton.setHorizontalTextPosition(SwingConstants.CENTER);
        backButton.setForeground(Color.WHITE);
        backButton.setText("Back");
        this.add(backButton);
    }

    private void drawWindowBar(Graphics g){
       g.drawImage(gameBar,15,15,this);
    }

    private void setBallStartPosition(Ball ball){
        ball.setX(width/2);
        ball.setY(getGround()-600);
        ball.setSpeedX(0);
        ball.setSpeedY(0);
    }

    private void setColonPosition(JLabel label){
        label.setBounds(width/2,20,32,32);
    }

    private void createColonLabel(){
        setColonPosition(barText[0]);
        setTextLabelForeground(barText[0]);
        barText[0].setText(":");
        this.add(barText[0]);
    }

    private void setTextLabelForeground(JLabel label){
        label.setFont(new Font("Comic Sans", Font.BOLD, 32));
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
    }

    private void setLabelPosition(JLabel label,int i){
        label.setBounds(width/2 - 20 + i*32,20,32,32);
    }

    private void setLabelPoints(JLabel label,Player player){
        label.setText(String.valueOf(player.getPoints()));
    }

    private void createPointsLabel(){
        setLabelPoints(points[0],player1);
        setLabelPoints(points[1],player2);
        for(int i = 0; i< points.length; i++){
            setTextLabelForeground(points[i]);
            setLabelPosition(points[i],i);
            this.add(points[i]);
        }
    }

    public static void resetPoints(){
        player1.setPoints(0);
        player2.setPoints(0);
    }

    private boolean isScored(){
        if(leftGoal.isLeftScored(ball)) {
            player2.setPoints(player2.getPoints() + 1);
            setLabelPoints(points[1],player2);
            return true;
        }
        else if(rightGoal.isRightScored(ball)) {
            player1.setPoints(player1.getPoints() + 1);
            setLabelPoints(points[0],player1);
            return true;
        }
        return false;
    }

    private void startGameAgain(){
        if(isScored()){
            setPlayerStanding();
            setBallStartPosition(ball);
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
        drawWindowBar(graphics);
        drawBorders(graphics,860,640);
        drawPlayers(graphics);
        drawGoalsAndBall(graphics);
    }

    private boolean checkIfPlayerHits(){
        if(Math.abs(player1.getX() - player2.getX()) < player1.getRadiusHead() + player2.getRadiusHead()){
            blockPlayers(false);
            player1.setPlayerBlocked(true);
            player2.setPlayerBlocked(true);
            return true;
        }else{
            return false;
        }
    }

    private boolean checkIfPlayersBlockedTheBall(){
        if(ball.checkIfIntersectsBoth(player1,player2)){
            blockPlayers(true);
            player1.setPlayerBallBlocked(true);
            player2.setPlayerBallBlocked(true);
            return true;
        }
        else{
            ball.setBlocked(false);
            return false;
        }
    }

    private void blockPlayers(boolean ifBall){
        if(player1.getCenterHeadX() < player2.getCenterHeadX() ){
            player1.setBlockedRight(true);
            player2.setBlockedLeft(true);
            if(ifBall) ball.setBlocked(true);
        }else{
            player1.setBlockedLeft(true);
            player2.setBlockedRight(true);
            if(ifBall) ball.setBlocked(true);
        }
    }

    private boolean playerExitFrame(Player player){
        if(player.getCenterHeadX()+player.getRadiusHead() >= 860-15){
            player.setX(860-15-2*player.getRadiusHead());
            player.setBlockedRight(true);
            player.setExitBlocked(true);
            return true;
        }else if(player.getCenterHeadX()-player.getRadiusHead() <= 15){
            player.setX(15);
            player.setBlockedLeft(true);
            player.setExitBlocked(true);
            return true;
        }else{
            if(player.isBlockedRight() && player.isExitBlocked()){
                player.setBlockedRight(false);
                player.setExitBlocked(false);
            }
            else if(player.isBlockedLeft() && player.isExitBlocked()){
                player.setBlockedLeft(false);
                player.setExitBlocked(false);
            }
            return false;
        }
    }

    private void unblockPlayers(){
        if(player1.isMovingRight() && !checkIfPlayerHits()){
            player1.setBlockedLeft(false);
            player2.setBlockedRight(false);
        }
        if(player1.isMovingLeft() && !checkIfPlayerHits()){
            player1.setBlockedRight(false);
            player2.setBlockedLeft(false);
        }
        if(player2.isMovingRight() && !checkIfPlayerHits()){
            player1.setBlockedRight(false);
            player2.setBlockedLeft(false);
        }
        if(player2.isMovingLeft() && !checkIfPlayerHits()){
            player1.setBlockedLeft(false);
            player2.setBlockedRight(false);
        }
    }

    public static int getGround(){
        return 625;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ball.checkIfIntersects(player1);
        ball.checkIfIntersects(player2);
        ball.checkIfBodyIntersects(player1);
        ball.checkIfBodyIntersects(player2);
        checkIfPlayersBlockedTheBall();
        checkIfPlayerHits();
        startGameAgain();
        Goal.ballHittingGoal(ball);
        playerExitFrame(player1);
        playerExitFrame(player2);
        unblockPlayers();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        this.repaint();
    }
}