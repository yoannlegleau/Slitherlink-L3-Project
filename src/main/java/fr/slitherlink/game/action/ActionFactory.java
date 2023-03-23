package fr.slitherlink.game.action;

import fr.slitherlink.game.grid.Edge;
import fr.slitherlink.game.grid.EdgeType;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */
public  interface ActionFactory {

    static GameAction setEdgeLine(int x, int y, String t){
        return new EdgeAction(x, y, t , EdgeType.LINE);
    }

    static GameAction setEdgeCross(int x, int y, String t){
        return new EdgeAction(x, y, t , EdgeType.CROSS);
    }

    static GameAction setEdgeEmpty(int x, int y, String t){
        return new EdgeAction(x, y, t , EdgeType.EMPTY);
    }

    static GameAction undo(){
        return new UndoAction();
    }

    static GameAction redo(){
        return new RedoAction();
    }

}
