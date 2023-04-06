package fr.slitherlink.game.help;

import org.junit.jupiter.api.Test;

import fr.slitherlink.save.technic.ListTechnic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

public class ListTechnicTest {
    private static ListTechnic listTechnic;

    @BeforeAll
    static void init(){
        listTechnic =  ListTechnic.generate();
    }

    @Test
    void getTitle1() {
        assertEquals("adjacent 0 et 3", listTechnic.getTechnic(0).getTitle());
    }
    
    @Test
    void getTitle25(){
        assertEquals("3 adjacent à deux 1 en diagonale", listTechnic.getTechnic(24).getTitle());
    } 

}
