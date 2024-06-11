package game_engine.model.events;

import game_engine.model.entities.Entity;
import java.util.Calendar;
import java.util.Set;


/**
 * Class models the event arguments for collision events that can occur within
 * the game.
 *
 * @author  Christian-2003, Elekt0
 */
public class CollisionEventArgs extends GameEventArgs {

    /**
     * Attribute stores all entities that are involved with the collision
     * event.
     */
    private final Set<Entity> entitiesInvolved;


    /**
     * Constructor instantiates a new event args instance.
     *
     * @param time              Time at which the event was triggered.
     * @param entity            Entity for which the event was triggered.
     * @param entitiesInvolved  Set of entities that are involved in the
     *                          collision.
     */
    public CollisionEventArgs(final Calendar time, final Entity entity, final Set<Entity> entitiesInvolved) {
        super(time, entity);
        this.entitiesInvolved = entitiesInvolved;
    }


    /**
     * Method returns the entities involved in the collision.
     *
     * @return  Entities involved in the collision.
     */
    public Set<Entity> getEntitiesInvolved() {
        return entitiesInvolved;
    }

}
