package backend.math;

import java.util.Objects;


/**
 * Class models a matrix and provides basic mathematical operations for matrices.
 *
 * @author  Christian-2003
 */
public class EMatrix {
    
    /**
     * Attribute stores the matrix's values within a two-dimensional array.
     * The array is defined as follows: {@code double[<width>][<height>]}.
     */
    private double[][] values;
    
    
    /**
     * Constructor instantiates a new 1x1 {@link EMatrix}.
     */
    public EMatrix() {
        values = new double[1][1];
    }
    
    /**
     * Constructor instantiates a new {@link EMatrix} with the passed width and height.
     *
     * @param width             Width for the matrix.
     * @param height            Height for the matrix.
     * @throws MathException    The passed width or height is less than (or equal to) 1.
     */
    public EMatrix(int width, int height) throws MathException {
        if (width < 1 || height < 1) {
            throw new MathException("EMatrix width and height must be greater than 1");
        }
        values = new double[width][height];
    }
    
    /**
     * Constructor instantiates a new {@link EMatrix} which contains the passed two-dimensional array
     * as values.
     *
     * @param matrix                Array of values for this matrix.
     * @throws NullPointerException The passed array is {@code null}.
     * @throws MathException        The width or height of the passed array is less than (or equal to) 1.
     */
    public EMatrix(double[][] matrix) throws NullPointerException, MathException {
        if (matrix == null) {
            throw new NullPointerException("Null is invalid matrix");
        }
        if (matrix.length < 1 || matrix[0].length < 1) {
            throw new MathException("EMatrix width and height must be greater than 1");
        }
        values = new double[matrix.length][matrix[0].length];
        setMatrix(matrix);
    }
    
    /**
     * Constructor instantiates a new {@link EMatrix}. The values of the passed matrix are copied
     * into this instance.
     *
     * @param matrix                Matrix whose values shall be copied into this instance.
     * @throws NullPointerException The passed matrix is {@code null}.
     */
    public EMatrix(EMatrix matrix) throws NullPointerException {
        if (matrix == null) {
            throw new NullPointerException("Null is invalid matrix");
        }
        values = new double[matrix.width()][matrix.height()];
        try {
            setMatrix(matrix.getMatrix());
        }
        catch (MathException e) {
            //This should actually never happen since with and height were defined above.
        }
    }
    
    
    /**
     * Method returns a two-dimensional array containing all values of this matrix. The array's format
     * is as follows: {@code double[<width>][<height>]}.
     *
     * @return  Two-dimensional array containing all values.
     */
    public double[][] getMatrix() {
        return values;
    }
    
