package br.puc.pi6.client.world;

import br.puc.pi6.client.world.worldAttribs.WorldSize;

public class GameWorld {
    // 0=ar, 1=grama, 2=terra, 3=tronco (placeholder)
    private final int width;
    private final int height;
    private final Tile[][] tiles;

    public GameWorld(WorldSize size) {
        this.width  = size.width;
        this.height = size.height;
        this.tiles  = new Tile[width][height];
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setTile(int x, int y, Tile id) {
        if (x>=0 && x<width && y>=0 && y<height) tiles[x][y] = id;
    }

    public Tile getTile(int x, int y) {
        if (x>=0 && x<width && y>=0 && y<height) return tiles[x][y];
        return Tile.AIR; // ar
    }

    public Tile[][] getTiles() { return tiles; }
}
