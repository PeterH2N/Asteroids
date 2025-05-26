import dk.sdu.petni23.asteroid.AsteroidSPI;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;

module Asteroid {
    requires Common;
    requires GameEngine;
    provides IEntitySPI with AsteroidSPI;
}