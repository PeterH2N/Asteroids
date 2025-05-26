package dk.sdu.petni23.rendersystem;

import dk.sdu.petni23.common.components.*;
import dk.sdu.petni23.gameengine.component.OptionalComponent;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.node.INodeSPI;
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

    public RenderNode(Entity entity) {
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
