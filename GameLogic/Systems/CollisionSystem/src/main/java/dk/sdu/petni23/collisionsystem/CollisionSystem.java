package dk.sdu.petni23.collisionsystem;

import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.services.ISystem;

public class CollisionSystem implements ISystem {
    @Override
    public void update(double deltaTime) {
        var nodes = Engine.get().getNodes(CollisionNode.class);
        for (var node1 : nodes) {
            if (!node1.collisionComponent.active) continue;
            for (var node2 : nodes) {
                if (!node2.collisionComponent.active) continue;
                if (node1 == node2) continue;
                if (node1.layerComponent.layer == node2.layerComponent.layer) continue;

                // collision
                var r1 = node1.sizeComponent.size * 0.5;
                var r2 = node2.sizeComponent.size * 0.5;

                var pos1 = node1.positionComponent.position;
                var pos2 = node2.positionComponent.position;

                var distSqr = pos1.distanceSq(pos2);
                var radiusSqr = (r1 + r2) * (r1 + r2);

                if (distSqr <= radiusSqr) {
                    node1.collisionComponent.onCollision.accept(node2);
                }


            }
        }
    }

    @Override
    public int getPriority() {
        return Priority.POSTPROCESSING.value;
    }
}
