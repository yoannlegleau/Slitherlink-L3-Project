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
    }

    public List<GameAction> getTargets() {
        return targets;
    }

    @Override
    public void accept(ActionVisitore visitore) {
        visitore.visit(this);
    }

    @Override
    public void doAction(Game game) {
        if (!isCanceled())
            if (game.isAssumptionMode()) {
                game.getActions().remove(this);

            } else {
                this.game = game;
                game.setAssumptionMode(true);
                game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.ASSUMPTION_START.toString()));
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isCanceled())
            switch (GameActionTypes.valueOf( e.getActionCommand())) {
                //TODO savoir si il faut ajouter les Fin au targets
                case ASSUMPTION_START -> this.game.subscribe(this);
                case ASSUMPTION_STOP -> {
                    game.unsubscribe(this);
                    if(!((AssumptionStop) e.getSource()).isValid());
                        swapCanceled();
                }
                default -> targets.add((GameAction) e.getSource());

            }
    }

    @Override
    public void swapCanceled() {
        super.swapCanceled();
        game.setAssumptionMode(!isCanceled());

        //metre a jour l'interface meme pour les undo redo
        if(isCanceled())
            game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.ASSUMPTION_STOP.toString()));
        else
            game.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, GameActionTypes.ASSUMPTION_START.toString()));

        for (GameAction g : targets)
            g.swapCanceled();
    }
}
