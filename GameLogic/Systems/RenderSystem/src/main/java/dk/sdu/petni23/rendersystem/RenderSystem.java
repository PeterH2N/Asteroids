package dk.sdu.petni23.rendersystem;

import dk.sdu.petni23.common.data.GameData;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;
import dk.sdu.petni23.common.util.Vector2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RenderSystem implements ISystem, IPlugin
{
    private static Canvas canvas;
    @Override
    public void update(double deltaTime)
    {
        var gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, GameData.getDisplayWidth(), GameData.getDisplayHeight());
        gc.setStroke(Color.WHITE);
        gc.setFill(Color.WHITE);
        for (var node : Engine.get().getNodes(RenderNode.class)) {
            if (!node.displayComponent.visible) continue;
            renderPolygon(node, gc);
            renderCircle(node, gc);
        }

    }

    @Override
    public int getPriority() {
        return Priority.RENDERING.value;
    }

    void renderCircle(RenderNode node, GraphicsContext gc) {
        if (node.circleComponent == null) return;
        double r = node.circleComponent.radius * GameData.getPPM();
        var pos = toScreenSpace(node.positionComponent.position);
        gc.fillOval(pos.x, pos.y, r, r);
    }

    void renderPolygon(RenderNode node, GraphicsContext gc) {
        if (node.polygonComponent == null) return;
        double[][] renderPoints = new double[2][node.polygonComponent.points.size()];
        for (int i = 0; i < node.polygonComponent.points.size(); i++) {
            var point = new Vector2D(node.polygonComponent.points.get(i));
            // rotate based on direction
            if (node.directionComponent != null && node.displayComponent.rotateWithDirection) {
                double angle = node.directionComponent.dir.getAngle();
                point.rotateBy(angle);
            }
            point.add(node.positionComponent.position);
            var screenPoint = toScreenSpace(point);
            renderPoints[0][i] = screenPoint.x;
            renderPoints[1][i] = screenPoint.y;
        }

        gc.strokePolygon(renderPoints[0], renderPoints[1], renderPoints[0].length);
    }

    @Override
    public void start()
    {
        canvas = new Canvas();
        GameData.gameWindow.getChildren().addFirst(canvas);
        canvas.widthProperty().bind(GameData.gameWindow.widthProperty());
        canvas.heightProperty().bind(GameData.gameWindow.heightProperty());
    }

    @Override
    public void stop() {
        GameData.gameWindow.getChildren().remove(canvas);
        canvas = null;
    }

    Vector2D toScreenSpace(double x, double y) {
        Vector2D origin = new Vector2D((double) GameData.getDisplayWidth() / 2, (double) GameData.getDisplayHeight() / 2);
        return origin.getAdded(new Vector2D(x * GameData.getPPM(), -y * GameData.getPPM()));
    }

    Vector2D toScreenSpace(Vector2D v) {
        return toScreenSpace(v.x, v.y);
    }
}
