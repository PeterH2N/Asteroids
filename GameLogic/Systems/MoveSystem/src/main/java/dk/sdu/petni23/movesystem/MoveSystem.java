package dk.sdu.petni23.movesystem;

import dk.sdu.petni23.common.data.GameData;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.services.ISystem;

public class MoveSystem implements ISystem
{
    @Override
    public void update(double deltaTime)
    {
        for (MoveNode node : Engine.getNodes(MoveNode.class)) {
            var pos = node.positionComponent.position;
            pos.add(node.velocityComponent.velocity.getMultiplied(deltaTime));

            double max = GameData.viewPortWidth / 2;

            // keep on screen
            if (pos.x > max) pos.x = -max + (pos.x - max);
            if (pos.x < -max) pos.x = max - (pos.x + max);
            if (pos.y > max) pos.y = -max + (pos.y - max);
            if (pos.y < -max) pos.y = max - (pos.y + max);

        }
    }

    @Override
    public int getPriority() {
        return Priority.PROCESSING.value;
    }
}
