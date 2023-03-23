package fr.slitherlink.game.action;

import fr.slitherlink.game.Game;

import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 12/03/2023
 */
public class RedoAction extends GameAction implements ActionTargeter{

    private Integer targetId;

    public RedoAction() {
        super();
        setCancelable(false);
    }

    @Override
    public Integer getTargetId() {
        return targetId;
    }

    @Override
    public Integer setTargetId(Integer targetId) {
        return this.targetId = targetId;
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
    }
}
