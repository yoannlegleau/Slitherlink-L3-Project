package fr.slitherlink.game.help;

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

@XmlRootElement
public class ListTechnic {

    @XmlTransient
    private static ListTechnic instance = null;

    @XmlElement(name = "technic")
    private ArrayList<Technic> listTechnic;

    private ListTechnic() {
        loadTechnics();
    }

    public static ListTechnic generate() {
        if (instance == null) {
            instance = new ListTechnic();
        }
        return instance;
    }

    public void loadTechnics(){
        try {
            instance = (ListTechnic) XmlResourcesManageur.concertXmlToJava(ListTechnic.class, "technics/technicsList");
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public Technic getTechnic(int id){
        return listTechnic.get(id);
    }

}