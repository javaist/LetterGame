package ru.xsd.lettergame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.xsd.lettergame.base.Sprite;
import ru.xsd.lettergame.math.Rect;

public class Catcher extends Sprite {

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
        sq_distance = pos.dst2(touchPosition);
        sq_speed = catcherSpeed * catcherSpeed;
//        System.out.println("Distance to point: " + sq_distance + "; catcherTexture speed: " + sq_speed);

        if (sq_speed > sq_distance) {
            pos.set(touchPosition);
        } else {
            catcherDirection.x = touchPosition.x - pos.x;
            catcherDirection.y = touchPosition.y - pos.y;
            catcherDirection.nor();
            catcherDirection.scl(catcherSpeed);
            pos.add(catcherDirection);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        touchPosition = touch.cpy();
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        System.out.println(touch);
        touchPosition = touch.cpy();
        return false;
    }
}
