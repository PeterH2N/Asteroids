package dk.sdu.petni23.roundsystem;

import dk.sdu.petni23.common.components.RoundComponent;
import dk.sdu.petni23.gameengine.node.Node;

public class RoundNode extends Node {

    public RoundComponent roundComponent;

    @Override
    public void onRemove() {
        RoundSystem.roundEntities.remove(parentEntity);
    }

    @Override
    public void onAdd() {
        RoundSystem.roundEntities.add(parentEntity);
    }
}
