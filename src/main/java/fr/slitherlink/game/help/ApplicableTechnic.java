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
            
            if (!coordExist(x-1, y, size) && !coordExist(x, y-1, size)
                && !(route.getCell(x, y+1).getLeft().isLine() && route.getCell(x+1, y).getTop().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (!coordExist(x, y-1, size) && !coordExist(x+1, y, size)
                && !(route.getCell(x-1, y).getTop().isLine() && route.getCell(x, y+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (!coordExist(x+1, y, size) && !coordExist(x, y+1, size)
                && !(route.getCell(x, y-1).getRight().isLine() && route.getCell(x-1, y).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (!coordExist(x, y+1, size) && !coordExist(x-1, y, size)
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

            if (!coordExist(x-1, y, size) && !coordExist(x, y-1, size)
                && !(route.getCell(x, y).getLeft().isLine() && route.getCell(x, y).getTop().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (!coordExist(x, y-1, size) && !coordExist(x+1, y, size)
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (!coordExist(x+1, y, size) && !coordExist(x, y+1, size)
                && !(route.getCell(x, y).getRight().isLine() && route.getCell(x, y).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (!coordExist(x, y+1, size) && !coordExist(x-1, y, size)
                && !(route.getCell(x, y).getBottom().isLine() && route.getCell(x, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else
                return false;

            return true;
        }
        return false;
    }

    /**
     * Regarde si la technique 8 est valide dans la direction Sud-Ouest
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si le patern est valide, false sinon
     */
    private boolean searchTech8PosSW(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        int xSave = x, ySave = y;

        if (coordExist(x, y, size) && gridNumber[x][y] == 3 && gridNumber[x-1][y+1] == 2){
            listCoord.add(new Coordinates(x, y));

            while(coordExist(x-1, y+1, size) && gridNumber[x-1][y+1] == 2){
                listCoord.add(new Coordinates(x-1, y+1));
                x--; y++;
            }
            if(coordExist(x-1, y+1, size) && gridNumber[x-1][y+1] == 3
                && route.getCell(x-1, y+1).getLeft().isLine() && route.getCell(x-1, y+1).getBottom().isLine() && route.getCell(xSave, ySave).getLeft().isLine() && route.getCell(xSave, ySave).getTop().isLine() ){
                    listCoord.add(new Coordinates(x-1, y+1));
                    return true;
            }else{
                listCoord.clear();
                return false;
            }
        }
        return false;
    }

    /**
     * Regarde si la technique 8 est valide dans la direction Sud-Est
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si le patern est valide, false sinon
     */
    private boolean searchTech8PosSE(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        int xSave = x, ySave = y;

        if (coordExist(x, y, size) && gridNumber[x][y] == 3 && gridNumber[x+1][y+1] == 2){
            listCoord.add(new Coordinates(x, y));
            
            while(coordExist(x+1, y+1, size) && gridNumber[x+1][y+1] == 2){
                listCoord.add(new Coordinates(x+1, y+1));
                x++; y++;
            }
            if(coordExist(x+1, y+1, size) && gridNumber[x+1][y+1] == 3
                && !(route.getCell(x+1, y+1).getRight().isLine() && route.getCell(x+1, y+1).getBottom().isLine() && route.getCell(xSave, ySave).getTop().isLine() && route.getCell(xSave, ySave).getLeft().isLine() ) ){
                    listCoord.add(new Coordinates(x+1, y+1));
                    return true;
            }else{
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

            if (searchTech8PosSW(x, y, gridNumber, route, size, listCoord))
                    return true;
            else if (searchTech8PosSE(x, y, gridNumber, route, size, listCoord))
                    return true;
            else
                return false;
        }
        return false;
    }
    
    /**
     * [Coin d'un 3 barré]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech9Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 3){

            if (coordExist(x-1, y-1, size) && (route.getCell(x-1, y-1).getBottom().isCross() && route.getCell(x-1, y-1).getRight().isCross())
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (coordExist(x+1, y-1, size) && (route.getCell(x+1, y-1).getBottom().isCross() && route.getCell(x+1, y-1).getLeft().isCross())
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (coordExist(x+1, y+1, size) && (route.getCell(x+1, y+1).getTop().isCross() && route.getCell(x+1, y+1).getLeft().isCross())
                && !(route.getCell(x, y).getBottom().isLine() && route.getCell(x, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if (coordExist(x-1, y+1, size) && (route.getCell(x-1, y+1).getTop().isCross() && route.getCell(x-1, y+1).getRight().isCross())
                && !(route.getCell(x, y).getBottom().isLine() && route.getCell(x, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else
                return false;

            return true;
        }
        return false;
    }

    /**
     * [3 atteint]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech10Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 3){

            if ((coordExist(x-1, y, size) && route.getCell(x-1, y).getTop().isLine()) || (coordExist(x, y-1, size) && route.getCell(x, y-1).getLeft().isLine())
                && !(route.getCell(x, y).getBottom().isLine() && route.getCell(x, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if ((coordExist(x, y-1, size) && route.getCell(x, y-1).getRight().isLine()) || (coordExist(x+1, y, size) && route.getCell(x+1, y).getTop().isLine())
                && !(route.getCell(x, y).getBottom().isLine() && route.getCell(x, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if ((coordExist(x+1, y, size) && route.getCell(x+1, y).getBottom().isLine()) || (coordExist(x, y+1, size) && route.getCell(x, y+1).getRight().isLine())
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else if ((coordExist(x, y+1, size) && route.getCell(x, y+1).getLeft().isLine()) || (coordExist(x-1, y, size) && route.getCell(x-1, y).getBottom().isLine())
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x, y));
            else
                return false;

            return true;
        }
        return false;
    }

    /**
     * Regarde si la technique 11 est valide dans la direction Nord-Ouest
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si le patern est valide, false sinon
     */
    private boolean searchTech11PosNW(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x, y, size) && gridNumber[x][y] == 2
            && (coordExist(x+1, y, size) && route.getCell(x+1, y).getBottom().isLine()) || (coordExist(x, y+1, size) && route.getCell(x, y+1).getRight().isLine()) ){
            listCoord.add(new Coordinates(x, y));

            while(coordExist(x-1, y-1, size) && gridNumber[x-1][y-1] == 2){
                listCoord.add(new Coordinates(x-1, y-1));
                x--; y--;
            }
            if(coordExist(x-1, y-1, size) && gridNumber[x-1][y-1] == 3
                && !(route.getCell(x-1, y-1).getLeft().isLine() && route.getCell(x-1, y-1).getTop().isLine()) ){
                listCoord.add(new Coordinates(x-1, y-1));
                return true;
            }else{
                listCoord.clear();
                return false;
            }
        }
        return false;
    }

    /**
     * Regarde si la technique 11 est valide dans la direction Nord-Est
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si le patern est valide, false sinon
     */
    private boolean searchTech11PosNE(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x, y, size) && gridNumber[x][y] == 2
            && (coordExist(x, y+1, size) && route.getCell(x, y+1).getLeft().isLine()) || (coordExist(x-1, y, size) && route.getCell(x-1, y).getBottom().isLine()) ){
            listCoord.add(new Coordinates(x, y));

            while(coordExist(x+1, y-1, size) && gridNumber[x+1][y-1] == 2){
                listCoord.add(new Coordinates(x+1, y-1));
                x++; y--;
            }
            if(coordExist(x+1, y-1, size) && gridNumber[x+1][y-1] == 3
                && !(route.getCell(x+1, y-1).getTop().isLine() && route.getCell(x+1, y-1).getRight().isLine()) ){
                listCoord.add(new Coordinates(x+1, y-1));
                return true;
            }else{
                listCoord.clear();
                return false;
            }
        }
        return false;
    }

    /**
     * Regarde si la technique 11 est valide dans la direction Sud-Est
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si le patern est valide, false sinon
     */
    private boolean searchTech11PosSE(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x, y, size) && gridNumber[x][y] == 2
            && (coordExist(x-1, y, size) && route.getCell(x-1, y).getTop().isLine()) || (coordExist(x, y-1, size) && route.getCell(x, y-1).getLeft().isLine()) ){
            listCoord.add(new Coordinates(x, y));

            while(coordExist(x+1, y+1, size) && gridNumber[x+1][y+1] == 2){
                listCoord.add(new Coordinates(x+1, y+1));
                x++; y++;
            }
            if(coordExist(x+1, y+1, size) && gridNumber[x+1][y+1] == 3
                && !(route.getCell(x+1, y+1).getBottom().isLine() && route.getCell(x+1, y+1).getRight().isLine()) ){
                listCoord.add(new Coordinates(x+1, y+1));
                return true;
            }else{
                listCoord.clear();
                return false;
            }
        }
        return false;
    }

    /**
     * Regarde si la technique 11 est valide dans la direction Sud-Ouest
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si le patern est valide, false sinon
     */
    private boolean searchTech11PosSW(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x, y, size) && gridNumber[x][y] == 2
            && (coordExist(x, y-1, size) && route.getCell(x, y-1).getRight().isLine()) || (coordExist(x+1, y, size) && route.getCell(x+1, y).getTop().isLine()) ){
            listCoord.add(new Coordinates(x, y));

            while(coordExist(x-1, y+1, size) && gridNumber[x-1][y+1] == 2){
                listCoord.add(new Coordinates(x-1, y+1));
                x--; y++;
            }
            if(coordExist(x-1, y+1, size) && gridNumber[x-1][y+1] == 3
                && !(route.getCell(x-1, y+1).getLeft().isLine() && route.getCell(x-1, y+1).getBottom().isLine()) ){
                listCoord.add(new Coordinates(x-1, y+1));
                return true;
            }else{
                listCoord.clear();
                return false;
            }
        }
        return false;
    }

    /**
     * [brochette de 2 atteignant un 3]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech11Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 2){
            
            if (searchTech11PosNW(x, y, gridNumber, route, size, listCoord))
                return true;
            else if (searchTech11PosNE(x, y, gridNumber, route, size, listCoord))
                return true;
            else if (searchTech11PosSE(x, y, gridNumber, route, size, listCoord))
                return true;
            else if (searchTech11PosSW(x, y, gridNumber, route, size, listCoord))
                return true;
        }
        return false;
    }

    /**
     * [3 & 2 diagonaux]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech12Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 2){
            listCoord.add(new Coordinates(x, y));

            if (route.getCell(x, y).getBottom().isCross() && coordExist(x-1, y-1, size) && gridNumber[x-1][y-1] == 3
                && !(route.getCell(x-1, y-1).getTop().isLine() && route.getCell(x-1, y-1).getLeft().isLine() && route.getCell(x, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x-1, y-1));
            else if (route.getCell(x, y).getRight().isCross() && coordExist(x-1, y-1, size) && gridNumber[x-1][y-1] == 3
                && !(route.getCell(x-1, y-1).getTop().isLine() && route.getCell(x-1, y-1).getLeft().isLine() && route.getCell(x, y).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x-1, y-1));
            else if (route.getCell(x, y).getLeft().isCross() && coordExist(x+1, y-1, size) && gridNumber[x+1][y-1] == 3
                && !(route.getCell(x+1, y-1).getTop().isLine() && route.getCell(x+1, y-1).getRight().isLine() && route.getCell(x, y).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x+1, y-1));
            else if (route.getCell(x, y).getBottom().isCross() && coordExist(x+1, y-1, size) && gridNumber[x+1][y-1] == 3
                && !(route.getCell(x+1, y-1).getTop().isLine() && route.getCell(x+1, y-1).getRight().isLine() && route.getCell(x, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x+1, y-1));
            else if (route.getCell(x, y).getLeft().isCross() && coordExist(x+1, y+1, size) && gridNumber[x+1][y+1] == 3
                && !(route.getCell(x+1, y+1).getBottom().isLine() && route.getCell(x+1, y+1).getRight().isLine() && route.getCell(x, y).getTop().isLine()) )
                    listCoord.add(new Coordinates(x+1, y+1));
            else if (route.getCell(x, y).getTop().isCross() && coordExist(x+1, y+1, size) && gridNumber[x+1][y+1] == 3
                && !(route.getCell(x+1, y+1).getBottom().isLine() && route.getCell(x+1, y+1).getRight().isLine() && route.getCell(x, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x+1, y+1));
            else if (route.getCell(x, y).getTop().isCross() && coordExist(x-1, y+1, size) && gridNumber[x-1][y+1] == 3
                && !(route.getCell(x-1, y+1).getLeft().isLine() && route.getCell(x-1, y+1).getBottom().isLine() && route.getCell(x, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x-1, y+1));
            else if (route.getCell(x, y).getRight().isCross() && coordExist(x-1, y+1, size) && gridNumber[x-1][y+1] == 3
                && !(route.getCell(x-1, y+1).getLeft().isLine() && route.getCell(x-1, y+1).getBottom().isLine() && route.getCell(x, y).getTop().isLine()) )
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
     * [3 & 2 adjacents]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech13Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 2){
            listCoord.add(new Coordinates(x, y));

            if (coordExist(x-1, y-1, size) && route.getCell(x-1, y-1).getBottom().isCross() && route.getCell(x-1, y-1).getRight().isCross() && coordExist(x, y+1, size) && gridNumber[x][y+1] == 3
                && !(route.getCell(x, y+1).getLeft().isLine() && route.getCell(x, y+1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x, y+1));
            else if (coordExist(x-1, y-1, size) && route.getCell(x-1, y-1).getBottom().isCross() && route.getCell(x-1, y-1).getRight().isCross() && coordExist(x+1, y, size) && gridNumber[x+1][y] == 3
                && !(route.getCell(x+1, y).getTop().isLine() && route.getCell(x+1, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x+1, y));
            else if (coordExist(x+1, y-1, size) && route.getCell(x+1, y-1).getLeft().isCross() && route.getCell(x+1, y-1).getBottom().isCross() && coordExist(x-1, y, size) && gridNumber[x-1][y] == 3
                && !(route.getCell(x-1, y).getLeft().isLine() && route.getCell(x-1, y).getTop().isLine()) )
                    listCoord.add(new Coordinates(x-1, y));
            else if (coordExist(x+1, y-1, size) && route.getCell(x+1, y-1).getLeft().isCross() && route.getCell(x+1, y-1).getBottom().isCross() && coordExist(x, y+1, size) && gridNumber[x][y+1] == 3
                && !(route.getCell(x, y+1).getBottom().isLine() && route.getCell(x, y+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x, y+1));
            else if (coordExist(x+1, y+1, size) && route.getCell(x+1, y+1).getLeft().isCross() && route.getCell(x+1, y+1).getTop().isCross() && coordExist(x, y-1, size) && gridNumber[x][y-1] == 3
                && !(route.getCell(x, y-1).getTop().isLine() && route.getCell(x, y-1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x, y-1));
            else if (coordExist(x+1, y+1, size) && route.getCell(x+1, y+1).getLeft().isCross() && route.getCell(x+1, y+1).getTop().isCross() && coordExist(x-1, y, size) && gridNumber[x-1][y] == 3
                && !(route.getCell(x-1, y).getLeft().isLine() && route.getCell(x-1, y).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x-1, y));
            else if (coordExist(x-1, y+1, size) && route.getCell(x-1, y+1).getTop().isCross() && route.getCell(x-1, y+1).getRight().isCross() && coordExist(x+1, y, size) && gridNumber[x+1][y] == 3
                && !(route.getCell(x+1, y).getBottom().isLine() && route.getCell(x+1, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x+1, y));
            else if (coordExist(x-1, y+1, size) && route.getCell(x-1, y+1).getTop().isCross() && route.getCell(x-1, y+1).getRight().isCross() && coordExist(x, y-1, size) && gridNumber[x][y-1] == 3
                && !(route.getCell(x, y-1).getLeft().isLine() && route.getCell(x, y-1).getTop().isLine()) )
                    listCoord.add(new Coordinates(x, y-1));
            else{
                listCoord.clear();
                return false;
            }

            return true;
        }
        return false;
    }

    /**
     * [3 & 2 adjacents bis]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech14Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 2){
            listCoord.add(new Coordinates(x, y));

            if (route.getCell(x, y).getTop().isCross() && coordExist(x, y+1, size) && gridNumber[x][y+1] == 3
                && !(route.getCell(x, y+1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x, y+1));
            else if (route.getCell(x, y).getRight().isCross() && coordExist(x-1, y, size) && gridNumber[x-1][y] == 3
                && !(route.getCell(x-1, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(x-1, y));
            else if (route.getCell(x, y).getBottom().isCross() && coordExist(x, y-1, size) && gridNumber[x][y-1] == 3
                && !(route.getCell(x, y-1).getTop().isLine()) )
                    listCoord.add(new Coordinates(x, y-1));
            else if (route.getCell(x, y).getLeft().isCross() && coordExist(x+1, y, size) && gridNumber[x+1][y] == 3
                && !(route.getCell(x+1, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(x+1, y));
            else{
                listCoord.clear();
                return false;
            }

            return true;
        }
        return false;
    }

    /**
     * [3 & 1 diagonaux]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech15Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 1){
            listCoord.add(new Coordinates(x, y));

            if (route.getCell(x, y).getLeft().isCross() && route.getCell(x, y).getTop().isCross() && coordExist(x+1, y+1, size) && gridNumber[x+1][y+1] == 3
                && !(route.getCell(x+1, y+1).getBottom().isLine() && route.getCell(x+1, y+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x+1, y+1));
            else if (route.getCell(x, y).getTop().isCross() && route.getCell(x, y).getRight().isCross() && coordExist(x-1, y+1, size) && gridNumber[x-1][y+1] == 3
                && !(route.getCell(x-1, y+1).getLeft().isLine() && route.getCell(x-1, y+1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(x-1, y+1));
            else if (route.getCell(x, y).getRight().isCross() && route.getCell(x, y).getBottom().isCross() && coordExist(x-1, y-1, size) && gridNumber[x-1][y-1] == 3
                && !(route.getCell(x-1, y-1).getLeft().isLine() && route.getCell(x-1, y-1).getTop().isLine()) )
                    listCoord.add(new Coordinates(x-1, y-1));
            else if (route.getCell(x, y).getBottom().isCross() && route.getCell(x, y).getLeft().isCross() && coordExist(x+1, y-1, size) && gridNumber[x+1][y-1] == 3
                && !(route.getCell(x+1, y-1).getTop().isLine() && route.getCell(x+1, y-1).getRight().isLine()) )
                    listCoord.add(new Coordinates(x+1, y-1));
            else{
                listCoord.clear();
                return false;
            }

            return true;
        }
        return false;
    }

    /**
     * Regarde si la technique 16 est valide dans la direction Nord-Ouest
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si le patern est valide, false sinon
     */
    private boolean searchTech16PosNW(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x+1, y+1, size) && ((route.getCell(x+1, y+1).getLeft().isLine() && route.getCell(x+1, y+1).getTop().isCross()) || (route.getCell(x+1, y+1).getLeft().isCross() && route.getCell(x+1, y+1).getTop().isLine()) ) ){
            
            while(coordExist(x, y, size) && gridNumber[x][y] == 2){
                listCoord.add(new Coordinates(x, y));
                x--; y--;
                if (coordExist(x, y, size) && ( (route.getCell(x, y).getBottom().isCross() && route.getCell(x, y).getRight().isEmpty()) || (route.getCell(x, y).getBottom().isEmpty() && route.getCell(x, y).getRight().isCross()) ) )
                    return true;
            }
            listCoord.clear();
        }
        return false;
    }

    /**
     * [brochette de 2]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech16Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(x,y, size) && gridNumber[x][y] == 2){
            
            if (searchTech16PosNW(x, y, gridNumber, route, size, listCoord))
                return true;
            else if (searchTech16PosNE(x, y, gridNumber, route, size, listCoord))
                return true;
            else if (searchTech16PosSE(x, y, gridNumber, route, size, listCoord))
                return true;
            else if (searchTech16PosSW(x, y, gridNumber, route, size, listCoord))
                return true;
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
