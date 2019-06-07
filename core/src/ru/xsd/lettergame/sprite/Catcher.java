package ru.xsd.lettergame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.xsd.lettergame.base.Sprite;
import ru.xsd.lettergame.math.Rect;

public class Catcher extends Sprite {

    private Rect worldBounds;

    private Vector2 touchPosition;
    private float catcherSpeed;
    private Vector2 catcherDirection;

    private float sq_distance;
    private float sq_speed;


    public Catcher(TextureRegion region) {
        super(region);
        regions[0].getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        touchPosition = pos.cpy();
        catcherSpeed = 0.005f;
        catcherDirection = new Vector2();


    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
        this.worldBounds = worldBounds;
        pos.set(worldBounds.pos);
        super.resize(worldBounds);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale, angle
        );
    }

    @Override
    public void update(float deltaTime) {
        System.out.println(catcherDirection);
        if (touchPosition != null) {
            sq_distance = pos.dst2(touchPosition);
            sq_speed = catcherSpeed * catcherSpeed;
//        System.out.println("Distance to point: " + sq_distance + "; catcherTexture speed: " + sq_speed);

            if (sq_speed > sq_distance) {
                pos.set(touchPosition);
                catcherDirection.scl(0f);
                touchPosition = null;
            } else {
                catcherDirection.x = touchPosition.x - pos.x;
                catcherDirection.y = touchPosition.y - pos.y;
                catcherDirection.nor();
                catcherDirection.scl(catcherSpeed);
                pos.add(catcherDirection);
            }
        } else {
            catcherDirection.nor();
            catcherDirection.scl(catcherSpeed);
            pos.add(catcherDirection);
        }
        if (getLeft() < worldBounds.getLeft()) {
            System.out.println("here");
            setLeft(worldBounds.getLeft());
        } else if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
        }
        if (getBottom() < worldBounds.getBottom()) {
            setBottom(worldBounds.getBottom());
        } else if (getTop() + 0.15f > worldBounds.getTop()) {
            setTop(worldBounds.getTop() - 0.15f);
        }

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        touchPosition = touch.cpy();
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        touchPosition = touch.cpy();
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        touchPosition = null;
        if (keycode == 19) {
            catcherDirection.y = 1f;
        } else if (keycode == 20) {
            catcherDirection.y = -1f;
        } else if (keycode == 21) {
            catcherDirection.x = -1f;
        } else if (keycode == 22) {
            catcherDirection.x = 1f;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == 19 || keycode == 20) {
            catcherDirection.y = 0f;
        } else if (keycode == 21 || keycode == 22) {
            catcherDirection.x = 0f;
        }
        return false;
    }
}
