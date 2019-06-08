package ru.xsd.lettergame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.xsd.lettergame.base.Sprite;
import ru.xsd.lettergame.math.Rect;

public class Background extends Sprite {
    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(2f);
        pos.set(worldBounds.pos);

        super.resize(worldBounds);
    }
}
