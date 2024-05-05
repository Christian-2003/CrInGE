package game_engine.model.events;


/**
 * Interface must be implemented for collision events.
 *
 * @author  Christian-2003, Elekt0
 */
public interface CollisionListener extends GameEventListener {

    /**
     * Method is called when a collision is registered.
     *
     * @param args  Event arguments.
     */
    void onCollision(CollisionEventArgs args);

}
