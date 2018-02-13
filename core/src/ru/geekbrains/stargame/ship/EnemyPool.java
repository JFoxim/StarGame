package ru.geekbrains.stargame.ship;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.stargame.Bullet.BulletPool;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.pool.SpritesPool;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final TextureAtlas atlas;
    private BulletPool bulletPool;
    //private  Rect worldBounds;

    public EnemyPool(TextureAtlas atlas, BulletPool bulletPool) {
        this.atlas = atlas;
        this.bulletPool = bulletPool;
        //this.worldBounds = worldBounds;
    }
    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(atlas, bulletPool);
    }

    public void resize(Rect worldBounds){
        super.resize(worldBounds);
    }

}
