package Lesson13;

import java.awt.*;

public class Entity {
    int gridWidth=10;
    int width, height; //pixel
    String imageFile;
    int speed; //vertical speed
    int x, y;
    MarioGame2HW panel;
    void show(Graphics g){
        Image img = Toolkit.getDefaultToolkit().getImage(imageFile);
        g.drawImage(img, x*gridWidth, y*gridWidth, width*gridWidth, height*gridWidth, panel);
    }
}
