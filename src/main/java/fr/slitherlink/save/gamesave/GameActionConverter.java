package fr.slitherlink.save.gamesave;

import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.game.action.actions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 */
public class GameActionConverter{
    public static List<GameActionSave> toGameActionSave(List<GameAction> gameActions) {
        GameActionToSaveVisitore visitore = new GameActionToSaveVisitore(gameActions);
        return visitore.getGameActionSave();
    }

    public static List<GameAction> toGameAction(List<GameActionSave> actions) {
        return ReloadGameActionVisitore.reloadGameAction(actions);
    }

}
