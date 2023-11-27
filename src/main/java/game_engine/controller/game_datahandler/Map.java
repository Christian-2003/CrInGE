package game_engine.controller.game_datahandler;

import game_engine.controller.game_datahandler.Exceptions.GameDataFileSyntaxException;
import game_engine.controller.game_datahandler.Exceptions.NotFoundException;
import game_engine.controller.game_datahandler.game_dataformat.Asset;
import game_engine.controller.game_datahandler.game_dataformat.Group;
import game_engine.controller.game_datahandler.game_dataformat.ImageSource;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Every prop has to be loaded, this class job is to load each prop from a file and add them into the runtime, as well as to save them into a file.
 * This class is also responsible for loading and saving the map. 
 * @version 1.0
 * @author Fabian001
 */
public class Map {

    // Variables to safe the Assets
    private static HashMap<String, ImageSource> imgSources = new HashMap<>(); // Key = FilePath: String
    private static HashMap<Integer, Asset> assets = new HashMap<>(); // Key = Asset-Index
    private static HashMap<Integer, Group> groups = new HashMap<>(); // Key = Group-Index

    // IDs-Counter for Asset and Group
    private static int idAsset_c = 0;
    private static int idGroup_c = 1; // <-- Index 0 = main

    private static String sourceinput = "";

    public Map() throws NotFoundException, GameDataFileSyntaxException {
        decode_to_variable();
    }

    public static void main(String[] args) throws NotFoundException, GameDataFileSyntaxException {
         decode_to_variable();
    }


    /**
     *
     * @throws GameDataFileSyntaxException | Throws when the Syntax isn't adding up
     * @throws NotFoundException | Throws when the file couldn't be found
     */
    public static void decode_to_variable() throws GameDataFileSyntaxException, NotFoundException {

        // Start Gruppe | Alle Gruppen sind mit main verbunden
        Group main = new Group("main", 0);
        groups.put(0, main);

        // Get all data for .gamedata
        String fileName = "fileTypeExampe.gamedata";
        Path filePath = Paths.get(".","src","main","java","game_engine","controller","game_datahandler" ,fileName).toAbsolutePath();

        StringBuilder input = new StringBuilder();
        boolean firstLine = true;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            for (String line; (line = br.readLine()) != null; ) {
                // ImageSource Data is places in line 1
                if (firstLine) sourceinput = line;
                firstLine = false;
                // The rest is placed below line 1
                input.append(line).append(System.lineSeparator());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
         * Extracts each Path from the first Line of the GameDataFile | Checks if the
         * File exists and if so creates a new ImageSource object
         */
        ArrayList<String> pathArrayList = FileCoder.extractPaths(sourceinput);

        for (String path : pathArrayList) {
            File imageFile = new File(path);
            if (!imageFile.exists())
                throw new GameDataFileSyntaxException("File Path does not exit");
            imgSources.put(path, new ImageSource(imageFile));
        }

        // This pattern captures each <group> including subgroups
        String groupPattern = "\\s{4,8}\\[([^\\[\\]]*?(\\[.*?)?[^\\[\\]]*?)\\s*;[^\\[\\]]*(?=;|$)";

        Pattern pattern = Pattern.compile(groupPattern);
        Matcher matcher = pattern.matcher(input);

        // While a group has been found
        while (matcher.find()) {
            // Extracting the content of each <group>
            String groupContent = matcher.group(0);
            // Get its group name
            String groupName = matcher.group(1);
            Group curr_group = new Group(groupName, idGroup_c);

            // Get each params of an Asset
            String[] assetArray = groupContent.replace("[" + groupName + ";", "").trim().split("\n");

            // Iterate through each asset
            for (String assetRaw : assetArray) {
                String[] val = assetRaw.split(";");
                String path = pathArrayList.get(Integer.parseInt(val[3].replace("}", "").replace(";", "")));

                //! Asset (<Asset-ID>, <Asset-Name>, <X-Grid>, <Y-Grid>, <FILE>, <Custome:Block> )
                Asset new_asset = new Asset(idAsset_c, val[0].replace("{", "").trim(), Integer.parseInt(val[1]),
                        Integer.parseInt(val[2]), new File(path), null);

                //? Adds asset to HashMap
                assets.put(idAsset_c, new_asset);
                //? Adds the Asset ID inside the current groups AssetID List
                curr_group.pushAssetID(idAsset_c);
                //? Increases the asset ID counter by 1
                idAsset_c++;

            }

            /* ? A subgroup has 8 whitespaces while a regular group only has 4
             * Look in fileTypeExampe.gamedata for more infos
             * If it's a subgroup add their represented parents and children to the desired group
             * If it's a group add it to the main group
             */
            if (groupContent.startsWith("        ")) {
                Group lastGroup = getLastInsertedGroup();
                Integer last_group_id = lastGroup.getId();
                curr_group.setParentGroupID(last_group_id);
                lastGroup.addChildGroupID(idGroup_c);
                groups.put(last_group_id, lastGroup);
            } else {
                curr_group.setParentGroupID(0);
                main.addChildGroupID(idGroup_c);
            }
            groups.put(idGroup_c, curr_group);
            idGroup_c++;
        }

        // ? Currently, my TEST-CHECK
        System.out.println("-- Check: --");
        for (Integer key : assets.keySet()) {
            String asset = assets.get(key).toString();
            System.out.println("AssetID: " + key + ", Value: " + asset);
        }
        for (Integer key : groups.keySet()) {
            String group = groups.get(key).toString();
            System.out.println("GroupID: " + key + ", Value: " + group);
        }

        /*

        UNTIL here the data should be finished and ready to be used...

         */


        //? Test-2 Encoding Data
        System.out.println("-- Encode Format --");
        System.out.println(FileCoder.encode_assets(groups, assets));
        System.out.println("--> Add part to safe to disk");
    }


    /**
     * Will get the last element from HashMap groups or return null
     * @unsafe No trows
     * @return last.Group | null
     */
    private static Group getLastInsertedGroup() {
        return groups.values().stream().reduce((first, second) -> second).orElse(null);
    }

}


