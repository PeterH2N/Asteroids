package dk.sdu.petni23.common.components;

import dk.sdu.petni23.common.util.Vector2D;
import dk.sdu.petni23.gameengine.component.Component;

public class VelocityComponent extends Component
{
    public final Vector2D velocity = new Vector2D();
    public VelocityComponent(Vector2D vel) {
        this.velocity.set(vel);
    }
    public VelocityComponent(){}
}
