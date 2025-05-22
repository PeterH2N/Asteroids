import dk.sdu.petni23.controlsystem.ControlNode;
import dk.sdu.petni23.controlsystem.ControlSystem;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.services.ISystem;


module ControlSystem {
    requires javafx.graphics;
    requires Common;
    requires GameEngine;
    provides INodeSPI with ControlNode.SPI;
    provides ISystem with ControlSystem;
    exports dk.sdu.petni23.controlsystem;
}