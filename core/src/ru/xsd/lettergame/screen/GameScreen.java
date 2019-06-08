package ru.xsd.lettergame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.xsd.lettergame.base.BaseScreen;
import ru.xsd.lettergame.math.Rect;
import ru.xsd.lettergame.pool.BulletPool;
import ru.xsd.lettergame.sprite.Background;
import ru.xsd.lettergame.sprite.ButtonExit;
import ru.xsd.lettergame.sprite.Catcher;
import ru.xsd.lettergame.sprite.Star;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private Game game;

    private Texture backgroundTexture;
    private Background background;
    private Background background2;

    private Texture catcherTexture;
    private Catcher catcher;

    private TextureAtlas menuAtlas;
    private TextureAtlas atlas;
    private Star[] starArray;

    private Music music;
    private Sound bulletSound;

    private BulletPool bulletPool;

    private ButtonExit buttonExit;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("textures/background.jpg");
        background = new Background(new TextureRegion(backgroundTexture));
        background2 = new Background(new TextureRegion(backgroundTexture));
        background.setTop(0f);
        background2.setTop(2f);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();

//        catcherTexture = new Texture("frog.png");

        atlas = new TextureAtlas("textures/mainAtlas.tpack");

        starArray = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            starArray[i] = new Star(atlas);
        }

        bulletPool = new BulletPool();
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));

        catcher = new Catcher(atlas, bulletPool, bulletSound);

        menuAtlas = new TextureAtlas("textures/menuAtlas.tpack");
        buttonExit = new ButtonExit(menuAtlas, game);
    }

    @Override
    public void update(float delta) {
        background.update(delta);
        background2.update(delta);
        for (Star star : starArray) {
            star.update(delta);
        }
        bulletPool.updateActiveSprites(delta);

        catcher.update(delta);

        freePools();
    }

    private void freePools(){
        bulletPool.freeActiveSprites();
    }

    @Override
    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        background.draw(batch);
        background2.draw(batch);
        for (Star star: starArray) {
            star.draw(batch);
        }
        bulletPool.drawActiveSprites(batch);
        catcher.draw(batch);

        buttonExit.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        background2.resize(worldBounds);
        for (Star star : starArray) {
            star.resize(worldBounds);
        }
        catcher.resize(worldBounds);

        buttonExit.resize(worldBounds);
        super.resize(worldBounds);
    }

    @Override
    public void dispose() {
        atlas.dispose();
        menuAtlas.dispose();
        backgroundTexture.dispose();
        bulletPool.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        catcher.keyDown(keycode);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        catcher.keyUp(keycode);
        return super.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        return super.keyTyped(character);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        catcher.touchDown(touch, pointer);
        buttonExit.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        buttonExit.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        catcher.touchDragged(touch, pointer);
        return super.touchDragged(touch, pointer);
    }
}
