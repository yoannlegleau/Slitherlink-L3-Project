module fr.slitherlink {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.xml.bind;

    opens fr.slitherlink.save.puzzle to java.xml.bind;
    opens fr.slitherlink.save.gamesave;
    opens fr.slitherlink.save.feePlay to java.xml.bind;
    opens fr.slitherlink.save.adventureMode to java.xml.bind;


    opens fr.slitherlink.app to javafx.fxml;
    exports fr.slitherlink.app;

    exports fr.slitherlink.app.fx_controlleur;
    opens fr.slitherlink.app.fx_controlleur to javafx.fxml;

    exports fr.slitherlink.app.fx_controlleur.component;
    opens fr.slitherlink.app.fx_controlleur.component to javafx.fxml;

    exports fr.slitherlink.app.gui;
    opens fr.slitherlink.app.gui to javafx.fxml;

    exports fr.slitherlink.game;
    opens fr.slitherlink.game to javafx.fxml;

    exports fr.slitherlink.save.puzzle;
    exports fr.slitherlink.save.adventureMode;


}