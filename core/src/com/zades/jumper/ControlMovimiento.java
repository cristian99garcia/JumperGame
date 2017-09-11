package com.zades.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by cristian on 09/09/17.
 */

public class ControlMovimiento {

    private Boton atras;
    private Boton adelante;
    private Boton arriba;

    private boolean[] pulsados = { false, false, false, false };
    private Boton[] botones;

    public ControlMovimiento() {
        float bw = 80f;
        float space = 5f;

        atras = new Boton(new Texture(Gdx.files.internal("izquierda.png")), 20, 50);
        adelante = new Boton(new Texture(Gdx.files.internal("derecha.png")), 120, 50);
        arriba = new Boton(new Texture(Gdx.files.internal("arriba.png")), 795 - (bw * 3 / 2), 50);

        botones = new Boton[] {
                atras,
                adelante,
                arriba,
        };
    }

    public Boton[] getBotones() {
        return botones;
    }

    public void setCamara(Matrix4 camara) {
        for (Boton boton: botones)
            boton.setCamara(camara);
    }

    public void pulsaciones(Vector3[] vectores) {
        boolean[] valores = new boolean[] { false, false, false };
        int boton;
        for (Vector3 vector: vectores) {
            boton = pulsacion(vector.x, vector.y);
            if (boton != -1) valores[boton] = true;
        }

        pulsados = valores;
    }

    public int pulsacion(float x, float y) {
        for (int i=0; i < botones.length; i++) {
            if (botones[i].dentro(x, y)) { return i; }
        }

        return -1;
    }

    public boolean[] getPulsados() {
        return pulsados;
    }

    public void setPulsados(boolean[] pulsados) {
        this.pulsados = pulsados;
    }

    public void update() {
        for (Boton boton: botones) boton.update();
    }
}
