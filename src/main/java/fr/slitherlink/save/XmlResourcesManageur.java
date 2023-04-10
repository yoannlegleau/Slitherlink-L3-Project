package fr.slitherlink.save;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 */
public class XmlResourcesManageur {

    public static final String RESOURCES_PATH = "src/main/resources/fr/slitherlink/";

    /**
     * sauvegarde un fichier xml dans les resources grace au marshaller de jaxB
     * @param o Object a sauvegarder
     * @param fileName Chemain d'accès au fichier à partir des resource et sans l'extension ex:"level/1"
     * @throws JAXBException
     */
    public static void concertJavaToXml(Object o , String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(o,new File(RESOURCES_PATH +fileName+".xml"));
    }

    /**
     * Récupère un fichier xml des resources gras a l'Unmarshaller de jaxB
     * @param c Classe de l'objet
     * @param fileName Chemain d'accès au fichier à partir des resource et sans l'extension ex:"level/1"
     * @return Objectde la classe c
     * @throws JAXBException
     */
    public static <T> T  concertXmlToJava(Class<T> c , String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File xmlFile = new File(RESOURCES_PATH +fileName+".xml");

        T unmarshal = null;
        try {
            unmarshal = (T) unmarshaller.unmarshal(xmlFile);
        }
        catch (IllegalArgumentException ignored){}

        return unmarshal;
    }

}
