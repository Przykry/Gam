package Game;

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
    private int x,y;
    private int widthTorso, heigthTorso;
    private int widthHead;

    public int getWidthTorso() {
        return widthTorso;
    }

    public int getHeigthTorso() {
        return heigthTorso;
    }

    public int getWidthHead() {
        return widthHead;
    }

    public int getHeigthHead() {
        return heigthHead;
    }

    private int heigthHead;
    private int speed;

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

    private int maxJump;
    private int shotStrength;
    private Image headImage[] = new Image[4];
    private Image torsoImage[] = new Image[4];
    private int jumpKey,leftKey,rightKey,shotKey;

    Image getHeadImage(int i) { return headImage[i];}

    Image getTorsoImage(int i) {
        return torsoImage[i];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    Player(String name, int shotStrength, int speed, int maxJump){
        this.name = name;
        this.x = this.y = 100;
        for(int i = 0; i < 4; i++) {
            setHeadImage(i);
            setTorsoImage(i);
        }
        abilities(shotStrength,speed,maxJump);
        setPlayerWidthHeight();
    }

    public void setKeys(int playerNumber){
        if(playerNumber == 1) getKeys(VK_UP,VK_LEFT,VK_RIGHT,VK_0);
        else if(playerNumber == 2) getKeys(VK_W,VK_A,VK_D, VK_ALT);
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
            this.widthHead = bi.getWidth();
            this.heigthHead = bi.getHeight();
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

    public void setPlayerPositionX(int directory){
        if(directory == 1) {
            x = x + speed;
        }
        else this.x -= speed;
    }


    public void setPlayerPositionY(int directory){
        if(directory == 0) this.y += maxJump;
        else this.y -= maxJump;
    }

}