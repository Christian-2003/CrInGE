package game_engine.view.canvas;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import game_engine.model.GameMap;
import javax.swing.*;


/**
 * Class implements a canvas which can display a {@link GameMap} with OpenGL.
 *
 * @author      Christian-2003
 * @deprecated  For some reason that I do not understand, OpenGL does not work with this repository. I will fix this
 *              later,for now, use {@link SwingGameCanvas} instead!
 */
public class GameCanvas implements GLEventListener {

    /**
     * Attribute references the {@link GameMap} which shall be rendered by this canvas.
     */
    private GameMap map;





    /**
     * Method is called by the referenced {@linkplain GLAutoDrawable} immediately after the OpenGL context is
     * initialized.
     *
     * @param drawable  Instance which called the method.
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        //Do nothing...
    }


    /**
     * Method is called by the referenced {@linkplain GLAutoDrawable} to dispose of this canvas.
     *
     * @param drawable  Instance which called the method.
     */
    @Override
    public void dispose(GLAutoDrawable drawable) {
        //Do nothing...
    }


    /**
     * Method is called by the referenced {@linkplain GLAutoDrawable} to initiate rendering by the canvas.
     *
     * @param drawable  Instance which called the method.
     */
    @Override
    public void display(GLAutoDrawable drawable) {

    }


    /**
     * Method is called by the referenced {@linkplain GLAutoDrawable} during the first repaint.
     *
     * @param drawable  Instance which called the method.
     * @param x         New x-coordinate for the canvas.
     * @param y         New y-coordinate for the canvas.
     * @param width     New width for the canvas.
     * @param height    New height for the canvas.
     */
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //Do nothing...
    }

}
