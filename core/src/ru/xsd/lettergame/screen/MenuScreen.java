package ru.xsd.lettergame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

import ru.xsd.lettergame.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private SpriteBatch batch;
    private Texture background;

    private Texture frog;

    private Vector2 touch;
    private Vector2 frogPosition;
    private float frogSpeed;
    private Vector2 frogDirection;

    private float sq_distance;
    private float sq_speed;


    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        background = new Texture("background.jpg");
        frog = new Texture("frog.png");
        touch = new Vector2();
        frogPosition = new Vector2(1, 1);
        frogSpeed = 10f;
        frogDirection = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.draw(frog, frogPosition.x, frogPosition.y, 64, 90);
		batch.end();

		sq_distance = frogPosition.dst2(touch);
		sq_speed = frogSpeed * frogSpeed;
//        System.out.println("Distance to point: " + sq_distance + "; frog speed: " + sq_speed);

        if (sq_speed > sq_distance) {
		    frogPosition.set(touch);
        } else {
            frogDirection.x = touch.x - frogPosition.x;
            frogDirection.y = touch.y - frogPosition.y;
            frogDirection.nor();
            frogDirection.scl(frogSpeed);
            frogPosition.add(frogDirection);
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        frog.dispose();
        super.dispose();
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println("touch.x = " + touch.x + " touch.y = " + touch.y);
        return false;
    }
}
