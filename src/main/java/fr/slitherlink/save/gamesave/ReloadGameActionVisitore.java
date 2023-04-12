package fr.slitherlink.save.gamesave;

import fr.slitherlink.game.action.ActionVisitore;
import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.game.action.actions.*;
import fr.slitherlink.game.grid.EdgeType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 */
public class ReloadGameActionVisitore implements ActionVisitore {

    private List<GameActionSave> gameActionSaves;

    private List<GameAction> gameActions;

    public static List<GameAction> reloadGameAction(List<GameActionSave> list) {
        ReloadGameActionVisitore reloadGameActionVisitore = new ReloadGameActionVisitore(list);
        return reloadGameActionVisitore.getGameActions();
    }

    public ReloadGameActionVisitore(List<GameActionSave> gameActionSaves) {
        if (gameActionSaves == null)
            return;

        this.gameActionSaves = gameActionSaves;

        gameActions = new ArrayList<>();
        for ( GameActionSave gameActionSave: gameActionSaves)
            gameActions.add(createGameAction(gameActionSave));

        for (GameAction gameAction : gameActions) {
            gameAction.accept(this);
        }
    }

    public List<GameAction> getGameActions() {
        return gameActions;
    }

    private static GameAction createGameAction(GameActionSave gameActionSave){
        GameAction gameAction = null;
        switch (GameActionTypes.valueOf( gameActionSave.gameActionTypes)){
            case REDO -> gameAction = new RedoAction();
            case UNDO -> gameAction = new UndoAction();
            case ASSUMPTION_START -> gameAction = new AssumptionStart();
            case ASSUMPTION_VALID -> gameAction = new AssumptionStop(true);
            case ASSUMPTION_CANCEL -> gameAction = new AssumptionStop(false);
            case SET_LINE, SET_CROSS, SET_EMPTY -> gameAction = new EdgeAction();
            case NEW_HELP, HIGHLIGTH_HELP -> gameAction = new HelpAction();
        }
        return gameAction;
    }


    @Override
    public void visit(AssumptionStart assumptionStart) {
        GameActionSave source = gameActionSaves.get(gameActions.indexOf(assumptionStart));
        assumptionStart.setCanceled(source.isCanceled());
        if (source.getTarget() != null)
            for (Integer target : source.getTarget())
                assumptionStart.addTarget(gameActions.get(target));
    }

    @Override
    public void visit(AssumptionStop assumptionStop) {
        GameActionTypes gameActionTypes;
        if (assumptionStop.isValid())
            gameActionTypes = GameActionTypes.ASSUMPTION_VALID;
        else
            gameActionTypes = GameActionTypes.ASSUMPTION_CANCEL;

        GameActionSave source = gameActionSaves.get(gameActions.indexOf(assumptionStop));
        assumptionStop.setCanceled(source.isCanceled());
        assumptionStop.setGameActionTypes(gameActionTypes);
        assumptionStop.setValid(source.getValidated());
        assumptionStop.setTarget(gameActions.get(source.getTarget().get(0)));
    }

    @Override
    public void visit(EdgeAction edgeAction) {
        GameActionSave source = gameActionSaves.get(gameActions.indexOf(edgeAction));
        edgeAction.setGameActionTypes(GameActionTypes.valueOf(source.gameActionTypes));
        edgeAction.setType(EdgeType.valueOf(source.gameActionTypes.replace("SET_", "")));
        edgeAction.setCanceled(source.isCanceled());
        edgeAction.setX(source.x);
        edgeAction.setY(source.y);
        edgeAction.setT(source.t);

    }

    @Override
    public void visit(RedoAction redoAction) {
        GameActionSave source = gameActionSaves.get(gameActions.indexOf(redoAction));
        redoAction.setCanceled(source.isCanceled());
        for (Integer target : source.getTarget())
            redoAction.setTarget(gameActions.get(target));
    }

    @Override
    public void visit(UndoAction undoAction) {
        GameActionSave source = gameActionSaves.get(gameActions.indexOf(undoAction));
        undoAction.setCanceled(source.isCanceled());
        for (Integer target : source.getTarget())
            undoAction.setTarget(gameActions.get(target));

    }

    @Override
    public void visit(HelpAction hintAction) {

    }
}
