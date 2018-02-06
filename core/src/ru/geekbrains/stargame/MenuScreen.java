package ru.geekbrains.stargame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


import ru.geekbrains.stargame.engine.Base2DScreen;
import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.math.Rect;

public class MenuScreen extends Base2DScreen  {
    //private SpriteBatch batch;
    private Texture backgroundTexture;
    private Texture playButtonTexture;
    private Texture exitButtonTexture;
    private Texture shipTexture;
    Rectangle shipRect;
    //private Sprite logoSprite;
    private Background background;
    private PlayButton playButton;
    private ExitButton exitButton;
    private Ship ship;
    private Rect rectPlay;
    private Rect rectExit;
    private Rect rectShip;


    public MenuScreen(Game game){
        super(game);
    }

    @Override
    public void show() {
        super.show();

        batch = new SpriteBatch();
        backgroundTexture = new Texture("sky.jpg");
        playButtonTexture = new Texture("Play.png");
        exitButtonTexture = new Texture("Exit.png");
        shipTexture = new Texture("ship_1.png");
        background = new Background(new TextureRegion(backgroundTexture));
        playButton = new PlayButton(new TextureRegion(playButtonTexture));
        exitButton = new ExitButton(new TextureRegion(exitButtonTexture));
        ship = new Ship(new TextureRegion(shipTexture));
        rectPlay = new Rect(background.getHeight()-0.47f, -0.45f, 0.03f, 0.03f);
        rectExit = new Rect(background.getHeight()-0.6f, -0.45f, 0.03f, 0.03f);
        rectShip = new Rect(0f, 0f, 0.08f, 0.08f);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //Отрисовка фона
        exitButton.set(rectExit);
        playButton.set(rectPlay);
        ship.set(rectShip);
        ship.setAngle(90);
        background.draw(batch);
//        exitButton.setBottom(-0.2f);
//        exitButton.setLeft(-0.2f);
        playButton.draw(batch);
        exitButton.draw(batch);
        ship.draw(batch);
        //batch.draw(background, 0, 0);
        //Отрисовка корабля

        batch.end();

    }

    @Override
    protected void resize(Rect worldBounds){
        super.resize(worldBounds);
        background.resize(worldBounds);
        playButton.resize(worldBounds);
        exitButton.resize(worldBounds);
        ship.resize(worldBounds);
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        super.touchUp(touch, pointer);
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        playButtonTexture.dispose();
        exitButtonTexture.dispose();
        shipTexture.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//       currentPosition.set(screenX, Gdx.graphics.getHeight()-screenY);
//       shipRect.x = screenX-79/2; //позиционирование картинки по центру клика (с учётом размера картинки)
//       shipRect.y = (Gdx.graphics.getHeight() - screenY)-40/2; //позиционирование картинки по центру клика (с учётом размера картинки)

       return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }


}
