import dk.sdu.petni23.gameengine.node.Node;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;
import dk.sdu.petni23.roundsystem.RoundNode;
import dk.sdu.petni23.roundsystem.RoundSystem;


module RoundSystem {
    requires javafx.graphics;
    requires Common;
    requires GameEngine;
    provides ISystem with RoundSystem;
    provides Node with RoundNode;
    provides IPlugin with RoundSystem;
    exports dk.sdu.petni23.roundsystem;
}