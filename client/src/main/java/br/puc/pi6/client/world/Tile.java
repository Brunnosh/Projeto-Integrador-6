package br.puc.pi6.client.world;

//TODO:Tiles com atributos diferentes (friccao,etc..) (BOX2D)
public enum Tile {
    AIR(0),
    DIRT(1),
    GRASS(2);

    private final int ID;

    Tile(int ID) {
        this.ID = ID;
    }

    public int getID(){
        return ID;
    }
}
