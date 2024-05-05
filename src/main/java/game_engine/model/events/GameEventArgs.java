package game_engine.model.events;

import java.util.Calendar;


/**
 * Class models the event arguments for all events that can occur within the game. Specialized events should
 * use event args extending this class.
 *
 * @author  Christian-2003, Elekt0
 */
public class GameEventArgs {

    /**
     * Attribute stores the time at which the event was triggered.
     */
    private final Calendar time;


    /**
     * Constructor instantiates a new event args instance.
     */
    public GameEventArgs(Calendar time) throws NullPointerException {
        if (time == null) {
            throw new NullPointerException();
        }
        this.time = time;
    }


    /**
     * Method returns the time at which the event was triggered.
     *
     * @return  Time at which the event was triggered.
     */
    public Calendar getTime() {
        return time;
    }

}
