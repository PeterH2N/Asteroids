package dk.sdu.petni23.common.components;

import dk.sdu.petni23.gameengine.component.Component;

public class ScoreComponent extends Component {
    public final int score;
    public ScoreComponent(int score) {
        this.score = score;
    }
}
