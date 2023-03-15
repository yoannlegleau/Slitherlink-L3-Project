package fr.slitherlink.game.grid;

import javafx.beans.value.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
public class Edge {
    private EdgeType type;

    private List<ActionListener> subscribers;

    public Edge() {
        subscribers = new ArrayList<>();
        this.type = EdgeType.EMPTY;
    }

    public void setType(EdgeType newType) {
        type = newType;
        notifySubscribers();
    }
    public EdgeType getType() {
        return type;
    }

    private void notifySubscribers() {
        for (ActionListener subscriber : subscribers) {
            subscriber.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, type.toString() ));
        }
    }

    /**
     * On compare les types des deux edges et on ne prand en compte que les lignes
     * @param obj Edge a comparer
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Edge edge = (Edge) obj;
        if (type == EdgeType.LINE && edge.type != EdgeType.LINE)
            return false;
        if (type != EdgeType.LINE && edge.type == EdgeType.LINE)
            return false;
        return true;
    }

    public void subscribe(ActionListener drawingEdge) {
        subscribers.add(drawingEdge);
    }
}
