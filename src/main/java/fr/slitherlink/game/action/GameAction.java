package fr.slitherlink.game.action;


import fr.slitherlink.game.Game;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
public abstract class GameAction {

    GameActionTypes gameActionTypes;

    private Boolean canceled = false;

    public GameAction() {
        super();
    }

    public GameActionTypes getGameActionTypes() {
        return gameActionTypes;
    }

    public void setGameActionTypes(GameActionTypes gameActionTypes) {
        this.gameActionTypes = gameActionTypes;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public abstract void doAction(Game game);

    public abstract void accept(ActionVisitore visitore);

    public Boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public  void swapCanceled(){
        canceled = !canceled;
    }

}
