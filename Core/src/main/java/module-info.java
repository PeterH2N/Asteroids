import dk.sdu.petni23.gameengine.entity.IEntitySPI;
import dk.sdu.petni23.gameengine.node.Node;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;

module Core {
    requires spring.context;
    requires javafx.controls;
    requires Common;
    requires GameEngine;
    opens dk.sdu.petni23.main to javafx.graphics, spring.core;
    exports dk.sdu.petni23.main;
    uses ISystem;
    uses IEntitySPI;
    uses Node;
    uses IPlugin;
}