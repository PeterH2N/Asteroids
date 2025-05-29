module GameEngine {
    requires spring.core;
    requires spring.context;
    requires spring.beans;
    exports dk.sdu.petni23.gameengine.services;
    exports dk.sdu.petni23.gameengine.entity;
    exports dk.sdu.petni23.gameengine.node;
    exports dk.sdu.petni23.gameengine.component;
    exports dk.sdu.petni23.gameengine;
}