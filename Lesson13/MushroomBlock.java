package Lesson13;

public class MushroomBlock extends Entity{
    MushroomBlock(MarioGame2HW s) {
        panel = s;
        width = 5;
        height = 6;
        imageFile = "Mushroom block.png";
        speed = 5;
        x = 80;
        y = (int)(Math.random()*50);
    }
    void move(){
        x -= speed;
    }
}
