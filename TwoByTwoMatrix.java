/**
 * This class contains various methods to manipulate a 2x2 matrix with given elements.
 * @author Varun Chauhan
 */
public class TwoByTwoMatrix {
    /** Top left element of matrix */
    double a;
    /** Top right element of matrix */
    double b;
    /** Bottom left element of matrix */
    double c;
    /** Bottom right element of matrix */
    double d;

    /**
     * Creates a TwoByTwoMatrix assigning 0 to each element
     */
    public TwoByTwoMatrix() { 
    }

    /**
     * Creates a TwoByTwoMatrix with parameters giving initial values of the elements
     * @param a Top left element of matrix
     * @param b Top right element of matrix
     * @param c Bottom left element of matrix
     * @param d Bottom right element of matrix
     */
    public TwoByTwoMatrix(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Returns a String representation of the implicit TwoByTwoMatrix.
     * @return String representation of TwoByTwoMatrix.
     */
    public String toString() {
        return "[" + roundThreeDecimals(this.a) + " " + roundThreeDecimals(this.b) + "]\n[" + roundThreeDecimals(this.c) + ' ' + roundThreeDecimals(this.d) + ']';
    }

    /**
     * Finds the determinant of the implicit TwoByTwoMatrix.
     * @return The determinant of the TwoByTwoMatrix.
     */
    public double determinant() {
        return (this.a * this.d) - (this.b * this.c); // determinant formula
    }

    /**
     * Adds two matrices together.
     * @param other The explicit TwoByTwoMatrix.
     * @return A new TwoByTwoMatrix that is the sum of the given matrices.
     */
    public TwoByTwoMatrix plus(TwoByTwoMatrix other) {
        return new TwoByTwoMatrix(this.a + other.a, this.b + other.b, this.c + other.c, this.d + other.d);
    }

    /**
     * Finds the different between two matrices.
     * @param other The explicit TwoByTwoMatrix.
     * @return A new TwoByTwoMatrix that is the difference between the implicit and explicit matrices.
     */
    public TwoByTwoMatrix minus(TwoByTwoMatrix other) {
        return new TwoByTwoMatrix(this.a - other.a, this.b - other.b, this.c - other.c, this.d - other.d);
    }

    /**
     * Finds the product of two matrices.
     * @param other The explicit TwoByTwoMatrix.
     * @return A new TwoByTwoMatrix that is the product of the two matrices.
     */
    public TwoByTwoMatrix multiply(TwoByTwoMatrix other) {
        double a1 = (this.a * other.a) + (this.b * other.c); // top left element of product matrix
        double a2 = (this.a * other.b) + (this.b * other.d); // top right element of product matrix
        double b1 = (this.c * other.a) + (this.d * other.c); // bottom left element of product matrix
        double b2 = (this.c * other.b) + (this.d * other.d); // bottom right element of product matrix

        return new TwoByTwoMatrix(a1, a2, b1, b2);
    }

    /**
     * Multiplies the implicit TwoByTwoMatrix by a scalar quanitity.
     * @param d The scalar quantity.
     */
    public void scalarMultiply(double d) {
        this.a *= d;
        this.b *= d;
        this.c *= d;
        this.d *= d;
    }

    /**
     * Checks if the implicit TwoByTwoMatrix is an identity matrix.
     * @return True if the implicit TwoByTwoMatrix is an identity matrix; false if not.
     */
    public boolean isIdentityMatrix() {
        return roundThreeDecimals(this.a) == 1 && roundThreeDecimals(this.b) == 0 && roundThreeDecimals(this.c) == 0 && roundThreeDecimals(this.d) == 1;
    }

    /**
     * Checks if the implicit TwoByTwoMatrix has an inverse.
     * @return True if the implicit TwoByTwoMatrix is invertible; false if not.
     */
    public boolean isInvertible() {
        return roundThreeDecimals(this.determinant()) != 0; // if the determinant is 0, that is the only case when there is no inverse
    }

    /**
     * Checks if two matrices are inverses of each other.
     * @param other The explicit TwoByTwoMatrix.
     * @return True if the matrices are inverses of each other; false if not.
     */
    public boolean isInverse(TwoByTwoMatrix other) {
        if(!this.isInvertible() || !this.isInvertible()) // if either matrix isn't invertiable, they can't be inverses
            return false;

        return this.multiply(other).isIdentityMatrix(); // the product of two inverses is the identity matrix
    }

    /**
     * Checks if two matrices have the same values in corresponding elements.
     * @param other The explicit TwoByTwoMatrix
     * @return True if the matrices are equal; false if not.
     */
    public boolean equals(TwoByTwoMatrix other) {
        return this.a == other.a && this.b == other.b && this.c == other.c && this.d == other.d;
    }

    /**
     * Finds the inverse of the implicit TwoByTwoMatrix.
     * @return The inverse of the TwoByTwoMatrix.
     */
    public TwoByTwoMatrix inverse() {
        if(!this.isInvertible()) // accounts for matrices with no inverse
            throw new RuntimeException("Inverse Matrix does not exist.");
        
        TwoByTwoMatrix inverse = new TwoByTwoMatrix(this.d, this.b * -1, this.c * -1, this.a); // find the adjugate
        inverse.scalarMultiply(1.0 / this.determinant()); // divide adjugate by the determinant to find inverse matrix
        return inverse;
    }

    /**
     * Finds the transpose of the implicit TwoByTwoMatrix.
     * @return The transpose of the TwoByTwoMatrix.
     */
    public TwoByTwoMatrix transpose() {
        return new TwoByTwoMatrix(this.a, this.c, this.b, this.d); // swap b and c
    }
    
    /**
     * Rounds a given double to 3 decimal places.
     * @param d The double to be rounded.
     * @return The rounded double.
     */
    public static double roundThreeDecimals(double d) {
        return Math.round(d * 1000) / 1000.0;
    }

}
