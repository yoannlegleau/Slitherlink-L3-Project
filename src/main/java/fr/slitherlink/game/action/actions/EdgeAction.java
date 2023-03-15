package fr.slitherlink.game.action.actions;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionVisitore;
import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.game.grid.Edge;
import fr.slitherlink.game.grid.EdgeType;

import javax.swing.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.event.ActionEvent;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/03/2023
 * @pakage fr.levelEditor
 */

public class EdgeAction extends GameAction {


    private EdgeType type;

    private int x;

    private int y;

    private String t;

    public EdgeAction() {
        super();
    }

    public EdgeAction(int i, int i1, String t, EdgeType line) {
        this();
        this.x = i;
        this.y = i1;
        this.t = t;
        this.type = line;
    }

    @Override
    public void accept(ActionVisitore visitore) {
        visitore.visit(this);
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
        game.checkisWin();
        game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "SET_"+type));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getT() {
        return t;
    }

    public EdgeType getType() {
        return type;
    }

    public void setType(EdgeType type) {
        this.type = type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setT(String t) {
        this.t = t;
    }
}
