package game_editor.model.map;

import javax.swing.ImageIcon;

/**
 * Represents one Block of the Map
 * 
 * @see {@link MapRepository}
 * 
 * @author Tim Schnur
 */
public class Asset {
    
    // Koordinates within the map
    private int x, y;

    // Image of the block
    private ImageSource imageSource;

    private Group parentGroup;

    public Asset(Group parentGroup, ImageSource imageSource, int x, int y){
        this.parentGroup = parentGroup;
        this. imageSource = imageSource;
        imageSource.use();
        this.x = x;
        this.y = y;
    }
    public Asset(Asset clone, int x, int y){
        this(clone.getParentGroup(), clone.getImageSource(), x, y);
    }
    
    public int getX(){
        return this.x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Group getParentGroup() {
        return parentGroup;
    }
    public void setParentGroup(Group parentGroup) {
        this.parentGroup = parentGroup;
    }
    public void setImageSource(ImageSource imageSource) {
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
     * The actual Image of the Block
     * @return ImageIcon of the ImageSource Object
     */
    public ImageIcon getImage(){
        return imageSource.getImage();
    }

    /**
     * <p> Clears up all data trash of the asset. </p>
     * <b> Should be called before unreferencing it </b>
     */
    public void delete(){
        imageSource.unuse();
        parentGroup.remove(this);
    }
}
