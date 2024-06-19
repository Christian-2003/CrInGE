package editor.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

/**
 * Represents the Editor.config File at Runtime.
 * @author Tim Schnur
 */
public final class EditorConfig {

    private EditorConfig() { }

    /**
     * List of all possible Attributes of the Editor.config.
     */
    public enum Attributes {
        /** recently opened projects. */
        recentlyOpened,
        /** known projects on this computer. */
        knownProjects;
    }

    /** path of the config File. */
    private static final String CONFIG_LOCATION = "Editor.config";

    /** recently opened projects. */
    private static String recentlyOpened = "";

    /** known projects on this computer. */
    private static ArrayList<String> knownProjects = new ArrayList<>();

    /**
     * get the value of an specific attribute.
     * @param attribute to be get {@link Attributes}
     * @return Object representing the value of the attribute
     */
    public static Object get(final Attributes attribute) {
        switch (attribute) {
            case recentlyOpened: return recentlyOpened;
            case knownProjects: return knownProjects;
            default: throw new NoSuchFieldError(attribute.toString());
        }
    }

    /**
     * sets an attribute to an new value.
     * @param attribute to be set {@link Attributes}
     * @param value that will be set
     * @throws DataFormatException
     * if the value does not match the expected format
     */
    public static void set(final Attributes attribute, final Object value)
        throws DataFormatException {
        try {
            switch (attribute) {
                case recentlyOpened:
                    recentlyOpened = (String) value;
                    break;
                default: throw new NoSuchFieldError(attribute.toString());
            }
        } catch (ClassCastException ex) {
            throw new DataFormatException("The Attribute "
            + attribute + " can not be set to " + value.getClass());
        }
    }

    /**
     * adds an project to the list of known ones.
     * @param projectLocation path to the project
     */
    public static void addKnownProject(final String projectLocation) {
        boolean exists = false;

        for (String project : knownProjects) {
            if (project == projectLocation) {
                exists = true;
            }
        }

        if (!exists) {
            knownProjects.add(projectLocation);
        }
    }
    /**
     * removes an project to the list of known ones.
     * @param projectLocation path to the project
     */
    public static void removeKnownProject(final String projectLocation) {
        knownProjects.remove(projectLocation);
    }

    /**
     * loads the state from the Editor.config File.
     * @throws IOException if the loading fails somehow
     */
    public static void load() throws IOException {
        File configFile = new File(CONFIG_LOCATION);

        try (BufferedReader reader = new BufferedReader(
            new FileReader(configFile))) {
            while (reader.ready()) {
                String[] line = reader.readLine().split(":");
                if (line.length > 1) {
                    if (line[0].equals(Attributes.knownProjects.name())) {
                        line[1] = line[1].substring(1, line[1].length()-1);
                        for (String project : line[1].split(",")) {
                            addKnownProject(project);
                        }
                    } else {
                        set(Attributes.valueOf(line[0]), line[1]);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            throw new IOException("Config File Not Found");
        } catch (DataFormatException ex) {
            throw new IOException(
            "Config File does not match the expected Format");
        }
    }

    /**
     * Saves the current state to the Editor.config File.
     * @throws ExportException if the Export fails somehow
     */
    public static void save() throws ExportException {
        try (PrintWriter writer = new PrintWriter(new File(CONFIG_LOCATION))) {
            writer.println(Attributes.recentlyOpened + ":" + recentlyOpened);
            writer.print(Attributes.knownProjects + ":");
            for (int i = 0; i < knownProjects.size(); i++) {
                if (i != 0) {
                    writer.write(",");
                }
                writer.write(knownProjects.get(i));
            }
            writer.println();
        } catch (Exception ex) {
            throw new ExportException(ex.getMessage());
        }
    }
}
