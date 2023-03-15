package fr.slitherlink.game.action;

import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 12/03/2023
 */
public interface ActionTargeter {

    List<GameAction> getTargets();

    void addTarge(GameAction targetId);

}
