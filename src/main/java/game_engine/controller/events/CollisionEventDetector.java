package game_engine.controller.events;

import game_engine.controller.EntityManager;
import game_engine.model.entities.Entity;
import game_engine.model.events.CollisionEventArgs;
import game_engine.model.events.CollisionListener;
import game_engine.model.events.EventTypes;

import java.awt.geom.Rectangle2D;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Class detects entity collision events.
 *
 * @author  Christian-2003, Elekt0
 */
public class CollisionEventDetector implements EventDetector {

    private static CollisionEventDetector singleton;

    private CollisionEventDetector() {}

    /**
     * Detects entity collision events.
     *
     * @param entity  Entity to detect collision events for.
     */
    @Override
    public void detect(Entity entity) {
        if(entity == null || entity.getCollisionListener().isEmpty()) {
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
