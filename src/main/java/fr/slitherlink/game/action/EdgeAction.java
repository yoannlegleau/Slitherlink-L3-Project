package fr.slitherlink.game.action;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.grid.Edge;
import fr.slitherlink.game.grid.EdgeType;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/03/2023
 * @pakage fr.levelEditor
 */
public class EdgeAction extends GameAction {

    private final EdgeType type;
    private final int x;
    private final int y;
    private final String t;

    public EdgeAction(int i, int i1, String t, EdgeType line) {
        this.x = i;
        this.y = i1;
        this.t = t;
        this.type = line;
    }

    @Override
    public void doAction(Game game) {
        Edge curent = game.getCurrentGrid().getCell(x, y).getTop();
        switch (t){
            case "T":
                curent = game.getCurrentGrid().getCell(x, y).getTop();
                break;
            case "R":
                curent = game.getCurrentGrid().getCell(x, y).getRight();
                break;
            case "B":
                curent = game.getCurrentGrid().getCell(x, y).getBottom();
                break;
            case "L":
                curent = game.getCurrentGrid().getCell(x, y).getLeft();
                break;
        }
        curent.setType(type);
    }

}
