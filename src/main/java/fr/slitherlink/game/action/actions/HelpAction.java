package fr.slitherlink.game.action.actions;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionVisitore;
import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.game.grid.Coordinates;
import fr.slitherlink.game.help.ApplicableTechnic;
import fr.slitherlink.save.technic.ListTechnic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 * @pakage fr.slitherlink.game.action
 */
public class HelpAction extends GameAction {
    private Integer technicId;
    private List<Coordinates> listCoord; // liste des coordonnées des cases à mettre en surbrillance

    public HelpAction() {
        super();
    }

    @Override
    public void accept(ActionVisitore visitore) {
        visitore.visit(this);
    }

    public Integer getTechnicId() {
        return technicId;
    }

    public void setIdTechnic(Integer technicId) {
        this.technicId = technicId;
    }

    public List<Coordinates> getListCoord() {
        return listCoord;
    }

    public void setListCoord(List<Coordinates> listCoord) {
        this.listCoord = listCoord;
    }

    private HelpAction getLastHelp(Game game){
        List<GameAction> listAction = game.getActions().stream()
            .filter( a -> a.getGameActionTypes() == GameActionTypes.NEW_HELP)
            .toList();
        if(listAction.isEmpty())
            return null;
        return (HelpAction) listAction.get(listAction.size() - 1);
    }

    @Override
    public void doAction(Game game) {
        ApplicableTechnic appTech = ApplicableTechnic.getInstance();
        HelpAction lastHelp = getLastHelp(game);
        LinkedList<Coordinates> listCoord;
        ListTechnic listTechnic;
        int nbTechnics;
        int taillePuzzle;

        // si la dernière aide demandée n'a pas été utilisée
        if(lastHelp != null && appTech.searchTech(lastHelp.getTechnicId(), lastHelp.getListCoord().get(0).getY(), lastHelp.getListCoord().get(0).getX(), game) != null){
            //TODO surlignage des cases
            //envoi de l'evenement pour la vue
            setGameActionTypes(GameActionTypes.HIGHLIGTH_HELP);
            game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.HIGHLIGTH_HELP.toString()));
        } else{
            nbTechnics = appTech.getNbTechnics();
            for(int i = 1; i <= nbTechnics; i++){
                taillePuzzle = game.getSolution().getSize();
                for(int y = 0; y < taillePuzzle; y++)
                    for(int x = 0; x < taillePuzzle; x++)
                        if( (listCoord = appTech.searchTech(i, y, x, game)) != null){
                            technicId = i;
                            this.listCoord = listCoord;
                            //envoi de l'evenement pour la vue
                            setGameActionTypes(GameActionTypes.NEW_HELP);
                            game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.NEW_HELP.toString()));
                            return ;
                        }
            }
            
            //si aucune aide n'a été trouvée
            game.getActions().remove(this);
            JOptionPane.showMessageDialog(null, "Aucune aide disponible", "Aide", JOptionPane.INFORMATION_MESSAGE);
        }

    }



}
