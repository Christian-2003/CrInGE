package game_editor.model.map;

import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * <p>This is the representation of an Image.</p>
 * The purpose of this class to keep track of the usage of ressources.
 * 
 * @author Tim Schnur
 */
public class ImageSource {

    // Source of the Image
    private File file;

    // Usable Imageicon
    private ImageIcon image;

    private int uses;
    
    public ImageSource(String path) throws FileNotFoundException, IOException {
        this(new File(path));
    }
    public ImageSource(File imageFile) throws FileNotFoundException, IOException{
        if(!imageFile.exists()) throw new FileNotFoundException();

        this.file = imageFile;
        this.image = new ImageIcon(ImageIO.read(imageFile));
        this.uses = 0;
    }

    /**
     * returns the count of assets and Entitys that use this image
     */
    public int getUses() {
        return uses;
    }

    public void use(){
        this.uses++;
    }
    public void unuse(){
        this.uses--;
    }
    public ImageIcon getImage() {
        return image;
    }
    public void setImage(String path) throws FileNotFoundException, IOException{
        setImage(new File(path));
    }
    public void setImage(File imageFile) throws FileNotFoundException, IOException{
        if(!imageFile.exists()) throw new FileNotFoundException();

        this.file = imageFile;
        this.image = new ImageIcon(ImageIO.read(imageFile));
    }
    /**
     * returns the name of this Image File
     */
    public String getName(){
        return this.file.getName();
    }
}
