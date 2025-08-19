package br.puc.pi6.client;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Launcher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Projeto-Integrador-6");
        cfg.setWindowedMode(1280, 720);
        cfg.useVsync(true);
        

        new Lwjgl3Application(new Game(), cfg);
    }
}
