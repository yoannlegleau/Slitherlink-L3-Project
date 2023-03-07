package fr.slitherlink.save;

import fr.slitherlink.game.action.EdgeAction;
import fr.slitherlink.game.action.GameAction;

import javax.swing.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 * @pakage fr.slitherlink.save
 */
public class GameActionAdapter extends XmlAdapter<GameActionAdapter.AdaptedAction, GameAction> {


    @Override
    public AdaptedAction marshal(GameAction v) throws Exception {
        if (v instanceof EdgeAction){
            AdaptedAction adaptedAction = new AdaptedAction();
            adaptedAction.x = ((EdgeAction) v).getX();
            adaptedAction.y = ((EdgeAction) v).getY();
            adaptedAction.t = ((EdgeAction) v).getT();
            return adaptedAction;
        }
        return null;
    }

    @Override
    public GameAction unmarshal(AdaptedAction v) throws Exception {
        return null;
    }

    public static class AdaptedAction {
        public int x;
        public int y;
        public String t;
    }
}
