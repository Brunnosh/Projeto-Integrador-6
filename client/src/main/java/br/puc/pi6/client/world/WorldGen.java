package br.puc.pi6.client.world;

import java.util.Random;

public class WorldGen {
    private final Random rng = new Random();

    public void generate(GameWorld w) {
        int ground = w.getHeight() / 2;

        for (int x = 0; x < w.getWidth(); x++) {
            for (int y = 0; y < w.getHeight(); y++) {
                if (y < ground - 2) w.setTile(x, y, 0);        // ar
                else if (y == ground - 2) w.setTile(x, y, 1);  // grama
                else w.setTile(x, y, 2);                       // terra
            }
        }
    }
}
