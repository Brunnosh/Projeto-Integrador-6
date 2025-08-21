package br.puc.pi6.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import br.puc.pi6.client.utils.GraphicsController;
import br.puc.pi6.client.world.GameWorld;
import br.puc.pi6.client.world.Tile;
import br.puc.pi6.client.world.WorldGen;
import br.puc.pi6.client.world.worldAttribs.WorldSize;

public class Game extends ApplicationAdapter {

    //--Grafico--//
    private GraphicsController graphics;
    
    private OrthographicCamera worldCam;
    private ScreenViewport  worldViewport;

    private OrthographicCamera uiCam;
    private ScreenViewport  uiViewport;
    
    private ShapeRenderer shapes;
    private float x = 100, y = 100, speed = 200, size = 40;

    //--UI--//
    private boolean paused = false;
    private Stage stage, pauseStage, hudStage;
    private Label coordsLabel;
    private Skin skin;
    private boolean inLocalWorld = false;

    //--Mundo--//
    private GameWorld world;
    private WorldSize selectedSize = WorldSize.MEDIUM; // default

    float zoomStep = 0.1f;
    float zoomMin = 0.25f;
    float zoomMax = 3f;

    @Override
    public void create() {
        shapes   = new ShapeRenderer();
        graphics = new GraphicsController(true);
    
        worldCam = new OrthographicCamera();
        worldViewport = new ScreenViewport(worldCam);
        worldCam.update();

        uiCam = new OrthographicCamera();
        uiViewport = new ScreenViewport(uiCam);
        // UI em pixels reais da tela
        uiCam.update();

        stage = new Stage(uiViewport);
        pauseStage= new Stage(uiViewport);
        hudStage  = new Stage(uiViewport);
        Gdx.input.setInputProcessor(stage);
        
        skin = new Skin(Gdx.files.internal("uiskin.json")); 

        buildMenu();
        buildPause();
        buildHud();
    }



    @Override
    public void render() {
        Gdx.gl.glClearColor(0.08f, 0.09f, 0.12f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        float dt = Gdx.graphics.getDeltaTime();
        if (inLocalWorld) {
            if (!paused) {
                pollInput(dt);
            }
            worldCam.position.set(x + size / 2, y + size / 2, 0); // centraliza no objeto
            worldCam.update();
            
            // aplica viewport do mundo
            worldViewport.apply();
            shapes.setProjectionMatrix(worldCam.combined);
            shapes.begin(ShapeRenderer.ShapeType.Filled);


            // desenha tiles (placeholder: blocos 8x8)
            if (world != null) {
                for (int ix = 0; ix < world.getWidth(); ix++) {
                    for (int iy = 0; iy < world.getHeight(); iy++) {
                        Tile block = world.getTile(ix, iy);
                        if (block == Tile.AIR) continue; // ar

                        switch (block) {
                            case GRASS: shapes.setColor(0f, 0.8f, 0f, 1f); break;         // grama
                            case DIRT: shapes.setColor(0.5f, 0.3f, 0.1f, 1f); break;      // terra
                            default: shapes.setColor(0.3f, 0.3f, 0.3f, 1f); break;
                        }
                        shapes.rect(ix * 8f, iy * 8f, 8f, 8f);
                    }
                }
            }

            // seu “player”/shape provisório continua igual:
            shapes.setColor(1f, 0.2f, 0.2f, 1f);
            shapes.rect(x, y, size, size*2);
            shapes.end();
            
            
            uiViewport.apply();
            coordsLabel.setText("X:" + (int) x + " Y:" + (int) y);
            hudStage.act(dt);
            hudStage.draw();

            if (paused) {
                uiViewport.apply();
                pauseStage.act(dt);
                pauseStage.draw();
            }
        } else {
            uiViewport.apply();
            stage.act(dt);
            stage.draw();
        }
       
    }

    private void pollInput(float dt){
        boolean shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT);

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            Gdx.app.log("Input", "Teste keybind dupla");
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.V)){
            graphics.toggleVSync();
            Gdx.app.log("VSync", graphics.isVSync() ? "ON" : "OFF");
        }

        // ZOOM IN ( + )
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_ADD)) {    // SHIFT + '=' => '+'
            worldCam.zoom -= zoomStep;
            worldCam.zoom = MathUtils.clamp(worldCam.zoom, zoomMin, zoomMax);
            worldCam.update();
        }

        // ZOOM OUT ( - )
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_SUBTRACT)) {                 // '-' da fileira principal
            worldCam.zoom += zoomStep;
            worldCam.zoom = MathUtils.clamp(worldCam.zoom, zoomMin, zoomMax);
            worldCam.update();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused = !paused;
            if (paused) {
                Gdx.input.setInputProcessor(pauseStage); // UI captura input
            } else {
                Gdx.input.setInputProcessor(null); // volta pro jogo
                pauseStage.unfocusAll();
                pauseStage.cancelTouchFocus();
            }
        }   

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))  x -= speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP))    y += speed * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))  y -= speed * dt;

    }

    @Override
    public void resize(int width, int height) {
    worldViewport.update(width, height); // 1:1 com a janela, sem manter proporção “virtual”
    uiViewport.update(width, height);    // UI em pixels da tela

    if (stage != null)     stage.getViewport().update(width, height, true);
    if (pauseStage != null)pauseStage.getViewport().update(width, height, true);
    if (hudStage != null)  hudStage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        if (shapes != null) shapes.dispose();
        if (stage != null) stage.dispose();
        if (skin != null) skin.dispose();
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
            world = new GameWorld(selectedSize);
            new WorldGen().generate(world);
            inLocalWorld = true;
            Gdx.input.setInputProcessor(null); 
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

    private void buildPause() {
        pauseStage = new Stage(new ScreenViewport());
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table pauseTable = new Table();
        pauseTable.setFillParent(true);
        pauseTable.defaults().pad(10);
        pauseStage.addActor(pauseTable);

        Label pauseLabel = new Label("PAUSADO", skin);
        TextButton btnResume = new TextButton("Continuar", skin);
        TextButton btnExit = new TextButton("Sair do Jogo", skin);

        btnResume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                paused = false;
                Gdx.input.setInputProcessor(null);
                pauseStage.unfocusAll();
                pauseStage.cancelTouchFocus();
            }
        });

        btnExit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        pauseTable.add(pauseLabel).row();
        pauseTable.add(btnResume).width(200).height(50).row();
        pauseTable.add(btnExit).width(200).height(50);
    }

    private void buildHud(){
        hudStage = new Stage(new ScreenViewport());
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table hudTable = new Table();
        hudTable.top().left().pad(10); // canto superior esquerdo
        hudTable.setFillParent(true);
        hudStage.addActor(hudTable);

        coordsLabel = new Label("X:0 Y:0", skin);
        hudTable.add(coordsLabel);
    }
}