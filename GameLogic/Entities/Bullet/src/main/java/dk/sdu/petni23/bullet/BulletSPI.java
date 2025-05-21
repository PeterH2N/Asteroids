package dk.sdu.petni23.bullet;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.common.util.Vector2D;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;

public class BulletSPI implements IEntitySPI {
    @Override
    public Entity create(Entity parent) {
        var position = parent.get(PositionComponent.class);
        var direction = parent.get(DirectionComponent.class);
        var velocity = parent.get(VelocityComponent.class);
        if (position == null || direction == null || velocity == null) return null;

        var dir = direction.dir.clone();
        var pos = position.position.clone().add(dir.multiply(0.8));
        var vel = velocity.velocity;

        Entity bullet = new Entity();
        bullet.add(new PositionComponent(pos));
        bullet.add(new VelocityComponent(dir.multiply(20).add(vel)));
        bullet.add(new DisplayComponent());
        bullet.add(new CircleComponent(0.15));
        bullet.add(new DurationComponent(2));
        return bullet;
    }
}
