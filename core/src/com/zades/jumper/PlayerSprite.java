package com.zades.jumper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

/**
 * Created by cristian on 09/09/17.
 */

public class PlayerSprite extends SpriteBatch {

    private int QUIETO = 0;
    private int SALTANDO = 1;
    private int CAYENDO = 2;

    public float x = 0;
    public float y = 0;
    public float width = 100;
    public float height = 100;

    public float velocidad = 0;
    public float velocidadMaxima = 20;

    private int estadoSalto = QUIETO;
    private float velocidadSalto = 0;
    private float velocidadSaltoMaxima = 15;
    private float factorSalto = 2f;

    ShapeRenderer shapeRenderer;
    Matrix4 camara;

    public PlayerSprite() {
        super();
        shapeRenderer = new ShapeRenderer();
    }

    public void update() {
        setProjectionMatrix(camara);
        x += velocidad;
        y += velocidadSalto;

        if (y > 0.01 && estadoSalto == CAYENDO) {
            caer();
        } else if (y <= 0.01 && estadoSalto == CAYENDO) {
            y = 0;
            velocidadSalto = 0;
            estadoSalto = QUIETO;
        }
        //else if (estadoSalto != CAYENDO) {System.out.println(""+estadoSalto);}

        begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
        end();
    }

    public void setCamara(Matrix4 camara) {
        this.camara = camara;
    }

    public void acelerar() {
        velocidad += (velocidadMaxima - velocidad) / 10f;
    }

    public void desacelerar() {
        velocidad -= (velocidadMaxima + velocidad) / 10f;
    }

    public void saltar() {
        if (estadoSalto == CAYENDO) {
            return;
        }

        estadoSalto = SALTANDO;
        System.out.println("SALTO: Seguir saltando " + y + " " + velocidadSalto);
        velocidadSalto += (velocidadSaltoMaxima - velocidadSalto) / factorSalto;

        if (velocidadSalto >= velocidadSaltoMaxima - 0.01) {
            // Empieza la caída
            estadoSalto = CAYENDO;
        }
    }

    public void caer() {
        //System.out.println("CAER: " + y + " " + (y <= 0.01))
        if (y <= 0.01) {
            System.out.println("SALTO: La caída terminó");
            y = 0;
            velocidadSalto = 0;
            estadoSalto = QUIETO;
        } else if (y > 0.01 && estadoSalto == CAYENDO) {
            System.out.println("SALTO: Seguir cayendo " + y + " " + velocidadSalto);
            velocidadSalto -= (velocidadSaltoMaxima + velocidadSalto) / factorSalto;
        }
    }

    public void frenar() {
        if (velocidad == 0) return;
        else if (velocidad > 0) velocidad = Math.max(velocidad - 4, 0);
        else if (velocidad < 0) velocidad = Math.min(velocidad + 4, 0);
    }

    public int getEstadoSalto() {
        return estadoSalto;
    }

    public void setEstadoSalto(int estado) {
        estadoSalto = estado;
    }
}
