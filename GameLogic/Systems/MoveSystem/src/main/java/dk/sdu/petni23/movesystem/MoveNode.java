package dk.sdu.petni23.movesystem;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.gameengine.component.OptionalComponent;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.node.Node;

public class MoveNode extends Node
{
    public PositionComponent positionComponent;
    public VelocityComponent velocityComponent;
    @OptionalComponent
    public SizeComponent sizeComponent;
    @OptionalComponent
    public AngularMomentumComponent angularMomentumComponent;
    @OptionalComponent
    public DirectionComponent directionComponent;

    public MoveNode(Entity entity) {
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
