package game_engine.controller.math_engine;

import java.io.Serializable;
import java.util.Objects;


/**
 * Class models a point within a two-dimensional coordinate system. Each point consists
 * of an x- and y-coordinate.
 *
 * @author  Christian-2003
 */
public class EPoint implements Serializable {
    
    /**
     * Attribute stores the x-coordinate of the point.
     */
    protected int x;
    
    /**
     * Attribute stores the y-coordinate of the point.
     */
    protected int y;
    
    
    /**
     * Constructor instantiates the new {@link EPoint} at {@code (0,0)}.
     */
    public EPoint() {
        x = 0;
        y = 0;
    }
    
    /**
     * Constructor instantiates the new {@link EPoint} with the specified coordinates.
     *
     * @param x X-coordinate for the point.
     * @param y Y-coordinate for the point.
     */
    public EPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Constructor instantiates a new {@link EPoint} and copies the coordinates of the passed point to
     * this instance.
     *
     * @param point                 Point whose coordinates shall be copied to this point.
     * @throws NullPointerException The passed point is {@code null}.
     */
    public EPoint(EPoint point) throws NullPointerException {
        if (point == null) {
            throw new NullPointerException("Null is invalid EPoint");
        }
        x = point.getX();
        y = point.getY();
    }
    
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    
    /**
     * Method tests whether the coordinates of the passed {@link EPoint} match the coordinates of
     * this point.
     *
     * @param obj   Object to be compared to this instance.
     * @return      Whether both coordinates are identical.
     */
    public boolean equals(Object obj) {
        if (obj instanceof EPoint point) {
            if (x == point.getX() && y == point.getY()) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Method returns a hash code for this {@link EPoint}.
     *
     * @return  Hash code for the point.
     */
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    
    /**
     * Method converts this {@link EPoint} into a String of the following format:
     * {@code (<x>,<y>)}.
     *
     * @return  String representation of the point.
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    
}
