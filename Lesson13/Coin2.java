package Lesson13;

public class Coin2 extends Entity {
    Coin2(MarioGame2HW s) {
        panel = s;
        width = 3;
        height = 3;
        imageFile = "mario_coin2.gif";
        speed = 2;
        x = 80;
        y = (int)(Math.random()*50);
    }
    void move(){
        x -= speed;
    }
}
