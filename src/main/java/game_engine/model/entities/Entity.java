package game_engine.model.entities;

import game_engine.controller.EntityManager;
import game_engine.model.GameObject;
import game_engine.model.events.CollisionListener;
import game_engine.model.events.EventTypes;
import game_engine.model.events.GameEventListener;
import game_engine.model.events.MoveListener;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


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
     * Attribute stores debugName from Editor, usage for error and debug messages only
     */
     private String debugName;
    /**
     * Attributes store the x-coordinate of the entity.
     */
    private double x;

    /**
     * Attributes store the y-coordinate of the entity.
     */
    private double y;

    /**
     * Attributes store the previous x-coordinate of the entity. This is
     * required to detect whether the entity was moved.
     */
    private double previousX;

    /**
     * Attributes store the previous y-coordinate of the entity. This is
     * required to detect whether the entity was moved.
     */
    private double previousY;

    /**
     * Attribute stores all events of the entity.
     */
    protected final Map<EventTypes, GameEventListener> events;


    /**
     * Constructor instantiates a new {@link Entity} with the passed arguments.
     * The UUID for the entity will be randomly generated.
     *
     * @param visible                   Whether the Entity is visible.
     * @param tangible                  Whether the Entity is tangible.
     * @param hitBox                    Dimensions of the hit box for the
     *                                  Entity.
     * @param size                      Size of the Entity.
     * @param x                         X-coordinate of the Entity.
     * @param y                         Y-coordinate of the Entity.
     * @throws NullPointerException     One of the passed arguments is
     * {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public Entity(final boolean visible, final boolean tangible, final Dimension hitBox, final Dimension size, final double x, final double y) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size);
        uuid = UUID.randomUUID();
        this.x = x;
        this.y = y;
        this.previousX = x;
        this.previousY = y;
        events = new HashMap<>();
    }

    /**
     * Constructor instantiates a new {@link Entity} and copies the attributes
     * of the passed Entity to the generated instance.
     *
     * @param entity                Entity whose attributes shall be copied to
     *                              this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public Entity(final Entity entity) throws NullPointerException {
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
    public void setPosition(final double x, final double y) {
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
    public void setCollisionListener(final CollisionListener collisionListener) {
        if (collisionListener == null) {
            throw new NullPointerException();
        }
        events.put(EventTypes.COLLISION, collisionListener);
    }

    /**
     * Method returns the collision listener of the entity.
     *
     * @return  Collision listener of the entity.
     */
    public Optional<CollisionListener> getCollisionListener() {
        if (!events.containsKey(EventTypes.COLLISION)) {
            return Optional.empty();
        }
        return Optional.of((CollisionListener)events.get(EventTypes.COLLISION));
    }

    /**
     * Method sets the move listener for the entity.
     *
     * @param moveListener New move listener for the entity.
     */
    public void setMoveListener(final MoveListener moveListener) {
        if (moveListener == null) {
            throw new NullPointerException();
        }
        events.put(EventTypes.MOVE, moveListener);
    }

    /**
     * Method returns the collision listener of the entity.
     *
     * @return  Collision listener of the entity.
     */
    public Optional<MoveListener> getMoveListener() {
        if (!events.containsKey(EventTypes.MOVE)) {
            return Optional.empty();
        }
        return Optional.of((MoveListener) events.get(EventTypes.MOVE));
    }

    /**
     * Method returns whether the entity is colliding with the passed entity.
     *
     * @param target    Entity to check for collision.
     * @return          Whether the entity is colliding with the passed entity.
     */
    public boolean isCollidingWith(final Entity target) {
        Rectangle2D.Double entityBounds = new Rectangle2D.Double(x, y, getHitBox().width, getHitBox().height);
        Rectangle2D.Double targetBounds = new Rectangle2D.Double(target.getX(), target.getY(), target.getHitBox().width, target.getHitBox().height);
        return entityBounds.intersects(targetBounds);
    }

    /**
     * Method checks for collisions with other entities.
     *
     * @return The colliding entities.
     */
    public Set<Entity> getCollidingEntities() {
        return EntityManager.getInstance().getAllEntities().stream().filter(target -> target != this).filter(this::isCollidingWith).collect(Collectors.toSet());
    }

    /**
     * Set a (new) debug name for current Entity
     * @param debugName | String
     */
    public void setDebugName(String debugName){
        this.debugName = debugName;
    }

    /**
     * Methode returns a unique DebugName of the entity.
     * @return debugName | String
     */
    public String getDebugName(){
        return this.uuid + "_" + this.debugName;
    }

    /**
     * Returns a short summery in GameData Syntax, what's inside this entity
     * @return String | (pseudo) GameDataSyntax
     * @author Farbian001
     */
    @Override
    public String toString(){
        String vis = this.isVisible()? "t" : "f";
        String tang = this.isTangible()? "t" : "f";
        return "{"+
                this.debugName +";"+
                this.getTexture() + ";"+
                vis+ ";" +
                tang+ ";" +
                (int) this.getSize().getWidth() + ";"+
                (int) this.getSize().getHeight() + ";"+
                (int) this.getHitBox().getWidth() + ";"+
                (int) this.getHitBox().getHeight() + ";" +
                (double) this.getX() + ";"+
                (double) this.getY()
                +"};";
    }

    /**
     * Returns additional header-information in GameData Syntax of current Entity
     * @return String | (pseudo) GameDataSyntax with headers
     * @author Farbian001
     */
    public String toDebugString(){
        return "Entity-"+ this.getDebugName() +
                "(Texture-Index: " + this.getTexture() +
                ",visible: "+ this.isVisible() +
                ",tangible: "+this.isTangible() +
                ",size-width: " + this.getSize().width +
                ",size-height: " + this.getSize().height +
                ",Hitbox-Widh: "+this.getHitBox().width +
                ",Hitbox-Height: "+ this.getHitBox().height +
                ",x: " + this.getX() +
                ",y: " + this.getY();
    }
}
