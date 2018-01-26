package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
		img = new Texture("sky.jpg");
		shipImg = new Texture("ship_1.png");

		ship = new Rectangle();
		ship.x = 800 / 2 - 64 / 2;
		ship.y = 20;
		ship.width = 64;
		ship.height = 64;
		//range = new Range()
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(shipImg, ship.x, ship.y);

		if(Gdx.input.isTouched()){
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			shipImg.x = (int) (touchPos.x -64 / 2);
		}

		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(shipImg, 10, 10);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
