package fr.slitherlink.save.adventureMode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 12/04/2023
 */
class AdventureModeRessourceManageurTest {

    @Test
    void getInstence() {
        assertNotNull(AdventureModeRessourceManageur.getInstence());
    }

    @Test
    void isLevelFinished() {

    }

    @Test
    void addLevel() {
        AdventureModeRessourceManageur.getInstence().addLevel(1);

    }
}