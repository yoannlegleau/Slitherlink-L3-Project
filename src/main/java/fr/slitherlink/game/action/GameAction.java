package fr.slitherlink.game.action;


import fr.slitherlink.game.Game;
import fr.slitherlink.save.GameActionAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
@XmlJavaTypeAdapter(GameActionAdapter.class)
public abstract class GameAction {

    public abstract void doAction(Game game);

}
