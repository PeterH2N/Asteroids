import dk.sdu.petni23.durationsystem.DurationNodeSPI;
import dk.sdu.petni23.durationsystem.DurationSystem;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.services.ISystem;

module DurationSystem {
    requires Common;
    requires GameEngine;
    provides INodeSPI with DurationNodeSPI;
    provides ISystem with DurationSystem;
    exports dk.sdu.petni23.durationsystem;
}