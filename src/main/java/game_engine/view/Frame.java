package game_engine.view;

import game_engine.view.canvas.DemoCanvas;
import javax.swing.*;
import java.awt.*;


/**
 * Class models a frame which resembles the main window of the compiled video game.
 * This frame contains a {@link DemoCanvas} to display a String.
 *
 * @author  Christian-2003
 */
public class Frame extends JFrame {

    /**
     * Attribute stores the canvas which is used to display a String.
     */
    private final DemoCanvas demoCanvas;


    /**
     * Constructor instantiates a new {@link Frame} which contains a {@link DemoCanvas} to display a String.
     *
     * @param title                 Title to be displayed within the Frame.
     * @throws NullPointerException The passed text is {@code null}.
     */
    public Frame(String title, String[] lines) throws NullPointerException {
        super(title);
        demoCanvas = new DemoCanvas(lines);
        add(demoCanvas, BorderLayout.CENTER);
        setTitle("Demo Game");
        setSize(480, 320); //HVGA screen resolution.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    /**
     * Method scrolls down.
     */
    public void scrollDown() {
        demoCanvas.scrollDown();
    }


    /**
     * Method scrolls up.
     */
    public void scrollUp() {
        demoCanvas.scrollUp();
    }

}
