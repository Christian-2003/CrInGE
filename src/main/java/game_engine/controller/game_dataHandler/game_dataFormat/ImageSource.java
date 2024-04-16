package game_engine.controller.game_datahandler.game_dataformat;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @see Asset
 * @author FabianDev001
 */
public class ImageSource {
    /**
     * File with associated file-path
     */
    private File file;
    /**
     * Counter on how many assets are using this source
     * @see Asset
     */
    private int uses = 1;

    public ImageSource(File file) {
        this.file = file;
    }

    /**
     * Returns this groups Image-File
     * @return File
     * @see File
     */
    public File getFile() {
        return file;
    }

    /**
     * Overrides the Image-File, using a new File
     * @param file | new File()
     * @return this.ImageSource
     * @throws FileNotFoundException If file does not exist
     */
    public ImageSource setFile(File file) throws FileNotFoundException {
        if(!file.exists()) throw new FileNotFoundException("Couldn't find file with file: " + file.getPath());
        this.file = file;
        return this;
    }

    /**
     * Overrides the Image-File, using a String Path
     * @param path | String-Path
     * @return this.ImageSource
     * @throws FileNotFoundException | If file-path does not exist
     */
    public ImageSource setFile(String path) throws FileNotFoundException {
        File newFile = new File(path);
        if(!newFile.exists()) throw new FileNotFoundException("Couldn't find file with path: " + path);
        this.file = newFile;
        return this;
    }

    /**
     * Returns how often this ImageSource is linked to an asset
     * @return int | How often (0 = not in use)
     */
    public int getUses() {
        return uses;
    }

    /**
     * Sets uses to a defined value
     * @param uses int
     * @return this.ImageSource
     * @throws IllegalArgumentException Parm is not allowed to be less than 0
     */
    public ImageSource setUses(int uses) {
        if(uses < 0) throw new IllegalArgumentException("Negative uses are not allowed");
        this.uses = uses;
        return this;
    }

    /**
     * Increments the uses counter for this ImageSource
     */
    public void increamentUses(){
        this.uses++;
    }

    /**
     * Decrement the uses counter for this ImageSource
     * @throws IndexOutOfBoundsException Throws when "uses" fall behind 0
     */
    public void decreamentUses(){
        if(this.uses == 0) {
            throw new IndexOutOfBoundsException("Can't decrement below 0 - ImageSoruce");
        }
        this.uses--;
    }

    /**
     * Returns a short summery in GameData Syntax, what's inside this ImageSource
     * @return String | (pseudo) GameFileSyntax
     */
    @Override public String toString(){
        return "ImageSource: { " + this.file.getPath() + "," + this.uses + " }";
    }
}
