package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.Background;
import ru.geekbrains.stargame.Bullet.Bullet;
import ru.geekbrains.stargame.Bullet.BulletPool;
import ru.geekbrains.stargame.engine.ActionListener;
import ru.geekbrains.stargame.engine.Base2DScreen;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;
import ru.geekbrains.stargame.ship.MainShip;
import ru.geekbrains.stargame.star.Star;
import ru.geekbrains.stargame.ui.ButtonExit;
import ru.geekbrains.stargame.ui.ButtonPlay;

public class GameScreen extends Base2DScreen implements ActionListener {

    private static final float BUTTON_HEIGHT = 0.15f;
    private static final float BUTTON_PRESS_SCALE = 0.9f;
    private static final int STAR_COUNT = 20;
    private static final float STAR_HEIGHT = 0.01f;

    private Texture backgroundTexture;
    private Background background;

    private TextureAtlas atlas;
    private TextureAtlas atlasMain;

    private ButtonExit buttonExit;
    private MainShip mainShip;

    private Star[] star;
    private BulletPool<Bullet> bullets;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("bg.png");
        background = new Background(new TextureRegion(backgroundTexture));
        atlasMain = new TextureAtlas("mainAtlas.tpack");
        mainShip = new MainShip(atlasMain);
        atlas = new TextureAtlas("menuAtlas.tpack");
        //buttonExit = new ButtonExit(atlas, BUTTON_PRESS_SCALE, this);
        //buttonExit.setHeightProportion(BUTTON_HEIGHT);
        star = new Star[STAR_COUNT];
        for (int i=0; i < star.length; i++) {
            star[i] = new Star(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), STAR_HEIGHT);
        }
        bullets = new BulletPool<Bullet>();
        bullets.obtain(atlasMain, mainShip, 0.02f);
     }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        for (int i=0; i < star.length; i++) {
            star[i].update(delta);
        }
        mainShip.update(delta);
        bullets.updateActiveObjects(delta);
    }

    public void draw() {
        Gdx.gl.glClearColor(0.7f, 0.3f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i=0; i < star.length; i++) {
            star[i].draw(batch);
        }
       // buttonExit.draw(batch);
        mainShip.draw(batch);
        bullets.drawActiveObjects(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundTexture.dispose();
        atlas.dispose();
        atlasMain.dispose();
        bullets.dispose();
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        super.touchUp(touch, pointer);
        //buttonExit.touchUp(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        bullets.keyDown(keycode);
        return false;
    }

  @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        bullets.keyUp(keycode);
        return false;
    }

    @Override
    public void touchDragged(Vector2 touch, int pointer) {
        mainShip.touchDragged(touch, pointer);
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
        //buttonExit.resize(worldBounds);
        bullets.resize(worldBounds);
        mainShip.resize(worldBounds);
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        super.touchDown(touch, pointer);
        //buttonExit.touchDown(touch, pointer);
        mainShip.touchDown(touch, pointer);
    }

    @Override
    public void actionPerformed(Object src) {
        if (src == buttonExit) {
            game.setScreen(new MenuScreen(game));
        } else
            throw new RuntimeException("Unknown src " + src);
        }
    }
