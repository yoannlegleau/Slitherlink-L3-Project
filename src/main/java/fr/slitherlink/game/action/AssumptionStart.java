package fr.slitherlink.game.action;

import fr.slitherlink.game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 * @pakage fr.slitherlink.game.action
 */
public class AssumptionStart extends GameAction implements ActionTargeter , ActionListener {

    private List<Integer> targetIds;

    @Override
    public List<Integer> getTargetId() {
        return null;
    }

    @Override
    public void setTargetId(Integer targetId) {
        return null;
    }

    @Override
    public void doAction(Game game) {
        if (isCanceled())
            return;
        game.subscribe(this);
        game.setAssumptionMode(true);
        game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.ASSUMPTION_START.toString()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
