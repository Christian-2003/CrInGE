package game_editor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author Tim Schnur
 */
class EinleseTest {

    @Test
    void ressourceTest() throws Exception{
        File testdataFile = new File(this.getClass().getResource("/Testdata.csv").toURI());
        String testdata;
        try (BufferedReader reader = new BufferedReader(new FileReader(testdataFile))){
            testdata = reader.readLine();
        }
        assertEquals("True", testdata);
    }
}
