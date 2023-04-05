package fr.slitherlink.save.puzzle;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 05/04/2023
 */
class PuzzleResourceManageurTest {

    @Test
    void getLevelIdList() {
        List<Integer> levelList = PuzzleResourceManageur.getLevelIdList();
        System.out.println(levelList);
    }

    @Test
    void getLevelList() {
        List<PuzzleSave> levelList = PuzzleResourceManageur.getLevelList();
        System.out.println(levelList);
    }

    @Test
    void loadPuzzle() {
        PuzzleSave level = PuzzleResourceManageur.LoadPuzzle(1);
        System.out.println(level);
    }

}