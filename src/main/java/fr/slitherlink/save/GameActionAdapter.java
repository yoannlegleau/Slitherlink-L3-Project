package fr.slitherlink.save;

import fr.slitherlink.game.action.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 * @pakage fr.slitherlink.save
 */
public class GameActionAdapter extends XmlAdapter<GameActionAdapter.AdaptedAction, GameAction> {


    @Override
    public AdaptedAction marshal(GameAction action) throws Exception {
        AdaptedAction adaptedAction = new AdaptedAction();
        if (action instanceof EdgeAction){
            adaptedAction.x = ((EdgeAction) action).getX();
            adaptedAction.y = ((EdgeAction) action).getY();
            adaptedAction.t = ((EdgeAction) action).getT();
            switch (((EdgeAction) action).getType()){
                case LINE:
                    adaptedAction.gameActionTypes = GameActionTypes.SET_LINE.value();
                    break;
                case CROSS:
                    adaptedAction.gameActionTypes = GameActionTypes.SET_CROSS.value();
                    break;
                case EMPTY:
                    adaptedAction.gameActionTypes = GameActionTypes.SET_EMPTY.value();
                    break;
            }

        }
        if (action instanceof UndoAction){
            adaptedAction.gameActionTypes = GameActionTypes.UNDO.value();
            adaptedAction.target = ((ActionTargeter)action).getTargetId();
        }
        if (action instanceof RedoAction){
            adaptedAction.gameActionTypes = GameActionTypes.REDO.value();
            adaptedAction.target = ((ActionTargeter)action).getTargetId();
        }
        if (action.isCanceled())
            adaptedAction.canceled = true;
        return adaptedAction;
    }

    @Override
    public GameAction unmarshal(AdaptedAction v) throws Exception {
        GameAction action = null;
        switch (GameActionTypes.fromValue(v.gameActionTypes)) {
            case SET_LINE:
                action = ActionFactory.setEdgeLine(v.x, v.y, v.t);
                break;
            case SET_CROSS:
                action = ActionFactory.setEdgeCross(v.x, v.y, v.t);
                break;
            case SET_EMPTY:
                action = ActionFactory.setEdgeEmpty(v.x, v.y, v.t);
                break;
            case UNDO:
                action = ActionFactory.undo();
                ((ActionTargeter)action).setTargetId(v.target);
                break;
            case REDO:
                action = ActionFactory.redo();
                ((ActionTargeter)action).setTargetId(v.target);
                break;
        }
        if (v.canceled != null)
            action.setCanceled(true);
        return action;
    }

        public static class AdaptedAction {

            @XmlAttribute(name = "type")
            public String gameActionTypes;

            @XmlAttribute(name = "canceled")
            public Boolean canceled;

            public Integer target;
            public Integer x;
            public Integer y;
            public String t;



        }
    }
