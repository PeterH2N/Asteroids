import dk.sdu.petni23.collisionsystem.CollisionNode;
import dk.sdu.petni23.collisionsystem.CollisionSystem;
import dk.sdu.petni23.gameengine.node.Node;
import dk.sdu.petni23.gameengine.services.ISystem;

module CollisionSystem {
    requires Common;
    requires GameEngine;
    provides Node with CollisionNode;
    provides ISystem with CollisionSystem;
    exports dk.sdu.petni23.collisionsystem;
}