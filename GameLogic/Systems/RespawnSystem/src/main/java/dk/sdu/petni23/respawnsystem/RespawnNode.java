package dk.sdu.petni23.respawnsystem;

import dk.sdu.petni23.common.components.CollisionComponent;
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
        RespawnSystem.timeSinceDeath = 0;
        RespawnSystem.lives--;
    }

    @Override
    public void onAdd() {
        RespawnSystem.player = parentEntity;
        parentEntity.get(CollisionComponent.class).active = false;
    }

    public static class SPI extends INodeSPI {
        public SPI() {
            super((Class<? extends Node>) SPI.class.getEnclosingClass());
        }
    }
}
