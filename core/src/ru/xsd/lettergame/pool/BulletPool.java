package ru.xsd.lettergame.pool;

import ru.xsd.lettergame.base.SpritePool;
import ru.xsd.lettergame.sprite.Bullet;

public class BulletPool extends SpritePool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
