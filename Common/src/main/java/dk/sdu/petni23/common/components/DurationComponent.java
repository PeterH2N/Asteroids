package dk.sdu.petni23.common.components;

import dk.sdu.petni23.gameengine.component.Component;

public class DurationComponent extends Component {
    public final double duration;
    public double timePassed = 0;

    public DurationComponent(double duration) {
        this.duration = duration;
    }
}
