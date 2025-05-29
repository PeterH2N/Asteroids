import dk.sdu.petni23.gameengine.node.Node;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;
import dk.sdu.petni23.scoresystem.ScoreNode;
import dk.sdu.petni23.scoresystem.ScoreSystem;

module ScoreSystem {
    requires Common;
    requires GameEngine;
    requires javafx.graphics;
    provides Node with ScoreNode;
    provides ISystem with ScoreSystem;
    provides IPlugin with ScoreSystem;
    exports dk.sdu.petni23.scoresystem;
}