package ru.geekbrains.stargame.Bullet;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;

public class Bullet extends Sprite  {
    private final Vector2 v = new Vector2();
    private final Vector2 v0 = new Vector2(0.0f, 0.5f);
    private Rect worldBounds;

    public Bullet(TextureAtlas atlas, float vx, float vy, float height) {
        super(atlas.findRegion("bulletMainShip"));
        v.set(vx, vy);
        setHeightProportion(height);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkAndHandleBounds();
    }

    protected void checkAndHandleBounds() {
//        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
//        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
//        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
//        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = v.x; //Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = v.y; //Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posX, posY).rotate(180);
    }

    public void keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP:
                moveUp();
                break;
        }
    }

    public void keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP:
                stop();
                break;
         }
    }

    private void moveUp() {
        v.set(v0);
    }

    private void stop() {
        v.setZero();
    }
}
