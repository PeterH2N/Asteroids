package dk.sdu.petni23.movesystem;

import dk.sdu.petni23.common.components.PositionComponent;
import dk.sdu.petni23.common.components.VelocityComponent;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.node.Node;

public class MoveNode extends Node
{
    public PositionComponent positionComponent;
    public VelocityComponent velocityComponent;

    public MoveNode(Entity entity) {
        super(entity);
    }
}
