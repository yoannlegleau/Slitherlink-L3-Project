package fr.slitherlink.save.technic;

import java.util.ArrayList;

import fr.slitherlink.save.XmlResourcesManageur;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe stockant les techniques de résolution
 * @author Bruneau Antoine
 * @version 1, 31/03/2023
 */
@XmlRootElement(name = "technicList")
public class ListTechnic {

    @XmlTransient
    private static ListTechnic instance = null;

    @XmlElement(name = "technic")
    private ArrayList<Technic> listTechnic;

    /**
     * Constructeur vide pour JAXB
     * utilisable uniquement pour la génération du fichier XML
     */ 
    public ListTechnic() {
        super();
        listTechnic = new ArrayList<>();
    }

    /**
     * Constructeur de la classe (singleton)
     * @return une instance de la classe contenant les informations des techniques 
     */
    public static ListTechnic getInstance() {
        if (instance == null)
            try {
                instance = (ListTechnic) XmlResourcesManageur.concertXmlToJava(ListTechnic.class, "technic/technicList");
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        return instance;
    }

    public Technic getTechnic(int num){
        return listTechnic.get(num-1);
    }

}