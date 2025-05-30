package dk.sdu.petni23.gameengine.entity;

import dk.sdu.petni23.gameengine.component.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Entity
{
    public final Class<? extends IEntitySPI> type;
    private static long idCount = 0;
    private final Long id = idCount++;
    private final Map<Class<? extends Component>, Component> components = new HashMap<>();

    public Entity(Class<? extends IEntitySPI> type) {
        this.type = type;
    }

    public <T extends Component> T add(T component) {
        Class<? extends Component> c = component.getClass();
        components.put(c, component);
        return component;
    }

    public void remove(Class<? extends Component> c) {
        components.remove(c);
    }

    public <T extends Component> T get(Class<T> c) {
        return (T) components.get(c);
    }

    public Collection<Class<? extends Component>> getComponentClasses() {
        return components.keySet();
    }

    public Collection<Component> getComponents() {
        return components.values();
    }

    public Long getId()
    {
        return id;
    }
}
