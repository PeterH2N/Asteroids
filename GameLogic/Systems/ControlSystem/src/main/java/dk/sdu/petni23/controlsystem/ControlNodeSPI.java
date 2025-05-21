package dk.sdu.petni23.controlsystem;

import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.node.Node;

public class ControlNodeSPI extends INodeSPI {
    public ControlNodeSPI() {
        super(ControlNode.class);
    }
}
