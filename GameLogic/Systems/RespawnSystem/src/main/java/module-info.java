import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;
import dk.sdu.petni23.respawnsystem.RespawnNode;
import dk.sdu.petni23.respawnsystem.RespawnSystem;

module RespawnSystem {
    requires Common;
    requires GameEngine;
    provides INodeSPI with RespawnNode.SPI;
    provides ISystem with RespawnSystem;
    provides IPlugin with RespawnSystem;
    exports dk.sdu.petni23.respawnsystem;
}