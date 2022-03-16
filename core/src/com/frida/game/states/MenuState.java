package com.frida.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.frida.game.sprites.Player;

public class MenuState extends State {

    private Texture playbutton; // heter playbutton men man kan trykke på hele skjermen for å starte
    private Player p1;
    private Player p2;
    private Sprite s1;
    private Sprite s2;
    private BitmapFont font1 = new BitmapFont();
    private BitmapFont font2 = new BitmapFont();
    private String string1 = "P1 score: ";
    private String string2 = "P2 score: ";
    private BitmapFont startFont = new BitmapFont();
    private String startString = "Click screen to play";


    public MenuState(GameStateManager gsm, int scoreP1, int scoreP2) {
        super(gsm);
        Pixmap pixmap200 = new Pixmap(Gdx.files.internal("start.png"));
        Pixmap pixmap100 = new Pixmap(150, 100, pixmap200.getFormat());
        pixmap100.drawPixmap(pixmap200,
                0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
                0, 0, pixmap100.getWidth(), pixmap100.getHeight()
        );
        playbutton = new Texture(pixmap100);
        pixmap200.dispose();
        pixmap100.dispose();
        p1 = new Player(300, 540);
        p2 = new Player(1730, 540);
        s1 = new Sprite(p1.getTexture());
        s2 = new Sprite(p2.getTexture());
        p1.setScore(scoreP1);
        p2.setScore(scoreP2);
        font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font1.getData().setScale(2);
        font2.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font2.getData().setScale(2);
        startFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        startFont.getData().setScale(2);
        s1.setPosition(p1.getPosition().x, p1.getPosition().y);
        s2.setPosition(p2.getPosition().x, p2.getPosition().y);
    }

    @Override
    protected void handleInput(float dt) {
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm, p1.getScore(), p2.getScore()));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(playbutton, 940, 520);
        s1.draw(sb);
        s2.draw(sb);
        font1.draw(sb, string1 + p1.getScore(), 40, 1060);
        font2.draw(sb, string2 + p2.getScore(), 1830, 1060);
        startFont.draw(sb, startString, 950, 200);
        sb.end();
    }

    public void dispose() {
        playbutton.dispose();
        font1.dispose();
        font2.dispose();
    }

    public void setStartString(String s) {
        startString = s;
    }
}
