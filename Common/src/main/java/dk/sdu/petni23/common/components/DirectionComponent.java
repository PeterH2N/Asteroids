package dk.sdu.petni23.common.components;

import dk.sdu.petni23.common.util.Vector2D;
import dk.sdu.petni23.gameengine.component.Component;

public class DirectionComponent extends Component {
    public final Vector2D dir = new Vector2D(1,0);

    public DirectionComponent(Vector2D dir) {
        this.dir.set(dir);
    }

    public DirectionComponent(){}
}
