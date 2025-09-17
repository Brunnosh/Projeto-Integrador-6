package br.puc.pi6.client.world;

import java.util.Random;

public class WorldGen {
    private final Random rng = new Random();

    public void generate(GameWorld w) {
    

        for (int x = 0; x < w.getWidth(); x++) {
            for (int y = 0; y < w.getHeight(); y++) { 
                w.setTile(x, y, Tile.DIRT);          
            }
        }
    }
}
