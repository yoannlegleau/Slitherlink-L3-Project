package fr.slitherlink.game.action;

import fr.slitherlink.game.action.actions.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 */
public interface ActionVisitore {


    void visit(AssumptionStart assumptionStart);

    void visit(AssumptionStop assumptionStop);

    void visit(EdgeAction edgeAction);

    void visit(RedoAction redoAction);

    void visit(UndoAction undoAction);

    void visit(HelpAction hintAction);

}
