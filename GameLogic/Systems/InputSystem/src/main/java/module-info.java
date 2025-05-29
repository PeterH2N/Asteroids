import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.inputsystem.InputSystem;
import dk.sdu.petni23.gameengine.services.ISystem;


module InputSystem {
    requires javafx.graphics;
    requires Common;
    requires GameEngine;
    provides ISystem with InputSystem;
    provides IPlugin with InputSystem;
    exports dk.sdu.petni23.inputsystem;
}