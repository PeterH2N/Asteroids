package dk.sdu.petni23.collisionsystem;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.node.Node;

public class CollisionNode extends Node {

    public PositionComponent positionComponent;
    public SizeComponent sizeComponent;
    public CollisionComponent collisionComponent;
    public LayerComponent layerComponent;

    public CollisionNode(Entity entity) {
        super(entity);
    }

    @Override
    public void onRemove() {

    }

    @Override
    public void onAdd() {

    }

    public static class SPI extends INodeSPI {
        public SPI() {
            super((Class<? extends Node>) SPI.class.getEnclosingClass());
        }
    }
}
