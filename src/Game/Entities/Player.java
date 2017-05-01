package Game.Entities;

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
    private int jumpKey,leftKey,rightKey,shotKey;

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

    public Image getHeadImage(int i) { return headImage[i];}

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

    public Player(int x, int y, String name, int shotStrength, int speed, int maxJump){
        this.name = name;
        this.x = x;
        this.y = y;
        for(int i = 0; i < 4; i++) {
            setHeadImage(i);
            setTorsoImage(i);
        }
        abilities(shotStrength,speed,maxJump);
        setPlayerWidthHeight();
    }

    public void setKeys(int playerNumber){
        if(playerNumber == 1) getKeys(VK_W,VK_A,VK_D, VK_ALT);
        else if(playerNumber == 2) getKeys(VK_UP,VK_LEFT,VK_RIGHT,VK_0);
    }

    private void getKeys(int jumpKey, int leftKey, int rightKey, int shotKey){
        this.jumpKey = jumpKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.shotKey = shotKey;
    }

    private void setHeadImage(int i){
        try {
            headImage[i] = ImageIO.read(new File("textures\\" +  name + "Head" + i + ".png"));
        }
        catch (IOException e) {
            System.out.println("Could not read the picture");
        }
    }

    private void setTorsoImage(int i){
        try {
            torsoImage[i] = ImageIO.read(new File("textures\\" +  name + "Torso" + i + ".png"));
        }
        catch (IOException e) {
            System.out.println("Could not read the picture");
        }
    }
    private void setWidthHeightTorso(){
        final BufferedImage bi;
        try {
            bi = ImageIO.read(new File("textures\\" +  name + "Torso" + 0 + ".png"));
            this.widthTorso = bi.getWidth();
            this.heigthTorso = bi.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setWidthHeightHead(){
        try {
            final BufferedImage bi = ImageIO.read(new File("textures\\" +  name + "Head" + 0 + ".png"));
            this.radiusHead = bi.getWidth();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPlayerWidthHeight(){
        setWidthHeightHead();
        setWidthHeightTorso();
    }


    private void abilities(int shotStrength, int speed, int maxJump){
            this.speed = speed;
            this.maxJump = maxJump;
            this.shotStrength = shotStrength;
    }

    public void movePlayerX(int directory){
        if(directory == 1) {
            x = x + speed;
        }
        else this.x -= speed;
    }


    public void movePlayerY(int directory){
        if(directory == 0) this.y += maxJump;
        else this.y -= maxJump;
    }

}