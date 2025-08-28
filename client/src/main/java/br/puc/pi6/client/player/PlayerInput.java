package br.puc.pi6.client.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerInput {
    public boolean left, right, jump;

    public void poll() {
        left  = Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT);
        right = Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        jump  = Gdx.input.isKeyJustPressed(Input.Keys.SPACE) ||
                Gdx.input.isKeyJustPressed(Input.Keys.W) ||
                Gdx.input.isKeyJustPressed(Input.Keys.UP);
    }
}
