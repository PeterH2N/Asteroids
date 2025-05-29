package dk.sdu.petni23.respawnsystem;

import dk.sdu.petni23.common.components.CollisionComponent;
import dk.sdu.petni23.common.components.RespawnComponent;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.node.Node;

public class RespawnNode extends Node {

    public RespawnComponent respawnComponent;

    @Override
    public void onRemove() {
        RespawnSystem.player = null;
        Engine.get().removeEntity(RespawnSystem.indicators.removeLast());
        RespawnSystem.timeSinceDeath = 0;
        RespawnSystem.lives--;
    }

    @Override
    public void onAdd() {
        RespawnSystem.player = parentEntity;
        parentEntity.get(CollisionComponent.class).active = false;
    }
}
