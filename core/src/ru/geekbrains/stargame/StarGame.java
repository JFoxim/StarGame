package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import org.w3c.dom.ranges.Range;

public class StarGame extends ApplicationAdapter {
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture img;
	Texture shipImg;
	Vector3 touchPos;
	Rectangle ship;
	Range range;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		touchPos = new Vector3();

		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		//Картинка фона звёздного неба
		img = new Texture("sky.jpg");
		//Картинка корабля
		shipImg = new Texture("ship_1.png");

		//Координаты корабля
    	ship = new Rectangle();
		ship.x = 800 / 2 - 79 / 2;
		ship.y = 20;
		//Размеры корабля
		ship.width = 79;
		ship.height = 40;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//Отрисовка фона
		batch.draw(img, 0, 0);
		//Отрисовка корабля
		batch.draw(shipImg, ship.x, ship.y);
		batch.end();

		//Обработка нажатий точскрина на android по горизонтале
		if(Gdx.input.isTouched()){
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			ship.x = (int) (touchPos.x -79 / 2);
		}

		//Движение корабля клавишами стрелок навлево и направо
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) ship.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) ship.x += 200 * Gdx.graphics.getDeltaTime();

		//Движение корабля клавишами стрелок вверх и вниз
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) ship.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) ship.y -= 200 * Gdx.graphics.getDeltaTime();

		//Ограничение выхода корабля за пределы экрана по горизонтале
		if (ship.x < 0) ship.x = 0;
		if (ship.x > 800 - 79) ship.x = 800 - 79;

		//Ограничение выхода корабля за пределы экрана по вертикале
		if (ship.y < 0) ship.y = 0;
		if (ship.y > 480 - 40) ship.y = 480 - 40;
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		img.dispose();
		shipImg.dispose();
	}
}
