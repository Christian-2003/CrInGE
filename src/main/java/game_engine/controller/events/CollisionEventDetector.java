package game_engine.controller.events;

import game_engine.model.entities.Entity;
import game_engine.model.events.CollisionEventArgs;
import java.util.Calendar;
import java.util.Set;


/**
 * Class detects entity collision events.
 *
 * @author  Christian-2003, Elekt0
 */
public class CollisionEventDetector implements EventDetector {

    /**
     * Field stores the singleton-instance for this class.
     */
    private static CollisionEventDetector singleton;


    /**
     * Constructor instantiates a new event detector.
     */
    private CollisionEventDetector() {

    }


    /**
     * Detects entity collision events.
     *
     * @param entity  Entity to detect collision events for.
     */
    @Override
    public void detect(final Entity entity) {
        if (entity == null || entity.getCollisionListener().isEmpty()) {
            throw new NullPointerException();
        }
        Set<Entity> entitiesInvolved = entity.getCollidingEntities();
        if (!entitiesInvolved.isEmpty()) {
            entity.getCollisionListener().get().onCollision(new CollisionEventArgs(
                    Calendar.getInstance(),
                    entity,
                    entitiesInvolved
            ));
        }
    }


    /**
     * Returns the singleton instance.
     *
     * @return  Singleton instance.
     */
    public static CollisionEventDetector getInstance() {
        if (singleton == null) {
            singleton = new CollisionEventDetector();
        }
        return singleton;
    }

}
