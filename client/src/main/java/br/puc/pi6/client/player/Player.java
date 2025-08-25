package br.puc.pi6.client.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import br.puc.pi6.client.utils.Constants;

public class Player {
    private final World world;
    public final Body body;

    // dimensões em METROS (tiles)
    public final float widthM  = 2.0f;
    public final float heightM = 3.0f;

    // sensor de pé (para detectar chão)
    private int footContacts = 0;

    public Player(World physicsWorld, float spawnX_pixels, float spawnY_pixels) {
        this.world = physicsWorld;

        // posição inicial em METROS (Box2D)
        float sx = spawnX_pixels / Constants.PPM;
        float sy = spawnY_pixels / Constants.PPM;

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(sx, sy);
        bd.fixedRotation = true; // não tombar
        body = world.createBody(bd);

        // corpo principal (caixa)
        PolygonShape box = new PolygonShape();
        box.setAsBox(widthM/2f, heightM/2f); // half-extents em metros

        FixtureDef fd = new FixtureDef();
        fd.shape = box;
        fd.density = 1f;
        fd.friction = 0.6f;  // atrito com chão
        fd.restitution = 0f; // sem quicar
        body.createFixture(fd).setUserData("player-main");
        box.dispose();

        // sensor dos pés (fino e largo) logo abaixo do corpo
        PolygonShape foot = new PolygonShape();
        foot.setAsBox(widthM*0.45f, 0.05f, new Vector2(0, -heightM/2f), 0);

        FixtureDef footFd = new FixtureDef();
        footFd.shape = foot;
        footFd.isSensor = true;
        body.createFixture(footFd).setUserData("player-foot");
        foot.dispose();
    }

    public boolean isOnGround() {
        return footContacts > 0;
    }

    // Chamado pelo ContactListener
    public void beginFootContact() { footContacts++; }
    public void endFootContact()   { footContacts = Math.max(0, footContacts - 1); }

    public Vector2 getCenterMeters() { return body.getPosition(); }

    // posição em PIXELS (para desenhar)
    public float getDrawXpx() {
        return body.getPosition().x * Constants.PPM - (widthM * Constants.PPM)/2f;
    }
    public float getDrawYpx() {
        return body.getPosition().y * Constants.PPM - (heightM * Constants.PPM)/2f;
    }
    public float getDrawWidthPx()  { return widthM  * Constants.PPM; }
    public float getDrawHeightPx() { return heightM * Constants.PPM; }
}
