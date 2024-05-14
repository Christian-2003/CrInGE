package game_engine.controller.events;

import game_engine.controller.EntityManager;
import game_engine.model.entities.Entity;
import game_engine.model.events.CollisionListener;
import game_engine.model.events.MoveEventArgs;
import game_engine.model.events.MoveListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Elekt0
 */
public class EventDetectionTest {

    @Nested
    @DisplayName("Test event detector instances")
    class Instances {
        @Test
        @DisplayName("CollisionDetector")
        void instantiateCollisionDetector(){
            assertInstanceOf(EventDetector.class, CollisionEventDetector.getInstance());
        }

        @Test
        @DisplayName("MoveEventDetector")
        void instantiateMoveEventDetector(){
            assertInstanceOf(EventDetector.class, MoveEventDetector.getInstance());
        }
    }

    @Nested
    @DisplayName("Test event detector methods")
    class DetectorMethods {
        @Test
        @DisplayName("CollisionDetector detect")
        void detectCollision(){
            assertThrowsExactly(NullPointerException.class, () -> {CollisionEventDetector.getInstance().detect(null);}, "not the correct Exception");
        }

        @Test
        @DisplayName("MoveEventDetector detect")
        void detectMove(){
            assertThrowsExactly(NullPointerException.class, () -> {MoveEventDetector.getInstance().detect(null);}, "not the correct Exception");
        }
    }

    @Nested
    @DisplayName("Test entity event registration")
    class EntityEventRegistration {
        @Test
        @DisplayName("Entity setCollisionListener")
        void emptyEventList(){
            Entity entity = new Entity(true, true, new Dimension(1, 1), new Dimension(1, 1), 0, 0);
            assertEquals(0, entity.getAllEvents().size());
        }

        @Test
        @DisplayName("Entity setCollisionListener")
        void addCollisionListener(){
            Entity entity = new Entity(true, true, new Dimension(1, 1), new Dimension(1, 1), 0, 0);
            assertThrowsExactly(NullPointerException.class, () -> entity.setCollisionListener(null), "not the correct Exception");
        }

        @Test
        @DisplayName("Entity setMoveListener")
        void addMoveListener(){
            Entity entity = new Entity(true, true, new Dimension(1, 1), new Dimension(1, 1), 0, 0);
            assertThrowsExactly(NullPointerException.class, () -> entity.setMoveListener(null), "not the correct Exception");
        }
    }

    @Nested
    @DisplayName("Test entity event retrieval")
    class EntityEventRetrieval {
        @Test
        @DisplayName("Entity getCollisionListener")
        void getCollisionListener(){
            Entity entity = new Entity(true, true, new Dimension(1, 1), new Dimension(1, 1), 0, 0);
            assertTrue(entity.getCollisionListener().isEmpty());
        }

        @Test
        @DisplayName("Entity getMoveListener")
        void getMoveListener(){
            Entity entity = new Entity(true, true, new Dimension(1, 1), new Dimension(1, 1), 0, 0);
            assertTrue(entity.getMoveListener().isEmpty());
        }
    }

    @Nested
    @DisplayName("Test if all events are registered")
    class AllEventsRegistered {
        @Test
        @DisplayName("All events are registered")
        void allEventsRegistered(){
            Entity entity = new Entity(true, true, new Dimension(1, 1), new Dimension(1, 1), 0, 0);
            entity.setCollisionListener((args) -> {});
            entity.setMoveListener((args) -> {});
            assertTrue(entity.getCollisionListener().isPresent());
            assertInstanceOf(CollisionListener.class, entity.getCollisionListener().get());
            assertTrue(entity.getMoveListener().isPresent());
            assertInstanceOf(MoveListener.class, entity.getMoveListener().get());
            assertEquals(2, entity.getAllEvents().size());
        }
    }

    @Nested
    @DisplayName("Test if exception is thrown if event listener is not set")
    class EventTriggerWithoutListener {
        @Test
        @DisplayName("Move event is triggered")
        void moveEventTrigger(){
            Entity entity = new Entity(true, true, new Dimension(1, 1), new Dimension(1, 1), 0, 0);
            assertThrowsExactly(NullPointerException.class, () -> {MoveEventDetector.getInstance().detect(entity);}, "not the correct Exception");
        }

        @Test
        @DisplayName("Collision event is triggered")
        void collisionEventTrigger(){
            Entity entity = new Entity(true, true, new Dimension(1, 1), new Dimension(1, 1), 0, 0);
            assertThrowsExactly(NullPointerException.class, () -> {CollisionEventDetector.getInstance().detect(entity);}, "not the correct Exception");
        }
    }

    @Nested
    @DisplayName("Test if events are triggered")
    class EventTriggerWithListener {
        @Test
        @DisplayName("Move event is triggered")
        void moveEventTrigger(){
            Entity entity = new Entity(true, true, new Dimension(1, 1), new Dimension(1, 1), 0, 0);
            EntityManager.getInstance().put(entity);
            entity.setMoveListener((MoveEventArgs args) -> {});
            entity.setPosition(1, 1);
            entity.setPosition(2, 2);
            assertTrue(entity.isMoved());
            MoveEventDetector.getInstance().detect(entity);
            // Check if the previous position is updated
            assertFalse(entity.isMoved());
        }

        @Test
        @DisplayName("Collision event is triggered")
        void collisionEventTrigger(){
            Entity entity = new Entity(true, true, new Dimension(1, 1), new Dimension(1, 1), 0, 0);
            EntityManager.getInstance().put(entity);
            entity.setCollisionListener((args) -> {
                assertEquals(1, args.getEntitiesInvolved().size());
            });
            Entity entity2 = new Entity(true, true, new Dimension(1, 1), new Dimension(1, 1), 1, 1);
            EntityManager.getInstance().put(entity2);
            CollisionEventDetector.getInstance().detect(entity);
        }
    }

}
