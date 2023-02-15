package fr.slitherlink.save;

import javax.xml.bind.JAXBException;

/**
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 * @pakage fr.slitherlink.save
 */
public class LevelResourceManageur {

    public static final String LEVEL_PATH = "level/";

    /**
     * Recupere les données d'un niveau a partire de son id.
     * @param id idantifein du niveux
     * @return
     */
    public static Level LoadLevel(int id){
        Level level;
        try {
            level = (Level) XmlResourcesManageur.concertXmlToJava(Level.class,LEVEL_PATH+ id);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return level;
    }

    /**
     * Sauvegarde un niveau dans les resource. Doit aitre utiliser uniquement par l'éditeur de niveaux
     * @param level niveau a sauvegarder
     */
    public static void saveLevel(Level level){
        try {
            XmlResourcesManageur.concertJavaToXml(level, LEVEL_PATH + level.getId());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
