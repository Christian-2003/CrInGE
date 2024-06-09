package game_engine.model.events;

import game_engine.model.entities.Entity;
import java.util.Calendar;


/**
 * Class models the event arguments for move events that can occur within the
 * game.
 *
 * @author  Christian-2003, Elekt0
 */
public class MoveEventArgs extends GameEventArgs {

    /**
     * Attribute stores the previous x-coordinate of the entity.
     */
    private final double previousX;

    /**
     * Attribute stores the previous y-coordinate of the entity.
     */
    private final double previousY;

    /**
     * Attribute stores the current x-coordinate of the entity.
     */
    private final double x;

    /**
     * Attribute stores the current y-coordinate of the entity.
     */
    private final double y;


    /**
     * Constructor instantiates a new event args instance.
     *
     * @param time      Time at which the event is triggered.
     * @param entity    Entity for which the event is triggered.
     * @param previousX Previous x-coordinate of the entity.
     * @param previousY Previous y-coordinate of the entity.
     * @param x         New x-coordinate of the entity.
     * @param y         New y-coordinate of the entity.
     */
    public MoveEventArgs(final Calendar time, final Entity entity, final double previousX, final double previousY, final double x, final double y) {
        super(time, entity);
        this.previousX = previousX;
        this.previousY = previousY;
        this.x = x;
        this.y = y;
    }


    /**
     * Method returns the x-coordinate from which the entity moved.
     *
     * @return  X-coordinate from which the entity moved.
     */
    public double getPreviousX() {
        return previousX;
    }

    /**
     * Method returns the y-coordinate from which the entity moved.
     *
     * @return  Y-coordinate from which the entity moved.
     */
    public double getPreviousY() {
        return previousY;
    }

    /**
     * Method returns the x-coordinate to which the entity moved.
     *
     * @return  X-coordinate to which the entity moved.
     */
    public double getX() {
        return x;
    }

    /**
     * Method returns the y-coordinate to which the entity moved.
     *
     * @return  Y-coordinate to which the entity moved.
     */
    public double getY() {
        return y;
    }

}
