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
     * The array is defined as follows: {@code double[<height>][<width>]}.
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
     * @param height            Height for the matrix.
     * @param width             Width for the matrix.
     * @throws MathException    The passed width or height is less than (or equal to) 1.
     */
    public EMatrix(int height, int width) throws MathException {
        if (width < 1 || height < 1) {
            throw new MathException("EMatrix width and height must be greater than 1");
        }
        values = new double[height][width];
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
        values = new double[matrix.height()][matrix.width()];
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
        if (values[0].length != width() || values.length != height()) {
            throw new MathException("Width and height of matrices do not match");
        }
        for (int y = 0; y < height(); y++) {
            System.arraycopy(values[y], 0, this.values[y], 0, values[y].length);
        }
    }
    
    
    /**
     * Method returns the value at the passed position within the {@link EMatrix}.
     *
     * @param y                             Y-position of the value.
     * @param x                             X-position of the value.
     * @return                              Value at the specified position.
     * @throws IndexOutOfBoundsException    The passed position does not exist within this matrix.
     */
    public double get(int y, int x) throws IndexOutOfBoundsException {
        if (x < 0 || x >= width()) {
            throw new IndexOutOfBoundsException("Index x=" + x + " out of bounds for matrix of width " + width() + ".");
        }
        else if (y < 0 || y >= height()) {
            throw new IndexOutOfBoundsException("Index y=" + y + " out of bounds for matrix of height " + height() + ".");
        }
        return values[y][x];
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
    public void set(int y, int x, double value) throws IndexOutOfBoundsException {
        if (x < 0 || x >= width()) {
            throw new IndexOutOfBoundsException("Index x=" + x + " out of bounds for matrix of width " + width() + ".");
        }
        else if (y < 0 || y >= height()) {
            throw new IndexOutOfBoundsException("Index y=" + y + " out of bounds for matrix of height " + height() + ".");
        }
        values[y][x] = value;
    }
    
    
    /**
     * Method returns the width of the matrix.
     *
     * @return  Width of the matrix.
     */
    public int width() {
        return values[0].length;
    }
    
    /**
     * Method returns the height of the matrix.
     *
     * @return  Height of the matrix.
     */
    public int height() {
        return values.length;
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
     * Method multiplies the passed{@link EMatrix} with this instance and returns the result
     * as a new matrix.
     *
     * @param matrix                Matrix with which this instance shall be multiplied.
     * @return                      Calculated matrix.
     * @throws NullPointerException The passed matrix is {@code null}.
     * @throws MathException        The matrices could not be multiplied.
     */
    public EMatrix multiply(EMatrix matrix) throws NullPointerException, MathException {
        if (matrix == null) {
            throw new NullPointerException("Null is invalid EMatrix");
        }
        if (width() != matrix.height()) {
            throw new MathException("Dimensions of matrices (" + height() + "x" + width() + " and " + matrix.height() + "x" + matrix.width() + ") insufficient for matrix multiplication");
        }
        EMatrix result = new EMatrix(height(), matrix.width());
        for (int i = 0; i < result.height(); i++) {
            for (int j = 0; j < result.width(); j++) {
                for (int k = 0; k < matrix.height(); k++) {
                    result.set(i, j, result.get(i, j) + values[i][k] * matrix.get(k, j));
                }
            }
        }
        return result;
    }
    
    /**
     * Method transposes this {@link EMatrix} and returns the result as a new matrix.
     *
     * @return  Transposed matrix.
     */
    public EMatrix transpose() {
        EMatrix result;
        try {
            result = new EMatrix(height(), width());
        }
        catch (MathException e) {
            //Should never happen:
            return new EMatrix();
        }
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                result.set(y, x, values[x][y]);
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
    
    /**
     * Method converts this {@link EMatrix} into a String of the following format:<br>
     * <c>[<i>&lt;v1&gt;</i> <i>&lt;v1&gt;</i> <i>&lt;v1&gt;</i> ...]<br>
     *    [<i>&lt;v4&gt;</i> <i>&lt;v5&gt;</i> <i>&lt;v6&gt;</i> ...]<br>
     *    [<i>&lt;v7&gt;</i> <i>&lt;v8&gt;</i> <i>&lt;v9&gt;</i> ...]<br>
     *    ...</c>
     *
     * @return  String representation of the matrix.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < height(); y++) {
            builder.append("[");
            for (int x = 0; x < width(); x++) {
                builder.append(values[y][x]);
                builder.append("\t");
            }
            builder.append("]");
            if (y < height() - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
    
}
