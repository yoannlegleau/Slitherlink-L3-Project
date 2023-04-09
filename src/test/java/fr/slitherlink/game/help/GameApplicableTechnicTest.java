package fr.slitherlink.game.help;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.slitherlink.game.grid.EdgeType;
import fr.slitherlink.game.grid.Grid;

/**
 * Classe de test de la classe ApplicableTechnic
 * @author Doneau Rafaël
 * @version 1, 16/03/2023
 */
public class GameApplicableTechnicTest {

    private static Grid grid;
    private static Integer[][] gridNumber;
    private static ApplicableTechnic appTech;
    
    @BeforeAll
    static void createApplicableTechnic(){
        appTech = ApplicableTechnic.getInstance();
        assertNotNull(appTech);
    }

    @BeforeEach
    void initApplicableTechnic(){
        grid = new Grid(3);
        gridNumber = new Integer[3][3];
    }

    /* Tests [Technique 0 & 3 adjacents]
     * 0 toujours pris en référence
     */

    @Test
    /* 3 0 */
    void getSearchTech1Pos1() {
        gridNumber[0][1] = 0;
        gridNumber[0][0] = 3;
        assertNotNull(appTech.searchTech(1, 0, 1, gridNumber, grid, grid.getSize()));
    }

    @Test
    /* 0 3 */
    void getSearchTech1Pos2() {
        gridNumber[0][0] = 0;
        gridNumber[0][1] = 3;
        assertNotNull(appTech.searchTech(1, 0,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3
     * 0
     */
    void getSearchTech1Pos3() {
        gridNumber[1][0] = 0;
        gridNumber[0][0] = 3;
        assertNotNull(appTech.searchTech(1, 1,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 0
     * 3
     */
    void getSearchTech1Pos4() {
        gridNumber[1][1] = 0;
        gridNumber[2][1] = 3;
        assertNotNull(appTech.searchTech(1, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech1Pos5() {
        gridNumber[0][2] = 0;
        gridNumber[0][0] = 3;
        assertNull(appTech.searchTech(1, 0,2,gridNumber,grid,grid.getSize()));
    }

    /* Tests [Technique 0 & 3 diagonaux] 
     * 0 toujours pris en référence
     */

    @Test
    /* 3
     *   0
     */
    void getSearchTech2Pos1() {
        gridNumber[1][1] = 0;
        gridNumber[0][0] = 3;
        assertNotNull(appTech.searchTech(2, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*     3
     *   0
     */
    void getSearchTech2Pos2() {
        gridNumber[1][1] = 0;
        gridNumber[0][2] = 3;
        assertNotNull(appTech.searchTech(2, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   0
     * 3
     */
    void getSearchTech2Pos3() {
        gridNumber[1][1] = 0;
        gridNumber[2][0] = 3;
        assertNotNull(appTech.searchTech(2, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   0
     *     3
     */
    void getSearchTech2Pos4() {
        gridNumber[1][1] = 0;
        gridNumber[2][2] = 3;
        assertNotNull(appTech.searchTech(2, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech2Pos5() {
        gridNumber[1][1] = 0;
        gridNumber[0][1] = 3;
        assertNull(appTech.searchTech(2, 1,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [Technique 3 & 3 adjacents] */

    @Test
    /* 3 de référence --> 3 3
     */
    void getSearchTech3Pos1() {
        gridNumber[0][0] = 3; // 3 de référence
        gridNumber[0][1] = 3;
        assertNotNull(appTech.searchTech(3, 0,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3 <-- 3 de référence
     * 3
     */
    void getSearchTech3Pos2() {
        gridNumber[0][0] = 3; // 3 de référence
        gridNumber[1][0] = 3;
        assertNotNull(appTech.searchTech(3, 0,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech3Pos3() {
        gridNumber[0][0] = 3;
        gridNumber[2][1] = 3;
        assertNull(appTech.searchTech(3, 0,0,gridNumber,grid,grid.getSize()));
    }

    /* Tests [Technique 3 & 3 diagonaux] */

    @Test
    /*   3 <-- 3 de référence
     * 3
     */
    void getSearchTech4Pos1() {
        gridNumber[0][1] = 3; // 3 de référence
        gridNumber[1][0] = 3;
        assertNotNull(appTech.searchTech(4, 0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3 <-- 3 de référence
     *   3
     */
    void getSearchTech4Pos2() {
        gridNumber[0][0] = 3; // 3 de référence
        gridNumber[1][1] = 3;
        assertNotNull(appTech.searchTech(4, 0,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech4Pos3() {
        gridNumber[0][0] = 3;
        gridNumber[2][1] = 3;
        assertNull(appTech.searchTech(4, 0,0,gridNumber,grid,grid.getSize()));
    }

    /* Tests [Coin d'un 2 tracé] */

    @Test
    /* _|
     *   2
     */
    void getSearchTech5Pos1() {
        gridNumber[1][1] = 2;
        grid.getCell(0, 0).getBottom().setType(EdgeType.LINE);
        grid.getCell(0, 0).getRight().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(5, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* _ 2
     *  |
     */
    void getSearchTech5Pos2() {
        gridNumber[0][1] = 2;
        grid.getCell(1, 0).getTop().setType(EdgeType.LINE);
        grid.getCell(1, 0).getRight().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(5, 0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  |_
     * 2
     */
    void getSearchTech5Pos3() {
        gridNumber[1][0] = 2;
        grid.getCell(0,1).getBottom().setType(EdgeType.LINE);
        grid.getCell(0,1).getLeft().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(5, 1,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 2 _
     *  |
     */
    void getSearchTech5Pos4() {
        gridNumber[0][0] = 2;
        grid.getCell(1,1).getTop().setType(EdgeType.LINE);
        grid.getCell(1,1).getLeft().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(5, 0,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech5Pos5() {
        gridNumber[0][0] = 2;
        assertNull(appTech.searchTech(5, 0,0,gridNumber,grid,grid.getSize()));
    }

    /* Tests [2 en coin] */

    @Test
    /* 2 en haut à gauche */
    void getSearchTech6Pos1() {
        gridNumber[0][0] = 2;
        assertNotNull(appTech.searchTech(6, 0,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 2 en haut à droite */
    void getSearchTech6Pos2() {
        gridNumber[0][2] = 2;
        assertNotNull(appTech.searchTech(6, 0,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 2 en bas à gauche */
    void getSearchTech6Pos3() {
        gridNumber[2][0] = 2;
        assertNotNull(appTech.searchTech(6, 2,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 2 en bas à droite */
    void getSearchTech6Pos4() {
        gridNumber[2][2] = 2;
        assertNotNull(appTech.searchTech(6, 2,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech6Pos5() {
        gridNumber[0][1] = 2;
        assertNull(appTech.searchTech(6, 0,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [3 en coin] */

    @Test
    /* 3 en haut à gauche */
    void getSearchTech7Pos1() {
        gridNumber[0][0] = 3;
        assertNotNull(appTech.searchTech(7, 0,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3 en haut à droite */
    void getSearchTech7Pos2() {
        gridNumber[0][2] = 3;
        assertNotNull(appTech.searchTech(7, 0,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3 en bas à gauche */
    void getSearchTech7Pos3() {
        gridNumber[2][0] = 3;
        assertNotNull(appTech.searchTech(7, 2,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3 en bas à droite */
    void getSearchTech7Pos4() {
        gridNumber[2][2] = 3;
        assertNotNull(appTech.searchTech(7, 2,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech7Pos5() {
        gridNumber[0][1] = 3;
        assertNull(appTech.searchTech(7, 0,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [3 & 3 diagnonaux avec des 2 entre] */

    /* Regarde si la technique 8 est valide dans la direction Sud-Ouest */
    @Test
    /*   3
     *  2
     * 3
     */
    void getSearchTech8PosSW1() {
        gridNumber[0][2] = 3;
        gridNumber[1][1] = 2;
        gridNumber[2][0] = 3;
        assertNotNull(ApplicableTechnic.searchTech8PosSW(0,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech8PosSW2() {
        gridNumber[0][2] = 3;
        gridNumber[1][1] = 2;
        gridNumber[2][1] = 3;
        assertNull(ApplicableTechnic.searchTech8PosSW(0,2,gridNumber,grid,grid.getSize()));
    }

    /* Regarde si la technique 8 est valide dans la direction Sud-Est */
    @Test
    /* 3
     *  2
     *   3
     */
    void getSearchTech8PosSE1() {
        gridNumber[0][0] = 3;
        gridNumber[1][1] = 2;
        gridNumber[2][2] = 3;
        assertNotNull(ApplicableTechnic.searchTech8PosSE(0,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech8PosSE2() {
        gridNumber[0][0] = 3;
        gridNumber[1][1] = 2;
        gridNumber[2][1] = 3;
        assertNull(ApplicableTechnic.searchTech8PosSE(0,0,gridNumber,grid,grid.getSize()));
    }

    /* Test avec la direction Sud-Est */
    @Test
    /* 3
     *  2
     *   2
     *    3
     */
    void getSearchTech8Pos1(){
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[0][0] = 3;
        gridNumber[1][1] = 2;
        gridNumber[2][2] = 2;
        gridNumber[3][3] = 3;
        assertNotNull(appTech.searchTech(8, 0,0,gridNumber,grid,grid.getSize()));
    }

    /* Test avec la direction Sud-Ouest */
    @Test
    /*    3
     *   2
     *  2
     * 3
     */
    void getSearchTech8Pos2(){
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[0][3] = 3;
        gridNumber[1][2] = 2;
        gridNumber[2][1] = 2;
        gridNumber[3][0] = 3;
        assertNotNull(appTech.searchTech(8, 0,3,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech8Pos3(){
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[0][0] = 3;
        gridNumber[1][1] = 2;
        gridNumber[2][2] = 2;
        gridNumber[3][2] = 3;
        assertNull(appTech.searchTech(8, 0,0,gridNumber,grid,grid.getSize()));
    }

    /* Tests [Coin d'un 3 barré] */

    @Test
    /*  x
     * x 3
     */
    void getSearchTech9Pos1() {
        gridNumber[1][1] = 3;
        grid.getCell(0, 0).getBottom().setType(EdgeType.CROSS);
        grid.getCell(0, 0).getRight().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(9, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x 3
     *  x
     */
    void getSearchTech9Pos2() {
        gridNumber[0][1] = 3;
        grid.getCell(1,0).getTop().setType(EdgeType.CROSS);
        grid.getCell(1,0).getRight().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(9, 0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  x
     * 3 x
     */
    void getSearchTech9Pos3() {
        gridNumber[1][0] = 3;
        grid.getCell(0,1).getBottom().setType(EdgeType.CROSS);
        grid.getCell(0,1).getLeft().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(9, 1,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3 x
     *  x
     */
    void getSearchTech9Pos4() {
        gridNumber[0][0] = 3;
        grid.getCell(1, 1).getTop().setType(EdgeType.CROSS);
        grid.getCell(1, 1).getLeft().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(9, 0,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech9Pos5() {
        gridNumber[0][0] = 3;
        grid.getCell(1, 1).getTop().setType(EdgeType.CROSS);
        assertNull(appTech.searchTech(9, 0,0,gridNumber,grid,grid.getSize()));
    }

    /* Tests [3 atteint] */

    @Test
    /* _
     *  3
     */
    void getSearchTech10Pos1() {
        gridNumber[1][1] = 3;
        grid.getCell(0, 0).getBottom().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(10, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* |
     *  3
     */
    void getSearchTech10Pos1b() {
        gridNumber[1][1] = 3;
        grid.getCell(0,0).getRight().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(10, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  _
     * 3
     */
    void getSearchTech10Pos2() {
        gridNumber[1][0] = 3;
        grid.getCell(0,1).getBottom().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(10, 1,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  |
     * 3
     */
    void getSearchTech10Pos2b() {
        gridNumber[1][0] = 3;
        grid.getCell(0,1).getLeft().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(10, 1,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* _3 */
    void getSearchTech10Pos3() {
        gridNumber[0][1] = 3;
        grid.getCell(1, 0).getTop().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(10, 0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  3
     * |
     */
    void getSearchTech10Pos3b() {
        gridNumber[0][1] = 3;
        grid.getCell(1,0).getRight().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(10, 0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3_ */
    void getSearchTech10Pos4() {
        gridNumber[0][0] = 3;
        grid.getCell(1, 1).getTop().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(10, 0,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3
     *  |
     */
    void getSearchTech10Pos4b() {
        gridNumber[0][0] = 3;
        grid.getCell(1,1).getLeft().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(10, 0,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech10Pos5() {
        gridNumber[0][0] = 3;
        assertNull(appTech.searchTech(10, 0,0,gridNumber,grid,grid.getSize()));
    }

    /* Tests [brochette de 2 atteignant un 3] */

    /* Regarde si la technique 11 est valide dans la direction Nord-Ouest */
    @Test
    /* 3
     *  2
     *   |
     */
    void getSearchTech11Pos1() {
        gridNumber[0][0] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(2, 2).getLeft().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech11PosNW(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3
     *  2 _
     */
    void getSearchTech11Pos1b() {
        gridNumber[0][0] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(2, 2).getTop().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech11PosNW(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech11Pos1c() {
        gridNumber[0][0] = 3;
        gridNumber[1][1] = 2;
        assertNull(ApplicableTechnic.searchTech11PosNW(1,1,gridNumber,grid,grid.getSize()));
    }

    /* Regarde si la technique 11 est valide dans la direction Nord-Est */
    @Test
    /*   3
     *  2
     * |
     */
    void getSearchTech11Pos2() {
        gridNumber[0][2] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(2, 0).getRight().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech11PosNE(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   3
     * _2
     */
    void getSearchTech11Pos2b() {
        gridNumber[0][2] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(2, 0).getTop().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech11PosNE(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech11Pos2c() {
        gridNumber[0][2] = 3;
        gridNumber[1][1] = 2;
        assertNull(ApplicableTechnic.searchTech11PosNE(1,1,gridNumber,grid,grid.getSize()));
    }

    /* Regarde si la technique 11 est valide dans la direction Sud-Est */
    @Test
    /* |
     *  2
     *   3
     */
    void getSearchTech11Pos3() {
        gridNumber[2][2] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(0, 0).getRight().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech11PosSE(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* _
     *  2
     *   3
     */
    void getSearchTech11Pos3b() {
        gridNumber[2][2] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(0, 0).getBottom().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech11PosSE(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech11Pos3c() {
        gridNumber[2][2] = 3;
        gridNumber[1][1] = 2;
        assertNull(ApplicableTechnic.searchTech11PosSE(1,1,gridNumber,grid,grid.getSize()));
    }

    /* Regarde si la technique 11 est valide dans la direction Sud-Ouest */
    @Test
    /*   |
     *  2
     * 3
     */
    void getSearchTech11Pos4() {
        gridNumber[2][0] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(0, 2).getLeft().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech11PosSW(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   _
     *  2
     * 3
     */
    void getSearchTech11Pos4b() {
        gridNumber[2][0] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(0, 2).getBottom().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech11PosSW(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech11Pos4c() {
        gridNumber[2][0] = 3;
        gridNumber[1][1] = 2;
        assertNull(ApplicableTechnic.searchTech11PosSW(1,1,gridNumber,grid,grid.getSize()));
    }

    /* Test avec la direction Nord-Ouest */
    @Test
    /* 3
     *  2
     *   2
     *    |
     */
    void getSearchTech11Pos5() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[0][0] = 3;
        gridNumber[1][1] = 2;
        gridNumber[2][2] = 2;
        grid.getCell(3, 3).getLeft().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(11, 2,2,gridNumber,grid,grid.getSize()));
    }

    /* Test avec la direction Nord-Est */
    @Test
    /*    3
     *   2
     *  2
     * |
     */
    void getSearchTech11Pos6() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[0][3] = 3;
        gridNumber[1][2] = 2;
        gridNumber[2][1] = 2;
        grid.getCell(3, 0).getRight().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(11, 2,1,gridNumber,grid,grid.getSize()));
    }

    /* Test avec la direction Sud-Est */
    @Test
    /* |
     *  2
     *   2
     *    3
     */
    void getSearchTech11Pos7() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[3][3] = 3;
        gridNumber[2][2] = 2;
        gridNumber[1][1] = 2;
        grid.getCell(0, 0).getRight().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(11, 1,1,gridNumber,grid,grid.getSize()));
    }

    /* Test avec la direction Sud-Ouest */
    @Test
    /*    |
     *   2
     *  2
     * 3
     */
    void getSearchTech11Pos8() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[3][0] = 3;
        gridNumber[2][1] = 2;
        gridNumber[1][2] = 2;
        grid.getCell(0, 3).getLeft().setType(EdgeType.LINE);
        assertNotNull(appTech.searchTech(11, 1,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech11Pos9() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[3][0] = 3;
        gridNumber[2][1] = 2;
        gridNumber[1][2] = 2;
        assertNull(appTech.searchTech(11, 1,2,gridNumber,grid,grid.getSize()));
    }


    /* Tests [3 & 2 diagonaux] */
    @Test
    /* 3
     *  2
     *  x
     */
    void getSearchTech12Pos() {
        gridNumber[0][0] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(2, 1).getTop().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(12, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3
     *  2x
     */
    void getSearchTech12Pos2() {
        gridNumber[0][0] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(1, 2).getLeft().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(12, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   3
     * x2
     */
    void getSearchTech12Pos3() {
        gridNumber[0][2] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(1, 0).getRight().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(12, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   3
     *  2
     *  x
     */
    void getSearchTech12Pos4() {
        gridNumber[0][2] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(2, 1).getTop().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(12, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  2x
     * 3
     */
    void getSearchTech12Pos5() {
        gridNumber[2][0] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(1, 2).getLeft().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(12, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  x
     *  2
     * 3
     */
    void getSearchTech12Pos6() {
        gridNumber[2][0] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(0, 1).getBottom().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(12, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x2
     *   3
     */
    void getSearchTech12Pos7() {
        gridNumber[2][2] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(1, 0).getRight().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(12, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  x
     *  2
     *   3
     */
    void getSearchTech12Pos8() {
        gridNumber[2][2] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(0, 1).getBottom().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(12, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech12Pos9() {
        gridNumber[2][2] = 3;
        gridNumber[1][1] = 2;
        assertNull(appTech.searchTech(12, 1,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [3 & 2 adjacents] */
    @Test
    /*  x
     * x2
     *  3
     */
    void getSearchTech13Pos() {
        gridNumber[2][1] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(1, 0).getTop().setType(EdgeType.CROSS);
        grid.getCell(0, 1).getLeft().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(13, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x
     * 2x
     * 3
     */
    void getSearchTech13Pos2() {
        gridNumber[2][1] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(0, 1).getRight().setType(EdgeType.CROSS);
        grid.getCell(1, 2).getTop().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(13, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 32x
     *  x
     */
    void getSearchTech13Pos3() {
        gridNumber[1][0] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(1,2).getBottom().setType(EdgeType.CROSS);
        grid.getCell(2,1).getRight().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(13, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  xx
     * 32
     */
    void getSearchTech13Pos4() {
        gridNumber[1][0] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(0,1).getRight().setType(EdgeType.CROSS);
        grid.getCell(1,2).getTop().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(13, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3
     * 2x
     * x
     */
    void getSearchTech13Pos5() {
        gridNumber[0][1] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(1, 2).getBottom().setType(EdgeType.CROSS);
        grid.getCell(2, 1).getRight().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(13, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  3
     * x2
     *  x
     */
    void getSearchTech13Pos6() {
        gridNumber[0][1] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(1, 0).getBottom().setType(EdgeType.CROSS);
        grid.getCell(2, 1).getLeft().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(13, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  x
     * x23
     */
    void getSearchTech13Pos7() {
        gridNumber[1][2] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(1, 0).getTop().setType(EdgeType.CROSS);
        grid.getCell(0, 1).getLeft().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(13, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x23
     *  x
     */
    void getSearchTech13Pos8() {
        gridNumber[1][2] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(2, 1).getLeft().setType(EdgeType.CROSS);
        grid.getCell(1, 0).getBottom().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(13, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech13Pos9() {
        gridNumber[1][2] = 3;
        gridNumber[1][1] = 2;
        assertNull(appTech.searchTech(13, 1,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [3 & 2 adjacents bis] */
    @Test
    /* 3
     * 2
     * x
     */
    void getSearchTech14Pos() {
        gridNumber[0][1] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(2, 1).getTop().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(14, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x
     * 2
     * 3
     */
    void getSearchTech14Pos2() {
        gridNumber[2][1] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(0, 1).getBottom().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(14, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x23 */
    void getSearchTech14Pos3() {
        gridNumber[1][2] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(1, 0).getRight().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(14, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 32x */
    void getSearchTech14Pos4() {
        gridNumber[1][0] = 3;
        gridNumber[1][1] = 2;
        grid.getCell(1, 2).getLeft().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(14, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech14Pos5() {
        gridNumber[1][2] = 3;
        gridNumber[1][1] = 2;
        assertNull(appTech.searchTech(14, 1,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [3 & 1 diagonaux] */
    @Test
    /* 3
     *  1x
     *  x
     */
    void getSearchTech15Pos() {
        gridNumber[0][0] = 3;
        gridNumber[1][1] = 1;
        grid.getCell(2, 1).getTop().setType(EdgeType.CROSS);
        grid.getCell(1, 2).getLeft().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(15, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   3
     * x1
     *  x
     */
    void getSearchTech15Pos2() {
        gridNumber[0][2] = 3;
        gridNumber[1][1] = 1;
        grid.getCell(2, 1).getTop().setType(EdgeType.CROSS);
        grid.getCell(1, 0).getRight().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(15, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  x
     * x1
     *   3
     */
    void getSearchTech15Pos3() {
        gridNumber[2][2] = 3;
        gridNumber[1][1] = 1;
        grid.getCell(0, 1).getBottom().setType(EdgeType.CROSS);
        grid.getCell(1, 0).getRight().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(15, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  x
     *  1x
     * 3
     */
    void getSearchTech15Pos4() {
        gridNumber[2][0] = 3;
        gridNumber[1][1] = 1;
        grid.getCell(0, 1).getBottom().setType(EdgeType.CROSS);
        grid.getCell(1, 2).getLeft().setType(EdgeType.CROSS);
        assertNotNull(appTech.searchTech(15, 1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech15Pos5() {
        gridNumber[1][1] = 1;
        gridNumber[2][2] = 3;
        assertNull(appTech.searchTech(15, 1,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [brochette de 2] */

    /* Regarde si la technique 16 est valide dans la direction Nord-Ouest */

    @Test
    /* x
     *  2
     *   2 <-- 2 de référence
     *     x
     *    |
     */
    void getSearchTech16PosNW1() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][1] = 2;
        gridNumber[2][2] = 2; // 2 de référence
        grid.getCell(1, 0).getTop().setType(EdgeType.CROSS);
        grid.getCell(2, 3).getBottom().setType(EdgeType.CROSS);
        grid.getCell(3, 2).getRight().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech16PosNW(2,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x
     *  2
     *   2 _<-- 2 de référence
     *    x
     */
    void getSearchTech16PosNW2() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][1] = 2;
        gridNumber[2][2] = 2; // 2 de référence
        grid.getCell(1, 0).getTop().setType(EdgeType.CROSS);
        grid.getCell(2, 3).getBottom().setType(EdgeType.LINE);
        grid.getCell(3, 2).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech16PosNW(2,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x
     * 2
     *  2 <-- 2 de référence
     *    x
     *   |
     */
    void getSearchTech16PosNW3() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][1] = 2;
        gridNumber[2][2] = 2; // 2 de référence
        grid.getCell(0, 1).getLeft().setType(EdgeType.CROSS);
        grid.getCell(2, 3).getBottom().setType(EdgeType.CROSS);
        grid.getCell(3, 2).getRight().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech16PosNW(2,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x
     * 2
     *  2 _<-- 2 de référence
     *   x
     */
    void getSearchTech16PosNW4() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][1] = 2;
        gridNumber[2][2] = 2; // 2 de référence
        grid.getCell(0, 1).getLeft().setType(EdgeType.CROSS);
        grid.getCell(2, 3).getBottom().setType(EdgeType.LINE);
        grid.getCell(3, 2).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech16PosNW(2,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech16PosNW5() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][1] = 2;
        gridNumber[2][2] = 2; // 2 de référence
        grid.getCell(0, 1).getLeft().setType(EdgeType.CROSS);
        grid.getCell(2, 3).getBottom().setType(EdgeType.LINE);
        assertNull(ApplicableTechnic.searchTech16PosNW(2,2,gridNumber,grid,grid.getSize()));
    }

    /* Regarde si la technique 16 est valide dans la direction Nord-Est */

    @Test
    /*     x
     *    2
     *   2 <-- 2 de référence
     * x
     *  |
     */
    void getSearchTech16PosNE1() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][2] = 2;
        gridNumber[2][1] = 2; // 2 de référence
        grid.getCell(1, 3).getTop().setType(EdgeType.CROSS);
        grid.getCell(2, 0).getBottom().setType(EdgeType.CROSS);
        grid.getCell(3, 1).getLeft().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech16PosNE(2,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*     x
     *    2
     *  _2 <-- 2 de référence
     *   x
     */
    void getSearchTech16PosNE2() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][2] = 2;
        gridNumber[2][1] = 2; // 2 de référence
        grid.getCell(1, 3).getTop().setType(EdgeType.CROSS);
        grid.getCell(2, 0).getBottom().setType(EdgeType.LINE);
        grid.getCell(3, 1).getLeft().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech16PosNE(2,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*    x
     *    2
     *   2 <-- 2 de référence
     * x
     *  |
     */
    void getSearchTech16PosNE3() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][2] = 2;
        gridNumber[2][1] = 2; // 2 de référence
        grid.getCell(0, 2).getRight().setType(EdgeType.CROSS);
        grid.getCell(2, 0).getBottom().setType(EdgeType.CROSS);
        grid.getCell(3, 1).getLeft().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech16PosNE(2,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*    x
     *    2
     *  _2 <-- 2 de référence
     *   x
     */
    void getSearchTech16PosNE4() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][2] = 2;
        gridNumber[2][1] = 2; // 2 de référence
        grid.getCell(0, 2).getRight().setType(EdgeType.CROSS);
        grid.getCell(2, 0).getBottom().setType(EdgeType.LINE);
        grid.getCell(3, 1).getLeft().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech16PosNE(2,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech16PosNE5() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][2] = 2;
        gridNumber[2][1] = 2; // 2 de référence
        grid.getCell(0, 2).getRight().setType(EdgeType.CROSS);
        grid.getCell(2, 0).getBottom().setType(EdgeType.LINE);
        assertNull(ApplicableTechnic.searchTech16PosNE(2,1,gridNumber,grid,grid.getSize()));
    }

    /* Regarde si la technique 16 est valide dans la direction Sud-Est */

    @Test
    /* _x
     *   2 <-- 2 de référence
     *    2
     *    x
     */
    void getSearchTech16PosSE1() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][1] = 2; // 2 de référence
        gridNumber[2][2] = 2;
        grid.getCell(1, 0).getTop().setType(EdgeType.LINE);
        grid.getCell(0, 1).getLeft().setType(EdgeType.CROSS);
        grid.getCell(3, 2).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech16PosSE(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* _x
     *   2 <-- 2 de référence
     *     2x
     */
    void getSearchTech16PosSE2() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][1] = 2; // 2 de référence
        gridNumber[2][2] = 2;
        grid.getCell(1, 0).getTop().setType(EdgeType.LINE);
        grid.getCell(0, 1).getLeft().setType(EdgeType.CROSS);
        grid.getCell(2, 3).getBottom().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech16PosSE(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  |
     *  x2 <-- 2 de référence
     *    2
     *    x
     */
    void getSearchTech16PosSE3() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][1] = 2; // 2 de référence
        gridNumber[2][2] = 2;
        grid.getCell(1, 0).getTop().setType(EdgeType.CROSS);
        grid.getCell(0, 1).getLeft().setType(EdgeType.LINE);
        grid.getCell(3, 2).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech16PosSE(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  |
     *  x2 <-- 2 de référence
     *    2x
     */
    void getSearchTech16PosSE4() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][1] = 2; // 2 de référence
        gridNumber[2][2] = 2;
        grid.getCell(1, 0).getTop().setType(EdgeType.CROSS);
        grid.getCell(0, 1).getLeft().setType(EdgeType.LINE);
        grid.getCell(2, 3).getBottom().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech16PosSE(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech16PosSE5() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][1] = 2; // 2 de référence
        gridNumber[2][2] = 2;
        grid.getCell(3, 2).getRight().setType(EdgeType.CROSS);
        grid.getCell(2, 3).getBottom().setType(EdgeType.LINE);
        assertNull(ApplicableTechnic.searchTech16PosSE(1,1,gridNumber,grid,grid.getSize()));
    }

    /* Regarde si la technique 16 est valide dans la direction Sud-Ouest */

    @Test
    /*    x_
     *   2 <-- 2 de référence
     *  2
     * x
     */
    void getSearchTech16PosSW1() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][2] = 2; // 2 de référence
        gridNumber[2][1] = 2;
        grid.getCell(1, 3).getTop().setType(EdgeType.LINE);
        grid.getCell(2, 0).getBottom().setType(EdgeType.CROSS);
        grid.getCell(0, 2).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech16PosSW(1,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   x_
     *  2 <-- 2 de référence
     * 2
     * x
     */
    void getSearchTech16PosSW2() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][2] = 2; // 2 de référence
        gridNumber[2][1] = 2;
        grid.getCell(1, 3).getTop().setType(EdgeType.LINE);
        grid.getCell(3, 1).getLeft().setType(EdgeType.CROSS);
        grid.getCell(0, 2).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech16PosSW(1,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*    |
     *   2x <-- 2 de référence
     *  2
     * x
     */
    void getSearchTech16PosSW3() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][2] = 2; // 2 de référence
        gridNumber[2][1] = 2;
        grid.getCell(1, 3).getTop().setType(EdgeType.CROSS);
        grid.getCell(2, 0).getBottom().setType(EdgeType.CROSS);
        grid.getCell(0, 2).getRight().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech16PosSW(1,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   |
     *  2x <-- 2 de référence
     * 2
     * x
     */
    void getSearchTech16PosSW4() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][2] = 2; // 2 de référence
        gridNumber[2][1] = 2;
        grid.getCell(1, 3).getTop().setType(EdgeType.CROSS);
        grid.getCell(3, 1).getLeft().setType(EdgeType.CROSS);
        grid.getCell(0, 2).getRight().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech16PosSW(1,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech16PosSW5() {
        grid = new Grid(4);
        gridNumber = new Integer[4][4];
        gridNumber[1][2] = 2; // 2 de référence
        gridNumber[2][1] = 2;
        grid.getCell(0, 2).getRight().setType(EdgeType.CROSS);
        grid.getCell(2, 0).getBottom().setType(EdgeType.LINE);
        assertNull(ApplicableTechnic.searchTech16PosSW(1,2,gridNumber,grid,grid.getSize()));
    }

    /* Test avec la direction Nord-Ouest */

    @Test
    /* x
     *  2
     *   2
     *    2 <-- 2 de référence
     *      x
     *     |
     */
    void getSearchTech16Pos1() {
        grid = new Grid(5);
        gridNumber = new Integer[5][5];
        gridNumber[1][1] = 2;
        gridNumber[2][2] = 2;
        gridNumber[3][3] = 2; // 2 de référence
        grid.getCell(1, 0).getTop().setType(EdgeType.CROSS);
        grid.getCell(3, 4).getBottom().setType(EdgeType.CROSS);
        grid.getCell(4, 3).getRight().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech16Pos(3,3,gridNumber,grid,grid.getSize()));
    }

    /* Test avec la direction Nord-Est */

    @Test
    /*      x
     *     2
     *    2
     *   2 <-- 2 de référence
     * x
     *  |
     */
    void getSearchTech16Pos2() {
        grid = new Grid(5);
        gridNumber = new Integer[5][5];
        gridNumber[1][3] = 2;
        gridNumber[2][2] = 2;
        gridNumber[3][1] = 2; // 2 de référence
        grid.getCell(1, 4).getTop().setType(EdgeType.CROSS);
        grid.getCell(3, 0).getBottom().setType(EdgeType.CROSS);
        grid.getCell(4, 1).getLeft().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech16Pos(3,1,gridNumber,grid,grid.getSize()));
    }

    /* Test avec la direction Sud-Est */

    @Test
    /* |
     * x2 <-- 2 de référence
     *   2
     *    2
     *      x
     */
    void getSearchTech16Pos3() {
        grid = new Grid(5);
        gridNumber = new Integer[5][5];
        gridNumber[1][1] = 2; // 2 de référence
        gridNumber[2][2] = 2;
        gridNumber[3][3] = 2;
        grid.getCell(1, 0).getTop().setType(EdgeType.LINE);
        grid.getCell(0, 1).getLeft().setType(EdgeType.CROSS);
        grid.getCell(4, 3).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech16Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    /* Test avec la direction Sud-Ouest */

    @Test
    /*     x_
     *    2 <-- 2 de référence
     *   2
     *  2
     * x
     */
    void getSearchTech16Pos4() {
        grid = new Grid(5);
        gridNumber = new Integer[5][5];
        gridNumber[1][3] = 2; // 2 de référence
        gridNumber[2][2] = 2;
        gridNumber[3][1] = 2;
        grid.getCell(1, 4).getTop().setType(EdgeType.LINE);
        grid.getCell(0, 3).getRight().setType(EdgeType.CROSS);
        grid.getCell(4, 1).getLeft().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech16Pos(1,3,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech16Pos5() {
        grid = new Grid(5);
        gridNumber = new Integer[5][5];
        gridNumber[1][3] = 2; // 2 de référence
        gridNumber[2][2] = 2;
        gridNumber[3][1] = 2;
        grid.getCell(1, 4).getTop().setType(EdgeType.LINE);
        grid.getCell(0, 3).getRight().setType(EdgeType.CROSS);
        grid.getCell(4, 1).getLeft().setType(EdgeType.LINE);
        assertNull(ApplicableTechnic.searchTech16Pos(1,3,gridNumber,grid,grid.getSize()));
    }

    /* Tests [2 atteint] */

    @Test
    /* _
     *  2
     *  x
     */
    void getSearchTech17Pos1() {
        gridNumber[0][1] = 2;
        grid.getCell(0, 0).getTop().setType(EdgeType.LINE);
        grid.getCell(1, 1).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* _
     *  2x
     */
    void getSearchTech17Pos2() {
        gridNumber[0][1] = 2;
        grid.getCell(0, 0).getTop().setType(EdgeType.LINE);
        grid.getCell(0, 2).getLeft().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* |
     *  2
     *  x
     */
    void getSearchTech17Pos3() {
        gridNumber[1][1] = 2;
        grid.getCell(0, 1).getLeft().setType(EdgeType.LINE);
        grid.getCell(2, 1).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* |
     *  2x
     */
    void getSearchTech17Pos4() {
        gridNumber[1][1] = 2;
        grid.getCell(0, 1).getLeft().setType(EdgeType.LINE);
        grid.getCell(1, 2).getLeft().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   |
     * x2
     */
    void getSearchTech17Pos5() {
        gridNumber[1][0] = 2;
        grid.getCell(0, 0).getRight().setType(EdgeType.LINE);
        grid.getCell(1, 0).getLeft().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(1,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  |
     * 2
     * x
     */
    void getSearchTech17Pos6() {
        gridNumber[1][0] = 2;
        grid.getCell(0, 0).getRight().setType(EdgeType.LINE);
        grid.getCell(2, 0).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(1,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   _
     * x2
     */
    void getSearchTech17Pos7() {
        gridNumber[0][1] = 2;
        grid.getCell(0, 2).getTop().setType(EdgeType.LINE);
        grid.getCell(0, 0).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  _
     * 2
     * x
     */
    void getSearchTech17Pos8() {
        gridNumber[0][1] = 2;
        grid.getCell(0, 2).getTop().setType(EdgeType.LINE);
        grid.getCell(1, 1).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x2_ */
    void getSearchTech17Pos9() {
        gridNumber[0][1] = 2;
        grid.getCell(0, 2).getBottom().setType(EdgeType.LINE);
        grid.getCell(0, 0).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x
     * 2_
     */
    void getSearchTech17Pos10() {
        gridNumber[1][1] = 2;
        grid.getCell(1, 2).getBottom().setType(EdgeType.LINE);
        grid.getCell(0, 1).getBottom().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x2
     *   |
     */
    void getSearchTech17Pos11() {
        gridNumber[0][1] = 2;
        grid.getCell(1, 1).getRight().setType(EdgeType.LINE);
        grid.getCell(0, 0).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x
     * 2
     *  |
     */
    void getSearchTech17Pos12() {
        gridNumber[1][1] = 2;
        grid.getCell(2, 1).getRight().setType(EdgeType.LINE);
        grid.getCell(0, 1).getBottom().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  x
     *  2
     * |
     */
    void getSearchTech17Pos13() {
        gridNumber[1][1] = 2;
        grid.getCell(2, 0).getRight().setType(EdgeType.LINE);
        grid.getCell(0, 1).getBottom().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  2x
     * |
     */
    void getSearchTech17Pos14() {
        gridNumber[0][1] = 2;
        grid.getCell(1, 0).getRight().setType(EdgeType.LINE);
        grid.getCell(0, 2).getLeft().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  x
     * _2
     */
    void getSearchTech17Pos15() {
        gridNumber[0][1] = 2;
        grid.getCell(1, 0).getTop().setType(EdgeType.LINE);
        grid.getCell(0, 1).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* _2x */
    void getSearchTech17Pos16() {
        gridNumber[0][1] = 2;
        grid.getCell(1, 0).getTop().setType(EdgeType.LINE);
        grid.getCell(0, 2).getLeft().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech17Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech17Pos17() {
        gridNumber[0][1] = 2;
        grid.getCell(1, 0).getTop().setType(EdgeType.LINE);
        assertNull(ApplicableTechnic.searchTech17Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [adjacent 3 et 1]
     * 1 toujours pris en reference
     */

    @Test
    /* x3
     *  1
     */
    void getSearchTech18Pos1() {
        gridNumber[0][1] = 3;
        gridNumber[1][1] = 1;
        grid.getCell(1, 0).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech18Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3x
     * 1
     */
    void getSearchTech18Pos2() {
        gridNumber[0][1] = 3;
        gridNumber[1][1] = 1;
        grid.getCell(1, 2).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech18Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  x
     * 1 3
     */
    void getSearchTech18Pos3() {
        gridNumber[1][2] = 3;
        gridNumber[1][1] = 1;
        grid.getCell(0, 1).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech18Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 1 3
     *  x
     */
    void getSearchTech18Pos4() {
        gridNumber[1][2] = 3;
        gridNumber[1][1] = 1;
        grid.getCell(2, 1).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech18Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 1
     * 3x
     */
    void getSearchTech18Pos5() {
        gridNumber[1][1] = 3;
        gridNumber[0][1] = 1;
        grid.getCell(1, 2).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech18Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  1
     * x3
     */
    void getSearchTech18Pos6() {
        gridNumber[1][1] = 3;
        gridNumber[0][1] = 1;
        grid.getCell(1, 0).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech18Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3 1
     *  x
     */
    void getSearchTech18Pos7() {
        gridNumber[1][2] = 3;
        gridNumber[1][1] = 1;
        grid.getCell(2, 1).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech18Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  x
     * 3 1
     */
    void getSearchTech18Pos8() {
        gridNumber[1][2] = 3;
        gridNumber[1][1] = 1;
        grid.getCell(0, 1).getRight().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech18Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech18Pos9() {
        gridNumber[1][2] = 3;
        gridNumber[1][1] = 1;
        assertNull(ApplicableTechnic.searchTech18Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [0 présent] */

    @Test
    /* 0 */
    void getSearchTech19Pos1() {
        gridNumber[1][1] = 0;
        assertNotNull(ApplicableTechnic.searchTech19Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech19Pos2() {
        gridNumber[1][1] = 1;
        assertNull(ApplicableTechnic.searchTech19Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [1 en coin] */

    @Test
    /* 1 en haut à gauche */
    void getSearchTech20Pos1() {
        gridNumber[0][0] = 1;
        assertNotNull(ApplicableTechnic.searchTech20Pos(0,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 1 en haut à droite */
    void getSearchTech20Pos2() {
        gridNumber[0][2] = 1;
        assertNotNull(ApplicableTechnic.searchTech20Pos(0,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 1 en bas à gauche */
    void getSearchTech20Pos3() {
        gridNumber[2][0] = 1;
        assertNotNull(ApplicableTechnic.searchTech20Pos(2,0,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 1 en bas à droite */
    void getSearchTech20Pos4() {
        gridNumber[2][2] = 1;
        assertNotNull(ApplicableTechnic.searchTech20Pos(2,2,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech20Pos5() {
        gridNumber[1][1] = 1;
        assertNull(ApplicableTechnic.searchTech20Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [3 & 1 diagonaux bis]
     * 3 toujours pris en reference
     */
    
    @Test
    /*  _
     * |3
     *   1
     */
    void getSearchTech21Pos1() {
        gridNumber[0][1] = 3;
        gridNumber[1][2] = 1;
        grid.getCell(0, 1).getTop().setType(EdgeType.LINE);
        grid.getCell(0, 0).getRight().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech21Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  _
     *  3|
     * 1
     */
    void getSearchTech21Pos2() {
        gridNumber[0][1] = 3;
        gridNumber[1][0] = 1;
        grid.getCell(0, 1).getTop().setType(EdgeType.LINE);
        grid.getCell(0, 2).getLeft().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech21Pos(0,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 1
     *  3|
     *  _
     */
    void getSearchTech21Pos3() {
        gridNumber[2][1] = 3;
        gridNumber[1][0] = 1;
        grid.getCell(2, 1).getBottom().setType(EdgeType.LINE);
        grid.getCell(2, 2).getLeft().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech21Pos(2,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   1
     * |3
     *  _
     */
    void getSearchTech21Pos4() {
        gridNumber[2][1] = 3;
        gridNumber[1][2] = 1;
        grid.getCell(2, 1).getBottom().setType(EdgeType.LINE);
        grid.getCell(2, 0).getRight().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech21Pos(2,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech21Pos5() {
        gridNumber[2][1] = 3;
        gridNumber[1][2] = 1;
        assertNull(ApplicableTechnic.searchTech21Pos(2,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [1 atteint] */

    @Test
    /* _x
     *  1
     */
    void getSearchTech22Pos1() {
        gridNumber[1][1] = 1;
        grid.getCell(0, 0).getRight().setType(EdgeType.CROSS);
        grid.getCell(0, 0).getBottom().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech22Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* |
     * x1
     */
    void getSearchTech22Pos2() {
        gridNumber[1][1] = 1;
        grid.getCell(0, 0).getBottom().setType(EdgeType.CROSS);
        grid.getCell(0, 0).getRight().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech22Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* |
     * 1x
     */
    void getSearchTech22Pos3() {
        gridNumber[1][1] = 1;
        grid.getCell(0, 2).getBottom().setType(EdgeType.CROSS);
        grid.getCell(0, 2).getLeft().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech22Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x_
     * 1
     */
    void getSearchTech22Pos4() {
        gridNumber[1][1] = 1;
        grid.getCell(0, 2).getLeft().setType(EdgeType.CROSS);
        grid.getCell(0, 2).getBottom().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech22Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 1_
     * x
     */
    void getSearchTech22Pos5() {
        gridNumber[1][1] = 1;
        grid.getCell(2, 2).getLeft().setType(EdgeType.CROSS);
        grid.getCell(2, 2).getTop().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech22Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 1x
     * |
     */
    void getSearchTech22Pos6() {
        gridNumber[1][1] = 1;
        grid.getCell(2, 2).getTop().setType(EdgeType.CROSS);
        grid.getCell(2, 2).getLeft().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech22Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x1
     * |
     */
    void getSearchTech22Pos7() {
        gridNumber[1][1] = 1;
        grid.getCell(2, 0).getTop().setType(EdgeType.CROSS);
        grid.getCell(2, 0).getRight().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech22Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* _1
     * x
     */
    void getSearchTech22Pos8() {
        gridNumber[1][1] = 1;
        grid.getCell(2, 0).getRight().setType(EdgeType.CROSS);
        grid.getCell(2, 0).getTop().setType(EdgeType.LINE);
        assertNotNull(ApplicableTechnic.searchTech22Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech22Pos9() {
        gridNumber[1][1] = 1;
        assertNull(ApplicableTechnic.searchTech22Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [Coin d'un 1 barré] */

    @Test
    /*  x
     * x1
     */
    void getSearchTech23Pos1() {
        gridNumber[1][1] = 1;
        grid.getCell(0, 1).getLeft().setType(EdgeType.CROSS);
        grid.getCell(1, 0).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech23Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x
     * 1x
     */
    void getSearchTech23Pos2() {
        gridNumber[1][1] = 1;
        grid.getCell(0, 1).getRight().setType(EdgeType.CROSS);
        grid.getCell(1, 2).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech23Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 1x
     * x
     */
    void getSearchTech23Pos3() {
        gridNumber[1][1] = 1;
        grid.getCell(2, 1).getRight().setType(EdgeType.CROSS);
        grid.getCell(1, 2).getBottom().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech23Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* x1
     *  x
     */
    void getSearchTech23Pos4() {
        gridNumber[1][1] = 1;
        grid.getCell(2, 1).getLeft().setType(EdgeType.CROSS);
        grid.getCell(1, 0).getBottom().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech23Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech23Pos5() {
        gridNumber[1][1] = 1;
        assertNull(ApplicableTechnic.searchTech23Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [1 & 1 diagonaux symétriques] */

    @Test
    /* 1
     *  1x <-- 1 de référence
     *  x
     */
    void getSearchTech24Pos1() {
        gridNumber[0][0] = 1;
        gridNumber[1][1] = 1;
        grid.getCell(1, 2).getLeft().setType(EdgeType.CROSS);
        grid.getCell(2, 1).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech24Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   1
     * x1  <-- 1 de référence
     *  x
     */
    void getSearchTech24Pos2() {
        gridNumber[0][2] = 1;
        gridNumber[1][1] = 1;
        grid.getCell(1, 0).getRight().setType(EdgeType.CROSS);
        grid.getCell(2, 1).getTop().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech24Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  x
     * x1  <-- 1 de référence
     *   1
     */
    void getSearchTech24Pos3() {
        gridNumber[2][2] = 1;
        gridNumber[1][1] = 1;
        grid.getCell(1, 0).getRight().setType(EdgeType.CROSS);
        grid.getCell(0, 1).getBottom().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech24Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*  x
     *  1x <-- 1 de référence
     * 1
     */
    void getSearchTech24Pos4() {
        gridNumber[2][0] = 1;
        gridNumber[1][1] = 1;
        grid.getCell(1, 2).getLeft().setType(EdgeType.CROSS);
        grid.getCell(0, 1).getBottom().setType(EdgeType.CROSS);
        assertNotNull(ApplicableTechnic.searchTech24Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech24Pos5() {
        gridNumber[2][0] = 1;
        gridNumber[1][1] = 1;
        assertNull(ApplicableTechnic.searchTech24Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    /* Tests [3 adjacents à deux 1 diagonaux]
     * 3 toujours pris en référence
     */

    @Test
    /* 1
     * 3 1
     */
    void getSearchTech25Pos1() {
        gridNumber[0][1] = 1;
        gridNumber[1][1] = 3;
        gridNumber[1][2] = 1;
        assertNotNull(ApplicableTechnic.searchTech25Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 3 1
     * 1
     */
    void getSearchTech25Pos2() {
        gridNumber[1][2] = 1;
        gridNumber[1][1] = 3;
        gridNumber[2][1] = 1;
        assertNotNull(ApplicableTechnic.searchTech25Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* 1 3
     *   1
     */
    void getSearchTech25Pos3() {
        gridNumber[1][0] = 1;
        gridNumber[1][1] = 3;
        gridNumber[2][1] = 1;
        assertNotNull(ApplicableTechnic.searchTech25Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /*   1
     * 1 3
     */
    void getSearchTech25Pos4() {
        gridNumber[1][0] = 1;
        gridNumber[1][1] = 3;
        gridNumber[0][1] = 1;
        assertNotNull(ApplicableTechnic.searchTech25Pos(1,1,gridNumber,grid,grid.getSize()));
    }

    @Test
    /* retourne faux */
    void getSearchTech25Pos5() {
        gridNumber[1][0] = 1;
        gridNumber[1][1] = 3;
        gridNumber[2][2] = 1;
        assertNull(ApplicableTechnic.searchTech25Pos(1,1,gridNumber,grid,grid.getSize()));
    }

}
