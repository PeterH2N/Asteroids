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
    private final static Map<Long, Entity> entities = new HashMap<>();
    private final static List<ISystem> systems = getServices(ISystem.class);
    private final static List<IPlugin> plugins = getServices(IPlugin.class);
    private final static List<Node> nodes = new ArrayList<>();
    private final static Collection<? extends INodeSPI> nodeSPIs = getServices(INodeSPI.class);
    private final static List<IEntitySPI> entitySPIs = getServices(IEntitySPI.class);
    public static Entity addEntity(Entity entity) {
        if (entities.get(entity.getId()) != null) return entity;
        entities.put(entity.getId(), entity);
        for (var spi : nodeSPIs) {
            if (spi.requirementsMet(entity.getComponentClasses())) {
                try {
                    var node = spi.nodeClass.getConstructor(Entity.class).newInstance(entity);
                    nodes.add(node);
                    node.onAdd();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return entity;
    }

    public static void removeEntity(Entity entity) {
        if (entity == null) return;
        if (entities.get(entity.getId()) == null) return;
        // store removed nodes to call their onRemove method afterward
        List<Node> removedNodes = new ArrayList<>();
        nodes.removeIf(node -> {
            if (node.parentEntity == entity) {
                removedNodes.add(node);
                return true;
            }
            return false;
        });
        removedNodes.forEach(Node::onRemove);
        entities.remove(entity.getId());

    }

    public static Entity getEntity(Entity entity) {
        return entities.get(entity.getId());
    }

    public static void start() {
        systems.sort(Comparator.comparingInt(ISystem::getPriority));
        for (var plugin : plugins) {
            plugin.start();
        }
    }

    public static void stop() {
        entities.forEach((aLong, entity) -> Engine.removeEntity(entity));
        for (var plugin : plugins) {
            plugin.stop();
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
