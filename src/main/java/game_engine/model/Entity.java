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
     * Attribute indicates whether the position of the entity has been changed since it was rendered the last
     * time.
     */
    private boolean positionChangedSinceLastRender;

    /**
     * Attributes store the previous x- and y-coordinates of the map object.
     */
    private double previousX, previousY;


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
        positionChangedSinceLastRender = false;
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
        positionChangedSinceLastRender = false;
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
     * Method returns the y-coordinate of the entity.
     *
     * @return  Y-coordinate of the entity.
     */
    public double getY() {
        return y;
    }

    /**
     * Method changes the position of the entity.
     *
     * @param x New x-coordinate for the entity.
     * @param y New y-coordinate for the entity.
     */
    public void setPosition(double x, double y) {
        previousX = this.x;
        previousY = this.y;
        this.x = x;
        this.y = y;
    }

    /**
     * Method returns the x-coordinate of the at which it is currently being displayed.
     * This method is needed by the {@link game_engine.controller.RendererManager}!
     *
     * @return  Previous x-coordinate.
     */
    public double getPreviousX() {
        return previousX;
    }

    /**
     * Method returns the y-coordinate of the at which it is currently being displayed.
     * This method is needed by the {@link game_engine.controller.RendererManager}!
     *
     * @return  Previous y-coordinate.
     */
    public double getPreviousY() {
        return previousY;
    }

    /**
     * Method returns whether the position of the entity has been changed since it was rendered
     * the last time.
     * This method is needed by the {@link game_engine.controller.RendererManager}!
     *
     * @return  Whether the position has been changed.
     */
    public boolean isPositionChangedSinceLastRender() {
        return positionChangedSinceLastRender;
    }

    /**
     * Method changes whether the position of the entity has been changed since it was rendered
     * the last time.
     * This method is needed by the {@link game_engine.controller.RendererManager}!
     *
     * @param positionChangedSinceLastRender    Whether the position has changed since last rendering.
     */
    public void setPositionChangedSinceLastRender(boolean positionChangedSinceLastRender) {
        this.positionChangedSinceLastRender = positionChangedSinceLastRender;
    }

}
