package fr.slitherlink.save.puzzle;

import fr.slitherlink.save.XmlResourcesManageur;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe permettant de charger les niveaux depuis les resources
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 * @pakage fr.slitherlink.save
 */
public class PuzzleResourceManageur {

    public static final String LEVEL_PATH = "puzzle/";

    public static List<Integer> getLevelIdList(){
        String dir = XmlResourcesManageur.RESOURCES_PATH + "puzzle";
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(file -> Integer.parseInt( file.getName().replace(".xml", "")))
                .collect(Collectors.toSet())
                .stream().sorted().toList();
    }

    public static List<PuzzleSave> getLevelList(){
        return getLevelIdList().stream().map(PuzzleResourceManageur::LoadPuzzle).toList();
    }

    public  static  List<PuzzleSave> getLevelListByDifficulty(Difficulty difficulty){
        return getLevelList().stream().filter(puzzleSave -> puzzleSave.getDifficulty() == difficulty).toList();
    }

    /**
     * Recupere un Objet contenen les données d'un niveau a partire de son id.
     * @param id identifiant du niveau
     * @return
     */
    public static PuzzleSave LoadPuzzle(int id){
        PuzzleSave level;
        try {
            level = XmlResourcesManageur.concertXmlToJava(PuzzleSave.class,LEVEL_PATH+ id);
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
