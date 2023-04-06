package fr.slitherlink.game.help;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTechnicTest {

    private static Technic technic;
    
    @BeforeEach
    void initTechnic(){
        technic = new Technic("title", "desc");
    }

    @Test
    void getSetTitle() {
        assertEquals("title", technic.getTitle());
    }

    @Test
    void getSetDesc() {
        assertEquals("desc", technic.getDesc());
    }

}
