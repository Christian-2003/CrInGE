package editor.model.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents the whole Map.
 * @author Tim Schnur
 */
public class Map {

    /** all imageSources of this map. */
    private ArrayList<ImageSource> imgSources;

    /** entrypoint for the group tree. */
    private Group main;

    /** creates a new Map Object with no content. */
    public Map() {
        this(new Group("main"), new ArrayList<>());
    }
    /**
     * @param imgSources of the Map
     */
    public Map(final ArrayList<ImageSource> imgSources) {
        this(new Group("main"), imgSources);
    }
    /**
     * @param main group of the map
     * @param imgSources of the map
     */
    public Map(final Group main, final ArrayList<ImageSource> imgSources) {
        this.main = main;
        this.imgSources = imgSources;
    }
    /**
     * @param path of the map
     * @throws FileNotFoundException if file could not be found
     * @throws IOException if an IO Error occures
     */
    public Map(final String path) throws FileNotFoundException, IOException {
        this(new File(path));
    }
    /**
     * @param file of the map
     * @throws FileNotFoundException if file could not be found
     * @throws IOException if an IO Error occures
     */
    public Map(final File file) throws FileNotFoundException, IOException {
        this(new MapLoader(file));
    }
    /**
     * @param loader of the map
     * @throws FileNotFoundException if file could not be found
     * @throws IOException if an IO Error occures
     */
    private Map(final MapLoader loader)
    throws FileNotFoundException, IOException {
        this(loader.getMain(), loader.getImgSources());
    }

    /**
     * @return the main Group of the map
     */
    public Group getMain() {
        return this.main;
    }
    /**
     * @return list of all ImageSources of the map
     */
    public ArrayList<ImageSource> getImgSources() {
        return imgSources;
    }

    /**
     * saves the map.
     * @param path to save to
     */
    public void save(final String path) {
        save(new File(path));
    }
    /**
     * saves the map.
     * @param file to save to
     */
    public void save(final File file) {
        save(new MapSaver(file));
    }
    /**
     * saves the map.
     * @param saver to save with
     */
    public void save(final MapSaver saver) {
        saver.save(this);
    }

    /**
     * converts the map.
     * @param path to convert to
     */
    public void convert(final String path) {
        convert(new File(path));
    }
    /**
     * converts the map.
     * @param file to convert to
     */
    public void convert(final File file) {
        convert(new MapConverter(file));
    }
    /**
     * converts the map.
     * @param converter to convert with
     */
    public void convert(final MapConverter converter) {
        converter.convert(this);
    }
}
