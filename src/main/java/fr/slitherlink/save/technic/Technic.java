package fr.slitherlink.save.technic;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 * Classe représentant les informations d'une technique
 * @author Bruneau Antoine
 * @version 1, 23/02/2023
 */
@XmlRootElement(name = "technic")
public class Technic {

    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "description")
    private String desc;

    /**
     * Constructeur vide pour JAXB
     * utilisable uniquement pour la génération du fichier XML
     */
    public Technic() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getId() {
        return id;
    }

}