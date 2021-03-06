package Game.Entities;

import Game.Windows.MenuWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Game.Windows.GameWindow.getGround;
import static java.lang.Thread.sleep;

/**
 * Created by Przykry on 26.04.2017.
 */

/**
 * Klasa zawodnika zawarta w niej jest cala logika poruszania sie zawodnikiem oraz jego wyglada i atrybuty
 */

public class Player implements Runnable {
    private String name;
    private int x;
    private int y;
    private int centerHeadX;
    private int centerHeadY;
    private int widthTorso, heightTorso;
    private int radiusHead;
    private int speed;
    private int maxJump;
    private int shotStrength;
    private Image headImage[] = new Image[2];
    private Image torsoImage[] = new Image[4];
    private int jumpKey;
    private int leftKey;
    private final static int TERMINAL_VELOCITY = 20;
    private final static int GRAVITY = 15;
    private int fallingVelocity = GRAVITY;
    private volatile boolean threadSuspended;
    private int playerTorsoImage;
    private int playerHeadImage;
    private int points;
    private int rightKey;
    private int shotKey;
    private boolean movingLeft, movingRight, jumping, shooting;
    private boolean blockedLeft, blockedRight;
    private boolean exitBlocked;

    public void setPoints(int points) {this.points = points;}

    static int getTerminalVelocity() { return TERMINAL_VELOCITY;}

    public int getPoints() { return points; }

    public int getPlayerTorsoImage() {
        return playerTorsoImage;
    }

    public int getPlayerHeadImage() {
        return playerHeadImage;
    }

    public void setPlayerTorsoImage(int p1) {
        this.playerTorsoImage = p1;
    }

    public void setPlayerHeadImage(int p2) {
        this.playerHeadImage = p2;
    }

    public int getJumpKey() {
        return jumpKey;
    }

