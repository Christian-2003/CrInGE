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
     * Attributes store the coordinate of the entity.
     */
    private double x, y;


    /**
     * Constructor instantiates a new {@link Entity} with the passed arguments. The UUID for the entity will be randomly
     * generated.
     *
     * @param visible                   Whether the Entity is visible.
     * @param tangible                  Whether the Entity is tangible.
     * @param hitBox                    Dimensions of the hit box for the Entity.
     * @param size                      Size of the Entity.
     * @param x                         X-coordinate of the Entity.
     * @param y                         Y-coordinate of the Entity.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public Entity(boolean visible, boolean tangible, Dimension hitBox, Dimension size, double x, double y) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size);
        uuid = UUID.randomUUID();
        this.x = x;
        this.y = y;
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
        this.x = entity.getX();
        this.y = entity.getY();
    }


    /**
     * Method returns the UUID of the entity.
     *
     * @return  UUID of the entity.
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Method returns the x-coordinate of the entity.
     *
     * @return  X-coordinate of the entity.
     */
    public double getX() {
        return x;
    }

    /**
     * Method changes the x-coordinate of the entity.
     *
     * @param x New x-coordinate for the entity.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Method returns the y-coordinate of the entity.
     *
     * @return  Y-coordinate of the entity.
     */
    public double getY() {
        return y;
    }

    /**
     * Method changes the y-coordinate of the entity.
     *
     * @param y New y-coordinate for the entity.
     */
    public void setY(double y) {
        this.y = y;
    }

}
