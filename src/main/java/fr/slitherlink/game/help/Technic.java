package fr.slitherlink.game.help;

/**
 * Classe représentant les informations d'une technique
 * @author Bruneau Antoine
 * @version 1, 23/02/2023
 */
public class Technic {

    private String title;
    private String desc;
    private String imagePath; // le chemin vers l'image représentant la technique

    public Technic(String title, String desc, String imagePath) {
        this.title = title;
        this.desc = desc;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImagePath() {
        return imagePath;
    }
}