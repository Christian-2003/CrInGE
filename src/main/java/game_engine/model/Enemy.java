package game_engine.model;

import java.awt.*;


/**
 * Class models a simple enemy which can be used as Entity.
 *
 * @author  Christian-2003
 */
public class Enemy extends Entity {

    /**
     * Constructor instantiates a new {@link Enemy} with the passed arguments.
     *
     * @param visible                   Whether the Enemy is visible.
     * @param tangible                  Whether the Enemy is tangible.
     * @param hitBox                    Dimensions of the hit box for the Enemy.
     * @param size                      Size of the Enemy.
     * @param x                         X-coordinate of the Enemy.
     * @param y                         Y-coordinate of the Enemy.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public Enemy(boolean visible, boolean tangible, Dimension hitBox, Dimension size, double x, double y) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size, x, y);
    }

    /**
     * Constructor instantiates a new {@link Enemy} and copies the attributes of the passed Enemy to the
     * generated instance.
     *
     * @param gameObject            Enemy whose attributes shall be copied to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public Enemy(Enemy gameObject) throws NullPointerException {
        super(gameObject);
    }

}
