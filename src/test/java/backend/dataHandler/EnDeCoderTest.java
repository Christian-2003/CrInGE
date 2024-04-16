package backend.dataHandler;

import game_engine.controller.game_datahandler.Exceptions.GameDataFileSyntaxException;
import game_engine.controller.game_datahandler.Exceptions.NotFoundException;


import game_engine.controller.game_datahandler.game_dataformat.Asset;
import game_engine.controller.game_datahandler.game_dataformat.Group;
import game_engine.controller.game_datahandler.game_dataformat.ImageSource;
import org.junit.jupiter.api.Test;

/**
 * @WIP
 * Tests to ensure the proper En/Decoding of GameData Files
 * @author FabianDev001
 */
public class EnDeCoderTest {

    public static void main(String[] args) throws GameDataFileSyntaxException, NotFoundException {

        // TestCase Readable String | Um Filereader zu überspringen;
        String sourceinput = "sources:[src/test/resources/dummyData/dummy_Cringe02.png;src/test/resources/dummyData/dummy_Cringe01.png;src/test/resources/dummyData/dummy_Crap01.png]";
        String input = """
                [main;
                    [group1;
                        {Asset1;100;200;0};
                        {Asset2;150;300;0};
                    ];
                    [group2;
                        {Asset3;200;400;1};
                        [subgroup1;
                            {Asset4;250;500;2};
                        ]
                    ]
                ]""";




    }
}
