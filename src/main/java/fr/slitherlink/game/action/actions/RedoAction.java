package fr.slitherlink.game.action.actions;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionVisitore;
import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.GameActionTypes;

import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 12/03/2023
 */
public class RedoAction extends GameAction {

    private GameAction target;


    public RedoAction() {
        super();
        setGameActionTypes(GameActionTypes.REDO);
    }

    public void setTarget(GameAction target) {
        this.target = target;
    }

    public GameAction getTarget() {
        return target;
    }

    @Override
    public void accept(ActionVisitore visitore) {
        visitore.visit(this);
    }

    @Override
    public void doAction(Game game) {
        List<GameAction> actions = game.getActions();
        if (actions.isEmpty())
            return;

        //passer les chaines d'undo et redo
        int targetId = actions.size() - 1;
        while (targetId >= 0 && actions.get(targetId) instanceof RedoAction)
            targetId--;

        while (targetId >= 0 &&
                !( actions.get(targetId) instanceof UndoAction && !actions.get(targetId).isCanceled()))
            targetId--;

        if (targetId >= 0) {
            target = actions.get(targetId);
            target.swapCanceled();
            game.redoAllAction();
            game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.REDO.toString()));
        } else
            game.getActions().remove(this);
    }

    @Override
    public void swapCanceled() {
        super.swapCanceled();
        target.swapCanceled();
    }
}
