import dk.sdu.petni23.asteroid.AsteroidPlugin;
import dk.sdu.petni23.asteroid.AsteroidSPI;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;
import dk.sdu.petni23.gameengine.services.IPlugin;

module Asteroid {
    requires Common;
    requires GameEngine;
    provides IEntitySPI with AsteroidSPI;
    provides IPlugin with AsteroidPlugin;
}