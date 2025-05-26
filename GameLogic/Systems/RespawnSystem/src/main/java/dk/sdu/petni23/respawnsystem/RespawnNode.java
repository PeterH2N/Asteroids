package dk.sdu.petni23.respawnsystem;

import dk.sdu.petni23.common.components.RespawnComponent;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.node.Node;

public class RespawnNode extends Node {

    public RespawnComponent respawnComponent;

    public RespawnNode(Entity entity) {
        super(entity);
    }

    @Override
    public void onRemove() {
        RespawnSystem.player = null;
        Engine.removeEntity(RespawnSystem.indicators.removeLast());
    }

    @Override
    public void onAdd() {
        RespawnSystem.player = parentEntity;
    }

    public static class SPI extends INodeSPI {
        public SPI() {
            super((Class<? extends Node>) SPI.class.getEnclosingClass());
        }
    }
}
