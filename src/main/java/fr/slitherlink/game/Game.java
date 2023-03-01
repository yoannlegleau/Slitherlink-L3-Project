package fr.slitherlink.game;

import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.grid.Grid;
import fr.slitherlink.save.GameSave;
import fr.slitherlink.save.GameSaveResourceManageur;
import fr.slitherlink.save.PuzzleResourceManageur;
import fr.slitherlink.save.PuzzleSave;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
public class Game {

    private Integer[][] numbers;
    private Grid solution;

    private Grid currentGrid;

    private List<GameAction> actions;

    private boolean assumptionMode;

    private boolean isSolved;

    private int NbHint;

    public Game(int puzzleId){
        loadGame(puzzleId);
        currentGrid = new Grid(solution.getSize());
        actions = new ArrayList<>();
        assumptionMode = false;
        isSolved = false;
        NbHint = 0;

    }

    public Grid getSolution( ){
        return solution;
    }

    public Grid getCurrentGrid() {
        return currentGrid;
    }

    public void action(GameAction action){
        if (isSolved)
            return;
        actions.add(action);
        action.doAction(this);
    }

    private void loadGame(int puzzleId){
        PuzzleSave puzzle = PuzzleResourceManageur.LoadPuzzle(puzzleId);
        numbers = puzzle.getGridNumbers();
        solution = puzzle.getSolution();
        GameSave gameSave = GameSaveResourceManageur.LoadLevel(puzzleId);
        for (GameAction action: gameSave.getActions())
            action(action);//        save = GameSaveResourceManageur.LoadLevel(puzzleId);
    }

}
