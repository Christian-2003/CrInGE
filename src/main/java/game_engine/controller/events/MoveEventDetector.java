package game_engine.controller.events;

import game_engine.model.entities.Entity;
import game_engine.model.events.MoveEventArgs;
import java.util.Calendar;


/**
 * Class detects entity move events.
 *
 * @author  Christian-2003, Elekt0
 */
public class MoveEventDetector implements EventDetector {

    /**
     * Field stores the singleton-instance of this class.
     */
    private static MoveEventDetector singleton;


    /**
     * Constructor instantiates a new move event detector.
     */
    private MoveEventDetector() {

    }


    /**
     * Detects entity move events.
     *
     * @param entity  Entity to detect move events for.
     */
    @Override
    public void detect(Entity entity) {
        if (entity == null || entity.getMoveListener().isEmpty()) {
            throw new NullPointerException();
        }
        if (entity.getX() != entity.getPreviousX() || entity.getY() != entity.getPreviousY()) {
            // prevent event double triggering
            entity.setPosition(entity.getX(), entity.getY());

            entity.getMoveListener().get().onMove(new MoveEventArgs(
                    Calendar.getInstance(),
                    entity,
                    entity.getPreviousX(),
                    entity.getPreviousY(),
                    entity.getX(),
                    entity.getY()
            ));
        }
    }


    /**
     * Returns the singleton instance.
     *
     * @return  Singleton instance.
     */
    public static MoveEventDetector getInstance() {
        if (singleton == null) {
            singleton = new MoveEventDetector();
        }
        return singleton;
    }

}
