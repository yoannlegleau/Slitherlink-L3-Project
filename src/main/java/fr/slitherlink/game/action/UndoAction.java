package fr.slitherlink.game.action;

import fr.slitherlink.game.Game;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 12/03/2023
 */
public class UndoAction extends GameAction implements ActionTargeter{

    private GameAction target;

    public UndoAction() {
        super();
        setCancelable(false);
    }

    @Override
    public List<GameAction> getTargetId() {
        LinkedList<GameAction> list = new LinkedList<>();
        list.add(target);
        return list;
    }

    @Override
    public void setTargetId(GameAction target) {
        this.target = target;
    }

    @Override
    public void doAction(Game game) {
        List<GameAction> actions = game.getActions();
        if (actions.isEmpty())
            return;

        //passer les chaines d'annulation
        fondTargetId = actions.size() - 1;
        while (targetId >= 0 && actions.get(targetId) instanceof UndoAction)
            targetId--;

        //chercher la première action annulable
        while (targetId >= 0 && actions.get(targetId).isCanceled())
            targetId--;

        if (targetId >= 0) {
            try {
                int subTargetId = ((ActionTargeter) game.getActions().get(targetId)).getTargetId();
                while (game.getActions().get(subTargetId) instanceof ActionTargeter )
                    subTargetId = ((ActionTargeter) game.getActions().get(subTargetId)).getTargetId();
                game.getActions().get(subTargetId).swapCanceled();
            }catch (Exception ignored){}
            game.getActions().get(targetId).swapCanceled();
        } else {
            targetId = null;
            game.getActions().remove(this);
        }

        game.redoAllAction();
        game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.UNDO.toString()));
    }
}
