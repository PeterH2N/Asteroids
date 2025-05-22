package dk.sdu.petni23.asteroid;

import dk.sdu.petni23.common.components.SizeComponent;
import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;
import dk.sdu.petni23.gameengine.services.IPlugin;

public class AsteroidPlugin implements IPlugin {
    @Override
    public void start() {
        IEntitySPI asteroidSPI = Engine.getEntitySPI("AsteroidSPI");
        if (asteroidSPI == null) return;
        var size = new Entity();
        size.add(new SizeComponent(3));
        Engine.addEntity(asteroidSPI.create(size));
    }

    @Override
    public void stop() {

    }
}
