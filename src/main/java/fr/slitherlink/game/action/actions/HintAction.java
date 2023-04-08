package fr.slitherlink.game.action.actions;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionVisitore;
import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.game.grid.Coordinates;
import fr.slitherlink.game.help.ApplicableTechnic;
import fr.slitherlink.game.help.Help;
import fr.slitherlink.save.technic.ListTechnic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

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
        ApplicableTechnic appTech = ApplicableTechnic.getInstance();
        Help lastHelp = game.getHelp();
        LinkedList<Coordinates> listCoord;
        ListTechnic listTechnic;
        int nbTechnics;
        int taillePuzzle;

        // si la dernière aide demandée n'a pas été utilisée
        if(lastHelp != null && appTech.searchTech(lastHelp.getTechnic().getId(), lastHelp.getListCoord().get(0).getY(), lastHelp.getListCoord().get(0).getX(), game) != null){
            //surlignage des cases
            game.incrementNbHint();
        } else{

            listTechnic = ListTechnic.getInstance();
            nbTechnics = appTech.getNbTechnics();

            for(int i = 1; i <= nbTechnics; i++){
                taillePuzzle = game.getSolution().getSize();
                for(int y = 0; y < taillePuzzle; y++)
                    for(int x = 0; x < taillePuzzle; x++)
                        if( (listCoord = appTech.searchTech(i, y, x, game)) != null){
                            game.setHelp(new Help(listTechnic.getTechnic(i), listCoord));
                            game.incrementNbHint();
                            return ;
                        }
            }
            
            //si aucune aide n'a été trouvée
            //decrementer le nombre d'aide
            JOptionPane.showMessageDialog(null, "Aucune aide disponible", "Aide", JOptionPane.INFORMATION_MESSAGE);
        }

        //envoi de l'evenement pour la vue
        game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.HINT.toString()));
    }



}
