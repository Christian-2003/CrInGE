package game_engine.controller.math_engine;


/**
 * Class models a math exception which can be thrown on math-related errors by the math engine.
 *
 * @author  Christian-2003
 */
public class MathException extends Exception {
    
    /**
     * Constructor instantiates a new {@link MathException} with the passed message.
     *
     * @param message   Message to be delivered alongside the exception.
     */
    public MathException(String message) {
        super(message);
    }

}
