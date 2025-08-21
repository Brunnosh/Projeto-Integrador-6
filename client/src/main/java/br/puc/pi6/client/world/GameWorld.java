package br.puc.pi6.client.world;

import br.puc.pi6.client.world.worldAttribs.WorldSize;

public class GameWorld {
    // 0=ar, 1=grama, 2=terra, 3=tronco (placeholder)
    private final int width;
    private final int height;
    private final int[][] tiles;

    public GameWorld(WorldSize size) {
        this.width  = size.width;
        this.height = size.height;
        this.tiles  = new int[width][height];
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setTile(int x, int y, int id) {
        if (x>=0 && x<width && y>=0 && y<height) tiles[x][y] = id;
    }

    public int getTile(int x, int y) {
        if (x>=0 && x<width && y>=0 && y<height) return tiles[x][y];
        return 0; // ar
    }

    public int[][] getTiles() { return tiles; }
}
