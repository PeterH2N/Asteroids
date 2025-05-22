import dk.sdu.petni23.durationsystem.DurationNode;
import dk.sdu.petni23.durationsystem.DurationSystem;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.services.ISystem;

module DurationSystem {
    requires Common;
    requires GameEngine;
    provides INodeSPI with DurationNode.SPI;
    provides ISystem with DurationSystem;
    exports dk.sdu.petni23.durationsystem;
}