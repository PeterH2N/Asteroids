import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.services.ISystem;
import dk.sdu.petni23.movesystem.MoveNode;
import dk.sdu.petni23.movesystem.MoveSystem;

module MoveSystem {
    requires javafx.graphics;
    requires Common;
    requires GameEngine;
    provides ISystem with MoveSystem;
    provides INodeSPI with MoveNode.SPI;
    exports dk.sdu.petni23.movesystem;
}