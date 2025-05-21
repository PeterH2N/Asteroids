package dk.sdu.petni23.gameengine.component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Used to mark that a component is not required when writing nodes
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionalComponent
{
}
