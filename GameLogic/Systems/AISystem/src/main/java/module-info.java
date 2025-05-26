import dk.sdu.petni23.aisystem.AINode;
import dk.sdu.petni23.aisystem.AISystem;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.services.ISystem;

module AISystem {
    requires Common;
    requires GameEngine;
    provides ISystem with AISystem;
    provides INodeSPI with AINode.SPI;
    exports dk.sdu.petni23.aisystem;
}