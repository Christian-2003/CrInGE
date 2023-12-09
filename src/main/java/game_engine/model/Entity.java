package game_engine.model;

import java.awt.*;


/**
 * Class models an Entity. Entities can interact with the GameMap.
 *
 * @author  Christian-2003
 */
public class Entity extends GameObject {

    /**
     * Constructor instantiates a new {@link Entity} with the passed arguments.
     *
     * @param visible                   Whether the Entity is visible.
     * @param tangible                  Whether the Entity is tangible.
     * @param hitBox                    Dimensions of the hit box for the Entity.
     * @param size                      Size of the Entity.
     * @param x                         X-coordinate of the Entity within the chunk.
     * @param y                         Y-coordinate of the Entity within the chunk.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public Entity(boolean visible, boolean tangible, Dimension hitBox, Dimension size, int x, int y) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size, x, y);
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
