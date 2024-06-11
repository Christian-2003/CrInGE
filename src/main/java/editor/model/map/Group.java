package editor.model.map;

import java.util.ArrayList;

/**
 * Can be used to select multiple Assets at the same time.
 * @author Tim Schnur
 */
public class Group {

    /** name of the group. */
    private String name;

    /** the parent group of this group. */
    private Group parentGroup;

    /** child groups of this group. */
    private ArrayList<Group> subGroups;

    /** assets within this group. */
    private ArrayList<Asset> assets;

    /**
     * @param name of the group
     */
    public Group(final String name) {
        this(name, null, new ArrayList<>(), new ArrayList<>());
    }
    /**
     * @param name of the group
     * @param parentGroup of this group
     */
    public Group(final String name, final Group parentGroup) {
        this(name, parentGroup, new ArrayList<>(), new ArrayList<>());
    }
    /**
     * @param name of this group
     * @param subGroups childs of this group
     * @param assets of this group
     */
    public Group(final String name, final ArrayList<Group> subGroups,
    final ArrayList<Asset> assets) {
        this(name, null, subGroups, assets);
    }
    /**
     * @param name of this group
     * @param parentGroup of this group
     * @param subGroups childs of this group
     * @param assets of this group
     */
    public Group(final String name, final Group parentGroup,
    final ArrayList<Group> subGroups, final ArrayList<Asset> assets) {
        this.name = name;
        this.parentGroup = parentGroup;
        this.subGroups = subGroups;
        this.assets = assets;
    }

    /**
     * @return name of the group
     */
    public String getName() {
        return name;
    }
    /**
     * @param name of the group
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * @return Parent {@link Group} of this group
     */
    public Group getParentGroup() {
        return parentGroup;
    }
    /**
     * @param parentGroup to move this group into
     */
    public void setParentGroup(final Group parentGroup) {
        if (parentGroup != null) {
            parentGroup.add(this);
        }
        if (this.parentGroup != null) {
            parentGroup.remove(this);
        }
        this.parentGroup = parentGroup;
    }
    /**
     * @return child groups of this group
     */
    public ArrayList<Group> getSubGroups() {
        return subGroups;
    }
    /**
     * @return assets within this group
     */
    public ArrayList<Asset> getAssets() {
        return assets;
    }
    /**
     * @param asset to be removed from this group
     */
    public void remove(final Asset asset) {
        this.assets.remove(asset);
    }
    /**
     * @param group to be removed from this group
     */
    public void remove(final Group group) {
        this.subGroups.remove(group);
    }
    /**
     * @param asset to be added to this group
     */
    public void add(final Asset asset) {
        this.assets.add(asset);
    }
    /**
     * @param group to be added to this group
     */
    public void add(final Group group) {
        this.subGroups.add(group);
    }

    /**
     * <p> Clears up all Data Trash of the Group and delets all sub Items. </p>
     * <b> Should be called before unreferencing an Group Object </b>
     */
    public void delete() {
        for (Group group : this.subGroups) {
            group.delete();
        }
        for (Asset asset : assets) {
            asset.delete();
        }
        parentGroup.remove(this);
    }
}
