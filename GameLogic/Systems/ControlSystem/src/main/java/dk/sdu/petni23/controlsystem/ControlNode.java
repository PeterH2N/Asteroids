package dk.sdu.petni23.controlsystem;

import dk.sdu.petni23.common.components.ControlComponent;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.node.Node;

public class ControlNode extends Node {

    public ControlComponent controlComponent;
    public ControlNode(Entity entity) {
        super(entity);
    }


    public static class SPI extends INodeSPI {
        public SPI() {
            super((Class<? extends Node>) SPI.class.getEnclosingClass());
        }
    }
}
