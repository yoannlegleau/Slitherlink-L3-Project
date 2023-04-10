package fr.slitherlink.save.gamesave;

import fr.slitherlink.save.XmlResourcesManageur;
import fr.slitherlink.save.gamesave.GameSave;
import fr.slitherlink.save.puzzle.PuzzleResourceManageur;
import fr.slitherlink.save.puzzle.PuzzleSave;

import javax.xml.bind.JAXBException;

/**
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 * @pakage fr.slitherlink.save
 */
public class GameSaveResourceManageur implements GameSaver {
    public static final String SAVE_PATH = "save/";

    int id ;

    public GameSaveResourceManageur(int id) {
        this.id = id;
    }

    /**
     * Recupere les données d'une sauvgarde a partire de son id.
     * @param id idantifein du niveux
     * @return
     */
    @Override
    public GameSave loadSave(){
        GameSave gameSave = null;
        try {
            gameSave = XmlResourcesManageur.concertXmlToJava(GameSave.class,SAVE_PATH+ id);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return gameSave;
    }

    /**
     * Sauvegarde une partie dans les resource.
     * @param gameSave niveau a sauvegarder
     */
    @Override
    public void save(GameSave gameSave){
        try {
            XmlResourcesManageur.concertJavaToXml(gameSave, SAVE_PATH + gameSave.getLevelId());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PuzzleSave loadPuzzle() {
        return PuzzleResourceManageur.LoadPuzzle(id);
    }

}
