package game_engine.model.events;

import java.util.Calendar;


/**
 * Class models the event arguments for move events that can occur within the game.
 *
 * @author  Christian-2003, Elekt0
 */
public class MoveEventArgs extends GameEventArgs {

    /**
     * Constructor instantiates a new event args instance.
     */
    public MoveEventArgs(Calendar time) {
        super(time);
    }

}
