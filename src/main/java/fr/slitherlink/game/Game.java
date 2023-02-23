package fr.slitherlink.game;

import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.grid.Grid;
import fr.slitherlink.save.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
public class Game {

    private Puzzle puzzle;

    private GameSave save;

    private Grid grid;

    private List<GameAction> actions;

    private boolean assumptionMode;

    private boolean isSolved;

    private int NbHint;

    public Game(int puzzleId){
        puzzle = PuzzleResourceManageur.LoadPuzzle(puzzleId);

        grid = new Grid(puzzle.getSize());
        actions = new ArrayList<>();
        assumptionMode = false;
        isSolved = false;
        NbHint = 0;

        save = GameSaveResourceManageur.LoadLevel(puzzleId);
        save.reloadGame(this);
    }

    public void action(GameAction action){
        if (isSolved)
            return;
        actions.add(action);
        action.doAction(this);
    }

}
