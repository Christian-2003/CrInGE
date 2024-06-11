package editor.model.map;

import javax.swing.ImageIcon;

/**
 * Represents one Block of the Map.
 * @see {@link Map}
 * @author Tim Schnur
 */
public class Asset {

    /** X-Coordinates within the map. */
    private int x;
    /** Y-Coordinates within the map. */
    private int y;

    /** Image of the block. */
    private ImageSource imageSource;

    /** parent Group of the asset. */
    private Group parentGroup;

    /**
     * creates an Asset Object.
     * @param parentGroup of the asset
     * @param imageSource of the Asset
     * @param x coordinate of the asset
     * @param y coordinate of the asset
     */
    public Asset(final Group parentGroup, final ImageSource imageSource,
    final int x, final int y) {
        this.parentGroup = parentGroup;
        this.imageSource = imageSource;
        imageSource.use();
        this.x = x;
        this.y = y;

        if (parentGroup != null) {
            parentGroup.add(this);
        }
    }
    /**
     * clones an Asset with new Coordinates.
     * @param clone for the new asset
     * @param x coordinates of the new asset
     * @param y coordinates of the new asset
     */
    public Asset(final Asset clone, final int x, final int y) {
        this(clone.getParentGroup(), clone.getImageSource(), x, y);
    }

    /**
     * @return the X-Coordinates of the asset
     */
    public int getX() {
        return this.x;
    }
    /**
     * sets the X-Coordinates of the asset.
     * @param x the new value
     */
    public void setX(final int x) {
        this.x = x;
    }
    /**
     * @return the Y-Coordinates of the asset
     */
    public int getY() {
        return y;
    }
    /**
     * sets the X-Coordinates of the asset.
     * @param y the new value
     */
    public void setY(final int y) {
        this.y = y;
    }
    /**
     * @return the parent Gorup of this asset
     */
    public Group getParentGroup() {
        return parentGroup;
    }
    /**
     * sets the parent Gorup of this asset.
     * @param parentGroup to be added to
     */
    public void setParentGroup(final Group parentGroup) {
        if (parentGroup != null) {
            parentGroup.add(this);
        }
        if (this.parentGroup != null) {
            parentGroup.remove(this);
        }
        this.parentGroup = parentGroup;
    }
    /**
     * sets the imageSource for this asset.
     * @param imageSource to be set
     */
    public void setImageSource(final ImageSource imageSource) {
        this.imageSource.unuse();
        this.imageSource = imageSource;
        this.imageSource.use();
    }
    /**
     * @return ImageSource Object with all Image informations
     */
    public ImageSource getImageSource() {
        return imageSource;
    }
    /**
     * The actual Image of the Block.
     * @return ImageIcon of the ImageSource Object
     */
    public ImageIcon getImage() {
        return imageSource.getImage();
    }

    /**
     * <p> Clears up all data trash of the asset. </p>
     * <b> Should be called before unreferencing it </b>
     */
    public void delete() {
        imageSource.unuse();
        parentGroup.remove(this);
    }
}
