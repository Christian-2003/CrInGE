package game_engine.model;

import java.awt.*;


/**
 * Class models a checkpoint which can be used as a MapObject.
 *
 * @author  Christian-2003
 */
public class Checkpoint extends MapObject {

    /**
     * Constructor instantiates a new {@link Checkpoint} with the passed arguments.
     *
     * @param visible                   Whether the Checkpoint is visible.
     * @param tangible                  Whether the Checkpoint is tangible.
     * @param hitBox                    Dimensions of the hit box for the Checkpoint.
     * @param size                      Size of the Checkpoint.
     * @param x                         X-coordinate of the Checkpoint within the chunk.
     * @param y                         Y-coordinate of the Checkpoint within the chunk.
     * @param moving                    Whether the Checkpoint is moving.
     * @param movable                   Whether the Checkpoint is movable.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public Checkpoint(boolean visible, boolean tangible, Dimension hitBox, Dimension size, int x, int y, boolean moving, boolean movable) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size, x, y, moving, movable);
    }

    /**
     * Constructor instantiates a new {@link Checkpoint} with the passed arguments.
     *
     * @param visible                   Whether the Checkpoint is visible.
     * @param tangible                  Whether the Checkpoint is tangible.
     * @param hitBox                    Dimensions of the hit box for the Checkpoint.
     * @param size                      Size of the Checkpoint.
     * @param x                         X-coordinate of the Checkpoint within the chunk.
     * @param y                         Y-coordinate of the Checkpoint within the chunk.
     * @param texture                   Index (within {@link GameMap#textures}) of the MapObject's texture.
     * @param moving                    Whether the Checkpoint is moving.
     * @param movable                   Whether the Checkpoint is movable.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public Checkpoint(boolean visible, boolean tangible, Dimension hitBox, Dimension size, int x, int y, int texture, boolean moving, boolean movable) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size, x, y, texture, moving, movable);
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
