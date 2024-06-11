package game_engine.model.events;


/**
 * Interface must be implemented for move events.
 *
 * @author  Christian-2003, Elekt0
 */
public interface MoveListener extends GameEventListener {

    /**
     * Method is called when an entity movement is registered.
     *
     * @param args  Event arguments.
     */
    void onMove(MoveEventArgs args);

}
