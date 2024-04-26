package game_editor.view.project_editor.MapEditor;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import game_editor.model.Asset;

/**
 * TODO add descriptiom
 * 
 * @author Tim Schnur
 */
public class MapEditorCell extends JButton{

    private Asset asset;

    private final int x,y;

    public MapEditorCell(int x, int y, Dimension preferredSize, Color backgroundColor){
        this(x, y, preferredSize, backgroundColor, null);
    }
    public MapEditorCell(int x, int y, Dimension preferedSize, Color backgroundColor, Asset asset){
        super();
        this.x = x;
        this.y = y;
        this.asset = asset;
        this.setPreferredSize(preferedSize);
        this.setBackground(backgroundColor);

        if(asset != null){
            asset.setX(x);
            asset.setY(y);
            this.setIcon(asset.getImage());
        }
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
        this.asset.setX(this.x);
        this.asset.setY(this.y);
        this.setIcon(asset.getImage());
    }
    public Asset removeAsset(){
        Asset removed = this.asset;
        this.asset = null;
        this.setIcon(null);
        return removed;
    }
    public Asset getAsset() {
        return asset;
    }
}
