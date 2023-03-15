package fr.slitherlink.game.action;

import fr.slitherlink.game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 * @pakage fr.slitherlink.game.action
 */
public class HintAction extends GameAction{


    @Override
    public void doAction(Game game) {
        //TODO Antoine : faire la recherche d'indice

        //envoi de l'evenement pour la vue
        game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,GameActionTypes.HINT.toString()));
    }
}
