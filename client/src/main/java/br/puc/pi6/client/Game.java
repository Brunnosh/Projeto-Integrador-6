package br.puc.pi6.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Game extends ApplicationAdapter {

    @Override
    public void create() {
        // aqui você carrega assets (texturas, fontes) ou inicializa sistemas
        System.out.println("LibGDX on! " + Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.08f, 0.09f, 0.12f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //TODO: Desacoplar update (TPS) do render.
        
       
    }

    @Override
    public void resize(int width, int height) {
        // atualizar viewport/câmera quando a janela muda de tamanho
    }

    @Override
    public void dispose() {
        // liberar recursos (texturas, fontes, batches)
    }
}
