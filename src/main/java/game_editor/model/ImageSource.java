package game_editor.model;

import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * TODO add description
 * 
 * @author Tim Schnur
 */
public class ImageSource {

    private File file;

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
    public String getName(){
        return this.file.getName();
    }
}
