package game_engine.model;

import java.awt.*;


/**
 * Class models a MapObject. A {@link GameMap} is made out of instances of this class.
 *
 * @author  Christian-2003
 */
public class MapObject extends GameObject {

    /**
     * Attributes store the coordinates for the map MapObject within a {@link GameChunk}.
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
     * Constructor instantiates a new {@link MapObject} with the passed arguments.
     *
     * @param visible                   Whether the MapObject is visible.
     * @param tangible                  Whether the MapObject is tangible.
     * @param x                         X-coordinate of the MapObject within the chunk.
     * @param y                         Y-coordinate of the MapObject within the chunk.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public MapObject(boolean visible, boolean tangible, int x, int y) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, new Dimension(1, 1), new Dimension(1, 1));
        setX(x);
        setY(y);
        setMoving(false);
        setMovable(false);
    }

    /**
     * Constructor instantiates a new {@link MapObject} with the passed arguments.
     *
     * @param visible                   Whether the MapObject is visible.
     * @param tangible                  Whether the MapObject is tangible.
     * @param x                         X-coordinate of the MapObject within the chunk.
     * @param y                         Y-coordinate of the MapObject within the chunk.
     * @param texture                   Index (within {@link GameMap#textures}) of the MapObject's texture.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public MapObject(boolean visible, boolean tangible, int x, int y, int texture) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, new Dimension(1, 1), new Dimension(1, 1), texture);
        setX(x);
        setY(y);
        setMoving(false);
        setMovable(false);
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


    public int getX() {
        return x;
    }

    public void setX(int x) throws IllegalArgumentException {
        if (x < 0 || x >= GameChunk.WIDTH) {
            throw new IllegalArgumentException("Invalid coordinate for MapObject: (" + x + ", " + y + ")");
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) throws IllegalArgumentException {
        if (y < 0 || y >= GameChunk.HEIGHT) {
            throw new IllegalArgumentException("Invalid coordinate for MapObject: (" + x + ", " + y + ")");
        }
        this.y = y;
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
