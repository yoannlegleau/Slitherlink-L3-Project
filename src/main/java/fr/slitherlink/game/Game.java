package fr.slitherlink.game;

import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.game.action.actions.RedoAction;
import fr.slitherlink.game.action.actions.UndoAction;
import fr.slitherlink.game.grid.Grid;
import fr.slitherlink.save.gamesave.GameSave;
import fr.slitherlink.save.gamesave.GameSaveResourceManageur;
import fr.slitherlink.save.puzzle.PuzzleResourceManageur;
import fr.slitherlink.save.puzzle.PuzzleSave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
public class Game {

    int puzzleId;
    private Integer[][] numbers;
    private Grid solution;

    private Grid currentGrid;

    private List<GameAction> actions;

    private Boolean doSave = true;

    private List<ActionListener> listeners = new ArrayList<>();

    private boolean assumptionMode;

    private boolean isSolved;

    private int NbHint;

    public Game(int puzzleId){
        this.puzzleId = puzzleId;
        loadPuzzle(puzzleId);
        actions = new ArrayList<>();
        currentGrid = new Grid(solution.getSize());
        init();
        loadGameSave();
    }
    public void init(){
        isSolved = false;
        assumptionMode = false;
        NbHint = 0;
        currentGrid.clear();
    }

    public void reset() {
        init();
        actions.clear();
        saveGame();
        notifyListeners(new ActionEvent(this, 0, GameActionTypes.RESET.toString()));
    }

    public void setDoSave(Boolean doSave) {
        this.doSave = doSave;
    }

    public Grid getSolution( ){
        return solution;
    }

    public Grid getCurrentGrid() {
        return currentGrid;
    }

    /**
    * modifie la grille courante ! Attention , Utilisable pour le LevelEditor
     * @param grid la grille à mettre en courante
     */
    public void setCurrentGrid(Grid grid) {
        currentGrid = grid;
    }

    public boolean isWin() {
        return isSolved;
    }

    public boolean isAssumptionMode() {
        return assumptionMode;
    }

    public void setAssumptionMode(boolean assumptionMode) {
        this.assumptionMode = assumptionMode;
    }

    public void incrementNbHint(){
        NbHint++;
    }

    public List<GameAction> getActions() {
        return actions;
    }

    public int getPuzzleId() {
        return puzzleId;
    }
    public Integer[][] getNumbers() {
        return numbers;
    }

    public void subscribe(ActionListener listener){
        listeners.add(listener);
    }

    public void unsubscribe(ActionListener listener){
        listeners.remove(listener);
    }

    public void notifyListeners(ActionEvent action){
        for (ActionListener listener: listeners)
            listener.actionPerformed(action);
    }

    public void action(GameAction action){
        if (isSolved)
            return;
        actions.add(action);
        action.doAction(this);
        saveGame();
    }

    private void loadPuzzle(int puzzleId){
        PuzzleSave puzzle = PuzzleResourceManageur.LoadPuzzle(puzzleId);
        numbers = puzzle.getGridNumbers();
        solution = puzzle.getSolution();
    }

    public void loadGameSave(){
        GameSave gameSave = GameSaveResourceManageur.LoadLevel(puzzleId);
        if (gameSave == null || gameSave.getActionsToGameAction() == null)
            return;
        actions = gameSave.getActionsToGameAction();
        redoAllAction();
    }

    public void redoAllAction(){
        init();
        for (GameAction action: actions) {
            if (!action.isCanceled() &&
                    action.getGameActionTypes() != GameActionTypes.UNDO &&
                    action.getGameActionTypes() != GameActionTypes.REDO)
                action.doAction(this);
        }
    }

    private void saveGame(){
        if (doSave)
            GameSaveResourceManageur.saveGameSave(new GameSave(this));
    }

    public void checkisWin() {
        if (currentGrid.equals(solution)) {
            isSolved = true;
            notifyListeners(new ActionEvent(this, 0, GameActionTypes.WIN.toString()));
        }else
            isSolved = false;
    }
}
