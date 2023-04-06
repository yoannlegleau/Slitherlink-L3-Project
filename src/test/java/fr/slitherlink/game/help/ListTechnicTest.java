package fr.slitherlink.game.help;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListTechnicTest {
    

    @Test
    public static void main(String[] args) {
        assertTrue(ListTechnic.generate().getTechnic(0).getTitle().equals("adjacent 0 et 3"));
        assertTrue(ListTechnic.generate().getTechnic(24).getTitle().equals("3 adjacent à deux 1 en diagonale"));
    }
}
