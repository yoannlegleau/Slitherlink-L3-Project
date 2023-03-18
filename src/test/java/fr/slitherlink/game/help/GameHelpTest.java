package fr.slitherlink.game.help;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameHelpTest {

    private static Help help;
    
    @BeforeEach
    void initHelp(){
        help = new Help(null, null);
    }

    @Test
    void getSetTechnic() {
        assertNull(help.getTechnic());
    }

    @Test
    void getSetListCoord() {
        assertNull(help.getListCoord());
    }
}
