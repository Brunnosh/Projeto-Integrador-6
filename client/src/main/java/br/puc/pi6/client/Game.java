package br.puc.pi6.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import br.puc.pi6.client.input.Action;
import br.puc.pi6.client.input.ActionBindings;
import br.puc.pi6.client.utils.GraphicsController;

public class Game extends ApplicationAdapter {

    private GraphicsController graphics;
    private ActionBindings bindings;

    private ShapeRenderer shapes;
    private float x = 100, y = 100, speed = 200, size = 40;

    @Override
    public void create() {
        shapes   = new ShapeRenderer();
        graphics = new GraphicsController(true); // coerente com cfg.useVsync(true)

        bindings = new ActionBindings();
        bindings.bind(Input.Keys.V, Action.TOGGLE_VSYNC);
        //UI: Adicionar multiplexer
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();

        pollInput(dt);

        Gdx.gl.glClearColor(0.08f, 0.09f, 0.12f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.rect(x, y, size, size);
        shapes.end();
       
    }

    private void pollInput(float dt){

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            Gdx.app.log("Input", "Teste keybind dupla");
        }

        bindings.scanJustPressed(action -> {
            switch (action) {
                case TOGGLE_VSYNC:
                    graphics.toggleVSync();
                    Gdx.app.log("VSync", graphics.isVSync() ? "ON" : "OFF");
                    break;

            }
        });


        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))  x -= speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP))    y += speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))  y -= speed * dt;

    }

    @Override
    public void resize(int width, int height) {
        // atualizar viewport/câmera quando a janela muda de tamanho
    }

    @Override
    public void dispose() {
        if (shapes != null) shapes.dispose();
    }
}
