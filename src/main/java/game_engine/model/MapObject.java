package game_engine.model;

import java.awt.*;


/**
 * TODO: Add description.
 *
 * @author  TODO: Add author.
 */
public class MapObject extends GameObject {

    /**
     * Attribute stores whether the MapObject is moving.
     */
    protected boolean moving;

    /**
     * Attribute stores whether the MapObject is movable.
     */
    protected boolean movable;


    /**
     * Constructor instantiates a new {@link MapObject} with the passed arguments.
     *
     * @param visible                   Whether the MapObject is visible.
     * @param tangible                  Whether the MapObject is tangible.
     * @param size                      Size of the MapObject.
     * @param x                         X-coordinate of the MapObject within the chunk.
     * @param y                         Y-coordinate of the MapObjeMapct within the chunk.
     * @param hitBox                    Dimensions of the hit box for the MapObject.
     * @param moving                    Whether the MapObject is moving.
     * @param movable                   Whether the MapObject is movable.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public MapObject(boolean visible, boolean tangible, Dimension hitBox, Dimension size, int x, int y, boolean moving, boolean movable) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size, x, y);
        this.moving = moving;
        this.movable = movable;
    }

    /**
     * Constructor instantiates a new {@link MapObject} with the passed arguments.
     *
     * @param visible                   Whether the MapObject is visible.
     * @param tangible                  Whether the MapObject is tangible.
     * @param size                      Size of the MapObject.
     * @param x                         X-coordinate of the MapObject within the chunk.
     * @param y                         Y-coordinate of the MapObject within the chunk.
     * @param texture                   Index (within {@link GameMap#textures}) of the MapObject's texture.
     * @param hitBox                    Dimensions of the hit box for the MapObject.
     * @param moving                    Whether the MapObject is moving.
     * @param movable                   Whether the MapObject is movable.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public MapObject(boolean visible, boolean tangible, Dimension hitBox, Dimension size, int x, int y, int texture, boolean moving, boolean movable) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size, x, y, texture);
        this.moving = moving;
        this.movable = movable;
    }

    /**
     * Constructor instantiates a new {@link MapObject} and copies the attributes of the passed MapObject to the
     * generated instance.
     *
     * @param mapObject             MapObject whose attributes shall be copied to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public MapObject(MapObject mapObject) throws NullPointerException {
        super(mapObject);
        moving = mapObject.isMoving();
        movable = mapObject.isMovable();
    }


    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }


    /**
     * Method tests whether the attributes of the passed {@link MapObject} match the attributes of
     * this instance. If so, {@code true} is returned. Otherwise, {@code false} will be returned.
     *
     * @param obj   Object to be tested.
     * @return      Whether the attributes of both MapObjects match.
     */
    public boolean equals(Object obj) {
        if (obj instanceof MapObject mapObject) {
            return super.equals(mapObject) && mapObject.isMoving() == moving && mapObject.isMovable() == movable;
        }
        return false;
    }

}
