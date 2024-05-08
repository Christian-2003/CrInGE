package game_engine.controller.events;

import game_engine.model.entities.Entity;

/**
 * Class detects entity collision events.
 *
 * @author  Christian-2003, Elekt0
 */
public class CollosionEventDetector implements EventDetector {

    private static CollosionEventDetector singleton;

    private CollosionEventDetector() {

    }

    @Override
    public void detect(Entity entity) {

    }

    public static CollosionEventDetector getInstance() {
        if (singleton == null) {
            singleton = new CollosionEventDetector();
        }
        return singleton;
    }

}
