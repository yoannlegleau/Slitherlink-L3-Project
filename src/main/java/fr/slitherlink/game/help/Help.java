package fr.slitherlink.game.help;

import java.util.List;
/**
 * Classe représentant une aide demandée par le joueur
 * @author Bruneau Antoine
 * @version 1, 03/03/2023
 */
public class Help {
    
    private Technic technic;
    private List<Coordinates> listCoord; // liste des coordonnées des cases à mettre en surbrillance

    public Help(Technic technic, List<Coordinates> listCoord) {
        this.technic = technic;
        this.listCoord = listCoord;
    }

    public Technic getTechnic() {
        return technic;
    }

    public List<Coordinates> getListCoord() {
        return listCoord;
    }
}
