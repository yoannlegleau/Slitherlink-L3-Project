package fr.slitherlink.save.gamesave;

import fr.slitherlink.game.action.ActionVisitore;
import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.game.action.actions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 */
public class GameActionToSaveVisitore implements ActionVisitore {


    List<GameActionSave> gameActionSaves;

    List<GameAction> gameActions;


    private GameActionToSaveVisitore() {
        super();
        gameActionSaves = new ArrayList<>();
    }

    public GameActionToSaveVisitore(List<GameAction> gameActions) {
        this();
        this.gameActions = gameActions;
        for (GameAction gameAction : gameActions) {
            gameAction.accept(this);
        }
    }

    List<GameActionSave> getGameActionSave(){
        return gameActionSaves;
    }

    @Override
    public void visit(AssumptionStart assumptionStart) {
        ArrayList<Integer> target = new ArrayList<>();
        for (GameAction gameAction : assumptionStart.getTargets())
            target.add(gameActions.indexOf(gameAction));

        gameActionSaves.add(GameActionSave.getGameActionSave()
                .setCanceled(assumptionStart.isCanceled())
                .setGameActionTypes(assumptionStart.getGameActionTypes())
                .setTarget(target)
        );
    }

    @Override
    public void visit(AssumptionStop assumptionStop) {
        gameActionSaves.add(GameActionSave.getGameActionSave()
                .setCanceled(assumptionStop.isCanceled())
                .setGameActionTypes(assumptionStop.getGameActionTypes())
                .setValidated(assumptionStop.isValid())
        );
    }

    @Override
    public void visit(EdgeAction edgeAction) {
        gameActionSaves.add(GameActionSave.getGameActionSave()
                .setCanceled(edgeAction.isCanceled())
                .setGameActionTypes("SET_"+edgeAction.getType().name())
                .setX(edgeAction.getX())
                .setY(edgeAction.getY())
                .setT(edgeAction.getT())
        );
    }

    @Override
    public void visit(RedoAction redoAction) {
        ArrayList<Integer> target = new ArrayList<>();
        target.add(gameActions.indexOf(redoAction.getTarget()));
        gameActionSaves.add(GameActionSave.getGameActionSave()
                .setCanceled(redoAction.isCanceled())
                .setGameActionTypes(redoAction.getGameActionTypes())
                .setTarget(target)
        );
    }

    @Override
    public void visit(UndoAction undoAction) {
        ArrayList<Integer> target = new ArrayList<>();
        target.add(gameActions.indexOf(undoAction.getTarget()));
        gameActionSaves.add(GameActionSave.getGameActionSave()
                .setCanceled(undoAction.isCanceled())
                .setGameActionTypes(undoAction.getGameActionTypes())
                .setTarget(target)
        );
    }

    @Override
    public void visit(HintAction hintAction) {
        gameActionSaves.add(GameActionSave.getGameActionSave()
                .setCanceled(hintAction.isCanceled())
                .setGameActionTypes(hintAction.getGameActionTypes())
        );
    }
}
