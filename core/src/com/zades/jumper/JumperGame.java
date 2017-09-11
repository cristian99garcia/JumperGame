package com.zades.jumper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import sun.rmi.runtime.Log;

public class JumperGame extends ApplicationAdapter {

    OrthographicCamera camara;

    PlayerSprite sprite;
    ControlMovimiento controles;
    Vector3[] toques;

    //Texture img;

    //static boolean projectionMatrixSet;

    @Override
    public void create() {
        camara = new OrthographicCamera();
        camara.setToOrtho(false, 800, 480);

        sprite = new PlayerSprite();
        sprite.setCamara(camara.combined);
        //projectionMatrixSet = false;

        controles = new ControlMovimiento();
        controles.setCamara(camara.combined);
        //img = new Texture("badlogic.jpg");

        toques = new Vector3[] {
                 new Vector3(),
                 new Vector3(),
        };
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camara.update();

        for (int i=0; i < 2; i++) {
            if (Gdx.input.isTouched(i))
                toques[i].set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
            else
                toques[i].set(-1, -1, -1);

            camara.unproject(toques[i]);
        }

        controles.pulsaciones(toques);
        controles.update();

        boolean[] p = controles.getPulsados();
        int avance = 5;
        for (int i = 0; i < p.length; i++) {
            if (p[i]) {
                if (i == 0) sprite.desacelerar();
                else if (i == 1) sprite.acelerar();
                else if (i == 2) { System.out.println(sprite.getEstadoSalto()+" "+sprite.y); sprite.saltar(); }
            }
        }

        if (!p[1] && !p[0]) {
            sprite.frenar();
        }

        if (!p[2]) {
            sprite.setEstadoSalto(2);
        }

        sprite.update();
    }
}
