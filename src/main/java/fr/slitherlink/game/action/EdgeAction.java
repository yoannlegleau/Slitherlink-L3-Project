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

    int x;
    int y;
    String t;

    public EdgeAction(int i, int i1, String t) {
        this.x = i;
        this.y = i1;
        this.t = t;
    }

    @Override
    public void doAction(Game game) {
        super.doAction(game);
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
        changeEdge(curent);
    }

    private void changeEdge(Edge curent) {
        if (curent.getType() == EdgeType.LINE)
            curent.setType(EdgeType.EMPTY);
        else
            curent.setType(EdgeType.EMPTY);
    }
}
