package dk.sdu.petni23.bullet;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;

public class BulletSPI implements IEntitySPI {
    @Override
    public Entity create(Entity parent) {
        var position = parent.get(PositionComponent.class);
        var direction = parent.get(DirectionComponent.class);
        var velocity = parent.get(VelocityComponent.class);
        var layer = parent.get(LayerComponent.class);
        if (position == null || direction == null || velocity == null || layer == null) return null;

        var dir = direction.dir.clone();
        var pos = position.position.clone().add(dir.multiply(0.8));
        var vel = velocity.velocity.clone();

        Entity bullet = new Entity(BulletSPI.class);
        bullet.add(new PositionComponent(pos));
        bullet.add(new VelocityComponent(dir.multiply(20).add(vel)));
        bullet.add(new DisplayComponent());
        bullet.add(new CircleComponent(0.15));
        bullet.add(new SizeComponent(0.3));
        var collision = bullet.add(new CollisionComponent());
        collision.onCollision = node -> Engine.get().removeEntity(bullet);
        bullet.add(new LayerComponent(layer.layer));
        bullet.add(new DurationComponent(1.5));
        return bullet;
    }
}
