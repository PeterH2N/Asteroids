package dk.sdu.petni23.common.components;

import dk.sdu.petni23.gameengine.component.Component;
import dk.sdu.petni23.gameengine.node.Node;

import java.util.function.Consumer;

public class CollisionComponent extends Component {
    public Consumer<Node> onCollision;

    public CollisionComponent(Consumer<Node> onCollision) {
        this.onCollision = onCollision;
    }
    public CollisionComponent() {}
}
