package game_engine.view;

import game_engine.controller.RendererManager;
import game_engine.view.canvas.DemoCanvas;
import game_engine.view.canvas.GameCanvas;

import javax.swing.*;
import java.awt.*;


/**
 * Class models a frame which resembles the main window of the compiled video game.
 * This frame contains a {@link DemoCanvas} to display a String.
 *
 * @author  Christian-2003
 */
public class GameFrame extends JFrame {

    /**
     * Attribute stores the canvas on which the {@link game_engine.model.GameMap} will be displayed.
     */
    private final GameCanvas canvas;


    /**
     * Constructor instantiates a new {@link GameFrame}.
     *
     * @param rendererManager       Reference to the RendererManager that is used for rendering.
     * @throws NullPointerException The passed RendererManager is {@code null}.
     */
    public GameFrame(RendererManager rendererManager) throws NullPointerException {
        super("CrInGE Engine");
        canvas = new GameCanvas(rendererManager);
        add(canvas, BorderLayout.CENTER);
        setSize(480, 320); //HVGA screen resolution.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    /**
     * Method repaints the {@link #canvas}. This is a quality of life method that is not necessarily required, but makes
     * life a lot easier.
     */
    public void repaintCanvas() {
        invalidate();
        canvas.invalidate();
        canvas.repaint();
    }

}
