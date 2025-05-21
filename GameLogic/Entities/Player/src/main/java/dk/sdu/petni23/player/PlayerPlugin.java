package dk.sdu.petni23.player;

import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;

public class PlayerPlugin implements IPlugin
{

    @Override
    public void start()
    {
        var spi = Engine.getEntitySPI("PlayerSPI");
        if (spi != null) {
            Engine.addEntity(spi.create(null));
        }
    }

    @Override
    public void stop() {

    }
}
