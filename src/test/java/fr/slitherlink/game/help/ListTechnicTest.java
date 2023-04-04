package fr.slitherlink.game.help;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListTechnicTest {
    

    @Test
    public static void main(String[] args) {
        assertTrue(ListTechnic.generate().listTechnic.size() > 0);
    }
}
