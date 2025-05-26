package dk.sdu.petni23.common.components;

import dk.sdu.petni23.common.util.Vector2D;
import dk.sdu.petni23.gameengine.component.Component;

public class AIComponent extends Component {
    public double timeSinceChangedDirection = 0;
    public double timeUntilNextMove = 0;

    public double timeSinceLastShot = Math.random();
}
