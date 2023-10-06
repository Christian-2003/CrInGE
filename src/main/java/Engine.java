import backend.math.EMatrix;
import backend.math.MathException;

public class Engine {
    
    public static void main(String[] args) throws NullPointerException, MathException {
        double[][] m1 = {
                {1, 2, 3},
                {4, 5, 6}
        };
        double[][] m2 = {
                {1, 2, 3, 4},
                {4, 5, 6, 7},
                {1, 2, 3, 4}
        };
        
        EMatrix a = new EMatrix(m1);
        System.out.println(a);
        System.out.println("A = (" + a.height() + "x" + a.width() + ")");
        System.out.println(System.lineSeparator());
        
        EMatrix b = new EMatrix(m2);
        System.out.println(b);
        System.out.println("B = (" + b.height() + "x" + b.width() + ")");
        System.out.println(System.lineSeparator());
        
        EMatrix c = a.multiply(b);
        System.out.println(c);
    }
    
}
