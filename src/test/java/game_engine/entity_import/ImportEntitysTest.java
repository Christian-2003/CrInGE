package game_engine.entity_import;

import game_engine.controller.game_dataHandler.Exceptions.GameDataFileSyntaxException;
import game_engine.controller.game_dataHandler.Exceptions.NotFoundException;
import game_engine.controller.game_dataHandler.FileCoder;
import game_engine.controller.game_dataHandler.Loader;
import game_engine.model.entities.Entity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Class implements unit tests for {@link FileCoder}.
 *
 * @author  Farbian001
 */
@Execution(ExecutionMode.CONCURRENT) // Run tests concurrently
public class ImportEntitysTest {

    public static Loader load = null;

    public ImportEntitysTest() throws NotFoundException, GameDataFileSyntaxException {
    }

    @BeforeAll()
    @DisplayName("Initial")
        static void init() throws NotFoundException, GameDataFileSyntaxException {
            load = new Loader();
            assertNotNull(load);
            assertNotNull(load.getImgSources());
            assertNotNull(load.getEntity());
        }


    @Test()
    @DisplayName("Check correct ImageIcon size")
    public void loadEntityTest1() throws NotFoundException, GameDataFileSyntaxException {
        assumeTrue(load.getImgSources().length == 14);
    }

    @Test()
    @DisplayName("Check if Entity's value are correct")
    public void checkEntity() throws NotFoundException, GameDataFileSyntaxException {
        String[] sol_Entity = {"{creeper1;12;t;t;1;2;1;2;2.0;5.0};", "{creeper2;12;t;t;1;2;1;2;5.5;7.5};", "{human1;13;t;f;1;1;1;1;1.0;2.0};"};
        int i = 0;
        HashMap<Integer, Entity> entityHashMap = load.getEntity();
        if (entityHashMap.size() != sol_Entity.length) {
            fail("Enity-Size does not match with expected size");
        }
        for (Map.Entry<Integer, Entity> entity : entityHashMap.entrySet()) {
            assertEquals(sol_Entity[i], entity.getValue().toString());
            i++;
        }
    }

    @Test()
    @DisplayName("Encode -> Decode Verify")
    public void decode_and_encode() throws GameDataFileSyntaxException, IOException {
        BufferedReader bR = new BufferedReader(new FileReader(Paths.get(".","src","main","java","game_engine","controller","game_dataHandler", "saveFiles" ,"creeperExample.gamedata").toFile()));
        StringBuilder sB = new StringBuilder();

        while(bR.ready()){
            sB.append(bR.readLine()).append(System.lineSeparator());
        }
        bR.close();
        String result = FileCoder.encode_ImageIcons(load.getImgSources()) + System.lineSeparator() + FileCoder.encode_assets(load.getGroups(), load.getEntity());
        assertEquals(sB.toString().replaceAll("//.*$", "").trim(), result.trim());
    }
}
