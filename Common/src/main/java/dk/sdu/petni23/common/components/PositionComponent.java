package dk.sdu.petni23.common.components;

import dk.sdu.petni23.common.util.Vector2D;
import dk.sdu.petni23.gameengine.component.Component;

public class PositionComponent extends Component
{
    public final Vector2D position = new Vector2D();

    public PositionComponent(Vector2D position) {
        this.position.set(position);
    }

    public PositionComponent(){}
}
