package ru.geekbrains.stargame.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.Bullet.BulletPool;
import ru.geekbrains.stargame.engine.math.Rect;


public class EnemyShip extends Ship {

    private static final float SHIP_HEIGHT = 0.15f;
    private static final float TOP_MARGIN = 0.08f;

    private final Vector2 v0 = new Vector2(0.0f, 0.1f);

    public EnemyShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("enemy1"), 1, 2, 2);
        setHeightProportion(SHIP_HEIGHT);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.bulletHeight = 0.02f;
        this.bulletV.set(0, -0.5f);
        this.bulletDamage = 1;
        this.reloadInterval = 0.6f;
        this.soundBullet = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
    }

   @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
//        reloadTimer += delta;
//        if (reloadTimer >= reloadInterval) {
//            reloadTimer = 0f;
//            shoot();
//        }
        moveDown();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setTop(worldBounds.getTop() - TOP_MARGIN);
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveDown() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

    public Vector2 getV() {
        return v;
    }
}
