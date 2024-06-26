package game_engine.view.canvas;

import game_engine.controller.RendererManager;
import javax.swing.JComponent;
import java.awt.Graphics;


/**
 * Class implements a canvas onto which a {@link game_engine.model.map.GameMap}
 * can be rendered through a {@link RendererManager}. The canvas calls a
 * specified RendererManager whenever it needs to be rendered.
 *
 * @author  Christian-2003
 */
public class GameCanvas extends JComponent {

    /**
     * Attribute <i>references</i> the {@link RendererManager} which renders a
     * {@link game_engine.model.map.GameMap} to this component.
     */
    private final RendererManager rendererManager;


    /**
     * Constructor instantiates a new {@link GameCanvas} onto which a
     * {@link RendererManager} can render a {@link game_engine.model.map.GameMap}.
     *
     * @param rendererManager       <i>Reference</i> to the RendererManager to
     *                              be used for rendering.
     * @throws NullPointerException The passed RendererManager is {@code null}.
     */
    public GameCanvas(final RendererManager rendererManager) throws NullPointerException {
        if (rendererManager == null) {
            throw new NullPointerException("Null is invalid RendererManager.");
        }
        this.rendererManager = rendererManager;
    }


    /**
     * Method calls the {@link #rendererManager} to render the game to the
     * canvas.
     *
     * @param g The {@linkplain Graphics} object to protect.
     */
    @Override
    public void paintComponent(final Graphics g) {
        rendererManager.renderGameMap(this, g);
    }

}
