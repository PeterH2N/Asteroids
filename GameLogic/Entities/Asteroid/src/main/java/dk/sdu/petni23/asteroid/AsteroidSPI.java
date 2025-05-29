package dk.sdu.petni23.asteroid;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.common.util.Vector2D;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsteroidSPI implements IEntitySPI {
    Random random = new Random();
    @Override
    public Entity create(Entity parent) {
        var positionComponent = parent.get(PositionComponent.class);
        var sizeComponent = parent.get(SizeComponent.class);
        if (sizeComponent == null) return null;
        if (positionComponent == null) positionComponent = new PositionComponent();
        var asteroid = new Entity(AsteroidSPI.class);
        var childPositionComponent = asteroid.add(new PositionComponent(positionComponent.position));
        asteroid.add(new VelocityComponent(new Vector2D(random.nextDouble(-2,2), random.nextDouble(-2,2))));
        asteroid.add(new AngularMomentumComponent(random.nextDouble(-1, 1)));
        asteroid.add(new DirectionComponent());
        asteroid.add(new DisplayComponent());
        asteroid.add(new RoundComponent());

        asteroid.add(new ScoreComponent((int) (5 * sizeComponent.size) / 5 * 5));

        asteroid.add(sizeComponent);
        double radius = sizeComponent.size * 0.5;
        int numVertices = random.nextInt(8, 13);
        double angleStep = 2 * Math.PI / numVertices;
        // make random asteroid shape
        List<Vector2D> points = new ArrayList<>();
        var n = new Vector2D(1,0);
        for (int i = 0; i < numVertices; i++) {
            var v = n.getRotatedBy(angleStep * i);
            double dist = random.nextDouble(radius * 0.5, radius);
            v.normalize().multiply(dist);
            points.add(v);
        }
        asteroid.add(new PolygonComponent(points.toArray(new Vector2D[0])));

        var collision = asteroid.add(new CollisionComponent());

        collision.onCollision = node -> {
            Engine.get().removeEntity(asteroid);

            if (sizeComponent.size <= 3) {
                // particles
                int numParticles = random.nextInt((int) (sizeComponent.size * 2), (int) (sizeComponent.size * 4));
                for (int i = 0; i < numParticles; i++) {
                    Engine.get().addEntity(particle(childPositionComponent, sizeComponent.size));
                }
                return;
            }

            // asteroid splitter
            var size = sizeComponent.size * 0.5;
            Entity child = new Entity(null);
            child.add(new SizeComponent(size));
            child.add(childPositionComponent);
            var child1 = create(child);
            var child2 = create(child);

            // set children to have same velocity
            var vel = asteroid.get(VelocityComponent.class);
            var vel1 = child1.get(VelocityComponent.class);
            var vel2 = child2.get(VelocityComponent.class);
            vel1.velocity.set(vel.velocity);
            vel2.velocity.set(vel.velocity);
            // add some velocity perpendicular to the velocity of the bullet
            var velBullet = node.parentEntity.get(VelocityComponent.class).velocity;
            var perp = velBullet.getPerp();
            var perpN = perp.getNormalized();
            child1.get(PositionComponent.class).position.add(perpN.getMultiplied(size * 0.5));
            child2.get(PositionComponent.class).position.add(perpN.getMultiplied(-size * 0.5));
            perp.multiply(0.05);
            vel1.velocity.add(perp);
            vel2.velocity.add(perp.getMultiplied(-1));

            Engine.get().addEntity(child1);
            Engine.get().addEntity(child2);
        };
        asteroid.add(new LayerComponent(LayerComponent.Layer.ENEMY));

        return asteroid;
    }

    Entity particle(PositionComponent positionComponent, double size) {
        var particle = new Entity(null);
        particle.add(new DisplayComponent());
        particle.add(new CircleComponent(0.1));
        particle.add(new DurationComponent(random.nextDouble(1,2)));
        var pos = positionComponent.position.clone();
        // offset position
        var n = new Vector2D(random.nextDouble(-1,1), random.nextDouble(-1,1)).normalize().multiply(size * random.nextDouble(0.2, 0.7));
        pos.add(n);
        particle.add(new PositionComponent(pos));
        particle.add(new VelocityComponent(n));


        return particle;
    }
}
