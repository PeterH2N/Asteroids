package dk.sdu.petni23.inputsystem;

import dk.sdu.petni23.common.data.GameData;
import dk.sdu.petni23.common.data.GameKeys;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class InputSystem implements ISystem, IPlugin {
    @Override
    public void update(double deltaTime) {
        GameData.gameKeys.update();
        if (GameData.gameKeys.isDown(KeyCode.SPACE))
            if (GameData.gameKeys.isPressed(KeyCode.SPACE)) System.out.println("space");
    }

    @Override
    public int getPriority() {
        return Priority.POSTPROCESSING.value;
    }

    @Override
    public void start() {
        Scene scene = GameData.scene;
        // set key values for game keys
        scene.setOnKeyPressed(event -> GameData.gameKeys.setInput(event.getCode(), true));
        scene.setOnKeyReleased(event -> GameData.gameKeys.setInput(event.getCode(), false));
    }

    @Override
    public void stop() {

    }
}
