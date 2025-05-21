package dk.sdu.petni23.common.components;
import dk.sdu.petni23.common.util.Vector2D;
import dk.sdu.petni23.gameengine.component.Component;

import java.util.ArrayList;
import java.util.List;

public class PolygonComponent extends Component
{
    public final List<Vector2D> points;

    public PolygonComponent(Vector2D... points) {
        this.points = new ArrayList<>();
        this.points.addAll(List.of(points));
    }

}
