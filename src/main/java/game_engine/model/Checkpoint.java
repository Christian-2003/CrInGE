package game_engine.model;

import java.awt.*;


/**
 * TODO: Add description.
 *
 * @author  TODO: Add author.
 */
public class Checkpoint extends MapObject {

    /**
     * Constructor instantiates a new {@link Checkpoint} with the passed arguments.
     *
     * @param visible               Whether the Checkpoint is visible.
     * @param tangible              Whether the Checkpoint is tangible.
     * @param hitBox                Dimensions of the hit box for the Checkpoint.
     * @param size                  Size of the Checkpoint.
     * @param graphics              Graphics for the Checkpoint.
     * @param moving                Whether the Checkpoint is moving.
     * @param movable               Whether the Checkpoint is movable.
     * @throws NullPointerException One of the passed arguments is {@code null}.
     */
    public Checkpoint(boolean visible, boolean tangible, Dimension hitBox, Dimension size, Graphics graphics, boolean moving, boolean movable) throws NullPointerException {
        super(visible, tangible, hitBox, size, graphics, moving, movable);
    }

    /**
     * Constructor instantiates a new {@link Checkpoint} and copies the attributes of the passed Checkpoint to the
     * generated instance.
     *
     * @param checkpoint            Checkpoint whose attributes shall be copied to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public Checkpoint(Checkpoint checkpoint) throws NullPointerException {
        super(checkpoint);
    }

}
