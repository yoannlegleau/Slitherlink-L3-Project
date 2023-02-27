package fr.slitherlink.save;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.GameAction;

import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 22/02/2023
 */
public class GameSave {

    int levelId;

    private List<GameAction> actions;

    public int getLevelId() {
        return levelId;
    }

    public void reloadGame(Game game){
        for (GameAction action: actions)
            action.doAction(game);
    }

    public List<GameAction> getActions() {
        return actions;
    }
}
