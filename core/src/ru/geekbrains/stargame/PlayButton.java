package ru.geekbrains.stargame;

import ru.geekbrains.stargame.engine.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.geekbrains.stargame.engine.math.Rect;

/**
 * Created by User on 01.02.2018.
 */

public class PlayButton extends Sprite {
    public PlayButton(TextureRegion region) {
        super(region);
    }


    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }

}
