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


    public AssumptionStop(){
        super();
        setGameActionTypes(GameActionTypes.ASSUMPTION_STOP);
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

    @Override
    public void doAction(Game game) {
        //TODO metre la ligique dans le start
        game.setAssumptionMode(false);
        game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.ASSUMPTION_STOP.toString()));
    }

    @Override
    public void accept(ActionVisitore visitore) {
        visitore.visit(this);
    }
}
