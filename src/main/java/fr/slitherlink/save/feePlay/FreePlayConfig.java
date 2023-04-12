package fr.slitherlink.save.feePlay;

import fr.slitherlink.save.gamesave.GameSave;
import fr.slitherlink.save.puzzle.Difficulty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 10/04/2023
 */

@XmlRootElement()
@XmlType(propOrder={"size","difficulty", "finishedFilter", "finishedLevelIdList", "gameSave"})
public class FreePlayConfig {

    private Integer size;
    public int getSize() {return size;}
    public void setSize(int size) {this.size = size;}

    private Difficulty difficulty;
    public Difficulty getDifficulty() {return difficulty;}
    public void setDifficulty(Difficulty difficulty) {this.difficulty = difficulty;}

    private Boolean finishedFilter;
    public Boolean getFinishedFilter() {return finishedFilter;}
    public void setFinishedFilter(Boolean finishedFilter) {this.finishedFilter = finishedFilter;}

    private GameSave gameSave;
    public GameSave getGameSave() {return gameSave;}
    public void setGameSave(GameSave gameSave) {this.gameSave = gameSave;}

    @XmlElement(name = "finishedLevelId")
    private List<Integer> finishedLevelIdList;
    public List<Integer> getFinishedLevelIdList() {return finishedLevelIdList;}

    public FreePlayConfig() {
        super();
        finishedLevelIdList = new ArrayList<>();
    }
}
