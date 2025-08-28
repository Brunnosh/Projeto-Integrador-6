package br.puc.pi6.client.physics;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import br.puc.pi6.client.player.PlayerInput;

public class PlayerPhysics {

    // “feel” básicos (px e s)
    public float GRAVITY     = -2000f;
    public float ACCEL_X     = 12000f;
    public float MAX_VEL_X   = 260f;
    public float JUMP_VEL_Y  = 700f;
    public float GROUND_DRAG = 0.90f;

    public final Rect bounds;   // em px (AABB)
    public final Vector2 vel;   // px/s
    public boolean onGround = false;

    private final CollisionManager collision;

    public PlayerPhysics(float spawnXpx, float spawnYpx, float widthPx, float heightPx, CollisionManager collision) {
        this.bounds = new Rect(spawnXpx, spawnYpx, widthPx, heightPx);
        this.vel = new Vector2();
        this.collision = collision;
    }

    public void update(float dt, PlayerInput input) {
        // input → aceleração
        float ax = 0f;
        if (input.left)  ax -= ACCEL_X;
        if (input.right) ax += ACCEL_X;

        // integra aceleração + gravidade
        vel.x += ax * dt;
        vel.x = MathUtils.clamp(vel.x, -MAX_VEL_X, MAX_VEL_X);
        vel.y += GRAVITY * dt;

        // movimento eixo X com resolução
        float dx = vel.x * dt;
        float movedX = collision.moveX(bounds, dx);
        if (Math.signum(dx) != Math.signum(movedX)) {
            // bateu em parede → zera vel.x
            vel.x = 0;
        }

        // movimento eixo Y com resolução
        float dy = vel.y * dt;
        boolean hitGround = collision.moveY(bounds, dy);
        if (hitGround) {
            onGround = true;
            vel.y = 0;
        } else {
            onGround = false;
        }

        // pulo
        if (input.jump && onGround) {
            vel.y = JUMP_VEL_Y;
            onGround = false;
        }

        // arrasto horizontal no chão quando sem input
        if (onGround && ax == 0) {
            vel.x *= GROUND_DRAG;
            if (Math.abs(vel.x) < 1f) vel.x = 0f;
        }
    }

    public float centerX() { return bounds.x + bounds.w / 2f; }
    public float centerY() { return bounds.y + bounds.h / 2f; }
}
