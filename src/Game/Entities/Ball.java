package Game.Entities;

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

/**
 *
 * Klasa przechowuje informacje i metody dotyczące piłki oraz implementuje wątek który porusza piłką.
 */
public class Ball implements Runnable {
    private int x, y;
    private int width, heigth;
    private int speedX, speedY;
    private int centerX, centerY;
    private int radius;
    private Image ballImage;
    private JPanel observer;
    private volatile boolean threadSuspended;
    private boolean blocked;

    public void setBlocked(boolean blocked){
        this.blocked = blocked;
    }

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

    private void setBallImage() {
        try {
            ballImage = ImageIO.read(new File("textures/ball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setRadius(Image img, JPanel observer){
        this.radius = img.getHeight(observer)/2;
    }

    int getRadius() {
        return radius;
    }

    int getCenterX() {
        return centerX;
    }

    int getCenterY() {
        return centerY;
    }

    private int getWidth() {
        return width;
    }

    private int getHeigth() {
        return heigth;
    }

    private int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.centerX = x + this.radius;
    }

    int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.centerY = y + this.radius;
    }

    int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    private Image getBallImage() {
        return ballImage;
    }

    /**
     * Metoda rusza piłką jeśli ta nie jest zablokowana
     */
    private void move(){
        if(!blocked) {
            this.x += speedX / 10;
            this.y += speedY / 10;
            this.centerX = this.x + 30;
            this.centerY = this.y + 30;
        }else {
            speedY = 0;
            speedX = 0;
        }
    }

    /**
     * Odwraca zwrot prędkości piłki w poziomie
     */
    private void reverseSpeedX(){
        this.speedX = -this.speedX;
    }

    /**
     * Odwraca zwrot prędkości piłki w pionie
     */
    void reverseSpeedY(){
        this.speedY = -this.speedY;
    }

    /**
     * Metoda sprawdza czy piłka nie uderzyła w ramkę okna i wywołuje jej ruch.
     */
    void directoryOfBall() {
        ballHittingBorder(observer.getHeight(),observer.getWidth());
        move();
    }

    public void drawBall(Graphics g){
        g.drawImage(getBallImage(), getX(), getY(), observer);
    }

    /**
     * Sprawdzenie czy piłka nie uderzyła w ramkę.
     * @param heigth
     * @param width
     */
    private void ballHittingBorder(int heigth, int width) {
        if (new Rectangle(0, 0, 15, heigth).intersects(new Rectangle(this.getX(), this.getY(), this.getRadius() * 2, this.getRadius() * 2))) {
            this.reverseSpeedX();
            if (this.getX() <= 15){
                this.setX(16);
                centerX = 46;
            }
        }
        if (new Rectangle(0, 0, width, 15).intersects(new Rectangle(this.getX(), this.getY(), this.getRadius() * 2, this.getRadius() * 2))) {
            this.reverseSpeedY();

            if (this.getY() <= 15){
                this.setY(16);
                centerY = 46;
            }
        }
        if (new Rectangle(0, heigth - 15, width, 15).intersects(new Rectangle(this.getX(), this.getY(), this.getRadius() * 2, this.getRadius() * 2))) {
            this.reverseSpeedY();
            if (this.getY() + this.getHeigth() >= heigth - 15){
                this.setY(heigth - 1 - 15 - this.getHeigth());
                this.centerY = 640 - 1 - 15 - radius;
            }
        }
        if (new Rectangle(width - 15, 0, 15, heigth).intersects(new Rectangle(this.getX(), this.getY(), this.getRadius() * 2, this.getRadius() * 2))) {
            this.reverseSpeedX();
            if (this.getX() + this.getWidth() >= width - 15){
                this.setX(width - 1 - 15 - this.getWidth());
                this.centerY = 860 - 1 - 15 - radius;
            }
        }
    }

    /**
     * Oblicza pitagorasa w celu sprawdzenia czy piłka nie zderzyła się z inną piłką (menu główne)
     * @param ball1
     * @return
     */
    private double calculatePythagoras(Ball ball1) {
        double aplusb = Math.pow(ball1.getCenterX() - this.getCenterX(), 2) + Math.pow(this.getCenterY() - ball1.getCenterY(), 2);
        return Math.sqrt(aplusb);
    }

    /**
     * Oblicza pitagorasa w celu sprawdzenia czy piłka nie zderzyła się z głową piłkarza (gra)
     * @param ball
     * @param player
     * @return
     */
    private double calculatePythagoras(Ball ball, Player player) {
        double aplusb = Math.pow(ball.getCenterX() - player.getCenterHeadX(), 2) + Math.pow(player.getCenterHeadY() - ball.getCenterY(), 2);
        return Math.sqrt(aplusb);
    }

    /**
     * Metoda na podstawie funkcji trygonometrycznych oblicza kierunek lotu piłki/piłek po zderzeniu.
     * @param obj
     */
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
           int dirX1=0, dirY1;
           double xDir1 = (this.getCenterX() - player.getCenterHeadX()) / calculatePythagoras(this,player);
           double yDir1 = (player.getCenterHeadY() - this.getCenterY()) / calculatePythagoras(this, player);
           if(player.isMovingLeft() && speedX <= 0 && player.getCenterHeadX() > this.getCenterX()){
               dirX1 = (int) Math.round(xDir1 * 20 - Math.abs(speedX) - player.getSpeed() * 10);
           }
           else if(player.isMovingLeft() && speedX <= 0 && player.getCenterHeadX() <= this.getCenterX()) {
               dirX1 = (int) Math.round(xDir1 * 20 + (Math.abs(speedX)*0.7)+player.getSpeed()*10);
           }
           else if(player.isMovingLeft() && speedX >= 0){
               dirX1 = (int) Math.round(xDir1 * 20 - Math.abs(speedX) * 0.7 - player.getSpeed()*10);
           }
           else if(player.isMovingRight() && speedX <= 0){
               dirX1 = (int) Math.round(xDir1 * 20 + Math.abs(speedX) * 0.7 + player.getSpeed()*10);
           }
           else if(player.isMovingRight() && speedX >= 0 && player.getCenterHeadX() > this.getCenterX()){
               dirX1 = (int) Math.round(xDir1 * 20 - Math.abs(speedX) * 0.7);
           }
           else if(player.isMovingRight() && speedX >= 0 && player.getCenterHeadX() <= this.getCenterX()){
               dirX1 = (int) Math.round(xDir1 * 20 + Math.abs(speedX) + player.getSpeed() * 10);
           }
           else if(speedX <= 0 && player.getCenterHeadX() > this.getCenterX()){
               dirX1 = (int) Math.round(xDir1 * 20 - Math.abs(speedX));
           }
           else if(speedX <= 0 && player.getCenterHeadX() <= this.getCenterX()) {
               dirX1 = (int) Math.round(xDir1 * 20 + (Math.abs(speedX)*0.7));
           }
           else if(speedX >= 0 && player.getCenterHeadX() > this.getCenterX()){
               dirX1 = (int) Math.round(xDir1 * 20 - Math.abs(speedX)*0.7);
           }
           else if(speedX >= 0 && player.getCenterHeadX() <= this.getCenterX()) {
               dirX1 = (int) Math.round(xDir1 * 20 + (Math.abs(speedX)));
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

    /**
     * Wywołuje sprawdzenie czy piłka zderzyła się z inną piłką bądź głową piłkarza i jeśli tak to oblicza nowy kierunek lotu.
     * @param obj
     */
    public void checkIfIntersects(Object obj) {
        if(obj instanceof Ball) {
            Ball ball1 = (Ball)obj;
            if (calculatePythagoras(ball1) <= ball1.getRadius() + this.getRadius()) {
                calculateDirection(ball1);
            }
        }
        else if(obj instanceof Player) {
            Player player = (Player) obj;
            if (calculatePythagoras(this, player) <= this.getRadius() + player.getRadiusHead()) {
                calculateDirection(player);
            }
        }
    }

    /**
     * Sprawdza czy piłka dotyka tułowia piłkarza (działa tylko wtedy jeśli wciśnięty jest przycisk strzału, inaczej można przebiec obok piłki)
     * @param player
     */
    public void checkIfBodyIntersects(Player player) {
        if(player.isShooting()) {
            if (new Rectangle(x, y, 60, 60).intersects(new Rectangle(player.getX() + 15, player.getY() + 80, player.getWidthTorso() - 10, player.getHeightTorso())) && centerX > player.getCenterHeadX()) {
                setX(player.getCenterHeadX()+player.getRadiusHead()-15);
                speedY -= player.getShotStrength() * 20;
                speedX += player.getShotStrength() * 20;
            } else if (new Rectangle(x, y, 60, 60).intersects(new Rectangle(player.getX() + 15, player.getY() + 80, player.getWidthTorso() - 10, player.getHeightTorso())) && centerX < player.getCenterHeadX()) {
                setX(player.getCenterHeadX()-player.getRadiusHead()-2*radius+15);
                speedY -= player.getShotStrength() * 20;
                speedX -= player.getShotStrength() * 20;
            }
        }
    }

    /**
     * Metoda sprawdza czy piłka nie zostalą zablokowana między dwoma graczami
     * @param player1
     * @param player2
     * @return
     */
    public boolean checkIfIntersectsBoth(Player player1, Player player2){
        if (player1.getCenterHeadX() > centerX && player2.getCenterHeadX() < centerX || player1.getCenterHeadX() < centerX && player2.getCenterHeadX() > centerX){
            if(centerY > player1.getY() && centerY > player2.getY() && centerY < player1.getY()+2*player1.getRadiusHead() && centerY < player2.getY()+2*player2.getRadiusHead()){
                return Math.abs(player1.getCenterHeadX() - centerX) <= player1.getRadiusHead() + radius && Math.abs(player2.getCenterHeadX() - centerX) <= player2.getRadiusHead() + radius;
            }
            else if(centerY > player1.getCenterHeadY()+player1.getRadiusHead() && centerY > player2.getCenterHeadY() + player2.getRadiusHead()) {
                return Math.abs(player1.getCenterHeadX() - 20 - centerX) <= player1.getRadiusHead() + radius && Math.abs(player2.getCenterHeadX() + 25 - centerX) <= player2.getRadiusHead() + radius;
            }
            else return false;
        }
        else return false;
    }

    /**
     * Ustala limit prędkości pionowej dla piłki
     */
    private void speedLimitY(){
        if(speedY > 250) speedY = 250;
        else if(speedY < -250) speedY = -250;
    }

    /**
     * Ustala limit prędkości poziomek dla piłki
     */
    private void speedLimitX(){
        if(speedX > 250) speedX = 250;
        else if(speedX < -250) speedX = -250;
    }

    /**
     * Imituje grawitacje piłki
     */
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
            speedLimitY();
            speedLimitX();
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

