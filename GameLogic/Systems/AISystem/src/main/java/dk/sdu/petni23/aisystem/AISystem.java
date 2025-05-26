package dk.sdu.petni23.aisystem;

import dk.sdu.petni23.common.util.Vector2D;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.services.ISystem;

import java.util.Random;

public class AISystem implements ISystem {

    static AINode player;
    Random random = new Random();
    @Override
    public void update(double deltaTime) {
        for (var node : Engine.getNodes(AINode.class)) {
            if (node == player) continue; // don't control player with AI of course
            // movement
            if (node.aiComponent.timeSinceChangedDirection >= node.aiComponent.timeUntilNextMove) {
                // choose new direction and time
                node.aiComponent.timeSinceChangedDirection = 0;
                // 50/50 between moving and staying still
                if (random.nextDouble() < 0.5) {
                    node.aiComponent.timeUntilNextMove = random.nextDouble(1,3);
                    node.velocityComponent.velocity.set(0,0);
                } else {
                    node.aiComponent.timeUntilNextMove = random.nextDouble(1,3);
                    node.velocityComponent.velocity.set(new Vector2D(random.nextDouble(-1,1), random.nextDouble(-1,1)).normalize().multiply(4));
                }
            } else {
                node.aiComponent.timeSinceChangedDirection += deltaTime;
            }

            if (player == null) continue;
            // direction
            var pos = player.positionComponent.position;
            var dir = pos.getSubtracted(node.positionComponent.position).getNormalized();
            dir.rotateBy(random.nextDouble(-0.4,0.4)); // aim slack
            node.directionComponent.dir.set(dir);

            // shooting
            if (node.aiComponent.timeSinceLastShot >= 3) {
                var bulletSPI = Engine.getEntitySPI("BulletSPI");
                if (bulletSPI != null) {
                    Engine.addEntity(bulletSPI.create(node.parentEntity));
                    node.aiComponent.timeSinceLastShot = 0;
                }
            } else node.aiComponent.timeSinceLastShot += deltaTime;
        }
    }

    @Override
    public int getPriority() {
        return Priority.PROCESSING.value;
    }
}
