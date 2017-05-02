package Game.Entities;

import Game.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.event.KeyEvent.*;

/**
 * Created by Przykry on 26.04.2017.
 */
public class Player {
    String name;
    private int x;
    private int y;
    private int centerHeadX;
    private int centerHeadY;
    private int widthTorso, heigthTorso;
    private int radiusHead;
    private int speed;
    private int maxJump;
    private int shotStrength;
    private Image headImage[] = new Image[4];
    private Image torsoImage[] = new Image[4];
    private int jumpKey;
    private int leftKey;
    private final static int TERMINAL_VELOCITY = 15;
    private final static int GRAVITY = 1;
    private int fallingVelocity = GRAVITY;

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

    private int rightKey;
    private int shotKey;
    private boolean movingLeft, movingRight, jumping;

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

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public Player(int x, int y, String name, int shotStrength, int speed, int maxJump) {
        this.name = name;
        this.x = x;
        this.y = y;
        for (int i = 0; i < 4; i++) {
            setHeadImage(i);
            setTorsoImage(i);
        }
        abilities(shotStrength, speed, maxJump);
        setPlayerWidthHeight();
        this.movingLeft = false;
        this.movingRight = false;
        this.jumping = false;
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

    public int getCenterHeadY() {
        return centerHeadY;
    }

    public int getWidthTorso() {
        return widthTorso;
    }

    public int getHeigthTorso() {
        return heigthTorso;
    }

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
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setKeys(int playerNumber) {
        if (playerNumber == 1) getKeys(VK_W, VK_A, VK_D, VK_ALT);
        else if (playerNumber == 2) getKeys(VK_UP, VK_LEFT, VK_RIGHT, VK_0);
    }

    private void getKeys(int jumpKey, int leftKey, int rightKey, int shotKey) {
        this.jumpKey = jumpKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.shotKey = shotKey;

    }

    public void movePlayerLeft() {
        this.x -= speed;
    }

    public void movePlayerRight() {
        this.x += speed;
    }

    private void setHeadImage(int i) {
        try {
            headImage[i] = ImageIO.read(new File("textures\\" + name + "Head" + i + ".png"));
        } catch (IOException e) {
            System.out.println("Could not read the picture");
        }
    }

    private void setTorsoImage(int i) {
        try {
            torsoImage[i] = ImageIO.read(new File("textures\\" + name + "Torso" + i + ".png"));
        } catch (IOException e) {
            System.out.println("Could not read the picture");
        }
    }

    private void setWidthHeightTorso() {
        final BufferedImage bi;
        try {
            bi = ImageIO.read(new File("textures\\" + name + "Torso" + 0 + ".png"));
            this.widthTorso = bi.getWidth();
            this.heigthTorso = bi.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setWidthHeightHead() {
        try {
            final BufferedImage bi = ImageIO.read(new File("textures\\" + name + "Head" + 0 + ".png"));
            this.radiusHead = bi.getWidth() / 2;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPlayerWidthHeight() {
        setWidthHeightHead();
        setWidthHeightTorso();
    }


    private void abilities(int shotStrength, int speed, int maxJump) {
        this.speed = speed;
        this.maxJump = maxJump;
        this.shotStrength = shotStrength;
    }

    public void jumping() {
        if(jumping) {
            if (Main.getGameWindow().getHeight() - 15 - getHeigthTorso() - (2 * getRadiusHead()) - maxJump < y) y-=TERMINAL_VELOCITY + fallingVelocity;
            else setJumping(false);
        }
    }

    public int getGround(){
        return Main.getGameWindow().getHeight() - 15 - getHeigthTorso() - (2 * getRadiusHead());
    }

    public void falling() {
        if (getGround() > y) {
            fallingVelocity = fallingVelocity + GRAVITY;
            if (fallingVelocity > TERMINAL_VELOCITY) {
                fallingVelocity = TERMINAL_VELOCITY;
            }
            this.y = this.y + 2;
        }
        else fallingVelocity = GRAVITY;
    }


}