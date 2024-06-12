/**
 * In this package all game_data_formats are defined
 */
package game_engine.controller.data_handler.game_data_format;

import java.io.File;
import java.io.FileNotFoundException;


/**
 * @see game_engine.model.entities.Entity
 * @author FabianDev001
 */
public class ImageSource {

    /**
     * Attribute store the file with associated file path.
     */
    private final File file;

    /**
     * Attribute stores how many entities use this image source as texture.
     */
    private int uses = 1;


    /**
     * Constructor instantiates a new image source for the passed file.
     * @param file  File from which to create the image source.
     */
    public ImageSource(final File file) {
        this.file = file;
    }


    /**
     * Method returns the file from this image source.
     * @return  File of this image source.
     */
    public File getFile() {
        return file;
    }

    /**
     * Method changes the file for this image source.
     * @param file                      New file for this image source.
     * @return                          This image source.
     * @throws FileNotFoundException    The passed file does not exist.
     */
    public ImageSource setFile(final File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(
                    "Couldn't find file '"
                            + file.getPath()
                            + "' for image source."
            );
        }
        setFile(file);
        return this;
    }

    /**
     * Method changes the file for this image source.
     * ...based on the passed file path.
     * @param path                      File path.
     * @return                          This image source.
     * @throws FileNotFoundException    The passed file path does not exist.
     */
    public ImageSource setFile(final String path) throws FileNotFoundException {
        File newFile = new File(path);
        if (!newFile.exists()) {
            throw new FileNotFoundException(
                    "Couldn't find file with path: "
                            + path
            );
        }
        setFile(newFile);
        return this;
    }

    /**
     * Method returns how many entities use this image source.
     * A value of {@code 0} indicates that
     * the image source is not used at all.
     * @return  How often the image source is used.
     */
    public int getUses() {
        return uses;
    }

    /**
     * Method changes how often the image source is used.
     * @param uses                      How often the image source is used.
     * @return                          This image source.
     * @throws IllegalArgumentException The number of uses is not
     *                                  allowed to be less than 0.
     */
    public ImageSource setUses(final int uses) {
        if (uses < 0) {
            throw new IllegalArgumentException("Negative uses are not allowed");
        }
        this.uses = uses;
        return this;
    }

    /**
     * Method increments the number of uses for theis image source.
     */
    public void incrementUses() {
        this.uses++;
    }

    /**
     * Method decrements the number of uses for this image source.
     *
     * @throws IndexOutOfBoundsException    The image source
     *                                      cannot be decremented below 0.
     */
    public void decrementUses() {
        if (this.uses == 0) {
            throw new IndexOutOfBoundsException(
                    "Can't decrement below 0 - ImageSoruce"
            );
        }
        this.uses--;
    }

    /**
     * Method returns a short summary in GameData syntax.
     * @return  Generated (pseudo) GameFileSyntax.
     */
    @Override
    public String toString() {
        return "ImageSource: { " + this.file.getPath() + "," + this.uses + " }";
    }

}
