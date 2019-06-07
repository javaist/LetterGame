package ru.xsd.lettergame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.xsd.lettergame.base.Sprite;
import ru.xsd.lettergame.math.Rect;

public class Catcher extends Sprite {

    private Vector2 catcherPosition;
    private float catcherSpeed;
    private Vector2 catcherDirection;

    private float sq_distance;
    private float sq_speed;


    public Catcher(TextureRegion region) {
        super(region);
        catcherPosition = new Vector2(getLeft(), getBottom());
        catcherSpeed = 0.005f;
        catcherDirection = new Vector2();

        regions[0].getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
        pos.set(worldBounds.pos);

        super.resize(worldBounds);
    }

    public void draw(SpriteBatch batch, Vector2 touch) {
        sq_distance = catcherPosition.dst2(touch);
        sq_speed = catcherSpeed * catcherSpeed;
//        System.out.println("Distance to point: " + sq_distance + "; catcherTexture speed: " + sq_speed);

        if (sq_speed > sq_distance) {
            catcherPosition.set(touch);
        } else {
            catcherDirection.x = touch.x - catcherPosition.x;
            catcherDirection.y = touch.y - catcherPosition.y;
            catcherDirection.nor();
            catcherDirection.scl(catcherSpeed);
            catcherPosition.add(catcherDirection);
        }

        setLeft(catcherPosition.x);
        setBottom(catcherPosition.y);

        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale, angle
        );
    }
}
