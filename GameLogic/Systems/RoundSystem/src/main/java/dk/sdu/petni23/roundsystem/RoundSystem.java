package dk.sdu.petni23.roundsystem;

import dk.sdu.petni23.common.components.PositionComponent;
import dk.sdu.petni23.common.components.SizeComponent;
import dk.sdu.petni23.common.data.GameData;
import dk.sdu.petni23.common.util.Vector2D;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoundSystem implements ISystem, IPlugin {

    final static List<Entity> roundEntities = new ArrayList<>();
    private static int roundNumber;
    private static final double delayBetweenRounds = 5; // seconds
    private static double currentDelay;
    private final static Random random = new Random();
    @Override
    public void update(double deltaTime) {
        var list = roundEntities;
        // new round should start
        if (currentDelay >= delayBetweenRounds) {
            startRound();
        }
        // if all entities are defeated, we count down till next round
        else if (roundEntities.isEmpty()) {
            currentDelay += deltaTime;
        }
    }

    void startRound() {
        // spawn 5 asteroids, add 2 for every round
        currentDelay = 0;
        int numAsteroids = 5 + roundNumber * 2;
        int numUFOs = roundNumber / 2;
        for (int i = 0; i < numAsteroids; i++) {
            Engine.get().addEntity(getAsteroid());
        }
        for (int i = 0; i < numUFOs; i++) {
            Engine.get().addEntity(getUFO());
        }
        roundNumber++;
    }

    Entity getAsteroid() {
        IEntitySPI asteroidSPI = Engine.get().getEntitySPI("AsteroidSPI");
        if (asteroidSPI == null) return null;
        double s = random.nextDouble(1,5);
        var size = new Entity(null);
        size.add(new SizeComponent(s));
        var asteroid = asteroidSPI.create(size);
        // set position
        // asteroids will be moved at least 2/3 world size away from center
        var position = asteroid.get(PositionComponent.class);
        Vector2D dir = new Vector2D(random.nextDouble(-1,1), random.nextDouble(-1,1)).getNormalized();
        double dist = random.nextDouble(0.67, 1);
        position.position.add(dir.multiply(dist * GameData.viewPortWidth * 0.5));
        return asteroid;
    }

    Entity getUFO() {
        var ufoSPI = Engine.get().getEntitySPI("UFOSPI");
        if (ufoSPI == null) return null;
        var ufo = ufoSPI.create(null);
        // ufo's will be moved at least 2/3 world size away from center
        var position = ufo.get(PositionComponent.class);
        Vector2D dir = new Vector2D(random.nextDouble(-1,1), random.nextDouble(-1,1)).getNormalized();
        double dist = random.nextDouble(0.67, 1);
        position.position.add(dir.multiply(dist * GameData.viewPortWidth * 0.5));
        return ufo;
    }

    @Override
    public int getPriority() {
        return Priority.PROCESSING.value;
    }

    @Override
    public void start() {
        roundNumber = 0;
        currentDelay = delayBetweenRounds;
    }

    @Override
    public void stop() {
        roundEntities.clear();
    }
}
