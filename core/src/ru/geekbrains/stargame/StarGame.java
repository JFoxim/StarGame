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
		img = new Texture("sky.jpg");
		shipImg = new Texture("ship_1.png");

    	ship = new Rectangle();
		ship.x = 800 / 2 - 79 / 2;
		ship.y = 20;
		ship.width = 79;
		ship.height = 40;
		//range = new Range()
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(shipImg, ship.x, ship.y);
//		batch.draw(shipImg, 10, 10);
		batch.end();

		if(Gdx.input.isTouched()){
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			ship.x = (int) (touchPos.x -79 / 2);
		}


		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) ship.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) ship.x += 200 * Gdx.graphics.getDeltaTime();

		if(Gdx.input.isKeyPressed(Input.Keys.UP)) ship.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) ship.y -= 200 * Gdx.graphics.getDeltaTime();

		if (ship.x < 0) ship.x = 0;
		if (ship.x > 800 - 79) ship.x = 800 - 79;

//		if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();
//
//		Iterator<Rectangle> iter = raindrops.iterator();
//		while (iter.hasNext()){
//			Rectangle raindrop = iter.next();
//			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
//			if (raindrop.y + 64 < 0) iter.remove();
//			if (raindrop.overlaps(bucket)){
//				dropSound.play();
//				iter.remove();
//			}
//		}

	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		img.dispose();
		shipImg.dispose();
	}
}
