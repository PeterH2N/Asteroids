package dk.sdu.petni23.asteroid;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.common.util.Vector2D;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;

import java.util.ArrayList;
import java.util.Arrays;
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
        var asteroid = new Entity();
        asteroid.add(positionComponent);
        asteroid.add(new VelocityComponent(new Vector2D(random.nextDouble(-2,2), random.nextDouble(-2,2))));
        asteroid.add(new AngularMomentumComponent(random.nextDouble(-1, 1)));
        asteroid.add(new DirectionComponent());
        asteroid.add(new DisplayComponent());

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

        return asteroid;
    }
}
