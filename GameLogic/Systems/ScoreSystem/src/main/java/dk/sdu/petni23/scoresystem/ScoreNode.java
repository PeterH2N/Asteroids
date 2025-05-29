package dk.sdu.petni23.scoresystem;

import dk.sdu.petni23.common.components.ScoreComponent;
import dk.sdu.petni23.gameengine.node.Node;

public class ScoreNode extends Node {
    public ScoreComponent scoreComponent;
    @Override
    public void onRemove() {
        ScoreSystem.score += scoreComponent.score;
    }

    @Override
    public void onAdd() {

    }
}
