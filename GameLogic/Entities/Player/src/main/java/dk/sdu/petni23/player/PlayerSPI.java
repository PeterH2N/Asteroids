package dk.sdu.petni23.player;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;
import dk.sdu.petni23.common.util.Vector2D;
import javafx.scene.input.KeyCode;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerSPI implements IEntitySPI
{
    @Override
    public Entity create(Entity parent)
    {
        Entity player = new Entity(PlayerSPI.class);

        var positionComponent = player.add(new PositionComponent());
        var velocityComponent = player.add(new VelocityComponent());
        var directionComponent = player.add(new DirectionComponent());
        player.add(new SizeComponent(1.5));
        player.add(new LayerComponent(LayerComponent.Layer.PLAYER));

        var display = player.add(new DisplayComponent());
        var offset = new Vector2D(0.3, 0);

        // polygon points
        var A = new Vector2D(0.5,0).add(offset);
        var B = new Vector2D(-0.75,0.5).add(offset);
        var C = new Vector2D(-0.75,-0.5).add(offset);
        var F = new Vector2D(-0.5, 0.2).add(offset);
        var G = new Vector2D(-0.5, -0.2).add(offset);

        player.add(new PolygonComponent(A,C,G,F,B));

        // the rocket trail that is visible when boosting
        var trail = new Entity(null);
        var H = new Vector2D(-0.5, 0.2).add(offset);
        var I = new Vector2D(-0.5, -0.2).add(offset);
        var J = new Vector2D(-1, 0).add(offset);

        trail.add(positionComponent);
        trail.add(directionComponent);
        trail.add(new PolygonComponent(H,I,J));
        trail.add(display);

        AtomicInteger trailFlicker = new AtomicInteger();
        // controls
        var control = player.add(new ControlComponent());
        control.isDown.put(KeyCode.LEFT, node -> directionComponent.dir.rotateBy(0.1).normalize());
        control.isDown.put(KeyCode.RIGHT, node -> directionComponent.dir.rotateBy(-0.1).normalize());
        control.isDown.put(KeyCode.UP, node -> {
            // making trail flicker
            int flicker = trailFlicker.incrementAndGet();
            if (flicker > 8 && flicker < 11)
                Engine.get().removeEntity(trail);
            else if (flicker > 11) {
                Engine.get().addEntity(trail);
                trailFlicker.set(0);
            }
            velocityComponent.velocity.add(directionComponent.dir.getMultiplied(0.3));
            if (velocityComponent.velocity.getLength() > 8) velocityComponent.velocity.normalize().multiply(8);
        });

        // showing trail or not
        control.isPressed.put(KeyCode.UP, node -> Engine.get().addEntity(trail));
        control.isReleased.put(KeyCode.UP, node -> Engine.get().removeEntity(trail));

        // shooting
        IEntitySPI bulletSPI = Engine.get().getEntitySPI("BulletSPI");
        control.isPressed.put(KeyCode.SPACE, node -> {
           if (bulletSPI == null) return;
           Engine.get().addEntity(bulletSPI.create(player));
        });

        var collision = player.add(new CollisionComponent());
        collision.onCollision = node -> {
            Engine.get().removeEntity(player);
            Engine.get().removeEntity(trail);

            // particles
            Engine.get().addEntity(line(new PolygonComponent(A,B), positionComponent, directionComponent, velocityComponent));
            Engine.get().addEntity(line(new PolygonComponent(A,C), positionComponent, directionComponent, velocityComponent));
            Engine.get().addEntity(line(new PolygonComponent(F,G), positionComponent, directionComponent, velocityComponent));

        };
        player.add(new RespawnComponent());
        player.add(new AIComponent());

        return player;
    }

    Entity line(PolygonComponent polygonComponent, PositionComponent positionComponent, DirectionComponent directionComponent, VelocityComponent velocityComponent) {
        var line = new Entity(null);
        Random random = new Random();

        var lineDisplay = new DisplayComponent();
        line.add(lineDisplay);
        var duration = new DurationComponent(2);
        line.add(duration);
        line.add(polygonComponent);
        line.add(new PositionComponent(positionComponent.position));
        line.add(new DirectionComponent(directionComponent.dir));
        line.add(new AngularMomentumComponent(random.nextDouble(-1,1)));
        line.add(new VelocityComponent(velocityComponent.velocity.getMultiplied(0.2)));

        return line;
    }
}
