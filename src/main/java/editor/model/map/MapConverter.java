package editor.model.map;

import java.io.File;

/**
 * Converts a Map of the editor to an Map of the Engine.
 */
public class MapConverter {

    /** File the Map should be converted to. */
    private File mapFile;

    /**
     * @param file the map should be converted to
     */
    MapConverter(final File file) {
        this.mapFile = file;
        //TODO export and convert for Engine Usage
    }

    /**
     * converts a map into the file.
     * @param map map that should be converted
     */
    public void convert(final Map map) {

    }
}
