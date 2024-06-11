package editor.model.map;

import java.io.File;
import java.util.ArrayList;

/**
 * Loads Maps from Files.
 */
public class MapLoader {

    /** main Gorup of the loaded map. */
    private Group main;

    /** all imageSources of the loaded map. */
    private ArrayList<ImageSource> imgSources;

    /**
     * @param file to load from
     */
    public MapLoader(final File file) {
        //TODO Load Repository
    }

    /**
     * @return the main group of the map
     */
    public Group getMain() {
        return main;
    }
    /**
     * @return all imageSources of the map
     */
    public ArrayList<ImageSource> getImgSources() {
        return imgSources;
    }

    /* Placeholder and possible implementation
    private static Group loadGroup(File mapFile)
    throws FileNotFoundException, IOException {
    }
    private static ArrayList<ImageSource> loadImageList(File mapFile)
    throws FileNotFoundException, IOException {
        ArrayList<ImageSource> imgSources = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(mapFile));
        boolean stop = false;
        reader.readLine();
        while (!stop && reader.ready()) {
            String line = reader.readLine().trim();
            if(line.startsWith("]")) stop = true;
            else{
                imgSources.add(new )
            }
        }
        reader.close();

        return imgSources;
    }*/
}
