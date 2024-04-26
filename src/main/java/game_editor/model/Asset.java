package game_editor.model;

import javax.swing.ImageIcon;

/**
 * TODO add descriptiom
 * 
 * @author Tim Schnur
 */
public class Asset {
    
    private int x, y;

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
        imageSource.unuse();
        this.imageSource = imageSource;
        imageSource.use();
    }
    public ImageSource getImageSource() {
        return imageSource;
    }
    public ImageIcon getImage(){
        return imageSource.getImage();
    }

    public void delete(){
        imageSource.unuse();
        parentGroup.remove(this);
    }
}
