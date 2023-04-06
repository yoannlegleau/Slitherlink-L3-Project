package fr.slitherlink.game.help;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Classe représentant les informations d'une technique
 * @author Bruneau Antoine
 * @version 1, 23/02/2023
 */
@XmlRootElement
public class Technic {

    @XmlAttribute(name = "title")
    private String title;
    @XmlAttribute(name = "description")
    private String desc;

    public Technic(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

}