package fr.slitherlink.save.gamesave;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionVisitore;
import fr.slitherlink.game.action.GameAction;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 22/02/2023
 */
@XmlRootElement
public class GameSave {

    private Integer levelId;
    @XmlAttribute(name = "id")
    public int getLevelId() {
        return levelId;
    }
    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    @XmlElement(name = "action")
    private List<GameActionSave> gameActionSaves;

    public GameSave() {
        super();
    }
    public GameSave(Game game){
        this();
        this.levelId = game.getPuzzleId();
        setActionsFromGameAction(game.getActions());
    }



    public void setActionsFromGameAction(List<GameAction> gameActions) {
        gameActionSaves = GameActionConverter.toGameActionSave(gameActions);
    }

    public List<GameAction> getActionsToGameAction() {
        return GameActionConverter.toGameAction(gameActionSaves);
    }

}
