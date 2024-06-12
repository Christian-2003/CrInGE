package game_engine.controller.data_handler.game_data_format;

import game_engine.controller.data_handler.exceptions.NotFoundException;
import game_engine.controller.utility.Utils;
import java.util.ArrayList;


/**
 * Class implements a Group of Entities.
 *
 * @see     game_engine.model.entities.Entity
 * @author  Farbian001
 */
public class Group {

    /**
     * Attribute stores the name of the group.
     */
    private String name;

    /**
     * Attribute stores the ID of the group.
     */
    private final Integer id;

    /**
     * Attribute stores the ID of the parent group.
     */
    private Integer parentGroupId;

    /**
     * Attribute stores the IDs of all child groups.
     */
    private ArrayList<Integer> childGroupIds;

    /**
     * Attribute stores the IDs of all assets within this group.
     */
    private final ArrayList<Integer> assetsIDs;


    /**
     * Constructor instantiates a new group with the passed arguments.
     *
     * @param name          Name for the group.
     * @param parentGroupId ID of the parent group.
     * @param childGroupIds List of IDs of all child groups.
     * @param assetsIDs     List of IDs of all assets within this group.
     */
    public Group(String name, Integer parentGroupId, ArrayList<Integer> childGroupIds, ArrayList<Integer> assetsIDs) {
        this.name = name;
        this.id = new Utils().generateNewRandomID();
        this.parentGroupId = parentGroupId;
        this.childGroupIds = childGroupIds;
        this.assetsIDs = assetsIDs;
    }

    /**
     * Constructor instantiates a new group with the passed name and ID.
     *
     * @param name  Name for the group.
     * @param id    ID for the group.
     */
    public Group(String name, int id){
        this.name = name;
        this.id = id;
        this.parentGroupId = null;
        this.childGroupIds = new ArrayList<>();
        this.assetsIDs = new ArrayList<>();
    }


    /**
     * Method returns the name of the current group.
     *
     * @return  Name of the current group.
     */
    public String getGroupName() {
        return this.name;
    }

    /**
     * Method changes the current name of the group to the passed argument. The passed String must
     * not contain any whitespaces or symbols and begin with an alphabetic character.
     *
     * @param name  New name for the group.
     */
    public void setGroupName(String name) {
        this.name = name;
    }

    /**
     * Method returns the ID of the group.
     *
     * @return  ID of the group.
     */
    public Integer getId(){
        return this.id;
    }

    /**
     * Method returns the ID of the parent group.
     *
     * @return  ID of the parent group.
     */
    public Integer getParentGroupID() {
        return parentGroupId;
    }

    /**
     * Method changes the group's parent through the passed ID of the parent group.
     *
     * @param parentGroupId ID of the new parent group.
     * @return              This group.
     */
    public Group setParentGroupID(Integer parentGroupId) {
        this.parentGroupId = parentGroupId;
        return this;
    }

    /**
     * Method returns a list of the IDs of all child groups.
     *
     * @return  List of IDs of all child groups.
     */
    public ArrayList<Integer> getChildGroupIDs() {
        return childGroupIds;
    }

    /**
     * Method changes the child groups to the list of IDs of the child groups passed as argument.
     *
     * @param childGroupIds List of IDs for the new child groups.
     * @return              This group.
     */
    public Group setChildGroupIDs(ArrayList<Integer> childGroupIds) {
        this.childGroupIds = childGroupIds;
        return this;
    }

    /**
     * Returns all assets-IDs that are nested under this group.
     *
     * @return  List of all asset-IDs.
     */
    public ArrayList<Integer> getAssetsIDs() {
        return assetsIDs;
    }

    /**
     * Method adds the asset defined by the passed ID to this group.
     *
     * @param id    ID of the asset to add to the group.
     */
    public void pushAssetID(int id){
        this.assetsIDs.add(id);
    }

    /**
     * Method removes the asset of the passed ID from this group.
     *
     * @param id    ID of the asset to remove.
     */
    public void removeAssetID(int id) throws NotFoundException {
        if (assetsIDs.contains(id)) {
            assetsIDs.remove(Integer.valueOf(id));
        }
        else {
            throw new NotFoundException("Asset-ID" + id + " not found in the Asset-List for Group " + this.name);
        }
    }

    /**
     * Method adds the group whose ID is passed as child of this group.
     * Duplicate child groups are possible!
     *
     * @param id    ID of the group to add as child.
     */
    public void addChildGroupID(int id){
        this.childGroupIds.add(id);
    }

    /**
     * Method removes the child group whose ID is passed as argument.
     *
     * @param id    ID of the child group to remove.
     */
    public void removeChildGroupID(int id) throws NotFoundException {
        if (childGroupIds.contains(id)) {
            childGroupIds.remove(Integer.valueOf(id));
        }
        else {
            throw new NotFoundException("ID " + id + " not found in the list.");
        }
    }

    /**
     * Method returns the string-representation of the group in GameData syntax with minimum information.
     *
     * @return  Generated (pseudo) GameFileSyntax.
     */
    @Override
    public String toString(){
        return "[" + this.name + ";" + this.getAssetsIDs() + ";";
    }

    /**
     * Method returns a string-representation just like {@link #toString()}, but with more useful information
     * for debugging.
     *
     * @return  Generated (pseudo) GameFileSyntax for debugging.
     */
    public String toStringDev(){
        return "[" + this.name + "; (Parent:" + this.parentGroupId +")\n\tAssets:{" + this.getAssetsIDs() + "}\n\tSubGroups:{" + this.getChildGroupIDs() + "}" + "\n];";
    }

}
