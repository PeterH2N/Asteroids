import dk.sdu.petni23.gameengine.entity.IEntitySPI;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.player.PlayerSPI;
import dk.sdu.petni23.player.PlayerPlugin;

module Player {
    requires javafx.graphics;
    requires Common;
    requires GameEngine;
    uses IEntitySPI;
    provides IEntitySPI with PlayerSPI;
    provides IPlugin with PlayerPlugin;
}