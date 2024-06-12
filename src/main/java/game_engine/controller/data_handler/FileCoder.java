package game_engine.controller.data_handler;

import game_engine.controller.data_handler.exceptions.GameDataFileSyntaxException;
import game_engine.controller.data_handler.game_data_format.Group;
import game_engine.model.entities.Entity;
import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * A Decoder and Encoder extracting and transforming game-data.
 * Game-Data File(s) are saved in a unique minimum head datastructures and can't be loaded without proper decoding.
 *
 * @version 1.0
 * @author  Fabian001
 */
public class FileCoder {

    /**
     * Constructor instantiates a new file coder.
     */
    public FileCoder() {
        System.out.println("Created - FileCoder");
    }


    /**
     * Method extracts each path from the first line of the GameDataFile.
     *
     * @param input                         GameData Syntax.
     * @return                              Extracted paths
     * @throws GameDataFileSyntaxException  The passed argument does not start with "sources: [.....]".
     */
    static ArrayList<String> extractPaths(String input) throws GameDataFileSyntaxException {
        Pattern pattern2 = Pattern.compile("sources:\\[([^;\\]]+(?:;[^;\\]]+)*)]");

        Matcher matcher2 = pattern2.matcher(input);
        if (!matcher2.find()) {
            throw new GameDataFileSyntaxException("An Error encountered with the GameDataFile Syntax: No 'sources:' found");
        }
        String pathsString = matcher2.group(1);

        if (pathsString.contains(",")) {
            System.out.println("Syntax-Warning-ImageSource: Comma found where a semicolon should be!");
            pathsString = pathsString.replaceAll(",",";");
        }
        String[] pathArray = pathsString.split(";");

        return new ArrayList<>(Arrays.asList(pathArray));
    }

    /**
     * Encodes all groups and assets to GameDataSyntax String.
     *
     * @param groups                        Includes all groups starting with main.
     * @param entity                        Includes all entities.
     * @return                              Correct GameDataSyntax.
     * @throws GameDataFileSyntaxException  If a group couldn't be found with its groupID.
     * @TODO-Fabian                         Custom-Assets werden noch nicht geladen!!!
     */
    public static String encodeAssets(HashMap<Integer, Group> groups, HashMap<Integer, Entity> entity) throws GameDataFileSyntaxException {
        StringBuilder builder = new StringBuilder();
        System.out.println("Start -- Encode");
        Group main = groups.get(0);
        if (main == null) {
            throw new GameDataFileSyntaxException("No main-group detected!!! - Encoding failed");
        }
        builder.append("[main;");
        builder.append(System.lineSeparator());
        for(Integer currentChildGroup : main.getChildGroupIDs()) {
            StringBuilder spaces = new StringBuilder().append("        ");
            Group currentGroup = groups.get(currentChildGroup);
            if (currentGroup == null) {
                throw new GameDataFileSyntaxException("No group found with ID: " + currentChildGroup + "!!! - Encoding failed");
            }
            System.out.println("Now: " + currentGroup.getGroupName() + " | spaces: " + spaces.length());
            builder.append("    ");
            builder.append(getAssetViaGroup(currentGroup, entity, groups, spaces));
            builder.append(System.lineSeparator());
            builder.append("    ");
            builder.append("]");
            builder.append(System.lineSeparator());
        }

        builder.append("]");
        return builder.toString();
    }

    /**
     * Recursive function for loading assets from the currentGroup and repeat the process if the group has subgroups nested.
     * Returns String of GameDataSyntax.
     *
     * @param currentGroup  Group that will be checked for Assets and Subgroups
     * @param entity        Holds all entity's
     * @param groups        Holds all groups
     * @param spaces        To ensure correct whitespace placement
     * @return              Correct GameDataSyntax
     */
    public static String getAssetViaGroup(Group currentGroup, HashMap<Integer, Entity> entity, HashMap<Integer, Group> groups, StringBuilder spaces) {
        StringBuilder builder = new StringBuilder();

        // Create a group Syntax Header
        builder.append("[");
        builder.append(currentGroup.getGroupName());
        builder.append(";");
        builder.append(System.lineSeparator());

        // Loop - Through all Assets
        for (Integer currentAssetID : currentGroup.getAssetsIDs()) {
            // Create an Asset and append it to current StringBuilder
            Entity currentAsset = entity.get(currentAssetID);
            builder.append(spaces).append(currentAsset.toString());
            if (currentAssetID + 1 < currentGroup.getAssetsIDs().size()) {
                builder.append(System.lineSeparator());
            }
        }
        StringBuilder spiral = new StringBuilder(spaces);

        // Loop - Through all SubGroups
        for (Integer currentGroupID: currentGroup.getChildGroupIDs()) {
            // Create a new current Group and rerun the function with this group
            Group currentChildGroup = groups.get(currentGroupID);
            System.out.println("Working: Subgroup: " + currentChildGroup.getGroupName() + "| spaces: " + spaces.length());

            builder.append(System.lineSeparator());
            builder.append(spaces);
            builder.append(getAssetViaGroup(currentChildGroup, entity, groups, spiral.append("    ")));
            builder.append(System.lineSeparator());
            builder.append(spaces);
            builder.append("]");
        }
        // Group -> [Assets]:[GroupIDs] - > Asset
        return builder.toString();
    }


    /**
     * Method encodes the passed image icons.
     *
     * @param icons Hashmap of icons to encode.
     * @return      Encoded image icons.
     */
    public static String encodeImageIcons(HashMap<Integer, ImageIcon> icons) {
        return "sources:[" + icons.values().stream().map(imageIcon -> {
                    String path = imageIcon.getDescription();
                    int textureIndex = path.indexOf("\\textures\\");
                    if (textureIndex != -1) {
                        path = path.substring(textureIndex + "\\textures\\".length()).replace("\\", "/");
                        if (!path.startsWith("/")) {
                            path = "/" + path;
                        }
                        return path;
                    }
                    else {
                        return path; // If "\textures\" is not found, return the original path
                    }
                }).collect(Collectors.joining(";")) + "]";
    }


    /**
     * Method encodes the passed image icons.
     *
     * @param icons Array of icons to encode.
     * @return      Encoded image icons.
     */
    public static String encodeImageIcons(ImageIcon[] icons){
        StringBuilder result = new StringBuilder("sources:[");

        for (int i = 0; i < icons.length; i++) {
            String path = icons[i].getDescription();
            int textureIndex = path.indexOf("\\textures\\");

            if (textureIndex != -1) {
                path = "/" + path.substring(textureIndex + "\\textures\\".length()).replace("\\", "/");
            }

            if (i != 0) {
                result.append(";");
            }

            result.append(path);
        }

        result.append("]");

        return result.toString();
    }

}
