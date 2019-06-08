package ru.xsd.lettergame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.xsd.lettergame.base.Sprite;
import ru.xsd.lettergame.math.Rect;

public class Background extends Sprite {

    private Rect worldBounds;

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(2f);
        this.worldBounds = worldBounds;
//        pos.set(worldBounds.pos);

        super.resize(worldBounds);
    }

    @Override
    public void update(float deltaTime) {
        pos.mulAdd(new Vector2(0f, -0.05f), deltaTime);
        if (getBottom() <= -3.45f) {
            setBottom(worldBounds.getTop()+0.049f);
        };
        super.update(deltaTime);
    }
}
