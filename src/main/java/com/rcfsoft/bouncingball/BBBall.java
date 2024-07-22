package com.rcfsoft.bouncingball;

import lombok.Getter;

import java.util.Random;

public class BBBall {
    @Getter
    double x;
    @Getter
    double y;
    double vx;
    double vy;
    double ax = 0.0;
    double ay = 0.25;
    BBBall() {
        Random random = new Random();
        x = BB.X_SIZE / 2;
        y = BB.Y_SIZE / 2;
        vx = random.nextDouble(11.0) - 5.0;
        vy = random.nextDouble(11.0) - 15.0;
    }
    public void delta() {
        vx = vx + ax;
        vy = vy + ay;
        x = x + vx;
        y = y + vy;
        if(x < BB.BALL_RADIUS || x >= BB.X_SIZE - BB.BALL_RADIUS) {
            x = x - vx;
            vx = -vx * 0.8;
        }
        if(y < BB.BALL_RADIUS || y >= BB.Y_SIZE - BB.BALL_RADIUS) {
            y = y - vy;
            vy = -vy * 0.8;
        }

    }
}
