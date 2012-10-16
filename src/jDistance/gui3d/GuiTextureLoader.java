/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.gui3d;

import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Texture2D;

/**
 * GuiTextureLoader ist eine Hilfklasse zum Laden von Texturen
 *
 */
public class GuiTextureLoader {

    /**
     * Standartkonstruktor
     */
    private GuiTextureLoader() {
    }

    /**
     * Lädt die Texturen eines weißen Spielfeldes
     * @return Textur des Spielfeldes
     */
    public static Texture2D loadWhiteFieldTexture() {
        TextureLoader textureLoader = new TextureLoader("img/white.jpeg", null);
        ImageComponent2D textureImage = textureLoader.getScaledImage(64, 64);
        Texture2D texture = new Texture2D(Texture2D.BASE_LEVEL, Texture2D.RGB, textureImage.getWidth(),
                textureImage.getHeight());
        texture.setImage(0, textureImage);
        return texture;
    }

    /**
     * Lädt die Holzmasserung für den Rahmen des Spielfeldes
     * @return Textur des Spielfeldes
     */
    public static Texture2D loadBorderTexture() {
        TextureLoader textureLoader = new TextureLoader("img/border.jpeg", null);
        ImageComponent2D textureImage = textureLoader.getScaledImage(1024, 1024);
        Texture2D texture = new Texture2D(Texture2D.BASE_LEVEL, Texture2D.RGB, textureImage.getWidth(),
                textureImage.getHeight());
        texture.setImage(0, textureImage);
        return texture;
    }

    /**
     * Lädt die Texturen eines schwarzen Spielfeldes
     * @return Textur des Spielfeldes
     */
    public static Texture2D loadBlackFieldTexture() {
        TextureLoader textureLoader = new TextureLoader("img/black.jpeg", null);
        ImageComponent2D textureImage = textureLoader.getScaledImage(64, 64);
        Texture2D texture = new Texture2D(Texture2D.BASE_LEVEL, Texture2D.RGB, textureImage.getWidth(),
                textureImage.getHeight());
        texture.setImage(0, textureImage);
        return texture;
    }

    /**
     * Lädt die Texturen für den Schatten der Spielfiguren
     * @return Textur des Schattens
     */
    public static Texture2D loadShadeTexture() {
        TextureLoader textureLoader = new TextureLoader("img/shade.png", "RGBA", null);
        ImageComponent2D textureImage = textureLoader.getScaledImage(512, 512);
        Texture2D texture = new Texture2D(Texture2D.BASE_LEVEL, Texture2D.RGBA, textureImage.getWidth(),
                textureImage.getHeight());
        texture.setImage(0, textureImage);
        return texture;
    }
}
