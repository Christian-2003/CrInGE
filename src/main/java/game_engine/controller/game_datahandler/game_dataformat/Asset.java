package game_engine.controller.game_dataHandler.game_dataFormat;


import game_engine.controller.game_dataHandler.Exceptions.NotFoundException;
import game_engine.controller.utility.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Class implements an Asset
 * @see Group
 * @see ImageSource
 * @author Farbian001
 */
public class Asset {
    /*
     * Shows the grid coordinates on which the asset is located
     */
    private int x,y;
    /*
     * Identifier for Asset
     */
    private final Integer id;
    /*
     * ImageSource for Sprite
     */
    /*
     * Name for the Asset
     */
    private String name;
    private ImageSource imageSource;
    /**
     * All parent-groups that are nested to the Asset
     */
    private ArrayList<Group> parentGroups = new ArrayList<>();

    private HashMap<String, String> customAttribute = new HashMap<>();

    public Asset(String name){
        this.id = new Utils().generateNewRandomID();
        this.name = name;
        // If not set stay visible in the center (@todo-fabian work on that later)
        this.x = -1;
        this.y = -1;
        imageSource = null;
    }

    public Asset(int id,String name ,int x, int y, File sprite/*, ArrayList<Group> parentGroups*/){
        this.id = id;
        this.name = name;
        //TODO-FABIAN001 Check if x,y are inbounds
        setX(x);
        setY(y);
        setImageSource(sprite);
       // setParentGroups(parentGroups);
    }

    public Asset(int id,String name ,int x, int y, File sprite, HashMap<String, String> customAttr){
        this.id = id;
        this.name = name;
        //TODO-FABIAN001 Check if x,y are inbounds
        setX(x);
        setY(y);
        setImageSource(sprite);
        this.customAttribute = customAttr;
        // setParentGroups(parentGroups);
    }

    /**
     * Returns all custom Attribute
     * @return HashMap<String, String> | Key = Attribute-Name
     */
    public HashMap<String, String> getCustomAttribute(){
        return this.customAttribute;
    }

    /**
     * Returns
     * @param attr_name
     * @return Set<String>
     * @throws NotFoundException | If Attribute can not be found
     * @todo-Fabian Need to add this function
     */
    public Set<String> getCustomeAttribute(String attr_name) throws NotFoundException {
        if(!customAttribute.containsKey(attr_name)) throw new NotFoundException("No attr ("+ attr_name +")");
        return customAttribute.keySet();
    }

    public String getName() {
        return name;
    }

    /**
     * Sets a new name for this asset
     * @param name | Assets-Name (no whitespaces!!!)
     * @return this.Asset
     */
    public Asset setName(String name) {
        this.name = name.trim();
        return this;
    }

    /**
     * Returns the "X"-Coordinate from an Asset
     * @return int
     */
    public int getX() {
        return this.x;
    }

    /**
     * Sets the "x"-Coordinate of an Assets
     *
     * @unsafe no checks currently
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the "Y"-Coordinate from an Asset
     * @return int
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets the "y"-Coordinate of an Assets
     *
     * @unsafe no checks currently
     */
    public void setY(int y) {
        //TODO-FABIAN001: Check if Value is inbounds
        this.y = y;
    }

    /**
     * Returns the Assets ID
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns the Sprite ImageClass from current Asset
     * @return ImageSource
     * @see ImageSource
     */
    public ImageSource getImageSource() {
        return imageSource;
    }

    /**
     * Sets the ImagieSource Class of an Assets
     * @unsafe No checks yet
     */
    public void setImageSource(File file) {
        this.imageSource = file.exists() ? new ImageSource(file): new ImageSource(null);
    }

    /**
     * Returns all Parent-Groups
     * @return ParentGroups
     */
    public ArrayList<Group> getParentGroups() {
        return parentGroups;
    }

    /**
     * Overrides parentGroups
     * @param parentGroups | ArrayList<Group>
     * @return this.Asset
     */
    public Asset setParentGroups(ArrayList<Group> parentGroups) {
        this.parentGroups = parentGroups;
        return this;
    }

    /**
     * Returns a short summery in GameData Syntax, what's inside this asset
     * @return String | (pseudo) GameDataSyntax
     * @todo-Fabian transform ImageSource-path to index of an ArrayList
     */
    @Override
    public String toString(){
        return "{" + this.name + ";" + this.id + ";" + this.x + ";" + this.y + ";" + this.imageSource.getFile().getPath() +"};";
    }
}
