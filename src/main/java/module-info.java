module fr.slitherlink {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.xml.bind;

    opens fr.slitherlink.save to java.xml.bind;
    opens fr.slitherlink.app to javafx.fxml;
    exports fr.slitherlink.app;
    exports fr.slitherlink.app.fx_controlleur;
    opens fr.slitherlink.app.fx_controlleur to javafx.fxml;
    exports fr.slitherlink.app.component;
    opens fr.slitherlink.app.component to javafx.fxml;
    exports fr.slitherlink.game;
    opens fr.slitherlink.game to javafx.fxml;
    opens fr.slitherlink.save.puzzle to java.xml.bind;
    opens fr.slitherlink.save.gamesave to java.xml.bind;
}