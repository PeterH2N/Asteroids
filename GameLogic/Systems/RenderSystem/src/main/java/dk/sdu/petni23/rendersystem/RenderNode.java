package dk.sdu.petni23.rendersystem;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.gameengine.component.OptionalComponent;
import dk.sdu.petni23.gameengine.node.Node;

public class RenderNode extends Node
{
    public DisplayComponent displayComponent;
    public PositionComponent positionComponent;
    @OptionalComponent
    public PolygonComponent polygonComponent;
    @OptionalComponent
    public DirectionComponent directionComponent;
    @OptionalComponent
    public CircleComponent circleComponent;

    @Override
    public void onRemove() {

    }

    @Override
    public void onAdd() {

    }
}
