package dk.sdu.petni23.controlsystem;

import dk.sdu.petni23.common.data.GameData;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.services.ISystem;

public class ControlSystem implements ISystem {
    @Override
    public void update(double deltaTime) {
        for (var node : Engine.getNodes(ControlNode.class)) {
            for (var keyCode : node.controlComponent.isDown.keySet()) {
                if (GameData.gameKeys.isDown(keyCode)) {
                    var action = node.controlComponent.isDown.get(keyCode);
                    if (action != null) action.accept(node);
                }
            }
            for (var keyCode : node.controlComponent.isPressed.keySet()) {
                if (GameData.gameKeys.isPressed(keyCode)) {
                    var action = node.controlComponent.isPressed.get(keyCode);
                    if (action != null) action.accept(node);
                }
            }
            for (var keyCode : node.controlComponent.isReleased.keySet()) {
                if (GameData.gameKeys.isReleased(keyCode)) {
                    var action = node.controlComponent.isReleased.get(keyCode);
                    if (action != null) action.accept(node);
                }
            }
        }
    }

    @Override
    public int getPriority() {
        return Priority.PROCESSING.value;
    }
}
