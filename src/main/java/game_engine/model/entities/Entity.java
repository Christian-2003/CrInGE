package game_engine.model.entities;

import game_engine.model.GameObject;
import game_engine.model.events.CollisionListener;
import game_engine.model.events.EventTypes;
import game_engine.model.events.GameEventListener;
import game_engine.model.events.MoveListener;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Class models an Entity. Entities can interact with the GameMap.
 *
 * @author  Christian-2003, Elekt0
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
     * Attributes store the previous coordinates of the entity. This is required to detect whether the entity was moved.
     */
    private double previousX, previousY;

    /**
     * Attribute stores all events of the entity.
     */
    protected final Map<EventTypes, GameEventListener> events;


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
        this.previousX = x;
        this.previousY = y;
        events = new HashMap<>();
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
        this.previousX = entity.getPreviousX();
        this.previousY = entity.getPreviousY();
        events = new HashMap<>();
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
     * Method returns the previous x-coordinate of the entity.
     *
     * @return  Previous x-coordinate of the entity.
     */
    public double getPreviousX() {
        return previousX;
    }

    /**
     * Method returns the previous y-coordinate of the entity.
     *
     * @return  Previous y-coordinate of the entity.
     */
    public double getPreviousY() {
        return previousY;
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
     * Method returns whether the entity has been moved.
     *
     * @return  Whether the entity has been moved.
     */
    public boolean isMoved() {
        return previousX != x || previousY != y;
    }

    /**
     * Method removes the information from the entity, that it has been moved.
     */
    public void removeMovedFlag() {
        previousX = x;
        previousY = y;
    }

    /**
     * Method returns all events of the entity.
     *
     * @return  All events of the entity.
     */
    public Map<EventTypes, GameEventListener> getAllEvents() {
        return events;
    }

    /**
     * Method sets the collision listener for the entity.
     *
     * @param collisionListener New collision listener for the entity.
     */
    public void setCollisionListener(CollisionListener collisionListener) {
        events.put(EventTypes.COLLISION, collisionListener);
    }

    /**
     * Method returns the collision listener of the entity.
     *
     * @return  Collision listener of the entity.
     */
    public CollisionListener getCollisionListener() {
        return (CollisionListener)events.get(EventTypes.COLLISION);
    }

    /**
     * Method sets the move listener for the entity.
     *
     * @param moveListener New move listener for the entity.
     */
    public void setMoveListener(MoveListener moveListener) {
        events.put(EventTypes.MOVE, moveListener);
    }

    /**
     * Method returns the collision listener of the entity.
     *
     * @return  Collision listener of the entity.
     */
    public MoveListener getMoveListener() {
        return (MoveListener) events.get(EventTypes.MOVE);
    }

}
