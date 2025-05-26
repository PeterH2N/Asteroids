package dk.sdu.petni23.roundsystem;

import dk.sdu.petni23.common.components.RoundComponent;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.node.Node;

public class RoundNode extends Node {

    public RoundComponent roundComponent;
    public RoundNode(Entity entity) {
        super(entity);
    }

    @Override
    public void onRemove() {
        RoundSystem.roundEntities.remove(parentEntity);
    }

    @Override
    public void onAdd() {
        RoundSystem.roundEntities.add(parentEntity);
    }

    public static class SPI extends INodeSPI {
        public SPI() {
            super((Class<? extends Node>) SPI.class.getEnclosingClass());
        }
    }
}
