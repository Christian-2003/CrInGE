package game_engine.controller.events;

import game_engine.model.entities.Entity;

/**
 * Interface needs to be implemented by all event detectors.
 *
 * @author  Christian-2003, Elekt0
 */
public interface EventDetector {

    void detect(Entity entity);

}
