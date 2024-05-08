package game_engine.model.events;

import game_engine.model.entities.Entity;

import java.util.Calendar;


/**
 * Class models the event arguments for move events that can occur within the game.
 *
 * @author  Christian-2003, Elekt0
 */
public class MoveEventArgs extends GameEventArgs {

    private final double previousX;
    private final double previousY;
    private final double x;
    private final double y;

    /**
     * Constructor instantiates a new event args instance.
     */
    public MoveEventArgs(Calendar time, Entity entity, double previousX, double previousY, double x, double y) {
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
