import dk.sdu.petni23.gameengine.node.Node;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;
import dk.sdu.petni23.respawnsystem.RespawnNode;
import dk.sdu.petni23.respawnsystem.RespawnSystem;

module RespawnSystem {
    requires Common;
    requires GameEngine;
    provides Node with RespawnNode;
    provides ISystem with RespawnSystem;
    provides IPlugin with RespawnSystem;
    exports dk.sdu.petni23.respawnsystem;
}