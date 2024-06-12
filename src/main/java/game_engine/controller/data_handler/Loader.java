/**
 * Package contains all files deemed necessary for game data handling.
 */
package game_engine.controller.data_handler;

import game_engine.controller.EntityManager;
import game_engine.controller.data_handler.exceptions.GameDataFileSyntaxException;
import game_engine.controller.data_handler.exceptions.NotFoundException;
import game_engine.controller.data_handler.game_data_format.Group;
import game_engine.model.entities.Entity;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Every prop has to be loaded, this class job is to load each prop from a file
 * and add them into the runtime, as well as to save them into a file.
 * This class is also responsible for loading and saving the map.
 *
 * @version 1.0
 * @author Fabian001
 */
public class Loader {
    /**
     * Field stores the image sources.
     * Key = FilePath: String
     */
    private static final HashMap<Integer, ImageIcon> IMGSOURCE
            = new HashMap<>();
    /**
     * Field stores the entities.
     * Key = Asset-Index
     */
    private static final HashMap<Integer, Entity> ENTITY = new HashMap<>();

    /**
     * Field stores the groups.
     * Key = Group-Index
     */
    private static final HashMap<Integer, Group> GROUPS = new HashMap<>();

    /**
     * Attribute stores the ID counter for assets.
     */
    private static int idAssetCounter = 0;
    /**
     * Attribute stores the ID counter for groups.
     * Index 0 = main
     */
    private static int idGroupCounter = 1;
    /**
     * Input of first Line (where Img-Array is present).
     */
    private static String sourceInput = "";

    /**
     * GETTER for ImgIconSources as Array.
     * @return IMGSOURCE[]
     */
    public ImageIcon[] getImgSources() {
        return IMGSOURCE.values().toArray(new ImageIcon[0]);
    }

    /**
     * GETTER for ImgIconSources.
     * @return IMGSOURCE
     */
    public HashMap<Integer, ImageIcon> getImgIconSources() {
        return IMGSOURCE;
    }

    /**
     * GETTER for Entity.
     * @return ENTITY
     */
    public HashMap<Integer, Entity> getEntity() {
        return ENTITY;
    }

    /**
     * Returns a HashMap of the groups found inside the .gameData file.
     * Only required in the needs of encoding Entity's back to Syntax.
     * @return HashMap<Integer, Group> | ID, Group
     */
    public HashMap<Integer, Group> getGroups() {
        return GROUPS;
    }

    /**
     * Runs the Demo decode Script for CreeperExample.gameData.
     * ...to loaded Entity's.
     * @throws NotFoundException
     * @throws GameDataFileSyntaxException
     */
    public Loader() throws NotFoundException, GameDataFileSyntaxException {
        decodeToVariable(
            Paths.get("src", "main",
                "java", "game_engine", "controller", "data_Handler",
                "savefiles", "creeperExample.gamedata")
                .toAbsolutePath());
    }

    /**
     * Runs a specified .gameData Syntax File to load Entity's.
     * @param path
     * @throws NotFoundException
     * @throws GameDataFileSyntaxException
     */
    public Loader(final Path path)
            throws NotFoundException, GameDataFileSyntaxException {
        if (!path.toString().toLowerCase().endsWith(".gamedata")) {
            throw new GameDataFileSyntaxException(
                "False File-Extension\nExpected:'.gameData' !"
                );
        }
        decodeToVariable(path.toAbsolutePath());
    }

    /**
     * @hidden Only testing!!!.
     * @param args Standard-Main Args
     */
    public static void main(final String[] args)
            throws NotFoundException,
            GameDataFileSyntaxException {
        decodeToVariable(
                Paths.get(".", "src", "main",
                        "java", "game_engine", "controller",
                        "data_Handler", "savefiles",
                                "fileTypeExampe.gamedata")
                        .toAbsolutePath());
    }

