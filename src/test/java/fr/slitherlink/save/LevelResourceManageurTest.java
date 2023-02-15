package fr.slitherlink.save;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 * @pakage fr.slitherlink.save
 */
class LevelResourceManageurTest {

    @Test
    void loadLevel() {
        assertNull(LevelResourceManageur.LoadLevel(0));
        assertEquals(1,LevelResourceManageur.LoadLevel(1).getId());
    }

    @Test
    void saveLevel() {
        Level level = new Level(1,6);
        LevelResourceManageur.saveLevel(level);
    }
}