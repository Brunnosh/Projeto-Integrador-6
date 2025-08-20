package br.puc.pi6.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import br.puc.pi6.client.utils.GraphicsController;

public class Game extends ApplicationAdapter {

    private GraphicsController graphics;
    

    private OrthographicCamera cam;
    private FitViewport viewport;
    private ShapeRenderer shapes;
    private float x = 100, y = 100, speed = 200, size = 40;

    private Stage stage;
    private Skin skin;
    private boolean inLocalWorld = false;


    @Override
    public void create() {
        shapes   = new ShapeRenderer();
        cam = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
        graphics = new GraphicsController(true); // coerente com cfg.useVsync(true)
    
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        skin = new Skin(Gdx.files.internal("uiskin.json")); // baixe uiskin.json + atlas + font em assets/

        buildMenu();
    }

        private void buildMenu() {
        stage.clear();

        Table root = new Table();
        root.setFillParent(true);
        root.defaults().pad(10);
        stage.addActor(root);

        Label title = new Label("Projeto PI6 - Cliente", skin);

        TextButton btnLocal = new TextButton("Iniciar Mundo Local", skin);
        TextButton btnConnect = new TextButton("Conectar a Servidor", skin);

        btnLocal.addListener(e -> {
            if (!btnLocal.isPressed()) return false;
            inLocalWorld = true;
            Gdx.input.setInputProcessor(null); // input manual no mundo
            return true;
        });

        btnConnect.addListener(e -> {
            if (!btnConnect.isPressed()) return false;
            Dialog d = new Dialog("Conectar", skin);
            d.text("Funcionalidade em breve.");
            d.button("OK");
            d.show(stage);
            return true;
        });

        root.add(title).padBottom(30).row();
        root.add(btnLocal).width(280).height(50).row();
        root.add(btnConnect).width(280).height(50).row();
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0.08f, 0.09f, 0.12f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if (inLocalWorld) {
            pollInput(dt);

            shapes.setProjectionMatrix(cam.combined);
            shapes.begin(ShapeRenderer.ShapeType.Filled);
            shapes.rect(x, y, size, size);
            shapes.end();
        } else {
            stage.act(dt);
            stage.draw();
        }
       
    }

    private void pollInput(float dt){

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            Gdx.app.log("Input", "Teste keybind dupla");
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.V)){
            graphics.toggleVSync();
            Gdx.app.log("VSync", graphics.isVSync() ? "ON" : "OFF");
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))  x -= speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP))    y += speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))  y -= speed * dt;

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        if (shapes != null) shapes.dispose();
        if (stage != null) stage.dispose();
        if (skin != null) skin.dispose();
    }
}