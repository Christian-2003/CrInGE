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
        if(entity == null) {
            throw new NullPointerException();
        }
        Set<Entity> entitiesInvolved = this.getCollidingEntities(entity);
        if (!entitiesInvolved.isEmpty()) {
            entity.getCollisionListener().onCollision(new CollisionEventArgs(
                    Calendar.getInstance(),
                    entity,
                    entitiesInvolved
            ));
        }
    }

    /**
     * Returns the entities that are colliding with the passed entity.
     *
     * @param entity  Entity to check for collisions.
     * @return        Entities that are colliding with the passed entity.
     */
    private Set<Entity> getCollidingEntities(Entity entity) {
        Set<Entity> collidingEntities = new HashSet<>();
        Rectangle2D.Double entityBounds = new Rectangle2D.Double(
                entity.getX(),
                entity.getY(),
                entity.getHitBox().width,
                entity.getHitBox().height
        );
        for(Entity target : EntityManager.getInstance().getAllEntities()) {
            if(entity == target) {
                continue;
            }
            Rectangle2D.Double targetBounds = new Rectangle2D.Double(
                    target.getX(),
                    target.getY(),
                    target.getHitBox().width,
                    target.getHitBox().height
            );
            if (entityBounds.intersects(targetBounds)) {
                collidingEntities.add(target);
            }
        }
        return collidingEntities;
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
