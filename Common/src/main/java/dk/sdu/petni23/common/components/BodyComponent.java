package dk.sdu.petni23.common.components;

import dk.sdu.petni23.common.shape.Shape;
import dk.sdu.petni23.gameengine.component.Component;

public class BodyComponent extends Component
{
    public final double mass;
    public final double invMass;
    public final Shape shape;

    public BodyComponent(double mass, Shape shape) {
        this.mass = mass;
        this.invMass = 1.0 / mass;
        this.shape = shape;
    }
}
