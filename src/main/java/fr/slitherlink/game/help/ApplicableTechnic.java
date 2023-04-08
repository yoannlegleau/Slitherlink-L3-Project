package fr.slitherlink.game.help;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.grid.*;

/**
 * Classe contenant les méthodes pour rechercher les techniques applicables dans la grille
 * @author Bruneau Antoine
 * @version 1, 04/03/2023
 */
public class ApplicableTechnic {

    private static ApplicableTechnic instance = null; /** instance de la classe */
    private ArrayList<searchTech> searchTechList; /** liste des méthodes de recherche de techniques */
    private int nbTechnics; /** nombre de techniques */

    /** Interface fonctionnelle pour les méthodes de recherche de techniques */
    @FunctionalInterface
    public interface searchTech {
        public LinkedList<Coordinates> apply(int y, int x, Integer[][] gridNumber, Grid route, int size);
    }

    /** Constructeur (Singleton) */
    private ApplicableTechnic() {
        searchTechList = new ArrayList<>();
        fillSearchTechList();
        nbTechnics = searchTechList.size();
    }

    /** Constructeur (Singleton) */
    public static ApplicableTechnic getInstance() {
        if (instance == null) {
            instance = new ApplicableTechnic();
        }
        return instance;
    }

    /** Recherche d'une technique applicable à une position donnée dans un puzzle en cours
     * @param num numéro de la technique (1 à 25)
     * @param y, x coordonnées de la case
     * @param game partie en cours
    */
    public LinkedList<Coordinates> searchTech(int num, int y, int x, Game game) {
        return searchTechList.get(num-1).apply(y, x, game.getNumbers(), game.getCurrentGrid(), game.getCurrentGrid().getSize());
    }

    /** Recherche d'une technique applicable à une position donnée dans une grille donnée (pour les tests)
     * @param num numéro de la technique (1 à 25)
     * @param y, x coordonnées de la case
     * @param gridNumber grille des nombres
     * @param route grille de la route
     * @param size taille de la grille
    */
    public LinkedList<Coordinates> searchTech(int num, int y, int x, Integer[][] gridNumber, Grid route, int size) {
        return searchTechList.get(num-1).apply(y, x, gridNumber, route, size);
    }

    public int getNbTechnics() {
        return nbTechnics;
    }

    /** Remplissage de la liste des méthodes de recherche de techniques*/
    private void fillSearchTechList() {
        searchTechList.add(ApplicableTechnic::searchTech1Pos);
        searchTechList.add(ApplicableTechnic::searchTech2Pos);
        searchTechList.add(ApplicableTechnic::searchTech3Pos);
        searchTechList.add(ApplicableTechnic::searchTech4Pos);
        searchTechList.add(ApplicableTechnic::searchTech5Pos);
        searchTechList.add(ApplicableTechnic::searchTech6Pos);
        searchTechList.add(ApplicableTechnic::searchTech7Pos);
        searchTechList.add(ApplicableTechnic::searchTech8Pos);
        searchTechList.add(ApplicableTechnic::searchTech9Pos);
        searchTechList.add(ApplicableTechnic::searchTech10Pos);
        searchTechList.add(ApplicableTechnic::searchTech11Pos);
        searchTechList.add(ApplicableTechnic::searchTech12Pos);
        searchTechList.add(ApplicableTechnic::searchTech13Pos);
        searchTechList.add(ApplicableTechnic::searchTech14Pos);
        searchTechList.add(ApplicableTechnic::searchTech15Pos);
        searchTechList.add(ApplicableTechnic::searchTech16Pos);
        searchTechList.add(ApplicableTechnic::searchTech17Pos);
        searchTechList.add(ApplicableTechnic::searchTech18Pos);
        searchTechList.add(ApplicableTechnic::searchTech19Pos);
        searchTechList.add(ApplicableTechnic::searchTech20Pos);
        searchTechList.add(ApplicableTechnic::searchTech21Pos);
        searchTechList.add(ApplicableTechnic::searchTech22Pos);
        searchTechList.add(ApplicableTechnic::searchTech23Pos);
        searchTechList.add(ApplicableTechnic::searchTech24Pos);
        searchTechList.add(ApplicableTechnic::searchTech25Pos);
    }

    /**
     * Vérifie si la case de coordonnées x, y existe dans la grille
     * @param y,x les coordonnées de la case
     * @param size la taille de la grille
     */
    private static boolean coordExist(int y, int x, int size) {
        return (x >= 0 && x < size && y >= 0 && y < size);
    }
    
