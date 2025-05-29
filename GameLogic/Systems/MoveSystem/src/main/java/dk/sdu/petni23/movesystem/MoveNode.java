package dk.sdu.petni23.movesystem;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.gameengine.component.OptionalComponent;
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

    @Override
    public void onRemove() {

    }

    @Override
    public void onAdd() {

    }
}
