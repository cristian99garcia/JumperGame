package com.zades.jumper;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

/**
 * Created by cristian on 09/09/17.
 */

public class Boton extends SpriteBatch {

    private float x = 0;
    private float y = 0;
    private float width = 0;
    private float height = 0;

    private Texture imagen;
    private Texture imagenActivada;
    private Matrix4 camara;

    public Boton() {
    }

    public Boton(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Boton(Texture imagen, float x, float y) {
        this.imagen = imagen;
        this.x = x;
        this.y = y;
        this.width = imagen.getWidth();
        this.height = imagen.getHeight();
    }

    public boolean dentro(float x, float y) {
        return this.x <= x &&
               this.x + width >= x &&
               this.y <= y &&
               this.y + height >= y;
    }

    public void setImagenActivada(Texture imagen) {
        this.imagenActivada = imagen;
    }

    public void update() {
        setProjectionMatrix(camara);
        begin();

        if (imagen != null)
            draw(imagen, x, y);

        end();
    }

    public void setCamara(Matrix4 camara) {
        this.camara = camara;
    }
}
