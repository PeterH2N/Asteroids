import dk.sdu.petni23.aisystem.AINode;
import dk.sdu.petni23.aisystem.AISystem;
import dk.sdu.petni23.gameengine.node.Node;
import dk.sdu.petni23.gameengine.services.ISystem;

module AISystem {
    requires Common;
    requires GameEngine;
    provides ISystem with AISystem;
    provides Node with AINode;
    exports dk.sdu.petni23.aisystem;
}