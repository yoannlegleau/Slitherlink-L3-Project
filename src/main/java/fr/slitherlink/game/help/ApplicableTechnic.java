package fr.slitherlink.game.help;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import fr.slitherlink.game.grid.*;

/**
 * Classe contenant les méthodes pour rechercher les techniques applicables dans la grille
 * @author Bruneau Antoine
 * @version 1, 04/03/2023
 */
public class ApplicableTechnic {
    
    public static final int TOP = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;

    private int size;
    private int [][] gridNumber;
    private Grid route;
    protected LinkedList<Coordinates> listCoord;

    public ApplicableTechnic(int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        this.gridNumber = initGrid(gridNumber,size);
        this.route = route;
        this.size = size;
        this.listCoord = listCoord;
    }

    /**
     * Initialise la grille avec des -1
     * @param grid la grille à initialiser
     * @param size la taille de la grille
     * @return
     */
    private int [][] initGrid(int[][] grid,int size) {
        grid = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = -1;
            }
        }
        return grid;
    }
    
    /**
     * Vérifie si la case de coordonnées x, y existe dans la grille
     * @param size la taille de la grille
     */
    private boolean coordExist(int y, int x, int size) {
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
    protected boolean searchTech1Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech2Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech3Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech4Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech5Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech6Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech7Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech8PosSW(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        int xSave = x, ySave = y;

        if (coordExist(y, x, size) && gridNumber[y][x] == 3 && coordExist(y+1, x-1, size) && gridNumber[y+1][x-1] == 2){
            listCoord.add(new Coordinates(y, x));

            while(coordExist(y+1, x-1, size) && gridNumber[y+1][x-1] == 2){
                listCoord.add(new Coordinates(y+1, x-1));
                x--; y++;
            }
            if(coordExist(y+1, x-1, size) && gridNumber[y+1][x-1] == 3
                && !(route.getCell(y+1, x-1).getLeft().isLine() && route.getCell(y+1, x-1).getBottom().isLine() && route.getCell(ySave, xSave).getLeft().isLine() && route.getCell(ySave, xSave).getTop().isLine()) ){
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
    protected boolean searchTech8PosSE(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        int xSave = x, ySave = y;

        if (coordExist(y, x, size) && gridNumber[y][x] == 3 && coordExist(y+1, x+1, size) && gridNumber[y+1][x+1] == 2){
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
    protected boolean searchTech8Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 3){

            if (searchTech8PosSW(y, x, gridNumber, route, size, listCoord))
                    return true;
            else if (searchTech8PosSE(y, x, gridNumber, route, size, listCoord))
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
    protected boolean searchTech9Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech10Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech11PosNW(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech11PosNE(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech11PosSE(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech11PosSW(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech11Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 2){
            
            if (searchTech11PosNW(y, x, gridNumber, route, size, listCoord))
                return true;
            else if (searchTech11PosNE(y, x, gridNumber, route, size, listCoord))
                return true;
            else if (searchTech11PosSE(y, x, gridNumber, route, size, listCoord))
                return true;
            else if (searchTech11PosSW(y, x, gridNumber, route, size, listCoord))
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
    protected boolean searchTech12Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech13Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech14Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech15Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech16PosNW(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech16PosNE(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech16PosSE(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech16PosSW(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech16Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 2){
            
            if (searchTech16PosNW(y, x, gridNumber, route, size, listCoord))
                return true;
            else if (searchTech16PosNE(y, x, gridNumber, route, size, listCoord))
                return true;
            else if (searchTech16PosSE(y, x, gridNumber, route, size, listCoord))
                return true;
            else if (searchTech16PosSW(y, x, gridNumber, route, size, listCoord))
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
    protected boolean searchTech17Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech18Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 1){
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y-1, x, size) && gridNumber[y-1][x] == 3){
                if( (!coordExist(y-1, x-1, size) || route.getCell(y-1, x-1).getBottom().isCross()) && route.getCell(y-1, x-1).getRight().isEmpty()){
                    listCoord.add(new Coordinates(y-1, x));
                    return true;
                } else if( (!coordExist(y-1, x+1, size) || route.getCell(y-1, x+1).getBottom().isCross()) && route.getCell(y-1, x+1).getLeft().isEmpty()){
                    listCoord.add(new Coordinates(y-1, x));
                    return true;
                }
            }
            if (coordExist(y, x+1, size) && gridNumber[y][x+1] == 3){
                if( (!coordExist(y-1, x+1, size) || route.getCell(y-1, x+1).getLeft().isCross()) && route.getCell(y-1, x+1).getBottom().isEmpty()){
                    listCoord.add(new Coordinates(y, x+1));
                    return true;
                } else if( (!coordExist(y+1, x+1, size) || route.getCell(y+1, x+1).getLeft().isCross()) && route.getCell(y+1, x+1).getTop().isEmpty()){
                    listCoord.add(new Coordinates(y, x+1));
                    return true;
                }
            }
            if (coordExist(y+1, x, size) && gridNumber[y+1][x] == 3){
                if( (!coordExist(y+1, x+1, size) || route.getCell(y+1, x+1).getTop().isCross()) && route.getCell(y+1, x+1).getLeft().isEmpty()){
                    listCoord.add(new Coordinates(y+1, x));
                    return true;
                } else if( (!coordExist(y+1, x-1, size) || route.getCell(y+1, x-1).getTop().isCross()) && route.getCell(y+1, x-1).getRight().isEmpty()){
                    listCoord.add(new Coordinates(y+1, x));
                    return true;
                }
            }
            if (coordExist(y, x-1, size) && gridNumber[y][x-1] == 3){
                if( (!coordExist(y+1, x-1, size) || route.getCell(y+1, x-1).getRight().isCross()) && route.getCell(y+1, x-1).getTop().isEmpty()){
                    listCoord.add(new Coordinates(y, x-1));
                    return true;
                } else if( (!coordExist(y-1, x-1, size) || route.getCell(y-1, x-1).getRight().isCross()) && route.getCell(y-1, x-1).getBottom().isEmpty()){
                    listCoord.add(new Coordinates(y, x-1));
                    return true;
                }
            }

            listCoord.clear();
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
    protected boolean searchTech19Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech20Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
    protected boolean searchTech21Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
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
     * [1 atteint]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    protected boolean searchTech22Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 1){
            
            if ( ( (coordExist(y, x-1, size) && route.getCell(y, x-1).getTop().isLine() && (!coordExist(y-1, x, size) || route.getCell(y-1, x).getLeft().isCross()) )
                || (coordExist(y-1, x, size) && route.getCell(y-1, x).getLeft().isLine() && (!coordExist(y, x-1, size) || route.getCell(y, x-1).getTop().isCross()) ) )
                && !(route.getCell(x, y).getBottom().isLine() && route.getCell(x, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if ( ( (coordExist(y-1, x, size) && route.getCell(y-1, x).getRight().isLine() && (!coordExist(y, x+1, size) || route.getCell(y, x+1).getTop().isCross()) )
                || (coordExist(y, x+1, size) && route.getCell(y, x+1).getTop().isLine() && (!coordExist(y-1, x, size) || route.getCell(y-1, x).getRight().isCross()) ) )
                && !(route.getCell(x, y).getBottom().isLine() && route.getCell(x, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if ( ( (coordExist(y, x+1, size) && route.getCell(y, x+1).getBottom().isLine() && (!coordExist(y+1, x, size) || route.getCell(y+1, x).getRight().isCross()) )
                || (coordExist(y+1, x, size) && route.getCell(y+1, x).getRight().isLine() && (!coordExist(y, x+1, size) || route.getCell(y, x+1).getBottom().isCross()) ) )
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else if ( ( (coordExist(y+1, x, size) && route.getCell(y+1, x).getLeft().isLine() && (!coordExist(y, x-1, size) || route.getCell(y, x-1).getBottom().isCross()) )
                || (coordExist(y, x-1, size) && route.getCell(y, x-1).getBottom().isLine() && (!coordExist(y+1, x, size) || route.getCell(y+1, x).getLeft().isCross()) ) )
                && !(route.getCell(x, y).getTop().isLine() && route.getCell(x, y).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x));
            else
                return false;

            return true;
        }
        return false;
    }

    /**
     * [Coin d'un 1 barré]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    protected boolean searchTech23Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 1){

            if (coordExist(y-1, x-1, size) && (route.getCell(y-1, x-1).getBottom().isCross() && route.getCell(y-1, x-1).getRight().isCross())
                && !(route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getLeft().isCross()) )
                    listCoord.add(new Coordinates(y, x));
            else if (coordExist(y-1, x+1, size) && (route.getCell(y-1, x+1).getBottom().isCross() && route.getCell(y-1, x+1).getLeft().isCross())
                && !(route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getRight().isCross()) )
                    listCoord.add(new Coordinates(y, x));
            else if (coordExist(y+1, x+1, size) && (route.getCell(y+1, x+1).getTop().isCross() && route.getCell(y+1, x+1).getLeft().isCross())
                && !(route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getRight().isCross()) )
                    listCoord.add(new Coordinates(y, x));
            else if (coordExist(y+1, x-1, size) && (route.getCell(y+1, x-1).getTop().isCross() && route.getCell(y+1, x-1).getRight().isCross())
                && !(route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getLeft().isCross()) )
                    listCoord.add(new Coordinates(y, x));
            else
                return false;

            return true;
        }
        return false;
    }

    /**
     * [1 & 1 diagonaux symétriques]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    protected boolean searchTech24Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 1){
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y-1, x-1, size) && gridNumber[y-1][x-1] == 1 && (route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getRight().isCross())
                && !(route.getCell(y-1, x-1).getTop().isCross() && route.getCell(y-1, x-1).getLeft().isCross()) )
                    listCoord.add(new Coordinates(y-1, x-1));
            else if (coordExist(y-1, x+1, size) && gridNumber[y-1][x+1] == 1 && (route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getLeft().isCross())
                && !(route.getCell(y-1, x+1).getTop().isCross() && route.getCell(y-1, x+1).getRight().isCross()) )
                    listCoord.add(new Coordinates(y-1, x+1));
            else if (coordExist(y+1, x+1, size) && gridNumber[y+1][x+1] == 1 && (route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getLeft().isCross())
                && !(route.getCell(y+1, x+1).getBottom().isCross() && route.getCell(y+1, x+1).getRight().isCross()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else if (coordExist(y+1, x-1, size) && gridNumber[y+1][x-1] == 1 && (route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getRight().isCross())
                && !(route.getCell(y+1, x-1).getBottom().isCross() && route.getCell(y+1, x-1).getLeft().isCross()) )
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
     * [3 adjacents à deux 1 diagonaux]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @param listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     * @return true si la technique est applicable, false sinon
     */
    protected boolean searchTech25Pos(int y, int x, int [][] gridNumber, Grid route, int size, LinkedList<Coordinates> listCoord) {
        if (coordExist(y, x, size) && gridNumber[y][x] == 3){
            listCoord.add(new Coordinates(y, x));

            if(coordExist(y-1, x, size) && gridNumber[y-1][x] == 1 && coordExist(y, x+1, size) && gridNumber[y][x+1] == 1
                && !(route.getCell(y-1, x).getLeft().isCross() && route.getCell(y-1, x).getTop().isCross() && route.getCell(y, x+1).getRight().isCross() && route.getCell(y, x+1).getBottom().isCross()) ){
                    listCoord.add(new Coordinates(y-1, x));
                    listCoord.add(new Coordinates(y, x+1));
            } else if (coordExist(y, x+1, size) && gridNumber[y][x+1] == 1 && coordExist(y+1, x, size) && gridNumber[y+1][x] == 1
                && !(route.getCell(y, x+1).getTop().isCross() && route.getCell(y, x+1).getRight().isCross() && route.getCell(y+1, x).getBottom().isCross() && route.getCell(y+1, x).getLeft().isCross()) ){
                    listCoord.add(new Coordinates(y, x+1));
                    listCoord.add(new Coordinates(y+1, x));
            } else if (coordExist(y+1, x, size) && gridNumber[y+1][x] == 1 && coordExist(y, x-1, size) && gridNumber[y][x-1] == 1
                && !(route.getCell(y+1, x).getRight().isCross() && route.getCell(y+1, x).getBottom().isCross() && route.getCell(y, x-1).getLeft().isCross() && route.getCell(y, x-1).getTop().isCross()) ){
                    listCoord.add(new Coordinates(y+1, x));
                    listCoord.add(new Coordinates(y, x-1));
            } else if (coordExist(y, x-1, size) && gridNumber[y][x-1] == 1 && coordExist(y-1, x, size) && gridNumber[y-1][x] == 1
                && !(route.getCell(y, x-1).getBottom().isCross() && route.getCell(y, x-1).getLeft().isCross() && route.getCell(y-1, x).getTop().isCross() && route.getCell(y-1, x).getRight().isCross()) ){
                    listCoord.add(new Coordinates(y, x-1));
                    listCoord.add(new Coordinates(y-1, x));
            } else {
                listCoord.clear();
                return false;
            }

            return true;
        }
        return false;
    }

    /*

    //listEndsPath
    private List<EdgePos> getEndsPath(Grid route, int size) {
        List<EdgePos> listEndsPath = new LinkedList<EdgePos>();
        int x, y;

        for(y=0; y < size; y++)
            for(x=0; x < size; x++){
                if(route.getCell(y, x).getTop().isLine() &&
                    ( !((coordExist(y-1, x, size) && route.getCell(y-1, x).getLeft().isLine()) || (coordExist(y, x, size) && route.getCell(y, x).getLeft().isLine()) || (coordExist(y-1, x-1, size) && (route.getCell(y-1, x-1).getRight().isLine() || route.getCell(y-1, x-1).getBottom().isLine()) ) || (coordExist(y, x-1, size) && (route.getCell(y, x-1).getRight().isLine() || route.getCell(y, x-1).getTop().isLine()) ) )
                    || !((coordExist(y-1, x, size) && route.getCell(y-1, x).getRight().isLine()) || (coordExist(y, x, size) && route.getCell(y, x).getRight().isLine()) || (coordExist(y-1, x+1, size) && (route.getCell(y-1, x+1).getLeft().isLine() || route.getCell(y-1, x+1).getBottom().isLine()) ) || (coordExist(y, x+1, size) && (route.getCell(y, x+1).getLeft().isLine() || route.getCell(y, x+1).getTop().isLine()) ) ) ) )
                        listEndsPath.add(new EdgePos(TOP, y, x));
                if(route.getCell(y, x).getBottom().isLine() &&
                    ( !((coordExist(y+1, x, size) && route.getCell(y+1, x).getLeft().isLine()) || (coordExist(y, x, size) && route.getCell(y, x).getLeft().isLine()) || (coordExist(y+1, x-1, size) && (route.getCell(y+1, x-1).getRight().isLine() || route.getCell(y+1, x-1).getTop().isLine()) ) || (coordExist(y, x-1, size) && (route.getCell(y, x-1).getRight().isLine() || route.getCell(y, x-1).getBottom().isLine()) ) )
                    || !((coordExist(y+1, x, size) && route.getCell(y+1, x).getRight().isLine()) || (coordExist(y, x, size) && route.getCell(y, x).getRight().isLine()) || (coordExist(y+1, x+1, size) && (route.getCell(y+1, x+1).getLeft().isLine() || route.getCell(y+1, x+1).getTop().isLine()) ) || (coordExist(y, x+1, size) && (route.getCell(y, x+1).getLeft().isLine() || route.getCell(y, x+1).getBottom().isLine()) ) ) ) )
                        listEndsPath.add(new EdgePos(BOTTOM, y, x));
                if(route.getCell(y, x).getLeft().isLine() &&
                    ( !((coordExist(y, x-1, size) && route.getCell(y, x-1).getTop().isLine()) || (coordExist(y, x, size) && route.getCell(y, x).getTop().isLine()) || (coordExist(y-1, x-1, size) && (route.getCell(y-1, x-1).getRight().isLine() || route.getCell(y-1, x-1).getBottom().isLine()) ) || (coordExist(y-1, x, size) && (route.getCell(y-1, x).getLeft().isLine() || route.getCell(y-1, x).getBottom().isLine()) ) )
                    || !((coordExist(y, x-1, size) && route.getCell(y, x-1).getBottom().isLine()) || (coordExist(y, x, size) && route.getCell(y, x).getBottom().isLine()) || (coordExist(y+1, x-1, size) && (route.getCell(y+1, x-1).getRight().isLine() || route.getCell(y+1, x-1).getTop().isLine()) ) || (coordExist(y+1, x, size) && (route.getCell(y+1, x).getLeft().isLine() || route.getCell(y+1, x).getTop().isLine()) ) ) ) )
                        listEndsPath.add(new EdgePos(LEFT, y, x));
                if(route.getCell(y, x).getRight().isLine() &&
                    ( !((coordExist(y, x+1, size) && route.getCell(y, x+1).getTop().isLine()) || (coordExist(y, x, size) && route.getCell(y, x).getTop().isLine()) || (coordExist(y-1, x+1, size) && (route.getCell(y-1, x+1).getLeft().isLine() || route.getCell(y-1, x+1).getBottom().isLine()) ) || (coordExist(y-1, x, size) && (route.getCell(y-1, x).getRight().isLine() || route.getCell(y-1, x).getBottom().isLine()) ) )
                    || !((coordExist(y, x+1, size) && route.getCell(y, x+1).getBottom().isLine()) || (coordExist(y, x, size) && route.getCell(y, x).getBottom().isLine()) || (coordExist(y+1, x+1, size) && (route.getCell(y+1, x+1).getLeft().isLine() || route.getCell(y+1, x+1).getTop().isLine()) ) || (coordExist(y+1, x, size) && (route.getCell(y+1, x).getRight().isLine() || route.getCell(y+1, x).getTop().isLine()) ) ) ) )
                        listEndsPath.add(new EdgePos(RIGHT, y, x));
            }
        return listEndsPath;
    } 


    
    private List<List<EdgePos>> getReachableEndsByEnds(List<EdgePos> listEnds, Grid route, int size) {
        List<List<EdgePos>> listReachableEnds = new LinkedList<>();
        for (EdgePos edgePos : listEnds) {
        }
    }

    
    private List<EdgePos> getReachableEndsByEnd(Grid route, int size, EdgePos edgePos, List<EdgePos> listEnds) {
        List<EdgePos> listReachableEnds = new LinkedList<>();
        Queue<EdgePos> queue = new LinkedList<>();
        EdgePos currentEdge;
        int x, y;
        int pos;
        //faire une copie profonde de route

        queue.add(edgePos);
        //cycler une fois
        while(!queue.isEmpty()){
            currentEdge = queue.poll();
            x = currentEdge.getX();
            y = currentEdge.getY();
            pos = currentEdge.getPosition();

            if(coordExist(y, x, size) && currentEdge != edgePos ){
                if(route.getCell(y, x).edges[pos].isLine() && listEnds.contains(currentEdge)){
                    listReachableEnds.add(currentEdge);
                    //remplacer par une croix
                } else if(route.getCell(y, x).edges[pos].isEmpty()){
                    //ajouter les voisins d'un bout à la queue
                    //ajouter les voisins de l'autre bout à la queue

                    //remplacer par une croix
                }
            }
        }
        
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
