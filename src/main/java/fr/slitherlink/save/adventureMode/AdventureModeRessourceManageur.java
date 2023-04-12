package fr.slitherlink.save.adventureMode;

import fr.slitherlink.save.XmlResourcesManageur;

import javax.xml.bind.JAXBException;

/**
 * @author LE GLEAU Yoann
 * @version 1, 12/04/2023
 */
public class AdventureModeRessourceManageur {

    private static final String ADVENTURE_MODE_SAVE_FILE_NAME = "config/adventureMod";

    AdventureModeSave save;

    static AdventureModeRessourceManageur instance = new AdventureModeRessourceManageur();

    public static AdventureModeRessourceManageur getInstence(){
        return instance;
    }

    private AdventureModeRessourceManageur(){
        try {
            save = XmlResourcesManageur.concertXmlToJava(AdventureModeSave.class,ADVENTURE_MODE_SAVE_FILE_NAME);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        if (save == null)
            save = new AdventureModeSave();
    }

    public Boolean isLevelFinished(int levelId){
        return save.getFinishedLevelIdList().contains(levelId);
    }

    public void addLevel(int levelId){
        if (isLevelFinished(levelId))
            return;
        save.getFinishedLevelIdList().add(levelId);
        try {
            XmlResourcesManageur.concertJavaToXml(save,ADVENTURE_MODE_SAVE_FILE_NAME);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
