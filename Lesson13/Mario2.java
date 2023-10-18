package Lesson13;

public class Mario2 extends Entity {
    int score;
    int lives;
    boolean movingUp;
    Mario2(MarioGame2HW s) {
        panel = s;
        width = 6;
        height = 8;
        imageFile = "mario-running.gif";
        speed = 2;//y increase by this
        score = 0;
        y = 50 / 2;
        x = 80 / 3;
    }

    void move() {
        if (movingUp)
            y = Math.max(y - speed, 0);
        else
            y = Math.min(y + speed, panel.getHeight()/gridWidth - height);
    }
}



