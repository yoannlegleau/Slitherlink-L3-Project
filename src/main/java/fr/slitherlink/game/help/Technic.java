package fr.slitherlink.game.help;

/**
 * Classe représentant les informations d'une technique
 * @author Bruneau Antoine
 * @version 1, 23/02/2023
 */
public class Technic {

    private String title;
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