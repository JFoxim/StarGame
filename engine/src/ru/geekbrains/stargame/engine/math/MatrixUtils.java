package ru.geekbrains.stargame.engine.math;


import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

/**
 * РЈС‚РёР»РёС‚Р° РґР»СЏ СЂР°Р±РѕС‚С‹ СЃ РјР°С‚СЂРёС†Р°РјРё
 */
public class MatrixUtils {

    private MatrixUtils() {
    }

    /**
     * Р Р°СЃС‡С‘С‚ РјР°С‚СЂРёС†С‹ РїРµСЂРµС…РѕРґР° 4x4
     * @param mat РёС‚РѕРіРѕРІР°СЏ РјР°С‚СЂРёС†Р° РїСЂРµРѕР±СЂР°Р·РѕРІР°РЅРёР№
     * @param src РёСЃС…РѕРґРЅС‹Р№ РєРІР°РґСЂР°С‚
     * @param dst РёС‚РѕРіРѕРІС‹Р№ РєРІР°РґСЂР°С‚
     */
    public static void calcTransitionMatrix(Matrix4 mat, Rect src, Rect dst) {
        float scaleX = dst.getWidth() / src.getWidth();
        float scaleY = dst.getHeight() / src.getHeight();
        mat.idt().translate(dst.pos.x, dst.pos.y, 0f).scale(scaleX, scaleY, 1f).translate(-src.pos.x, -src.pos.y, 0f);
    }

    /**
     * Р Р°СЃС‡С‘С‚ РјР°С‚СЂРёС†С‹ РїРµСЂРµС…РѕРґР° 3x3
     * @param mat РёС‚РѕРіРѕРІР°СЏ РјР°С‚СЂРёС†Р° РїСЂРµРѕР±СЂР°Р·РѕРІР°РЅРёР№
     * @param src РёСЃС…РѕРґРЅС‹Р№ РєРІР°РґСЂР°С‚
     * @param dst РёС‚РѕРіРѕРІС‹Р№ РєРІР°РґСЂР°С‚
     */
    public static void calcTransitionMatrix(Matrix3 mat, Rect src, Rect dst) {
        float scaleX = dst.getWidth() / src.getWidth();
        float scaleY = dst.getHeight() / src.getHeight();
        mat.idt().translate(dst.pos.x, dst.pos.y).scale(scaleX, scaleY).translate(-src.pos.x, -src.pos.y);
    }
}