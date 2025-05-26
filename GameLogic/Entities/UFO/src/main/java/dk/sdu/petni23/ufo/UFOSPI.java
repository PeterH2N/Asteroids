package dk.sdu.petni23.ufo;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.common.util.Vector2D;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;

public class UFOSPI implements IEntitySPI {
    @Override
    public Entity create(Entity parent) {

        Entity ufo = new Entity(UFOSPI.class);

        ufo.add(new PositionComponent());
        ufo.add(new VelocityComponent());
        ufo.add(new DirectionComponent());
        ufo.add(new SizeComponent(1.5));
        ufo.add(new LayerComponent(LayerComponent.Layer.ENEMY));

        var display = ufo.add(new DisplayComponent());
        display.rotateWithDirection = false;
        var scale = 1.5;

        // polygon
        var A = new Vector2D(-0.5, 0).multiply(scale);
        var B = new Vector2D(0.5, 0).multiply(scale);
        var C = new Vector2D(0.2, -0.2).multiply(scale);
        var D = new Vector2D(-0.2, -0.2).multiply(scale);
        var E = new Vector2D(-0.2, 0.2).multiply(scale);
        var F = new Vector2D(0.2, 0.2).multiply(scale);
        var G = new Vector2D(0.1, 0.4).multiply(scale);
        var H = new Vector2D(-0.1, 0.4).multiply(scale);
        ufo.add(new PolygonComponent(A,B,C,D,A,E,F,B,F,G,H,E));
        ufo.add(new AIComponent());
        ufo.add(new RoundComponent());
        var collision = ufo.add(new CollisionComponent());
        collision.onCollision = node -> Engine.removeEntity(ufo);

        return ufo;
    }
}
