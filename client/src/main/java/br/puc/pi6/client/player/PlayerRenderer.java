package br.puc.pi6.client.player;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import br.puc.pi6.client.physics.PlayerPhysics;

public class PlayerRenderer {
    public void draw(ShapeRenderer shapes, PlayerPhysics p) {
        shapes.rect(p.bounds.x, p.bounds.y, p.bounds.w, p.bounds.h);
    }
}