package dk.sdu.petni23.main;

import dk.sdu.petni23.gameengine.entity.IEntitySPI;
import dk.sdu.petni23.gameengine.node.Node;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;


@Configuration
public class ServiceConfiguration {

    public ServiceConfiguration() {

    }
    @Bean
    public List<ISystem> systems() {
        return getServices(ISystem.class);
    }
    @Bean
    public List<IEntitySPI> entitySPIs() {
        return getServices(IEntitySPI.class);
    }
    @Bean
    public List<Node> nodes() {
        return getServices(Node.class);
    }
    @Bean
    public List<IPlugin> plugins() {
        return getServices(IPlugin.class);
    }

    private static <T> ArrayList<T> getServices(Class<T> c) {
        return new ArrayList<>(ServiceLoader.load(c).stream().map(ServiceLoader.Provider::get).toList()) ;
    }
}
