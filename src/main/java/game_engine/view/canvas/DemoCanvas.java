package game_engine.view.canvas;

import javax.swing.*;


/**
 * Class models a DemoCanvas which can display a String.
 *
 * @author  Christian-2003
 */
public class DemoCanvas extends JTextArea {

    /**
     * Constructor instantiates a new {@link DemoCanvas} which displays the passed text within this component.
     *
     * @param text                  Text which shall be displayed within this component.
     * @throws NullPointerException The passed text is {@code null}.
     */
    public DemoCanvas(String text) throws NullPointerException {
        super();
        if (text == null) {
            throw new NullPointerException("Null is invalid text.");
        }
        setText(text);
        setEditable(false);
        setFocusable(false);
    }

}
