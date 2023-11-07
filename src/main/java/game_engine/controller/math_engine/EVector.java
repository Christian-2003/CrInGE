package game_engine.controller.math_engine;


/**
 * Class models a two-dimensional vector and provides basic mathematical operations
 * for vectors.
 *
 * @author  Christian-2003
 */
public class EVector extends EPoint {
    
    /**
     * Constructor instantiates a new {@link EVector} to {@code (0,0)}.
     */
    public EVector() {
        super();
    }
    
    /**
     * Constructor instantiates the new {@link EVector} with the specified coordinates.
     *
     * @param x X-coordinate for the vector.
     * @param y Y-coordinate for the vector.
     */
    public EVector(int x, int y) {
        super(x, y);
    }
    
    /**
     * Constructor instantiates a new {@link EVector} and copies the coordinates of the passed vector
     * to this instance.
     *
     * @param vector                Vector whose coordinates shall be copied to this vector.
     * @throws NullPointerException The passed vector is {@code null}.
     */
    public EVector(EVector vector) {
        super(vector);
    }
    
    
    /**
     * Method adds the passed {@link EVector} to this vector and returns the result as a new vector.
     *
     * @param vector                Vector which shall be added to this vector.
     * @return                      Calculated vector.
     * @throws NullPointerException The passed vector is {@code null}.
     */
    public EVector add(EVector vector) throws NullPointerException {
        if (vector == null) {
            throw new NullPointerException("Null is invalid EVector");
        }
        return new EVector(x + vector.getX(), y + vector.getY());
    }
    
    /**
     * Method subtracts the passed {@link EVector} from this vector and returns the result as a new vector.
     *
     * @param vector                Vector which shall be subtracted from this vector.
     * @return                      Calculated vector.
     * @throws NullPointerException The passed vector is {@code null}.
     */
    public EVector subtract(EVector vector) throws NullPointerException {
        if (vector == null) {
            throw new NullPointerException("Null is invalid EVector");
        }
        return new EVector(x - vector.getX(), y - vector.getY());
    }
    
    /**
     * Method multiplies this {@link EVector} with the passed scalar and returns the result as a new vector.
     *
     * @param scalar    Scalar with whom this vector shall be multiplied.
     * @return          Calculated vector.
     */
    public EVector scalarMultiplication(int scalar) {
        return new EVector(x * scalar, y * scalar);
    }
    
    /**
     * Method multiplies this {@link EVector} with the passed vector and returns the result as a scalar.
     *
     * @param vector    Vector with whom this instance shall be multiplied.
     * @return          Calculated scalar.
     */
    public int dotProduct(EVector vector) throws NullPointerException {
        if (vector == null) {
            throw new NullPointerException("Null is invalid EVector");
        }
        return x * vector.getX() + y * vector.getY();
    }
    
    /**
     * Method calculates the length of this vector.
     *
     * @return  Length of the vector.
     */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }
    
    /**
     * Calculates the angle between this {@link EVector} and the passed vector in <b>radiant</b>.
     *
     * @param vector                Vector with whom the angle shall be calculated.
     * @return                      Angle between this vector and the passed vector.
     * @throws NullPointerException The passed vector is {@code null}.
     */
    public double angle(EVector vector) throws NullPointerException {
        final int decimalPlace = 10;
        if (vector == null) {
            throw new NullPointerException("Null is invalid EVector");
        }
        else if (this.equals(vector)){
            return 0.0;
        }
        double step = dotProduct(vector) / (length() * vector.length());
        if(step > 1){
            return 0.0;
        }
        return Math.round(Math.acos(step)*Math.pow(10, decimalPlace))/Math.pow(10, decimalPlace);
    }
    
    /**
     * Method calculates the cross product of this {@link EVector} and the passed vector.
     * Since these vectors are two-dimensional, the result is a scalar that is identical to
     * the determinant of the corresponding 2x2 matrix.
     *
     * @param vector                Vector with whom the cross product shall be calculated.
     * @return                      Calculated cross product.
     * @throws NullPointerException The passed vector is {@code null}.
     */
    public int crossProduct(EVector vector) throws NullPointerException {
        if (vector == null) {
            throw new NullPointerException("Null is invalid EVector");
        }
        return x * vector.getY() - y * vector.getX();
    }
    
    public boolean isLinearlyDependend(EVector vector) throws NullPointerException {
        if(vector == null){
            throw new NullPointerException("Null is invalid EVector");
        }
        if(this.x/vector.getX() == this.y/vector.getY()){
            return true;
        }
        return false;
    }
}
