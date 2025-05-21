import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;
import dk.sdu.petni23.rendersystem.RenderNodeSPI;
import dk.sdu.petni23.rendersystem.RenderSystem;

module RenderSystem {
    requires javafx.graphics;
    requires Common;
    requires GameEngine;
    exports dk.sdu.petni23.rendersystem;
    provides INodeSPI with RenderNodeSPI;
    provides ISystem with RenderSystem;
    provides IPlugin with RenderSystem;
}