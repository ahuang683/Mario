package Lesson13;

public class NormalBlocks extends Entity {
    NormalBlocks(MarioGame2HW s) {
        panel = s;
        width = 5;
        height = 6;
        imageFile = "block.png";
        speed = 5;
        x = 80;
        y = (int)(Math.random()*50);

    }
    void move(){
        x -= speed;

    }
}
