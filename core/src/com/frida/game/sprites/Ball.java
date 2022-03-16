package com.frida.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.frida.game.PongGame;

import java.util.Random;

public class Ball {
    private Vector3 position;
    private Vector3 velocity;
    private int difficultyCount = 0; // høyner vanskeligheten når telleren når ballen har blitt fanget et gitt antall ganger

    private Texture ball;

    public Ball(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(7, 7, 0);
        Random random = new Random();
        velocity.x = velocity.x * (random.nextInt(2) == 0 ? -1 : 1);
        velocity.y = velocity.y * (random.nextInt(2) == 0 ? -1 : 1);
        Pixmap pixmap200 = new Pixmap(Gdx.files.internal("ball.png"));
        Pixmap pixmap100 = new Pixmap(30, 30, pixmap200.getFormat());
        pixmap100.drawPixmap(pixmap200,
                0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
                0, 0, pixmap100.getWidth(), pixmap100.getHeight()
        );
        ball = new Texture(pixmap100);
        pixmap200.dispose();
        pixmap100.dispose();
    }

    public void update(float dt) {
        ball = getTexture();
        position.add(velocity.x, velocity.y, 0);

        if (position.y < 0) {
            position.y = 0;
            changeDirection(false);
        }

        if (position.x < 0) {
            position.x = 0;
            changeDirection(true);
        }

        if (position.x > PongGame.WIDTH-150) {
            position.x = PongGame.WIDTH - ball.getWidth();
            changeDirection(true);
        }

        if (position.y > PongGame.HEIGHT-70) {
            position.y = PongGame.HEIGHT - 70;
            changeDirection(false);
        }
        if (difficultyCount == 8) {
            velocity.add(3, 0, 0);
            difficultyCount = 0;
        }

    }

    public void changeDirection(Boolean f) {
        if (f) {
            velocity.x = - velocity.x;
            difficultyCount += 1;
        }
        else {
            velocity.y = - velocity.y;
        }
    }

    public Texture getTexture() {
        return ball;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }
}
