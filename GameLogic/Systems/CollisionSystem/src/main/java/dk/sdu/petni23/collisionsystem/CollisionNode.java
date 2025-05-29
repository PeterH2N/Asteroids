package dk.sdu.petni23.collisionsystem;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.gameengine.node.Node;

public class CollisionNode extends Node {

    public PositionComponent positionComponent;
    public SizeComponent sizeComponent;
    public CollisionComponent collisionComponent;
    public LayerComponent layerComponent;

    @Override
    public void onRemove() {

    }

    @Override
    public void onAdd() {

    }
}
