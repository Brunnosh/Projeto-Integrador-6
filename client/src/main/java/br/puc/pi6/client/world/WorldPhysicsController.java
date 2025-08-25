package br.puc.pi6.client.world;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import br.puc.pi6.client.utils.Constants;

public class WorldPhysicsController {
    private final World physicsWorld;
    private final GameWorld gameWorld;

    public WorldPhysicsController(World physicsWorld, GameWorld gameWorld) {
        this.physicsWorld = physicsWorld;
        this.gameWorld = gameWorld;
    }


    public void buildPhysicsFromTiles() {
        for (int x = 0; x < gameWorld.getWidth(); x++) {
            for (int y = 0; y < gameWorld.getHeight(); y++) {
                Tile tile = gameWorld.getTile(x, y);
                if (tile != Tile.AIR) {
                    Body body = createTileBody(x, y);

                }
            }
        }
    }


    public Body createTileBody(int tileX, int tileY) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;

        float centerX = (tileX * Constants.TILE_SIZE_PX + Constants.TILE_SIZE_PX/2f) / Constants.PPM;
        float centerY = (tileY * Constants.TILE_SIZE_PX + Constants.TILE_SIZE_PX/2f) / Constants.PPM;
        def.position.set(centerX, centerY);

        Body body = physicsWorld.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.TILE_SIZE_PX/2f / Constants.PPM, Constants.TILE_SIZE_PX/2f / Constants.PPM);

        body.createFixture(shape, 0f);
        shape.dispose();

        return body;
    }


}
