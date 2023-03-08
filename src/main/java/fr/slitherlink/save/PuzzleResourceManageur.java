package fr.slitherlink.save;

import javax.xml.bind.JAXBException;

/**
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 * @pakage fr.slitherlink.save
 */
public class PuzzleResourceManageur {

    public static final String LEVEL_PATH = "puzzle/";

    /**
     * Recupere les données d'un niveau a partire de son id.
     * @param id idantifein du niveux
     * @return
     */
    public static PuzzleSave LoadPuzzle(int id){
        PuzzleSave level;
        try {
            level = (PuzzleSave) XmlResourcesManageur.concertXmlToJava(PuzzleSave.class,LEVEL_PATH+ id);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return level;
    }

    /**
     * Sauvegarde un niveau dans les resource. Doit aitre utiliser uniquement par l'éditeur de niveaux
     * @param level niveau a sauvegarder
     */
    public static void saveLevel(PuzzleSave level){
        try {
            XmlResourcesManageur.concertJavaToXml(level, LEVEL_PATH + level.getId());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
