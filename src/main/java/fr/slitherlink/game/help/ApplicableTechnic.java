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
        if (coordExist(y, x, size) && gridNumber[y][x] == 0){
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y-1, x, size) && (gridNumber[y-1][x] == 3)
                && !(route.getCell(y-1, x).getTop().isLine() && route.getCell(y-1, x).getRight().isLine() && route.getCell(y-1, x).getLeft().isLine() && route.getCell(y-1, x-1).getBottom().isLine() && route.getCell(y-1, x+1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y-1, x));
            else if (coordExist(y, x+1, size) && (gridNumber[y][x+1] == 3)
                && !(route.getCell(y, x+1).getRight().isLine() && route.getCell(y, x+1).getTop().isLine() && route.getCell(y, x+1).getBottom().isLine() && route.getCell(y-1, x+1).getLeft().isLine() && route.getCell(y+1, x+1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x+1));
            else if (coordExist(y+1, x, size) && (gridNumber[y+1][x] == 3)
                && !(route.getCell(y+1, x).getBottom().isLine() && route.getCell(y+1, x).getRight().isLine() && route.getCell(y+1, x).getLeft().isLine() && route.getCell(y+1, x-1).getTop().isLine() && route.getCell(y+1, x+1).getTop().isLine()) )
                    listCoord.add(new Coordinates(y+1, x));
            else if (coordExist(y, x-1, size) && (gridNumber[y][x-1] == 3)
                && !(route.getCell(y, x-1).getLeft().isLine() && route.getCell(y, x-1).getTop().isLine() && route.getCell(y, x-1).getBottom().isLine() && route.getCell(y-1, x-1).getRight().isLine() && route.getCell(y+1, x-1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x-1));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 0){
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y-1, x-1, size) && (gridNumber[y-1][x-1] == 3)
                && !(route.getCell(y-1, x-1).getBottom().isLine() && route.getCell(y-1, x-1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (coordExist(y-1, x+1, size) && (gridNumber[y-1][x+1] == 3)
                && !(route.getCell(y-1, x+1).getBottom().isLine() && route.getCell(y-1, x+1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y-1, x+1));
            else if (coordExist(y+1, x+1, size) && (gridNumber[y+1][x+1] == 3)
                && !(route.getCell(y+1, x+1).getTop().isLine() && route.getCell(y+1, x+1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else if (coordExist(y+1, x-1, size) && (gridNumber[y+1][x-1] == 3)
                && !(route.getCell(y+1, x-1).getTop().isLine() && route.getCell(y+1, x-1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 3){
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y, x+1, size) && (gridNumber[y][x+1] == 3)
                && !(route.getCell(y, x).getLeft().isLine() && route.getCell(y, x).getRight().isLine() && route.getCell(y, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x+1));
            else if (coordExist(y+1, x, size) && (gridNumber[y+1][x] == 3)
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getBottom().isLine() && route.getCell(y+1, x).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y+1, x));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 3){
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y+1, x-1, size) && (gridNumber[y+1][x-1] == 3)
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getRight().isLine() && route.getCell(y+1, x-1).getBottom().isLine() && route.getCell(y+1, x-1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (coordExist(y+1, x+1, size) && (gridNumber[y+1][x+1] == 3)
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getLeft().isLine() && route.getCell(y+1, x+1).getBottom().isLine() && route.getCell(y+1, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x+1));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 2){

            if (coordExist(y-1, x-1, size) && (route.getCell(y-1, x-1).getBottom().isLine() && route.getCell(y-1, x-1).getRight().isLine())
                && !(route.getCell(y, x).getBottom().isLine() && route.getCell(y, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if (coordExist(y-1, x+1, size) && (route.getCell(y-1, x+1).getBottom().isLine() && route.getCell(y-1, x+1).getLeft().isLine())
                && !(route.getCell(y, x).getBottom().isLine() && route.getCell(y, x).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if (coordExist(y+1, x+1, size) && (route.getCell(y+1, x+1).getTop().isLine() && route.getCell(y+1, x+1).getLeft().isLine())
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if (coordExist(y+1, x-1, size) && (route.getCell(y+1, x-1).getTop().isLine() && route.getCell(y+1, x-1).getRight().isLine())
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 2){
            
            if (!coordExist(y, x-1, size) && !coordExist(y-1, x, size)
                && !(route.getCell(y+1, x).getLeft().isLine() && route.getCell(y, x+1).getTop().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if (!coordExist(y-1, x, size) && !coordExist(y, x+1, size)
                && !(route.getCell(y, x-1).getTop().isLine() && route.getCell(y+1, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if (!coordExist(y, x+1, size) && !coordExist(y+1, x, size)
                && !(route.getCell(y-1, x).getRight().isLine() && route.getCell(y, x-1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if (!coordExist(y+1, x, size) && !coordExist(y, x-1, size)
                && !(route.getCell(y, x+1).getBottom().isLine() && route.getCell(y-1, x).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 3){

            if (!coordExist(y, x-1, size) && !coordExist(y-1, x, size)
                && !(route.getCell(y, x).getLeft().isLine() && route.getCell(y, x).getTop().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if (!coordExist(y-1, x, size) && !coordExist(y, x+1, size)
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if (!coordExist(y, x+1, size) && !coordExist(y+1, x, size)
                && !(route.getCell(y, x).getRight().isLine() && route.getCell(y, x).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if (!coordExist(y+1, x, size) && !coordExist(y, x-1, size)
                && !(route.getCell(y, x).getBottom().isLine() && route.getCell(y, x).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x));
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

        if (coordExist(y, x, size) && gridNumber[y][x] == 3 && gridNumber[y+1][x-1] == 2){
            listCoord.add(new Coordinates(y, x));

            while(coordExist(y+1, x-1, size) && gridNumber[y+1][x-1] == 2){
                listCoord.add(new Coordinates(y+1, x-1));
                x--; y++;
            }
            if(coordExist(y+1, x-1, size) && gridNumber[y+1][x-1] == 3
                && route.getCell(y+1, x-1).getLeft().isLine() && route.getCell(y+1, x-1).getBottom().isLine() && route.getCell(ySave, xSave).getLeft().isLine() && route.getCell(ySave, xSave).getTop().isLine() ){
                    listCoord.add(new Coordinates(y+1, x-1));
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

        if (coordExist(y, x, size) && gridNumber[y][x] == 3 && gridNumber[y+1][x+1] == 2){
            listCoord.add(new Coordinates(y, x));
            
            while(coordExist(y+1, x+1, size) && gridNumber[y+1][x+1] == 2){
                listCoord.add(new Coordinates(y+1, x+1));
                x++; y++;
            }
            if(coordExist(y+1, x+1, size) && gridNumber[y+1][x+1] == 3
                && !(route.getCell(y+1, x+1).getRight().isLine() && route.getCell(y+1, x+1).getBottom().isLine() && route.getCell(ySave, xSave).getTop().isLine() && route.getCell(ySave, xSave).getLeft().isLine() ) ){
                    listCoord.add(new Coordinates(y+1, x+1));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 3){

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
        if (coordExist(y, x, size) && gridNumber[y][x] == 3){

            if (coordExist(y-1, x-1, size) && (route.getCell(y-1, x-1).getBottom().isCross() && route.getCell(y-1, x-1).getRight().isCross())
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if (coordExist(y-1, x+1, size) && (route.getCell(y-1, x+1).getBottom().isCross() && route.getCell(y-1, x+1).getLeft().isCross())
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if (coordExist(y+1, x+1, size) && (route.getCell(y+1, x+1).getTop().isCross() && route.getCell(y+1, x+1).getLeft().isCross())
                && !(route.getCell(y, x).getBottom().isLine() && route.getCell(y, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if (coordExist(y+1, x-1, size) && (route.getCell(y+1, x-1).getTop().isCross() && route.getCell(y+1, x-1).getRight().isCross())
                && !(route.getCell(y, x).getBottom().isLine() && route.getCell(y, x).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 3){

            if ((coordExist(y, x-1, size) && route.getCell(y, x-1).getTop().isLine()) || (coordExist(y-1, x, size) && route.getCell(y-1, x).getLeft().isLine())
                && !(route.getCell(y, x).getBottom().isLine() && route.getCell(y, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if ((coordExist(y-1, x, size) && route.getCell(y-1, x).getRight().isLine()) || (coordExist(y, x+1, size) && route.getCell(y, x+1).getTop().isLine())
                && !(route.getCell(y, x).getBottom().isLine() && route.getCell(y, x).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if ((coordExist(y, x+1, size) && route.getCell(y, x+1).getBottom().isLine()) || (coordExist(y+1, x, size) && route.getCell(y+1, x).getRight().isLine())
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if ((coordExist(y+1, x, size) && route.getCell(y+1, x).getLeft().isLine()) || (coordExist(y, x-1, size) && route.getCell(y, x-1).getBottom().isLine())
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 2
            && (coordExist(y, x+1, size) && route.getCell(y, x+1).getBottom().isLine()) || (coordExist(y+1, x, size) && route.getCell(y+1, x).getRight().isLine()) ){
            listCoord.add(new Coordinates(y, x));

            while(coordExist(y-1, x-1, size) && gridNumber[y-1][x-1] == 2){
                listCoord.add(new Coordinates(y+1, x-1));
                x--; y--;
            }
            if(coordExist(y-1, x-1, size) && gridNumber[y-1][x-1] == 3
                && !(route.getCell(y-1, x-1).getLeft().isLine() && route.getCell(y-1, x-1).getTop().isLine()) ){
                listCoord.add(new Coordinates(y+1, x-1));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 2
            && (coordExist(y+1, x, size) && route.getCell(y+1, x).getLeft().isLine()) || (coordExist(y, x-1, size) && route.getCell(y, x-1).getBottom().isLine()) ){
            listCoord.add(new Coordinates(y, x));

            while(coordExist(y-1, x+1, size) && gridNumber[y-1][x+1] == 2){
                listCoord.add(new Coordinates(y-1, x+1));
                x++; y--;
            }
            if(coordExist(y-1, x+1, size) && gridNumber[y-1][x+1] == 3
                && !(route.getCell(y-1, x+1).getTop().isLine() && route.getCell(y-1, x+1).getRight().isLine()) ){
                listCoord.add(new Coordinates(y-1, x+1));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 2
            && (coordExist(y, x-1, size) && route.getCell(y, x-1).getTop().isLine()) || (coordExist(y-1, x, size) && route.getCell(y-1, x).getLeft().isLine()) ){
            listCoord.add(new Coordinates(y, x));

            while(coordExist(y+1, x+1, size) && gridNumber[y+1][x+1] == 2){
                listCoord.add(new Coordinates(y+1, x+1));
                x++; y++;
            }
            if(coordExist(y+1, x+1, size) && gridNumber[y+1][x+1] == 3
                && !(route.getCell(y+1, x+1).getBottom().isLine() && route.getCell(y+1, x+1).getRight().isLine()) ){
                listCoord.add(new Coordinates(y+1, x+1));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 2
            && (coordExist(y-1, x, size) && route.getCell(y-1, x).getRight().isLine()) || (coordExist(y, x+1, size) && route.getCell(y, x+1).getTop().isLine()) ){
            listCoord.add(new Coordinates(y, x));

            while(coordExist(y+1, x-1, size) && gridNumber[y+1][x-1] == 2){
                listCoord.add(new Coordinates(y+1, x-1));
                x--; y++;
            }
            if(coordExist(y+1, x-1, size) && gridNumber[y+1][x-1] == 3
                && !(route.getCell(y+1, x-1).getLeft().isLine() && route.getCell(y+1, x-1).getBottom().isLine()) ){
                listCoord.add(new Coordinates(y+1, x-1));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 2){
            
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 2){
            listCoord.add(new Coordinates(y, x));

            if (route.getCell(y, x).getBottom().isCross() && coordExist(y-1, x-1, size) && gridNumber[y-1][x-1] == 3
                && !(route.getCell(y-1, x-1).getTop().isLine() && route.getCell(y-1, x-1).getLeft().isLine() && route.getCell(y, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getRight().isCross() && coordExist(y-1, x-1, size) && gridNumber[y-1][x-1] == 3
                && !(route.getCell(y-1, x-1).getTop().isLine() && route.getCell(y-1, x-1).getLeft().isLine() && route.getCell(y, x).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getLeft().isCross() && coordExist(y-1, x+1, size) && gridNumber[y-1][x+1] == 3
                && !(route.getCell(y-1, x+1).getTop().isLine() && route.getCell(y-1, x+1).getRight().isLine() && route.getCell(y, x).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y-1, x+1));
            else if (route.getCell(y, x).getBottom().isCross() && coordExist(y-1, x+1, size) && gridNumber[y-1][x+1] == 3
                && !(route.getCell(y-1, x+1).getTop().isLine() && route.getCell(y-1, x+1).getRight().isLine() && route.getCell(y, x).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y-1, x+1));
            else if (route.getCell(y, x).getLeft().isCross() && coordExist(y+1, x+1, size) && gridNumber[y+1][x+1] == 3
                && !(route.getCell(y+1, x+1).getBottom().isLine() && route.getCell(y+1, x+1).getRight().isLine() && route.getCell(y, x).getTop().isLine()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else if (route.getCell(y, x).getTop().isCross() && coordExist(y+1, x+1, size) && gridNumber[y+1][x+1] == 3
                && !(route.getCell(y+1, x+1).getBottom().isLine() && route.getCell(y+1, x+1).getRight().isLine() && route.getCell(y, x).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else if (route.getCell(y, x).getTop().isCross() && coordExist(y+1, x-1, size) && gridNumber[y+1][x-1] == 3
                && !(route.getCell(y+1, x-1).getLeft().isLine() && route.getCell(y+1, x-1).getBottom().isLine() && route.getCell(y, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getRight().isCross() && coordExist(y+1, x-1, size) && gridNumber[y+1][x-1] == 3
                && !(route.getCell(y+1, x-1).getLeft().isLine() && route.getCell(y+1, x-1).getBottom().isLine() && route.getCell(y, x).getTop().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 2){
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y-1, x-1, size) && route.getCell(y-1, x-1).getBottom().isCross() && route.getCell(y-1, x-1).getRight().isCross() && coordExist(y+1, x, size) && gridNumber[y+1][x] == 3
                && !(route.getCell(y+1, x).getLeft().isLine() && route.getCell(y+1, x).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y+1, x));
            else if (coordExist(y-1, x-1, size) && route.getCell(y-1, x-1).getBottom().isCross() && route.getCell(y-1, x-1).getRight().isCross() && coordExist(y, x+1, size) && gridNumber[y][x+1] == 3
                && !(route.getCell(y, x+1).getTop().isLine() && route.getCell(y, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x+1));
            else if (coordExist(y-1, x+1, size) && route.getCell(y-1, x+1).getLeft().isCross() && route.getCell(y-1, x+1).getBottom().isCross() && coordExist(y, x-1, size) && gridNumber[y][x-1] == 3
                && !(route.getCell(y, x-1).getLeft().isLine() && route.getCell(y, x-1).getTop().isLine()) )
                    listCoord.add(new Coordinates(y, x-1));
            else if (coordExist(y-1, x+1, size) && route.getCell(y-1, x+1).getLeft().isCross() && route.getCell(y-1, x+1).getBottom().isCross() && coordExist(y+1, x, size) && gridNumber[y+1][x] == 3
                && !(route.getCell(y+1, x).getBottom().isLine() && route.getCell(y+1, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x));
            else if (coordExist(y+1, x+1, size) && route.getCell(y+1, x+1).getLeft().isCross() && route.getCell(y+1, x+1).getTop().isCross() && coordExist(y-1, x, size) && gridNumber[y-1][x] == 3
                && !(route.getCell(y-1, x).getTop().isLine() && route.getCell(y-1, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y-1, x));
            else if (coordExist(y+1, x+1, size) && route.getCell(y+1, x+1).getLeft().isCross() && route.getCell(y+1, x+1).getTop().isCross() && coordExist(y, x-1, size) && gridNumber[y][x-1] == 3
                && !(route.getCell(y, x-1).getLeft().isLine() && route.getCell(y, x-1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y, x-1));
            else if (coordExist(y+1, x-1, size) && route.getCell(y+1, x-1).getTop().isCross() && route.getCell(y+1, x-1).getRight().isCross() && coordExist(y, x+1, size) && gridNumber[y][x+1] == 3
                && !(route.getCell(y, x+1).getBottom().isLine() && route.getCell(y, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x+1));
            else if (coordExist(y+1, x-1, size) && route.getCell(y+1, x-1).getTop().isCross() && route.getCell(y+1, x-1).getRight().isCross() && coordExist(y-1, x, size) && gridNumber[y-1][x] == 3
                && !(route.getCell(y-1, x).getLeft().isLine() && route.getCell(y-1, x).getTop().isLine()) )
                    listCoord.add(new Coordinates(y-1, x));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 2){
            listCoord.add(new Coordinates(y, x));

            if (route.getCell(y, x).getTop().isCross() && coordExist(y+1, x, size) && gridNumber[y+1][x] == 3
                && !(route.getCell(y+1, x).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y+1, x));
            else if (route.getCell(y, x).getRight().isCross() && coordExist(y, x-1, size) && gridNumber[y][x-1] == 3
                && !(route.getCell(y, x-1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x-1));
            else if (route.getCell(y, x).getBottom().isCross() && coordExist(y-1, x, size) && gridNumber[y-1][x] == 3
                && !(route.getCell(y-1, x).getTop().isLine()) )
                    listCoord.add(new Coordinates(y-1, x));
            else if (route.getCell(y, x).getLeft().isCross() && coordExist(y, x+1, size) && gridNumber[y][x+1] == 3
                && !(route.getCell(y, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x+1));
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 1){
            listCoord.add(new Coordinates(y, x));

            if (route.getCell(y, x).getLeft().isCross() && route.getCell(y, x).getTop().isCross() && coordExist(y+1, x+1, size) && gridNumber[y+1][x+1] == 3
                && !(route.getCell(y+1, x+1).getBottom().isLine() && route.getCell(y+1, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else if (route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getRight().isCross() && coordExist(y+1, x-1, size) && gridNumber[y+1][x-1] == 3
                && !(route.getCell(y+1, x-1).getLeft().isLine() && route.getCell(y+1, x-1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getRight().isCross() && route.getCell(y, x).getBottom().isCross() && coordExist(y-1, x-1, size) && gridNumber[y-1][x-1] == 3
                && !(route.getCell(y-1, x-1).getLeft().isLine() && route.getCell(y-1, x-1).getTop().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getLeft().isCross() && coordExist(y-1, x+1, size) && gridNumber[y-1][x+1] == 3
                && !(route.getCell(y-1, x+1).getTop().isLine() && route.getCell(y-1, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y-1, x+1));
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
        if (coordExist(y+1, x+1, size) && ((route.getCell(y+1, x+1).getLeft().isLine() && route.getCell(y+1, x+1).getTop().isCross()) || (route.getCell(y+1, x+1).getLeft().isCross() && route.getCell(y+1, x+1).getTop().isLine()) ) ){
            
            while(coordExist(y, x, size) && gridNumber[y][x] == 2){
                listCoord.add(new Coordinates(y, x));
                x--; y--;
                if (coordExist(y, x, size) && ( (route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getRight().isEmpty()) || (route.getCell(y, x).getBottom().isEmpty() && route.getCell(y, x).getRight().isCross()) ) )
                    return true;
            }
            listCoord.clear();
        }
        return false;
    }

    /**
     * Regarde si la technique 16 est valide dans la direction Nord-Est
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si le patern est valide, false sinon
     */
    private boolean searchTech16PosNE(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y+1, x-1, size) && ((route.getCell(y+1, x-1).getTop().isLine() && route.getCell(y+1, x-1).getRight().isCross()) || (route.getCell(y+1, x-1).getTop().isCross() && route.getCell(y+1, x-1).getRight().isLine()) ) ){
            
            while(coordExist(y, x, size) && gridNumber[y][x] == 2){
                listCoord.add(new Coordinates(y, x));
                x++; y--;
                if (coordExist(y, x, size) && ( (route.getCell(y, x).getLeft().isCross() && route.getCell(y, x).getBottom().isEmpty()) || (route.getCell(y, x).getLeft().isEmpty() && route.getCell(y, x).getBottom().isCross()) ) )
                    return true;
            }
            listCoord.clear();
        }
        return false;
    }

    /**
     * Regarde si la technique 16 est valide dans la direction Sud-Est
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si le patern est valide, false sinon
     */
    private boolean searchTech16PosSE(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y-1, x-1, size) && ((route.getCell(y-1, x-1).getBottom().isLine() && route.getCell(y-1, x-1).getRight().isCross()) || (route.getCell(y-1, x-1).getBottom().isCross() && route.getCell(y-1, x-1).getRight().isLine()) ) ){
            
            while(coordExist(y, x, size) && gridNumber[y][x] == 2){
                listCoord.add(new Coordinates(y, x));
                x++; y++;
                if (coordExist(y, x, size) && ( (route.getCell(y, x).getLeft().isCross() && route.getCell(y, x).getTop().isEmpty()) || (route.getCell(y, x).getLeft().isEmpty() && route.getCell(y, x).getTop().isCross()) ) )
                    return true;
            }
            listCoord.clear();
        }
        return false;
    }

    /**
     * Regarde si la technique 16 est valide dans la direction Sud-Ouest
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si le patern est valide, false sinon
     */
    private boolean searchTech16PosSW(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y-1, x+1, size) && ((route.getCell(y-1, x+1).getLeft().isLine() && route.getCell(y-1, x+1).getBottom().isCross()) || (route.getCell(y-1, x+1).getLeft().isCross() && route.getCell(y-1, x+1).getBottom().isLine()) ) ){
            
            while(coordExist(y, x, size) && gridNumber[y][x] == 2){
                listCoord.add(new Coordinates(y, x));
                x--; y++;
                if (coordExist(y, x, size) && ( (route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getRight().isEmpty()) || (route.getCell(y, x).getTop().isEmpty() && route.getCell(y, x).getRight().isCross()) ) )
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
        if (coordExist(y, x, size) && gridNumber[y][x] == 2){
            
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
     * [2 atteint]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech17Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 2){

            if ((coordExist(y, x-1, size) && route.getCell(y, x-1).getTop().isLine()) || (coordExist(y-1, x, size) && route.getCell(y-1, x).getLeft().isLine())
                && ( (route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getRight().isEmpty()) || (route.getCell(y, x).getBottom().isEmpty() && route.getCell(y, x).getRight().isCross()) ) )
                    listCoord.add(new Coordinates(y, x));
            else if ((coordExist(y-1, x, size) && route.getCell(y-1, x).getRight().isLine()) || (coordExist(y, x+1, size) && route.getCell(y, x+1).getTop().isLine())
                && ( (route.getCell(y, x).getLeft().isCross() && route.getCell(y, x).getBottom().isEmpty()) || (route.getCell(y, x).getLeft().isEmpty() && route.getCell(y, x).getBottom().isCross()) ) )
                    listCoord.add(new Coordinates(y, x));
            else if ((coordExist(y, x+1, size) && route.getCell(y, x+1).getBottom().isLine()) || (coordExist(y+1, x, size) && route.getCell(y+1, x).getRight().isLine())
                && ( (route.getCell(y, x).getLeft().isCross() && route.getCell(y, x).getTop().isEmpty()) || (route.getCell(y, x).getLeft().isEmpty() && route.getCell(y, x).getTop().isCross()) ) )
                    listCoord.add(new Coordinates(y, x));
            else if ((coordExist(y+1, x, size) && route.getCell(y+1, x).getLeft().isLine()) || (coordExist(y, x-1, size) && route.getCell(y, x-1).getBottom().isLine())
                && ( (route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getRight().isEmpty()) || (route.getCell(y, x).getTop().isEmpty() && route.getCell(y, x).getRight().isCross()) ) )
                    listCoord.add(new Coordinates(y, x));
            else
                return false;

            return true;
        }
        return false;
    }

    /**
     * [adjacent 3 et 1]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech18Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 1){
            listCoord.add(new Coordinates(y, x));

            if (route.getCell(y, x).getTop().isCross() && coordExist(y+1, x, size) && gridNumber[y+1][x] == 3
                && !(route.getCell(y+1, x).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y+1, x));
            else if (route.getCell(y, x).getRight().isCross() && coordExist(y, x-1, size) && gridNumber[y][x-1] == 3
                && !(route.getCell(y, x-1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x-1));
            else if (route.getCell(y, x).getBottom().isCross() && coordExist(y-1, x, size) && gridNumber[y-1][x] == 3
                && !(route.getCell(y-1, x).getTop().isLine()) )
                    listCoord.add(new Coordinates(y-1, x));
            else if (route.getCell(y, x).getLeft().isCross() && coordExist(y, x+1, size) && gridNumber[y][x+1] == 3
                && !(route.getCell(y, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x+1));
            else{
                listCoord.clear();
                return false;
            }

            return true;
        }
        return false;
    }
    
    /**
     * [0 présent]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech19Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 0
            && !(route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getRight().isCross() && route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getLeft().isCross()) ) {
                    listCoord.add(new Coordinates(y, x));
                    return true;
            }else
                return false;

    }

    /**
     * [1 en coin]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech20Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 1){

            if (!coordExist(y, x-1, size) && !coordExist(y-1, x, size)
                && !(route.getCell(y, x).getLeft().isCross() && route.getCell(y, x).getTop().isCross()) )
                    listCoord.add(new Coordinates(y, x));
            else if (!coordExist(y-1, x, size) && !coordExist(y, x+1, size)
                && !(route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getRight().isCross()) )
                    listCoord.add(new Coordinates(y, x));
            else if (!coordExist(y, x+1, size) && !coordExist(y+1, x, size)
                && !(route.getCell(y, x).getRight().isCross()) && route.getCell(y, x).getBottom().isCross() )
                    listCoord.add(new Coordinates(y, x));
            else if (!coordExist(y+1, x, size) && !coordExist(y, x-1, size)
                && !(route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getLeft().isCross()) )
                    listCoord.add(new Coordinates(y, x));
            else
                return false;

            return true;
        }
        return false;
    }

    /**
     * [3 & 1 diagonaux bis]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    private boolean searchTech21Pos(int x, int y, Integer [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 3){
            listCoord.add(new Coordinates(y, x));

            if (route.getCell(y, x).getLeft().isLine() && route.getCell(y, x).getTop().isLine() && coordExist(y+1, x+1, size) && gridNumber[y+1][x+1] == 1
                && !(route.getCell(y+1, x+1).getBottom().isCross() && route.getCell(y+1, x+1).getRight().isCross()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else if (route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getRight().isLine() && coordExist(y+1, x-1, size) && gridNumber[y+1][x-1] == 1
                && !(route.getCell(y+1, x-1).getLeft().isCross() && route.getCell(y+1, x-1).getBottom().isCross()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getRight().isLine() && route.getCell(y, x).getBottom().isLine() && coordExist(y-1, x-1, size) && gridNumber[y-1][x-1] == 1
                && !(route.getCell(y-1, x-1).getLeft().isCross() && route.getCell(y-1, x-1).getTop().isCross()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getLeft().isLine() && route.getCell(y, x).getBottom().isLine() && coordExist(y-1, x+1, size) && gridNumber[y-1][x+1] == 1
                && !(route.getCell(y-1, x+1).getTop().isCross() && route.getCell(y-1, x+1).getRight().isCross()) )
                    listCoord.add(new Coordinates(y-1, x+1));
            else{
                listCoord.clear();
                return false;
            }

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
