package game_engine.controller.game_datahandler;

import game_engine.controller.game_datahandler.Exceptions.GameDataFileSyntaxException;
import game_engine.controller.game_datahandler.game_dataformat.Asset;
import game_engine.controller.game_datahandler.game_dataformat.Group;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * A Decoder and Encoder extracting and transforming game-data.
 * Game-Data File(s) are saved in a unique minimum head datastructures and can't be loaded without proper decoding.
 * @see Map
 * @see game_engine.controller.game_datahandler.game_dataformat
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
        System.out.println(pathsString);
        String[] pathArray = pathsString.split(";");

        return new ArrayList<>(Arrays.asList(pathArray));
    }

    /**
     * Encodes all groups and assets to GameDataSyntax String
     * @param groups | Includes all groups starting with main
     * @param assets | Includes all assets
     * @return String | (correct) GameDataSyntax
     * @throws GameDataFileSyntaxException | If a group couldn't be found with its groupID
     * @todo-Fabian Custom-Assets werden noch nicht geladen!!!
     */
    public static String encode_assets(HashMap<Integer, Group> groups, HashMap<Integer, Asset> assets) throws GameDataFileSyntaxException {
        StringBuilder stBD = new StringBuilder();
        System.out.println("Start -- Encode");
        Group main = groups.get(0);
        if(main == null) throw new GameDataFileSyntaxException("No main-group detected!!! - Encoding failed");
        stBD.append("[main;\n");
        for(Integer curr_subG : main.getChildGroupIDs()) {
            StringBuilder spaces = new StringBuilder().append("        ");
            Group curr_group = groups.get(curr_subG);
            if(curr_group == null) throw new GameDataFileSyntaxException("No group found with ID: " + curr_subG + "!!! - Encoding failed");
            System.out.println("Now: " + curr_group.getGroupName() + "| spaces: " + spaces.length());
            stBD.append("    ").append(getAssetviaGroup(curr_group, assets,groups,spaces)).append("\n").append("    ").append("]").append(System.lineSeparator());
        }

        stBD.append("]");
        return stBD.toString();
    }

    /**
     * Recursive function for loading assets from the cur_group and repeat the process if the group has subgroups nested
     * Returns String of GameDataSyntax
     * @param cur_group | Group | Group that will be checked for Assets and Subgroups
     * @param assets | HashMap<Assets> | Holds all assets
     * @param groups | Hashmap<Group>  | Holds all groups
     * @param spaces | StringBuilder   | To ensure correct whitespace placement
     * @return String | (correct) GameDataSyntax
     */
    public static String getAssetviaGroup(Group cur_group, HashMap<Integer, Asset> assets, HashMap<Integer, Group> groups, StringBuilder spaces) {
        StringBuilder stB = new StringBuilder();
        // Create a group Syntax Header
        stB.append("[").append(cur_group.getGroupName()).append(";\n");
        // Loop - Through all Assets
        for(Integer curr_asset_id : cur_group.getAssetsIDs()){
            // Create an Asset and append it to current StringBuilder
            Asset curr_Asset = assets.get(curr_asset_id);
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

            stB.append(System.lineSeparator()).append(spaces).append(getAssetviaGroup(curr_childGroup, assets, groups,spiral.append("    "))).append(System.lineSeparator()).append(spaces).append("]");

        }
        // Group -> [Assets]:[GroupIDs] - > Asset
        return stB.toString();
    }
}



