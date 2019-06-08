package ru.xsd.lettergame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.xsd.lettergame.base.Sprite;
import ru.xsd.lettergame.math.Rect;
import ru.xsd.lettergame.math.Rnd;

public class Star extends Sprite {

    private Vector2 speed;
    private Rect worldBounds;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        speed = new Vector2(Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f));
        setHeightProportion(0.01f);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posX, posY);
    }

    @Override
    public void update(float deltaTime) {
        pos.mulAdd(speed, deltaTime);
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        } else if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
    }
}
