package com.frida.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.frida.game.PongGame;
import com.frida.game.sprites.Ball;
import com.frida.game.sprites.Player;

public class PlayState extends State{
    private Ball ball;
    private Sprite sprite;
    private Player p1;
    private Player p2;
    private Sprite s1;
    private Sprite s2;
    private BitmapFont font1 = new BitmapFont();
    private BitmapFont font2 = new BitmapFont();
    private String string1 = "P1 score: ";
    private String string2 = "P2 score: ";


    public PlayState(GameStateManager gsm, int scoreP1, int scoreP2) {
        super(gsm);
        ball = new Ball(PongGame.WIDTH/2, PongGame.HEIGHT/2);
        sprite = new Sprite(ball.getTexture());
        sprite.flip(true, false);
        p1 = new Player(300, 540);
        p2 = new Player(1730, 540);
        s1 = new Sprite(p1.getTexture());
        s2 = new Sprite(p2.getTexture());
        p1.setScore(scoreP1);
        p2.setScore(scoreP2);
        cam.setToOrtho(false, PongGame.WIDTH, PongGame.HEIGHT);
        font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font1.getData().setScale(2);
        font2.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font2.getData().setScale(2);
    }

    @Override
    public void handleInput(float dt) {
        if (Gdx.input.isTouched()) {
            float xTouchPixels = Gdx.input.getX();
            float yTouchPixels = Gdx.input.getY();

            if (xTouchPixels < 1015) {
                p1.update(dt, xTouchPixels, yTouchPixels);
            }
            if (xTouchPixels > 1015) {
                p2.update(dt, xTouchPixels, yTouchPixels);
            }
        }
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
        ball.update(dt);
        sprite.setTexture(ball.getTexture());
        sprite.setPosition(ball.getPosition().x, ball.getPosition().y);
        s1.setPosition(p1.getPosition().x, p1.getPosition().y);
        s2.setPosition(p2.getPosition().x, p2.getPosition().y);
        if (gamePointp1()) {
            p1.setScore(p1.getScore() + 1);
            if (p1.getScore() == 21) { // game over
                MenuState menu = new MenuState(gsm, 0, 0);
                gsm.set(menu);
                menu.setStartString("P1 Win!!");
                dispose();
            }
            else {
                MenuState menu = new MenuState(gsm, p1.getScore(), p2.getScore());
                gsm.set(menu);
                menu.setStartString("Point to P1");
                dispose();
            }
        }
        if (gamePointp2()) {
            p2.setScore(p2.getScore() + 1);
            if (p2.getScore() == 21) { // game over
                MenuState menu = new MenuState(gsm, 0, 0);
                gsm.set(menu);
                menu.setStartString("P2 Win!!");
                dispose();
            }
            else {
                MenuState menu = new MenuState(gsm, p1.getScore(), p2.getScore());
                gsm.set(menu);
                menu.setStartString("Point to P2");
                dispose();
            }
        }
        collisionDetection();
    }

    public void collisionDetection() {
        if (sprite.getBoundingRectangle().overlaps(s2.getBoundingRectangle())) {
            if (p2.getPosition().x < ball.getPosition().x + ball.getTexture().getWidth() - 10) {
                return;
            }
            ball.changeDirection(true);
        }
        if (sprite.getBoundingRectangle().overlaps(s1.getBoundingRectangle())) {
            if (p1.getPosition().x > ball.getPosition().x + 20) {
                return;
            }
            ball.changeDirection(true);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        draw(sb);
    }

    @Override
    public void dispose() {
        font1.dispose();
        font2.dispose();
    }

    private void draw(SpriteBatch sb) {
        sb.begin();
        sprite.draw(sb);
        s1.draw(sb);
        s2.draw(sb);
        font1.draw(sb, string1 + p1.getScore(), 40, 1060);
        font2.draw(sb, string2 + p2.getScore(), 1830, 1060);
        sb.end();
    }

    private Boolean gamePointp1() {
        if (ball.getPosition().x == PongGame.WIDTH - 30) {
            return true;
        }
        return false;
    }

    private Boolean gamePointp2() {
        if (ball.getPosition().x == 0) {
            return true;
        }
        return false;
    }

    private Boolean gameOver() {
        return false;
    }
}