    /**
     * Decodes .gameData Syntax to usable variables for the Engine.
     * @param filePath A File path.
     * @throws GameDataFileSyntaxException
     * Throws when the Syntax isn't adding up
     */
    public static void decodeToVariable(final Path filePath)
            throws GameDataFileSyntaxException,
            NotFoundException {
        // Validate if File exits as .gamedata
        File f = new File(filePath.toUri());
        if (!(f.exists() && !f.isDirectory())) {
            throw new NotFoundException("No file found on " + f.getPath());
        }

        // Start Gruppe | Alle Gruppen sind mit main verbunden
        Group main = new Group("main", 0);
        GROUPS.put(0, main);

        StringBuilder input = new StringBuilder();
        boolean firstLine = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            for (String line; (line = reader.readLine()) != null;) {
                // ImageSource Data is places in line 1
                if (firstLine) {
                    sourceInput = line;
                }
                firstLine = false;
                // The rest is placed below line 1
                input.append(line);
                input.append("\r\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Extracts each Path from the first Line of the GameDataFile
        // Checks if the File exists and if so
        // creates a new ImageSource object
        ArrayList<String> pathArrayList = FileCoder.extractPaths(sourceInput);
        int i = 0;
        for (String path : pathArrayList) {
            // ? All assets should be in the texture folder if
            // not an absolute path is required
            if (path.startsWith("/")) {
                path = "src/main/resources/textures" + path;
            }
            File imageFile = new File(path);
            if (!imageFile.exists()) {
                throw new GameDataFileSyntaxException(
                        "File Path does not exit"
                );
            }
            IMGSOURCE.put(i, new ImageIcon(imageFile.getAbsolutePath()));
            i++;
        }
        // This pattern captures each <group> including subgroups
        String groupPattern =
                "\\s{4,8}\\[([^\\[\\]]*?(\\[.*?)?"
                        + "[^\\[\\]]*?)\\s*;[^\\[\\]]*(?=;|$)";
        Pattern pattern = Pattern.compile(groupPattern);
        Matcher matcher = pattern.matcher(input);
        // While a group has been found
        while (matcher.find()) {
            // Extracting the content of each <group>
            String groupContent = matcher.group(0);
            // Get its group name
            String groupName = matcher.group(1);
            Group currentGroup = new Group(groupName, idGroupCounter);
            // Get each params of an Asset.
            String[] assetArray = groupContent
                    .replace("[" + groupName + ";", "")
                    .trim()
                    .split("\n");
            // Iterate through each asset.
            for (String assetRaw : assetArray) {
                if (assetRaw.contains(",")) {
                    System.out.println(
                            "Syntax-Warning:"
                                    + " Comma found where "
                                    + "a semicolom should be.\n"
                                    + " -> (" + f.getName()
                            + ":" + ((idGroupCounter
                            + idAssetCounter) + 3) + ") "
                            + f.getPath() + ":"
                            + ((idGroupCounter + idAssetCounter) + 3));
                    assetRaw = assetRaw.replaceAll(",", ";");
                }
                String[] val = Arrays
                        .stream(
                                assetRaw.split(";"))
                        .map(s -> s.replace("}", ""))
                        .toArray(String[]::new);
                Entity newEntity = getEntity(val);
                System.out.println("Entity: "
                        + val[0]
                        .replace("{", "")
                        .trim() + " created");

                // ? Adds asset to HashMap
                ENTITY.put(idAssetCounter, newEntity);
                EntityManager.getInstance().put(newEntity);
                // ? Adds the Asset ID inside the current groups AssetID List
                currentGroup.pushAssetID(idAssetCounter);
                // ? Increases the asset ID counter by 1
                idAssetCounter++;
            }
            /*
             * ? A subgroup has 8 whitespaces while a regular group only has 4
             * Look in fileTypeExampe.gamedata for more infos
             * If it's a subgroup add their represented parents and
             * children to the desired group
             * If it's a group add it to the main group
             */
            if (groupContent.startsWith("        ")) {
                Group lastGroup = getLastInsertedGroup();
                Integer lastGroupID = lastGroup.getId();
                currentGroup.setParentGroupID(lastGroupID);
                lastGroup.addChildGroupID(idGroupCounter);
                GROUPS.put(lastGroupID, lastGroup);
            } else {
                currentGroup.setParentGroupID(0);
                main.addChildGroupID(idGroupCounter);
            }
            GROUPS.put(idGroupCounter, currentGroup);
            idGroupCounter++;
        }
    }

    private static Entity getEntity(final String[] val)
            throws GameDataFileSyntaxException {
        if (val.length < 10) {
            String cache = Arrays.toString(val);
            if (cache.contains("[{")
                    && cache.endsWith("]")
                    && !cache.contains(",")) {
                throw new GameDataFileSyntaxException(
                        "Can't comprehend between Entity or Group |"
                                + " Check if a Semicolon is missing!\n"
                                + "Value: " + cache);
            } else {
                throw new GameDataFileSyntaxException(
                        "Entity Size is to low\n"
                                + "Expected: 10\n"
                                + "Has: " + val.length + "\n"
                                + "Value: " + cache);
            }
        }
        // ! Entity (Entity-Name/UUID | Texture-Index | visible |
        // tangible | width-size | height-size |
        // width-hitbox | height-hitbox |
        // x:double | y:double).
        Dimension size = new Dimension(Integer.parseInt(val[4]),
                Integer.parseInt(val[5]));
        Dimension hitbox = new Dimension(Integer.parseInt(val[6]),
                Integer.parseInt(val[7]));
        Entity newEntity = new Entity(parseBool(val[2]),
                parseBool(val[3]),
                size,
                hitbox,
                Double.parseDouble(val[8]),
                Double.parseDouble(val[9]));
        // ? Textures need to be set separately.
        newEntity.setTexture(Integer.parseInt(val[1]));
        // ? Debug-Name is used to identify an entity for debug purposes.
        newEntity.setDebugName(val[0].replace("{", "").trim());
        return newEntity;
    }

    /**
     * Will get the last element from HashMap
     * groups or return null.
     * @unsafe No trows
     * @return last.Group | null
     */
    private static Group getLastInsertedGroup() {
        return GROUPS
                .values()
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }

    private static Boolean parseBool(final String val) {
        return Objects.equals(val, "t");
    }

}
