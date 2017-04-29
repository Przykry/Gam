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

    public Ball(int x, int y, int speedX, int speedY, JPanel observer){
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        setBallImage();
        setRadius(ballImage,observer);
        this.width = this.heigth = this.radius*2;
        this.centerX = this.x + this.radius;
        this.centerY = this.y + this.radius;
    }

    public void setBallImage() {
        try {
            ballImage = ImageIO.read(new File("textures\\pilka.png"));
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
}

