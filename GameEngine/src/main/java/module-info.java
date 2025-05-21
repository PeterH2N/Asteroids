module GameEngine {
    uses dk.sdu.petni23.gameengine.node.INodeSPI;
    uses dk.sdu.petni23.gameengine.services.ISystem;
    uses dk.sdu.petni23.gameengine.entity.IEntitySPI;
    uses dk.sdu.petni23.gameengine.services.IPlugin;
    exports dk.sdu.petni23.gameengine.services;
    exports dk.sdu.petni23.gameengine.entity;
    exports dk.sdu.petni23.gameengine.node;
    exports dk.sdu.petni23.gameengine.component;
    exports dk.sdu.petni23.gameengine;
}