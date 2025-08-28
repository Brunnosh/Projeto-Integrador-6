package br.puc.pi6.client.physics;

import br.puc.pi6.client.utils.Constants;
import br.puc.pi6.client.world.GameWorld;

public class CollisionManager {

    private final GameWorld world;
    

    public CollisionManager(GameWorld world) {
        this.world = world;
    }


    // recebe uma AABB e checa se colide com o mundo
    public boolean collides(float x, float y, float w, float h) {
        int startX = Math.max(0, (int)Math.floor(x / Constants.TILE_SIZE_PX));
        int endX   = Math.min(world.getWidth()-1,  (int)Math.floor((x + w - 1) / Constants.TILE_SIZE_PX));
        int startY = Math.max(0, (int)Math.floor(y / Constants.TILE_SIZE_PX));
        int endY   = Math.min(world.getHeight()-1, (int)Math.floor((y + h - 1) / Constants.TILE_SIZE_PX));
        for (int tx = startX; tx <= endX; tx++) {
            for (int ty = startY; ty <= endY; ty++) {
                if (world.isSolid(tx, ty)) return true;
            }
        }
        return false;
    }

    // move no eixo X resolvendo colisão
    public float moveX(Rect b, float dx) {
        if (dx == 0) return 0;
        float step = Math.signum(dx);
        float moved = 0f;
        while (Math.abs(moved) < Math.abs(dx)) {
            float candidate = b.x + step;
            if (collides(candidate, b.y, b.w, b.h)) break;
            b.x = candidate;
            moved += step;
        }
        return moved;
    }

    // move no eixo Y resolvendo colisão; retorna true se tocou chão no movimento descendente
    public boolean moveY(Rect b, float dy) {
        if (dy == 0) return false;
        float step = Math.signum(dy);
        boolean hitGround = false;
        float moved = 0f;
        while (Math.abs(moved) < Math.abs(dy)) {
            float candidate = b.y + step;
            if (collides(b.x, candidate, b.w, b.h)) {
                if (step < 0) hitGround = true; // estava descendo
                break;
            }
            b.y = candidate;
            moved += step;
        }
        return hitGround;
    }
}
