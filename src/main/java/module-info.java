module fr.slitherlink {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.xml.bind;
    requires firebase.admin;
    requires com.google.api.client;
    requires com.google.api.client.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires com.google.api.client.extensions.jetty.auth;
    requires google.api.client;
    requires com.google.api.client.json.jackson2;
    requires google.api.services.oauth2.v2.rev50;

    opens fr.slitherlink.save.puzzle to java.xml.bind;
    opens fr.slitherlink.save.gamesave;
    opens fr.slitherlink.save.feePlay to java.xml.bind;
    opens fr.slitherlink.save.adventureMode to java.xml.bind;
    opens fr.slitherlink.save.technic to java.xml.bind;


    opens fr.slitherlink.app to javafx.fxml;
    exports fr.slitherlink.app;

    exports fr.slitherlink.app.gui;
    opens fr.slitherlink.app.gui to javafx.fxml;

    exports fr.slitherlink.game;
    opens fr.slitherlink.game to javafx.fxml;

    // exports fr.slitherlink.save.puzzle;
    exports fr.slitherlink.save.puzzle;
    exports fr.slitherlink.save.adventureMode;


}