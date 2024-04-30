package game_editor.model;

import java.io.*;
import java.util.ArrayList;

public class RepositoryLoader {

    private Group main;

    private ArrayList<ImageSource> imgSources;

    public RepositoryLoader(File mapFile){
        //TODO Load Repository
    }

    public Group getMain() {
        return main;
    }
    public ArrayList<ImageSource> getImgSources() {
        return imgSources;
    }

    /*private static Group loadGroup(File mapFile) throws FileNotFoundException, IOException{
    }
    private static ArrayList<ImageSource> loadImageList(File mapFile) throws FileNotFoundException, IOException{
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
