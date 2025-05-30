package dk.sdu.petni23.respawnsystem;

import dk.sdu.petni23.common.components.*;
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
    static double timeSinceDeath;
    static int lives;
    static List<Entity> indicators = new ArrayList<>();
    static double flicker;

    @Override
    public void update(double deltaTime) {
        timeSinceDeath += deltaTime;
        if (player == null) {
            if (timeSinceDeath >= delay && lives > 0) {
                var playerSPI = Engine.get().getEntitySPI("PlayerSPI");
                if (playerSPI == null) return;
                var p = Engine.get().addEntity(playerSPI.create(null));
                p.get(CollisionComponent.class).active = false;
            }
            if (lives <= 0) {
                GameData.gameOver();
            }
        } else if (!player.get(CollisionComponent.class).active) {
            if (timeSinceDeath >= delay + 1) {
                player.get(CollisionComponent.class).active = true;
                player.get(DisplayComponent.class).visible = true;
            } else {
                if (flicker > 0.15){
                    var display = player.get(DisplayComponent.class);
                    display.visible = !display.visible;
                    flicker = 0;
                }
                flicker += deltaTime;
            }
        }
    }

    @Override
    public int getPriority() {
        return Priority.PROCESSING.value;
    }

    @Override
    public void start() {
        lives = 3;
        timeSinceDeath = delay;
        flicker = 0;
        // indicators will be taken from player's components
        var spi = Engine.get().getEntitySPI("PlayerSPI");
        if (spi == null) return;
        var polygon = spi.create(null).get(PolygonComponent.class);
        for (int i = 0; i < lives; i++) {
            indicators.add(Engine.get().addEntity(makeIndicator(i, polygon)));
        }
    }

    @Override
    public void stop() {
        indicators.clear();
    }

    Entity makeIndicator(int i, PolygonComponent pc) {
        var indicator = new Entity(null);
        var pos = indicator.add(new PositionComponent()).position;
        indicator.add(new DirectionComponent(new Vector2D(0,1)));
        indicator.add(new DisplayComponent());
        indicator.add(pc);

        pos.y = GameData.viewPortWidth * 0.5 - 4;
        pos.x = -GameData.viewPortWidth * 0.5 + 2;
        pos.x += 2 * i;

        return indicator;
    }
}
