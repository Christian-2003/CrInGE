package editor.view.project_editor.map_editor;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import editor.model.map.Asset;

/**
 * Represents an Cell within the Map Editor Table.
 * @author Tim Schnur
 */
public class MapEditorCell extends JButton {

    /** Asset of this cell. */
    private Asset asset;

    /** X-Coordinate of this cell. */
    private final int x;
    /** Y-Coordinate of this cell. */
    private final int y;

    /**
     * @param x Coordinate of the cell
     * @param y Coordinate of the cell
     * @param preferredSize of the cell
     * @param backgroundColor of the cell
     */
    public MapEditorCell(final int x, final int y,
    final Dimension preferredSize, final Color backgroundColor) {
        this(x, y, preferredSize, backgroundColor, null);
    }
    /**
     * @param x Coordinate of the cell
     * @param y Coordinate of the cell
     * @param preferedSize of the cell
     * @param backgroundColor of the cell
     * @param asset of the cell
     */
    public MapEditorCell(final int x, final int y, final Dimension preferedSize,
    final Color backgroundColor, final Asset asset) {
        super();
        this.x = x;
        this.y = y;
        this.asset = asset;
        this.setPreferredSize(preferedSize);
        this.setBackground(backgroundColor);

        if (asset != null) {
            asset.setX(x);
            asset.setY(y);
            this.setIcon(asset.getImage());
        }
    }

    /**
     * @param asset this cell should be set to
     */
    public void setAsset(final Asset asset) {
        if (this.asset != null) {
            this.removeAsset();
        }
        this.asset = asset;
        this.asset.setX(this.x);
        this.asset.setY(this.y);
        this.setIcon(asset.getImage());
    }
    /** removes the asset from this cell. */
    public void removeAsset() {
        asset.delete();
        this.asset = null;
        this.setIcon(null);
    }
    /**
     * @return the Asset of this cell
     */
    public Asset getAsset() {
        return asset;
    }

    /**
     * @return the X-Coordinate of this cell
     */
    public int getX() {
        return this.x;
    }
    /**
     * @return the Y-Coordinate of this cell
     */
    public int getY() {
        return this.y;
    }
}
