package fr.slitherlink.save.technic;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Classe représentant les informations d'une technique
 * @author Bruneau Antoine
 * @version 1, 23/02/2023
 */
@XmlRootElement(name = "technic")
public class Technic {

    @XmlAttribute(name = "title")
    private String title;
    @XmlAttribute(name = "description")
    private String desc;

    public Technic() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

}