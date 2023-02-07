package fr.slitherlink.save;

import javafx.fxml.FXML;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Structure de données sauvegarder represent des niveaux
 * @author LE GLEAU Yoann
 * @version 1, 07/02/2023
 * @pakage fr.slitherlink.save
 */


@XmlRootElement(name = "Level")
public class LevelData {
    int id;
    int size;
}
