package dk.sdu.petni23.respawnsystem;

import dk.sdu.petni23.common.components.DirectionComponent;
import dk.sdu.petni23.common.components.DisplayComponent;
import dk.sdu.petni23.common.components.PolygonComponent;
import dk.sdu.petni23.common.components.PositionComponent;
import dk.sdu.petni23.common.data.GameData;
import dk.sdu.petni23.common.util.Vector2D;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;

import java.util.ArrayList;
import java.util.List;

public class RespawnSystem implements ISystem, IPlugin {
    // only the player can respawn.
    static Entity player;
    private static final double delay = 2;
    private static double timeSinceDeath = 0;
    private static int lives = 3;
    static List<Entity> indicators = new ArrayList<>();

    @Override
    public void update(double deltaTime) {
        if (player != null) return;

        timeSinceDeath += deltaTime;
        if (timeSinceDeath >= delay && lives > 1) {
            var playerSPI = Engine.getEntitySPI("PlayerSPI");
            if (playerSPI == null) return;
            Engine.addEntity(playerSPI.create(null));
            timeSinceDeath = 0;
            lives--;
        }

        if (lives <= 0) GameData.gameOver();
    }

    @Override
    public int getPriority() {
        return Priority.PROCESSING.value;
    }

    @Override
    public void start() {
        // indicators will be taken from player's components
        var spi = Engine.getEntitySPI("PlayerSPI");
        if (spi == null) return;
        var polygon = spi.create(null).get(PolygonComponent.class);
        for (int i = 0; i < lives; i++) {
            indicators.add(Engine.addEntity(makeIndicator(i, polygon)));
        }
    }

    @Override
    public void stop() {
        lives = 3;
        indicators.clear();
    }

    Entity makeIndicator(int i, PolygonComponent pc) {
        var indicator = new Entity(null);
        var pos = indicator.add(new PositionComponent()).position;
        indicator.add(new DirectionComponent(new Vector2D(0,1)));
        indicator.add(new DisplayComponent());
        indicator.add(pc);

        pos.y = GameData.viewPortWidth * 0.5 - 2;
        pos.x = -GameData.viewPortWidth * 0.5 + 2;
        pos.x += 2 * i;

        return indicator;
    }
}
