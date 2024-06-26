package game_engine.controller.utility;

import java.util.UUID;


/**
 * Included useful functions which are needed in multiple class instances.
 *
 * @author  FabianDev001
 */
public class Utils {

    /**
     * Method generates a new random ID.
     *
     * @return  Generated ID.
     */
    public Integer generateNewRandomID() {
        return UUID.randomUUID().hashCode();
    }


    /**
     * An addition to the cmd-logger - In the future will create a log file
     * for details.
     *
     * @param message   Message to log.
     */
    public void logRed(final String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }

}
