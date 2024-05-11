package game_engine.controller.game_dataHandler;

import game_engine.controller.game_dataHandler.Exceptions.GameDataFileSyntaxException;
import game_engine.controller.game_dataHandler.game_dataFormat.Group;
import game_engine.model.entities.Entity;

import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * A Decoder and Encoder extracting and transforming game-data.
 * Game-Data File(s) are saved in a unique minimum head datastructures and can't be loaded without proper decoding.
 * @see Map
 * @see game_engine.controller.game_dataHandler
 * @version 1.0
 * @author Fabian001
 */
public class FileCoder {


    public FileCoder(){
        System.out.println("Created - FileCoder");
    }


    /**
     * Extracts each Path from the first Line of the GameDataFile
     * @param input | GameData Syntax
     * @return ArrayList<String> paths
     * @throws GameDataFileSyntaxException If param doesn't start with "sources: [.....]"
     */
    static ArrayList<String> extractPaths(String input) throws GameDataFileSyntaxException {
        Pattern pattern2 = Pattern.compile("sources:\\[([^;\\]]+(?:;[^;\\]]+)*)]");

        Matcher matcher2 = pattern2.matcher(input);
        if (!matcher2.find()) {
            throw new GameDataFileSyntaxException(
                    "An Error encountered with the GameDataFile Syntax: No 'sources:' found");
        }
        String pathsString = matcher2.group(1);

        if(pathsString.contains(",")) {
            System.out.println("⚠️ Syntax-Warning-ImageSource: Comma found where a semicolon should be!");
            pathsString = pathsString.replaceAll(",",";");
        }
        String[] pathArray = pathsString.split(";");

        return new ArrayList<>(Arrays.asList(pathArray));
    }

    /**
     * Encodes all groups and assets to GameDataSyntax String
     * @param groups | Includes all groups starting with main
     * @param entity | Includes all entity
     * @return String | (correct) GameDataSyntax
     * @throws GameDataFileSyntaxException | If a group couldn't be found with its groupID
     * @todo-Fabian Custom-Assets werden noch nicht geladen!!!
     */
    public static String encode_assets(HashMap<Integer, Group> groups, HashMap<Integer, Entity> entity) throws GameDataFileSyntaxException {
        StringBuilder stBD = new StringBuilder();
        System.out.println("Start -- Encode");
        Group main = groups.get(0);
        if(main == null) throw new GameDataFileSyntaxException("No main-group detected!!! - Encoding failed");
        stBD.append("[main;").append(System.lineSeparator());
        for(Integer curr_subG : main.getChildGroupIDs()) {
            StringBuilder spaces = new StringBuilder().append("        ");
            Group curr_group = groups.get(curr_subG);
            if(curr_group == null) throw new GameDataFileSyntaxException("No group found with ID: " + curr_subG + "!!! - Encoding failed");
            System.out.println("Now: " + curr_group.getGroupName() + " | spaces: " + spaces.length());
            stBD.append("    ").append(getAssetviaGroup(curr_group, entity ,groups,spaces)).append(System.lineSeparator()).append("    ").append("]").append(System.lineSeparator());
        }

        stBD.append("]");
        return stBD.toString();
    }

    /**
     * Recursive function for loading assets from the cur_group and repeat the process if the group has subgroups nested
     * Returns String of GameDataSyntax
     * @param cur_group | Group | Group that will be checked for Assets and Subgroups
     * @param entity | HashMap<Entity> | Holds all entity's
     * @param groups | Hashmap<Group>  | Holds all groups
     * @param spaces | StringBuilder   | To ensure correct whitespace placement
     * @return String | (correct) GameDataSyntax
     */
    public static String getAssetviaGroup(Group cur_group, HashMap<Integer, Entity> entity, HashMap<Integer, Group> groups, StringBuilder spaces) {
        StringBuilder stB = new StringBuilder();
        // Create a group Syntax Header
        stB.append("[").append(cur_group.getGroupName()).append(";").append(System.lineSeparator());
        // Loop - Through all Assets
        for(Integer curr_asset_id : cur_group.getAssetsIDs()){
            // Create an Asset and append it to current StringBuilder
            Entity curr_Asset = entity.get(curr_asset_id);
            stB.append(spaces).append(curr_Asset.toString());
            if(curr_asset_id + 1 < cur_group.getAssetsIDs().size()){
                stB.append(System.lineSeparator());
            }
        }
        StringBuilder spiral = new StringBuilder(spaces);
        // Loop - Through all SubGroups
        for(Integer curr_Group_id: cur_group.getChildGroupIDs()){
            // Create a new current Group and rerun the function with this group
            Group curr_childGroup = groups.get(curr_Group_id);
            System.out.println("Working: Subgroup: " + curr_childGroup.getGroupName() + "| spaces: " + spaces.length());

            stB.append(System.lineSeparator()).append(spaces).append(getAssetviaGroup(curr_childGroup, entity, groups,spiral.append("    "))).append(System.lineSeparator()).append(spaces).append("]");

        }
        // Group -> [Assets]:[GroupIDs] - > Asset
        return stB.toString();
    }
    public static String encode_ImageIcons(HashMap<Integer, ImageIcon> icons) {
        return "sources:[" + icons.values().stream()
                .map(imageIcon -> {
                    String path = imageIcon.getDescription();
                    int textureIndex = path.indexOf("\\textures\\");
                    if (textureIndex != -1) {
                        path = path.substring(textureIndex + "\\textures\\".length()).replace("\\", "/");
                        if (!path.startsWith("/")) {
                            path = "/" + path;
                        }
                        return path;
                    } else {
                        return path; // If "\textures\" is not found, return the original path
                    }
                })
                .collect(Collectors.joining(";")) + "]";
    }


    public static String encode_ImageIcons(ImageIcon[] icons){

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



