package backend.math;

import java.util.Objects;


/**
 * Class models a matrix and provides basic mathematical operations for matrices.
 * In order to easily create an EMatrix, use a two-dimensional double array created as follows:
 * <br><c>
 * &nbsp;&nbsp;&nbsp;&nbsp;double[][] a = {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<i>&lt;a11&gt;</i>, <i>&lt;a12&gt;</i>, <i>&lt;a13&gt;</i>, ...},<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<i>&lt;a21&gt;</i>, <i>&lt;a22&gt;</i>, <i>&lt;a23&gt;</i>, ...},<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<i>&lt;a31&gt;</i>, <i>&lt;a32&gt;</i>, <i>&lt;a33&gt;</i>, ...},<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;};
 * </c><br>
 * and pass as argument to the constructor {@link #EMatrix(double[][])}.
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
     * Method generates an identity matrix for this {@link EMatrix}. This only works with an {@code NxN} matrix.
     * The identity matrix is as follows:
     * <br><c>
     *     [1, 0, 0, 0, ...]<br>
     *     [0, 1, 0, 0, ...]<br>
     *     [0, 0, 1, 0, ...]<br>
     *     [0, 0, 0, 1, ...]<br>
     *     ...
     * </c>
     *
     * @return                  Identity matrix for this instance.
     * @throws MathException    The {@link #width()} and {@link #height()} of the matrix are insufficient to
     *                          create an identity matrix.
     */
    public EMatrix createIdentity() throws MathException {
        if (height() != width()) {
            throw new MathException("Cannot create matrix identity for " + height() + "x" + width() + "-matrix");
        }
        EMatrix identity = new EMatrix(height(), width());
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (x == y) {
                    identity.set(y, x, 1);
                }
                else {
                    identity.set(y, x, 0);
                }
            }
        }
        return identity;
    }
    
    
    /**
     * Method calculates the determinant for this {@link EMatrix}. This only works with an {@code NxN} matrix.
     *
     * @return                  Determinant of this matrix.
     * @throws MathException    The {@link #width()} and {@link #height()} of the matrix are insufficient to
     *                          calculate a determinant.
     */
    public double determinant() throws MathException {
        if (width() != height()) {
            throw new MathException("Cannot calculate determinant for " + height() + "x" + width() + "-matrix");
        }
        return det(values);
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
                    if (values[y][x] != matrix.get(y, x)) {
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
        int[] hashs = new int[values.length];
        for(int i=0; i<hashs.length; i++){
            hashs[i] = Objects.hash(values[i]);
        }
        return Objects.hash(hashs);
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
    
    
    /**
     * Method calculates the determinant of the passed two-dimensional array.
     *
     * @param matrix                Two-dimensional array whose determinant shall be calculated.
     * @return                      The determinant for the array.
     * @throws NullPointerException The passed array is {@code null}.
     * @throws MathException        The determinant could not be calculated.
     */
    private double det(double[][] matrix) throws NullPointerException, MathException {
        if (matrix == null) {
            throw new NullPointerException("Null is invalid matrix");
        }
        if (matrix.length < 1 || matrix[0].length < 1) {
            throw new MathException("Matrix too small to calculate determinant");
        }
        if (matrix.length != matrix[0].length) {
            throw new MathException("Width and height of matrix must match to calculate determinant");
        }
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        double det = 0;
        for (int i = 0; i < matrix.length; i++) {
            double[][] newMatrix = removeRowAndColFromMatrix(matrix, i, 0);
            double newDet = det(newMatrix);
            if (i % 2 == 0) {
                det += matrix[0][i] * newDet;
            }
            else {
                det -= matrix[0][i] * newDet;
            }
        }
        return det;
    }
    
    
    /**
     * Method removes the specified row and column from the passed two-dimensional array. The result is returned as
     * array which is exactly one unit smaller in both directions than the passed array.
     *
     * @param matrix                        Two-dimensional array from which a row and column shall be removed.
     * @param col                           Index of the column to be removed.
     * @param row                           Index of the row to be removed.
     * @return                              Two-dimensional array without the respective column and row.
     * @throws NullPointerException         The passed array is {@code null}.
     * @throws IndexOutOfBoundsException    The specified column or row is out of bounds.
     * @throws MathException                The column or row could not be removed.
     */
    private double[][] removeRowAndColFromMatrix(double[][] matrix, int col, int row) throws NullPointerException, IndexOutOfBoundsException, MathException {
        if (matrix == null) {
            throw new NullPointerException("Null is invalid matrix");
        }
        if (row < 0 || row > matrix.length) {
            throw new IndexOutOfBoundsException("Row index " + row + " out of bounds for length " + matrix.length);
        }
        if (col < 0 || col > matrix.length) {
            throw new IndexOutOfBoundsException("Column index " + col + " out of bounds for length " + matrix.length);
        }
        if (matrix.length < 1 || matrix[0].length < 1) {
            throw new MathException("Matrix is too small to remove row or column");
        }
        double[][] result = new double[matrix.length - 1][matrix[0].length - 1];
        boolean rowSkipped = false;
        for (int y = 0; y < matrix.length; y++) {
            if (y == row) {
                rowSkipped = true;
                continue;
            }
            boolean colSkipped = false;
            for (int x = 0; x < matrix[y].length; x++) {
                if (x == col) {
                    colSkipped = true;
                    continue;
                }
                int nX = x;
                int nY = y;
                if (colSkipped) {
                    nX--;
                }
                if (rowSkipped) {
                    nY--;
                }
                result[nY][nX] = matrix[y][x];
            }
        }
        return result;
    }
    
}
