package game_engine.model;

import game_engine.model.map.GameChunk;
import game_engine.model.map.objects.MapObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Class implements unit tests for {@link GameChunk}.
 *
 * @author  Christian-2003
 */
public class GameChunkTest {

    @Test()
    @DisplayName("Iterate through chunk without MapObjects")
    public void accessMapObjectWithNull() {
        GameChunk chunk = new GameChunk(null, null);
        Assertions.assertThrowsExactly(NullPointerException.class, chunk::nextMapObject);
    }


    @Test()
    @DisplayName("Iterate through chunk without external MapObjects")
    public void accessExternalMapObjectWithNull() {
        GameChunk chunk = new GameChunk(null, null);
        Assertions.assertThrowsExactly(NullPointerException.class, chunk::nextExternalMapObject);
    }


    @Test
    @DisplayName("Access MapObject")
    public void accessMapObject() {
        MapObject[] mapObjects = {new MapObject(true, true, 0, 0)};
        GameChunk chunk = new GameChunk(mapObjects, null);
        Assertions.assertEquals(mapObjects[0], chunk.nextMapObject());
    }


    @Test
    @DisplayName("Access external MapObject")
    public void accessExternalMapObject() {
        MapObject[] mapObjects = {new MapObject(true, true, 0, 0)};
        GameChunk chunk = new GameChunk(null, mapObjects);
        Assertions.assertEquals(mapObjects[0], chunk.nextExternalMapObject());
    }


    @Test
    @DisplayName("Access non existent MapObject")
    public void accessNonExistentMapObject() {
        MapObject[] mapObjects = {new MapObject(true, true, 0, 0)};
        GameChunk chunk = new GameChunk(mapObjects, null);
        Assertions.assertEquals(mapObjects[0], chunk.nextMapObject());
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, chunk::nextMapObject);
    }


    @Test
    @DisplayName("Access non existent external MapObject")
    public void accessNonExistentExternalMapObject() {
        MapObject[] mapObjects = {new MapObject(true, true, 0, 0)};
        GameChunk chunk = new GameChunk(null, mapObjects);
        Assertions.assertEquals(mapObjects[0], chunk.nextExternalMapObject());
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, chunk::nextExternalMapObject);
    }


    @Test
    @DisplayName("Reset MapObject iterator")
    public void resetMapObjectIterator() {
        MapObject[] mapObjects = {new MapObject(true, true, 0, 0)};
        GameChunk chunk = new GameChunk(mapObjects, null);
        Assertions.assertEquals(mapObjects[0], chunk.nextMapObject());
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, chunk::nextMapObject);
        chunk.resetMapObjectIterator();
        Assertions.assertEquals(mapObjects[0], chunk.nextMapObject());
    }


    @Test
    @DisplayName("Reset external MapObject iterator")
    public void resetExternalMapObjectIterator() {
        MapObject[] mapObjects = {new MapObject(true, true, 0, 0)};
        GameChunk chunk = new GameChunk(null, mapObjects);
        Assertions.assertEquals(mapObjects[0], chunk.nextExternalMapObject());
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, chunk::nextExternalMapObject);
        chunk.resetExternalMapObjectIterator();
        Assertions.assertEquals(mapObjects[0], chunk.nextExternalMapObject());
    }

}
