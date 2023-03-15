package fr.slitherlink.game.action;

import fr.slitherlink.game.Game;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 12/03/2023
 */
public mpiclass RedoAction extends GameAction implements ActionTargeter{

    private Integer targetId;

    public RedoAction() {
        super();
        setCancelable(false);
    }

    @Override
    public List<Integer> getTargetId() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(targetId);
        return list;
    }

    @Override
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    @Override
    public void doAction(Game game) {
        List<GameAction> actions = game.getActions();
        if (actions.isEmpty())
            return;

        //passer les chaines d'undo et redo
        targetId = actions.size() - 1;
        while (targetId >= 0 && actions.get(targetId) instanceof RedoAction)
            targetId--;

        while (targetId >= 0 &&
                !( actions.get(targetId) instanceof UndoAction && !actions.get(targetId).isCanceled()))
            targetId--;

        game.getActions().get(targetId).swapCanceled();

        game.redoAllAction();
        game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.REDO.toString()));
    }

    @Override
    public void swapCanceled() {
        super.swapCanceled();
    }
}
