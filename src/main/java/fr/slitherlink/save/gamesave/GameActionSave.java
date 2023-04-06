package fr.slitherlink.save.gamesave;

import fr.slitherlink.game.action.GameActionTypes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 */

@XmlRootElement(name = "action")
public class GameActionSave {
    @XmlAttribute(name = "type")
    public String gameActionTypes;

    @XmlAttribute(name = "canceled")
    public Boolean canceled;

    @XmlAttribute(name = "validated")
    public Boolean validated;

    @XmlElement(name = "target")
    public List<Integer> target;

    public Integer x;
    public Integer y;
    public String t;

    public static GameActionSave getGameActionSave(){
        return new GameActionSave();
    }

    public GameActionSave() {
        super();
    }

    public GameActionSave setGameActionTypes(String gameActionTypes) {
        this.gameActionTypes =gameActionTypes;
        return this;
    }

    public GameActionSave setGameActionTypes(GameActionTypes gameActionTypes) {
        return setGameActionTypes(String.valueOf(gameActionTypes));
    }

    public GameActionSave setCanceled(Boolean canceled) {
        if (canceled)
            this.canceled = canceled;
        return this;
    }

    public Boolean getValidated() {
        return validated;
    }

    @XmlTransient
    public GameActionSave setValidated(Boolean validated) {
        this.validated = validated;
        return this;
    }


    public GameActionSave setTarget(List<Integer> target) {
        this.target = target;
        return this;
    }

    @XmlTransient
    public List<Integer> getTarget() {
        return target;
    }

    public GameActionSave setX(Integer x) {
        this.x = x;
        return this;
    }

    public GameActionSave setY(Integer y) {
        this.y = y;
        return this;
    }

    public GameActionSave setT(String t) {
        this.t = t;
        return this;
    }

    @XmlTransient
    public Boolean isCanceled() {
        if (canceled == null)
            return false;
        return canceled;
    }
}
