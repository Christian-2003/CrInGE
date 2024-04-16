package game_editor.view.project_editor.MapEditor.model;

import java.io.*;
import java.util.ArrayList;

/**
 * TODO description
 * 
 * @author Tim Schnur
 */
public class MapRepository {
    
    private ArrayList<ImageSource> imgSources; // alle ImageSources

    private Group main; // Einstiegspunkt für Gruppenschachtelung

    public MapRepository(){
        this(new Group("main"), new ArrayList<>());
    }
    public MapRepository(ArrayList<ImageSource> imgSources){
        this(new Group("main"), imgSources);
    }
    public MapRepository(Group main, ArrayList<ImageSource> imgSources){
        this.main = main;
        this.imgSources = imgSources;
    }
    public MapRepository(String mapPath) throws FileNotFoundException, IOException{
        this(new File(mapPath));
    }
    public MapRepository(File mapFile) throws FileNotFoundException, IOException{
        this(new RepositoryLoader(mapFile));
    }
    private MapRepository(RepositoryLoader loader) throws FileNotFoundException, IOException{
        this(loader.getMain(), loader.getImgSources());
    }

    public Group getMain(){
        return this.main;
    }
    public ArrayList<ImageSource> getImgSources() {
        return imgSources;
    }

    
    public void save(File file){}   //TODO save Repository
    public void convert(File file){} //TODO export and convert for Engine Usage via Converter
}
