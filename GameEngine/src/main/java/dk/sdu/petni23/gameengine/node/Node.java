package dk.sdu.petni23.gameengine.node;

import dk.sdu.petni23.gameengine.component.Component;
import dk.sdu.petni23.gameengine.component.OptionalComponent;
import dk.sdu.petni23.gameengine.entity.Entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public abstract class Node {
    public Entity parentEntity;
    public Node() {
    }

    public static List<Class<? extends Component>> getComponentClasses(Class<? extends Node> nodeClass) {
        List<Class<? extends Component>> componentClasses = new ArrayList<>();
        for (Field field : nodeClass.getDeclaredFields()) {
            Class<?> type = field.getType();
            if (Component.class.isAssignableFrom(type)&& !field.isAnnotationPresent(OptionalComponent.class)) {
                componentClasses.add((Class<? extends Component>) type);
            }
        }
        return componentClasses;
    }

    public static <T extends Node> void initNode(Entity entity, T node) {
        var components = entity.getComponents();
        for (Component component : components) {
            for (Field field : node.getClass().getFields()) {
                if (component.getClass().isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    try {
                        field.set(node, component);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public List<Component> getComponents() {
        List<Component> components = new ArrayList<>();
        for (Field field : this.getClass().getFields()) {
            if (field.getType().isAssignableFrom(Component.class)) {
                field.setAccessible(true);
                try {
                    components.add((Component)field.get(this));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return components;
    }

    public boolean requirementsMet(Collection<Class<? extends Component>> componentClasses) {
        return new HashSet<>(componentClasses).containsAll(Node.getComponentClasses(this.getClass()));
    }

    public abstract void onRemove();
    public abstract  void onAdd();
}
