import dk.sdu.petni23.gameengine.entity.IEntitySPI;
import dk.sdu.petni23.ufo.UFOSPI;

module UFO {
    requires javafx.graphics;
    requires Common;
    requires GameEngine;
    provides IEntitySPI with UFOSPI;
}