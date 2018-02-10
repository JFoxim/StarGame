package ru.geekbrains.stargame.Bullet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.pool.SpritesPool;
import ru.geekbrains.stargame.ship.MainShip;

public class BulletPool <T extends Bullet> extends SpritesPool {

    // список активных объектов
    protected final List<Bullet> activeObjects = new LinkedList<Bullet>();

    // список свободных объектов
    protected final List<Bullet> freeObjects = new ArrayList<Bullet>();

    TextureAtlas atlas;
    MainShip mainShip;
    float height;

    protected final Bullet newObject(){
        return new Bullet(atlas, mainShip.pos.x, mainShip.pos.y+0.02f, height);
    }

    public Bullet obtain(TextureAtlas atlas, MainShip mainShip, float height) {
        Bullet bullet;
        this.mainShip = mainShip;
        this.height = height;
        if (freeObjects.isEmpty()) {
            bullet = newObject();
        } else {
            bullet = freeObjects.remove(freeObjects.size() - 1);
        }
        activeObjects.add(bullet);
        return bullet;
    }

    public void updateActiveObjects(float delta) {
       super.updateActiveObjects(delta);
    }

    public void drawActiveObjects(SpriteBatch batch) {
       super.drawActiveObjects(batch);
    }

    public void freeAllDestroyedActiveObjects() {
       super.freeAllDestroyedActiveObjects();
    }

    public void free(Bullet object) {
       super.free(object);
    }

    public void dispose() {
        super.dispose();
    }

    public void resize(Rect worldBounds) {
        for (Bullet bullet : activeObjects) {
             bullet.resize(worldBounds);
        }
    }

    public void keyDown(int keycode) {
        obtain();
        for (Bullet bullet : activeObjects) {
             bullet.keyDown(keycode);
        }
    }

    public void keyUp(int keycode) {

    }
}

