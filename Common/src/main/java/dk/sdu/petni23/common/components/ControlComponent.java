package dk.sdu.petni23.common.components;

import dk.sdu.petni23.gameengine.component.Component;
import dk.sdu.petni23.gameengine.node.Node;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ControlComponent extends Component
{
    public final Map<KeyCode, Consumer<Node>> isDown = new HashMap<>();
    public final Map<KeyCode, Consumer<Node>> isPressed = new HashMap<>();
    public final Map<KeyCode, Consumer<Node>> isReleased = new HashMap<>();

}
