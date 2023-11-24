package game_engine.model;

import java.awt.*;


/**
 * TODO: Add description.
 *
 * @author  TODO: Add author.
 */
public class Entity extends GameObject {

    /**
     * Constructor instantiates a new {@link Entity} with the passed arguments.
     *
     * @param visible               Whether the Entity is visible.
     * @param tangible              Whether the Entity is tangible.
     * @param hitBox                Dimensions of the hit box for the Entity.
     * @param size                  Size of the Entity.
     * @throws NullPointerException One of the passed arguments is {@code null}.
     */
    public Entity(boolean visible, boolean tangible, Dimension hitBox, Dimension size) throws NullPointerException {
        super(visible, tangible, hitBox, size);
    }

    /**
     * Constructor instantiates a new {@link Entity} and copies the attributes of the passed Entity to the
     * generated instance.
     *
     * @param gameObject            Entity whose attributes shall be copied to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public Entity(Entity gameObject) throws NullPointerException {
        super(gameObject);
    }

}
