package dk.sdu.petni23.rendersystem;

import dk.sdu.petni23.gameengine.component.Component;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.node.Node;

import java.util.List;

public class RenderNodeSPI extends INodeSPI
{
    public RenderNodeSPI() {
        super(RenderNode.class);
    }
}
