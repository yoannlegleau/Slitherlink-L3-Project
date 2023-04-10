package fr.slitherlink.save.feePlay;

import fr.slitherlink.save.XmlResourcesManageur;
import fr.slitherlink.save.gamesave.GameSave;
import fr.slitherlink.save.gamesave.GameSaveResourceManageur;
import fr.slitherlink.save.gamesave.GameSaver;
import fr.slitherlink.save.puzzle.Difficulty;
import fr.slitherlink.save.puzzle.PuzzleResourceManageur;
import fr.slitherlink.save.puzzle.PuzzleSave;

import javax.xml.bind.JAXBException;

/**
 * @author LE GLEAU Yoann
 * @version 1, 10/04/2023
 */
public class FreePlayRessourceManager implements GameSaver {

    private static final String FREE_PLAY_CONFIG_FILE = "config/freePlayConfig";

    private static FreePlayRessourceManager instance;

    private final FreePlayConfig config;

    private FreePlayRessourceManager() {
        try {
            config = XmlResourcesManageur.concertXmlToJava(FreePlayConfig.class, FREE_PLAY_CONFIG_FILE);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static FreePlayRessourceManager getInstance() {
        if (instance == null)
            instance = new FreePlayRessourceManager();
        return instance;
    }

    public void saveConfig(){
        try {
            XmlResourcesManageur.concertJavaToXml(config, FREE_PLAY_CONFIG_FILE);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSize() {return config.getSize();}
    public void setSize(int size) {
        config.setSize(size);
        saveConfig();
    }

    public Difficulty getDifficulty() {return config.getDifficulty();}
    public void setDifficulty(Difficulty difficulty) {
        config.setDifficulty(difficulty);
        saveConfig();
    }

    public Boolean getFinishedFilter() {return config.getFinishedFilter();}
    public void setFinishedFilter(Boolean finishedFilter) {
        config.setFinishedFilter(finishedFilter);
        saveConfig();
    }

    public void addFinishedLevelId(int id) {
        if (!config.getFinishedLevelIdList().contains(id))
            config.getFinishedLevelIdList().add(id);
        saveConfig();
    }
    public boolean isFinished(int id) {
        return config.getFinishedLevelIdList().contains(id);
    }

    @Override
    public GameSave loadSave() {
        return config.getGameSave();
    }

    @Override
    public PuzzleSave loadPuzzle() {
        return PuzzleResourceManageur.LoadPuzzle(config.getGameSave().getLevelId());
    }

    @Override
    public void save(GameSave gameSave) {
        config.setGameSave(gameSave);
        saveConfig();
    }

    public void createSave(int levelId) {
        GameSave gameSave = new GameSave();
        gameSave.setLevelId(levelId);
        config.setGameSave(gameSave);
        saveConfig();
    }
}
