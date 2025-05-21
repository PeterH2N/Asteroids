package dk.sdu.petni23.common.data;


import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class GameKeys
{
    private final Map<KeyCode, Boolean> inputs = new HashMap<>();
    private final Map<KeyCode, Boolean> pInputs = new HashMap<>();

    public GameKeys() {
        KeyCode[] keyCodes = KeyCode.values();
        for (KeyCode keyCode : keyCodes) {
            inputs.put(keyCode, false);
            pInputs.put(keyCode, false);
        }
    }

    public void update() {
        for (KeyCode keyCode : inputs.keySet()) {
            pInputs.put(keyCode, inputs.get(keyCode));
        }
    }

    public void setInput(KeyCode k, boolean b) {
        inputs.put(k, b);
    }

    public boolean isDown(KeyCode k) {
        return inputs.get(k);
    }

    public boolean isPressed(KeyCode k) {
        return inputs.get(k) && !pInputs.get(k);
    }

    public boolean isReleased(KeyCode k) {
        return !inputs.get(k) && pInputs.get(k);
    }
}
