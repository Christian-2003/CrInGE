package game_engine.model;

import java.awt.*;
import java.util.UUID;


/**
 * Class models an Entity. Entities can interact with the GameMap.
 *
 * @author  Christian-2003
 */
public class Entity extends GameObject {

    /**
     * Attribute stores a UUID with which the entity can be identified.
     */
    private final UUID uuid;


    /**
     * Constructor instantiates a new {@link Entity} with the passed arguments. The UUID for the entity will be randomly
     * generated.
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
        uuid = UUID.randomUUID();
    }

    /**
     * Constructor instantiates a new {@link Entity} and copies the attributes of the passed Entity to the
     * generated instance.
     *
     * @param entity                Entity whose attributes shall be copied to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public Entity(Entity entity) throws NullPointerException {
        super(entity);
        this.uuid = entity.uuid;
    }


    /**
     * Method returns the UUID of the entity.
     *
     * @return  UUID of the entity.
     */
    public UUID getUuid() {
        return uuid;
    }

}
