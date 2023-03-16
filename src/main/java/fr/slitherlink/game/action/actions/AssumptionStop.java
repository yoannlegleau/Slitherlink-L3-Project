package fr.slitherlink.game.action.actions;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionVisitore;
import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.GameActionTypes;

import java.awt.event.ActionEvent;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 */
public class AssumptionStop extends GameAction {

    private boolean valid;

    private GameAction target;

    public AssumptionStop(){
        super();
    }
    public AssumptionStop(boolean valid){
        this();
        this.valid = valid;
    }

    /**
     * definie si l'hipothese est validé. À utiliser uniquement pendant le rechargement d'une partie
     * @param valid
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid(){
        return valid;
    }

    public GameAction getTarget() {
        return target;
    }

    public void setTarget(GameAction target) {
        this.target = target;
    }

    @Override
    public void doAction(Game game) {

        int targetId = game.getActions().size() - 1;

        while (targetId >= 0 && game.getActions().get(targetId).getGameActionTypes() != GameActionTypes.ASSUMPTION_START)
            targetId--;

        if (targetId >= 0) {
            game.setAssumptionMode(false);
            target = game.getActions().get(targetId);
            if (isValid()) {
                setGameActionTypes(GameActionTypes.ASSUMPTION_VALID);
                game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.ASSUMPTION_VALID.toString()));
            } else {
                setGameActionTypes(GameActionTypes.ASSUMPTION_CANCEL);
                target.swapCanceled();
            }
            game.redoAllAction();
        }

    }

    @Override
    public void accept(ActionVisitore visitore) {
        visitore.visit(this);
    }

    @Override
    public void swapCanceled() {
        super.swapCanceled();
        if (!isValid())
            target.swapCanceled();
    }
}
