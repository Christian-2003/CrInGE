package game_editor.model;

import java.io.*;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

/**
 * Represents the Editor.config File at Runtime
 * 
 * @author Tim Schnur
 */
public class EditorConfig {

    /**
     * List of all possible Attributes of the Editor.config
     */
    public enum attributes {
        recentlyOpened,
        knownProjects;
    }

    private static final String configLocation = "Editor.config";

    private static String recentlyOpened = "";

    private static ArrayList<String> knownProjects = new ArrayList<>();

    /**
     * get the value of an specific attribute
     * 
     * @param attribute to be get {@link attributes}
     * @return Object representing the value of the attribute
     */
    public static Object get(attributes attribute) {
        switch (attribute) {
            case recentlyOpened: return recentlyOpened;
            case knownProjects: return knownProjects;
            default: throw new NoSuchFieldError(attribute.toString());
        }
    }

    /**
     * sets an attribute to an new value
     * 
     * @param attribute to be set {@link attributes}
     * @param value that will be set
     * @throws DataFormatException if the value does not match the expected format
     */
    public static void set(attributes attribute, Object value) throws DataFormatException {
        try {
            switch (attribute) {
                case recentlyOpened: {
                    recentlyOpened = (String)value;
                    break;
                }
                default: throw new NoSuchFieldError(attribute.toString());
            }
        } catch (ClassCastException ex) {throw new DataFormatException("The Attribute "+attribute+" can not be set to "+value.getClass());}
    }

    public static void addKnownProject(String projectLocation) {
        boolean exists = false;
        
        for (String project : knownProjects) {
            if (project==projectLocation) exists = true;
        }

        if (!exists) knownProjects.add(projectLocation);
    }
    public static void removeKnownProject(String projectLocation) {
        knownProjects.remove(projectLocation);
    }
    
    /**
     * loads the state from the Editor.config File
     * 
     * @throws IOException if the loading fails somehow
     */
    public static void load() throws IOException {
        File configFile = new File(configLocation);

        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            while (reader.ready()) {
                String[] line = reader.readLine().split(":");
                if (line.length > 1) {
                    if (line[0].equals(attributes.knownProjects.name())) {
                        for (String project : line[1].split(",")) {
                            addKnownProject(project);
                        }
                    }
                    else {
                        set(attributes.valueOf(line[0]), line[1]);
                    }
                }
            }
        } catch (FileNotFoundException ex) {throw new IOException("Config File Not Found");}
        catch (DataFormatException ex) {throw new IOException("Config File does not match the expected Format");}
    }

    /**
     * Saves the current state to the Editor.config File
     * 
     * @throws ExportException if the Export fails somehow
     */
    public static void save() throws ExportException{
        try (PrintWriter writer = new PrintWriter(new File(configLocation))) {
            writer.println(attributes.recentlyOpened+":"+recentlyOpened);
            writer.print(attributes.knownProjects+":");
            for (int i=0; i<knownProjects.size(); i++) {
                if (i!=0) writer.write(",");
                writer.write(knownProjects.get(i));
            }
            writer.println();
        } catch (Exception ex) {
            throw new ExportException(ex.getMessage());
        }
    }
}
