package com.frida.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.frida.game.PongGame;

public class Player {
    private Vector3 position;
    private Vector3 velocity;
    private TextureRegion player;
    private int score = 0;

    public Player(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(100, 80, 0);
        player = new TextureRegion(new Texture("block.png"), 200, 200, 10, 70);
    }

    public void update(float dt, float xPos, float yPos) {
        if (xPos < 0 && yPos < 0) {
            return;
        }
        position.y = 1050 - yPos - player.getRegionHeight()/2;

        if (position.y < 0) {
            position.y = 0;
        }

        if (position.y > PongGame.HEIGHT - player.getRegionHeight()) {
            position.y = PongGame.HEIGHT - player.getRegionHeight();
        }
        return;
    }

    public Boolean collidesWith(Ball h) {
        return this.position.x < h.getPosition().x + h.getTexture().getWidth() &&
                this.position.y < h.getPosition().y + h.getTexture().getHeight() &&
                this.position.x + this.getTexture().getRegionWidth() > h.getPosition().x &&
                this.position.y + this.getTexture().getRegionHeight() > h.getPosition().y;
    }

    public TextureRegion getTexture() {
        return player;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void flipVelocity() {
        velocity.x = - (float) (100);
        velocity.y = - (float) (100);
    }

    public int getScore(){
        return score;
    }

    public void setScore(int s) { score = s;}
}
