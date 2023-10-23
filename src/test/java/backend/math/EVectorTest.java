package backend.math;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
//10:40-11:00,11:20-
public class EVectorTest {
    @Test
    @DisplayName("adding null to vector")
    void addNull(){
        assertThrowsExactly(NullPointerException.class, () -> {new EVector().add(null);});
    }

    @Test
    @DisplayName("adding two vectors")
    void add(){
        assertEquals(new EVector(2, 3), new EVector(1, 2).add(new EVector(1, 1)));
    }

    @Test
    @DisplayName("substracting null from vector")
    void subNull(){
        assertThrowsExactly(NullPointerException.class, () -> {new EVector().subtract(null);});
    }

    @Test
    @DisplayName("substracting two vectors")
    void sub(){
        assertEquals(new EVector(2, 3), new EVector(3, 8).subtract(new EVector(1, 5)));
    }

    @Test
    void scalarMultiplication(){
        assertEquals(new EVector(3,6), new EVector(1, 2).scalarMultiplication(3));
    }

    @Test
    void dotProduct(){
        assertEquals(34, new EVector(2,4).dotProduct(new EVector(3,7)));
    }

    @Test
    @DisplayName("creating dotproduct of null throws NullPointerException")
    void dotProductNull(){
        assertThrowsExactly(NullPointerException.class, () -> {new EVector().dotProduct(null);});
    }

    @Test
    void length(){
        assertEquals(5, new EVector(3, 4).length());
        assertEquals(0, new EVector().length());
    }

    @Test
    @DisplayName("crossproduct with null throws NullPointerException")
    void angleNull(){
        assertThrowsExactly(NullPointerException.class, () -> {new EVector().angle(null);});
    }

    @Test
    void angle(){
        assertEquals(0, new EVector(100, 2).angle(new EVector(100, 2)));
        assertEquals(0, new EVector(1, 2).angle(new EVector(1, 2)));
        assertEquals(0, new EVector(100, 200).angle(new EVector(100, 200)));
        double result = 0.9097531579; // richtiger Wert für (1,2).angle(5,1) berchnet
        assertEquals(result, new EVector(1, 2).angle(new EVector(5, 1)));
    }
//:20-30
    @Test
    @DisplayName("crossproduct with null throws NullPointerException")
    void crossProductNull(){
        assertThrowsExactly(NullPointerException.class, () -> {new EVector().crossProduct(null);});
    }

    @Test
    void crossProduct(){
        assertEquals(11, new EVector(3, 4).crossProduct(new EVector(1, 5)));
    }
}
