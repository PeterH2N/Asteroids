import dk.sdu.petni23.durationsystem.DurationNode;
import dk.sdu.petni23.durationsystem.DurationSystem;
import dk.sdu.petni23.gameengine.node.Node;
import dk.sdu.petni23.gameengine.services.ISystem;

module DurationSystem {
    requires Common;
    requires GameEngine;
    provides Node with DurationNode;
    provides ISystem with DurationSystem;
    exports dk.sdu.petni23.durationsystem;
}