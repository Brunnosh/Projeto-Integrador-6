package br.puc.pi6.client.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;

public class GraphicsController  {
    private boolean vsync;
    private final Preferences prefs;

    private static final String PREFS_NAME = "settings";
    private static final String KEY_VSYNC  = "vsync";

    public GraphicsController(boolean defaultVSync) {
        prefs = Gdx.app.getPreferences(PREFS_NAME);
        vsync = prefs.contains(KEY_VSYNC) ? prefs.getBoolean(KEY_VSYNC, defaultVSync) : defaultVSync;
        apply(); // aplica no início
        persist(); // garante que exista na primeira vez
    }

    public void setVSync(boolean on) { vsync = on; apply(); persist(); }
    public void toggleVSync() { setVSync(!vsync); }
    public boolean isVSync() { return vsync; }

    private void apply() {
        if (Gdx.graphics instanceof Lwjgl3Graphics) {
            ((Lwjgl3Graphics) Gdx.graphics).setVSync(vsync);
            // Opcional: limitar FPS quando VSync OFF para não saturar CPU/GPU:
            // Gdx.graphics.setForegroundFPS(vsync ? 0 : 120);
        }
    }

    private void persist() {
        prefs.putBoolean(KEY_VSYNC, vsync).flush();
    }
}
