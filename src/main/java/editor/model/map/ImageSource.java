package editor.model.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * <p>This is the representation of an Image.</p>
 * The purpose of this class to keep track of the usage of ressources.
 * @author Tim Schnur
 */
public class ImageSource {

    /** Source of the Image. */
    private File file;

    /** Usable Imageicon. */
    private ImageIcon image;

    /** uses of this imageSource. */
    private int uses;

    /**
     * @param path to the imageSource
     * @throws FileNotFoundException if File can not be found
     * @throws IOException if some IO Error occoures
     */
    public ImageSource(final String path)
    throws FileNotFoundException, IOException {
        this(new File(path));
    }
    /**
     * @param imageFile of the Image Source
     * @throws FileNotFoundException if File can not be found
     * @throws IOException if some IO Error occoures
     */
    public ImageSource(final File imageFile)
    throws FileNotFoundException, IOException {
        if (!imageFile.exists()) {
            throw new FileNotFoundException();
        }

        this.file = imageFile;
        this.image = new ImageIcon(ImageIO.read(imageFile));
        this.uses = 0;
    }

    /**
     * @return the count of assets and Entitys that use this image
     */
    public int getUses() {
        return uses;
    }

    /** increases the counter of usages. */
    public void use() {
        this.uses++;
    }
    /** decreases the counter of usages. */
    public void unuse() {
        this.uses--;
    }
    /**
     * @return the ImageIcon of this Source
     */
    public ImageIcon getImage() {
        return image;
    }
    /**
     * sets a new image for this source.
     * @param path to the image
     * @throws FileNotFoundException if File can not be found
     * @throws IOException if some IO Error occoures
     */
    public void setImage(final String path)
    throws FileNotFoundException, IOException {
        setImage(new File(path));
    }
    /**
     * sets a new image for this source.
     * @param imageFile to the image
     * @throws FileNotFoundException if File can not be found
     * @throws IOException if some IO Error occoures
     */
    public void setImage(final File imageFile)
    throws FileNotFoundException, IOException {
        if (!imageFile.exists()) {
            throw new FileNotFoundException();
        }

        this.file = imageFile;
        this.image = new ImageIcon(ImageIO.read(imageFile));
    }
    /**
     * @return the name of this Image File.
     */
    public String getName() {
        return this.file.getName();
    }
}
