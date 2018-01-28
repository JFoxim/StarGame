package ru.geekbrains.stargame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import ru.geekbrains.stargame.engine.Base2DScreen;

public class MenuScreen extends Base2DScreen  {

    private SpriteBatch batch;
    private Texture background;
    private Texture ship;
    Rectangle shipRect;

    public MenuScreen(Game game){
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        background = new Texture("sky.jpg");
        ship = new Texture("ship_1.png");
        //Координаты корабля
        shipRect = new Rectangle();
        //Начальные координаты - корабль за пределами экрана
        shipRect.x = -50; //800 / 2 - 79 / 2;
        shipRect.y = -50; //20;
        //Размеры корабля картинки
        shipRect.width = 79;
        shipRect.height = 40;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //camera.update();
        //batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //Отрисовка фона
        batch.draw(background, 0, 0);
        //Отрисовка корабля
        batch.draw(ship, shipRect.x, shipRect.y);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        shipRect.x = screenX-79/2; //позиционирование картинки по центру клика (с учётом размера картинки)
        shipRect.y = (Gdx.graphics.getHeight() - screenY)-40/2; //позиционирование картинки по центру клика (с учётом размера картинки)
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }


}
