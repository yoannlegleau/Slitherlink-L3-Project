package fr.slitherlink.game.help;

import java.util.List;
import java.util.LinkedList;
import fr.slitherlink.game.grid.Grid;

/**
 * Classe contenant les méthodes pour rechercher les techniques applicables dans la grille
 * @author Bruneau Antoine
 * @version 1, 04/03/2023
 */
public class ApplicableTechnic {
    
    /**
     * Vérifie si la case de coordonnées x, y existe dans la grille
     * @param size la taille de la grille
     */
    private boolean coordExist(int x, int y, int size) {
        return (x >= 0 && x < size && y >= 0 && y < size);
    }
    
    /**
     * [Technique 0 & 3 adjacents]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return la liste des coordonnées des cases à mettre en surbrillance si la technique est applicable, null sinon
     */
    private LinkedList<Coordinates> searchTech1Pos(int x, int y, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 0){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

            if (coordExist(x, y-1, size) && (gridNumber[x][y-1] == 3)){
                if ( !(route.getCell(x, y-1).getTop().isLine() && route.getCell(x, y-1).getRight().isLine() && route.getCell(x, y-1).getLeft().isLine() && route.getCell(x-1, y-1).getBottom().isLine() && route.getCell(x+1, y-1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x, y-1));
            } else if (coordExist(x+1, y, size) && (gridNumber[x+1][y] == 3)){
                if ( !(route.getCell(x+1, y).getRight().isLine() && route.getCell(x+1, y).getTop().isLine() && route.getCell(x+1, y).getBottom().isLine() && route.getCell(x+1, y-1).getLeft().isLine() && route.getCell(x+1, y+1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x+1, y));
            } else if (coordExist(x, y+1, size) && (gridNumber[x][y+1] == 3)){
                if ( !(route.getCell(x, y+1).getBottom().isLine() && route.getCell(x, y+1).getRight().isLine() && route.getCell(x, y+1).getLeft().isLine() && route.getCell(x-1, y+1).getTop().isLine() && route.getCell(x+1, y+1).getTop().isLine()) )
                    listCoord.add(new Coordinates(x, y+1));
            } else if (coordExist(x-1, y, size) && (gridNumber[x-1][y] == 3))
                if ( !(route.getCell(x-1, y).getLeft().isLine() && route.getCell(x-1, y).getTop().isLine() && route.getCell(x-1, y).getBottom().isLine() && route.getCell(x-1, y-1).getRight().isLine() && route.getCell(x-1, y+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x-1, y));
            
            if(!listCoord.isEmpty()){
                listCoord.addFirst(new Coordinates(x, y));
                return listCoord;
            }
        }
        return null;
    }

    /**
     * [Technique 0 & 3 diagonaux]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return la liste des coordonnées des cases à mettre en surbrillance si la technique est applicable, null sinon
     */
    private LinkedList<Coordinates> searchTech2Pos(int x, int y, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 0){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

            if (coordExist(x-1, y-1, size) && (gridNumber[x-1][y-1] == 3)){
                if ( !(route.getCell(x-1, y-1).getBottom().isLine() && route.getCell(x-1, y-1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x-1, y-1));
            } else if (coordExist(x+1, y-1, size) && (gridNumber[x+1][y-1] == 3)){
                if ( !(route.getCell(x+1, y-1).getBottom().isLine() && route.getCell(x+1, y-1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x+1, y-1));
            } else if (coordExist(x+1, y+1, size) && (gridNumber[x+1][y+1] == 3)){
                if ( !(route.getCell(x+1, y+1).getTop().isLine() && route.getCell(x+1, y+1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x+1, y+1));
            } else if (coordExist(x-1, y+1, size) && (gridNumber[x-1][y+1] == 3))
                if ( !(route.getCell(x-1, y+1).getTop().isLine() && route.getCell(x-1, y+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x-1, y+1));

            if(!listCoord.isEmpty()){
                listCoord.addFirst(new Coordinates(x, y));
                return listCoord;
            }
        }
        return null;
    }

    /**
     * [Technique 3 & 3 adjacents]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return la liste des coordonnées des cases à mettre en surbrillance si la technique est applicable, null sinon
     */
    private LinkedList<Coordinates> searchTech3Pos(int x, int y, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 3){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

            if (coordExist(x+1, y, size) && (gridNumber[x+1][y] == 3)){
                if ( !(route.getCell(x, y).getLeft().isLine() && route.getCell(x, y).getRight().isLine() && route.getCell(x+1, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x+1, y));
            } else if (coordExist(x, y+1, size) && (gridNumber[x][y+1] == 3))
                if ( !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getBottom().isLine() && route.getCell(x, y+1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x, y+1));
            
            if(!listCoord.isEmpty()){
                listCoord.addFirst(new Coordinates(x, y));
                return listCoord;
            }
        }
        return null;
    }

    /**
     * [Technique 3 & 3 diagonaux]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return la liste des coordonnées des cases à mettre en surbrillance si la technique est applicable, null sinon
     */
    private LinkedList<Coordinates> searchTech4Pos(int x, int y, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 3){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

            if (coordExist(x-1, y+1, size) && (gridNumber[x-1][y+1] == 3)){
                if ( !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getRight().isLine() && route.getCell(x-1, y+1).getBottom().isLine() && route.getCell(x-1, y+1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x-1, y+1));
            } else if (coordExist(x+1, y+1, size) && (gridNumber[x+1][y+1] == 3))
                if ( !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getLeft().isLine() && route.getCell(x+1, y+1).getBottom().isLine() && route.getCell(x+1, y+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x+1, y+1));
            
            if(!listCoord.isEmpty()){
                listCoord.addFirst(new Coordinates(x, y));
                return listCoord;
            }
        }
        return null;
    }

    



















    /**
    private List<Coordinates> searchTech1Grid(Integer [][] gridNumber, Grid route, int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
            }
        }
        return null;
    }
    */


}
