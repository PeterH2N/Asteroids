import dk.sdu.petni23.controlsystem.ControlNodeSPI;
import dk.sdu.petni23.controlsystem.ControlSystem;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.services.ISystem;


module ControlSystem {
    requires javafx.graphics;
    requires Common;
    requires GameEngine;
    provides INodeSPI with ControlNodeSPI;
    provides ISystem with ControlSystem;
    exports dk.sdu.petni23.controlsystem;
}