    /**
     * [Technique 0 & 3 adjacents]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech1Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 0)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y-1, x, size) && (Objects.equals(gridNumber[y-1][x], 3))
                && !(route.getCell(y-1, x).getTop().isLine() && route.getCell(y-1, x).getRight().isLine() && route.getCell(y-1, x).getLeft().isLine() && route.getCell(y-1, x-1).getBottom().isLine() && route.getCell(y-1, x+1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y-1, x));
            else if (coordExist(y, x+1, size) && (Objects.equals(gridNumber[y][x+1], 3))
                && !(route.getCell(y, x+1).getRight().isLine() && route.getCell(y, x+1).getTop().isLine() && route.getCell(y, x+1).getBottom().isLine() && route.getCell(y-1, x+1).getLeft().isLine() && route.getCell(y+1, x+1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x+1));
            else if (coordExist(y+1, x, size) && (Objects.equals(gridNumber[y+1][x], 3))
                && !(route.getCell(y+1, x).getBottom().isLine() && route.getCell(y+1, x).getRight().isLine() && route.getCell(y+1, x).getLeft().isLine() && route.getCell(y+1, x-1).getTop().isLine() && route.getCell(y+1, x+1).getTop().isLine()) )
                    listCoord.add(new Coordinates(y+1, x));
            else if (coordExist(y, x-1, size) && (Objects.equals(gridNumber[y][x-1], 3))
                && !(route.getCell(y, x-1).getLeft().isLine() && route.getCell(y, x-1).getTop().isLine() && route.getCell(y, x-1).getBottom().isLine() && route.getCell(y-1, x-1).getRight().isLine() && route.getCell(y+1, x-1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x-1));
            else{
                return null;
            }
            return listCoord;
        }
        return null;
    }

    /**
     * [Technique 0 & 3 diagonaux]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech2Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 0)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y-1, x-1, size) && (Objects.equals(gridNumber[y-1][x-1], 3))
                && !(route.getCell(y-1, x-1).getBottom().isLine() && route.getCell(y-1, x-1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (coordExist(y-1, x+1, size) && (Objects.equals(gridNumber[y-1][x+1], 3))
                && !(route.getCell(y-1, x+1).getBottom().isLine() && route.getCell(y-1, x+1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y-1, x+1));
            else if (coordExist(y+1, x+1, size) && (Objects.equals(gridNumber[y+1][x+1], 3))
                && !(route.getCell(y+1, x+1).getTop().isLine() && route.getCell(y+1, x+1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else if (coordExist(y+1, x-1, size) && (Objects.equals(gridNumber[y+1][x-1], 3))
                && !(route.getCell(y+1, x-1).getTop().isLine() && route.getCell(y+1, x-1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else{
                listCoord.clear();
                return null;
            }
            return listCoord;
        }
        return null;
    }

    /**
     * [Technique 3 & 3 adjacents]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech3Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 3)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y, x+1, size) && (Objects.equals(gridNumber[y][x+1], 3))
                && !(route.getCell(y, x).getLeft().isLine() && route.getCell(y, x).getRight().isLine() && route.getCell(y, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x+1));
            else if (coordExist(y+1, x, size) && (Objects.equals(gridNumber[y+1][x], 3))
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getBottom().isLine() && route.getCell(y+1, x).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y+1, x));
            else
                return null;
            return listCoord;
        }
        return null;
    }

    /**
     * [Technique 3 & 3 diagonaux]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech4Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 3)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y+1, x-1, size) && (Objects.equals(gridNumber[y+1][x-1], 3))
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getRight().isLine() && route.getCell(y+1, x-1).getBottom().isLine() && route.getCell(y+1, x-1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (coordExist(y+1, x+1, size) && (Objects.equals(gridNumber[y+1][x+1], 3))
                && !(route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getLeft().isLine() && route.getCell(y+1, x+1).getBottom().isLine() && route.getCell(y+1, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else
                return null;
            return listCoord;
        }
        return null;
    }

    /**
     * [Coin d'un 2 tracé]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech5Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

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
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * [2 en coin]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech6Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            
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
                return null;

            return listCoord;
        }
        return null;
    }
    
    /**
     * [3 en coin]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech7Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 3)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

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
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * Regarde si la technique 8 est valide dans la direction Sud-Ouest
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech8PosSW(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        int xSave = x, ySave = y;

        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 3) && coordExist(y+1, x-1, size) && Objects.equals(gridNumber[y+1][x-1], 2)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            while(coordExist(y+1, x-1, size) && Objects.equals(gridNumber[y+1][x-1], 2)){
                listCoord.add(new Coordinates(y+1, x-1));
                x--; y++;
            }
            if(coordExist(y+1, x-1, size) && Objects.equals(gridNumber[y+1][x-1], 3)
                && !(route.getCell(y+1, x-1).getLeft().isLine() && route.getCell(y+1, x-1).getBottom().isLine() && route.getCell(ySave, xSave).getLeft().isLine() && route.getCell(ySave, xSave).getTop().isLine()) ){
                    listCoord.add(new Coordinates(y+1, x-1));
                    return listCoord;
            }else
                return null;
        }
        return null;
    }

    /**
     * Regarde si la technique 8 est valide dans la direction Sud-Est
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech8PosSE(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        int xSave = x, ySave = y;

        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 3) && coordExist(y+1, x+1, size) && Objects.equals(gridNumber[y+1][x+1], 2)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));
            
            while(coordExist(y+1, x+1, size) && Objects.equals(gridNumber[y+1][x+1], 2)){
                listCoord.add(new Coordinates(y+1, x+1));
                x++; y++;
            }
            if(coordExist(y+1, x+1, size) && Objects.equals(gridNumber[y+1][x+1], 3)
                && !(route.getCell(y+1, x+1).getRight().isLine() && route.getCell(y+1, x+1).getBottom().isLine() && route.getCell(ySave, xSave).getTop().isLine() && route.getCell(ySave, xSave).getLeft().isLine() ) ){
                    listCoord.add(new Coordinates(y+1, x+1));
                    return listCoord;
            }else
                return null;
        }
        return null;
    }

    /**
     * [3 & 3 diagnonaux avec des 2 entre]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech8Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 3)){
            LinkedList<Coordinates> listCoord;

            if ( (listCoord = searchTech8PosSW(y, x, gridNumber, route, size)) != null)
                    return listCoord;
            else if ( (listCoord = searchTech8PosSE(y, x, gridNumber, route, size)) != null)
                    return listCoord;
            else
                return null;
        }
        return null;
    }
    
    /**
     * [Coin d'un 3 barré]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech9Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 3)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

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
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * [3 atteint]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech10Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 3)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

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
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * Regarde si la technique 11 est valide dans la direction Nord-Ouest
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech11PosNW(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)
            && (coordExist(y, x+1, size) && route.getCell(y, x+1).getBottom().isLine()) || (coordExist(y+1, x, size) && route.getCell(y+1, x).getRight().isLine()) ){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            while(coordExist(y-1, x-1, size) && Objects.equals(gridNumber[y-1][x-1], 2)){
                listCoord.add(new Coordinates(y+1, x-1));
                x--; y--;
            }
            if(coordExist(y-1, x-1, size) && Objects.equals(gridNumber[y-1][x-1], 3)
                && !(route.getCell(y-1, x-1).getLeft().isLine() && route.getCell(y-1, x-1).getTop().isLine()) ){
                listCoord.add(new Coordinates(y+1, x-1));
                return listCoord;
            }else
                return null;
        }
        return null;
    }

    /**
     * Regarde si la technique 11 est valide dans la direction Nord-Est
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech11PosNE(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)
            && (coordExist(y+1, x, size) && route.getCell(y+1, x).getLeft().isLine()) || (coordExist(y, x-1, size) && route.getCell(y, x-1).getBottom().isLine()) ){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            while(coordExist(y-1, x+1, size) && Objects.equals(gridNumber[y-1][x+1], 2)){
                listCoord.add(new Coordinates(y-1, x+1));
                x++; y--;
            }
            if(coordExist(y-1, x+1, size) && Objects.equals(gridNumber[y-1][x+1], 3)
                && !(route.getCell(y-1, x+1).getTop().isLine() && route.getCell(y-1, x+1).getRight().isLine()) ){
                listCoord.add(new Coordinates(y-1, x+1));
                return listCoord;
            }else
                return null;
        }
        return null;
    }

    /**
     * Regarde si la technique 11 est valide dans la direction Sud-Est
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech11PosSE(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)
            && (coordExist(y, x-1, size) && route.getCell(y, x-1).getTop().isLine()) || (coordExist(y-1, x, size) && route.getCell(y-1, x).getLeft().isLine()) ){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            while(coordExist(y+1, x+1, size) && Objects.equals(gridNumber[y+1][x+1], 2)){
                listCoord.add(new Coordinates(y+1, x+1));
                x++; y++;
            }
            if(coordExist(y+1, x+1, size) && Objects.equals(gridNumber[y+1][x+1], 3)
                && !(route.getCell(y+1, x+1).getBottom().isLine() && route.getCell(y+1, x+1).getRight().isLine()) ){
                listCoord.add(new Coordinates(y+1, x+1));
                return listCoord;
            }else
                return null;
        }
        return null;
    }

    /**
     * Regarde si la technique 11 est valide dans la direction Sud-Ouest
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech11PosSW(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)
            && (coordExist(y-1, x, size) && route.getCell(y-1, x).getRight().isLine()) || (coordExist(y, x+1, size) && route.getCell(y, x+1).getTop().isLine()) ){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            while(coordExist(y+1, x-1, size) && Objects.equals(gridNumber[y+1][x-1], 2)){
                listCoord.add(new Coordinates(y+1, x-1));
                x--; y++;
            }
            if(coordExist(y+1, x-1, size) && Objects.equals(gridNumber[y+1][x-1], 3)
                && !(route.getCell(y+1, x-1).getLeft().isLine() && route.getCell(y+1, x-1).getBottom().isLine()) ){
                listCoord.add(new Coordinates(y+1, x-1));
                return listCoord;
            }else
                return null;
        }
        return null;
    }

    /**
     * [brochette de 2 atteignant un 3]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech11Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)){
            LinkedList<Coordinates> listCoord;

            if ( (listCoord = searchTech11PosNW(y, x, gridNumber, route, size)) != null)
                return listCoord;
            else if ( (listCoord = searchTech11PosNE(y, x, gridNumber, route, size)) != null)
                return listCoord;
            else if ( (listCoord = searchTech11PosSE(y, x, gridNumber, route, size)) != null)
                return listCoord;
            else if ( (listCoord = searchTech11PosSW(y, x, gridNumber, route, size)) != null)
                return listCoord;
        }
        return null;
    }

    /**
     * [3 & 2 diagonaux]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech12Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            if (route.getCell(y, x).getBottom().isCross() && coordExist(y-1, x-1, size) && Objects.equals(gridNumber[y-1][x-1], 3)
                && !(route.getCell(y-1, x-1).getTop().isLine() && route.getCell(y-1, x-1).getLeft().isLine() && route.getCell(y, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getRight().isCross() && coordExist(y-1, x-1, size) && Objects.equals(gridNumber[y-1][x-1], 3)
                && !(route.getCell(y-1, x-1).getTop().isLine() && route.getCell(y-1, x-1).getLeft().isLine() && route.getCell(y, x).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getLeft().isCross() && coordExist(y-1, x+1, size) && Objects.equals(gridNumber[y-1][x+1], 3)
                && !(route.getCell(y-1, x+1).getTop().isLine() && route.getCell(y-1, x+1).getRight().isLine() && route.getCell(y, x).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y-1, x+1));
            else if (route.getCell(y, x).getBottom().isCross() && coordExist(y-1, x+1, size) && Objects.equals(gridNumber[y-1][x+1], 3)
                && !(route.getCell(y-1, x+1).getTop().isLine() && route.getCell(y-1, x+1).getRight().isLine() && route.getCell(y, x).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y-1, x+1));
            else if (route.getCell(y, x).getLeft().isCross() && coordExist(y+1, x+1, size) && Objects.equals(gridNumber[y+1][x+1], 3)
                && !(route.getCell(y+1, x+1).getBottom().isLine() && route.getCell(y+1, x+1).getRight().isLine() && route.getCell(y, x).getTop().isLine()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else if (route.getCell(y, x).getTop().isCross() && coordExist(y+1, x+1, size) && Objects.equals(gridNumber[y+1][x+1], 3)
                && !(route.getCell(y+1, x+1).getBottom().isLine() && route.getCell(y+1, x+1).getRight().isLine() && route.getCell(y, x).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else if (route.getCell(y, x).getTop().isCross() && coordExist(y+1, x-1, size) && Objects.equals(gridNumber[y+1][x-1], 3)
                && !(route.getCell(y+1, x-1).getLeft().isLine() && route.getCell(y+1, x-1).getBottom().isLine() && route.getCell(y, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getRight().isCross() && coordExist(y+1, x-1, size) && Objects.equals(gridNumber[y+1][x-1], 3)
                && !(route.getCell(y+1, x-1).getLeft().isLine() && route.getCell(y+1, x-1).getBottom().isLine() && route.getCell(y, x).getTop().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * [3 & 2 adjacents]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech13Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y-1, x-1, size) && route.getCell(y-1, x-1).getBottom().isCross() && route.getCell(y-1, x-1).getRight().isCross() && coordExist(y+1, x, size) && Objects.equals(gridNumber[y+1][x], 3)
                && !(route.getCell(y+1, x).getLeft().isLine() && route.getCell(y+1, x).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y+1, x));
            else if (coordExist(y-1, x-1, size) && route.getCell(y-1, x-1).getBottom().isCross() && route.getCell(y-1, x-1).getRight().isCross() && coordExist(y, x+1, size) && Objects.equals(gridNumber[y][x+1], 3)
                && !(route.getCell(y, x+1).getTop().isLine() && route.getCell(y, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x+1));
            else if (coordExist(y-1, x+1, size) && route.getCell(y-1, x+1).getLeft().isCross() && route.getCell(y-1, x+1).getBottom().isCross() && coordExist(y, x-1, size) && Objects.equals(gridNumber[y][x-1], 3)
                && !(route.getCell(y, x-1).getLeft().isLine() && route.getCell(y, x-1).getTop().isLine()) )
                    listCoord.add(new Coordinates(y, x-1));
            else if (coordExist(y-1, x+1, size) && route.getCell(y-1, x+1).getLeft().isCross() && route.getCell(y-1, x+1).getBottom().isCross() && coordExist(y+1, x, size) && Objects.equals(gridNumber[y+1][x], 3)
                && !(route.getCell(y+1, x).getBottom().isLine() && route.getCell(y+1, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x));
            else if (coordExist(y+1, x+1, size) && route.getCell(y+1, x+1).getLeft().isCross() && route.getCell(y+1, x+1).getTop().isCross() && coordExist(y-1, x, size) && Objects.equals(gridNumber[y-1][x], 3)
                && !(route.getCell(y-1, x).getTop().isLine() && route.getCell(y-1, x).getRight().isLine()) )
                    listCoord.add(new Coordinates(y-1, x));
            else if (coordExist(y+1, x+1, size) && route.getCell(y+1, x+1).getLeft().isCross() && route.getCell(y+1, x+1).getTop().isCross() && coordExist(y, x-1, size) && Objects.equals(gridNumber[y][x-1], 3)
                && !(route.getCell(y, x-1).getLeft().isLine() && route.getCell(y, x-1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y, x-1));
            else if (coordExist(y+1, x-1, size) && route.getCell(y+1, x-1).getTop().isCross() && route.getCell(y+1, x-1).getRight().isCross() && coordExist(y, x+1, size) && Objects.equals(gridNumber[y][x+1], 3)
                && !(route.getCell(y, x+1).getBottom().isLine() && route.getCell(y, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x+1));
            else if (coordExist(y+1, x-1, size) && route.getCell(y+1, x-1).getTop().isCross() && route.getCell(y+1, x-1).getRight().isCross() && coordExist(y-1, x, size) && Objects.equals(gridNumber[y-1][x], 3)
                && !(route.getCell(y-1, x).getLeft().isLine() && route.getCell(y-1, x).getTop().isLine()) )
                    listCoord.add(new Coordinates(y-1, x));
            else
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * [3 & 2 adjacents bis]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech14Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            if (route.getCell(y, x).getTop().isCross() && coordExist(y+1, x, size) && Objects.equals(gridNumber[y+1][x], 3)
                && !(route.getCell(y+1, x).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y+1, x));
            else if (route.getCell(y, x).getRight().isCross() && coordExist(y, x-1, size) && Objects.equals(gridNumber[y][x-1], 3)
                && !(route.getCell(y, x-1).getLeft().isLine()) )
                    listCoord.add(new Coordinates(y, x-1));
            else if (route.getCell(y, x).getBottom().isCross() && coordExist(y-1, x, size) && Objects.equals(gridNumber[y-1][x], 3)
                && !(route.getCell(y-1, x).getTop().isLine()) )
                    listCoord.add(new Coordinates(y-1, x));
            else if (route.getCell(y, x).getLeft().isCross() && coordExist(y, x+1, size) && Objects.equals(gridNumber[y][x+1], 3)
                && !(route.getCell(y, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y, x+1));
            else
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * [3 & 1 diagonaux]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech15Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 1)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            if (route.getCell(y, x).getLeft().isCross() && route.getCell(y, x).getTop().isCross() && coordExist(y+1, x+1, size) && Objects.equals(gridNumber[y+1][x+1], 3)
                && !(route.getCell(y+1, x+1).getBottom().isLine() && route.getCell(y+1, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else if (route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getRight().isCross() && coordExist(y+1, x-1, size) && Objects.equals(gridNumber[y+1][x-1], 3)
                && !(route.getCell(y+1, x-1).getLeft().isLine() && route.getCell(y+1, x-1).getBottom().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getRight().isCross() && route.getCell(y, x).getBottom().isCross() && coordExist(y-1, x-1, size) && Objects.equals(gridNumber[y-1][x-1], 3)
                && !(route.getCell(y-1, x-1).getLeft().isLine() && route.getCell(y-1, x-1).getTop().isLine()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getLeft().isCross() && coordExist(y-1, x+1, size) && Objects.equals(gridNumber[y-1][x+1], 3)
                && !(route.getCell(y-1, x+1).getTop().isLine() && route.getCell(y-1, x+1).getRight().isLine()) )
                    listCoord.add(new Coordinates(y-1, x+1));
            else
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * Regarde si la technique 16 est valide dans la direction Nord-Ouest
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech16PosNW(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y+1, x+1, size) && ((route.getCell(y+1, x+1).getLeft().isLine() && route.getCell(y+1, x+1).getTop().isCross()) || (route.getCell(y+1, x+1).getLeft().isCross() && route.getCell(y+1, x+1).getTop().isLine()) ) ){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

            while(coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)){
                listCoord.add(new Coordinates(y, x));
                x--; y--;
                if (coordExist(y, x, size) && ( (route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getRight().isEmpty()) || (route.getCell(y, x).getBottom().isEmpty() && route.getCell(y, x).getRight().isCross()) ) )
                    return listCoord;
            }
            listCoord.clear();
        }
        return null;
    }

    /**
     * Regarde si la technique 16 est valide dans la direction Nord-Est
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech16PosNE(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y+1, x-1, size) && ((route.getCell(y+1, x-1).getTop().isLine() && route.getCell(y+1, x-1).getRight().isCross()) || (route.getCell(y+1, x-1).getTop().isCross() && route.getCell(y+1, x-1).getRight().isLine()) ) ){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

            while(coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)){
                listCoord.add(new Coordinates(y, x));
                x++; y--;
                if (coordExist(y, x, size) && ( (route.getCell(y, x).getLeft().isCross() && route.getCell(y, x).getBottom().isEmpty()) || (route.getCell(y, x).getLeft().isEmpty() && route.getCell(y, x).getBottom().isCross()) ) )
                    return listCoord;
            }
            listCoord.clear();
        }
        return null;
    }

    /**
     * Regarde si la technique 16 est valide dans la direction Sud-Est
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech16PosSE(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y-1, x-1, size) && ((route.getCell(y-1, x-1).getBottom().isLine() && route.getCell(y-1, x-1).getRight().isCross()) || (route.getCell(y-1, x-1).getBottom().isCross() && route.getCell(y-1, x-1).getRight().isLine()) ) ){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

            while(coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)){
                listCoord.add(new Coordinates(y, x));
                x++; y++;
                if (coordExist(y, x, size) && ( (route.getCell(y, x).getLeft().isCross() && route.getCell(y, x).getTop().isEmpty()) || (route.getCell(y, x).getLeft().isEmpty() && route.getCell(y, x).getTop().isCross()) ) )
                    return listCoord;
            }
            listCoord.clear();
        }
        return null;
    }

    /**
     * Regarde si la technique 16 est valide dans la direction Sud-Ouest
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech16PosSW(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y-1, x+1, size) && ((route.getCell(y-1, x+1).getLeft().isLine() && route.getCell(y-1, x+1).getBottom().isCross()) || (route.getCell(y-1, x+1).getLeft().isCross() && route.getCell(y-1, x+1).getBottom().isLine()) ) ){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

            while(coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)){
                listCoord.add(new Coordinates(y, x));
                x--; y++;
                if (coordExist(y, x, size) && ( (route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getRight().isEmpty()) || (route.getCell(y, x).getTop().isEmpty() && route.getCell(y, x).getRight().isCross()) ) )
                    return listCoord;
            }
            listCoord.clear();
        }
        return null;
    }

    /**
     * [brochette de 2]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech16Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)){
            LinkedList<Coordinates> listCoord;

            if ( (listCoord = searchTech16PosNW(y, x, gridNumber, route, size)) != null)
                return listCoord;
            else if ( (listCoord = searchTech16PosNE(y, x, gridNumber, route, size)) != null)
                return listCoord;
            else if ( (listCoord = searchTech16PosSE(y, x, gridNumber, route, size)) != null)
                return listCoord;
            else if ( (listCoord = searchTech16PosSW(y, x, gridNumber, route, size)) != null)
                return listCoord;
        }
        return null;
    }

     /**
     * [2 atteint]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech17Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 2)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

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
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * [adjacent 3 et 1]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech18Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 1)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y-1, x, size) && Objects.equals(gridNumber[y-1][x], 3)){
                if( (!coordExist(y-1, x-1, size) || route.getCell(y-1, x-1).getBottom().isCross()) && route.getCell(y-1, x-1).getRight().isEmpty()){
                    listCoord.add(new Coordinates(y-1, x));
                    return listCoord;
                } else if( (!coordExist(y-1, x+1, size) || route.getCell(y-1, x+1).getBottom().isCross()) && route.getCell(y-1, x+1).getLeft().isEmpty()){
                    listCoord.add(new Coordinates(y-1, x));
                    return listCoord;
                }
            }
            if (coordExist(y, x+1, size) && Objects.equals(gridNumber[y][x+1], 3)){
                if( (!coordExist(y-1, x+1, size) || route.getCell(y-1, x+1).getLeft().isCross()) && route.getCell(y-1, x+1).getBottom().isEmpty()){
                    listCoord.add(new Coordinates(y, x+1));
                    return listCoord;
                } else if( (!coordExist(y+1, x+1, size) || route.getCell(y+1, x+1).getLeft().isCross()) && route.getCell(y+1, x+1).getTop().isEmpty()){
                    listCoord.add(new Coordinates(y, x+1));
                    return listCoord;
                }
            }
            if (coordExist(y+1, x, size) && Objects.equals(gridNumber[y+1][x], 3)){
                if( (!coordExist(y+1, x+1, size) || route.getCell(y+1, x+1).getTop().isCross()) && route.getCell(y+1, x+1).getLeft().isEmpty()){
                    listCoord.add(new Coordinates(y+1, x));
                    return listCoord;
                } else if( (!coordExist(y+1, x-1, size) || route.getCell(y+1, x-1).getTop().isCross()) && route.getCell(y+1, x-1).getRight().isEmpty()){
                    listCoord.add(new Coordinates(y+1, x));
                    return listCoord;
                }
            }
            if (coordExist(y, x-1, size) && Objects.equals(gridNumber[y][x-1], 3)){
                if( (!coordExist(y+1, x-1, size) || route.getCell(y+1, x-1).getRight().isCross()) && route.getCell(y+1, x-1).getTop().isEmpty()){
                    listCoord.add(new Coordinates(y, x-1));
                    return listCoord;
                } else if( (!coordExist(y-1, x-1, size) || route.getCell(y-1, x-1).getRight().isCross()) && route.getCell(y-1, x-1).getBottom().isEmpty()){
                    listCoord.add(new Coordinates(y, x-1));
                    return listCoord;
                }
            }

            listCoord.clear();
        }
        return null;
    }
    
    /**
     * [0 présent]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech19Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 0)
            && !(route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getRight().isCross() && route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getLeft().isCross()) ) {
                    LinkedList<Coordinates> listCoord = new LinkedList<>();
                    listCoord.add(new Coordinates(y, x));
                    return listCoord;
            }else
                return null;

    }

    /**
     * [1 en coin]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech20Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 1)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

            if (!coordExist(y, x-1, size) && !coordExist(y-1, x, size)
                && !(route.getCell(y, x).getLeft().isCross() && route.getCell(y, x).getTop().isCross()) )
                    listCoord.add(new Coordinates(y, x));
            else if (!coordExist(y-1, x, size) && !coordExist(y, x+1, size)
                && !(route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getRight().isCross()) )
                    listCoord.add(new Coordinates(y, x));
            else if (!coordExist(y, x+1, size) && !coordExist(y+1, x, size)
                && !(route.getCell(y, x).getRight().isCross() && route.getCell(y, x).getBottom().isCross()) )
                    listCoord.add(new Coordinates(y, x));
            else if (!coordExist(y+1, x, size) && !coordExist(y, x-1, size)
                && !(route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getLeft().isCross()) )
                    listCoord.add(new Coordinates(y, x));
            else
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * [3 & 1 diagonaux bis]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech21Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 3)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            if (route.getCell(y, x).getLeft().isLine() && route.getCell(y, x).getTop().isLine() && coordExist(y+1, x+1, size) && Objects.equals(gridNumber[y+1][x+1], 1)
                && !(route.getCell(y+1, x+1).getBottom().isCross() && route.getCell(y+1, x+1).getRight().isCross()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else if (route.getCell(y, x).getTop().isLine() && route.getCell(y, x).getRight().isLine() && coordExist(y+1, x-1, size) && Objects.equals(gridNumber[y+1][x-1], 1)
                && !(route.getCell(y+1, x-1).getLeft().isCross() && route.getCell(y+1, x-1).getBottom().isCross()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getRight().isLine() && route.getCell(y, x).getBottom().isLine() && coordExist(y-1, x-1, size) && Objects.equals(gridNumber[y-1][x-1], 1)
                && !(route.getCell(y-1, x-1).getLeft().isCross() && route.getCell(y-1, x-1).getTop().isCross()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else if (route.getCell(y, x).getLeft().isLine() && route.getCell(y, x).getBottom().isLine() && coordExist(y-1, x+1, size) && Objects.equals(gridNumber[y-1][x+1], 1)
                && !(route.getCell(y-1, x+1).getTop().isCross() && route.getCell(y-1, x+1).getRight().isCross()) )
                    listCoord.add(new Coordinates(y-1, x+1));
            else
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * [1 atteint]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech22Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 1)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

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
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * [Coin d'un 1 barré]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech23Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 1)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();

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
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * [1 & 1 diagonaux symétriques]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech24Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 1)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            if (coordExist(y-1, x-1, size) && Objects.equals(gridNumber[y-1][x-1], 1) && (route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getRight().isCross())
                && !(route.getCell(y-1, x-1).getTop().isCross() && route.getCell(y-1, x-1).getLeft().isCross()) )
                    listCoord.add(new Coordinates(y-1, x-1));
            else if (coordExist(y-1, x+1, size) && Objects.equals(gridNumber[y-1][x+1], 1) && (route.getCell(y, x).getBottom().isCross() && route.getCell(y, x).getLeft().isCross())
                && !(route.getCell(y-1, x+1).getTop().isCross() && route.getCell(y-1, x+1).getRight().isCross()) )
                    listCoord.add(new Coordinates(y-1, x+1));
            else if (coordExist(y+1, x+1, size) && Objects.equals(gridNumber[y+1][x+1], 1) && (route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getLeft().isCross())
                && !(route.getCell(y+1, x+1).getBottom().isCross() && route.getCell(y+1, x+1).getRight().isCross()) )
                    listCoord.add(new Coordinates(y+1, x+1));
            else if (coordExist(y+1, x-1, size) && Objects.equals(gridNumber[y+1][x-1], 1) && (route.getCell(y, x).getTop().isCross() && route.getCell(y, x).getRight().isCross())
                && !(route.getCell(y+1, x-1).getBottom().isCross() && route.getCell(y+1, x-1).getLeft().isCross()) )
                    listCoord.add(new Coordinates(y+1, x-1));
            else
                return null;

            return listCoord;
        }
        return null;
    }

    /**
     * [3 adjacents à deux 1 diagonaux]
     * 
     * Vérifie si cette technique est applicable à la position x, y
     * @param y,x les coordonnées de la case
     * @param gridNumber la grille de nombres
     * @param route la grille de tracés
     * @param size la taille de la grille
     * @return listCoord la liste des coordonnées des cases faisant partie de la technique (si elle est applicable)
     */
    public static LinkedList<Coordinates> searchTech25Pos(int y, int x, Integer [][] gridNumber, Grid route, int size) {
        if (coordExist(y, x, size) && Objects.equals(gridNumber[y][x], 3)){
            LinkedList<Coordinates> listCoord = new LinkedList<>();
            listCoord.add(new Coordinates(y, x));

            if(coordExist(y-1, x, size) && Objects.equals(gridNumber[y-1][x], 1) && coordExist(y, x+1, size) && Objects.equals(gridNumber[y][x+1], 1)
                && !(route.getCell(y-1, x).getLeft().isCross() && route.getCell(y-1, x).getTop().isCross() && route.getCell(y, x+1).getRight().isCross() && route.getCell(y, x+1).getBottom().isCross()) ){
                    listCoord.add(new Coordinates(y-1, x));
                    listCoord.add(new Coordinates(y, x+1));
            } else if (coordExist(y, x+1, size) && Objects.equals(gridNumber[y][x+1], 1) && coordExist(y+1, x, size) && Objects.equals(gridNumber[y+1][x], 1)
                && !(route.getCell(y, x+1).getTop().isCross() && route.getCell(y, x+1).getRight().isCross() && route.getCell(y+1, x).getBottom().isCross() && route.getCell(y+1, x).getLeft().isCross()) ){
                    listCoord.add(new Coordinates(y, x+1));
                    listCoord.add(new Coordinates(y+1, x));
            } else if (coordExist(y+1, x, size) && Objects.equals(gridNumber[y+1][x], 1) && coordExist(y, x-1, size) && Objects.equals(gridNumber[y][x-1], 1)
                && !(route.getCell(y+1, x).getRight().isCross() && route.getCell(y+1, x).getBottom().isCross() && route.getCell(y, x-1).getLeft().isCross() && route.getCell(y, x-1).getTop().isCross()) ){
                    listCoord.add(new Coordinates(y+1, x));
                    listCoord.add(new Coordinates(y, x-1));
            } else if (coordExist(y, x-1, size) && Objects.equals(gridNumber[y][x-1], 1) && coordExist(y-1, x, size) && Objects.equals(gridNumber[y-1][x], 1)
                && !(route.getCell(y, x-1).getBottom().isCross() && route.getCell(y, x-1).getLeft().isCross() && route.getCell(y-1, x).getTop().isCross() && route.getCell(y-1, x).getRight().isCross()) ){
                    listCoord.add(new Coordinates(y, x-1));
                    listCoord.add(new Coordinates(y-1, x));
            } else
                return null;

            return listCoord;
        }
        return null;
    }

    /* [Futures Techniques à finir d'implémenter]

    private static final int TOP = 0;
    private static final int RIGHT = 1;
    private static final int BOTTOM = 2;
    private static final int LEFT = 3;

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
