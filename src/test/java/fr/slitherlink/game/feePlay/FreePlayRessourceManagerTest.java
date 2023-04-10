package fr.slitherlink.game.feePlay;

import fr.slitherlink.save.feePlay.FreePlayRessourceManager;
import fr.slitherlink.save.puzzle.Difficulty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 10/04/2023
 */
class FreePlayRessourceManagerTest {

    @Test
    void getInstance() {
        FreePlayRessourceManager instance = FreePlayRessourceManager.getInstance();
        assertNotNull(instance);
    }

    @Test
    void setDefaultConfig() {
        FreePlayRessourceManager instance = FreePlayRessourceManager.getInstance();

        instance.setSize(6);
        instance.setDifficulty(Difficulty.MEDIUM);
        instance.setFinishedFilter(true);
        instance.addFinishedLevelId(1);
        instance.addFinishedLevelId(2);
        instance.saveConfig();
    }

    @Test
    void saveConfig() {
        FreePlayRessourceManager instance = FreePlayRessourceManager.getInstance();

        instance.saveConfig();
    }

}