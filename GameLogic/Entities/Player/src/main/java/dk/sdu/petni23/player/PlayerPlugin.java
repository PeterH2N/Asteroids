package dk.sdu.petni23.player;

import dk.sdu.petni23.gameengine.Engine;
import dk.sdu.petni23.gameengine.services.IPlugin;

public class PlayerPlugin implements IPlugin
{

    @Override
    public void start()
    {
        var spi = Engine.get().getEntitySPI("PlayerSPI");
        if (spi != null) {
            Engine.get().addEntity(spi.create(null));
        }
    }

    @Override
    public void stop() {

    }
}
