import dk.sdu.petni23.bullet.BulletSPI;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;

module Bullet {
    requires Common;
    requires GameEngine;
    provides IEntitySPI with BulletSPI;
}