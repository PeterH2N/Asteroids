import dk.sdu.petni23.controlsystem.ControlNode;
import dk.sdu.petni23.controlsystem.ControlSystem;
import dk.sdu.petni23.gameengine.node.Node;
import dk.sdu.petni23.gameengine.services.ISystem;


module ControlSystem {
    requires javafx.graphics;
    requires Common;
    requires GameEngine;
    provides Node with ControlNode;
    provides ISystem with ControlSystem;
    exports dk.sdu.petni23.controlsystem;
}