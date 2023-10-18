package Lesson13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MarioGame2HW extends JPanel implements Runnable {
    Mario2 m;
    ArrayList<Coin2> coins; //declaration
    ArrayList<Koopa> koopas; //declaration
    ArrayList<MushroomBlock> MB;
    ArrayList<NormalBlocks> NB;
    boolean GameDone;
    int U=0;
    MarioGame2HW(){

        m = new Mario2(this);
        coins = new ArrayList<Coin2>(); //initialize it to empty
        koopas = new ArrayList<>(); //initialize it to empty
        MB = new ArrayList<>();
        NB = new ArrayList<>();
        setFocusable(true);
        KeyAdapter myKeyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode()==KeyEvent.VK_SPACE)
                    m.movingUp = true;
            }

            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode()==KeyEvent.VK_SPACE)
                    m.movingUp = false;
            }
        };
        addKeyListener(myKeyAdapter);
    }


    public void paintComponent (Graphics g){
        int BlocksY=0;
        super.paintComponent(g);
        m.show(g);

        for (Coin2 c: coins) {
            c.show(g);
        }
        for (Koopa k: koopas) {
            k.show(g);
        }
        for (NormalBlocks n:NB){
            n.show(g);
            Image img = Toolkit.getDefaultToolkit().getImage("block.png");
            for(int i =0; i<=U; i++){
                g.drawImage(img, n.x*10+i*n.width*10, n.y*10, n.width*10, n.height*10, this);
            }
            BlocksY=n.y;

        }
        for(MushroomBlock mb: MB){
            Image img = Toolkit.getDefaultToolkit().getImage("Mushroom block.png");
            g.drawImage(img, mb.x*10, BlocksY*10, mb.width*10, mb.height*10, this);
        }

        g.drawString(m.score+"",20,20);
        g.drawString("lives: "+m.lives+"",450,20);
        if(GameDone){
            g.drawString("GAME OVER",250,250);
        }
    }
    //play sound function
    void playSound(String soundFileName){
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File(soundFileName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();   // create clip reference
            clip.open(audioInputStream);    // open audioInputStream to the clip
            clip.loop(0);
        } catch (Exception exception ) { }

    }
    int randomPercent(){
        int result = (int) (Math.random()*100);
        return result;
    }


    public void run(){
        int counter = 0;
        while(true){
            //tell the computer to pause (sleep)
            m.move();
            for (Coin2 c: coins)
                c.move();
            for (Koopa k: koopas)
                k.move();
            for (NormalBlocks n: NB){
                n.move();
}
            for(MushroomBlock mb: MB){
                mb.move();
            }
            //generate a coin
            if (counter%1000==0){
                Coin2 c = new Coin2(this);
                coins.add(c);
            }
            if (counter%1000==0){
                Koopa k = new Koopa(this);
                koopas.add(k);
            }
            if(counter%2000==0){
                NormalBlocks n = new NormalBlocks(this);
                NB.add(n);

                U=(int)(Math.random()*10);

            }
            if(counter%2000==0){
                MushroomBlock mb = new MushroomBlock(this);
                MB.add(mb);

                U=(int)(Math.random()*10);
            }
            counter+=100;
            //detect collision
            for( int i=0; i<coins.size();i++){
                Coin2 c = coins.get(i);
                if(checkOverlap (m,c)){
                        playSound("Mario Coin sound.mp3");
                        m.score+=10;
                        coins.remove(c);
                        i--;
                }
            }
            for( int i = 0; i<koopas.size(); i++){
                Koopa k = koopas.get(i);
                if(checkOverlap(m,k)){
                    m.lives--;
                    koopas.remove(k);
                    i--;
                }
            }
            if(m.score==30){
                m.lives++;
                m.score=0;
            }
            if(m.lives<0){
                GameDone = true;
            }
            if(GameDone){
                break;
            }
            repaint();
            try {
                Thread.sleep(100); //milliseconds
            } catch (InterruptedException e) {
            }
        }
    }
    static boolean checkOverlap (Entity e1, Entity e2){
        if(e1.x>e2.x+e2.width){
            return false;
        }
        if(e1.x+e1.width<e2.x){
            return false;
        }
        if(e1.y+e1.height<e2.y){
            return false;
        }
        if(e1.y>e2.y+e2.height){
            return false;
        }
        return true;
    }
    public static void main (String argv[]) {
        JFrame myFrame = new JFrame("MarioGame1 HW");
        MarioGame2HW myPanel = new MarioGame2HW();
        myPanel.setPreferredSize(new Dimension(800, 500));
        myFrame.add(myPanel);
        myFrame.pack();
        myFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        new Thread(myPanel).start();
    }
}

