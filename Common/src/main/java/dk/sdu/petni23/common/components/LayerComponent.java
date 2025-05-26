package dk.sdu.petni23.common.components;

import dk.sdu.petni23.gameengine.component.Component;

public class LayerComponent extends Component {

    public final Layer layer;

    public LayerComponent(Layer layer) {
        this.layer = layer;
    }

    public enum Layer {
        PLAYER,
        ENEMY
    }
}
