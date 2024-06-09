package game_engine.model.map.objects;

import game_engine.model.GameObject;
import game_engine.model.map.GameChunk;
import game_engine.model.map.GameMap;
import java.awt.Dimension;


/**
 * Class models a MapObject. A {@link GameMap} is made out of instances of this
 * class.
 *
 * @author  Christian-2003
 */
public class MapObject extends GameObject {

    /**
     * Attributes store the coordinates for the map MapObject within a
     * {@link GameChunk}.
     */
    protected int x, y;

    /**
     * Attribute stores whether the MapObject is moving.
     */
    protected boolean moving;

    /**
     * Attribute stores whether the MapObject is movable.
     */
    protected boolean movable;


    /**
     * Constructor instantiates a new {@link MapObject} with the passed
     * arguments.
     *
     * @param visible                   Whether the MapObject is visible.
     * @param tangible                  Whether the MapObject is tangible.
     * @param x                         X-coordinate of the MapObject within
     *                                  the chunk.
     * @param y                         Y-coordinate of the MapObject within
     *                                  the chunk.
     * @throws NullPointerException     One of the passed arguments is
     *                                  {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public MapObject(final boolean visible, final boolean tangible, final int x, final int y) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, new Dimension(1, 1), new Dimension(1, 1));
        setX(x);
        setY(y);
        setMoving(false);
        setMovable(false);
    }

    /**
     * Constructor instantiates a new {@link MapObject} with the passed
     * arguments.
     *
     * @param visible                   Whether the MapObject is visible.
     * @param tangible                  Whether the MapObject is tangible.
     * @param x                         X-coordinate of the MapObject within
     *                                  the chunk.
     * @param y                         Y-coordinate of the MapObject within
     *                                  the chunk.
     * @param texture                   Index (within {@link GameMap#textures})
     *                                  of the MapObject's texture.
     * @throws NullPointerException     One of the passed arguments is
     *                                  {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public MapObject(final boolean visible, final boolean tangible, final int x, final int y, final int texture) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, new Dimension(1, 1), new Dimension(1, 1), texture);
        setX(x);
        setY(y);
        setMoving(false);
        setMovable(false);
    }

    /**
     * Constructor instantiates a new {@link MapObject} and copies the
     * attributes of the passed MapObject to the generated instance.
     *
     * @param mapObject             MapObject whose attributes shall be copied
     *                              to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public MapObject(final MapObject mapObject) throws NullPointerException {
        super(mapObject);
        moving = mapObject.isMoving();
        movable = mapObject.isMovable();
    }


    /**
     * Method returns the x-coordinate of the map object.
     *
     * @return  X-coordinate of the map object.
     */
    public int getX() {
        return x;
    }

    /**
     * Method changes the x-coordinate of the map object.
     *
     * @param x                         New x-coordinate.
     * @throws IllegalArgumentException The x-coordinate is out of bounds.
     */
    public void setX(final int x) throws IllegalArgumentException {
        if (x < 0 || x >= GameChunk.WIDTH) {
            throw new IllegalArgumentException("Invalid coordinate for MapObject: (" + x + ", " + y + ")");
        }
        this.x = x;
    }

    /**
     * Method returns the y-coordinate of the map object.
     *
     * @return  Y-coordinate of the map object.
     */
    public int getY() {
        return y;
    }

    /**
     * Method changes the y-coordinate of the map object.
     *
     * @param y                         New y-coordinate.
     * @throws IllegalArgumentException The y-coordinate is out of bounds.
     */
    public void setY(final int y) throws IllegalArgumentException {
        if (y < 0 || y >= GameChunk.HEIGHT) {
            throw new IllegalArgumentException("Invalid coordinate for MapObject: (" + x + ", " + y + ")");
        }
        this.y = y;
    }

    /**
     * Method returns whether the map object is moving.
     *
     * @return  Whether the map object is moving.
     */
    public boolean isMoving() {
        return moving;
    }

    /**
     * Method changes whether the map object is moving.
     *
     * @param moving    Whether the map object is moving.
     */
    public void setMoving(final boolean moving) {
        this.moving = moving;
    }

    /**
     * Method returns whether the map object is movable.
     *
     * @return  Whether the map object is movable.
     */
    public boolean isMovable() {
        return movable;
    }

    /**
     * Method changes whether the map object is movable.
     *
     * @param movable   Whether the map object is movable.
     */
    public void setMovable(final boolean movable) {
        this.movable = movable;
    }


    /**
     * Method tests whether the attributes of the passed {@link MapObject}
     * match the attributes of this instance. If so, {@code true} is returned.
     * Otherwise, {@code false} will be returned.
     *
     * @param obj   Object to be tested.
     * @return      Whether the attributes of both MapObjects match.
     */
    public boolean equals(final Object obj) {
        if (obj instanceof MapObject mapObject) {
            boolean superIdentical = super.equals(mapObject);
            boolean isMoving = mapObject.isMoving() == moving;
            boolean isMovable = mapObject.isMovable() == movable;
            return superIdentical && isMoving && isMovable;
        }
        return false;
    }

}
