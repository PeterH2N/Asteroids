package dk.sdu.petni23.gameengine;

import dk.sdu.petni23.gameengine.entity.Entity;
import dk.sdu.petni23.gameengine.entity.IEntitySPI;
import dk.sdu.petni23.gameengine.node.Node;
import dk.sdu.petni23.gameengine.services.IPlugin;
import dk.sdu.petni23.gameengine.services.ISystem;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class Engine
{

    private static Engine engine;
    public static Engine get() {
        return engine;
    }
    public static void init(ApplicationContext ctx) {
        engine = new Engine(ctx);
    }
    private Engine(ApplicationContext ctx) {
        this.ctx = ctx;
        plugins = getBean("plugins");
        nodeClasses = getBean("nodes");
        entitySPIs = getBean("entitySPIs");
        systems = getBean("systems");
    }
    private final ApplicationContext ctx;
    private final Map<Long, Entity> entities = new HashMap<>();
    private final List<ISystem> systems;
    private final List<IPlugin> plugins;
    private final List<Node> nodes = new ArrayList<>();
    private final Collection<? extends Node> nodeClasses;
    private final List<IEntitySPI> entitySPIs;
    public Entity addEntity(Entity entity) {
        if (entity == null) return null;
        if (entities.get(entity.getId()) != null) return entity;
        entities.put(entity.getId(), entity);
        for (var spi : nodeClasses) {
            if (spi.requirementsMet(entity.getComponentClasses())) {
                try {
                    var node = spi.getClass().getConstructor().newInstance();
                    node.parentEntity = entity;
                    Node.initNode(entity, node);
                    nodes.add(node);
                    node.onAdd();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return entity;
    }

    public void removeEntity(Entity entity) {
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

    public Entity getEntity(Entity entity) {
        return entities.get(entity.getId());
    }

    public void start() {
        systems.sort(Comparator.comparingInt(ISystem::getPriority));
        for (var plugin : plugins) {
            plugin.start();
        }
    }

    public void stop() {
        entities.forEach((aLong, entity) -> removeEntity(entity));
        for (var plugin : plugins) {
            plugin.stop();
        }
    }

    public void update(double deltaTime) {
        for (var system : systems) {
            system.update(deltaTime);
        }
    }

    @SafeVarargs
    public final <T extends Node> List<T> getNodes(Class<T>... nodeTypes) {
        List<T> r = new ArrayList<>();
        for (Node node : nodes) {
            for (Class<T> nodeType : nodeTypes) {
                if (nodeType.isInstance(node))
                    r.add((T)node);
            }
        }
        return r;
    }

    /**
     * @param name String representing the simple name of the entity SPI class
     * @return Entity SPI with the given name, or null if not found
     * **/
    public IEntitySPI getEntitySPI(String name) {
        for (var spi : entitySPIs) {
            if (spi.getClass().getSimpleName().equals(name)) return spi;
        }
        return null;
    }

    private <T> List<T> getBean(String beanName) {
        return new ArrayList<T>(ctx.getBean(beanName, List.class));
    }
}
