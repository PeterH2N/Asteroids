package dk.sdu.petni23.movesystem;

import dk.sdu.petni23.gameengine.component.Component;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.node.Node;
import java.util.List;

public class MoveNodeSPI extends INodeSPI
{
    public MoveNodeSPI() {
        super(MoveNode.class);
    }
}
