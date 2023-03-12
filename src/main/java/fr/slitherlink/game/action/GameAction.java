package fr.slitherlink.game.action;


import fr.slitherlink.game.Game;
import fr.slitherlink.save.GameActionAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
@XmlJavaTypeAdapter(GameActionAdapter.class)
public abstract class GameAction {

    private Boolean canceled = false;

    private Boolean cancelable = true;

    public abstract void doAction(Game game);

    public Boolean isCanceled() {
        return canceled;
    }

    public Boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(Boolean cancelable) {
        this.cancelable = cancelable;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public  void swapCanceled(){
        canceled = !canceled;
    }
}
