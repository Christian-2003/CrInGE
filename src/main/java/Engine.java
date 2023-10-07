import backend.math.EMatrix;
import backend.math.MathException;

public class Engine {
    
    public static void main(String[] args) throws NullPointerException, MathException {
        /*
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
        System.out.println(System.lineSeparator());
        */
        
        /*
        double[][] m3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8 ,9}
        };
        EMatrix d = new EMatrix(m3);
        System.out.println(d);
        System.out.println(System.lineSeparator());
        System.out.println(d.createIdentity());
        */
        
        double[][] m4 = {
                {1, 4, 6},
                {1, 2, 5},
                {3, 2, 1}
        };
        EMatrix e = new EMatrix(m4);
        System.out.println(e);
        System.out.println(System.lineSeparator());
        System.out.println(e.determinant());
    }
    
}
