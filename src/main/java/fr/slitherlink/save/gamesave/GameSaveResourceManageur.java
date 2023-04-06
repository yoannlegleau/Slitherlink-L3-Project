package fr.slitherlink.save.gamesave;

import fr.slitherlink.save.XmlResourcesManageur;
import fr.slitherlink.save.gamesave.GameSave;

import javax.xml.bind.JAXBException;

/**
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 * @pakage fr.slitherlink.save
 */
public class GameSaveResourceManageur {

    public static final String SAVE_PATH = "save/";

    /**
     * Recupere les données d'une sauvgarde a partire de son id.
     * @param id idantifein du niveux
     * @return
     */
    public static GameSave LoadLevel(int id){
        GameSave gameSave = null;
        try {
            gameSave = (GameSave) XmlResourcesManageur.concertXmlToJava(GameSave.class,SAVE_PATH+ id);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return gameSave;
    }

    /**
     * Sauvegarde une partie dans les resource.
     * @param gameSave niveau a sauvegarder
     */
    public static void saveGameSave(GameSave gameSave){
        try {
            XmlResourcesManageur.concertJavaToXml(gameSave, SAVE_PATH + gameSave.getLevelId());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
