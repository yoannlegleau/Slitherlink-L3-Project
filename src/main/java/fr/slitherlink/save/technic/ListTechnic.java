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

    private ListTechnic() {
        try {
            instance = (ListTechnic) XmlResourcesManageur.concertXmlToJava(ListTechnic.class, "technic/technicList");
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized ListTechnic generate() {
        if (instance == null)
            new ListTechnic();
        return instance;
    }

    public Technic getTechnic(int id){
        return listTechnic.get(id);
    }

    public static void main(String[] args) {
        ListTechnic listTechnic = ListTechnic.generate();
        System.out.println(listTechnic.getTechnic(0).getTitle());
    }

}