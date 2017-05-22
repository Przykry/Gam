package Game.Windows;

import Game.ButtonListeners.ButtonListeners.Menu.AcceptMenuButtonListener;
import Game.ButtonListeners.BackButtonListener;
import Game.ButtonListeners.ButtonListeners.Menu.MenuActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Przykry on 29.04.2017.
 */
public class MenuWindow extends JPanel implements WindowInt, ActionListener {
    private int width,heigth;
    private Image backgroundImage;
    private static int[] keys = new int[8];

    private JButton [] buttons = new JButton[]{
            new JButton(),
            new JButton()
    };

    private JButton [] keySwitchers = new JButton[]{
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton()
    };

    private String [] filepaths = new String[]{
            "textures\\Buttons\\chooseButtons.png",
            "textures\\Buttons\\keySwitcher.png",
            "textures\\Buttons\\keySwitcher1.png"
    };

    private ActionListener[] listeners = new ActionListener[]{
            new BackButtonListener(),
            new AcceptMenuButtonListener(),
    };


    public MenuWindow(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
        for(int i = 0; i < buttons.length; i++){
            createButton(buttons[i],125+430*i,450,filepaths[0],listeners[i]);
            if(i ==0) buttons[i].setText("Back");
            else buttons[i].setText("Accept");
            this.add(buttons[i]);
        }

        try {
            this.backgroundImage = getBackgroundImage("mainBackground");
            setKeys();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
           this.backgroundImage = Toolkit.getDefaultToolkit().createImage("background.jpg");
        }

        keySwitchers();
        createPlayerLabel(keySwitchers[8],105,"Player 1");
        createPlayerLabel(keySwitchers[9],535,"Player 2");
        this.setFocusable(true);
        this.setLayout(null);
    }

    private void keySwitchers(){
        for(int i=0;i<4;i++){
            createButton(keySwitchers[i],135,200+50*i,filepaths[1], new MenuActionListener(this,i));
            createButton(keySwitchers[i+4],565,200+50*i,filepaths[1],new MenuActionListener(this,i+4));
            keySwitchers[i].setFont(new Font("Hobo Std",Font.ITALIC,24));
            keySwitchers[i+4].setFont(new Font("Hobo Std",Font.ITALIC,24));
            setTextMovementKey(keySwitchers[i],keys[i]);
            setTextMovementKey(keySwitchers[i+4],keys[i+4]);
            this.add(keySwitchers[i]);
            this.add(keySwitchers[i+4]);
        }
    }



    public boolean switcherClicked(int i){
        return keySwitchers[i].getModel().isEnabled();
    }

    public JButton getKeySwitcher(int i){
        return keySwitchers[i];
    }

    private void createPlayerLabel(JButton player, int x, String text){
        createButton(player,x,150,filepaths[2],null);
        player.setText(text);
        this.add(player);
    }


    private void menuDescription(Graphics graphics){
        graphics.setColor(Color.decode("#2A2C2E"));
        graphics.setFont(new Font("Hobo Std", Font.PLAIN , 32));
        graphics.drawString(" <Jump>",375,235);
        graphics.drawString(" <Left>",375,235 + 50);
        graphics.drawString("<Right>",375,235 + 2 * 50);
        graphics.drawString(" <Shot>",375,235 + 3 * 50);

    }


    private void setTextMovementKey(JButton button, int key){
        button.setText(String.valueOf(KeyEvent.getKeyText(key)));
    }

    private void setKeys() throws FileNotFoundException {
        File file = new File("textures\\KeyBindings.txt");
        Scanner scr = new Scanner(file);
        for(int i=0;i<8;i++) {
            keys[i] = scr.nextInt();
        }
    }

    public static int[] getKeys(){
        return keys;
    }

    public void paintComponent(Graphics g){
     drawBackground(g,backgroundImage,this);
     drawBorders(g,width,heigth);
     menuDescription(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
