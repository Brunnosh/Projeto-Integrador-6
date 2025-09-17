package br.puc.pi6.client.world.worldAttribs;


public enum WorldSize {
    SMALL(200, 120),
    MEDIUM(400, 240),
    LARGE(800, 480);

    public final int width;
    public final int height;

    WorldSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
}
