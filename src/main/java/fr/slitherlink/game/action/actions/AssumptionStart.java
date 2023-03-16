package fr.slitherlink.game.action.actions;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionVisitore;
import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.GameActionTypes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 * @pakage fr.slitherlink.game.action
 */
public class AssumptionStart extends GameAction implements ActionListener {

    private List<GameAction> targets;

    private Game game;

    public AssumptionStart() {
        super();
        setGameActionTypes(GameActionTypes.ASSUMPTION_START);
        targets = new ArrayList<>();
    }

    public void addTarget(GameAction gameAction) {
        targets.add(gameAction);
    }

    public List<GameAction> getTargets() {
        return targets;
    }

    public void setSubscribed(boolean subscribed) {
        if (game == null)
            return;

        if (subscribed) {
            if (!game.isSubscribed(this))
                game.subscribe(this);
        } else
            if (game.isSubscribed(this))
                game.unsubscribe(this);

    }

    @Override
    public void accept(ActionVisitore visitore) {
        visitore.visit(this);
    }

    @Override
    public void doAction(Game game) {
        if (isCanceled())
            return;

        this.game = game;
        if (game.isAssumptionMode())
            game.getActions().remove(this);
        else {
            setSubscribed(true);
            game.setAssumptionMode(true);
            game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.ASSUMPTION_START.toString()));
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isCanceled())
            switch (GameActionTypes.valueOf(e.getActionCommand())) {
                case ASSUMPTION_CANCEL,ASSUMPTION_VALID -> {
                        setSubscribed(false);
                }
                case RESET -> setSubscribed(false);
                case UNDO, REDO, SET_CROSS,SET_EMPTY,SET_LINE -> {
                    if (!targets.contains(e.getSource()) && !asAssumptionStartTarget((GameAction) e.getSource()))
                        targets.add((GameAction) e.getSource());
                }
            }
    }

    private boolean asAssumptionStartTarget(GameAction gameAction){
        return switch (gameAction.getGameActionTypes()) {
            case ASSUMPTION_START -> true;
            case UNDO -> asAssumptionStartTarget(((UndoAction) gameAction).getTarget());
            case REDO -> asAssumptionStartTarget(((RedoAction) gameAction).getTarget());
            case ASSUMPTION_VALID, ASSUMPTION_CANCEL -> asAssumptionStartTarget(((AssumptionStop) gameAction).getTarget());
            default -> false;
        };
    }


    @Override
    public void swapCanceled() {
        super.swapCanceled();
        setSubscribed(!isCanceled());
        game.setAssumptionMode(!isCanceled());
        if(isCanceled())
            game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.ASSUMPTION_CANCEL.toString()));
        else
            game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.ASSUMPTION_START.toString()));

        for (GameAction g : targets)
            g.swapCanceled();


    }
}
