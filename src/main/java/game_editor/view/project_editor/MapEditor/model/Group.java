package game_editor.view.project_editor.MapEditor.model;

import java.util.ArrayList;

/**
 * TODO add descriptiom
 * 
 * @author Tim Schnur
 */
public class Group {

    private String name;

    private Group parentGroup;

    private ArrayList<Group> subGroups;

    private ArrayList<Asset> assets;
    
    public Group(String name){
        this(name, null, new ArrayList<>(), new ArrayList<>());
    }
    public Group(String name, Group parentGroup){
        this(name, parentGroup, new ArrayList<>(), new ArrayList<>());
    }
    public Group(String name, ArrayList<Group> subGroups, ArrayList<Asset> assets){
        this(name, null, subGroups, assets);
    }
    public Group(String name, Group parentGroup, ArrayList<Group> subGroups, ArrayList<Asset> assets){
        this.name = name;
        this.parentGroup = parentGroup;
        this.subGroups = subGroups;
        this.assets = assets;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Group getParentGroup() {
        return parentGroup;
    }
    public void setParentGroup(Group parentGroup) {
        this.parentGroup = parentGroup;
    }
    public ArrayList<Group> getSubGroups() {
        return subGroups;
    }
    public ArrayList<Asset> getAssets() {
        return assets;
    }
    public void remove(Asset asset){
        this.assets.remove(asset);
    }
    public void remove(Group group){
        this.subGroups.remove(group);
    }
    public void add(Asset asset){
        this.assets.add(asset);
    }
    public void add(Group group){
        this.subGroups.add(group);
    }

    public void delete(){
        for(Group group : this.subGroups){
            group.delete();
        }
        for(Asset asset : assets){
            asset.delete();
        }
        parentGroup.remove(this);
    }
}
