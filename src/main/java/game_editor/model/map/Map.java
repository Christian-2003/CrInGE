package game_editor.model.map;

import java.io.*;
import java.util.ArrayList;

/**
 * This class represents the whole Map
 * 
 * @author Tim Schnur
 */
public class Map {
    
    private ArrayList<ImageSource> imgSources; // alle ImageSources

    private Group main; // Einstiegspunkt für Gruppenschachtelung

    public Map(){
        this(new Group("main"), new ArrayList<>());
    }
    public Map(ArrayList<ImageSource> imgSources){
        this(new Group("main"), imgSources);
    }
    public Map(Group main, ArrayList<ImageSource> imgSources){
        this.main = main;
        this.imgSources = imgSources;
    }
    public Map(String mapPath) throws FileNotFoundException, IOException{
        this(new File(mapPath));
    }
    public Map(File mapFile) throws FileNotFoundException, IOException{
        this(new MapLoader(mapFile));
    }
    private Map(MapLoader loader) throws FileNotFoundException, IOException{
        this(loader.getMain(), loader.getImgSources());
    }

    public Group getMain(){
        return this.main;
    }
    public ArrayList<ImageSource> getImgSources() {
        return imgSources;
    }

    public void save(String path) {
        save(new File(path));
    }
    public void save(File file) {
        save(new MapSaver(file));
    }
    public void save(MapSaver saver) {
        saver.save(this);
    }
    public void convert(String path) {
        convert(new File(path));
    }
    public void convert(File file) {
        convert(new MapConverter(file));
    }
    public void convert(MapConverter converter) {
        converter.convert(this);
    }
}
