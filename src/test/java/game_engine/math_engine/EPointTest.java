package game_engine.controller.math_engine;

import game_engine.controller.math_engine.EPoint;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tim Schnur
 */
public class EPointTest {

    @Nested
    @DisplayName("Test the EPoint-construtors")
    class Creation{
        @Test
        @DisplayName("default constructor with values")
        void createEPoint(){
            assertInstanceOf(EPoint.class, new EPoint(0,0));
        }

        @Test
        @DisplayName("copy values of EPoint in new EPoint")
        void castEPoint(){
            assertInstanceOf(EPoint.class, new EPoint(new EPoint(0, 0)));
        }

        @Test
        @DisplayName("creating EPoint with null throws NullPointerException")
        void castEPointNull(){
            assertThrowsExactly(NullPointerException.class, () -> {new EPoint(null);}, "not the correct Exception");
        }
    }

    @Nested
    @DisplayName("Test Getter and Setter")
    class GetterSetter{
        EPoint point;
        @BeforeEach
        void setPoint(){
            point = new EPoint(3, 4);
        }

        @Test
        @DisplayName("Get X returns correct")
        void getX(){
            assertEquals(3, point.getX());
        }

        @Test
        @DisplayName("Get Y returns correct")
        void getY(){
            assertEquals(4, point.getY());
        }

        @Test
        @DisplayName("X is set correctly")
        void setX(){
            point.setX(10);
            assertEquals(10, point.getX());
        }

        @Test
        @DisplayName("Y is set correctly")
        void setY(){
            point.setY(10);
            assertEquals(10, point.getY());
        }
    }

    @Test
    void equals(){
        assertTrue(new EPoint(0,0).equals(new EPoint(0,0)));
    }

    @Test
    @DisplayName("null is not equal")
    void equalsNull(){
        assertFalse(new EPoint(0,0).equals(new EPoint(0,1)));
    }

    @Test
    @DisplayName("same Points have same hashCode")
    void sameHash(){
        assertEquals(new EPoint(0, 0).hashCode(), new EPoint(0, 0).hashCode());
    }

    @Test
    @DisplayName("different Points have differetn hashCode")
    void differentHash(){
        assertNotEquals(new EPoint(0, 0).hashCode(), new EPoint(0, 1).hashCode());
    }

    @Test
    @DisplayName("to String returns correct")
    void String(){
        assertEquals("(0,0)", new EPoint(0, 0).toString());
    }
}
