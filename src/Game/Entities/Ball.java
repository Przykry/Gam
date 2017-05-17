package Game.Entities;

import Game.Windows.GameWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static Game.Windows.GameWindow.getGround;
import static java.lang.Thread.sleep;

/**
 * Created by Daniel on 28.04.2017.
 */
public class Ball implements Runnable {
    private int x, y;
    private int width, heigth;
    private int speedX, speedY;
    private int centerX, centerY;
    private int radius;
    private Image ballImage;
    JPanel observer;
    private volatile boolean threadSuspended;

    public Ball(int x, int y, int speedX, int speedY, JPanel observer){
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.observer = observer;
        setBallImage();
        setRadius(ballImage,observer);
        this.width = this.heigth = this.radius*2;
        this.centerX = this.x + this.radius;
        this.centerY = this.y + this.radius;
    }

    public void setBallImage() {
        try {
            ballImage = ImageIO.read(new File("textures\\ball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRadius(Image img, JPanel observer){
        this.radius = img.getHeight(observer)/2;
    }

    public int getRadius() {
        return radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.centerX = x + this.radius;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.centerY = y + this.radius;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public Image getBallImage() {
        return ballImage;
    }

    private void move(){
        this.x += speedX/10;
        this.y += speedY/10;
        this.centerX = this.x + 30;
        this.centerY = this.y + 30;
    }

    public void reverseSpeedX(){
        this.speedX = -this.speedX;
    }

    public void reverseSpeedY(){
        this.speedY = -this.speedY;
    }

    void directoryOfBall() {
        ballHittingBorder(observer.getHeight(),observer.getWidth());
        move();
    }

    public void drawBall(Graphics g){
        g.drawImage(getBallImage(), getX(), getY(), observer);
    }

    private void ballHittingBorder(int heigth, int width) {
        if (new Rectangle(0, 0, 15, heigth).intersects(new Rectangle(this.getX(), this.getY(), this.getRadius() * 2, this.getRadius() * 2))) {
            this.reverseSpeedX();
            if (this.getX() < 15) this.setX(16);
        }
        if (new Rectangle(0, 0, width, 15).intersects(new Rectangle(this.getX(), this.getY(), this.getRadius() * 2, this.getRadius() * 2))) {
            this.reverseSpeedY();

            if (this.getY() < 15) this.setY(16);
        }
        if (new Rectangle(0, heigth - 15, width, 15).intersects(new Rectangle(this.getX(), this.getY(), this.getRadius() * 2, this.getRadius() * 2))) {
            this.reverseSpeedY();
            if (this.getY() + this.getHeigth() > heigth - 15) this.setY(heigth - 1 - 15 - this.getHeigth());
        }
        if (new Rectangle(width - 15, 0, 15, heigth).intersects(new Rectangle(this.getX(), this.getY(), this.getRadius() * 2, this.getRadius() * 2))) {
            this.reverseSpeedX();
            if (this.getX() + this.getWidth() > width - 15) this.setX(width - 1 - 15 - this.getWidth());
        }
    }

    private double calculatePythagoras(Ball ball1) {
        double aplusb = Math.pow(ball1.getCenterX() - this.getCenterX(), 2) + Math.pow(this.getCenterY() - ball1.getCenterY(), 2);
        return Math.sqrt(aplusb);
    }
    private double calculatePythagoras(Ball ball, Player player) {
        double aplusb = Math.pow(ball.getCenterX() - player.getCenterHeadX(), 2) + Math.pow(player.getCenterHeadY() - ball.getCenterY(), 2);
        return Math.sqrt(aplusb);
    }


    private void calculateDirection(Object obj) {
       if(obj instanceof Ball) {
           Ball ball = (Ball)obj;
           double xDir1 = (ball.getCenterX() - this.getCenterX()) / calculatePythagoras(ball);
           double yDir1 = (this.getCenterY() - ball.getCenterY()) / calculatePythagoras(ball);

           double xDir2 = (this.getCenterX() - ball.getCenterX()) / calculatePythagoras(ball);
           double yDir2 = (ball.getCenterY() - this.getCenterY()) / calculatePythagoras(ball);

           int dirX1 = (int) Math.round(xDir1 * 50);
           int dirY1 = (int) Math.round(yDir1 * 50);

           int dirX2 = (int) Math.round(xDir2 * 50);
           int dirY2 = (int) Math.round(yDir2 * 50);
           ball.setSpeedX(dirX1);
           ball.setSpeedY(-dirY1);
           this.setSpeedX(dirX2);
           this.setSpeedY(-dirY2);
       }
       else if(obj instanceof Player){
           Player player = (Player) obj;
           int dirX1, dirY1;
           double xDir1 = (this.getCenterX() - player.getCenterHeadX()) / calculatePythagoras(this,player);
           double yDir1 = (player.getCenterHeadY() - this.getCenterY()) / calculatePythagoras(this, player);
           if(player.isMovingLeft() && speedX < 0 && player.getCenterHeadX() > this.getCenterX()){
               dirX1 = (int) Math.round(xDir1 * 20 - Math.abs(speedX) - player.getSpeed() * 10);
           }
           else if(player.isMovingLeft() && speedX < 0 && player.getCenterHeadX() <= this.getCenterX()) {
               dirX1 = (int) Math.round(xDir1 * 20 + (Math.abs(speedX)*0.7)+player.getSpeed()*10);
           }
           else if(player.isMovingLeft() && speedX > 0){
               dirX1 = (int) Math.round(xDir1 * 20 - Math.abs(speedX) * 0.7 - player.getSpeed()*10);
           }
           else if(player.isMovingRight() && speedX < 0){
               dirX1 = (int) Math.round(xDir1 * 20 + Math.abs(speedX) * 0.7 + player.getSpeed()*10);
           }
           else if(player.isMovingRight() && speedX > 0 && player.getCenterHeadX() > this.getCenterX()){
               dirX1 = (int) Math.round(xDir1 * 20 - Math.abs(speedX) * 0.7);
           }
           else if(player.isMovingRight() && speedX > 0 && player.getCenterHeadX() <= this.getCenterX()){
               dirX1 = (int) Math.round(xDir1 * 20 + Math.abs(speedX) + player.getSpeed() * 10);
           }
           else{
               dirX1 = (int) Math.round(xDir1 * 20 - speedX * 0.7);
           }
           if(player.isJumping()) {
               dirY1 = (int) Math.round(yDir1 * (Math.abs(speedY))+player.getTerminalVelocity()*4);
           }
           else{
               dirY1 = (int) Math.round(yDir1 * (Math.abs(speedY)));
               if(dirY1 < -150) dirY1 = -150;
           }
           this.setSpeedX(dirX1);
           this.setSpeedY(-dirY1);
       }
    }

    void checkIfIntersects(Object obj) {
        if(obj instanceof Ball) {
            Ball ball1 = (Ball)obj;
            if (calculatePythagoras(ball1) <= ball1.getRadius() + this.getRadius()) {
                calculateDirection(ball1);
            }
        }
        else if(obj instanceof Player){
            Player player = (Player)obj;
            if (calculatePythagoras(this, player) <= this.getRadius() + player.getRadiusHead()) {
                calculateDirection(player);
            }
        }
    }

    private void speedLimitY(){
        if(speedY > 300) speedY = 300;
        else if(speedY < -300) speedY = -300;
    }

    private void gravity(){
        if(y + 2*radius < getGround() && speedY < 300) speedY += 10;
        else if(y+2*radius >= getGround()) {
            y = getGround() - 2*radius;
            speedY = (int)Math.round((-speedY) * 0.7);
            speedX = (int)Math.round(speedX*0.8);
        }
    }

    @Override
    public void run() {
        while(true){
            directoryOfBall();
            gravity();
            checkIfIntersects(GameWindow.getPlayer(1));
            checkIfIntersects(GameWindow.getPlayer(2));
            speedLimitY();


            try{
                sleep(20);
                if(threadSuspended){
                    synchronized(this){
                        while (threadSuspended){
                            wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

