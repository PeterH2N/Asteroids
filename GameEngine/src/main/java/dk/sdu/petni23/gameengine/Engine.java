package dk.sdu.petni23.gameengine;

import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;
import dk.sdu.petni23.gameengine.node.INodeSPI;
import dk.sdu.petni23.gameengine.node.Node;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Engine
{
    private final static List<Entity> entities = new ArrayList<>();
    private final static List<ISystem> systems = getServices(ISystem.class);
    private final static List<IPlugin> plugins = getServices(IPlugin.class);
    private final static List<Node> nodes = new ArrayList<>();
    private final static Collection<? extends INodeSPI> nodeSPIs = getServices(INodeSPI.class);
    private final static List<IEntitySPI> entitySPIs = getServices(IEntitySPI.class);
    public static void addEntity(Entity entity) {
        if (entities.contains(entity)) return;
        entities.add(entity);
        for (var spi : nodeSPIs) {
            if (spi.requirementsMet(entity.getComponentClasses())) {
                try {
                    nodes.add(spi.nodeClass.getConstructor(Entity.class).newInstance(entity));
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void removeEntity(Entity entity) {
        entities.remove(entity);
        nodes.removeIf(node -> node.parentEntity == entity);
    }

    public static void start() {
        systems.sort(Comparator.comparingInt(ISystem::getPriority));
        for (var plugin : plugins) {
            plugin.start();
        }
    }

    public static void update(double deltaTime) {
        for (var system : systems) {
            system.update(deltaTime);
        }
    }

    @SafeVarargs
    public static <T extends Node> List<T> getNodes(Class<T>... nodeTypes) {
        List<T> r = new ArrayList<>();
        for (Node node : nodes) {
            for (Class<T> nodeType : nodeTypes) {
                if (nodeType.isInstance(node))
                    r.add((T)node);
            }
        }
        return r;
    }

    private static <T> List<T> getServices(Class<T> c) {
        return new ArrayList<>(ServiceLoader.load(c).stream().map(ServiceLoader.Provider::get).toList());
    }

    /**
     * @param name String representing the simple name of the entity SPI class
     * @return Entity SPI with the given name, or null if not found
     * **/
    public static IEntitySPI getEntitySPI(String name) {
        for (var spi : entitySPIs) {
            if (spi.getClass().getSimpleName().equals(name)) return spi;
        }
        return null;
    }
}
