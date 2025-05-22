package dk.sdu.petni23.common.components;

import dk.sdu.petni23.gameengine.component.Component;

public class AngularMomentumComponent extends Component {
    public double momentum = 0;

    public AngularMomentumComponent(){}
    public AngularMomentumComponent(double momentum) {
        this.momentum = momentum;
    }
}
