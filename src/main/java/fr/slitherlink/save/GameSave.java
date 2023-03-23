package fr.slitherlink.save;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.GameAction;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 22/02/2023
 */
@XmlRootElement
public class GameSave {

    int levelId;

    @XmlElement
    private List<GameAction> actions;

    public GameSave() {
        super();
    }
    public GameSave(Game game){
        this();
        this.levelId = game.getPuzzleId();
        this.actions = game.getActions();
    }

    @XmlAttribute(name = "id")
    public int getLevelId() {
        return levelId;
    }

    public List<GameAction> getActions() {
        return actions;
    }

}
