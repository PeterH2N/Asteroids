package dk.sdu.petni23.durationsystem;

import dk.sdu.petni23.common.components.DurationComponent;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.node.Node;

public class DurationNode extends Node {

    public DurationComponent durationComponent;
    public DurationNode(Entity entity) {
        super(entity);
    }

    public static class SPI extends INodeSPI {
        public SPI() {
            super((Class<? extends Node>) SPI.class.getEnclosingClass());
        }
    }
}
