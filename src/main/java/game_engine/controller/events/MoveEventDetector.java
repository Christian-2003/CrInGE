package game_engine.controller.events;

import game_engine.model.entities.Entity;
import game_engine.model.events.EventTypes;
import game_engine.model.events.MoveEventArgs;
import game_engine.model.events.MoveListener;

import java.util.Calendar;

/**
 * Class detects entity move events.
 *
 * @author  Christian-2003, Elekt0
 */
public class MoveEventDetector implements EventDetector {

    private static MoveEventDetector singleton;

    private MoveEventDetector() {}

    /**
     * Detects entity move events.
     *
     * @param entity  Entity to detect move events for.
     */
    @Override
    public void detect(Entity entity) {
        if(entity == null) {
            throw new NullPointerException();
        }
        if(entity.getX() != entity.getPreviousX() || entity.getY() != entity.getPreviousY()) {
            // prevent event double triggering
            entity.setPosition(entity.getX(), entity.getY());

            MoveListener listener = (MoveListener) entity.getAllEvents().get(EventTypes.MOVE);
            listener.onMove(new MoveEventArgs(
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
