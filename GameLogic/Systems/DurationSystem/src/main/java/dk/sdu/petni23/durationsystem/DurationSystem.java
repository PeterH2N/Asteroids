package dk.sdu.petni23.durationsystem;

import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.services.ISystem;

public class DurationSystem implements ISystem {
    @Override
    public void update(double deltaTime) {
        for (var node : Engine.get().getNodes(DurationNode.class)) {
            node.durationComponent.timePassed += deltaTime;
            if (node.durationComponent.timePassed >= node.durationComponent.duration) {
                 Engine.get().removeEntity(node.parentEntity);
            }
        }
    }

    @Override
    public int getPriority() {
        return Priority.PROCESSING.value;
    }
}
