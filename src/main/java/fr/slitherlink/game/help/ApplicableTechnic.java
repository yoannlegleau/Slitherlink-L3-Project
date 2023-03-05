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
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech1Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 0){
            listCoord.add(new Coordinates(x, y));

            if (coordExist(x, y-1, size) && (gridNumber[x][y-1] == 3)
                && !(route.getCell(x, y-1).getTop().isLine() && route.getCell(x, y-1).getRight().isLine() && route.getCell(x, y-1).getLeft().isLine() && route.getCell(x-1, y-1).getBottom().isLine() && route.getCell(x+1, y-1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x, y-1));
            else if (coordExist(x+1, y, size) && (gridNumber[x+1][y] == 3)
                && !(route.getCell(x+1, y).getRight().isLine() && route.getCell(x+1, y).getTop().isLine() && route.getCell(x+1, y).getBottom().isLine() && route.getCell(x+1, y-1).getLeft().isLine() && route.getCell(x+1, y+1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x+1, y));
            else if (coordExist(x, y+1, size) && (gridNumber[x][y+1] == 3)
                && !(route.getCell(x, y+1).getBottom().isLine() && route.getCell(x, y+1).getRight().isLine() && route.getCell(x, y+1).getLeft().isLine() && route.getCell(x-1, y+1).getTop().isLine() && route.getCell(x+1, y+1).getTop().isLine()) )
                    listCoord.add(new Coordinates(x, y+1));
            else if (coordExist(x-1, y, size) && (gridNumber[x-1][y] == 3)
                && !(route.getCell(x-1, y).getLeft().isLine() && route.getCell(x-1, y).getTop().isLine() && route.getCell(x-1, y).getBottom().isLine() && route.getCell(x-1, y-1).getRight().isLine() && route.getCell(x-1, y+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x-1, y));
            else{
                listCoord.clear();
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * [Technique 0 & 3 diagonaux]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech2Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 0){
            listCoord.add(new Coordinates(x, y));

            if (coordExist(x-1, y-1, size) && (gridNumber[x-1][y-1] == 3)
                && !(route.getCell(x-1, y-1).getBottom().isLine() && route.getCell(x-1, y-1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x-1, y-1));
            else if (coordExist(x+1, y-1, size) && (gridNumber[x+1][y-1] == 3)
                && !(route.getCell(x+1, y-1).getBottom().isLine() && route.getCell(x+1, y-1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x+1, y-1));
            else if (coordExist(x+1, y+1, size) && (gridNumber[x+1][y+1] == 3)
                && !(route.getCell(x+1, y+1).getTop().isLine() && route.getCell(x+1, y+1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x+1, y+1));
            else if (coordExist(x-1, y+1, size) && (gridNumber[x-1][y+1] == 3)
                && !(route.getCell(x-1, y+1).getTop().isLine() && route.getCell(x-1, y+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x-1, y+1));
            else{
                listCoord.clear();
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * [Technique 3 & 3 adjacents]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech3Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 3){
            listCoord.add(new Coordinates(x, y));

            if (coordExist(x+1, y, size) && (gridNumber[x+1][y] == 3)
                && !(route.getCell(x, y).getLeft().isLine() && route.getCell(x, y).getRight().isLine() && route.getCell(x+1, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x+1, y));
            else if (coordExist(x, y+1, size) && (gridNumber[x][y+1] == 3)
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getBottom().isLine() && route.getCell(x, y+1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x, y+1));
            else{
                listCoord.clear();
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * [Technique 3 & 3 diagonaux]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech4Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 3){
            listCoord.add(new Coordinates(x, y));

            if (coordExist(x-1, y+1, size) && (gridNumber[x-1][y+1] == 3)
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getRight().isLine() && route.getCell(x-1, y+1).getBottom().isLine() && route.getCell(x-1, y+1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x-1, y+1));
            else if (coordExist(x+1, y+1, size) && (gridNumber[x+1][y+1] == 3)
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getLeft().isLine() && route.getCell(x+1, y+1).getBottom().isLine() && route.getCell(x+1, y+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x+1, y+1));
            else{
                listCoord.clear();
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * [Coin d'un 2 tracé]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech5Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 2){

            if (coordExist(x-1, y-1, size) && (route.getCell(x-1, y-1).getBottom().isLine() && route.getCell(x-1, y-1).getRight().isLine())
                && !(route.getCell(x, y).getBottom().isLine() && route.getCell(x, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (coordExist(x+1, y-1, size) && (route.getCell(x+1, y-1).getBottom().isLine() && route.getCell(x+1, y-1).getLeft().isLine())
                && !(route.getCell(x, y).getBottom().isLine() && route.getCell(x, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (coordExist(x+1, y+1, size) && (route.getCell(x+1, y+1).getTop().isLine() && route.getCell(x+1, y+1).getLeft().isLine())
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (coordExist(x-1, y+1, size) && (route.getCell(x-1, y+1).getTop().isLine() && route.getCell(x-1, y+1).getRight().isLine())
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else
                return false;

            return true;
        }
        return false;
    }

    /**
     * [2 en coin]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech6Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 2){
            
            if (!coordExist(x-1, y-1, size)
                && !(route.getCell(x, y+1).getLeft().isLine() && route.getCell(x+1, y).getTop().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (!coordExist(x+1, y-1, size)
                && !(route.getCell(x-1, y).getTop().isLine() && route.getCell(x, y+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (!coordExist(x+1, y+1, size)
                && !(route.getCell(x, y-1).getRight().isLine() && route.getCell(x-1, y).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (!coordExist(x-1, y+1, size)
                && !(route.getCell(x+1, y).getBottom().isLine() && route.getCell(x, y-1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else
                return false;

            return true;
        }
        return false;
    }
    
    /**
     * [3 en coin]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech7Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 3){

            if (!coordExist(x-1, y-1, size)
                && !(route.getCell(x, y).getLeft().isLine() && route.getCell(x, y).getTop().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (!coordExist(x+1, y-1, size)
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (!coordExist(x+1, y+1, size)
                && !(route.getCell(x, y).getRight().isLine() && route.getCell(x, y).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (!coordExist(x-1, y+1, size)
                && !(route.getCell(x, y).getBottom().isLine() && route.getCell(x, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else
                return false;

            return true;
        }
        return false;
    }

    /**
     * Regarde si le patern de nombre est valide pour la technique 8 dans la direction Sud-Ouest
     * @param gridNumber la grille de nombres
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si le patern est valide, false sinon
     */
    private boolean searchTech8SW(int x, int y, Integer [][] gridNumber, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x, y, size)){
            if (gridNumber[x][y] == 3){
                listCoord.add(new Coordinates(x, y));
                if(listCoord.size() == 1) //Si on est sur le premier 3, on continue la recherche
                    return searchTech8SW(x-1, y+1, gridNumber, size, listCoord);
                else 
                    return true;
            } else if (gridNumber[x][y] == 2){
                listCoord.add(new Coordinates(x, y));
                return searchTech8SW(x-1, y+1, gridNumber, size, listCoord);
            } else {
                listCoord.clear();
                return false;
            }
        }
        return false;
    }

    /**
     * Regarde si le patern de nombre est valide pour la technique 8 dans la direction Sud-Est
     * @param gridNumber la grille de nombres
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si le patern est valide, false sinon
     */
    private boolean searchTech8SE(int x, int y, Integer [][] gridNumber, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x, y, size)){
            if (gridNumber[x][y] == 3){
                listCoord.add(new Coordinates(x, y));
                if(listCoord.size() == 1) //Si on est sur le premier 3, on continue la recherche
                    return searchTech8SW(x+1, y+1, gridNumber, size, listCoord);
                else 
                    return true;
            } else if (gridNumber[x][y] == 2){
                listCoord.add(new Coordinates(x, y));
                return searchTech8SW(x+1, y+1, gridNumber, size, listCoord);
            } else {
                listCoord.clear();
                return false;
            }
        }
        return false;
    }

    /**
     * [3 & 3 diagnonaux avec des 2 entre]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech8Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 3){

            if (searchTech8SW(x, y, gridNumber, size, listCoord)
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getRight().isLine() && route.getCell(listCoord.getLast().getX(),listCoord.getLast().getY()).getLeft().isLine() && route.getCell(listCoord.getLast().getX(),listCoord.getLast().getY()).getBottom().isLine()))
                    return true;
            else if (searchTech8SE(x, y, gridNumber, size, listCoord)
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getLeft().isLine() && route.getCell(listCoord.getLast().getX(),listCoord.getLast().getY()).getRight().isLine() && route.getCell(listCoord.getLast().getX(),listCoord.getLast().getY()).getBottom().isLine()))
                    return true;
            else
                return false;
        }
        return false;
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
