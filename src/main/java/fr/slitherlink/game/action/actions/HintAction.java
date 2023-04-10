package fr.slitherlink.game.action.actions;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionVisitore;
import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.GameActionTypes;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 * @pakage fr.slitherlink.game.action
 */
public class HintAction extends GameAction {

    public HintAction() {
        super();
        setGameActionTypes(GameActionTypes.HINT);
    }

    @Override
    public void accept(ActionVisitore visitore) {
        visitore.visit(this);
    }

    @Override
    public void doAction(Game game) {


        //TODO Antoine : faire la recherche d'indice

        //TODO Antoine : ajouter l'utilisation si un indice est trouvé
        game.incrementNbHint();

        //envoi de l'evenement pour la vue
        game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.HINT.toString()));
    }
}
