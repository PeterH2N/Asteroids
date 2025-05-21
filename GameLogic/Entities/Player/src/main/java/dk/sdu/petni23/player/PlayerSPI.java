package dk.sdu.petni23.player;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;
import dk.sdu.petni23.common.util.Vector2D;
import javafx.scene.input.KeyCode;

import java.util.concurrent.atomic.AtomicInteger;

public class PlayerSPI implements IEntitySPI
{
    @Override
    public Entity create(Entity parent)
    {
        Entity player = new Entity();

        var positionComponent = player.add(new PositionComponent());
        var velocityComponent = player.add(new VelocityComponent());
        var directionComponent = player.add(new DirectionComponent());

        player.add(new DisplayComponent());
        var offset = new Vector2D(0.3, 0);

        // polygon points
        var A = new Vector2D(0.5,0).add(offset);
        var B = new Vector2D(-0.75,0.5).add(offset);
        var C = new Vector2D(-0.75,-0.5).add(offset);
        var F = new Vector2D(-0.5, 0.4).add(offset);
        var G = new Vector2D(-0.5, -0.4).add(offset);

        player.add(new PolygonComponent(A,C,G,F,B));

        // the rocket trail that is visible when boosting
        var trail = new Entity();
        var H = new Vector2D(-0.5, 0.2).add(offset);
        var I = new Vector2D(-0.5, -0.2).add(offset);
        var J = new Vector2D(-1, 0).add(offset);

        trail.add(positionComponent);
        trail.add(directionComponent);
        trail.add(new PolygonComponent(H,I,J));
        trail.add(new DisplayComponent());

        AtomicInteger trailFlicker = new AtomicInteger();
        // controls
        var control = player.add(new ControlComponent());
        control.isDown.put(KeyCode.LEFT, node -> directionComponent.dir.rotateBy(0.1).normalize());
        control.isDown.put(KeyCode.RIGHT, node -> directionComponent.dir.rotateBy(-0.1).normalize());
        control.isDown.put(KeyCode.UP, node -> {
            // making trail flicker
            int flicker = trailFlicker.incrementAndGet();
            if (flicker > 8 && flicker < 11)
                Engine.removeEntity(trail);
            else if (flicker > 11) {
                Engine.addEntity(trail);
                trailFlicker.set(0);
            }
            velocityComponent.velocity.add(directionComponent.dir.getMultiplied(0.3));
            if (velocityComponent.velocity.getLength() > 8) velocityComponent.velocity.normalize().multiply(7);
        });

        // showing trail or not
        control.isPressed.put(KeyCode.UP, node -> Engine.addEntity(trail));
        control.isReleased.put(KeyCode.UP, node -> Engine.removeEntity(trail));

        // shooting
        IEntitySPI bulletSPI = Engine.getEntitySPI("BulletSPI");
        control.isPressed.put(KeyCode.SPACE, node -> {
           if (bulletSPI == null) return;
           Engine.addEntity(bulletSPI.create(player));
        });

        return player;
    }
}
