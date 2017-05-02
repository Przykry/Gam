package Game.Entities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Daniel on 28.04.2017.
 */
public class Ball {
    private int x, y;
    private int width, heigth;
    private int speedX, speedY;
    private int centerX, centerY;
    private int radius;
    private Image ballImage;
    JPanel observer;

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

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public Image getBallImage() {
        return ballImage;
    }

    public void move(){
        this.x += speedX/10;
        this.y += speedY/10;
        this.centerX += speedX/10;
        this.centerY += speedY/10;
    }

    public void reverseSpeedX(){
        this.speedX = -this.speedX;
    }

    public void reverseSpeedY(){
        this.speedY = -this.speedY;
    }





    public void increaseSpeedX(int x){
        if(this.speedX >= 0){
            this.speedX += x;
        }
        else{
            this.speedX -= x;
        }
    }

    public void increaseSpeedY(int y){
        if(this.speedY >= 0){
            this.speedY += y;
        }
        else{
            this.speedY -= y;
        }
    }

    public void decreaseSpeedX(int x){
        if(this.speedX > 0){
            this.speedX -= x;
        }
        else if(this.speedX < 0){
            this.speedX += x;
        }
    }

    public void decreaseSpeedY(int y){
        if(this.speedY > 0){
            setSpeedY(this.speedY - y);
        }
        else if(this.speedY < 0){
            setSpeedY(this.speedY + y);
        }
    }


    public void directoryOfBall() {
        ballHittingBorder(observer.getHeight(),observer.getWidth());
        move();
    }

    public void drawBall(Graphics g){
        g.drawImage(getBallImage(), getX(), getY(), observer);
    }

    public void ballHittingBorder(int heigth, int width) {
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

    public double calculatePythagoras(Ball ball1) {
        double aplusb = Math.pow(ball1.getCenterX() - this.getCenterX(), 2) + Math.pow(this.getCenterY() - ball1.getCenterY(), 2);
        double toReturn = Math.sqrt(aplusb);
        return toReturn;
    }

    public void calculateDirection(Ball ball1) {
        double xDir1 = (ball1.getCenterX() - this.getCenterX()) / calculatePythagoras(ball1);
        double yDir1 = (this.getCenterY() - ball1.getCenterY()) / calculatePythagoras(ball1);

        double xDir2 = (this.getCenterX() - ball1.getCenterX()) / calculatePythagoras(ball1);
        double yDir2 = (ball1.getCenterY() - this.getCenterY()) / calculatePythagoras(ball1);

        int dirX1 = (int) Math.round(xDir1 * 40);
        int dirY1 = (int) Math.round(yDir1 * 40);

        int dirX2 = (int) Math.round(xDir2 * 40);
        int dirY2 = (int) Math.round(yDir2 * 40);
        ball1.setSpeedX(dirX1);
        ball1.setSpeedY(-dirY1);
        this.setSpeedX(dirX2);
        this.setSpeedY(-dirY2);
    }

    public void checkIfIntersects(Ball ball1) {
        if (calculatePythagoras(ball1) <= ball1.getRadius() + this.getRadius()) {
            calculateDirection(ball1);
        }
    }

}

