package game_engine.controller.game_datahandler.game_dataformat;

import game_engine.controller.game_datahandler.Exceptions.NotFoundException;
import game_engine.controller.utility.Utils;

import java.util.ArrayList;


/**
 * Class implements a Group of Assets
 * @see Asset
 * @author Farbian001
 */
public class Group {
    private String name;
    private Integer id;
    private Integer parentGroupId;
    private ArrayList<Integer> childGroupIds;
    private ArrayList<Integer> assetsIDs;

    public Group(String name, Integer parentGroupId, ArrayList<Integer> childGroupIds, ArrayList<Integer> assetsIDs) {
        this.name = name;
        this.id = new Utils().generateNewRandomID();
        this.parentGroupId = parentGroupId;
        this.childGroupIds = childGroupIds;
        this.assetsIDs = assetsIDs;
    }

    public Group(String name, int id){
        this.name = name;
        this.id = id;
        this.parentGroupId = null;
        this.childGroupIds = new ArrayList<>();
        this.assetsIDs = new ArrayList<>();
    }


    /**
     * Returns current group name
     * @return this.Group.name
     */
    public String getGroupName() {
        return this.name;
    }

    /**
     * Changes the current group name to param
     * @param new_name String: No whitespaces or symbols; Must begin with alphabetic
     */
    public void setGroupName(String new_name){
        this.name = new_name;
    }

    /**
     * Get the ID of this Group
     * @return this.Group.ID
     */
    public Integer getId(){
        return this.id;
    }

    /**
     * Get the ID of the Parent(group) from this Group
     * @return Group-ID
     */
    public Integer getParentGroupID() {
        return parentGroupId;
    }

    /**
     * Changes the Parent Group by it's ID
     * @param parentGroupId
     * @return this.Group
     */
    public Group setParentGroupID(Integer parentGroupId) {
        this.parentGroupId = parentGroupId;
        return this;
    }

    /**
     * Returns all IDs of Groups that are nested under the current group
     * @return Integer-GroupIDs
     */
    public ArrayList<Integer> getChildGroupIDs() {
        return childGroupIds;
    }

    /**
     * Overrides current ChildGroupID Hashmap to a new one
     * @param childGroupIds
     * @return this.Group
     * @see Group
     */
    public Group setChildGroupIDs(ArrayList<Integer> childGroupIds) {
        this.childGroupIds = childGroupIds;
        return this;
    }

    /**
     * Returns all Assets-IDs that are nested under this group
     * @return Asset-IDs
     */
    public ArrayList<Integer> getAssetsIDs() {
        return assetsIDs;
    }

    /**
     * Adds an assets via ID, to this group
     * @param id
     */
    public void pushAssetID(int id){
        this.assetsIDs.add(id);
    }

    /**
     * Removes an asset via asset ID from this group
     * @param id Asset.ID
     */
    public void removeAssetID(int id) throws NotFoundException {
        if (assetsIDs.contains(id)) {
            assetsIDs.remove(Integer.valueOf(id));
        } else {
            throw new NotFoundException("Asset-ID" + id + " not found in the Asset-List for Group " + this.name);
        }
    }

    /**
     * Adds a below nested group via ID to this group
     * @param id Group.ID
     * @unsafe Duplicates are possible
     */
    public void addChildGroupID(int id){
        this.childGroupIds.add(id);
    }

    /**
     * Removes a Child Group by its ID
     * @param id | Child Group.ID
     */
    public void removeChildGroupID(int id) throws NotFoundException {
        if (childGroupIds.contains(id)) {
            childGroupIds.remove(Integer.valueOf(id));
        } else {
            throw new NotFoundException("ID " + id + " not found in the list.");
        }
    }

    /**
     * Returns a String in GameData Syntax, with minimum information
     * @return String | (pseudo) GameFileSyntax
     */
    @Override
    public String toString(){
        return "[" + this.name + ";"+this.getAssetsIDs() +";";
    }

    /**
     * Same as toString(), just with more usefully information about this group
     * @return String | (pseudo-dev) GameFileSyntax
     */
    public String toString_dev(){
        return "[" + this.name + "; (Parent:"+ this.parentGroupId +")\n\tAssets:{" + this.getAssetsIDs() + "}\n\tSubGroups:{"+ this.getChildGroupIDs() +"}" + "\n];";
    }
}
