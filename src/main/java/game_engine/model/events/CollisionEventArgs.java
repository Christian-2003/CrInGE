package game_engine.model.events;

import java.util.Calendar;


/**
 * Class models the event arguments for collision events that can occur within the game.
 *
 * @author  Christian-2003
 */
public class CollisionEventArgs extends GameEventArgs {

    /**
     * Constructor instantiates a new event args instance.
     */
    public CollisionEventArgs(Calendar time) {
        super(time);
    }

}
