package game_engine.model;

import java.awt.*;


/**
 * TODO: Add description.
 *
 * @author  TODO: Add author.
 */
public class Enemy extends Entity {

    /**
     * Constructor instantiates a new {@link Enemy} with the passed arguments.
     *
     * @param visible               Whether the Enemy is visible.
     * @param tangible              Whether the Enemy is tangible.
     * @param hitBox                Dimensions of the hit box for the Enemy.
     * @param size                  Size of the Enemy.
     * @param graphics              Graphics for the Enemy.
     * @throws NullPointerException One of the passed arguments is {@code null}.
     */
    public Enemy(boolean visible, boolean tangible, Dimension hitBox, Dimension size, Graphics graphics) throws NullPointerException {
        super(visible, tangible, hitBox, size, graphics);
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
