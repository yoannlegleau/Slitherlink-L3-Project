package fr.slitherlink.game.help;

import java.util.ArrayList;
import fr.slitherlink.save.XmlResourcesManageur;
import javax.xml.bind.JAXBException;

/**
 * Classe stockant les techniques de résolution
 * @author Bruneau Antoine
 * @version 1, 31/03/2023
 */

public class ListTechnic {

    private static ListTechnic instance = null;
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
            this.listTechnic = (ArrayList<Technic>) XmlResourcesManageur.concertXmlToJava(ArrayList<Technic>.class, "technics/technicsList");
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}