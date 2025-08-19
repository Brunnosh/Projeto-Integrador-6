package br.puc.pi6.client.input;

import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntIntMap;

public class ActionBindings {

    
    private final IntIntMap keyToAction = new IntIntMap(); // Unordered map (par) tecla e ação
    
    private final IntArray keys = new IntArray();//Todas as teclas que tem uma binding

    //"Acopla" uma tecla a uma ação (declarada em actions.java)
    public void bind(int key, Action action) {
        if (!keyToAction.containsKey(key)) keys.add(key);
        keyToAction.put(key, action.ordinal());
    }

    //duh
    public void unbind(int key) {
        keyToAction.remove(key, 0);
        keys.removeValue(key);
    }

    //recebe a tecla e descobre a ação acoplada a ela
    public Action actionFor(int key) {
        int ord = keyToAction.get(key, -1);
        return ord >= 0 ? Action.values()[ord] : null;
    }

    /** Varre apenas as teclas bindadas; quando JUST PRESSED, chama o handler com a ação. */
    public void scanJustPressed(Consumer<Action> onJustPressed) {
        for (int i = 0; i < keys.size; i++) {
            int key = keys.get(i);
            if (Gdx.input.isKeyJustPressed(key)) {
                Action a = actionFor(key);
                if (a != null) onJustPressed.accept(a);
            }
        }
    }
}