    /**
     * Method sets the passed two-dimensional array as this {@link EMatrix}-instance's values.
     *
     * @param values                New values for this matrix.
     * @throws NullPointerException The passed array is {@code null}.
     * @throws MathException        The width and height of the matrices do not match.
     */
    public void setMatrix(double[][] values) throws NullPointerException, MathException {
        if (values == null) {
            throw new NullPointerException("Null is invalid matrix");
        }
        if (values.length != width() || values[0].length != height()) {
            throw new MathException("Width and height of matrices do not match");
        }
        for (int x = 0; x < width(); x++) {
            System.arraycopy(values[x], 0, this.values[x], 0, height());
        }
    }
    
    
    /**
     * Method returns the value at the passed position within the {@link EMatrix}.
     *
     * @param x                             X-position of the value.
     * @param y                             Y-position of the value.
     * @return                              Value at the specified position.
     * @throws IndexOutOfBoundsException    The passed position does not exist within this matrix.
     */
    public double get(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0 || x >= width()) {
            throw new IndexOutOfBoundsException("Index x=" + x + " out of bounds for matrix of width " + width() + ".");
        }
        else if (y < 0 || y >= height()) {
            throw new IndexOutOfBoundsException("Index y=" + y + " out of bounds for matrix of height " + height() + ".");
        }
        return values[x][y];
    }
    
    /**
     * Method changes the value at the passed position within the {@link EMatrix} to the specified
     * value.
     *
     * @param x                             X-position of the value.
     * @param y                             Y-position of the value.
     * @param value                         New value for the position.
     * @throws IndexOutOfBoundsException    The passed position does not exist within this matrix.
     */
    public void set(int x, int y, double value) throws IndexOutOfBoundsException {
        if (x < 0 || x >= width()) {
            throw new IndexOutOfBoundsException("Index x=" + x + " out of bounds for matrix of width " + width() + ".");
        }
        else if (y < 0 || y >= height()) {
            throw new IndexOutOfBoundsException("Index y=" + y + " out of bounds for matrix of height " + height() + ".");
        }
        values[x][y] = value;
    }
    
    
    /**
     * Method returns the width of the matrix.
     *
     * @return  Width of the matrix.
     */
    public int width() {
        return values.length;
    }
    
    /**
     * Method returns the height of the matrix.
     *
     * @return  Height of the matrix.
     */
    public int height() {
        return values[0].length;
    }
    
    
    /**
     * Method adds the passed {@link EMatrix} to this instance and returns the result as a
     * new matrix.
     *
     * @param matrix                Matrix to be added to this instance.
     * @return                      Calculated matrix.
     * @throws NullPointerException The passed matrix is {@code null}.
     * @throws MathException        The width and height of the matrices do not match.
     */
    public EMatrix add(EMatrix matrix) throws NullPointerException, MathException {
        if (matrix == null) {
            throw new NullPointerException("Null is invalid EMatrix");
        }
        if (width() != matrix.width() || height() != matrix.height()) {
            throw new MathException("Width and height of matrices do not match");
        }
        EMatrix result = new EMatrix(width(), height());
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                result.set(x, y, values[x][y] + matrix.get(x, y));
            }
        }
        return result;
    }
    
    /**
     * Method subtracts the passed {@link EMatrix} from this instance and returns the
     * result as a new matrix.
     *
     * @param matrix                Matrix to be subtracted from this instance.
     * @return                      Calculated matrix.
     * @throws NullPointerException The passed matrix is {@code null}.
     * @throws MathException        The width and height of the matrices do not match.
     */
    public EMatrix subtract(EMatrix matrix) throws NullPointerException, MathException {
        if (matrix == null) {
            throw new NullPointerException("Null is invalid EMatrix");
        }
        if (width() != matrix.width() || height() != matrix.height()) {
            throw new MathException("Width and height of matrices do not match");
        }
        EMatrix result = new EMatrix(width(), height());
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                result.set(x, y, values[x][y] - matrix.get(x, y));
            }
        }
        return result;
    }
    
    /**
     * Method multiplies this {@link EMatrix} with the passed scalar and returns
     * the result as a new matrix.
     *
     * @param scalar    Scalar with whom this matrix shall be multiplied.
     * @return          Calculated matrix.
     */
    public EMatrix multiply(double scalar) {
        EMatrix result;
        try {
            result = new EMatrix(width(), height());
        }
        catch (MathException e) {
            //Should not happen, since width and height should be correct:
            return new EMatrix();
        }
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                result.set(x, y, values[x][y] * scalar);
            }
        }
        return result;
    }
    
    
    /**
     * Method tests whether this {@link EMatrix} is a square matrix, i.e.
     * {@code width() == height()}.
     *
     * @return  Whether this matrix is a square matrix.
     */
    public boolean isSquare() {
        return width() == height();
    }
    
    
    /**
     * Method tests whether the passed {@link EMatrix} is identical to this matrix.
     *
     * @param obj   Matrix to be compared to this instance.
     * @return      Whether both matrices are identical.
     */
    public boolean equals(Object obj) {
        if (obj instanceof EMatrix matrix) {
            if (width() != matrix.width() || height() != matrix.height()) {
                return false;
            }
            for (int x = 0; x < width(); x++) {
                for (int y = 0; y < height(); y++) {
                    if (values[x][y] != matrix.get(x, y)) {
                        return false;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Method generates a hash code for this {@link EMatrix}.
     *
     * @return  Hash code for the matrix.
     */
    public int hashCode() {
        return Objects.hash(values);
    }
    
}
