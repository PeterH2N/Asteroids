package dk.sdu.petni23.aisystem;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.gameengine.node.Node;

public class AINode extends Node {
    public AIComponent aiComponent;
    public LayerComponent layerComponent;
    public PositionComponent positionComponent;
    public VelocityComponent velocityComponent;
    public DirectionComponent directionComponent;

    @Override
    public void onRemove() {
        if (layerComponent.layer == LayerComponent.Layer.PLAYER)
            AISystem.player = null;
    }

    @Override
    public void onAdd() {
        if (layerComponent.layer == LayerComponent.Layer.PLAYER)
            AISystem.player = this;
    }
}
