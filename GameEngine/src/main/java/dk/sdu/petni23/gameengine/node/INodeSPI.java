package dk.sdu.petni23.gameengine.node;

import dk.sdu.petni23.gameengine.component.Component;

import java.util.Collection;
import java.util.HashSet;

public abstract class INodeSPI
{
    public final Class<? extends Node> nodeClass;

    protected INodeSPI(Class<? extends Node> nodeClass) {
        this.nodeClass = nodeClass;
    }

    public boolean requirementsMet(Collection<Class<? extends Component>> componentClasses) {
        return new HashSet<>(componentClasses).containsAll(Node.getComponentClasses(nodeClass));
    }
}
