package game_engine.controller.events;

import game_engine.model.entities.Entity;

/**
 * Class detects entity move events.
 *
 * @author  Christian-2003, Elekt0
 */
public class MoveEventDetector implements EventDetector {

    private static MoveEventDetector singleton;

    private MoveEventDetector() {

    }

    @Override
    public void detect(Entity entity) {

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
