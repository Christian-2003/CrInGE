package game_engine.model.events;

import game_engine.model.entities.Entity;

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
    private final Entity entity;


    /**
     * Constructor instantiates a new event args instance.
     */
    public GameEventArgs(Calendar time, Entity entity) throws NullPointerException {
        if (time == null | entity == null) {
            throw new NullPointerException();
        }
        this.time = time;
        this.entity = entity;
    }


    /**
     * Method returns the time at which the event was triggered.
     *
     * @return  Time at which the event was triggered.
     */
    public Calendar getTime() {
        return time;
    }

    /**
     * Method returns the entity that triggered the event.
     * May need to be moved to a GameEntityEventArgs class later.
     *
     * @return  Entity that triggered the event.
     */
    public Entity getEntity() {
        return entity;
    }
}
