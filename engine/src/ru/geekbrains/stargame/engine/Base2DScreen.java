package ru.geekbrains.stargame.engine;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.engine.math.MatrixUtils;
import ru.geekbrains.stargame.engine.math.Rect;

public class Base2DScreen implements Screen, InputProcessor{
    protected Game game;
    private Rect screenBounds; // границы области рисования
    private Rect worldBounds; //границы мировой системы координат
    private Rect glBoubds;  //Дефолтные границы проекции мира - gl

    protected SpriteBatch batch;

    protected Matrix4 worldToGl;
    protected Matrix3 screenToWorld;
    protected Vector2 touch = new Vector2();


    public Base2DScreen(Game game){
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        this.screenBounds = new Rect();
        this.worldBounds = new Rect();
        this.glBoubds = new Rect(0, 0, 1f, 1f);
        this.worldToGl = new Matrix4();
        this.screenToWorld = new Matrix3();
        if (batch != null){
            throw new RuntimeException("batch != null, повторная screen без");
        }
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {

    }

    protected void resize(Rect worldBounds) {

    }

    @Override
    public void resize(int width, int height) {
     screenBounds.setSize(width, height);
     screenBounds.setLeft(0);
     screenBounds.setBottom(0);

     float aspect = width/(float)height;
     worldBounds.setHeight(1f);
     worldBounds.setWidth(1f*aspect);
     MatrixUtils.calcTransitionMatrix(worldToGl, worldBounds, glBoubds);
     batch.setProjectionMatrix(worldToGl);
     MatrixUtils.calcTransitionMatrix(screenToWorld, screenBounds, worldBounds);
     resize(worldBounds);
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
    }

    @Override
    public void dispose() {

        batch.dispose();
        batch = null;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld);
        System.out.println("touchDown X=" + touch.x + " Y=" + touch.y);
        touchDown(touch, pointer);
        return false;
    }

    protected void touchDown(Vector2 touch, int pointer) {

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenBounds.getHeight()- screenY).mul(screenToWorld);
        System.out.println("touchUP X=" + touch.x + " Y=" + touch.y);
        touchUp(touch, pointer);
        return false;
    }

    protected  void touchUp(Vector2 touch, int pointer){

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld);
        System.out.println("touchDragged X=" + touch.x + " Y=" + touch.y);
        touchDragged(touch, pointer);
        return false;
    }

    protected void touchDragged(Vector2 touch, int pointer){

    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
