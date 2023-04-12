package fr.slitherlink.game.help;

import fr.slitherlink.save.technic.ListTechnic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ListTechnicTest {

    private static ListTechnic listTechnic;

    @BeforeAll
    static void createListTechnic(){
        listTechnic = ListTechnic.getInstance();
        assertNotNull(listTechnic);
    }

    @Test
    void getId1() {
        assertEquals(1, listTechnic.getTechnic(0).getId());
    }

    @Test
    void getTitle1() {
        assertEquals("adjacent 0 et 3", listTechnic.getTechnic(0).getTitle());
    }
    
    @Test
    void getTitle25(){
        assertEquals("3 adjacent à deux 1 en diagonale", listTechnic.getTechnic(24).getTitle());
    } 

    @Test
    void getId25() {
        assertEquals(25, listTechnic.getTechnic(24).getId());
    }
}