    public int getLeftKey() {
        return leftKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    public int getShotKey() {
        return shotKey;
    }

    public boolean isExitBlocked() {
        return exitBlocked;
    }

    boolean isShooting(){ return shooting; }

    public void setShooting(boolean shooting){ this.shooting = shooting; }

    public void setExitBlocked(boolean exitBlocked) {
        this.exitBlocked = exitBlocked;
    }

    public boolean isBlockedRight() {
        return blockedRight;
    }

    public void setBlockedRight(boolean blockedRight) {
        this.blockedRight = blockedRight;
    }

    public boolean isBlockedLeft() {
        return blockedLeft;
    }

    public void setBlockedLeft(boolean blockedLeft) {
        this.blockedLeft = blockedLeft;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setCenterHeadX(int centerHeadX) {
        this.centerHeadX = centerHeadX;
    }

    public void setCenterHeadY(int centerHeadY) {
        this.centerHeadY = centerHeadY;
    }

    public int getCenterHeadX() {
        return centerHeadX;
    }

    int getCenterHeadY() {
        return centerHeadY;
    }

    public int getWidthTorso() {
        return widthTorso;
    }

    public int getHeightTorso() {return heightTorso;}

    public boolean getMovingRight() { return movingRight;}

    public boolean getMovingLeft() { return movingLeft; }

    public int getRadiusHead() {
        return radiusHead;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxJump() {
        return maxJump;
    }

    public int getShotStrength() {
        return shotStrength;
    }

    public Image getHeadImage(int i) {
        return headImage[i];
    }

    public Image getTorsoImage(int i) {
        return torsoImage[i];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
        setCenterHeadX(x+radiusHead);
    }

    public Player(int x, int y, String name, int shotStrength, int speed, int maxJump) {
        this.name = name;
        this.x = x;
        this.y = y;
        for (int i = 0; i < 4; i++) {
            if(i<2) setHeadImage(i);
            setTorsoImage(i);
        }
        abilities(shotStrength, speed, maxJump);
        setPlayerWidthHeight();
        this.movingLeft = false;
        this.movingRight = false;
        this.jumping = false;
        this.exitBlocked = false;
        this.blockedRight = false;
        this.blockedLeft = false;
        this.shooting = false;
    }

    public void setY(int y) {
        this.y = y;
        setCenterHeadY(y+radiusHead);
    }

    /**
     * ustawia przyciski poruszania pobrane z menu
     * @param playerNumber
     */
    public void setKeys(int playerNumber) {
        int keys[] = MenuWindow.getKeys();
        if(playerNumber == 1) setKeys(keys[0],keys[1],keys[2],keys[3]);
        else if(playerNumber == 2) setKeys(keys[4],keys[5],keys[6],keys[7]);
    }

    /**
     * przypisuje wartosci klawiszy
     * @param jumpKey
     * @param leftKey
     * @param rightKey
     * @param shotKey
     */
    private void setKeys(int jumpKey, int leftKey, int rightKey, int shotKey) {
        this.jumpKey = jumpKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.shotKey = shotKey;
    }

    /**
     * porusza zwodnika w lewo jesli ten nie jest zablokowany
     */
    private void movePlayerLeft() {
        if(!isBlockedLeft()) {
            this.x -= speed;
            this.centerHeadX -= speed;
        }
    }

    /**
     * porusza zawodnika w prawo jesli ten nie jest zablokowany
     */
    private void movePlayerRight() {
        if(!isBlockedRight()) {
            this.x += speed;
            this.centerHeadX += speed;
        }
    }
    /**
     * wpisuje wartosc i-tego obrazka do tablicy z obrazkami glow
     */

    private void setHeadImage(int i) {
        try {
            headImage[i] = ImageIO.read(new File("textures/" + name + "/"+ name + "Head" + i + ".png"));
        } catch (IOException e) {
            System.out.println("Could not read the picture");
        }
    }

    /**
     * wpisuje wartosc i-tego obrazka do tablicy z obrazkami tulowiow
     * @param i
     */
    private void setTorsoImage(int i) {
        try {
            torsoImage[i] = ImageIO.read(new File("textures/" + name + "/" + name + "Torso" + i + ".png"));
        } catch (IOException e) {
            System.out.println("Could not read the picture");
        }
    }

    /**
     * pobiera z obrazka szerokosc i wysokosc tulowia
     */
    private void setWidthHeightTorso() {
        final BufferedImage bi;
        try {
            bi = ImageIO.read(new File("textures/" + name + "/" + name + "Torso" + 0 + ".png"));
            this.widthTorso = bi.getWidth();
            this.heightTorso = bi.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  pobiera z obrazka promien glowy
     */
    private void setWidthHeightHead() {
        try {
            final BufferedImage bi = ImageIO.read(new File("textures/" + name + "/" + name + "Head" + 0 + ".png"));
            this.radiusHead = bi.getWidth() / 2;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ustawia wysokosc i szerokosc glowy i tulowia zawodnika
     */
    private void setPlayerWidthHeight() {
        setWidthHeightHead();
        setWidthHeightTorso();
    }

    /**
     * ustawianie wartosci atrybutow pilkarza
     * @param shotStrength
     * @param speed
     * @param maxJump
     */
    private void abilities(int shotStrength, int speed, int maxJump) {
        this.speed = speed;
        this.maxJump = maxJump;
        this.shotStrength = shotStrength;
    }

    /**
     * skakanie piłkarza
     */

    private void jumping() {
        if(jumping) {
            if (getGround() - 2*radiusHead - heightTorso - maxJump < y){
                y-=TERMINAL_VELOCITY;
                centerHeadY -= TERMINAL_VELOCITY;
            }
            else setJumping(false);
        }
    }

    /**
     * Grawitacja dla piłkarzy
     */
    private void falling() {
        if (isNoGround()) {
            fallingVelocity = fallingVelocity + GRAVITY;
            if (fallingVelocity > TERMINAL_VELOCITY) {
                fallingVelocity = TERMINAL_VELOCITY;
            }
            this.y = this.y + fallingVelocity - 10;
            this.centerHeadY = this.centerHeadY + fallingVelocity - 10;
        }
        else fallingVelocity = GRAVITY;
    }

    private boolean isNoGround(){
        return getGround() > y + 2*this.radiusHead+this.heightTorso;
    }

    @Override
    public void run() {
        while (true){
            falling();
            jumping();
            if(isMovingLeft()){
                setBlockedRight(false);
                movePlayerLeft();
            }
            if(isMovingRight()) {
                setBlockedLeft(false);
                movePlayerRight();
            }
            try{
                sleep(15);
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