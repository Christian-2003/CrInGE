package game_engine.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Class implements unit tests for {@link GameMap}.
 *
 * @author  Christian-2003
 */
public class GameMapTest {

    @Test
    @DisplayName("Create GameMap with chunk")
    public void createWithChunk() {
        GameChunk[] chunks = {new GameChunk(null, null)};
        new GameMap(1, 1, chunks);
    }


    @Test
    @DisplayName("Create GameMap without height")
    public void createWithNoHeight() {
        GameChunk[] chunks = {new GameChunk(null, null)};
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new GameMap(1, 0, chunks));
    }


    @Test
    @DisplayName("Create GameMap without width")
    public void createWithNoWidth() {
        GameChunk[] chunks = {new GameChunk(null, null)};
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new GameMap(0, 1, chunks));
    }

    @Test
    @DisplayName("Create GameMap without correct number of chunks")
    public void createWithIncorrectChunkNumber() {
        GameChunk[] chunks = {new GameChunk(null, null)};
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new GameMap(2, 1, chunks));
    }


    @Test
    @DisplayName("Create GameMap with correct number of chunks")
    public void createWithCorrectNumberOfChunks() {
        GameChunk[] chunks = {new GameChunk(null, null), new GameChunk(null, null)};
        new GameMap(2, 1, chunks);
    }


    @Test
    @DisplayName("Create GameMap with null as chunk")
    public void createWithNullChunk() {
        GameChunk[] chunks = {null};
        new GameMap(1, 1, chunks);
    }


    @Test
    @DisplayName("Access chunk out of range")
    public void accessChunkOutOfRange() {
        //10 Chunks:
        GameChunk[] chunks = {new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null)};
        GameMap map = new GameMap(5, 2, chunks);
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> map.get(10));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> map.get(-1));
    }


    @Test
    @DisplayName("Access chunk in range with coordinates")
    public void accessChunkWithCoordinates() {
        //10 Chunks:
        GameChunk[] chunks = {new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null)};
        GameMap map = new GameMap(5, 2, chunks);
        Assertions.assertEquals(map.get(2, 0), chunks[2]);
        Assertions.assertEquals(map.get(2, 1), chunks[7]);
    }


    @Test
    @DisplayName("Access chunk out of range with coordinates")
    public void accessChunkOutOfRangeWithCoordinates() {
        //10 Chunks:
        GameChunk[] chunks = {new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null), new GameChunk(null, null)};
        GameMap map = new GameMap(5, 2, chunks);
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> map.get(7, 0));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> map.get(3, 2));
    }

}
